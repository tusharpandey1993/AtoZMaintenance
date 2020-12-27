package com.base.BaseProject.CommonFiles;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author Manoj Prasad Helper class for shared preferences
 */
public class AppPreferences {
    public static final String KEY_METER_VALUE = "ParentalPreferences";
    private static final String KEY_PREF_LOGGEDIN = "login";
    private static final String KEY_PREF_CATEGORY = "loginCategory";
    private static final String KEY_PREF_MOBILE_NUMBER = "mobilenum";
    private static final String KEY_PREF_EMAIL = "email";
    private static final String KEY_PREF_NAME = "name";
    private static final String KEY_PREF_TOKEN = "token";
    private static final String KEY_PREF_QBCONFIG = "qbconfig";
    private static final String BOT_ID = "botid";
    private static final String AUTH_ID = "authid";
    private static final String flagSaveData = "flagSaveData";
    private static final String CountryCode = "CountryCode";
    private static final String tempMobile = "tempMobile";
    private static final String tempEmail = "tempEmail";
    private static final String  KEY_PREF_UserName = "UserName";
    private static final String KEY_IS_COACH_MARKS_SHOWN_APP_STORE = "isCoachMarkShownAppStore";
    private static final String  Source_To_Desitnation = "Source_To_Desitnation";
    private static final String User_Type = "User_Type";
    private static final String UserId = "UserId";
    private static final String Employee_Code = "Employee_Code";
    private static final String ContactApiResponse = "ContactApiResponse";
    private static final String ContactTrainerApiResponse = "ContactTrainerApiResponse";
    private static final String FeedbackApiResponse = "FeedbackApiResponse";
    private static final String VideoApiResponse = "VideoApiResponse";
    private static final String isUserModeTrainer = "isUserModeTrainer";


    private static AppPreferences instance;


    public static synchronized AppPreferences getInstance() {
        if (instance == null) {
            instance = new AppPreferences();
        }
        return instance;
    }

    public static boolean getIsShowedShowcaseAppStore(Context context) {
        return getPreferencesConstants(context).getBoolean( KEY_IS_COACH_MARKS_SHOWN_APP_STORE,false);
//        return getBoolean(context, KEY_IS_COACH_MARKS_SHOWN_APP_STORE, false);
    }

    public static void setIsShowedShowcaseAppStore(Context context, boolean value) {
        getPreferencesConstants(context).edit().putBoolean( KEY_IS_COACH_MARKS_SHOWN_APP_STORE, value).apply();
//        putBoolean(context, KEY_IS_COACH_MARKS_SHOWN_APP_STORE, value);
    }

    public static boolean getisUserModeTrainer(Context context) {
        return getPreferences(context).getBoolean( isUserModeTrainer,false);
    }

    public static void setisUserModeTrainer(Context context, boolean value) {
        getPreferences(context).edit().putBoolean( isUserModeTrainer, value).apply();
    }


    private static SharedPreferences getPreferences(Context context) {
        return context.getSharedPreferences("TrigApp", Context.MODE_PRIVATE);
    }

    private static SharedPreferences getPreferencesConstants(Context context) {
        return context.getSharedPreferences("TrigAppConstants", Context.MODE_PRIVATE);
    }

    public static void putLong(Context context, String key, long value) {
        getPreferences(context).edit().putLong(key, value).commit();
    }

    public static long getLong(Context context, String key) {
        return getPreferences(context).getLong(key, 0);
    }

    private static void putString(Context context, String key, String value) {
        getPreferences(context).edit().putString(key, value).commit();
    }

    private static String getString(Context context, String key, String defValue) {
        return getPreferences(context).getString(key, defValue);
    }

    public static int getInt(Context context, String key) {
        return getPreferences(context).getInt(KEY_METER_VALUE + key, 0);
    }

    public static void putInt(Context context, String key, int value) {
        getPreferences(context).edit().putInt(KEY_METER_VALUE + key, value).commit();
    }

    public static boolean getBoolean(Context context, String key, boolean def) {
        return getPreferences(context).getBoolean(KEY_METER_VALUE + key, def);
    }

    public static void putBoolean(Context context, String key, boolean value) {
        getPreferences(context).edit().putBoolean(KEY_METER_VALUE + key, value).commit();
    }

    public static String getStringPrefrence(Context context, String type) {
        return getString(context, KEY_METER_VALUE + type, "");
    }

    public static void setStringPrefrence(Context context, String type, String value) {
        putString(context, KEY_METER_VALUE + type, value);
    }

    public static void clear(Context context) {
        getPreferences(context).edit().clear().commit();
    }


    /**
     * @param context the context
     * @param isLogin flag for login
     */
    public static void setLoginPref(Context context, boolean isLogin) {
        putBoolean(context, KEY_PREF_LOGGEDIN, isLogin);
    }

    /**
     * @param context the context
     * @return the sync count
     */
    public static boolean isLoggedIn(Context context) {
        return getBoolean(context, KEY_PREF_LOGGEDIN, false);
    }

    public static void setMobileNumber(Context context, String color) {
        setStringPrefrence(context, KEY_PREF_MOBILE_NUMBER, color);
    }

    public static String getMobileNumber(Context context) {
        String mobilenumber = getStringPrefrence(context, KEY_PREF_MOBILE_NUMBER);
        return mobilenumber;
    }

    public static void setUserName (Context context, String username) {
        setStringPrefrence(context, KEY_PREF_UserName, username);
    }

    public static String getUserName (Context context) {
        String UserName = getStringPrefrence(context, KEY_PREF_UserName);
        return UserName;
    }


    public static int getSource_To_Desitnation(Context context) {
        return getPreferences(context).getInt(Source_To_Desitnation, -1);
    }

    public static void setSource_To_Desitnation(Context context, int value) {
        getPreferences(context).edit().putInt(Source_To_Desitnation, value).commit();
    }


    public static void setName(Context context, String value) {
        setStringPrefrence(context, KEY_PREF_NAME, value);
    }

    public static String getName(Context context) {
        String value = getStringPrefrence(context, KEY_PREF_NAME);
        return value;
    }


    public static void setUserId(Context context, String value) {
        setStringPrefrence(context, UserId, value);
    }

    public static String getUserId(Context context) {
        String value = getStringPrefrence(context, UserId);
        return value;
    }


    public static void setUser_Type(Context context, String value) {
        setStringPrefrence(context, User_Type, value);
    }

    public static String getUser_Type(Context context) {
        String value = getStringPrefrence(context, User_Type);
        return value;
    }

    public static void setEmployee_Code(Context context, String value) {
        setStringPrefrence(context, Employee_Code, value);
    }

    public static String getEmployee_Code(Context context) {
        String value = getStringPrefrence(context, Employee_Code);
        return value;
    }

    public static void setContactApiResponse(Context context, String value) {
        setStringPrefrence(context, ContactApiResponse, value);
    }

    public static String getContactApiResponse(Context context) {
        String value = getStringPrefrence(context, ContactApiResponse);
        return value;
    }
    public static void setContactTrainerApiResponse(Context context, String value) {
        setStringPrefrence(context, ContactTrainerApiResponse, value);
    }

    public static String getContactTrainerApiResponse(Context context) {
        String value = getStringPrefrence(context, ContactTrainerApiResponse);
        return value;
    }
    public static void setFeedbackApiResponse(Context context, String value) {
        setStringPrefrence(context, FeedbackApiResponse, value);
    }

    public static String getFeedbackApiResponse(Context context) {
        String value = getStringPrefrence(context, FeedbackApiResponse);
        return value;
    }

    public static void setVideoApiResponse(Context context, String value) {
        setStringPrefrence(context, VideoApiResponse, value);
    }

    public static String setVideoApiResponse(Context context) {
        String value = getStringPrefrence(context, VideoApiResponse);
        return value;
    }

    public static void setEmail(Context context, String value) {
        setStringPrefrence(context, KEY_PREF_EMAIL, value);
    }

    public static String getEmail(Context context) {
        String value = getStringPrefrence(context, KEY_PREF_EMAIL);
        return value;
    }


    public static void setLoginCategory(Context context, String value) {
        setStringPrefrence(context, KEY_PREF_CATEGORY, value);
    }

    public static String getLoginCategory(Context context) {
        String value = getStringPrefrence(context, KEY_PREF_CATEGORY);
        return value;
    }


    public static void setToken(Context context, String value) {
        setStringPrefrence(context, KEY_PREF_TOKEN, value);
    }

    public static String getToken(Context context) {
        String value = getStringPrefrence(context, KEY_PREF_TOKEN);
        /*if (TextUtils.isEmpty(value)) {
            value = "2bd7f907b7f5b6bbd91822c0c7b835f6";
        }*/
        return value;
    }

    public static void setBotId(Context context, String value) {
        setStringPrefrence(context, BOT_ID, value);
    }

    public static String getBotId(Context context) {
        String value1 = getStringPrefrence(context, BOT_ID);
        /*if (TextUtils.isEmpty(value)) {
            value = "2bd7f907b7f5b6bbd91822c0c7b835f6";
        }*/
        return value1;
    }


    public static void setAuthId(Context context, String value) {
        setStringPrefrence(context, AUTH_ID, value);
    }

    public static String getAuthId(Context context) {
        String value1 = getStringPrefrence(context, AUTH_ID);
        /*if (TextUtils.isEmpty(value)) {
            value = "2bd7f907b7f5b6bbd91822c0c7b835f6";
        }*/
        return value1;
    }

    public static void saveQbConfig(Context context, String value) {
        setStringPrefrence(context, KEY_PREF_QBCONFIG, value);
    }

    public static String getQbConfig(Context context) {
        return getStringPrefrence(context, KEY_PREF_QBCONFIG);
    }

    public static void setSaveSuccess(Context context, String value) {
        setStringPrefrence(context, flagSaveData, value);
    }

    public static String getSaveSuccess(Context context) {
        return getStringPrefrence(context, flagSaveData);
    }

    public static void setCountryCode(Context context, String value) {
        setStringPrefrence(context, CountryCode, value);
    }

    public static String getCountryCode(Context context) {
        return getStringPrefrence(context, CountryCode);
    }

    public static void setTempMobile(Context context, String value) {
        setStringPrefrence(context, tempMobile, value);
    }

    public static String getTempMobile(Context context) {
        return getStringPrefrence(context, tempMobile);
    }

    public static void setTempEmail(Context context, String value) {
        setStringPrefrence(context, tempEmail, value);
    }

    public static String getTempEmail(Context context) {
        return getStringPrefrence(context, tempEmail);
    }
}
