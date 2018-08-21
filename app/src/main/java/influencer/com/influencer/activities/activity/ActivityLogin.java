package influencer.com.influencer.activities.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.FaceDetector;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.appevents.AppEventsLogger;

import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;
import com.orhanobut.hawk.Hawk;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import influencer.com.influencer.R;
import influencer.com.influencer.activities.apiResponses.registerAPI.ForgetPwdAPI;
import influencer.com.influencer.activities.apiResponses.registerAPI.LoginAPI;
import influencer.com.influencer.activities.apiResponses.registerAPI.RegisterAPI;
import influencer.com.influencer.activities.callback.IForgetPasswordCallback;
import influencer.com.influencer.activities.callback.ILoginCallback;
import influencer.com.influencer.activities.callback.IRegisterCallback;
import influencer.com.influencer.activities.constants.Contants;
import influencer.com.influencer.activities.retrofit.RetrofitUtil;
import retrofit2.Response;

import static android.os.Build.ID;
import static android.provider.ContactsContract.Intents.Insert.EMAIL;
import static android.provider.ContactsContract.Intents.Insert.PHONE;

public class ActivityLogin extends AppCompatActivity implements Validator.ValidationListener,IForgetPasswordCallback,ILoginCallback {

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

    @BindView(R.id.login_password)
    @Password(min = 6, scheme = Password.Scheme.ALPHA_NUMERIC_MIXED_CASE)
    EditText edPassword;

    CallbackManager callbackManager;
    LoginManager loginManager;


    String email,gender,facebookName,phoneno;
    int profilePictureView;
    private ProfileTracker profileTracker;
    private Uri imgUrlFb;

    ProgressDialog progressDialog;
    AlertDialog.Builder   dialogBuilder;
    AlertDialog alertDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_infuencer);
//        hidingTheStatusBar();
        ButterKnife.bind(this);




        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

        callbackManager = CallbackManager.Factory.create();
        validator = new Validator(ActivityLogin.this);
        validator.setValidationListener(this);


        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(edPassword.getWindowToken(), 0);




    }

//    private void hidingTheStatusBar() {
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//
//
//    }


    @Override
    public void onValidationSucceeded() {
        progressDialog=new ProgressDialog ( ActivityLogin.this );
        progressDialog.setMessage ( "Please Wait..." );

progressDialog.show ();

        RetrofitUtil retrofitUtil = new RetrofitUtil (ActivityLogin.this);
        retrofitUtil.LoginResponse (edLogin.getText().toString(),edPassword.getText().toString());

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

    }

    //forgot password validations with button click--------------------------------------------------------------------------





    @OnClick(R.id.textView3)
    public void openforgetpassword() {




         dialogBuilder = new AlertDialog.Builder(this);

        view = LayoutInflater.from(ActivityLogin.this).inflate(R.layout.activity_forget_password, null);


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


                    RetrofitUtil retrofitUtil = new RetrofitUtil ( ActivityLogin.this );
                    retrofitUtil.ForgetResponse ( etemail.getText ().toString () );




                } else {
                    etemail.setError("Invalid ");
                }

            }
        });

        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        alertDialog.show();
    }

    //----------------------------------------------------------------------------------------------------------------------------------------------------








// ------------------Facebook Integration with button click-----------------------------------------------------------------------------------------------
    @OnClick(R.id.regfbbtn)
    public void facebooklogin()
    {


        loginManager.getInstance().logInWithReadPermissions(ActivityLogin.this, Arrays.asList("public_profile","email"));

        loginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {




                        setFacebookData(loginResult);

                    }
                    @Override
                    public void onCancel() {

                        Toast.makeText(getApplicationContext(),"cencel",Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onError(FacebookException exception) {

                        Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_LONG).show();

                    }
                });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }
    //-------------------------------------------------------------------------------------------------------------------------------------------------





    //-----------------------Facebook Integration -----------------------------------------------------------------------------------------------------
    private void setFacebookData(final LoginResult loginResult)
    {

        final GraphRequest request = GraphRequest.newMeRequest(
                loginResult.getAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        // Application code
                        try {
                            Log.i("Response",response.toString());

//
                          String firstname=response.getJSONObject ().getString ( "first_name" );
                          String lastname=response.getJSONObject ().getString ( "last_name" );
                          String id=response.getJSONObject ().getString ( "id" );
                          String link="https://www.facebook.com/profile.php?id=";



                            if(response.getJSONObject().has("email"))
                            {
                                String email=response.getJSONObject().getString("email");
                                Hawk.put(Contants.USER_EMAIL,email);


                            }
                            else
                            {

                                Toast.makeText(getApplicationContext(),"Don't Recieve email from your id please Enter your email",Toast.LENGTH_LONG).show();
                                final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(ActivityLogin.this);

                                view = LayoutInflater.from(ActivityLogin.this).inflate(R.layout.activity_forget_password, null);


                                dialogBuilder.setView(view);

                                final AlertDialog alertDialog = dialogBuilder.create();
                                final EditText etemail = view.findViewById(R.id.forgotemail);
                                final Button login=view.findViewById(R.id.btnforgotpwd);
                                login.setText("Login");

                                view.findViewById(R.id.forgotcencelbtn).setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {


                                        alertDialog.dismiss();

                                    }
                                });


                               login.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View v) {
                                       String email = etemail.getText().toString();

                                       if (email.matches("[a-zA-Z0-9._-]+@[a-z]+.[ a-z]+") && email.length()>0)

                                       {
                                           Hawk.put(Contants.USER_EMAIL,email);

                                           Intent intent=new Intent(getApplicationContext(),ActivitySelectIntrest.class);

                                           startActivity(intent);


                                       } else {
                                           etemail.setError("Invalid Email");
                                       }
                                   }
                               });

                                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                                alertDialog.show();

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,email,first_name,last_name");
        request.setParameters(parameters);
        request.executeAsync();

    }




//------------------------------------------------------------------------------------------------------------------------------------------------------


    @Override
    public void getRegisterResponseSuccess(Response <ForgetPwdAPI> forgetPwdAPIResponse) {

        if(forgetPwdAPIResponse.body ( ) != null) {
            if(forgetPwdAPIResponse.body ().getSuccess ()) {
              Toast.makeText ( getApplicationContext (),forgetPwdAPIResponse.body ().getMessage (),Toast.LENGTH_SHORT ).show ();
alertDialog.dismiss ();

              progressDialog.hide ();



            } else {
                Toast.makeText ( getApplicationContext (),forgetPwdAPIResponse.body ().getMessage (),Toast.LENGTH_SHORT ).show ();

                progressDialog.hide ();
            }

        }

    }

    @Override
    public void getLoginResponseSuccess(Response <LoginAPI> loginAPIResponse) {

        if(loginAPIResponse.body ( ) != null) {
            if(loginAPIResponse.body ().getSuccess ()) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();


            } else {
                Toast.makeText ( getApplicationContext (),loginAPIResponse.body ().getMessage (),Toast.LENGTH_SHORT ).show ();

                progressDialog.hide ();
            }

        }


    }

    @Override
    public void getRegisterFailure(Throwable registerAPIResponse) {

    }
//------------------------------------------------------------------------------------------------------------------------------------------------------


}
