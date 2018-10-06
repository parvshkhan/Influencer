package influencer.com.influencer.activities.fragments.influencer;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.jaygoo.widget.OnRangeChangedListener;
import com.jaygoo.widget.RangeSeekBar;
import com.orhanobut.hawk.Hawk;

import org.w3c.dom.Text;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import influencer.com.influencer.R;
import influencer.com.influencer.activities.constants.Contants;


public class FragmentTarget extends Fragment {


    @BindView(R.id.rangeBar)
    RangeSeekBar seekBarBottom;

    @BindView(R.id.seekBar)
    RangeSeekBar genderratio;


    @BindView(R.id.textView56)
    TextView progressone;
    @BindView(R.id.textView57)
    TextView progresstwo;
    View view;
    int gnderratio;

    int left, right;
    ArrayList<String> value = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fragment_target, container, false);
        ButterKnife.bind(this, view);
        seakbarselected();
        rangseekbarproperties();
        return view;

    }

    private void rangseekbarproperties() {

        seekBarBottom.setIndicatorTextDecimalFormat("0");
        genderratio.setValue(50);
        seekBarBottom.setValue(20, 80);


    }

    private void seakbarselected() {

        genderratio.setOnRangeChangedListener(new OnRangeChangedListener() {
            @Override
            public void onRangeChanged(RangeSeekBar view, float leftValue, float rightValue, boolean isFromUser) {

                int value = Math.round(leftValue);
                progressone.setText(String.valueOf(value));
                progresstwo.setText(String.valueOf(100 - value));
                gnderratio = (int) leftValue;

                String value1 = String.valueOf(value);
                Hawk.put(Contants.RATIO, value1);
            }

            @Override
            public void onStartTrackingTouch(RangeSeekBar view, boolean isLeft) {

            }

            @Override
            public void onStopTrackingTouch(RangeSeekBar view, boolean isLeft) {

            }
        });


        //--=======-------------------------Buttom seek bar click-----------------------------------------------------------------


        seekBarBottom.setOnRangeChangedListener(new OnRangeChangedListener() {
            @Override
            public void onRangeChanged(RangeSeekBar view, float leftValue, float rightValue, boolean isFromUser) {
                left = (int) leftValue;
                right = (int) rightValue;

                value.add(String.valueOf(left));
                value.add(String.valueOf(right));
                Hawk.put(Contants.AGERANGE, left + "," + right);
            }

            @Override
            public void onStartTrackingTouch(RangeSeekBar view, boolean isLeft) {

            }
            @Override
            public void onStopTrackingTouch(RangeSeekBar view, boolean isLeft) {

            }
        });
    }
    public boolean clickevent() {
        Toast.makeText(getContext(), "gender Ratio:" + left+","+right, Toast.LENGTH_SHORT).show();
        return true;
    }
}
