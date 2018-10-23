package influencer.com.influencer.activities.fragments.influencer.campaign;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.hawk.Hawk;

import butterknife.BindView;
import butterknife.ButterKnife;
import influencer.com.influencer.R;
import influencer.com.influencer.activities.adapters.campaign.InvitedCampaignAdapter;
import influencer.com.influencer.activities.adapters.campaign.RequestedCampaignAdapter;
import influencer.com.influencer.activities.apiResponses.registerAPI.influencerAPI.campaign.invitedCampaign.InvitedCampaignAPI;
import influencer.com.influencer.activities.apiResponses.registerAPI.influencerAPI.campaign.requestedcampaign.RequestedAPI;
import influencer.com.influencer.activities.constants.Contants;
import influencer.com.influencer.activities.restclient.RestClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class InvitedCampaigns extends Fragment {
    @BindView(R.id.home_list)
    RecyclerView rchomeList;
    View view;
    Dialog dialog;

    @BindView(R.id. imageView8)
    ImageView emptyimg;


    @BindView(R.id. textView8)
    TextView emptytext;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_four, container, false);
        ButterKnife.bind ( this,view );
        dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custom_dialog_progress);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCancelable(false);
        dialog.show();
        callapi();

        return view;
    }

    private void callapi() {

        RestClient.GitApiInterface restClient = RestClient.getClient();
        restClient.invitedcampaign(Hawk.get(Contants.INFLUENCERID,"")).enqueue(new Callback<InvitedCampaignAPI>() {
            @Override
            public void onResponse(Call<InvitedCampaignAPI> call, Response<InvitedCampaignAPI> response) {
                dialog.dismiss();

                if ( response.body().getInvitedCampaign().size()==0)
                {
                    emptyimg.setVisibility(View.VISIBLE);
                    emptytext.setVisibility(View.VISIBLE);
                    rchomeList.setVisibility(View.GONE);
                }
                InvitedCampaignAdapter recadapter= new InvitedCampaignAdapter(response.body().getInvitedCampaign());
                rchomeList.setLayoutManager(new LinearLayoutManager(getContext()));
                rchomeList.setItemAnimator(new DefaultItemAnimator());
                rchomeList.setAdapter(recadapter);
            }
            @Override
            public void onFailure(Call<InvitedCampaignAPI> call, Throwable t) {

                Toast.makeText(getContext(),t.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });
    }


}
