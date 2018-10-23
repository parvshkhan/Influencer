package influencer.com.influencer.activities.activity.influencer;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import influencer.com.influencer.R;

import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jaygoo.widget.RangeSeekBar;
import com.orhanobut.hawk.Hawk;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import influencer.com.influencer.activities.apiResponses.registerAPI.influencerAPI.campaign.searchCampaign.ApplyCampaignApi;
import influencer.com.influencer.activities.constants.Contants;
import influencer.com.influencer.activities.restclient.RestClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityPost extends AppCompatActivity {
    //@BindView(R.id.toolbar)
//
    @BindView(R.id.textView9)
    TextView price;
    @BindView(R.id.imageView13)
    ImageView img;
    @BindView(R.id.textView28)
    TextView title;

    @BindView(R.id.textView30)
    TextView detail;
    @BindView(R.id.circleImageView)
    CircleImageView logo;

    @BindView(R.id.textView26)
    TextView name;

    @BindView(R.id.seekBar)
    RangeSeekBar genderratio;

    @BindView(R.id.textView56)
    TextView edvalue;
    @BindView(R.id.textView57)
    TextView edvalue1;


    @BindView(R.id.rangeBar)
    RangeSeekBar agerange;

    @BindView(R.id.button5)
    Button button;

    @BindView(R.id.button7)
    Button btnclose;

    @BindView(R.id.editText3)
    EditText etcomment;

    Dialog dialog;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        ButterKnife.bind(this);
        genderratio.setEnabled(false);
        agerange.setEnabled(false);
        checkfragment();

edvalue.setText(String.valueOf(getIntent().getIntExtra("ratio",0)));
        genderratio.setValue(getIntent().getIntExtra("ratio",0));
        Picasso.with(ActivityPost.this).load(getIntent().getStringExtra("img")).into(img);
        price.setText("\u20ac"+getIntent().getIntExtra("price",0));
        detail.setText(getIntent().getStringExtra("detail"));
        title.setText(getIntent().getStringExtra("title"));
        name.setText(getIntent().getStringExtra("name"));
        agerange.setValue(getIntent().getIntExtra("agefrom",0),getIntent().getIntExtra("ageto",0));
        Picasso.with(ActivityPost.this).load(getIntent().getStringExtra("logo")).into(logo);

    }



    private void checkfragment() {
        if (getIntent().getStringExtra("view")!=null)
        {

            if (getIntent().getStringExtra("view").equals("Invited"))
            {
                button.setText("Approved");
                btnclose.setText("Rejected");
                etcomment.setVisibility(View.GONE);
            }
            else if (getIntent().getStringExtra("view").equals("Active"))
            {
                button.setVisibility(View.GONE);
                btnclose.setVisibility(View.GONE);
                etcomment.setVisibility(View.GONE);
            }
        }
    }



    @OnClick(R.id.backhome)
    public void backicon()
    {
        finish();
    }

    @OnClick(R.id.button5)
    public void applybtnclick()
    {

        if (getIntent().getStringExtra("view").equals("list"))
        {
            String cmt=etcomment.getText().toString();
            if (cmt.isEmpty())
            {
                etcomment.setError("Field is required");
            }
            else
            {

                dialog = new Dialog(ActivityPost.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.custom_dialog_progress);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.setCancelable(false);
                dialog.show();

                RestClient.GitApiInterface restClient = RestClient.getClient();
                restClient.applycampaign(Hawk.get(Contants.INFLUENCERID,""),getIntent().getStringExtra("imgid"),cmt).enqueue(new Callback<ApplyCampaignApi>() {
                    @Override
                    public void onResponse(Call<ApplyCampaignApi> call, Response<ApplyCampaignApi> response) {
                        dialog.dismiss();
                        finish();
                        Toast.makeText(getApplicationContext(),response.body().getMessage(),Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<ApplyCampaignApi> call, Throwable t) {
                        dialog.dismiss();
                        Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_SHORT).show();

                    }
                });

            }
        }
        else if (getIntent().getStringExtra("view").equals("Active"))
        {
            Toast.makeText(getApplicationContext(),"Active",Toast.LENGTH_SHORT).show();
        }

    }

}
