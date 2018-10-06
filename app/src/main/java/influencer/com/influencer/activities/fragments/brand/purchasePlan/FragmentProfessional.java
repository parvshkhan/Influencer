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


public class FragmentProfessional extends Fragment {

    @BindView(R.id.textView79)
    TextView forthoption;
    String euro = "\u20ac";

    @BindView(R.id.textView73)
    TextView price;

    View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_professional, container, false);


        ButterKnife.bind(this,view);
        price.setText("350"+euro);

        cuttingtext();
        return view;
    }

    private void cuttingtext() {

        forthoption.setPaintFlags(forthoption.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
    }
}
