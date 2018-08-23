package influencer.com.influencer.activities.retrofit;

import android.content.Context;

import influencer.com.influencer.activities.activity.ActivityLogin;
import influencer.com.influencer.activities.activity.ActivityRegister;
import influencer.com.influencer.activities.apiResponses.registerAPI.ForgetPwdAPI;
import influencer.com.influencer.activities.apiResponses.registerAPI.LoginAPI;
import influencer.com.influencer.activities.apiResponses.registerAPI.RegisterAPI;
import influencer.com.influencer.activities.callback.IForgetPasswordCallback;
import influencer.com.influencer.activities.callback.ILoginCallback;
import influencer.com.influencer.activities.callback.IRegisterCallback;
import influencer.com.influencer.activities.restclient.RestClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RetrofitUtil {


    private RestClient.GitApiInterface restClient = RestClient.getClient ( );
    private IRegisterCallback iRegisterCallback;
    private IForgetPasswordCallback iforgetPasswordCallback;
    private ILoginCallback iLoginCallback;

    public RetrofitUtil(Context  ctx) {
        if(ctx instanceof ActivityRegister)
        {
            iRegisterCallback = (IRegisterCallback) ctx;
        }

        if(ctx instanceof ActivityLogin)
        {
            iLoginCallback= (ILoginCallback) ctx;
            iforgetPasswordCallback=(IForgetPasswordCallback) ctx;

        }



    }


    public void RegisterResponse(String email,String password)
    {

        restClient.registerAPI ( email, password).enqueue ( new Callback<RegisterAPI> ( ) {
            @Override
            public void onResponse(Call<RegisterAPI> call, Response<RegisterAPI> response) {
                iRegisterCallback.getRegisterResponseSuccess (response);
                }
                @Override
                public void onFailure(Call <RegisterAPI> call, Throwable t) {
                iRegisterCallback.getRegisterFailure (t);
                }


                }
        );
    }

    public void LoginResponse(String email,String password)
    {

        restClient.loginAPI(email,password).enqueue(new Callback<LoginAPI>() {
            @Override
            public void onResponse(Call<LoginAPI> call, Response<LoginAPI> response) {
                 iLoginCallback.getLoginResponseSuccess(response);
            }
            @Override
            public void onFailure(Call<LoginAPI> call, Throwable t) {
                iLoginCallback.getRegisterFailure (t);
            }
        });
    }

    public void ForgetpwdResponse(String email)
    {

        restClient.forgotpwdAPI(email).enqueue(new Callback<ForgetPwdAPI>() {
            @Override
            public void onResponse(Call<ForgetPwdAPI> call, Response<ForgetPwdAPI> response) {
                iforgetPasswordCallback.getRegisterResponseSuccess (response);
            }
            @Override
            public void onFailure(Call<ForgetPwdAPI> call, Throwable t) {
                iforgetPasswordCallback.getRegisterFailure (t);
            }
        });
    }


}
