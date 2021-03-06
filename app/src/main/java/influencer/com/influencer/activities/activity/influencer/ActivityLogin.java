package influencer.com.influencer.activities.activity.influencer;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.ProfileTracker;
import com.google.android.gms.maps.model.Dash;
import com.google.gson.Gson;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;
import com.orhanobut.hawk.Hawk;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import influencer.com.influencer.R;
import influencer.com.influencer.activities.activity.ActivityPurchase;
import influencer.com.influencer.activities.activity.FbLoginBaseActivity;
import influencer.com.influencer.activities.activity.MainActivity;
import influencer.com.influencer.activities.activity.WaitingPage;
import influencer.com.influencer.activities.activity.brand.ActivityBrandRegister;
import influencer.com.influencer.activities.apiResponses.registerAPI.influencerDashboardAPI.DashBoardApi;
import influencer.com.influencer.activities.callback.ICallback;
import influencer.com.influencer.activities.customtoast.CustomToast;
import influencer.com.influencer.activities.apiResponses.registerAPI.influencerAPI.FacebookApi;
import influencer.com.influencer.activities.apiResponses.registerAPI.influencerAPI.ForgetPwdAPI;
import influencer.com.influencer.activities.apiResponses.registerAPI.influencerAPI.LoginAPI;
import influencer.com.influencer.activities.application.MyApplication;
import influencer.com.influencer.activities.callback.IFacebookCallback;
import influencer.com.influencer.activities.callback.IForgetPasswordCallback;
import influencer.com.influencer.activities.callback.ILoginCallback;
import influencer.com.influencer.activities.constants.Contants;
import influencer.com.influencer.activities.restclient.RestClient;
import influencer.com.influencer.activities.retrofit.RetrofitUtil;

import retrofit2.Response;



public class ActivityLogin extends FbLoginBaseActivity implements Validator.ValidationListener, IForgetPasswordCallback, ILoginCallback, IFacebookCallback,ICallback {

    @BindView(R.id.register_move)
    TextView tvregisterNow;

    @BindView(R.id.loginbtn)
    Button btLogin;

    @BindView(R.id.regfbbtn)
    Button ffdfdfdfdf;


    Validator validator;

    @BindView(R.id.login_email)
    @NotEmpty
    @Email(message = "Please enter the valid email")
    EditText edLogin;

    @BindView(R.id.reg_insta_btn)
    Button loginwithinsta;

    @BindView(R.id.login_password)
    @Password(min = 6, scheme = Password.Scheme.ALPHA_NUMERIC_MIXED_CASE)
    EditText edPassword;
    AlertDialog alertDialog;



    private String email, gender, facebookName, phoneno;
    private int profilePictureView;
    private ProfileTracker profileTracker;
    private Uri imgUrlFb;

    private ProgressDialog progressDialog;

    private Dialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_infuencer);


//        hidingTheStatusBar();
        ButterKnife.bind(this);


        initializefb();


        validator = new Validator(ActivityLogin.this);
        validator.setValidationListener(this);





    }

// =========================================================================================================================================
//                                                   ENTER VALIDATION SUCCESS
// =========================================================================================================================================

    @Override
    public void onValidationSucceeded() {
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custom_dialog_progress);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCancelable(false);
        dialog.show();

        RetrofitUtil retrofitUtil = new RetrofitUtil(ActivityLogin.this);
        retrofitUtil.LoginResponse(edLogin.getText().toString(), edPassword.getText().toString());

    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {


        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);

            // Display error messages ;)
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();

            }
        }

    }


    @OnClick(R.id.loginbtn)
    public void openMainAcivity() {



        validator.validate();


    }

    @OnClick(R.id.register_move)
    public void openRegisterActivity() {

        Intent intent = new Intent(getApplicationContext(), ActivityRegister.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
    }


//=========================================================================================================================================
    //                                             FORGET PASSWORD BUTTON CLICK
//=========================================================================================================================================


    @OnClick(R.id.textView3)
    public void openforgetpassword() {
        forgetpassword();
    }

    @Override
    protected void sendforgetpassworddata() {

        RetrofitUtil retrofitUtil = new RetrofitUtil(ActivityLogin.this);
        retrofitUtil.ForgetpwdResponse(Hawk.get("femail").toString());

    }


    //========================================================================================================================================
    //                                                  FACEBOOK BUTTON CLICK
    //=========================================================================================================================================
    @OnClick(R.id.regfbbtn)
    public void facebooklogin() {

        facebookloginClick();

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);



    }
    //========================================================================================================================================
    //                                                     SEND FACEBOOK DATA TO API
    //========================================================================================================================================


    @Override
    protected void senduserdetails() {

        dialog = new Dialog(ActivityLogin.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custom_dialog_progress);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCancelable(false);
        dialog.show();

        RetrofitUtil retrofitUtil = new RetrofitUtil(ActivityLogin.this);
        retrofitUtil.FacebookapiResponse(Hawk.get(Contants.USER_EMAIL, ""), Hawk.get(Contants.USER_ID, ""), Hawk.get(Contants.USER_FIRSTNAME, ""), Hawk.get(Contants.USER_LASTNAME, ""), Hawk.get(Contants.USER_PROFILELINK, ""));

    }


//==========================================================================================================================================
    //                                                     API RESPONSES
//==========================================================================================================================================



    //                                               FORGET PASSWORD
    @Override
    public void getRegisterResponseSuccess(Response<ForgetPwdAPI> forgetPwdAPIResponse) {

        if (forgetPwdAPIResponse.body() != null) {
            if (forgetPwdAPIResponse.body().getSuccess()) {
                String message = forgetPwdAPIResponse.body().getMessage();
                new CustomToast(getApplicationContext(), message);


                progressDialog.hide();
                progressDialog.cancel();
                alertDialog.dismiss();


            } else {
                String error = forgetPwdAPIResponse.body().getMessage();
                new CustomToast(getApplicationContext(), error);

                progressDialog.hide();
                progressDialog.cancel();
            }

        }

    }

    //                                          LOGIN

    @Override
    public void getLoginResponseSuccess(Response<LoginAPI> loginAPIResponse) {
        Hawk.put(Contants.INFLUENCERID,loginAPIResponse.body().getInfluencerID());
        Hawk.put(Contants.PROFILENAME,loginAPIResponse.body().getName());
        Hawk.put(Contants.PROGILEIMG,loginAPIResponse.body().getPicture());

        if (loginAPIResponse.body() != null) {
            if (loginAPIResponse.body().getStatus().equals("1")) {
                dialog.dismiss();
                startActivity(new Intent(getApplicationContext(),ActivitySelectIntrest.class));
                overridePendingTransition(R.anim.fadein, R.anim.slide_to_right);
                finish();
            }
            else if (loginAPIResponse.body().getStatus().equals("2"))
            {

                RetrofitUtil retrofitUtil = new RetrofitUtil(ActivityLogin.this);
                retrofitUtil.dashboardresponse(String.valueOf(Hawk.get(Contants.INFLUENCERID)));

            }
            else if (loginAPIResponse.body().getStatus().equals("3"))
            {
                dialog.dismiss();
                startActivity(new Intent(getApplicationContext(),WaitingPage.class));
                overridePendingTransition(R.anim.fadein, R.anim.slide_to_right);
                finish();
            }

            else if (loginAPIResponse.body().getStatus().equals("4"))
            {
                dialog.dismiss();
                startActivity(new Intent(getApplicationContext(),InfluencerPlans.class));
                overridePendingTransition(R.anim.fadein, R.anim.slide_to_right);
                finish();
            }
            else
            {
                dialog.dismiss();
                Toast.makeText(getApplicationContext(),loginAPIResponse.body().getMessage(),Toast.LENGTH_SHORT).show();
            }
        }
}


    @Override
    public void getRegisterFailure(Throwable registerAPIResponse) {


    }


    @Override
    public void getFacebookResponseSuccess(Response<FacebookApi> facebookApiResponse) {

        if (facebookApiResponse.body() != null) {
            if (facebookApiResponse.body().getStatus().equals("1")) {
                dialog.dismiss();
                startActivity(new Intent(getApplicationContext(),ActivitySelectIntrest.class));
                overridePendingTransition(R.anim.fadein, R.anim.slide_to_right);
                finish();
            }
            else if (facebookApiResponse.body().getStatus().equals("2"))
            {
                Hawk.put(Contants.INFLUENCERID,facebookApiResponse.body().getInfluencerID());

                RetrofitUtil retrofitUtil = new RetrofitUtil(ActivityLogin.this);
                retrofitUtil.dashboardresponse(String.valueOf(Hawk.get(Contants.INFLUENCERID)));

            }
            else if (facebookApiResponse.body().getStatus().equals("3"))
            {
                dialog.dismiss();
                startActivity(new Intent(getApplicationContext(),WaitingPage.class));
                overridePendingTransition(R.anim.fadein, R.anim.slide_to_right);
                finish();
            }

            else if (facebookApiResponse.body().getStatus().equals("4"))
            {
                dialog.dismiss();
                startActivity(new Intent(getApplicationContext(),InfluencerPlans.class));
                overridePendingTransition(R.anim.fadein, R.anim.slide_to_right);
                finish();
            }
            else
            {
                dialog.dismiss();
                Toast.makeText(getApplicationContext(),facebookApiResponse.body().getMessage(),Toast.LENGTH_SHORT).show();
            }
        }

    }

    @Override
    public void getFacebookFailure(Throwable facebookAPIResponse) {

    }


    //=========================================================================================================================================
    //                                                    INSTAGRAM BUTTON CLICK
    //=========================================================================================================================================
    @OnClick(R.id.reg_insta_btn)
    public void instaloginbtnclick() {




    }



    //====================================================== DASHBOARD API RESPONSE =========================================================
    @Override
    public void getresponse(Object response) {
        if (response instanceof DashBoardApi) {

            if(!((DashBoardApi) response).getSuccess())
            {
                if (((DashBoardApi) response).getStatus().equals(3)) {
                    dialog.dismiss();
                    Intent intent = new Intent(getApplicationContext(), WaitingPage.class);
                    startActivity(intent);
                    finish();

                } else if (((DashBoardApi) response).getStatus().equals(2)) {

                    dialog.dismiss();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();

                } else if (((DashBoardApi) response).getStatus().equals(4)) {

                    dialog.dismiss();
                    Intent intent = new Intent(getApplicationContext(), InfluencerPlans.class);
                    startActivity(intent);
                    finish();

                }else if (((DashBoardApi) response).getStatus().equals(1)) {

                    dialog.dismiss();
                    Intent intent = new Intent(getApplicationContext(), ActivitySelectIntrest.class);
                    startActivity(intent);
                    finish();
                }
            }
            if(((DashBoardApi) response).getSuccess())
            {
                dialog.dismiss();
                Hawk.put("tname",((DashBoardApi) response).getUserDetail().getName());
                Hawk.put("tdob",((DashBoardApi) response).getUserDetail().getDob());
                Hawk.put("tlanguage",((DashBoardApi) response).getUserDetail().getLanguage());
                Hawk.put("tratio",((DashBoardApi) response).getUserDetail().getGenderratio());
                Hawk.put("tagefrom",((DashBoardApi) response).getUserDetail().getAgeFrom());
                Hawk.put("tageto",((DashBoardApi) response).getUserDetail().getAgeTo());
                Hawk.put("tinterest",((DashBoardApi) response).getUserDetail().getInterest());
                Hawk.put("aboutme",((DashBoardApi) response).getUserDetail().getAboutme());
                Hawk.put("gndr",((DashBoardApi) response).getUserDetail().getGender());
                startActivity(new Intent(getApplicationContext(),Dashboard.class));
            }
        }
        else
        {
            Toast.makeText(getApplicationContext(),((DashBoardApi) response).getMessage(),Toast.LENGTH_SHORT).show();
        }




    }

    @Override
    public void getfailerresponse(Throwable throwable) {

    }
}
