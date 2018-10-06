package influencer.com.influencer.activities.fragments.influencer;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.orhanobut.hawk.Hawk;

import javax.xml.validation.Validator;

import butterknife.BindView;
import butterknife.ButterKnife;
import influencer.com.influencer.R;
import influencer.com.influencer.activities.constants.Contants;


public class FragmentContactShipijng extends Fragment{

    @BindView(R.id.login_password2)
    EditText etfname;
    @BindView(R.id.login_password3)
    EditText etlname;
    @BindView(R.id.login_password)
    EditText etaddress;
    @BindView(R.id.login_password5)
    EditText etcity;
    @BindView(R.id.login_password4)
    EditText etpostalcode;
    @BindView(R.id.spinner)
    Spinner selectcountry;
    Validator validator;
    View view;







    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_fragment_contact_shipijng, container, false);
        ButterKnife.bind(this, view);
        return view;


    }

    public boolean clickevent() {

        boolean isformcomplete=false;

        return checkvalidation(isformcomplete);
    }

    private boolean checkvalidation(boolean isformcomplete) {
        String fname=etfname.getText().toString();
        String lname=etlname.getText().toString();
        String address=etaddress.getText().toString();
        String city=etcity.getText().toString();
        String postalcode=etpostalcode.getText().toString();
        String country=selectcountry.getSelectedItem().toString();

        if(fname.equals(""))
            etfname.setError("Can't Empty");
        else if (lname.equals(""))
            etlname.setError("Can't Empty");
        else if (address.equals(""))
            etaddress.setError("Can't Empty");
        else if (city.equals(""))
            etcity.setError("Can't Empty");
        else if (postalcode.equals(""))
            etpostalcode.setError("Can't Empty");
        else if (country.equals("Select Country"))
            Toast.makeText(getContext(),"Please Select Country",Toast.LENGTH_SHORT).show();
        else
            isformcomplete=true;
        Hawk.put(Contants.FNAME,fname);
        Hawk.put(Contants.LNAME,lname);
        Hawk.put(Contants.ADDRESS,address);
        Hawk.put(Contants.CIRY,city);
        Hawk.put(Contants.POSTALCODE,postalcode);
        Hawk.put(Contants.COUNTRY,country);
        return isformcomplete;
    }
}
