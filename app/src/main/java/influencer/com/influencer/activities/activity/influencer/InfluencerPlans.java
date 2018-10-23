package influencer.com.influencer.activities.activity.influencer;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

import com.orhanobut.hawk.Hawk;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import influencer.com.influencer.R;
import influencer.com.influencer.activities.activity.MainActivity;
import influencer.com.influencer.activities.activity.WaitingPage;
import influencer.com.influencer.activities.adapters.PostPagerAdapter;
import influencer.com.influencer.activities.apiResponses.registerAPI.influencerAPI.PlanApi;
import influencer.com.influencer.activities.apiResponses.registerAPI.influencerDashboardAPI.DashBoardApi;
import influencer.com.influencer.activities.callback.ICallback;
import influencer.com.influencer.activities.constants.Contants;
import influencer.com.influencer.activities.fragments.brand.purchasePlan.FragmentPremium;
import influencer.com.influencer.activities.fragments.brand.purchasePlan.FragmentProfessional;
import influencer.com.influencer.activities.fragments.brand.purchasePlan.FragmentStarter;
import influencer.com.influencer.activities.fragments.influencer.plans.FreePlan;
import influencer.com.influencer.activities.fragments.influencer.plans.Premium;
import influencer.com.influencer.activities.retrofit.RetrofitUtil;

public class InfluencerPlans extends AppCompatActivity implements ICallback {

    @BindView(R.id.psttabs)
    TabLayout tabLayout;
    @BindView(R.id.pstviewpager)
    ViewPager viewPager;
    Dialog dialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_influencer_plans);

        ButterKnife.bind(this);

        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#ffffff"));
        tabLayout.setSelectedTabIndicatorHeight((int) (2 * getResources().getDisplayMetrics().density));
        tabLayout.setTabTextColors(Color.parseColor("#727272"), Color.parseColor("#ffffff"));
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
    }


    private void setupViewPager(ViewPager viewPager) {
        PostPagerAdapter adapter=new PostPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(new FreePlan(), "Free");
        adapter.addFragment(new Premium(), "Premium");
        viewPager.setAdapter(adapter);
    }






    @OnClick(R.id.button6)
    public void opennextfragment() {
        switch (viewPager.getCurrentItem()) {
            case 0:
                String plan="1";

                Toast.makeText(getApplicationContext(),plan,Toast.LENGTH_SHORT).show();
                caldashboardapi(plan);
                break;

            case 1:

                break;
            default:
                Toast.makeText(this, "end", Toast.LENGTH_SHORT).show();
                break;
        }

    }

    private void caldashboardapi(String plan) {

        dialog = new Dialog(InfluencerPlans.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custom_dialog_progress);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCancelable(false);
        dialog.show();

        RetrofitUtil retrofitUtil=new RetrofitUtil(InfluencerPlans.this);
        retrofitUtil.influencerplan(plan,Hawk.get(Contants.INFLUENCERID,""));
    }

    @Override
    public void getresponse(Object response) {
        if (response instanceof PlanApi)
        {
            if (((PlanApi) response).getSuccess())
            {

                dialog.dismiss();
                RetrofitUtil retrofitUtil=new RetrofitUtil(InfluencerPlans.this);
                retrofitUtil.dashboardresponse(Hawk.get(Contants.INFLUENCERID,""));
            }
            else
            {
                Toast.makeText(getApplicationContext(),((PlanApi) response).getMessage(),Toast.LENGTH_SHORT).show();
            }
        }
        if (response instanceof DashBoardApi) {

            if (!((DashBoardApi) response).getSuccess()) {
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

                } else if (((DashBoardApi) response).getStatus().equals(1)) {

                    dialog.dismiss();
                    Intent intent = new Intent(getApplicationContext(), ActivitySelectIntrest.class);
                    startActivity(intent);
                    finish();
                }
            }
            if (((DashBoardApi) response).getSuccess()) {
                dialog.dismiss();
                Hawk.put("tname", ((DashBoardApi) response).getUserDetail().getName());
                Hawk.put("tdob", ((DashBoardApi) response).getUserDetail().getDob());
                Hawk.put("tlanguage", ((DashBoardApi) response).getUserDetail().getLanguage());
                Hawk.put("tratio", ((DashBoardApi) response).getUserDetail().getGenderratio());
                Hawk.put("tagefrom", ((DashBoardApi) response).getUserDetail().getAgeFrom());
                Hawk.put("tageto", ((DashBoardApi) response).getUserDetail().getAgeTo());
                Hawk.put("tinterest", ((DashBoardApi) response).getUserDetail().getInterest());
                startActivity(new Intent(getApplicationContext(), Dashboard.class));
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
