package influencer.com.influencer.activities.retrofit;

import android.content.Context;

import influencer.com.influencer.activities.activity.ActivityLogin;
import influencer.com.influencer.activities.activity.ActivityRegister;
import influencer.com.influencer.activities.apiResponses.registerAPI.ForgetPwdAPI;
import influencer.com.influencer.activities.apiResponses.registerAPI.LoginAPI;
import influencer.com.influencer.activities.apiResponses.registerAPI.ProfileHomeAPI;
import influencer.com.influencer.activities.apiResponses.registerAPI.RegisterAPI;
import influencer.com.influencer.activities.apiResponses.registerAPI.UserProfileAPI;
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
        if(ctx instanceof ActivityLogin)
            iRegisterCallback= (IRegisterCallback) ctx;
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


    public void LoginResponse(String email,String password)
    {

       restClient.loginAPI(email,password).enqueue(new Callback<LoginAPI>() {
           @Override
           public void onResponse(Call<LoginAPI> call, Response<LoginAPI> response) {

               iRegisterCallback.getRegisterResponse (response);

           }

           @Override
           public void onFailure(Call<LoginAPI> call, Throwable t) {

               iRegisterCallback.getRegisterResponse (t);

           }
       });
    }



    public void ForgetpwdResponse(String email)
    {
        restClient.forgotpwdAPI(email).enqueue(new Callback<ForgetPwdAPI>() {
            @Override
            public void onResponse(Call<ForgetPwdAPI> call, Response<ForgetPwdAPI> response) {

                iRegisterCallback.getRegisterResponse (response);

            }

            @Override
            public void onFailure(Call<ForgetPwdAPI> call, Throwable t) {

                iRegisterCallback.getRegisterResponse (t);

            }
        });
    }


    public void profileHomeResponse(String image)
    {
       restClient.profilehomeAPI(image).enqueue(new Callback<ProfileHomeAPI>() {
           @Override
           public void onResponse(Call<ProfileHomeAPI> call, Response<ProfileHomeAPI> response) {

           }

           @Override
           public void onFailure(Call<ProfileHomeAPI> call, Throwable t) {

           }
       });
    }


    public void userProfileResponse(String email)
    {

        restClient.userprofileAPI(email).enqueue(new Callback<UserProfileAPI>() {
            @Override
            public void onResponse(Call<UserProfileAPI> call, Response<UserProfileAPI> response) {

            }

            @Override
            public void onFailure(Call<UserProfileAPI> call, Throwable t) {

            }
        });

    }






}
