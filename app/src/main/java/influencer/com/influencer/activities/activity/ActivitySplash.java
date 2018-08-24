package influencer.com.influencer.activities.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;

import com.facebook.AccessToken;
import com.facebook.login.LoginManager;
import com.orhanobut.hawk.Hawk;
import com.steelkiwi.instagramhelper.InstagramHelper;

import influencer.com.influencer.R;
import influencer.com.influencer.activities.constants.Contants;



public class ActivitySplash extends AppCompatActivity {

    LoginManager loginManager;

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

                    Log.i("LoginType", "isCustom-->>"+isCustomLogin());
                    Log.i("LoginType", "isFbLogin-->>"+isFbLogin());
                    Log.i("LoginType", "isInstaLogin-->>"+isInstaLogin());
                    if(isCustomLogin() || isFbLogin() || isInstaLogin())
                    {
                        if(Hawk.get(Contants.USER_EMAIL,null)!=null)
                        {
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));

                            finish();
                            return;
                        }
                        LoginManager.getInstance().logOut();
                        Hawk.delete(Contants.USER_EMAIL);

                    }


                        Intent intent=new Intent(ActivitySplash.this,SelectionActivity.class);
                        startActivity(intent);
                        finish();



                } catch (InterruptedException e) {

                    e.printStackTrace( );
                }
                super.run( );
            }
        };
        thread.start();


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
}
