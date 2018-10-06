package influencer.com.influencer.activities.retrofit;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import influencer.com.influencer.activities.activity.WaitingPage;
import influencer.com.influencer.activities.activity.brand.ActivityBrandUserInfo;
import influencer.com.influencer.activities.activity.influencer.ActivityAbout;
import influencer.com.influencer.activities.activity.brand.ActivityBrandRegister;
import influencer.com.influencer.activities.activity.influencer.ActivityLogin;
import influencer.com.influencer.activities.activity.influencer.ActivityRegister;
import influencer.com.influencer.activities.activity.brand.BrandLogin;
import influencer.com.influencer.activities.apiResponses.registerAPI.brand.BrandFacebookApi;
import influencer.com.influencer.activities.apiResponses.registerAPI.brand.BrandForgetPwd;
import influencer.com.influencer.activities.apiResponses.registerAPI.brand.BrandSetUp;
import influencer.com.influencer.activities.apiResponses.registerAPI.influencerAPI.FacebookApi;
import influencer.com.influencer.activities.apiResponses.registerAPI.influencerAPI.ForgetPwdAPI;
import influencer.com.influencer.activities.apiResponses.registerAPI.influencerAPI.LoginAPI;
import influencer.com.influencer.activities.apiResponses.registerAPI.influencerAPI.RegisterAPI;
import influencer.com.influencer.activities.apiResponses.registerAPI.influencerAPI.UserProfileAPI;
import influencer.com.influencer.activities.apiResponses.registerAPI.brand.BrandLoginApi;
import influencer.com.influencer.activities.apiResponses.registerAPI.brand.BrandRegisterApi;
import influencer.com.influencer.activities.apiResponses.registerAPI.influencerDashboardAPI.DashBoardApi;
import influencer.com.influencer.activities.callback.ICallback;
import influencer.com.influencer.activities.callback.IFacebookCallback;
import influencer.com.influencer.activities.callback.IForgetPasswordCallback;
import influencer.com.influencer.activities.callback.ILoginCallback;
import influencer.com.influencer.activities.callback.IRegisterCallback;
import influencer.com.influencer.activities.restclient.RestClient;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RetrofitUtil {


    private RestClient.GitApiInterface restClient = RestClient.getClient();
    private IRegisterCallback iRegisterCallback;
    private IForgetPasswordCallback iforgetPasswordCallback;
    private ILoginCallback iLoginCallback;
    private IFacebookCallback iFacebookCallback;
    private ICallback iCallback;

    public RetrofitUtil(Context ctx) {
        if (ctx instanceof ActivityRegister) {
            iRegisterCallback = (IRegisterCallback) ctx;
        } else if (ctx instanceof ActivityLogin) {
            iLoginCallback = (ILoginCallback) ctx;
            iforgetPasswordCallback = (IForgetPasswordCallback) ctx;
            iFacebookCallback = (IFacebookCallback) ctx;
            iCallback=(ICallback) ctx;
        } else if (ctx instanceof ActivityAbout) {
            iCallback = (ICallback) ctx;
        } else if (ctx instanceof ActivityBrandRegister) {
            iCallback = (ICallback) ctx;
        } else if (ctx instanceof BrandLogin) {
            iCallback = (ICallback) ctx;
        } else if (ctx instanceof ActivityBrandUserInfo) {
            iCallback = (ICallback) ctx;
        }
        else if (ctx instanceof WaitingPage) {
            iCallback = (ICallback) ctx;
        }
        else {
            Toast.makeText(ctx.getApplicationContext(), "context missing", Toast.LENGTH_LONG).show();
        }
    }

    //-----------------------------------------------------------------------------------------------------------------------------------
    //                                          INFLUENCER API RESPONSE
    //-----------------------------------------------------------------------------------------------------------------------------------


//===================================================== REGISTER ========================================================================

    public void RegisterResponse(String email, String password, String confirmpassword) {

        restClient.registerAPI(email, password, confirmpassword).enqueue(new Callback<RegisterAPI>() {
            @Override
            public void onResponse(Call<RegisterAPI> call, Response<RegisterAPI> response) {

                iRegisterCallback.getRegisterResponseSuccess(response);
            }

            @Override
            public void onFailure(Call<RegisterAPI> call, Throwable t) {

                iRegisterCallback.getRegisterFailure(t);
            }
        });
    }

//======================================================= LOGIN =========================================================================

    public void LoginResponse(String email, String password) {

        restClient.loginAPI(email, password).enqueue(new Callback<LoginAPI>() {
            @Override
            public void onResponse(Call<LoginAPI> call, Response<LoginAPI> response) {
                iLoginCallback.getLoginResponseSuccess(response);
            }

            @Override
            public void onFailure(Call<LoginAPI> call, Throwable t) {
                iLoginCallback.getRegisterFailure(t);
            }
        });
    }

//=================================================== FORGET PASSWORD ===================================================================

    public void ForgetpwdResponse(String email) {

        restClient.forgotpwdAPI(email).enqueue(new Callback<ForgetPwdAPI>() {
            @Override
            public void onResponse(Call<ForgetPwdAPI> call, Response<ForgetPwdAPI> response) {
                iforgetPasswordCallback.getRegisterResponseSuccess(response);
            }

            @Override
            public void onFailure(Call<ForgetPwdAPI> call, Throwable t) {
                iforgetPasswordCallback.getRegisterFailure(t);
            }
        });
    }

    //===================================================== INSTAGRAM ===================================================================

    public void instagramintegration(String url)
    {

        restClient.getInstaData(url).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                iCallback.getresponse(response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });

    }

//==================================================== FACEBOOK DATA ====================================================================

    public void FacebookapiResponse(String email, String id, String firstname, String lastname, String link) {

        restClient.facebookAPI(email, id, firstname, lastname, link).enqueue(new Callback<FacebookApi>() {
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

//============================================================ SETUP PAGE ===============================================================

    public void userdetails(RequestBody userid, RequestBody name, MultipartBody.Part file, RequestBody gender, RequestBody
            language, RequestBody dob, RequestBody description, RequestBody interest, RequestBody genderratio,
                            RequestBody ageratio, RequestBody firstname, RequestBody lastname, RequestBody country,
                            RequestBody address, RequestBody city, RequestBody postalcode, RequestBody height, RequestBody
                                    jeanlength, RequestBody jeanwidth, RequestBody pants, RequestBody shirt,
                            RequestBody shoesize, RequestBody underwear, RequestBody extrainfo, RequestBody instaLink,
                            RequestBody instaprofilepic, RequestBody fbprofilepic, RequestBody fbname, RequestBody instaname, RequestBody fblink) {

        restClient.userdetailapi(userid, name, file, gender, language, dob, description, interest, genderratio, ageratio, firstname, lastname, country, address, city, postalcode, height, jeanlength, jeanwidth, pants, shirt, shoesize
                , underwear, extrainfo, instaLink, instaprofilepic, fbprofilepic, fbname, instaname, fblink).enqueue(new Callback<UserProfileAPI>() {
            @Override
            public void onResponse(Call<UserProfileAPI> call, Response<UserProfileAPI> response) {
                iCallback.getresponse(response.body());
            }

            @Override
            public void onFailure(Call<UserProfileAPI> call, Throwable t) {
                iCallback.getfailerresponse(t);

            }
        });

    }

//========================================================= DASHBOARD ===================================================================


    public void dashboardresponse(String id) {

        restClient.dashboardapi(id).enqueue(new Callback<DashBoardApi>() {
            @Override
            public void onResponse(Call<DashBoardApi> call, Response<DashBoardApi> response) {

                iCallback.getresponse(response.body());

            }

            @Override
            public void onFailure(Call<DashBoardApi> call, Throwable t) {

                iCallback.getfailerresponse(t);

            }
        });
    }


    //------------------------------------------------------------------------------------------------------------------------------------
    //                                                     SEND BRAND DETAIL TO API
    //------------------------------------------------------------------------------------------------------------------------------------


    //================================================ BRAND REGISTER ====================================================================

    public void BrandRegister(String email, String password, String confirmpassword) {

        restClient.brandRegisterApiCall(email, password, confirmpassword).enqueue(new Callback<BrandRegisterApi>() {
            @Override
            public void onResponse(Call<BrandRegisterApi> call, Response<BrandRegisterApi> response) {

                iCallback.getresponse(response.body());

            }

            @Override
            public void onFailure(Call<BrandRegisterApi> call, Throwable t) {
                iCallback.getfailerresponse(t);

            }
        });
    }


    //===================================================== BRAND LOGIN ==================================================================

    public void BrandLoginResponse(String email, String password) {

        restClient.brandloginapi(email, password).enqueue(new Callback<BrandLoginApi>() {
            @Override
            public void onResponse(Call<BrandLoginApi> call, Response<BrandLoginApi> response) {

                iCallback.getresponse(response.body());

            }

            @Override
            public void onFailure(Call<BrandLoginApi> call, Throwable t) {

                iCallback.getfailerresponse(t);

            }
        });
    }


    //===================================================== FORGET PASSWORD ==============================================================


    public void BrandForgetpwdResponse(String email) {

        restClient.brandforgetpwd(email).enqueue(new Callback<BrandForgetPwd>() {
            @Override
            public void onResponse(Call<BrandForgetPwd> call, Response<BrandForgetPwd> response) {

                iCallback.getresponse(response.body());

            }

            @Override
            public void onFailure(Call<BrandForgetPwd> call, Throwable t) {

                iCallback.getresponse(t);

            }
        });
    }


//============================================================ FACEBOOK ==================================================================


    public void BrandFacebookapiResponse(String email, String id, String firstname, String lastname, String link) {

        restClient.brandfbapi(email, id, firstname, lastname, link).enqueue(new Callback<BrandFacebookApi>() {
            @Override
            public void onResponse(Call<BrandFacebookApi> call, Response<BrandFacebookApi> response) {
                iCallback.getresponse(response.body());
            }

            @Override
            public void onFailure(Call<BrandFacebookApi> call, Throwable t) {

                iCallback.getfailerresponse(t);

            }
        });


    }

//============================================================ SET UP ==================================================================

    public void BrandsetuprResponse(RequestBody userid, MultipartBody.Part logo, RequestBody firstname, RequestBody lastname,
                                    RequestBody country, RequestBody address, RequestBody postalcode, RequestBody organization,
                                    RequestBody btw, RequestBody companyNo, RequestBody phoneno, RequestBody email, RequestBody detail) {

        restClient.brandsetupapi(userid, logo, firstname, lastname, country, address, postalcode, organization, btw, companyNo, phoneno, email, detail).enqueue(new Callback<BrandSetUp>() {
            @Override
            public void onResponse(Call<BrandSetUp> call, Response<BrandSetUp> response) {

                iCallback.getresponse(response.body());

            }

            @Override
            public void onFailure(Call<BrandSetUp> call, Throwable t) {

                iCallback.getfailerresponse(t);

            }
        });

    }


}