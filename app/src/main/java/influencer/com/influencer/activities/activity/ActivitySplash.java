package influencer.com.influencer.activities.activity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.login.LoginManager;
import com.orhanobut.hawk.Hawk;
import com.vansuita.pickimage.bundle.PickSetup;


import influencer.com.influencer.R;
import influencer.com.influencer.activities.activity.influencer.ActivityLogin;
import influencer.com.influencer.activities.activity.influencer.ActivitySelectIntrest;
import influencer.com.influencer.activities.activity.influencer.Dashboard;
import influencer.com.influencer.activities.activity.influencer.InfluencerPlans;
import influencer.com.influencer.activities.apiResponses.registerAPI.influencerDashboardAPI.DashBoardApi;
import influencer.com.influencer.activities.callback.ICallback;
import influencer.com.influencer.activities.constants.Contants;
import influencer.com.influencer.activities.retrofit.RetrofitUtil;


public class ActivitySplash extends AppCompatActivity implements ICallback {

    LoginManager loginManager;
    PackageInfo info;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hidingTheStatusBar();
        setContentView(R.layout.activity_splash);



        Thread thread=new Thread()
        {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);

                    if (Hawk.get(String.valueOf(Contants.INFLUENCERID))!=null)
                    {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                callapi(String .valueOf(Hawk.get(Contants.INFLUENCERID)));
                            }
                        });

                    }
                    else {
                        Intent intent = new Intent(ActivitySplash.this, SelectionActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.fadein, R.anim.slide_to_right);
                        finish();
                    }


                } catch (InterruptedException e) {

                    e.printStackTrace( );
                }
                super.run( );
            }


        };
        thread.start();


    }

    private void callapi(String  id) {

        RetrofitUtil retrofitUtil=new RetrofitUtil(ActivitySplash.this);
        retrofitUtil.dashboardresponse(id);



    }

    private void hidingTheStatusBar() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


    }



public boolean isFbLogin()
{
    AccessToken accessToken = AccessToken.getCurrentAccessToken();
    return accessToken != null && !accessToken.isExpired();
}
public boolean isCustomLogin()
{

   return false;
}
boolean isInstaLogin()
{


   return false;
}

    @Override
    public void getresponse(Object response) {
        if (response==null)
        {
         finish();
         Toast.makeText(ActivitySplash.this,"dsfsdf",Toast.LENGTH_SHORT).show();
        }

        if (response instanceof DashBoardApi) {

           if(!((DashBoardApi) response).getSuccess())
           {
               if (((DashBoardApi) response).getStatus().equals(3)) {
                   Intent intent = new Intent(getApplicationContext(), WaitingPage.class);
                   startActivity(intent);
                   finish();
               } else if (((DashBoardApi) response).getStatus().equals(2)) {
                   Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                   startActivity(intent);
                   finish();
               } else if (((DashBoardApi) response).getStatus().equals(4)) {
                   Intent intent = new Intent(getApplicationContext(), InfluencerPlans.class);
                   startActivity(intent);
                   finish();
               }else if (((DashBoardApi) response).getStatus().equals(1)) {
                   Intent intent = new Intent(getApplicationContext(), ActivitySelectIntrest.class);
                   startActivity(intent);
                   finish();
               }
           }
           else if (((DashBoardApi) response).getSuccess()) {
               Intent intent = new Intent(getApplicationContext(), Dashboard.class);
               startActivity(intent);
               finish();
           }

            else
                Toast.makeText(getApplicationContext(), ((DashBoardApi) response).getMessage(), Toast.LENGTH_LONG).show();
        }


    }

    @Override
    public void getfailerresponse(Throwable throwable) {

    }
}
