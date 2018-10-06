package influencer.com.influencer.activities.activity.influencer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.OnClick;
import influencer.com.influencer.R;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import influencer.com.influencer.activities.adapters.PostPagerAdapter;
import influencer.com.influencer.activities.fragments.FragmentBrief;
import influencer.com.influencer.activities.fragments.FragmentFacebook;
import influencer.com.influencer.activities.fragments.FragmentInstagram;

public class ActivityPost extends AppCompatActivity {
    //@BindView(R.id.toolbar)
//    private Toolbar toolbar;
    @BindView(R.id.psttabs)
    TabLayout tabLayout;
    @BindView(R.id.pstviewpager)
    ViewPager viewPager;

    @BindView(R.id.backhome)
    ImageView backbtn;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        ButterKnife.bind(this);

        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#ffffff"));
        tabLayout.setSelectedTabIndicatorHeight((int) (2 * getResources().getDisplayMetrics().density));
        tabLayout.setTabTextColors(Color.parseColor("#727272"), Color.parseColor("#ffffff"));
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        PostPagerAdapter adapter=new PostPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(new FragmentBrief(), "Brief");
        adapter.addFragment(new FragmentFacebook(), "Facebook");
        adapter.addFragment(new FragmentInstagram(), "Instagram");

        viewPager.setAdapter(adapter);



    }

    @OnClick(R.id.backhome)
    public void openhomeactivity()
    {
        finish();

    }

}
