package influencer.com.influencer.activities.fragments;

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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import influencer.com.influencer.R;
import influencer.com.influencer.activities.adapters.Recadapter;
import influencer.com.influencer.activities.model.Recpojo;

public class FragmentOne extends Fragment {


    @BindView(R.id.home_list)
    RecyclerView rchomeList;

    private List<Recpojo> imgList = new ArrayList<>();







    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View  view = inflater.inflate(R.layout.fragment_one, container, false);
        ButterKnife.bind(this,view);

        imgList.add(new Recpojo("Westpac","The Sun-harald City surf presented by westpac ","efefefe"));
        imgList.add(new Recpojo("Westpac","The Sun-harald City surf presented by westpac ","efefefe"));
        imgList.add(new Recpojo("Westpac","The Sun-harald City surf presented by westpac ","efefefe"));
        imgList.add(new Recpojo("Westpac","The Sun-harald City surf presented by westpac ","efefefe"));
        imgList.add(new Recpojo("Westpac","The Sun-harald City surf presented by westpac ","efefefe"));
        imgList.add(new Recpojo("Westpac","The Sun-harald City surf presented by westpac ","efefefe"));
        imgList.add(new Recpojo("Westpac","The Sun-harald City surf presented by westpac ","efefefe"));
        imgList.add(new Recpojo("Westpac","The Sun-harald City surf presented by westpac ","efefefe"));
        imgList.add(new Recpojo("Westpac","The Sun-harald City surf presented by westpac ","efefefe"));

        Recadapter recadapter= new Recadapter(imgList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rchomeList.setLayoutManager(linearLayoutManager);
        rchomeList.setItemAnimator(new DefaultItemAnimator());
        rchomeList.setAdapter(recadapter);



        return view;

    }
}
