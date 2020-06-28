package com.mbn.elkhodary.utils;

import com.ciyashop.library.apicall.URLS;

public class APIS {

    //TODO:Copy and Paste URL and Key Below from Admin Panel.
    public final String APP_URL = "https://www.stop4web.com/store03/";
    public final String WOO_MAIN_URL = APP_URL + "wp-json/wc/v2/";
    public final String MAIN_URL = APP_URL + "wp-json/pgs-woo-api/v1/";

    public static final String CONSUMERKEY = "FmCr5oif1iPV";
    public static final String CONSUMERSECRET = "xXxfueuoZkve9vjdUArUCvStcw6dbSvB3tc2thGNgEZ0lFp9";
    public static final String OAUTH_TOKEN = "MKXEatTsGAymbjxZ2twtRrzo";
    public static final String OAUTH_TOKEN_SECRET = "MdXwleUTbd05m7pM5spA390f2hnJn4dBQHPrpAW4StYwsMr6";

    public static final String WOOCONSUMERKEY = "ck_465e1ea2e675d28e96d199f24dac19685bbc7dd9";
    public static final String WOOCONSUMERSECRET = "cs_d32c8d182738104fe835b1fc0023c30502322cda";
    public static final String version="3.2.3";

    public APIS() {
        URLS.APP_URL = APP_URL;
        URLS.NATIVE_API = APP_URL + "wp-json/wc/v3/";
        URLS.WOO_MAIN_URL = WOO_MAIN_URL;
        URLS.MAIN_URL = MAIN_URL;
        URLS.version = version;
        URLS.CONSUMERKEY = CONSUMERKEY;
        URLS.CONSUMERSECRET = CONSUMERSECRET;
        URLS.OAUTH_TOKEN = OAUTH_TOKEN;
        URLS.OAUTH_TOKEN_SECRET = OAUTH_TOKEN_SECRET;
        URLS.WOOCONSUMERKEY = WOOCONSUMERKEY;
        URLS.WOOCONSUMERSECRET = WOOCONSUMERSECRET;
    }
}