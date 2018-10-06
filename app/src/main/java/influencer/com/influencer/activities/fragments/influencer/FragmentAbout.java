package influencer.com.influencer.activities.fragments.influencer;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.orhanobut.hawk.Hawk;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.listeners.IPickCancel;
import com.vansuita.pickimage.listeners.IPickResult;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import influencer.com.influencer.R;
import influencer.com.influencer.activities.constants.Contants;


public class FragmentAbout extends Fragment {

    @BindView(R.id.loadimage)
    ImageButton addimage;
    @BindView(R.id.profileimg)
    CircleImageView profileimg;
    @BindView(R.id.editText4)
    EditText dateofbirth;
    Calendar myCalendar;

    @NotEmpty(message = "Cant Empaty")
    @BindView(R.id.editText5)
    EditText edname;

    @BindView(R.id.radioGroup)
    RadioGroup edgender;

    @BindView(R.id.spinner)
    Spinner selectlanguage;

    @BindView(R.id.editText3)
    EditText etdescribe;
    @BindView(R.id.male)
    RadioButton male;

    @BindView(R.id.female)
    RadioButton female;

    boolean isImageAttached;

    String imgpath;
    String dateob;


    AlertDialog.Builder dialogBuilder;
    AlertDialog alertDialog;
    View view;
    private static final int CAMERA_REQUEST = 1888;
    private static int RESULT_LOAD_IMAGE = 1;
    DatePickerDialog.OnDateSetListener date;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fragment_about, container, false);
        ButterKnife.bind(this, view);
        opencalender();
        return view;
    }

    private void opencalender() {

        myCalendar = Calendar.getInstance();

        date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {

                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };


    }

    @OnClick(R.id.editText4)
    public void edittextclick() {


        new DatePickerDialog(getContext(), date, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void updateLabel() {

        String myFormat = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        dateofbirth.setText(sdf.format(myCalendar.getTime()));

        dateob=String.valueOf(dateofbirth.getText().toString());

    }

    @OnClick(R.id.loadimage)
    public void loadimageclick() {
        opendialog();
    }


    private void opendialog() {


        PickImageDialog.build(new PickSetup())
                .setOnPickResult(new IPickResult() {
                    @Override
                    public void onPickResult(PickResult r) {

                        if (r.getError() == null) {
                            profileimg.setImageBitmap(r.getBitmap());
                            imgpath = String.valueOf(r.getPath());

//                            File file = new File(String.valueOf(Hawk.put(Contants.IMGLINK,imgpath )));
//
//                            RequestBody fbodyImage = RequestBody.create(MediaType.parse("image/*"), file);
                            isImageAttached = true;
                        } else {
                            Toast.makeText(getContext(), r.getError().getMessage(), Toast.LENGTH_LONG).show();
                            isImageAttached = false;
                        }
                    }
                })
                .setOnPickCancel(new IPickCancel() {
                    @Override
                    public void onCancelClick() {
                    }
                }).show(getActivity());


    }


    public boolean clickevent() {
        boolean isformcomplete = false;


        return checkvalidation(isformcomplete);

    }

    private boolean checkvalidation(boolean isformcomplete) {
        String gender = "";

        if (male.isChecked()) {
            gender = male.getText().toString();
        } else if (female.isChecked()) {
            gender = female.getText().toString();
        }
        String name = edname.getText().toString();
        String dob = dateofbirth.getText().toString();
        String describe = etdescribe.getText().toString();
        String language = selectlanguage.getSelectedItem().toString();

        if (gender.equals("")) {
            Toast.makeText(getContext(), "Please Select Gender", Toast.LENGTH_SHORT).show();
        } else if (name.equals(""))
            edname.setError("Can't Empty");
        else if (dob.equals(""))
            dateofbirth.setError("Choose Date");
        else if (describe.equals(""))
            etdescribe.setError("Field Required");
        else if (language.equals("Select Language"))
            Toast.makeText(getContext(), "Plese Select Language", Toast.LENGTH_SHORT).show();

        else if (!isImageAttached)
            Toast.makeText(getContext(), "Please Selece Image", Toast.LENGTH_SHORT).show();

        else
            isformcomplete = true;
        Hawk.put(Contants.NAME, name);
        Hawk.put(Contants.GENDER, gender);
        Hawk.put(Contants.DOB, dateob);
        Hawk.put(Contants.DESCRIBE, describe);
        Hawk.put(Contants.LANGUAGE, language);
        Hawk.put(Contants.IMGLINK, imgpath);


        return isformcomplete;


    }
}
