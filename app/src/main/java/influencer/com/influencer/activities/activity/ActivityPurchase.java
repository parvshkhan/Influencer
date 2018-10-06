package influencer.com.influencer.activities.activity;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import influencer.com.influencer.R;
import influencer.com.influencer.activities.adapters.PostPagerAdapter;
import influencer.com.influencer.activities.fragments.brand.purchasePlan.FragmentPremium;
import influencer.com.influencer.activities.fragments.brand.purchasePlan.FragmentProfessional;
import influencer.com.influencer.activities.fragments.brand.purchasePlan.FragmentStarter;

public class ActivityPurchase extends AppCompatActivity {

    @BindView(R.id.psttabs)
    TabLayout tabLayout;
    @BindView(R.id.pstviewpager)
    ViewPager viewPager;

    @BindView(R.id.backhome)
    ImageView backbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);

        ButterKnife.bind(this);

        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#ffffff"));
        tabLayout.setSelectedTabIndicatorHeight((int) (2 * getResources().getDisplayMetrics().density));
        tabLayout.setTabTextColors(Color.parseColor("#727272"), Color.parseColor("#ffffff"));
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        PostPagerAdapter adapter=new PostPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(new FragmentStarter(), "Starter");
        adapter.addFragment(new FragmentProfessional(), "Professional");
        adapter.addFragment(new FragmentPremium(), "Premium");
        viewPager.setAdapter(adapter);

    }


    @OnClick(R.id.backhome)
    public void openhomeactivity()
    {
        finish();

    }
}
