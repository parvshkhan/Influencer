package influencer.com.influencer.activities.fragments.influencer.register;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
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
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import influencer.com.influencer.R;
import influencer.com.influencer.activities.activity.influencer.ActivityAbout;
import influencer.com.influencer.activities.constants.Contants;
import influencer.com.influencer.activities.customtoast.CustomToast;

import static com.facebook.FacebookSdk.getApplicationContext;
import static com.facebook.FacebookSdk.isFacebookRequestCode;
import static influencer.com.influencer.R.drawable.user;


public class FragmentSocialAccount extends Fragment {


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

    @BindView(R.id.editText6)
    TextView edinstausername;
    Dialog progressDialog;


    View view;
    Context context;
    CallbackManager callbackManager;
    LoginManager loginManager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_fragment_social_account, container, false);
        AccessToken accessToken=AccessToken.getCurrentAccessToken();
        ButterKnife.bind(this, view);
        if(accessToken != null && !accessToken.isExpired())
        {
            fbbtn.setText("Logout");
            Picasso.with(getActivity()).load(String.valueOf(Hawk.get(Contants.FIMG,""))).into(fbimg);
            fbname.setVisibility(View.VISIBLE);
            Hawk.get(Contants.FIMG);
            fbname.setText(Hawk.get(Contants.USER_FIRSTNAME,""));
        }
        if (Hawk.get(Contants.INSTANAME)!=null)
        {

            edinstausername.setVisibility(View.GONE);
            instanmae.setVisibility(View.VISIBLE);
            instaimg.setVisibility(View.VISIBLE);
            instabtn.setText("Logout");
            instanmae.setText(Hawk.get(Contants.INSTANAME,""));
            Picasso.with(getActivity()).load(Hawk.get(Contants.INSTAIMG,"")).error(user).into(instaimg);

        }



        context = view.getContext();
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(getApplicationContext());

        callbackManager = CallbackManager.Factory.create();

        return view;
    }


    public boolean clickevent() {
//        boolean isformcompelte=false;


        return true;

    }




    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (isFacebookRequestCode(requestCode)) {
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }
        if (requestCode == 4002) {

            edinstausername.setVisibility(View.GONE);
            Picasso.with(getActivity()).load(data.getStringExtra("DATA_URL")).into(instaimg);
            instaimg.setVisibility(View.VISIBLE);
            String name=data.getStringExtra("fullname");
            String username=data.getStringExtra("User");
            instanmae.setText(name);
            instabtn.setVisibility(View.VISIBLE);
            instanmae.setVisibility(View.VISIBLE);
            progressDialog.dismiss();
            if (!name.isEmpty())
            {
                instabtn.setVisibility(View.VISIBLE);
                instabtn.setText("Logout");
            }

            Hawk.put(Contants.INSTANAME,name);
            Hawk.put(Contants.ISTALINK,username);
            Hawk.put(Contants.INSTAIMG,data.getStringExtra("DATA_URL"));
        }
        if (requestCode == 4001)
        {
            progressDialog.dismiss();
            Toast.makeText(getActivity(),"please enter valid user name",Toast.LENGTH_SHORT).show();

        }

    }




    @OnClick(R.id.btn_id)
    public void instabtnclick() {
        String username = edinstausername.getText().toString();

        if (Hawk.get(Contants.INSTANAME)!=null) {
            Hawk.delete(Contants.INSTANAME);
            Hawk.delete(Contants.INSTAIMG);
            Hawk.delete(Contants.ISTALINK);
            instaimg.setVisibility(View.GONE);
            edinstausername.setVisibility(View.VISIBLE);
            instanmae.setVisibility(View.GONE);
            edinstausername.setText("");
            instabtn.setText("Instagram");
        }
        else if (username.equals("")) {
            edinstausername.setError("Can't Empty");
        }
        else
        {

            progressDialog = new Dialog(getActivity());
            progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            progressDialog.setContentView(R.layout.custom_dialog_progress);
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            progressDialog.setCancelable(false);
            progressDialog.show();
            String url = "https://www.instagram.com/"+username+"/?__a=1";
            ((ActivityAbout) getActivity()).instacalledmethod(url,progressDialog);
        }
    }



    @OnClick(R.id.btn_id2)
    public void fbbtnclick() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        if(accessToken != null && !accessToken.isExpired())
        {
            LoginManager.getInstance().logOut();
            fbimg.setImageResource(R.drawable.ic_user);
            fbname.setVisibility(View.GONE);
            Hawk.delete(Contants.USER_FIRSTNAME);
            fbbtn.setText("Facebook");

        }
        else
        {
            loginManager.getInstance().logInWithReadPermissions(FragmentSocialAccount.this, Arrays.asList("public_profile", "email"));

            loginManager.getInstance().registerCallback(callbackManager,
                    new FacebookCallback<LoginResult>() {
                        @Override
                        public void onSuccess(LoginResult loginResult) {

                            fbname.setVisibility(View.VISIBLE);
                            checkinsta.setText("connect");
                            setFacebookData(loginResult);
                            fbbtn.setText("Logout");



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

    }



    private void setFacebookData(LoginResult loginResult) {


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
                            String firstname = response.getJSONObject().getString("name");
                            String imgpath = "https://graph.facebook.com/"+id+"/picture?type=large";
                            Hawk.put(Contants.USER_PROFILELINK, link);
                            Hawk.put(Contants.USER_ID, id);
                            Hawk.put(Contants.USER_FIRSTNAME, firstname);
                            fbname.setText(firstname);
                            Picasso.with(getActivity()).load(imgpath).into(fbimg);
                            Hawk.put(Contants.FIMG, imgpath);



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


