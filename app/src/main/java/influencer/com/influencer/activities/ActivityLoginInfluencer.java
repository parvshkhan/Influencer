package influencer.com.influencer.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import influencer.com.influencer.R;

public class ActivityLoginInfluencer extends AppCompatActivity implements View.OnClickListener {
TextView move_register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        hidingTheStatusBar();

        setContentView(R.layout.activity_infuencer);
        findid();

        move_register.setOnClickListener(this);

    }

    private void hidingTheStatusBar() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


    }

    private void findid() {

        move_register=findViewById(R.id.register_move);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.register_move:
                Intent intent=new Intent(ActivityLoginInfluencer.this,ActivityRegisterInfluencer.class);
                startActivity(intent);
                finish();
        }



    }
}
