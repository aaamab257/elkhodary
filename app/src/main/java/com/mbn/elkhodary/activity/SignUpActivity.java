package com.mbn.elkhodary.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.ciyashop.library.apicall.PostApi;
import com.ciyashop.library.apicall.URLS;
import com.ciyashop.library.apicall.interfaces.OnResponseListner;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mbn.elkhodary.R;
import com.mbn.elkhodary.customview.edittext.EditTextRegular;
import com.mbn.elkhodary.customview.textview.TextViewLight;
import com.mbn.elkhodary.customview.textview.TextViewRegular;
import com.mbn.elkhodary.javaclasses.SyncWishList;
import com.mbn.elkhodary.model.LogIn;
import com.mbn.elkhodary.utils.BaseActivity;
import com.mbn.elkhodary.utils.Config;
import com.mbn.elkhodary.utils.Constant;
import com.mbn.elkhodary.utils.CustomToast;
import com.mbn.elkhodary.utils.RequestParamUtils;
import com.mbn.elkhodary.utils.Utils;
import com.rilixtech.CountryCodePicker;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUpActivity extends BaseActivity implements OnResponseListner {


    private static final String TAG = SignUpActivity.class.getSimpleName();

    @BindView(R.id.etUsername)
    EditTextRegular etUsername;

    @BindView(R.id.etEmail)
    EditTextRegular etEmail;

    @BindView(R.id.etContact)
    EditTextRegular etContact;

    @BindView(R.id.etPass)
    EditTextRegular etPass;

    @BindView(R.id.etConfirmPass)
    EditTextRegular etConfirmPass;

    @BindView(R.id.ivLogo)
    ImageView ivLogo;

    @BindView(R.id.tvAlreadyAccount)
    TextViewLight tvAlreadyAccount;

    @BindView(R.id.tvSignInNow)
    TextViewLight tvSignInNow;

    @BindView(R.id.tvSignUp)
    TextViewLight tvSignUp;

    @BindView(R.id.ccp)
    CountryCodePicker ccp;
    AlertDialog alertDialog;
    private FirebaseAuth                                          mAuth;
    private PhoneAuthProvider.ForceResendingToken                 mResendToken;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private String                                                mVerificationId;
    private CustomToast                                           toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_ups);
        ButterKnife.bind(this);
        toast = new CustomToast(this);
        mAuth = FirebaseAuth.getInstance();
        setScreenLayoutDirection();
        setThemeColor();

        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(PhoneAuthCredential credential) {
                Log.e(TAG, "onVerificationCompleted:" + credential);
                signInWithPhoneAuthCredential(credential);
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                // This callback is invoked in an invalid request for verification is made,
                // for instance if the the phone number format is not valid.
                Log.e(TAG, "onVerificationFailed", e);

                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    // Invalid request
                    // [START_EXCLUDE]
                    if (alertDialog != null) {
                        alertDialog.dismiss();
                    }
                    Toast.makeText(SignUpActivity.this, getString(R.string.invalid_phone_number), Toast.LENGTH_SHORT).show();
                    // [END_EXCLUDE]
                } else if (e instanceof FirebaseTooManyRequestsException) {
                    // The SMS quota for the project has been exceeded
                    // [START_EXCLUDE]
                    Toast.makeText(SignUpActivity.this, getString(R.string.quoto_exceeded), Toast.LENGTH_SHORT).show();
                    // [END_EXCLUDE]
                }

               /* // Show a message and update the UI
                // [START_EXCLUDE]
                updateUI(STATE_VERIFY_FAILED);
                // [END_EXCLUDE]*/
            }

            @Override
            public void onCodeSent(String verificationId, PhoneAuthProvider.ForceResendingToken token) {
                // The SMS verification code has been sent to the provided phone number, we
                // now need to ask the user to enter the code and then construct a credential
                // by combining the code with a verification ID.
                Log.e(TAG, "onCodeSent:" + verificationId);
                if (alertDialog != null) {
                    alertDialog.show();
                }
                // Save verification ID and resending token so we can use them later
                mVerificationId = verificationId;
                mResendToken = token;
            }
        };
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {

        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.e(TAG, "signInWithCredential:success");
                            FirebaseUser user = task.getResult().getUser();

                            if (alertDialog != null) {
                                alertDialog.dismiss();
                            }
                            registerUser();
                        } else {
                            // Sign in failed, display a message and update the UI
                            Log.e(TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                                // [START_EXCLUDE silent]
                                Toast.makeText(SignUpActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                // [END_EXCLUDE]
                            }
                           /* // [START_EXCLUDE silent]
                            // Update UI
                            updateUI(STATE_SIGNIN_FAILED);
                            // [END_EXCLUDE]*/
                        }
                    }
                });
    }

    @OnClick(R.id.tvSignInNow)
    public void tvSignInNowClick() {
        Intent intent = new Intent(SignUpActivity.this, LogInActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.ivBlackBackButton)
    public void ivBackClick() {
        onBackPressed();
    }


    public void setThemeColor() {

        if (Constant.APPLOGO != null && !Constant.APPLOGO.equals("")) {
            Picasso.with(this).load(Constant.APPLOGO).error(R.drawable.logo).into(ivLogo);
        }
        Drawable mDrawable = getResources().getDrawable(R.drawable.login);
        mDrawable.setColorFilter(new
                PorterDuffColorFilter(Color.parseColor(getPreferences().getString(Constant.APP_COLOR, Constant.PRIMARY_COLOR)), PorterDuff.Mode.OVERLAY));


        tvAlreadyAccount.setTextColor(Color.parseColor(getPreferences().getString(Constant.APP_COLOR, Constant.PRIMARY_COLOR)));
        tvSignInNow.setTextColor(Color.parseColor(getPreferences().getString(Constant.APP_COLOR, Constant.PRIMARY_COLOR)));
        tvSignUp.setBackgroundColor(Color.parseColor(getPreferences().getString(Constant.SECOND_COLOR, Constant.SECONDARY_COLOR)));
    }

    @OnClick(R.id.tvSignUp)
    public void tvSignUpClick() {
        if (etUsername.getText().toString().length() == 0) {
            Toast.makeText(this, R.string.enter_username, Toast.LENGTH_SHORT).show();
        } else if (etEmail.getText().toString().length() == 0) {
            Toast.makeText(this, R.string.enter_email_address, Toast.LENGTH_SHORT).show();
        } else if (!Utils.isValidEmail(etEmail.getText().toString())) {
            Toast.makeText(this, R.string.enter_valid_email_address, Toast.LENGTH_SHORT).show();
        } else if (etContact.getText().toString().isEmpty()) {
            Toast.makeText(this, R.string.enter_contact_number, Toast.LENGTH_SHORT).show();
        } else if (etPass.getText().toString().isEmpty()) {
            Toast.makeText(this, R.string.enter_password, Toast.LENGTH_SHORT).show();
        } else if (etConfirmPass.getText().toString().isEmpty()) {
            Toast.makeText(this, R.string.enter_confirm_password, Toast.LENGTH_SHORT).show();
        } else if (etPass.getText().toString().equals(etConfirmPass.getText().toString())) {
            if (Config.OTPVerification) {
                String number = ccp.getSelectedCountryCodeWithPlus() + etContact.getText().toString().trim();
                Log.e("Otp :-", number);
                ShowDialogForOTP(number);
            } else {
                registerUser();
            }

        } else {
            Toast.makeText(this, R.string.password_and_confirm_password_not_matched, Toast.LENGTH_SHORT).show();
        }
    }

    private void ShowDialogForOTP(final String number) {

        // [START start_phone_auth]
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                number,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks
        // [END start_phone_auth]

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(SignUpActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_otp_verification, null);
        dialogBuilder.setView(dialogView);

        final EditTextRegular etOTP = (EditTextRegular) dialogView.findViewById(R.id.etOTP);
        TextViewRegular tvVerificationText = dialogView.findViewById(R.id.tvVerificationText);

        TextViewRegular tvDone = (TextViewRegular) dialogView.findViewById(R.id.tvDone);

        TextViewRegular tvResend = (TextViewRegular) dialogView.findViewById(R.id.tvResend);

        tvVerificationText.setText(getResources().getString(R.string.please_type_verification_code_sent_to_in) + etContact.getText().toString());
        alertDialog = dialogBuilder.create();
        alertDialog.getWindow().getAttributes().windowAnimations = R.style.DialogTheme;


        tvDone.setBackgroundColor(Color.parseColor(getPreferences().getString(Constant.SECOND_COLOR, Constant.SECONDARY_COLOR)));
        tvResend.setBackgroundColor(Color.parseColor(getPreferences().getString(Constant.SECOND_COLOR, Constant.SECONDARY_COLOR)));
        tvDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = etOTP.getText().toString();
                if (etOTP.getText().toString().length() == 0) {
                    toast.showToast(getString(R.string.enter_verificiation_code));
                    toast.showBlackbg();
//                    etOTP.setError("Enter Verification Code");
                    return;
                }
                verifyPhoneNumberWithCode(mVerificationId, code);
            }
        });

        tvResend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(SignUpActivity.this,
                        getString(R.string.otp_sent_again) + number, Toast.LENGTH_SHORT).show();

                resendVerificationCode(number, mResendToken);
            }
        });

    /*    rvProductVariation = (RecyclerView) dialogView.findViewById(R.id.rvProductVariation);
        TextViewRegular tvDone = (TextViewRegular) dialogView.findViewById(R.id.tvDone);
        TextViewRegular tvCancel = (TextViewRegular) dialogView.findViewById(R.id.tvCancel);*/

    }

    private void verifyPhoneNumberWithCode(String mVerificationId, String code) {

        // [START verify_with_code]
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, code);
        // [END verify_with_code]
        signInWithPhoneAuthCredential(credential);
    }

    private void resendVerificationCode(String number, PhoneAuthProvider.ForceResendingToken token) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                number,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks,         // OnVerificationStateChangedCallbacks
                token);             // ForceResendingToken from callbacks
    }

    public void registerUser() {
        if (Utils.isInternetConnected(this)) {
            showProgress("");
            PostApi postApi = new PostApi(this, "create_customer", this, getlanuage());
            JSONObject object = new JSONObject();
            try {

                object.put(RequestParamUtils.email, etEmail.getText().toString());
                object.put(RequestParamUtils.username, etUsername.getText().toString());
                object.put(RequestParamUtils.mobile, ccp.getSelectedCountryCodeWithPlus() + etContact.getText().toString().trim());
                object.put(RequestParamUtils.PASSWORD, etPass.getText().toString());
                object.put(RequestParamUtils.deviceType, Constant.DEVICE_TYPE);

                String token = getPreferences().getString(RequestParamUtils.NOTIFICATION_TOKEN, "");
                object.put(RequestParamUtils.deviceToken, token);

                postApi.callPostApi(new URLS().CREATE_CUSTOMER, object.toString());

            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                Toast.makeText(this, R.string.something_went_wrong_try_after_somtime, Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, R.string.internet_not_working, Toast.LENGTH_LONG).show();
        }


    }

    @Override
    public void onResponse(final String response, String methodName) {

        if (methodName.equals(RequestParamUtils.createCustomer)) {
            if (response != null && response.length() > 0) {
                try {
                    final LogIn loginRider = new Gson().fromJson(
                            response, new TypeToken<LogIn>() {
                            }.getType());

                    JSONObject jsonObj = new JSONObject(response);
                    String status = jsonObj.getString("status");

                    if (status.equals("error")) {
                        Toast.makeText(getApplicationContext(), jsonObj.getString("message"), Toast.LENGTH_SHORT).show(); //display in long period of time
                        dismissProgress();
                    } else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //set call here
                                if (loginRider.status.equals("success")) {

                                    SharedPreferences.Editor pre = getPreferences().edit();
                                    pre.putString(RequestParamUtils.CUSTOMER, "");
                                    pre.putString(RequestParamUtils.ID, loginRider.user.id + "");
                                    pre.putString(RequestParamUtils.PASSWORD, etPass.getText().toString());
                                    pre.commit();


                                    new SyncWishList(SignUpActivity.this).syncWishList(getPreferences().getString(RequestParamUtils.ID, ""), false);

//                                    Intent intent = new Intent(SignUpActivity.this, AccountActivity.class);
//                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(getApplicationContext(), R.string.enter_proper_detail, Toast.LENGTH_SHORT).show(); //display in long period of time
                                }
                            }
                        });
                        dismissProgress();
                    }
                } catch (Exception e) {
                    Log.e(methodName + "Gson Exception is ", e.getMessage());
                    Toast.makeText(getApplicationContext(), R.string.something_went_wrong, Toast.LENGTH_SHORT).show(); //display in long period of time
                }
            }
        }
    }


}
