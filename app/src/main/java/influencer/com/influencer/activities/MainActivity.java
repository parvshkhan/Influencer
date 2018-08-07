package influencer.com.influencer.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;

import influencer.com.influencer.R;

public class MainActivity extends AppCompatActivity {


    private SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        searchView = findViewById(R.id.searchView);
        searchView.setQueryHint("Search..");


    }
}
