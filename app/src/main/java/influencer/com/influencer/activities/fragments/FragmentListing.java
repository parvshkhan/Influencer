package influencer.com.influencer.activities.fragments;

import android.app.Activity;
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
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import influencer.com.influencer.R;
import influencer.com.influencer.activities.adapters.Recadapter;
import influencer.com.influencer.activities.model.Recpojo;

public class FragmentListing extends Fragment {

    @BindView(R.id.searchView)
    SearchView search;
    InputMethodManager imm;


    @BindView(R.id.home_list)
    RecyclerView rchomeList;

    private List<Recpojo> imgList = new ArrayList<>();
    private View view;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
          view = inflater.inflate(R.layout.fragment_one, container, false);
        ButterKnife.bind(this,view);
        search.requestFocus();
        search.setFocusableInTouchMode(true);
//         imm = (InputMethodManager)getActivity().getSystemService(getActivity().INPUT_METHOD_SERVICE);


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

    @OnClick(R.id.searchView)
    public void searchclick()
    {


    }
}
