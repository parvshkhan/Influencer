package influencer.com.influencer.activities.restclient;


import com.google.android.gms.common.api.Response;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import influencer.com.influencer.activities.apiResponses.registerAPI.FacebookApi;
import influencer.com.influencer.activities.apiResponses.registerAPI.ForgetPwdAPI;
import influencer.com.influencer.activities.apiResponses.registerAPI.LoginAPI;
import influencer.com.influencer.activities.apiResponses.registerAPI.ProfileHomeAPI;
import influencer.com.influencer.activities.apiResponses.registerAPI.RegisterAPI;
import influencer.com.influencer.activities.apiResponses.registerAPI.UserProfileAPI;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public class RestClient {


    public static GitApiInterface getClient() {

        String baseUrl = "http://192.168.0.61/InfluencerAPI/";

        OkHttpClient client = new OkHttpClient.Builder ( )
                .connectTimeout ( 100, TimeUnit.SECONDS )
                .readTimeout ( 100, TimeUnit.SECONDS ).build ( );
        Retrofit retrofit = new Retrofit.Builder ( )
                .baseUrl ( baseUrl ).client ( client )
                .addConverterFactory ( GsonConverterFactory.create ( new Gson ( ) ) ).build ( );


        HttpLoggingInterceptor logging = new HttpLoggingInterceptor ( );
        logging.setLevel ( HttpLoggingInterceptor.Level.BODY );


        GitApiInterface gitApiInterface = retrofit.create ( GitApiInterface.class );

        return gitApiInterface;

    }


    public interface GitApiInterface {


        // 1 Register API

        @FormUrlEncoded
        @POST("Register")
        Call <RegisterAPI> registerAPI(@Field("Email") String Email,
                                       @Field("Password") String Password);
//-------------forget password ----------------------------------------------------------------------------

        @FormUrlEncoded
        @POST("ForgotPassword")
        Call <ForgetPwdAPI> forgotpwdAPI(@Field("Email") String Email

        );

//facebooklogin-------------------------------------------------------------------------------------------

        @FormUrlEncoded
        @POST("FacebookLogin")
        Call <FacebookApi> facebookAPI(@Field("Email") String Email,
                                       @Field("id") String Password,
                                       @Field("firstname") String firstname,
                                       @Field("lastname") String lastname,
                                       @Field("link") String link

        );

//login api--------------------------------------------------------------------------------------------
        @FormUrlEncoded
        @POST("Login")
        Call <LoginAPI> loginAPI(@Field("Email") String Email,
                                       @Field("Password") String Password
        );

    }
}

