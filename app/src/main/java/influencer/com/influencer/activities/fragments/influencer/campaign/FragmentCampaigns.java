package influencer.com.influencer.activities.fragments.influencer.campaign;


import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import influencer.com.influencer.R;
import influencer.com.influencer.activities.adapters.campaign.ActiveCampaignAdapter;
import influencer.com.influencer.activities.apiResponses.registerAPI.influencerAPI.campaign.activeCampaign.ActiveCampaign;
import influencer.com.influencer.activities.apiResponses.registerAPI.influencerAPI.campaign.activeCampaign.ActiveCampaign_;
import influencer.com.influencer.activities.constants.Contants;
import influencer.com.influencer.activities.restclient.RestClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentCampaigns extends Fragment {


    View view;

    @BindView(R.id.home_list)
    RecyclerView rchomeList;
    Dialog dialog;

    @BindView(R.id. imageView8)
    ImageView emptyimg;

    @BindView(R.id. textView8)
    TextView emptytext;



    private ArrayList<ActiveCampaign_> imgList = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_two, container, false);
        ButterKnife.bind(this,view);
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

        RestClient.GitApiInterface restclient=RestClient.getClient();
        restclient.activecampaign(Hawk.get(Contants.INFLUENCERID,"")).enqueue(new Callback<ActiveCampaign>() {
            @Override
            public void onResponse(Call<ActiveCampaign> call, Response<ActiveCampaign> response) {


                dialog.dismiss();

               if ( response.body().getActiveCampaign().size()==0)
               {
                   emptyimg.setVisibility(View.VISIBLE);
                   emptytext.setVisibility(View.VISIBLE);
                   rchomeList.setVisibility(View.GONE);
               }
                ActiveCampaignAdapter recadapter= new ActiveCampaignAdapter(response.body().getActiveCampaign());
                rchomeList.setLayoutManager(new LinearLayoutManager(getContext()));
                rchomeList.setItemAnimator(new DefaultItemAnimator());
                rchomeList.setAdapter(recadapter);



            }

            @Override
            public void onFailure(Call<ActiveCampaign> call, Throwable t) {
                Toast.makeText(getActivity(),t.toString(),Toast.LENGTH_SHORT).show();
            }
        });

    }


}
