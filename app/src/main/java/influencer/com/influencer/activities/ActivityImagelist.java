package influencer.com.influencer.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import influencer.com.influencer.R;
import influencer.com.influencer.activities.pojoclass.Recpojo;
import influencer.com.influencer.activities.recAdapter.Recadapter;

public class ActivityImagelist extends AppCompatActivity {
    private List<Recpojo> imgList = new ArrayList<>();
    RecyclerView imgrecyclter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imglist);
        findid();
        setRecyclterview();

    }

    private void findid() {

        imgrecyclter=findViewById(R.id.imglist);


    }

    private void setRecyclterview() {

        Recadapter  recadapter= new Recadapter(imgList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        imgrecyclter.setLayoutManager(mLayoutManager);
        imgrecyclter.setItemAnimator(new DefaultItemAnimator());
        imgrecyclter.setAdapter(recadapter);




    }


}
