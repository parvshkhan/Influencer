package influencer.com.influencer.activities.activity.influencer;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.Dash;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.orhanobut.hawk.Hawk;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import influencer.com.influencer.R;
import influencer.com.influencer.activities.activity.MainActivity;
import influencer.com.influencer.activities.activity.WaitingPage;
import influencer.com.influencer.activities.adapters.PostPagerAdapter;
import influencer.com.influencer.activities.apiResponses.registerAPI.influencerAPI.UserProfileAPI;
import influencer.com.influencer.activities.apiResponses.registerAPI.influencerDashboardAPI.DashBoardApi;
import influencer.com.influencer.activities.callback.ICallback;
import influencer.com.influencer.activities.constants.Contants;
import influencer.com.influencer.activities.customViewPagger.CustomViewPager;
import influencer.com.influencer.activities.fragments.influencer.FragmentAbout;
import influencer.com.influencer.activities.fragments.influencer.FragmentCharacterstics;
import influencer.com.influencer.activities.fragments.influencer.FragmentContactShipijng;
import influencer.com.influencer.activities.fragments.influencer.FragmentSocialAccount;
import influencer.com.influencer.activities.fragments.influencer.FragmentTarget;
import influencer.com.influencer.activities.retrofit.RetrofitUtil;
import influencer.com.influencer.activities.viewpaggeranimation.DepthTransformation;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class ActivityAbout extends AppCompatActivity implements Validator.ValidationListener, ICallback {

    private Toolbar toolbar;
    @BindView(R.id.psttabs)
    TabLayout tabLayout;
    @BindView(R.id.pstviewpager)
    CustomViewPager viewPager;

    @BindView(R.id.button3)
    TextView backbtn;
    View view;
    @BindView(R.id.button4)
    TextView nextbtn;

    @BindView(R.id.imageView39)
    ImageView backicon;

    FragmentAbout fragmentAbout;
    FragmentTarget fragmentTarget;
    FragmentContactShipijng fragmentContactShipijng;
    FragmentCharacterstics fragmentCharacterstics;
    FragmentSocialAccount fragmentSocialAccount;

    ProgressDialog progressDialog;

    Dialog dialog;

    Dialog pdialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);
        tablayout();
        onpagechangelistner();
    }


    private void onpagechangelistner() {

        viewPager.setPagingEnabled(false);
        viewPager.setOffscreenPageLimit(4);

        tabLayout.clearOnTabSelectedListeners();
        LinearLayout tabStrip = ((LinearLayout) tabLayout.getChildAt(0));
        for (int i = 0; i < tabStrip.getChildCount(); i++) {
            tabStrip.getChildAt(i).setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return true;
                }
            });
        }

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                btnevent(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {


            }
        });

    }


    private void btnevent(int position) {
        if (position == 0) {
            backbtn.setVisibility(View.INVISIBLE);
            backicon.setVisibility(View.INVISIBLE);
        } else {
            backbtn.setVisibility(View.VISIBLE);
            backicon.setVisibility(View.VISIBLE);
        }

        if (position == 4) {
            nextbtn.setText("FINISH");
        } else {
            nextbtn.setText("NEXT");
        }
    }


    private void tablayout() {
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#ffffff"));
        tabLayout.setSelectedTabIndicatorHeight((int) (2 * getResources().getDisplayMetrics().density));
        tabLayout.setTabTextColors(Color.parseColor("#727272"), Color.parseColor("#ffffff"));
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        fragmentSocialAccount.onActivityResult(requestCode, resultCode, data);
    }


    private void setupViewPager(ViewPager viewPager) {
        PostPagerAdapter adapter = new PostPagerAdapter(getSupportFragmentManager());

        DepthTransformation depthTransformation = new DepthTransformation();

        fragmentAbout = new FragmentAbout();
        fragmentTarget = new FragmentTarget();
        fragmentContactShipijng = new FragmentContactShipijng();
        fragmentCharacterstics = new FragmentCharacterstics();
        fragmentSocialAccount = new FragmentSocialAccount();

        adapter.addFragment(fragmentAbout, "About");
        adapter.addFragment(fragmentTarget, "Target");
        adapter.addFragment(fragmentContactShipijng, "Contact/Shipping");
        adapter.addFragment(fragmentCharacterstics, "CharacterStics");
        adapter.addFragment(fragmentSocialAccount, "Social Account");
        viewPager.setPageTransformer(true, depthTransformation);
        viewPager.setAdapter(adapter);

    }


    @OnClick(R.id.button4)
    public void opennextfragment() {
        switch (viewPager.getCurrentItem()) {
            case 0:

                if (fragmentAbout.clickevent()) {
                    viewPager.setCurrentItem(1);
                }
                break;

            case 1:
                if (fragmentTarget.clickevent()) {
                    viewPager.setCurrentItem(2);
                }
                break;


            case 2:
                if (fragmentContactShipijng.clickevent()) {
                    viewPager.setCurrentItem(3);
                }
                break;

            case 3:
                if (fragmentCharacterstics.clickevent()) {
                    viewPager.setCurrentItem(4);
                }
                break;
            case 4:
                if (fragmentSocialAccount.clickevent()) {

                    insertdata();
                }
                break;

            default:

                Toast.makeText(this, "End", Toast.LENGTH_SHORT).show();

                break;
        }

    }

    private void insertdata() {


        dialog = new Dialog(ActivityAbout.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custom_dialog_progress);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.show();
        dialog.setCancelable(false);
        RetrofitUtil retrofitUtil = new RetrofitUtil(ActivityAbout.this);


        File file = new File(Hawk.get(Contants.IMGLINK, ""));

        MultipartBody.Part fbodyImage = prepareFilePart(this, file, "file");

        RequestBody fBodyinfId = RequestBody.create(MediaType.parse("text/plain"), Hawk.get(Contants.INFLUENCERID, ""));
        RequestBody name = RequestBody.create(MediaType.parse("text/plain"), Hawk.get(Contants.NAME, ""));
        RequestBody gender = RequestBody.create(MediaType.parse("text/plain"), Hawk.get(Contants.GENDER, ""));
        RequestBody language = RequestBody.create(MediaType.parse("text/plain"), Hawk.get(Contants.LANGUAGE, ""));
        RequestBody dob = RequestBody.create(MediaType.parse("text/plain"), Hawk.get(Contants.DOB, ""));
        RequestBody describe = RequestBody.create(MediaType.parse("text/plain"), Hawk.get(Contants.DESCRIBE, ""));
        RequestBody interest = RequestBody.create(MediaType.parse("text/plain"), (String) Hawk.get("data"));
        RequestBody ratio = RequestBody.create(MediaType.parse("text/plain"), Hawk.get(Contants.RATIO, ""));
        RequestBody range = RequestBody.create(MediaType.parse("text/plain"), Hawk.get(Contants.AGERANGE, ""));
        RequestBody fname = RequestBody.create(MediaType.parse("text/plain"), Hawk.get(Contants.FNAME, ""));
        RequestBody lname = RequestBody.create(MediaType.parse("text/plain"), Hawk.get(Contants.LNAME, ""));
        RequestBody country = RequestBody.create(MediaType.parse("text/plain"), Hawk.get(Contants.COUNTRY, ""));
        RequestBody address = RequestBody.create(MediaType.parse("text/plain"), Hawk.get(Contants.ADDRESS, ""));
        RequestBody city = RequestBody.create(MediaType.parse("text/plain"), Hawk.get(Contants.CIRY, ""));
        RequestBody postcode = RequestBody.create(MediaType.parse("text/plain"), Hawk.get(Contants.POSTALCODE, ""));
        RequestBody height = RequestBody.create(MediaType.parse("text/plain"), Hawk.get(Contants.HEIGHT, ""));
        RequestBody jeans = RequestBody.create(MediaType.parse("text/plain"), Hawk.get(Contants.JEANS, ""));
        RequestBody width = RequestBody.create(MediaType.parse("text/plain"), Hawk.get(Contants.WAIST, ""));
        RequestBody pants = RequestBody.create(MediaType.parse("text/plain"), Hawk.get(Contants.PANTS, ""));
        RequestBody shirt = RequestBody.create(MediaType.parse("text/plain"), Hawk.get(Contants.SHIRT, ""));
        RequestBody shoes = RequestBody.create(MediaType.parse("text/plain"), Hawk.get(Contants.SHOES, ""));
        RequestBody underwear = RequestBody.create(MediaType.parse("text/plain"), Hawk.get(Contants.UNDERWEAR, ""));
        RequestBody extrainfo = RequestBody.create(MediaType.parse("text/plain"), Hawk.get(Contants.EXTRAINFO, ""));
        RequestBody instalink = RequestBody.create(MediaType.parse("text/plain"), Hawk.get(Contants.ISTALINK,""));
        RequestBody instaimg = RequestBody.create(MediaType.parse("text/plain"), Hawk.get(Contants.INSTAIMG,""));
        RequestBody fbimg = RequestBody.create(MediaType.parse("text/plain"), Hawk.get(Contants.FIMG, ""));
        RequestBody fbname = RequestBody.create(MediaType.parse("text/plain"), Hawk.get(Contants.USER_FIRSTNAME, ""));
        RequestBody instaname = RequestBody.create(MediaType.parse("text/plain"), Hawk.get(Contants.INSTANAME,""));
        RequestBody fblink = RequestBody.create(MediaType.parse("text/plain"), Hawk.get(Contants.USER_PROFILELINK, ""));

        retrofitUtil.userdetails(fBodyinfId, name, fbodyImage, gender, language, dob, describe, interest, ratio, range, fname, lname, country, address, city, postcode, height, jeans, width, pants, shirt, shoes, underwear, extrainfo, instalink, instaimg, fbimg, fbname, instaname, fblink);
    }

    @NonNull
    public static MultipartBody.Part prepareFilePart(Context context, File imagePath, String partName) {

        RequestBody requestFile =
                RequestBody.create(
                        MediaType.parse("image/*"),
                        imagePath
                );


        return MultipartBody.Part.createFormData(partName, imagePath.getName(), requestFile);
    }


    @OnClick(R.id.button3)
    public void movepreviousfragment() {

        switch (viewPager.getCurrentItem()) {
            case 1:
                viewPager.setCurrentItem(0);
                break;

            case 2:
                viewPager.setCurrentItem(1);
                break;


            case 3:
                viewPager.setCurrentItem(2);
                break;

            case 4:
                viewPager.setCurrentItem(3);
                break;


            default:


                break;
        }


    }


    @Override
    public void onValidationSucceeded() {

        Toast.makeText(getApplicationContext(), "done", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {

        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);

            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }

    }

    @Override
    public void getresponse(Object response) {


        if (response instanceof UserProfileAPI) {
            //  progressDialog.dismiss();
            if (((UserProfileAPI) response).getSuccess()) {

                RetrofitUtil retrofitUtil = new RetrofitUtil(ActivityAbout.this);
                retrofitUtil.dashboardresponse(String.valueOf(Hawk.get(Contants.INFLUENCERID, "")));


            } else {


                Toast.makeText(getApplicationContext(), ((UserProfileAPI) response).getMessage(), Toast.LENGTH_LONG).show();
                progressDialog.dismiss();

            }

        } else if (response instanceof DashBoardApi) {

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

            } else
                Toast.makeText(getApplicationContext(), ((DashBoardApi) response).getMessage(), Toast.LENGTH_LONG).show();
        }

        else if (response instanceof String) {

            JSONObject jsonObject= null;
            if (response.equals("{}"))
            {
                Intent intent =new Intent();
                intent.putExtra("null", (CharSequence) jsonObject);
                fragmentSocialAccount.onActivityResult(4001,0,intent);
            }
            else
            {
                try {
                    jsonObject = new JSONObject((String) response);
                    Log.d("USER_NAME", jsonObject.optJSONObject("graphql").optJSONObject("user").optString("full_name"));
                    Log.d("USER_NAME", jsonObject.optJSONObject("graphql").optJSONObject("user").optString("profile_pic_url_hd"));

                    Intent intent = new Intent();
                    intent.putExtra("DATA_URL",jsonObject.optJSONObject("graphql").optJSONObject("user").optString("profile_pic_url_hd"));
                    intent.putExtra("fullname",jsonObject.optJSONObject("graphql").optJSONObject("user").optString("full_name"));
                    intent.putExtra("User",jsonObject.optJSONObject("graphql").optJSONObject("user").optString("username"));


                    fragmentSocialAccount.onActivityResult(4002,0,intent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }



        }
        else if (response==null)
        {
            if(pdialog.isShowing()) {
                Toast.makeText(getApplicationContext(), "server error try again", Toast.LENGTH_SHORT).show();
                pdialog.dismiss();
            }
            else if (progressDialog.isShowing()) {
                Toast.makeText(getApplicationContext(), "server error try again", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        }


    }

    @Override
    public void getfailerresponse(Throwable throwable) {

    }


    public void instacalledmethod(String url,Dialog pdialog) {
        this.pdialog=pdialog;
        RetrofitUtil retrofitUtil = new RetrofitUtil(ActivityAbout.this);
        retrofitUtil.instagramintegration(url);

    }

}