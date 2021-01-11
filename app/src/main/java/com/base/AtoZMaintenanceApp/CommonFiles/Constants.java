package com.base.AtoZMaintenanceApp.CommonFiles;

public class Constants {


    private static Constants constants;

    public static Constants getInstance() {
        if (constants == null) {
            constants = new Constants();
        }
        return constants;
    }

    public static final String IS_LOGGED_IN = "is_logged_in";
    public static final String SCHEDULED_DATE_FORMAT = "dd MMM, yyyy,HH:mm";
    public static String internetCheck = "http://13.127.232.32/speed_test/download/";
    public final Integer NoInternetCase = 900 ;
    public static String left = "left";
    public static String right = "right";
    public static String Cancel = "Cancel";
    public static String LOGOUT = "LOGOUT";
    public static String EXIT = "EXIT";
    public static String RETRY = "RETRY";
    public static String DISMISS = "DISMISS";
    public static String OK = "OK";

    //otp page message
    public static String OTPMEASSAGE = "";

    public static String getOTPMEASSAGE() {
        return OTPMEASSAGE;
    }

    public static void setOTPMEASSAGE(String OTPMEASSAGE) {
        Constants.OTPMEASSAGE = OTPMEASSAGE;
    }

    public static boolean parentWeak = false;
    public static boolean botWeak = false;

    public static boolean isParentWeak() {
        return parentWeak;
    }

    public static void setParentWeak(boolean parentWeak) {
        Constants.parentWeak = parentWeak;
    }

    public static boolean isBotWeak() {
        return botWeak;
    }

    public static void setBotWeak(boolean botWeak) {
        Constants.botWeak = botWeak;
    }

    //Success page message
    public static String SUCCESSLOGIN = "";

    public static String getSUCCESSLOGIN() {
        return SUCCESSLOGIN;
    }

    public static void setSUCCESSLOGIN(String SUCCESSLOGIN) {
        Constants.SUCCESSLOGIN = SUCCESSLOGIN;
    }
    //Success page message on Bot Associated
    public static String SUCCESSLOGINONBOTASSOCIATION = "";

    public static String getSUCCESSLOGINONBOTASSOCIATION() {
        return SUCCESSLOGINONBOTASSOCIATION;
    }

    public static void setSUCCESSLOGINONBOTASSOCIATION(String SUCCESSLOGINONBOTASSOCIATION) {
        Constants.SUCCESSLOGINONBOTASSOCIATION = SUCCESSLOGINONBOTASSOCIATION;
    }
    public final Integer exitApp = 800;
    public final Integer logout = 810;
    public final Integer loginScreen = 100;
    public final Integer feedback = 101;
    public final Integer assessment = 102;
    public String user = "user";
    public String trainer = "trainer";
    public String Branch = "Branch";
    public String Unit = "Unit";
    public String EMPCode = "EMPCode";
    public String FilterList = "FilterList";
}
