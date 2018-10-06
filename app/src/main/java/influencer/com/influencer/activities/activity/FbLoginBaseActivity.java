package influencer.com.influencer.activities.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.orhanobut.hawk.Hawk;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import influencer.com.influencer.R;
import influencer.com.influencer.activities.apiResponses.registerAPI.brand.BrandForgetPwd;
import influencer.com.influencer.activities.constants.Contants;
import influencer.com.influencer.activities.customtoast.CustomToast;
import influencer.com.influencer.activities.retrofit.RetrofitUtil;


public abstract class FbLoginBaseActivity extends AppCompatActivity {

    protected LoginManager loginManager;
    protected CallbackManager callbackManager;

    protected AlertDialog.Builder dialogBuilder;
    protected AlertDialog alertDialog;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void initializefb() {


        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(getApplicationContext());

        callbackManager = CallbackManager.Factory.create();


    }

//========================================================================================================================================
//                                                        FACEBOOK INTEGRATION
//========================================================================================================================================

    public void facebookloginClick() {
        loginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile", "email"));

        loginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {


                        setFacebookData(loginResult);

                    }

                    @Override
                    public void onCancel() {

                        String cencel = "cencel";
                        new CustomToast(getApplicationContext(), cencel);

                    }

                    @Override
                    public void onError(FacebookException exception) {

                        String cencel = "error";
                        new CustomToast(getApplicationContext(), cencel);

                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);


        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void setFacebookData(final LoginResult loginResult) {

        final GraphRequest request = GraphRequest.newMeRequest(
                loginResult.getAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @SuppressLint("LongLogTag")
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {


                        try {
                            Log.i("Response", response.toString());
                            String id = response.getJSONObject().getString("id");
                            String link = "https://www.facebook.com/" + id;
                            String firstname = response.getJSONObject().getString("first_name");
                            String lastname = response.getJSONObject().getString("last_name");
                            Hawk.put(Contants.FIMG,"https://graph.facebook.com/"+id+"/picture?type=large");
                            Hawk.put(Contants.USER_PROFILELINK, link);
                            Hawk.put(Contants.USER_ID, id);
                            Hawk.put(Contants.USER_FIRSTNAME, firstname);
                            Hawk.put(Contants.USER_LASTNAME, lastname);
                            if (response.getJSONObject().has("email")) {
                                String email = response.getJSONObject().getString("email");
                                Hawk.put(Contants.USER_EMAIL, email);

                                senduserdetails();


                            } else {

                                Toast.makeText(getApplicationContext(), "Don't Recieve email from your id please Enter your email", Toast.LENGTH_LONG).show();
                                final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(FbLoginBaseActivity.this);

                                View view = LayoutInflater.from(FbLoginBaseActivity.this).inflate(R.layout.activity_forget_password, null);


                                dialogBuilder.setView(view);

                                final AlertDialog alertDialog = dialogBuilder.create();
                                final EditText etemail = view.findViewById(R.id.forgotemail);
                                final Button login = view.findViewById(R.id.btnforgotpwd);
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

                                        if (email.matches("[a-zA-Z0-9._-]+@[a-z]+.[ a-z]+") && email.length() > 0)

                                        {
                                            Hawk.put(Contants.USER_EMAIL, email);
                                            senduserdetails();

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


    protected abstract void senduserdetails();


//=======================================================================================================================================
//                                                     FORGET PASSWORD
//=======================================================================================================================================

    public void forgetpassword()
    {
        dialogBuilder = new AlertDialog.Builder(this);

        View view = LayoutInflater.from(FbLoginBaseActivity.this).inflate(R.layout.activity_forget_password, null);


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

                if (email.matches("[a-zA-Z0-9._-]+@[a-z]+.[ a-z]+") && email.length() > 0) {
                    Dialog progressDialog = new Dialog(FbLoginBaseActivity.this);
                    Hawk.put("femail",email);
                    sendforgetpassworddata();






                } else {
                    etemail.setError("Invalid ");
                }

            }
        });

        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        alertDialog.show();
    }








    protected abstract void sendforgetpassworddata();


}
