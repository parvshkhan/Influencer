package influencer.com.influencer.activities.activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.igalata.bubblepicker.BubblePickerListener;
import com.igalata.bubblepicker.BubbleSize;
import com.igalata.bubblepicker.model.PickerItem;
import com.igalata.bubblepicker.rendering.BubblePicker;
import com.orhanobut.hawk.Hawk;

import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import influencer.com.influencer.R;
import influencer.com.influencer.activities.constants.Contants;

public class ActivitySelectIntrest extends AppCompatActivity {
    BubblePicker bubblePicker;
    @BindView (R.id.interestnext)
    Button interestnextbtn;
    String items;



    String[] name={"Food", "Food", "Beauty", "Beauty", "Lifestyle", "Lifestyle", "Baby", "Baby", "Intertainment", "Intertainment", "Interior", "Health", "Adventure", "Sports", "Travel"};


    int[] colors = {
            android.graphics.Color.parseColor("#043c75"),
            android.graphics.Color.parseColor("#043c75"),
            android.graphics.Color.parseColor("#043c75"),
            android.graphics.Color.parseColor("#043c75"),
            android.graphics.Color.parseColor("#043c75"),
            android.graphics.Color.parseColor("#043c75"),
            android.graphics.Color.parseColor("#043c75"),
            android.graphics.Color.parseColor("#043c75"),
            android.graphics.Color.parseColor("#043c75"),
            android.graphics.Color.parseColor("#043c75"),
            android.graphics.Color.parseColor("#043c75"),
            android.graphics.Color.parseColor("#043c75"),
            android.graphics.Color.parseColor("#043c75"),
            android.graphics.Color.parseColor("#043c75"),
            android.graphics.Color.parseColor("#043c75")
    };

    int[] images = {R.drawable.flower, R.drawable.flower, R.drawable.flower, R.drawable.flower, R.drawable.flower, R.drawable.flower, R.drawable.flower, R.drawable.flower, R.drawable.flower, R.drawable.flower, R.drawable.flower, R.drawable.flower, R.drawable.flower, R.drawable.flower, R.drawable.flower};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_intrest);

        ButterKnife.bind(this);



        bubblePicker = (BubblePicker) findViewById(R.id.picker);
        bubblePicker.setBubbleSize(BubbleSize.LARGE);
        ArrayList<PickerItem> listitems =  new ArrayList<>();
        for (int i=0;i<name.length;i++){
//            PickerItem item = new PickerItem(name[i],null, true , R.drawable.flower);
            PickerItem item = new PickerItem(name[i],colors[i], android.graphics.Color.WHITE, getDrawable(images[i]));

            listitems.add(item);
//            Drawable drawable = this.getResources().getDrawable(R.drawable.yourDrawableID);
        }

        bubblePicker.setItems(listitems);


        bubblePicker.setListener(new BubblePickerListener() {
            @Override
            public void onBubbleSelected(@NotNull PickerItem pickerItem) {

                items=String.valueOf(pickerItem.getTitle());
                Hawk.put(Contants.ITEMS,items);
            }

            @Override
            public void onBubbleDeselected(@NotNull PickerItem pickerItem) {
//                Toast.makeText(ActivitySelectIntrest.this,pickerItem.getTitle()+"Removed from Your Interest", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick(R.id.interestnext)
    public void openmainActivity()
    {
        Intent intent=new Intent(getApplicationContext(),ActivityAbout.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fadein, R.anim.slide_to_right);
        finish();
    }



}