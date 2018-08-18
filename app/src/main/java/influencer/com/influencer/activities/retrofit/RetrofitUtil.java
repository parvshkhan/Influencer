package influencer.com.influencer.activities.retrofit;

import android.content.Context;

import influencer.com.influencer.activities.activity.ActivityLogin;
import influencer.com.influencer.activities.activity.ActivityRegister;
import influencer.com.influencer.activities.apiResponses.registerAPI.RegisterAPI;
import influencer.com.influencer.activities.callback.IRegisterCallback;
import influencer.com.influencer.activities.restclient.RestClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RetrofitUtil {


    private RestClient.GitApiInterface restClient = RestClient.getClient ( );
    private IRegisterCallback iRegisterCallback;




    public RetrofitUtil(Context  ctx) {
        if(ctx instanceof ActivityRegister)
            iRegisterCallback = (IRegisterCallback) ctx;

    }


    public void RegisterResponse(String username,String email,String password)
    {

        restClient.registerAPI ( email, password, username ).enqueue ( new Callback<RegisterAPI> ( ) {
            @Override
            public void onResponse(Call<RegisterAPI> call, Response<RegisterAPI> response) {
              iRegisterCallback.getRegisterResponse (response);
            }

            @Override
            public void onFailure(Call <RegisterAPI> call, Throwable t) {

                iRegisterCallback.getRegisterResponse (t);

            }
        } );


    }







}
