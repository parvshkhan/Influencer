package influencer.com.influencer.activities;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;

import butterknife.ButterKnife;
import influencer.com.influencer.R;
import influencer.com.influencer.activities.adapters.ViewPagerAdapter;
import influencer.com.influencer.activities.fragments.FragmentFive;
import influencer.com.influencer.activities.fragments.FragmentFour;
import influencer.com.influencer.activities.fragments.FragmentOne;
import influencer.com.influencer.activities.fragments.FragmentThree;
import influencer.com.influencer.activities.fragments.FragmentTwo;

public class ActivityProfileMain extends AppCompatActivity {




    private SearchView searchView;

    Button influencerbtn;


    BottomNavigationView bottomNavigationView;

    private ViewPager viewPager;

    FragmentOne fragmentOne ;
    FragmentTwo fragmentTwo;
    FragmentThree fragmentThree;
    FragmentFour fragmentFour;
    FragmentFive fragmentFive;

    MenuItem prevMenuItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_main);
        ButterKnife.bind(this);
        findid();

    }



    private void findid() {


        influencerbtn = findViewById(R.id.influencerbtn);

        bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation);


        viewPager = (ViewPager) findViewById(R.id.viewpager);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.tab_one:
                                viewPager.setCurrentItem(0);
                                break;
                            case R.id.tab_two:
                                viewPager.setCurrentItem(1);
                                break;
                            case R.id.tab_three:
                                viewPager.setCurrentItem(2);
                                break;

                            case R.id.tab_four:
                                viewPager.setCurrentItem(3);
                                break;

                            case R.id.tab_five:
                                viewPager.setCurrentItem(4);
                                break;

                        }
                        return false;
                    }
                });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (prevMenuItem != null) {
                    prevMenuItem.setChecked(false);
                }
                else
                {
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                Log.d("page", "onPageSelected: "+position);
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
                prevMenuItem = bottomNavigationView.getMenu().getItem(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });



        setupViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        fragmentOne=new FragmentOne();
        fragmentTwo=new FragmentTwo();
        fragmentThree=new FragmentThree();
        fragmentFour=new FragmentFour();
        fragmentFive=new FragmentFive();

        adapter.addFragment(fragmentOne);
        adapter.addFragment(fragmentTwo);
        adapter.addFragment(fragmentThree);
        adapter.addFragment(fragmentFour);
        adapter.addFragment(fragmentFive);
        viewPager.setAdapter(adapter);
    }

//    private void findid() {
//        searchView = findViewById(R.id.searchView);
//
//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//
//                System.out.println("ActivityProfileMain.onQueryTextSubmit " + query);
//
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//
//                System.out.println("ActivityProfileMain.onQueryTextSubmit " + newText);
//                return false;
//            }
//        });
//    }

}
