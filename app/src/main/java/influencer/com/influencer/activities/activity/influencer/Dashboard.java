package influencer.com.influencer.activities.activity.influencer;



import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.hawk.Hawk;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import influencer.com.influencer.R;
import influencer.com.influencer.activities.constants.Contants;
import influencer.com.influencer.activities.fragments.influencer.campaign.FragmentCampaigns;
import influencer.com.influencer.activities.fragments.influencer.campaign.FragmentListing;
import influencer.com.influencer.activities.fragments.influencer.campaign.Fragment_PreviousCampaign;
import influencer.com.influencer.activities.fragments.influencer.campaign.InvitedCampaigns;
import influencer.com.influencer.activities.fragments.influencer.campaign.RequestedCampaigns;

public class Dashboard extends AppCompatActivity {

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @BindView(R.id.drawer)
    DrawerLayout drawerLayout;


    @BindView(R.id.toolbar2)
    Toolbar toolbar;

    @BindView(R.id.searchitem)
    android.support.v7.widget.SearchView searchview;

    @BindView(R.id.circleImageView2)
    CircleImageView profileimg;

    @BindView(R.id.drawerlayout)
    ConstraintLayout drawlayout;

    @BindView(R.id. textView7)
    TextView showprofilename;


    @BindView(R.id. textView6)
    TextView previouscampaign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ButterKnife.bind(this);
        searchview.setQueryHint("Search..");
        toolbarsetup();
        showprofiledetail();

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction= fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container, new FragmentListing());
        fragmentTransaction.commit();

    }

    private void showprofiledetail() {
        Picasso.with(Dashboard.this).load(Hawk.get(Contants.PROGILEIMG,"")).error(R.drawable.user).into(profileimg);
        showprofilename.setText(Hawk.get(Contants.PROFILENAME,""));
    }

    private void toolbarsetup() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InputMethodManager inputManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);

                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
                drawerLayout.openDrawer(Gravity.START);
            }
        });


    }


    @OnClick(R.id.textView83)
    public void opendashboard()
    {
        searchview.setVisibility(View.VISIBLE);
        drawerLayout.closeDrawers();
        fragmentTransaction= fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container,new FragmentListing()).commit();
    }


    @OnClick(R.id.textView86)
    public void openInvitedCampaign()
    {
        searchview.setVisibility(View.GONE);
        toolbar.setTitle("Invited Campaigns");
        drawerLayout.closeDrawers();
        fragmentTransaction= fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, new InvitedCampaigns());
        fragmentTransaction.commit();
    }

    @OnClick(R.id.textView85)
    public void openRequestedCampaign()
    {
        searchview.setVisibility(View.GONE);
        toolbar.setTitle("Requested Campaigns");
        drawerLayout.closeDrawers();
        fragmentTransaction= fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, new RequestedCampaigns());
        fragmentTransaction.commit();
    }


    @OnClick(R.id.textView84)
    public void opencomaign()
    {
        searchview.setVisibility(View.GONE);
        toolbar.setTitle("Active Campaign");
        drawerLayout.closeDrawers();
        fragmentTransaction= fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, new FragmentCampaigns());
        fragmentTransaction.commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @OnClick(R.id.searchitem)
    public void searchclick()
    {
        searchview.setIconified(false);
    }



    @OnClick(R.id.drawerlayout)
    public void drawlayouclick()
    {
    }



    @OnClick(R.id.textView6)
    public void previouscampaignclick()
    {
        searchview.setVisibility(View.GONE);
        toolbar.setTitle("Previous Campaign");
        drawerLayout.closeDrawers();
        fragmentTransaction= fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, new Fragment_PreviousCampaign());
        fragmentTransaction.commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id) {
            case R.id.item1:
                startActivity(new Intent(Dashboard.this, UserProfile.class));
                break;
            case R.id.item2:
                Toast.makeText(getApplicationContext(), id, Toast.LENGTH_LONG).show();
                break;
            case R.id.item3:
             Hawk.delete(Contants.INFLUENCERID);
             startActivity(new Intent(Dashboard.this,ActivityLogin.class));
             finish();
                break;
            default: return false;
        }
        return super.onOptionsItemSelected(item);
    }


}
