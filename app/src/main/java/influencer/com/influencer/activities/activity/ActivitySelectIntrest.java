package influencer.com.influencer.activities.activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.igalata.bubblepicker.BubblePickerListener;
import com.igalata.bubblepicker.model.PickerItem;
import com.igalata.bubblepicker.rendering.BubblePicker;

import java.util.ArrayList;

import influencer.com.influencer.R;

public class ActivitySelectIntrest extends AppCompatActivity {
    PickerItem pickerItem;

    BubblePicker picker;
    String[] titles={"charan","shazeb","parvesh","prabh","ankit"};

    int[] image={R.drawable.flower,R.drawable.flower,R.drawable.flower,R.drawable.flower,R.drawable.flower};

//    int[] color={Color.parseColor("#0000" ),Color.parseColor("#0000"),Color.parseColor("#0000"),Color.parseColor("#0000"),Color.parseColor("#0000"),};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_intrest);

        picker=findViewById(R.id.picker);
        ArrayList<PickerItem> items=new ArrayList<>();

        for(int i=0;i<titles.length;i++)
        {
            pickerItem = new PickerItem (titles[i],getResources ().getDrawable (image[i]), true,getColor ( R.color.blue ));
            items.add(pickerItem);

        }
        picker.setItems ( items );
        picker.setListener ( new BubblePickerListener ( ) {
            @Override
            public void onBubbleSelected(PickerItem pickerItem) {

            }

            @Override
            public void onBubbleDeselected(PickerItem pickerItem) {

            }
        } );

    }

    @Override
    protected void onResume() {
        super.onResume();
        picker.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        picker.onPause();
    }
}
