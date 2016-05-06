package timesofmoney.qnbmerchant.utilities;

import android.content.Context;
import android.content.SharedPreferences;

import timesofmoney.qnbmerchant.models.MerchantProfile;

/**
 * Created by kunalk on 4/13/2016.
 */
public class AppSettings {

    public static String MOBILE_NUMBER="mobile";
    public static String KEY="key";
    public static String SESSION_ID="sessionid";
    public static String MERCHANT_ID="merchantid";
    public static String LAST_NAME="lastname";
    public static String ADDRESS="address";
    public static String EMAIL_ID="address";
    public static String LAST_LOGIN="lastlogin";
    public static String WALLETS="wallets";
    public static MerchantProfile objMerchantProfile;
    public static String isTOKENSENDTOSERVER="isTOKENSENDTOSERVER";
    public static String NOTIFICATIONS="NOTIFICATIONS";
    public static String ISCHECKEDTERMS="ISCHECKEDTERMS";
    public static String APPLANGUAGE="APPLANGUAGE";





    public static void putData(Context context,String key, String value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("consumer",context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }


    public static String getData(Context context,String key) {
        if(context==null)
         return null;
        SharedPreferences preferences = context.getSharedPreferences("consumer", context.MODE_PRIVATE);
        return preferences.getString(key, "");
    }
}
