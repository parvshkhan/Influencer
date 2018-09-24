package influencer.com.influencer.activities.retrofit;

import android.content.Context;
import android.media.FaceDetector;

import influencer.com.influencer.activities.activity.ActivityAbout;
import influencer.com.influencer.activities.activity.ActivityLogin;
import influencer.com.influencer.activities.activity.ActivityRegister;
import influencer.com.influencer.activities.apiResponses.registerAPI.FacebookApi;
import influencer.com.influencer.activities.apiResponses.registerAPI.ForgetPwdAPI;
import influencer.com.influencer.activities.apiResponses.registerAPI.LoginAPI;
import influencer.com.influencer.activities.apiResponses.registerAPI.RegisterAPI;
import influencer.com.influencer.activities.apiResponses.registerAPI.UserProfileAPI;
import influencer.com.influencer.activities.callback.ICallback;
import influencer.com.influencer.activities.callback.IFacebookCallback;
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
    private IFacebookCallback iFacebookCallback;
    private ICallback iCallback;

    public RetrofitUtil(Context  ctx) {
        if(ctx instanceof ActivityRegister)
        {
            iRegisterCallback = (IRegisterCallback) ctx;
        }

        if(ctx instanceof ActivityLogin)
        {
            iLoginCallback= (ILoginCallback) ctx;
            iforgetPasswordCallback=(IForgetPasswordCallback) ctx;
            iFacebookCallback=(IFacebookCallback)ctx;

        }
        if (ctx instanceof ActivityAbout)
        {
            iCallback=(ICallback) ctx;
        }



    }


    public void RegisterResponse(String email,String password,String confirmpassword)
    {

        restClient.registerAPI ( email, password,confirmpassword).enqueue ( new Callback<RegisterAPI> ( ) {
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

    public void FacebookapiResponse(String email,String id,String firstname,String lastname,String link)
    {

        restClient.facebookAPI(email,id,firstname,lastname,link).enqueue(new Callback<FacebookApi>() {
            @Override
            public void onResponse(Call<FacebookApi> call, Response<FacebookApi> response) {
                iFacebookCallback.getFacebookResponseSuccess(response);
            }
            @Override
            public void onFailure(Call<FacebookApi> call, Throwable t) {

                iFacebookCallback.getFacebookFailure(t);
            }
        });
    }

    public void userdetails(String userid,String name,String file,String gender,String
            language,String dob,String description,String interest,String genderratio,
                            String ageratio,String firstname, String lastname,String country,
                            String address,String city,String postalcode,String height,String
                                    jeanlength,String jeanwidth,String pants,String shirt,
                            String shoesize,String underwear,String extrainfo,String instaLink,
                            String instaprofilepic,String fbprofilepic,String fbname,String instaname,String fblink)

    {
        restClient.userdetailapi(userid,name,file,gender,language,dob,description,interest,genderratio,ageratio,firstname,lastname,country,address,city,postalcode,height,jeanlength,jeanwidth,pants,shirt,shoesize
        ,underwear,extrainfo,instaLink,instaprofilepic,fbprofilepic,fbname,instaname,fblink).enqueue(new Callback<UserProfileAPI>() {
            @Override
            public void onResponse(Call<UserProfileAPI> call, Response<UserProfileAPI> response) {
                iCallback.getresponse(response);
            }

            @Override
            public void onFailure(Call<UserProfileAPI> call, Throwable t) {
                iCallback.getfailerresponse(t);

            }
        });



    }



}
