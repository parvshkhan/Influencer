package influencer.com.influencer.activities.fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.orhanobut.hawk.Hawk;
import com.squareup.picasso.Picasso;
import com.steelkiwi.instagramhelper.InstagramHelper;
import com.steelkiwi.instagramhelper.InstagramHelperConstants;
import com.steelkiwi.instagramhelper.model.InstagramUser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import influencer.com.influencer.R;
import influencer.com.influencer.activities.activity.ActivityLogin;
import influencer.com.influencer.activities.application.MyApplication;
import influencer.com.influencer.activities.constants.Contants;
import influencer.com.influencer.activities.customtoast.CustomToast;
import influencer.com.influencer.activities.retrofit.RetrofitUtil;

import static android.app.Activity.RESULT_OK;
import static com.facebook.FacebookSdk.getApplicationContext;
import static com.facebook.FacebookSdk.isFacebookRequestCode;


public class FragmentSocialAccount extends Fragment {

    InstagramHelper instagramHelper;
    @BindView(R.id.imageView36)
    CircleImageView instaimg;

    @BindView(R.id.textView67)
    TextView instanmae;

    @BindView(R.id.btn_id)
    Button instabtn;

    @BindView(R.id.imageView38)
    CircleImageView fbimg;

    @BindView(R.id.textView68)
    TextView fbname;

    @BindView(R.id.btn_id2)
    Button fbbtn;

    @BindView(R.id.textView69)
    TextView checkinsta;

    @BindView(R.id.textView70)
    TextView checkfacebook;





    View view;
    Context context;
    CallbackManager callbackManager;
    LoginManager loginManager;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view= inflater.inflate(R.layout.fragment_fragment_social_account, container, false);

        ButterKnife.bind(this, view);

        context=view.getContext();
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(getApplicationContext());

        callbackManager = CallbackManager.Factory.create();
        instagramHelper = MyApplication.getInstagramHelper();
        return view;
    }

    public boolean clickevent() {
        boolean isformcomplete=false;
        if (checkfacebook.getText().equals(""))
        {
            Toast.makeText(getContext(),"please connect with facebook ",Toast.LENGTH_SHORT).show();

        }
        else if (checkinsta.getText().equals(""))
        {
            Toast.makeText(getContext(),"please connect with insta ",Toast.LENGTH_SHORT).show();
        }
        else
        {
           isformcomplete=true;
        }

        return isformcomplete;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(isFacebookRequestCode(requestCode))
        {
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }


        else if (requestCode == InstagramHelperConstants.INSTA_LOGIN && resultCode == RESULT_OK) {
            InstagramUser user = instagramHelper.getInstagramUser(getContext());
            checkfacebook.setText("Connected Successful");
            instabtn.setVisibility(View.GONE);
            checkfacebook.setVisibility(View.VISIBLE);
            instanmae.setVisibility(View.VISIBLE);
            String path=user.getData().getProfilePicture();
            Picasso.with(getContext()).load(user.getData().getProfilePicture()).into(instaimg);
            instanmae.setText(user.getData().getFullName());
            String userrname=user.getData().getUsername();
String name=String.valueOf(user.getData().getFullName());
            Hawk.put(Contants.INSTAIMG,path);
            Hawk.put(Contants.INSTANAME,name);
            Hawk.put(Contants.ISTALINK,userrname);

        } else {
//            Toast.makeText(getContext(), "Login failed", Toast.LENGTH_LONG).show();
        }
    }



    @OnClick(R.id.btn_id)
    public void instabtnclick()
    {
        instagramHelper.loginFromActivity(getActivity());

    }

    @OnClick(R.id.btn_id2)
    public void fbbtnclick()
    {
        loginManager.getInstance().logInWithReadPermissions(FragmentSocialAccount.this, Arrays.asList("public_profile","email"));

        loginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {

checkinsta.setVisibility(View.VISIBLE);
fbbtn.setVisibility(View.GONE);
fbname.setVisibility(View.VISIBLE);
                        checkinsta.setText("Connected Successful");

                        setFacebookData(loginResult);

                    }
                    @Override
                    public void onCancel() {

                        String cencel="cencel";
                        new CustomToast(getApplicationContext(),cencel);

                    }

                    @Override
                    public void onError(FacebookException exception) {

                        String cencel="error";
                        new CustomToast(getApplicationContext(),cencel);

                    }
                });
    }

    private void setFacebookData(LoginResult loginResult) {


        final GraphRequest request = GraphRequest.newMeRequest(
                loginResult.getAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @SuppressLint("LongLogTag")
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {


                        try {
                            Log.i("Response",response.toString());
                            String id=response.getJSONObject().getString("id");
                            String link="https://www.facebook.com/"+id;
                            String firstname=response.getJSONObject().getString("name");
                            String imgpath= String.valueOf(Profile.getCurrentProfile().getProfilePictureUri(200,200));
                            Hawk.put(Contants.USER_PROFILELINK,link);
                            Hawk.put(Contants.USER_ID,id);
                            Hawk.put(Contants.USER_FIRSTNAME,firstname);
                            Hawk.put(Contants.FIMG,imgpath);
                            fbname.setText(firstname);

                            Picasso.with(getApplicationContext()).load(imgpath).into(fbimg);




                            if(response.getJSONObject().has("email"))
                            {
                                String email=response.getJSONObject().getString("email");
                                Hawk.put(Contants.USER_EMAIL,email);

//dfgfg


                            }
                            else
                            {

                                Toast.makeText(getApplicationContext(),"Don't Recieve email from your id please Enter your email",Toast.LENGTH_LONG).show();
                                final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getApplicationContext());

                                view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.activity_forget_password, null);


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
//                                            senduserdetails();

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
        parameters.putString("fields", "id,email,name");
        request.setParameters(parameters);
        request.executeAsync();


    }


}


