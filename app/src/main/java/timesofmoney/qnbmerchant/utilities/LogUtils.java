package timesofmoney.qnbmerchant.utilities;

import android.util.Log;

import timesofmoney.qnbmerchant.BuildConfig;


/**
 * Created by kunalk on 10/27/2015.
 */
public class LogUtils {


    public static void Verbose(String tag,String message)
    {

        if(BuildConfig.DEBUG)
            Log.v(tag, message);

    }

    public static void Exception(Exception e)
    {

        if(BuildConfig.DEBUG)
           e.printStackTrace();

    }

    public static void Exception(Exception e,String p)
    {

        if(BuildConfig.DEBUG)
            e.printStackTrace();
        Log.v("Exception",p);

    }
}
