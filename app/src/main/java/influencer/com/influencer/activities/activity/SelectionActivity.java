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
        finish();
        Intent intent = new Intent(getApplicationContext(), ActivityLogin.class);
        startActivity(intent);

    }






}
