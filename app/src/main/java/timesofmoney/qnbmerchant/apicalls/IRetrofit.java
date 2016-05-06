package timesofmoney.qnbmerchant.apicalls;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by kunalk on 1/20/2016.
 */
public interface IRetrofit {
    @FormUrlEncoded
    @POST("mobileRequestHandler")
    Call<ResponseBody> executeAPI(@Field("merchantMobileRequestXML") String mobileRequestXML);


    @GET("mobileRequestHandler")
    Call<ResponseBody> getSecurityKey(@Query("merchantMobileRequestXML") String mobileRequestXML);


}
