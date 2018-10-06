package influencer.com.influencer.activities.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import influencer.com.influencer.R;
import influencer.com.influencer.activities.activity.influencer.ActivityLogin;
import influencer.com.influencer.activities.activity.brand.BrandLogin;

/**
 * Created by android on 3/8/18.
 */

public class SelectionActivity extends AppCompatActivity {


    @BindView(R.id.influencerbtn)
    Button btInfluencer;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hidingTheStatusBar();
        setContentView(R.layout.activity_influencer_main);
        ButterKnife.bind(this);




    }

    private void hidingTheStatusBar() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);



    }




    @OnClick(R.id.influencerbtn)
    public void openLoginActivity()
    {

        Intent intent = new Intent(getApplicationContext(), ActivityLogin.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);

    }

    @OnClick(R.id.button2)
    public void openbrandactivity()
    {

        Intent intent = new Intent(getApplicationContext(), BrandLogin.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);


    }






}
