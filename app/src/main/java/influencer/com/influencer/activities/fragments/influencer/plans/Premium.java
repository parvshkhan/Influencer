package influencer.com.influencer.activities.fragments.influencer.plans;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import influencer.com.influencer.R;


public class Premium extends Fragment {

    @BindView(R.id.textView73)
    TextView price;
    View view;

    String euro = "\u20ac";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_premium2, container, false);

        ButterKnife.bind(this,view);
        price.setText("5"+euro);

        return view;
    }

}
