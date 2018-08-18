package influencer.com.influencer.activities.restclient;


import com.google.android.gms.common.api.Response;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import influencer.com.influencer.activities.apiResponses.registerAPI.RegisterAPI;
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


        // Shahzeb commented this code
        //The logging interceptor will be added to the http client
//        OkHttpClient.Builder httpClient = new OkHttpClient.Builder ( );
//        httpClient.addInterceptor ( logging );
//
//        Gson gson = new GsonBuilder ( )
//                .setLenient ( )
//                .create ( );
        //The Retrofit builder will have the client attached, in order to get connection logs

// Shahzeb commented this code

//        Retrofit retrofit = new Retrofit.Builder ( )
//                .client ( httpClient.build ( ) )
//                .addConverterFactory ( GsonConverterFactory.create ( gson ) )
//                .baseUrl ( baseUrl )
//                .build ( );


        GitApiInterface gitApiInterface = retrofit.create ( GitApiInterface.class );

        return gitApiInterface;

    }


    public interface GitApiInterface {


        // 1 Register API

        @FormUrlEncoded
        @POST("Register")
        Call <RegisterAPI> registerAPI(@Field("Email") String Email,
                                       @Field("Password") String Password, @Field("UserName") String UserName);


    }
}

