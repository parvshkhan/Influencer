package influencer.com.influencer.activities.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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
import com.steelkiwi.instagramhelper.InstagramHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import influencer.com.influencer.R;
import influencer.com.influencer.activities.application.MyApplication;
import influencer.com.influencer.activities.retrofit.RetrofitUtil;

public class BrandLogin extends AppCompatActivity implements Validator.ValidationListener {


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


    private InstagramHelper instagramHelper;

    String email, gender, facebookName, phoneno;
    int profilePictureView;
    private ProfileTracker profileTracker;
    private Uri imgUrlFb;

    ProgressDialog progressDialog;
    AlertDialog.Builder dialogBuilder;
    AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_login);

        ButterKnife.bind(this);

        instagramHelper = MyApplication.getInstagramHelper();


        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

        callbackManager = CallbackManager.Factory.create();
        validator = new Validator(BrandLogin.this);
        validator.setValidationListener(this);


        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(edPassword.getWindowToken(), 0);
    }


    @Override
    public void onValidationSucceeded() {

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

    @OnClick(R.id.textView3)
    public void openforgetpassword() {


        dialogBuilder = new AlertDialog.Builder(this);

        view = LayoutInflater.from(BrandLogin.this).inflate(R.layout.activity_forget_password, null);


        dialogBuilder.setView(view);


        alertDialog = dialogBuilder.create();
        final EditText etemail = view.findViewById(R.id.forgotemail);

        view.findViewById(R.id.forgotcencelbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                alertDialog.dismiss();

            }
        });

        view.findViewById(R.id.btnforgotpwd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = etemail.getText().toString();

                if (email.matches("[a-zA-Z0-9._-]+@[a-z]+.[ a-z]+") && email.length()>0)

                {
                    progressDialog=new ProgressDialog ( view.getContext () );
                    progressDialog.setMessage ( "Please wait..." );
                    progressDialog.show ();


                    RetrofitUtil retrofitUtil = new RetrofitUtil( BrandLogin.this);
                    retrofitUtil.ForgetpwdResponse( etemail.getText ().toString () );




                } else {
                    etemail.setError("Invalid ");
                }

            }
        });

        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        alertDialog.show();
    }

    @OnClick(R.id.loginbtn)
    public void loginbtnclick()
    {
validator.validate();
    }
}
