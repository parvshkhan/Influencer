package influencer.com.influencer.activities.activity.brand;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.ProfileTracker;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;
import com.orhanobut.hawk.Hawk;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import influencer.com.influencer.R;
import influencer.com.influencer.activities.activity.FbLoginBaseActivity;
import influencer.com.influencer.activities.activity.MainActivity;
import influencer.com.influencer.activities.apiResponses.registerAPI.brand.BrandFacebookApi;
import influencer.com.influencer.activities.apiResponses.registerAPI.brand.BrandForgetPwd;
import influencer.com.influencer.activities.apiResponses.registerAPI.brand.BrandLoginApi;
import influencer.com.influencer.activities.application.MyApplication;
import influencer.com.influencer.activities.callback.ICallback;
import influencer.com.influencer.activities.constants.Contants;
import influencer.com.influencer.activities.retrofit.RetrofitUtil;

public class BrandLogin extends FbLoginBaseActivity implements Validator.ValidationListener,ICallback {


    @BindView(R.id.register_move)
    TextView tvregisterNow;

    @BindView(R.id.loginbtn)
    Button btLogin;

    View view;

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

    CallbackManager callbackManager;
    LoginManager loginManager;





    private ProfileTracker profileTracker;
    private Uri imgUrlFb;

    ProgressDialog progressDialog;
    AlertDialog.Builder dialogBuilder;
    AlertDialog alertDialog;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_login);

        ButterKnife.bind(this);
        initializefb();



        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

        callbackManager = CallbackManager.Factory.create();

        validator = new Validator(BrandLogin.this);
        validator.setValidationListener(this);

    }






    @Override
    public void onValidationSucceeded() {


        progressDialog = new ProgressDialog(BrandLogin.this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.show();
        RetrofitUtil retrofitUtil = new RetrofitUtil(BrandLogin.this);
        retrofitUtil.BrandLoginResponse(edLogin.getText().toString(), edPassword.getText().toString());

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

    @OnClick(R.id.register_move)
    public void openRegisterActivity() {

        Intent intent = new Intent(getApplicationContext(), ActivityBrandRegister.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);


    }

//=========================================================================================================================================
    //                                            BUTTON CLICK
//=========================================================================================================================================



// ======= FORGET PASSWORD CLICK =======

    @OnClick(R.id.textView3)
    public void openforgetpassword() {
        forgetpassword();
    }

    //======= SEND DATA FORGET PASSWORD TO API ======
    @Override
    protected void sendforgetpassworddata() {


        dialog = new Dialog(BrandLogin.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custom_dialog_progress);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCancelable(false);
        dialog.show();

        RetrofitUtil retrofitUtil = new RetrofitUtil(BrandLogin.this);
        retrofitUtil.BrandForgetpwdResponse(Hawk.get("femail").toString());

    }


    //======= LOGIN BUTTON CLICK ========


    @OnClick(R.id.loginbtn)
    public void loginbtnclick()
    {
        validator.validate();
    }



    //====== FACEBOOK BUTTON CLICK =======


    @OnClick(R.id.regfbbtn)
    public void fbclick()
    {
        facebookloginClick();
    }


    //======= SEND FACEBOOK DATA TO API ======


    @Override
    protected void senduserdetails() {

        dialog = new Dialog(BrandLogin.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custom_dialog_progress);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCancelable(false);
        dialog.show();

        RetrofitUtil retrofitUtil = new RetrofitUtil(BrandLogin.this);
        retrofitUtil.BrandFacebookapiResponse(Hawk.get(Contants.USER_EMAIL, ""), Hawk.get(Contants.USER_ID, ""), Hawk.get(Contants.USER_FIRSTNAME, ""), Hawk.get(Contants.USER_LASTNAME, ""), Hawk.get(Contants.USER_PROFILELINK, ""));

    }

//=========================================================================================================================================
//                                                         API RESPONSE
//=========================================================================================================================================

    @Override
    public void getresponse(Object response) {

//====================================================== LOGIN RESPONSE ===================================================================

        if (response instanceof BrandLoginApi) {
            if (((BrandLoginApi) response).getSuccess()) {
                progressDialog.hide();
                progressDialog.cancel();
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), ((BrandLoginApi) response).getMessage(), Toast.LENGTH_LONG).show();

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                progressDialog.hide();
                progressDialog.cancel();
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), ((BrandLoginApi) response).getMessage(), Toast.LENGTH_LONG).show();
            }
        }

//===================================================== FORGET PASSWORD ===================================================================

        else if (response instanceof BrandForgetPwd)
        {
            if (((BrandForgetPwd) response).getSuccess())
            {

                dialog.hide();
                dialog.cancel();
                dialog.dismiss();
                Toast.makeText(getApplicationContext(), ((BrandForgetPwd) response).getMessage(), Toast.LENGTH_LONG).show();

            }
            else
            {

                dialog.hide();
                dialog.cancel();
                dialog.dismiss();
                Toast.makeText(getApplicationContext(), ((BrandForgetPwd) response).getMessage(), Toast.LENGTH_LONG).show();
            }

        }

//====================================================== FACEBOOK ========================================================================

        else if (response instanceof BrandFacebookApi)
        {
            if (((BrandFacebookApi) response).getSuccess())
            {
                dialog.hide();
                dialog.cancel();
                dialog.dismiss();
                Toast.makeText(getApplicationContext(), ((BrandFacebookApi) response).getMessage(), Toast.LENGTH_LONG).show();

            }
            else
            {

                dialog.hide();
                dialog.cancel();
                dialog.dismiss();

                Toast.makeText(getApplicationContext(), ((BrandFacebookApi) response).getMessage(), Toast.LENGTH_LONG).show();
            }

        }

    }

    @Override
    public void getfailerresponse(Throwable throwable) {

    }
}
