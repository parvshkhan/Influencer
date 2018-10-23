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
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import influencer.com.influencer.R;
import influencer.com.influencer.activities.adapters.Recadapter;
import influencer.com.influencer.activities.apiResponses.registerAPI.influencerAPI.campaign.searchCampaign.Campaign;
import influencer.com.influencer.activities.apiResponses.registerAPI.influencerAPI.campaign.searchCampaign.CampaignAPI;
import influencer.com.influencer.activities.constants.Contants;
import influencer.com.influencer.activities.restclient.RestClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentListing extends Fragment implements android.support.v7.widget.SearchView.OnQueryTextListener {



    @BindView(R.id.home_list)
    RecyclerView rchomeList;

    private View view;
    Dialog dialog;

    @BindView(R.id. imageView8)
    ImageView emptyimg;

    @BindView(R.id. textView8)
    TextView emptytext;
android.support.v7.widget.SearchView searchView;

    private List<Campaign> campaignAPIList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_one, container, false);
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

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        searchView =    getActivity().findViewById(R.id.searchitem);
        searchView.setOnQueryTextListener(this);
    }

    private void callapi() {

        RestClient.GitApiInterface restClient = RestClient.getClient();
        restClient.campaignlist(Hawk.get(Contants.INFLUENCERID,"")).enqueue(new Callback<CampaignAPI>() {
            @Override
            public void onResponse(Call<CampaignAPI> call, Response<CampaignAPI> response) {
                dialog.dismiss();
                if ( response.body().getCampaign().size()==0)
                {
                    emptyimg.setVisibility(View.VISIBLE);
                    emptytext.setVisibility(View.VISIBLE);
                    rchomeList.setVisibility(View.GONE);
                }
                Recadapter recadapter= new Recadapter(response.body().getCampaign());
                rchomeList.setLayoutManager(new LinearLayoutManager(getContext()));
                rchomeList.setItemAnimator(new DefaultItemAnimator());
                rchomeList.setAdapter(recadapter);
                campaignAPIList = response.body().getCampaign();

            }
            @Override
            public void onFailure(Call<CampaignAPI> call, Throwable t) {
                Toast.makeText(getActivity(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }



    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        if (!newText.isEmpty()) {
            List<Campaign> tempList = new ArrayList<>();
            for(int i=0;i<campaignAPIList.size();i++)
            {
                if(campaignAPIList.get(i).getTitle().toLowerCase().contains(newText.toLowerCase()))
                {
                    tempList.add(campaignAPIList.get(i));
                }
            }

            if(tempList.size()>0) {
                rchomeList.setVisibility(View.VISIBLE);
                Recadapter recadapter = new Recadapter(tempList);
                rchomeList.setAdapter(recadapter);
                recadapter.notifyDataSetChanged();
            }
            else
            {
                rchomeList.setVisibility(View.GONE);
                emptyimg.setVisibility(View.VISIBLE);
                emptytext.setVisibility(View.VISIBLE);

            }
        }
        else
        {
            Recadapter recadapter= new Recadapter(campaignAPIList);
            rchomeList.setAdapter(recadapter);
        }
        return true;
    }

    @Override
    public void onResume() {
        super.onResume();

        callapi();
    }
}
