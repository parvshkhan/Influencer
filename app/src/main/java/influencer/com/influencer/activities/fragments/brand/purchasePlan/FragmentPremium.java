package influencer.com.influencer.activities.fragments.brand.purchasePlan;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import influencer.com.influencer.R;


public class FragmentPremium extends Fragment {

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
        view = inflater.inflate(R.layout.fragment_premium, container, false);

        ButterKnife.bind(this,view);
        price.setText("500"+euro);

        cuttingtext();
        return view;
    }

    private void cuttingtext() {


    }

}
