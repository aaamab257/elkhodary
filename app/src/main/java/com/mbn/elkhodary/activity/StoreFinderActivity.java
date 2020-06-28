package com.mbn.elkhodary.activity;

import android.annotation.SuppressLint;

import android.os.Bundle;
import android.widget.Toast;

import com.ciyashop.library.apicall.PostApi;
import com.ciyashop.library.apicall.URLS;
import com.ciyashop.library.apicall.interfaces.OnResponseListner;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.mbn.elkhodary.R;
import com.mbn.elkhodary.model.StoreFinder;
import com.mbn.elkhodary.utils.BaseActivity;
import com.mbn.elkhodary.utils.RequestParamUtils;
import com.mbn.elkhodary.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class StoreFinderActivity extends BaseActivity implements OnMapReadyCallback, OnResponseListner {
    // Google Map

    // Latitude & Longitude
    private Double Latitude = 0.00;
    private Double Longitude = 0.00;

    List<StoreFinder.Datum> location = new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_finder);
        ButterKnife.bind(this);
        settvImage();
        hideSearchNotification();
        setToolbarTheme();
        showBackButton();
        setScreenLayoutDirection();

        getStore();


        // *** Focus & Zoom


    }

    public void getStore() {
        if (Utils.isInternetConnected(this)) {
            showProgress("");
            PostApi postApi = new PostApi(this, RequestParamUtils.getBlog, this, getlanuage());

            postApi.callPostApi(new URLS().GET_STORES + getPreferences().getString(RequestParamUtils.CurrencyText, ""), "");
        } else {
            Toast.makeText(this, R.string.internet_not_working, Toast.LENGTH_LONG).show();
        }


    }

    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap map) {
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        map.setMyLocationEnabled(true);
        map.setTrafficEnabled(true);
        map.setIndoorEnabled(true);
        map.setBuildingsEnabled(true);
        map.getUiSettings().setZoomControlsEnabled(true);
        Latitude = Double.parseDouble(location.get(0).lat);
        Longitude = Double.parseDouble(location.get(0).lng);
        LatLng coordinate = new LatLng(Latitude, Longitude);
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(coordinate, 13));

        // *** Marker (Loop)
        for (int i = 0; i < location.size(); i++) {
            Latitude = Double.parseDouble(location.get(i).lat);
            Longitude = Double.parseDouble(location.get(i).lng);
            String name = location.get(i).address;
            MarkerOptions marker = new MarkerOptions().position(new LatLng(Latitude, Longitude)).title(name);
            marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.online_store));

            map.addMarker(marker);
        }
    }

    @Override
    public void onResponse(String response, String methodName) {
        dismissProgress();
        if (response != null && response.length() > 0) {

            Gson gson = new GsonBuilder().serializeNulls().create();
            StoreFinder storeFinderRider = gson.fromJson(
                    response, new TypeToken<StoreFinder>() {
                    }.getType());

            location.addAll(storeFinderRider.data);


        }

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }
}