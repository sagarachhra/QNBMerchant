package timesofmoney.qnbmerchant;

import android.app.Application;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;


import timesofmoney.qnbmerchant.models.MerchantProfile;
import timesofmoney.qnbmerchant.utilities.AppSettings;

/**
 * Created by pankajp on 18-04-2016.
 */
public class QNBMerchantApp extends Application {

    private static QNBMerchantApp qnbMerchantApp;

    public static MerchantProfile getMerchantProfile() {
        return objMerchantProfile;
    }

    public static void setMerchantProfile(MerchantProfile objMerchantProfile) {
        QNBMerchantApp.objMerchantProfile = objMerchantProfile;
    }

    public static QNBMerchantApp getQnbMerchantApp() {
        return qnbMerchantApp;
    }

    public static void setQnbMerchantApp(QNBMerchantApp qnbMerchantApp) {
        QNBMerchantApp.qnbMerchantApp = qnbMerchantApp;
    }

    private static MerchantProfile objMerchantProfile;



    public  String getKey()
    {
        return AppSettings.getData(this, AppSettings.KEY);
    }

    public  String getSessionID()
    {
        String s =AppSettings.getData(this,AppSettings.SESSION_ID);

        if(TextUtils.isEmpty(s))
        return "0";

        return s;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        qnbMerchantApp = this;
    }

    public String getIMEINo()
    {
        TelephonyManager telephonyManager = (TelephonyManager)getSystemService(TELEPHONY_SERVICE);
        return telephonyManager.getDeviceId();
    }
    public static QNBMerchantApp getInstance() {
        return qnbMerchantApp;
    }


}
