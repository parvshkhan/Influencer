package influencer.com.influencer.activities.restclient;


import com.google.android.gms.common.api.Response;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;

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

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        //The logging interceptor will be added to the http client
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        Gson gson = new GsonBuilder ()
                .setLenient()
                .create();
        //The Retrofit builder will have the client attached, in order to get connection logs


        String baseUrl = "http://18.217.234.39/NXSPOT/";
        Retrofit retrofit = new Retrofit.Builder()
                .client(httpClient.build())
                .addConverterFactory( GsonConverterFactory.create(gson))
                .baseUrl(baseUrl)
                .build();


        GitApiInterface gitApiInterface = retrofit.create(GitApiInterface.class);

        return gitApiInterface;

    }


    public interface GitApiInterface {
        @Multipart
        @POST("v1.1/UserRegister")
        Call<ResponseBody> registerResponse(@Body HashMap<String, String> hashMap);
 }
}

