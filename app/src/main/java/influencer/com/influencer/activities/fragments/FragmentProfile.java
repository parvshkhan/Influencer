package influencer.com.influencer.activities.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.orhanobut.hawk.Hawk;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import influencer.com.influencer.R;
import influencer.com.influencer.activities.constants.Contants;

public class FragmentProfile extends Fragment {
    @BindView(R.id.profile_image)
    CircleImageView profileimage;
    @BindView(R.id.textView8)
    TextView name;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_four, container, false);
        ButterKnife.bind ( this,view );


        name.setText(Hawk.get(Contants.USER_EMAIL)+"");
        return view;
    }
}
