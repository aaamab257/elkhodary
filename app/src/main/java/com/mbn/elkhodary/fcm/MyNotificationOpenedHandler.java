package com.mbn.elkhodary.fcm;

import android.util.Log;
import android.widget.Toast;

import com.mbn.elkhodary.utils.MyApplication;
import com.mbn.elkhodary.utils.RequestParamUtils;
import com.onesignal.OSNotificationAction;
import com.onesignal.OSNotificationOpenResult;
import com.onesignal.OneSignal;

import org.json.JSONObject;

public class MyNotificationOpenedHandler implements OneSignal.NotificationOpenedHandler {
    // This fires when a notification is opened by tapping on it.
    @Override
    public void notificationOpened(OSNotificationOpenResult result) {
        OSNotificationAction.ActionType actionType = result.action.type;
        JSONObject data = result.notification.payload.additionalData;
        String activityToBeOpened;

        //While sending a Push notification from OneSignal dashboard
        // you can send an addtional data named "activityToBeOpened" and retrieve the value of it and do necessary operation
        //If key is "activityToBeOpened" and value is "AnotherActivity", then when a user clicks
        //on the notification, AnotherActivity will be opened.
        //Else, if we have not set any additional data MainActivity is opened.
//        if (data != null) {
//            activityToBeOpened = data.optString("activityToBeOpened", null);
//            if (activityToBeOpened != null && activityToBeOpened.equals("AnotherActivity")) {
//               Log.e("OneSignalExample", "customkey set with value: " + activityToBeOpened);
//                Intent intent = new Intent(MyApplication.getContext(), HomeActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
//                MyApplication.getContext().startActivity(intent);
//            } else if (activityToBeOpened != null &&
//                    activityToBeOpened.equals("MainActivity")) {
//               Log.e("OneSignalExample", "customkey set with value: " + activityToBeOpened);
//                Intent intent = new Intent(MyApplication.getContext(), HomeActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
//                MyApplication.getContext().startActivity(intent);
//            } else {
//                Intent intent = new Intent(MyApplication.getContext(), HomeActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
//                MyApplication.getContext().startActivity(intent);
//            }
//
//        }

        //If we send notification with action buttons we need to specidy the button id's and retrieve it to 
        //do the necessary operation.
        if (actionType == OSNotificationAction.ActionType.ActionTaken) {
            Log.e("OneSignalExample", "Button pressed with id: " + result.action.actionID);
            if (result.action.actionID.equals(RequestParamUtils.actionOne)) {
                Toast.makeText(MyApplication.getContext(), "ActionOne Button was pressed", Toast.LENGTH_LONG).show();
            } else if (result.action.actionID.equals(RequestParamUtils.actionTwo)) {
                Toast.makeText(MyApplication.getContext(), "ActionTwo Button was pressed", Toast.LENGTH_LONG).show();
            }
        }
    }
}