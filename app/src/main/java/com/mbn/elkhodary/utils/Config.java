package com.mbn.elkhodary.utils;

public class Config {

    public static final boolean IS_DOWNLOAD_SHOW = false; // set true to show Downloadable product in account page

    public static final boolean IS_VARIATION_POPUP_SHOW = false;  // set false to show variation in detail page instead of popup
    public static final boolean IS_MANAGE_FROM_SERVER = true; // set false to manage locally

    //    =====================Set Following to set following functionality locally=====================
    //TODO:Set this only when  IS_MANAGE_FROM_SERVER=true
    public static final boolean IS_SLIDER_SHOW = true;      // set false to disable slider
    public static final boolean IS_LOGIN_SHOW = true;       // set false to disable login screen before start application
    public static final boolean IS_INFINITE_LAYOUT = true;  // set false to set Default Home Layout
    public static final String LANGUAGE = "en";             // set Your Language Here
    public static boolean IS_RTL = false;    // set true to show Application RTL
    public static boolean IS_CATALOG_MODE_OPTION = false;   //set true when you want to disable addtocart and buy now  button

    public static boolean OTPVerification = true; //set true when you want to disable OTP verification  and active OTP verification

    public static boolean SHIMMER_VIEW = true; //set true when you want to disable progressbar and active shimmer view

    public static boolean WOO_API_DELIVER_PINCODE = false; //set true when you want to disable progressbar and active shimmer view

//    ==============================================================================================

}
