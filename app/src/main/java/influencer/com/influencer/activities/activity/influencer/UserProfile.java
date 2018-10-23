package influencer.com.influencer.activities.activity.influencer;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.orhanobut.hawk.Hawk;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import influencer.com.influencer.R;
import influencer.com.influencer.activities.constants.Contants;

public class UserProfile extends AppCompatActivity {

    @BindView(R.id.profile_image)
    CircleImageView userprofile;


    @BindView(R.id.textView8)
    TextView name;

    @BindView(R.id.imageView9)
    ImageView backbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        ButterKnife.bind(this);

        if(Hawk.get(Contants.PROGILEIMG)!=null)
        {
            Picasso.with(UserProfile.this).load(Hawk.get(Contants.PROGILEIMG,"")).into(userprofile);
        }
        if (Hawk.get(Contants.PROFILENAME)!=null)
        {
            name.setText(Hawk.get(Contants.PROFILENAME,""));
        }

    }


    @OnClick(R.id.imageView9)
    public void backbtnclick()
    {
        finish();
    }

    @OnClick(R.id.textView15)
    public void editprofile()
    {
        startActivity(new Intent(UserProfile.this,EditProfile.class));
    }

    @OnClick(R.id.textView16)
    public void openchangepwd()
    {
        startActivity(new Intent(UserProfile.this,ChangePassword.class));
    }


}
