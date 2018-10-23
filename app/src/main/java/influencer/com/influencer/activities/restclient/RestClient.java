package influencer.com.influencer.activities.restclient;


import com.google.gson.Gson;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import influencer.com.influencer.activities.apiResponses.registerAPI.influencerAPI.ChangePwdApi;
import influencer.com.influencer.activities.apiResponses.registerAPI.influencerAPI.UpdateProfileApi;
import influencer.com.influencer.activities.apiResponses.registerAPI.influencerAPI.campaign.invitedCampaign.InvitedCampaignAPI;
import influencer.com.influencer.activities.apiResponses.registerAPI.influencerAPI.campaign.previousCampaign.PreviousCampaignAPI;
import influencer.com.influencer.activities.apiResponses.registerAPI.influencerAPI.campaign.requestedcampaign.RequestedAPI;
import influencer.com.influencer.activities.apiResponses.registerAPI.brand.BrandFacebookApi;
import influencer.com.influencer.activities.apiResponses.registerAPI.brand.BrandForgetPwd;
import influencer.com.influencer.activities.apiResponses.registerAPI.brand.BrandSetUp;
import influencer.com.influencer.activities.apiResponses.registerAPI.influencerAPI.FacebookApi;
import influencer.com.influencer.activities.apiResponses.registerAPI.influencerAPI.ForgetPwdAPI;
import influencer.com.influencer.activities.apiResponses.registerAPI.influencerAPI.LoginAPI;
import influencer.com.influencer.activities.apiResponses.registerAPI.influencerAPI.PlanApi;
import influencer.com.influencer.activities.apiResponses.registerAPI.influencerAPI.RegisterAPI;
import influencer.com.influencer.activities.apiResponses.registerAPI.influencerAPI.UserProfileAPI;
import influencer.com.influencer.activities.apiResponses.registerAPI.brand.BrandLoginApi;
import influencer.com.influencer.activities.apiResponses.registerAPI.brand.BrandRegisterApi;
import influencer.com.influencer.activities.apiResponses.registerAPI.influencerAPI.campaign.activeCampaign.ActiveCampaign;
import influencer.com.influencer.activities.apiResponses.registerAPI.influencerAPI.campaign.searchCampaign.ApplyCampaignApi;
import influencer.com.influencer.activities.apiResponses.registerAPI.influencerAPI.campaign.searchCampaign.CampaignAPI;
import influencer.com.influencer.activities.apiResponses.registerAPI.influencerDashboardAPI.DashBoardApi;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Url;

public class RestClient {


    public static GitApiInterface getClient() {

        String baseUrl = "http://192.168.0.61/";
//        String baseUrl = "https://app.influencernetwork.be/InfluencerAPI/";

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor ( );
        logging.setLevel ( HttpLoggingInterceptor.Level.BODY );

        OkHttpClient client = new OkHttpClient.Builder ( )
                .connectTimeout ( 100, TimeUnit.SECONDS ).addInterceptor(logging)
                .readTimeout ( 100, TimeUnit.SECONDS ).build ( );
        Retrofit retrofit = new Retrofit.Builder ( )
                .baseUrl ( baseUrl ).client ( client )
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory ( GsonConverterFactory.create ( new Gson ( ) ) ).build ( );





        GitApiInterface gitApiInterface = retrofit.create ( GitApiInterface.class );

        return gitApiInterface;

    }


    public interface GitApiInterface {


//=========================================================================================================================================
//                                              INFLUENCER
//==========================================================================================================================================


//=========================================================== REGISTER API ================================================================

        @FormUrlEncoded
        @POST("InfluencerAPI/Register")
        Call <RegisterAPI> registerAPI(@Field("Email") String Email,
                                       @Field("Password") String Password,
                                       @Field("ConfirmPassword") String Confirmpassword
        );
//========================================================= FORGET PASSWORD API ===========================================================

        @FormUrlEncoded
        @POST("InfluencerAPI/ForgotPassword")
        Call <ForgetPwdAPI> forgotpwdAPI(@Field("Email") String Email

        );

//============================================================ FACEBOOK LOGIN =============================================================

        @FormUrlEncoded
        @POST("InfluencerAPI/FacebookLogin")
        Call <FacebookApi> facebookAPI(@Field("email") String Email,
                                       @Field("id") String id,
                                       @Field("firstname") String firstname,
                                       @Field("lastname") String lastname,
                                       @Field("link") String link

        );

//================================================================LOGIN API================================================================

        @FormUrlEncoded
        @POST("InfluencerAPI/Login")
        Call <LoginAPI> loginAPI(@Field("Email") String Email,
                                 @Field("Password") String Password
        );



        @GET
        Call <String> getInstaData(@Url String url);


//============================================================ SETUP API ==================================================================

        @FormUrlEncoded
        @POST("InfluencerAPI/register.php")
        Call<ResponseBody> RegisterUser(@FieldMap Map<String, String> params);

        @Multipart
        @POST("InfluencerAPI/SetUp")
        Call <UserProfileAPI> userdetailapi(@Part("userid") RequestBody Email,
                                            @Part("name") RequestBody name,
                                            @Part MultipartBody.Part image,  // image file
                                            @Part("gender") RequestBody gender,
                                            @Part("language") RequestBody language,
                                            @Part("dob") RequestBody dob,
                                            @Part("description") RequestBody description,
                                            @Part("interest") RequestBody interest,
                                            @Part("genderratio") RequestBody genderratio,
                                            @Part("ageratio") RequestBody ageratio,
                                            @Part("firstname") RequestBody firstname,
                                            @Part("lastname") RequestBody lastname,
                                            @Part("country") RequestBody country,
                                            @Part("address") RequestBody address,
                                            @Part("city") RequestBody city,
                                            @Part("postalcode") RequestBody postalcode,
                                            @Part("height") RequestBody height,
                                            @Part("jeanlength") RequestBody jeanlength,
                                            @Part("jeanwidth") RequestBody jeanwidth,
                                            @Part("pants") RequestBody pants,
                                            @Part("shirt") RequestBody shirt,
                                            @Part("shoesize") RequestBody shoesize,
                                            @Part("underwear") RequestBody underwear,
                                            @Part("extrainfo") RequestBody extrainfo,
                                            @Part("instaLink") RequestBody instaLink,
                                            @Part("instaprofilepic") RequestBody instaprofilepic,
                                            @Part("fbprofilepic") RequestBody fbprofilepic,
                                            @Part("fbname") RequestBody fbname,
                                            @Part("instaname") RequestBody instaname,
                                            @Part("fblink") RequestBody fblink
        );


        //===================================================== DASHBOARD API ============================================================

        @FormUrlEncoded
        @POST("InfluencerAPI/Dashboard")
        Call <DashBoardApi> dashboardapi(@Field("userid") String id);


        @FormUrlEncoded
        @POST("InfluencerAPI/PlanPay")
        Call <PlanApi> planpay(@Field("plan") String plan,
                               @Field("userid") String userid

        );


//=========================================== CAMPAIGN API ================================================
        @FormUrlEncoded
        @POST("InfluencerAPI/CampaignList")
        Call <CampaignAPI> campaignlist(@Field("userid") String userid
        );

        @FormUrlEncoded
        @POST("InfluencerAPI/MyActiveCampaign")
        Call <ActiveCampaign> activecampaign(@Field("userid") String userid
        );

        @FormUrlEncoded
        @POST("InfluencerAPI/RequestedCampaign")
        Call <RequestedAPI> requestedcampain(@Field("userid") String userid
        );

        @FormUrlEncoded
        @POST("InfluencerAPI/InvitedCampaign")
        Call <InvitedCampaignAPI> invitedcampaign(@Field("userid") String userid
        );


        @FormUrlEncoded
        @POST("InfluencerAPI/MyPreviousCampaign")
        Call <PreviousCampaignAPI>previousCampaign(@Field("userid") String userid
        );



        @FormUrlEncoded
        @POST("InfluencerAPI/ChangePassword")
        Call <ChangePwdApi> changepwd(@Field("userid") String id,
                                      @Field("OldPassword") String oldpwd,
                                      @Field("NewPassword") String newpwd
        );

        @FormUrlEncoded
        @POST("InfluencerAPI/InfluencerCampaignReq")
        Call <ApplyCampaignApi> applycampaign(@Field("userid") String id,
                                          @Field("campid") String imgid,
                                          @Field("aboutyou") String about
        );

        @Multipart
        @POST("InfluencerAPI/DetailSave1")
        Call <UpdateProfileApi> updateprofile(@Part("userid") String  id,
                                              @Part("name") String  name,
                                              @Part MultipartBody.Part image,  // image file
                                              @Part("gender") String  gender,
                                              @Part("language") String language,
                                              @Part("dob") String  dob,
                                              @Part("interest") String interest,
                                              @Part("genderratio") String genderratio,
                                              @Part("ageratio") String ageratio,
                                              @Part("about") String description);

        //================================================================================================================================
        //                                                            BRAND
        //================================================================================================================================


        @FormUrlEncoded
        @POST("BrandApi/Register")
        Call <BrandRegisterApi> brandRegisterApiCall(@Field("Email") String Email,
                                                     @Field("Password") String Password,
                                                     @Field("ConfirmPassword") String Confirmpassword
        );


        //==================================================== BRAND LOGIN ================================================================


        @FormUrlEncoded
        @POST("BrandApi/Login")
        Call <BrandLoginApi> brandloginapi(@Field("Email") String Email,
                                           @Field("Password") String Password
        );

        //================================================ BRAND FORGET PASSWORD ==========================================================

        @FormUrlEncoded
        @POST("BrandApi/ForgotPassword")
        Call <BrandForgetPwd> brandforgetpwd(@Field("Email") String Email

        );


        //================================================ BRAND FACEBOOK LOGIN ===========================================================
        @FormUrlEncoded
        @POST("BrandApi/FacebookLogin")
        Call <BrandFacebookApi> brandfbapi(@Field("email") String Email,
                                           @Field("id") String id,
                                           @Field("firstname") String firstname,
                                           @Field("lastname") String lastname,
                                           @Field("link") String link

        );


        //================================================ BRAND SET UP ===================================================================


        @Multipart
        @POST("BrandApi/SetUp")
        Call <BrandSetUp> brandsetupapi(@Part("userid") RequestBody Email,
                                        @Part MultipartBody.Part image,  // image file
                                        @Part("firstname") RequestBody fname,
                                        @Part("lastname") RequestBody lname,
                                        @Part("country") RequestBody country,
                                        @Part("address") RequestBody address,
                                        @Part("postalcode") RequestBody postalcode,
                                        @Part("organization") RequestBody organization,
                                        @Part("btw") RequestBody btw,
                                        @Part("companyNo") RequestBody companyno,
                                        @Part("phoneno") RequestBody phoneno,
                                        @Part("email") RequestBody email,
                                        @Part("detail") RequestBody detail

        );


    }









}

