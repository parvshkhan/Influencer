package influencer.com.influencer.activities.activity;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.jaygoo.widget.RangeSeekBar;
import com.mobsandgeeks.saripaar.ContextualAnnotationRule;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.orhanobut.hawk.Hawk;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import influencer.com.influencer.R;
import influencer.com.influencer.activities.adapters.PostPagerAdapter;
import influencer.com.influencer.activities.callback.ICallback;
import influencer.com.influencer.activities.constants.Contants;
import influencer.com.influencer.activities.customViewPagger.CustomViewPager;
import influencer.com.influencer.activities.fragments.FragmentAbout;
import influencer.com.influencer.activities.fragments.FragmentCharacterstics;
import influencer.com.influencer.activities.fragments.FragmentContactShipijng;
import influencer.com.influencer.activities.fragments.FragmentSocialAccount;
import influencer.com.influencer.activities.fragments.FragmentTarget;
import influencer.com.influencer.activities.retrofit.RetrofitUtil;
import influencer.com.influencer.activities.viewpaggeranimation.DepthTransformation;

public class ActivityAbout extends AppCompatActivity implements Validator.ValidationListener,ICallback {

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

        tabLayout.clearOnTabSelectedListeners();
        LinearLayout tabStrip = ((LinearLayout)tabLayout.getChildAt(0));
        for(int i = 0; i < tabStrip.getChildCount(); i++) {
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
        if(position==0)
        {
            backbtn.setVisibility(View.INVISIBLE);
            backicon.setVisibility(View.INVISIBLE);
        }
        else
        {
            backbtn.setVisibility(View.VISIBLE);
            backicon.setVisibility(View.VISIBLE);
        }

        if(position==4)
        {
            nextbtn.setText("FINISH");
        }
        else
        {
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
        PostPagerAdapter adapter=new PostPagerAdapter(getSupportFragmentManager());

        DepthTransformation depthTransformation = new DepthTransformation();

        fragmentAbout =new FragmentAbout();
        fragmentTarget=new FragmentTarget();
        fragmentContactShipijng=new FragmentContactShipijng();
        fragmentCharacterstics=new FragmentCharacterstics();
        fragmentSocialAccount=new FragmentSocialAccount();

        adapter.addFragment(fragmentAbout, "About");
        adapter.addFragment(fragmentTarget, "Target");
        adapter.addFragment(fragmentContactShipijng, "Contact/Shipping");
        adapter.addFragment(fragmentCharacterstics, "CharacterStics");
        adapter.addFragment(fragmentSocialAccount, "Social Account");
        viewPager.setPageTransformer(true,depthTransformation);
        viewPager.setAdapter(adapter);

    }


    @OnClick(R.id.button4)
    public void opennextfragment()
    {
        switch (viewPager.getCurrentItem())
        {
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
                if (fragmentSocialAccount.clickevent())
                {

                    insertdata();
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    finish();
                    overridePendingTransition(R.anim.fadein, R.anim.slide_to_right);
                }
                break;

            default:

                Toast.makeText(this, "End", Toast.LENGTH_SHORT).show();

                break;
        }

    }

    private void insertdata() {


        progressDialog=new ProgressDialog ( this);
        progressDialog.setMessage ( "Please wait..." );
        progressDialog.show ();

        RetrofitUtil retrofitUtil = new RetrofitUtil( ActivityAbout.this );
        retrofitUtil.userdetails(Hawk.get(Contants.INFLUENCERID,""),Hawk.get(Contants.NAME,""),Hawk.get(Contants.IMGLINK,""),Hawk.get(Contants.GENDER,""),Hawk.get(Contants.LANGUAGE,""),Hawk.get(Contants.DOB,""),Hawk.get(Contants.DESCRIBE,""),
                Hawk.get(Contants.ITEMS,""),Hawk.get(Contants.RATIO,""),Hawk.get(Contants.AGERANGE,""),Hawk.get(Contants.FNAME,""),Hawk.get(Contants.LNAME,""),Hawk.get(Contants.COUNTRY,""),
                Hawk.get(Contants.ADDRESS,""),Hawk.get(Contants.CIRY,""),Hawk.get(Contants.POSTALCODE,""),Hawk.get(Contants.HEIGHT,""),Hawk.get(Contants.JEANS,""),Hawk.get(Contants.WAIST,""),Hawk.get(Contants.PANTS,""),Hawk.get(Contants.SHIRT,""),
                Hawk.get(Contants.SHOES,""),Hawk.get(Contants.UNDERWEAR,""),Hawk.get(Contants.EXTRAINFO,""),Hawk.get(Contants.ISTALINK,""),Hawk.get(Contants.INSTAIMG,""),Hawk.get(Contants.FIMG,""),Hawk.get(Contants.FNAME,""),Hawk.get(Contants.INSTANAME,""),Hawk.get(Contants.USER_PROFILELINK,""));




    }

    @OnClick(R.id.button3)
    public void movepreviousfragment()
    {

        switch (viewPager.getCurrentItem())
        {
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

        Toast.makeText(getApplicationContext(),"done",Toast.LENGTH_SHORT).show();

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


        if(response!=null) {

                Intent intent= new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
                progressDialog.hide();

            } else {

                Toast.makeText(getApplicationContext(),"Failed",Toast.LENGTH_LONG).show();
                progressDialog.hide();


            }

    }

    @Override
    public void getfailerresponse(Throwable throwable) {

    }
}