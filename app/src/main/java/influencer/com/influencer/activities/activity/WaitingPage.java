package influencer.com.influencer.activities.activity;

import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.hawk.Hawk;
import com.squareup.picasso.Picasso;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import influencer.com.influencer.R;
import influencer.com.influencer.activities.activity.influencer.ActivityAbout;
import influencer.com.influencer.activities.activity.influencer.ActivityLogin;
import influencer.com.influencer.activities.activity.influencer.InfluencerPlans;
import influencer.com.influencer.activities.apiResponses.registerAPI.influencerDashboardAPI.DashBoardApi;
import influencer.com.influencer.activities.callback.ICallback;
import influencer.com.influencer.activities.constants.Contants;
import influencer.com.influencer.activities.retrofit.RetrofitUtil;

public class WaitingPage extends AppCompatActivity implements ICallback {



    @BindView(R.id.imageView41)
    CircleImageView profileimg;

    @BindView(R.id.textView81)
    TextView profilename;

    @BindView(R.id.imageView42)
    ImageView logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting_page);
        ButterKnife.bind(this);
        setdata();
        callTimer();
    }

    private void setdata() {
       if (Hawk.get(Contants.INSTAIMG)!=null)
       {
           Picasso.with(WaitingPage.this).load(Hawk.get(Contants.INSTAIMG,"")).error(R.drawable.user).into(profileimg);
       }
       if (Hawk.get(Contants.PROGILEIMG)!=null)
       {
           Picasso.with(WaitingPage.this).load(Hawk.get(Contants.PROGILEIMG,"")).error(R.drawable.user).into(profileimg);
       }

    }


    private void callTimer() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                RetrofitUtil retrofitUtil = new RetrofitUtil(WaitingPage.this);
                retrofitUtil.dashboardresponse( String.valueOf(Hawk.get(Contants.INFLUENCERID, "")));
            }
        }, 3000);
    }


    @Override
    public void getresponse(Object response) {

        if(response instanceof DashBoardApi)
        {

            if (((DashBoardApi) response).getStatus().equals(3)) {
                callTimer();

            }
            else if (((DashBoardApi) response).getStatus().equals(2))
            {

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();

            }
            else if (((DashBoardApi) response).getStatus().equals(4))
            {


                Intent intent = new Intent(getApplicationContext(), InfluencerPlans.class);
                startActivity(intent);
                finish();

            }
            else
            {
                Toast.makeText(getApplicationContext(),((DashBoardApi) response).getMessage(),Toast.LENGTH_LONG).show();
            }

        }




    }

    @Override
    public void getfailerresponse(Throwable throwable) {

    }

    @OnClick(R.id.imageView42)
    public void logoutbtnclick()
    {
        Hawk.delete(Contants.INFLUENCERID);
        startActivity(new Intent(WaitingPage.this,ActivityLogin.class));
        finish();
    }
}
