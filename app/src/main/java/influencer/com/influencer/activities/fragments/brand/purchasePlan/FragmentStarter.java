package influencer.com.influencer.activities.fragments.brand.purchasePlan;


import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import influencer.com.influencer.R;


public class FragmentStarter extends Fragment {



    @BindView(R.id.textView77)
    TextView thiroption;

    @BindView(R.id.textView78)
    TextView forthoption;

    @BindView(R.id.textView79)
    TextView fifthoption;

    @BindView(R.id.textView73)
    TextView price;
    View view;

    String euro = "\u20ac";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_starter, container, false);

        ButterKnife.bind(this,view);
        price.setText("200"+euro);

        cuttingtext();


        return view;
    }

    private void cuttingtext() {

        thiroption.setPaintFlags(thiroption.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        forthoption.setPaintFlags(forthoption.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        fifthoption.setPaintFlags(fifthoption.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

    }


}
