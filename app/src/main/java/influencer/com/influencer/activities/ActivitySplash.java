package influencer.com.influencer.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import influencer.com.influencer.R;

/**
 * Created by android on 3/8/18.
 */

public class ActivitySplash extends AppCompatActivity {

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
                    Intent intent=new Intent(ActivitySplash.this,InfluencerMainActivity.class);
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
}
