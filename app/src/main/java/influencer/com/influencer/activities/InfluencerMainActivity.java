package influencer.com.influencer.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import influencer.com.influencer.R;

/**
 * Created by android on 3/8/18.
 */

public class InfluencerMainActivity extends AppCompatActivity implements View.OnClickListener{

    Button influencerbtn;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        hidingTheStatusBar();

        setContentView(R.layout.activity_influencer_main);
        findid();
        influencerbtn.setOnClickListener(this);

    }






    private void hidingTheStatusBar() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


    }

    private void findid() {

        influencerbtn=findViewById(R.id.influencerbtn);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.influencerbtn:
                Intent intent=new Intent(getApplicationContext(),ActivityLoginInfluencer.class);
                startActivity(intent);
                break;
        }

    }
}
