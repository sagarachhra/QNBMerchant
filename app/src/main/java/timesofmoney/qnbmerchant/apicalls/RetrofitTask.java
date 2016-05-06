package timesofmoney.qnbmerchant.apicalls;

import android.util.Log;

import java.util.concurrent.TimeUnit;


import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


/**
 * Created by kunalk on 1/29/2016.
 */

public class RetrofitTask {

    private IRetrofitTask callback;


    private static volatile RetrofitTask retrofitTask;
    private IRetrofit iRetrofit;

    public static RetrofitTask getInstance() {
        if (retrofitTask == null) {
            retrofitTask = new RetrofitTask();
        }

        return retrofitTask;
    }


    public static void makeNull()
    {
        retrofitTask=null;
    }
    public RetrofitTask() {


        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder.readTimeout(30, TimeUnit.SECONDS);
        builder.connectTimeout(30, TimeUnit.SECONDS);

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(interceptor);
        OkHttpClient client = builder.build();
        //10.158.202.61:8080


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://movit.timesofmoney.com/mps/")
                .client(client)
                .build();

        iRetrofit = retrofit.create(IRetrofit.class);

    }

    public void executeGetKey(String param, final IRetrofitTask callback) {


        param = "qnb|ME|" + param;


        Call<ResponseBody> responseBodyCall = iRetrofit.getSecurityKey(param);

        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Response<ResponseBody> response) {

             if(response.code()==200) {
                 try {
                     String apiResponse = response.body().string();
                     callback.handleResponse(true, apiResponse);
                 } catch (Exception e) {
                     e.printStackTrace();
                     callback.handleResponse(false, e.getMessage());
                     makeNull();
                 }
             }else
             {
                 callback.handleResponse(false, "Server code "+response.code());

             }
            }

            @Override
            public void onFailure(Throwable t) {

                callback.handleResponse(false, t.getMessage());
            }
        });

    }

    public void executeTask(String param, final IRetrofitTask callback) {



        String postParam = "qnb|ME|"  + "|" + param;


        Call<ResponseBody> responseBodyCall = iRetrofit.executeAPI(postParam);

        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Response<ResponseBody> response) {

                if(response.code()==200) {
                    try {
                        String apiResponse = response.body().string();
                        callback.handleResponse(true, apiResponse);
                    } catch (Exception e) {
                        e.printStackTrace();
                        callback.handleResponse(false, e.getMessage());
                        makeNull();
                    }
                }else
                {
                    callback.handleResponse(false, "Server code "+response.code());
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.v("TAG"," Fail "+t.toString());
                callback.handleResponse(false, t.toString());
            }
        });

    }



    public interface IRetrofitTask {
         void handleResponse(boolean isSuccess, String response);
    }

}
