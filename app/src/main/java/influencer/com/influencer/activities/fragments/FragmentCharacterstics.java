package influencer.com.influencer.activities.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.jaygoo.widget.OnRangeChangedListener;
import com.jaygoo.widget.RangeSeekBar;
import com.orhanobut.hawk.Hawk;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import influencer.com.influencer.R;
import influencer.com.influencer.activities.constants.Contants;


public class FragmentCharacterstics extends Fragment {

    @BindView(R.id.seekBar2)
    RangeSeekBar seekBar;
    @BindView(R.id.textView60)
    TextView textView;

    @BindView(R.id.spinner3)
    Spinner jeanslength;

    @BindView(R.id.spinner2)
    Spinner selectwaist;

    @BindView(R.id.spinner8)
    Spinner pentsize;

    @BindView(R.id.spinner7)
    Spinner shirsize;

    @BindView(R.id.spinner10)
    Spinner shoesize;

    @BindView(R.id.spinner9)
    Spinner usize;

    @BindView(R.id.editText)
    EditText etextrainfo;

    float value;




    View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_fragment_characterstics, container, false);

        ButterKnife.bind(this,view);
        seekBar.setValue(160.5f);
        textView.setText("160.5");
        seakbarselected();

        return  view;
    }

    private void seakbarselected() {



        seekBar.setOnRangeChangedListener(new OnRangeChangedListener() {
            @Override
            public void onRangeChanged(RangeSeekBar view, float leftValue, float rightValue, boolean isFromUser) {
                DecimalFormat df = new DecimalFormat("0.0");

                textView.setText(String.valueOf(df.format(leftValue)));
                value=leftValue;
                Hawk.put(Contants.HEIGHT,String.valueOf(value));


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
        boolean isformcomplete=false;

        checkvalidation(isformcomplete);



        return checkvalidation(isformcomplete);
    }

    private boolean checkvalidation(boolean isformcomplete) {

        final  String length=jeanslength.getSelectedItem().toString();
        String waist=selectwaist.getSelectedItem().toString();
        String psize=pentsize.getSelectedItem().toString();
        String ssize=shirsize.getSelectedItem().toString();
        String shsize=shoesize.getSelectedItem().toString();
        String underwearsize=usize.getSelectedItem().toString();
        String extrainfo=etextrainfo.getText().toString();

        if(length.equals("Jeans length"))
        Toast.makeText(getContext(),"select Jeans length",Toast.LENGTH_SHORT).show();
       else if(waist.equals("Select Waist"))
            Toast.makeText(getContext(),"select Waist",Toast.LENGTH_SHORT).show();
        else if(psize.equals("Pants Size"))
            Toast.makeText(getContext(),"select pant size",Toast.LENGTH_SHORT).show();
        else if(ssize.equals("Shirt Size"))
            Toast.makeText(getContext(),"select Shirt size",Toast.LENGTH_SHORT).show();
        else if(shsize.equals("Shoe size"))
            Toast.makeText(getContext(),"select Shoe size",Toast.LENGTH_SHORT).show();
        else if(underwearsize.equals("Underwear Size"))
            Toast.makeText(getContext(),"select Underwear size",Toast.LENGTH_SHORT).show();

        else
            isformcomplete = true;
            Hawk.put(Contants.JEANS, length);
            Hawk.put(Contants.WAIST, waist);
            Hawk.put(Contants.PANTS, psize);
            Hawk.put(Contants.SHIRT, ssize);
            Hawk.put(Contants.SHOES, shsize);
            Hawk.put(Contants.UNDERWEAR, underwearsize);
            Hawk.put(Contants.EXTRAINFO,extrainfo);







        return isformcomplete;
    }
}
