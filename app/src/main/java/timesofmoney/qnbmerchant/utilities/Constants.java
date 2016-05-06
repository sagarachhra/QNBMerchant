package timesofmoney.qnbmerchant.utilities;

import android.content.Context;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.widget.Toast;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import timesofmoney.qnbmerchant.QNBMerchantApp;


/**
 * Created by kunalk on 4/7/2016.
 */
public class Constants {

    public static final int SUCCESS_P2P = 0;
    public static final int SUCCESS_P2M = 1;
    public static final int SUCCESS_OTP = 2;


    public static boolean isNetworkAvailable(Context context) {
        return ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo() != null;
    }

    private static String bytesToHexString(byte[] bytes) {
        // http://stackoverflow.com/questions/332079
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }


    public static String getHash(byte[] bytes) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.update(bytes);
            return bytesToHexString(digest.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }


    public static boolean isValidEmail(String emailStr) {
        Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }


    public static void showToast(Context context, String msg) {
        if (context != null)
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public static String getReqXML(String reqType, String reqDetails) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:SS");

        String xml = "<?xml version='1.0' encoding='UTF-8'?>\n" +
                "<MpsXml>\n" +
                "\t<Header>\n" +
                "\t\t<ChannelId>APP</ChannelId>\n" +
                "\t\t<Timestamp>" + sdf.format(calendar.getTime()) + "</Timestamp>\n" +
                "\t\t<SessionId>" + QNBMerchantApp.getInstance().getSessionID() + "</SessionId>\n" +
                "\t\t<ServiceProvider>qnb</ServiceProvider>\n" +
                "\t</Header>\n" +
                "\t<Request>\n" +
                "\t\t<RequestType>" + reqType + "</RequestType>\n" +
                "\t\t<UserType>ME</UserType>\n" +
                "\t</Request>\n" +
                "\t<RequestDetails>\n" +
                     reqDetails +
                "\n\t\t<ResponseURL>{ResponseURL}</ResponseURL>\n" +
                "\t\t<ResponseVar>{ResponseVar}</ResponseVar>\n" +
                "\t</RequestDetails>\n" +
                "</MpsXml>\n";

        LogUtils.Verbose("XML Req ", xml);
        return xml;
    }

    public static void changeLanguage(String language,Context context)
    {
        Locale locale = new Locale("ar");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        context.getResources().updateConfiguration(config,
                context.getResources().getDisplayMetrics());
    }

}
