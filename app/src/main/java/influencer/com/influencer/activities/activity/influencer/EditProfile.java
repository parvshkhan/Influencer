package influencer.com.influencer.activities.activity.influencer;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.hawk.Hawk;
import com.squareup.picasso.Picasso;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.listeners.IPickCancel;
import com.vansuita.pickimage.listeners.IPickResult;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import influencer.com.influencer.R;
import influencer.com.influencer.activities.apiResponses.registerAPI.influencerAPI.UpdateProfileApi;
import influencer.com.influencer.activities.constants.Contants;
import influencer.com.influencer.activities.restclient.RestClient;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfile extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {


    List<Integer> age = new ArrayList<>();
    @BindView(R.id.ageratio)
    Spinner ageratio;
    @BindView(R.id.textView21)
    TextView profilename;

    @BindView(R.id.male)
    RadioButton male;

    @BindView(R.id.fml)
    RadioButton female;

    @BindView(R.id.circleImageView4)
    CircleImageView profilepic;

    @BindView(R.id.editText2)
    EditText edname;

    @BindView(R.id.editText11)
    EditText about;


    @BindView(R.id.spinner)
    Spinner language;

    @BindView(R.id.spinner4)
    Spinner agefrom;

    @BindView(R.id.spinner5)
    Spinner ageto;

    @BindView(R.id.checkBox)
    CheckBox Beauty;
    @BindView(R.id.checkBox5)
    CheckBox events;
    @BindView(R.id.checkBox8)
    CheckBox interior;
    @BindView(R.id.checkBox2)
    CheckBox cosmetic;
    @BindView(R.id.checkBox6)
    CheckBox fashion;
    @BindView(R.id.checkBox9)
    CheckBox lifestyle;
    @BindView(R.id.checkBox12)
    CheckBox travel;
    @BindView(R.id.checkBox11)
    CheckBox sports;
    @BindView(R.id.checkBox3)
    CheckBox entertain;
    @BindView(R.id.checkBox7)
    CheckBox fooddrink;
    @BindView(R.id.checkBox10)
    CheckBox photography;

    @BindView(R.id.editText7)
    TextView dateofbirth;
    Calendar myCalendar;

    @BindView(R.id.loadimage)
    ImageButton addimage;

   private ArrayList<String> interestlist=new ArrayList<>();


    DatePickerDialog.OnDateSetListener date;

    private MultipartBody.Part multipart;
    private  String loadimage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profile);
        ButterKnife.bind(this);
        setdata();
        spinnerlist();
        opencalender();
        checkboxlistner();

    }

    private void checkboxlistner() {
        Beauty.setOnCheckedChangeListener(this);
        events.setOnCheckedChangeListener(this);
        interior.setOnCheckedChangeListener(this);
        cosmetic.setOnCheckedChangeListener(this);
        fashion.setOnCheckedChangeListener(this);
        lifestyle.setOnCheckedChangeListener(this);
        travel.setOnCheckedChangeListener(this);
        sports.setOnCheckedChangeListener(this);
        entertain.setOnCheckedChangeListener(this);
        fooddrink.setOnCheckedChangeListener(this);
        photography.setOnCheckedChangeListener(this);

    }

    //======================================== SET DATA ==================================================

    private void setdata() {

        if (Hawk.get("gndr") != null) {
            String gndr = Hawk.get("gndr");
            if (gndr.contains("Male")) {
                male.setChecked(true);
            } else if (gndr.contains("Female")) {
                female.setChecked(true);
            }
        }
        if (Hawk.get(Contants.PROGILEIMG) != null) {
            Picasso.with(EditProfile.this).load(Hawk.get(Contants.PROGILEIMG, "")).error(R.drawable.user).into(profilepic);
        }
        if (Hawk.get(Contants.PROFILENAME) != null) {
            edname.setText(Hawk.get(Contants.PROFILENAME, ""));
            profilename.setText(Hawk.get(Contants.PROFILENAME, ""));
        }
        if (Hawk.get("tdob") != null) {
            dateofbirth.setText(Hawk.get("tdob", ""));
        }
        if (Hawk.get("tlanguage") != null) {
            String compareValue = Hawk.get("tlanguage");
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Selectlanguage, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            language.setAdapter(adapter);
            if (compareValue != null) {
                int spinnerPosition = adapter.getPosition(compareValue);
                language.setSelection(spinnerPosition);
            }
        }

        if (Hawk.get("tratio") != null) {
            spinnerlist();
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    ageratio.setSelection(Integer.parseInt(Hawk.get("tratio").toString()) - 1);
                }
            }, 10);
        }
        if (Hawk.get("tagefrom") != null) {
            if (Hawk.get("tagefrom").equals("NaN")) {
                Hawk.put("tagefrom", "1");
            }

            spinnerlist();
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    agefrom.setSelection(Integer.parseInt(Hawk.get("tagefrom").toString()) - 1);
                }
            }, 10);

        }
        if (Hawk.get("tageto") != null) {

            if (Hawk.get("tageto").equals("NaN")) {
                Hawk.put("tageto", "1");
            }

            spinnerlist();
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    ageto.setSelection(Integer.parseInt(Hawk.get("tageto").toString()) - 1);
                }
            }, 10);

        }
        if (Hawk.get("tinterest") != null) {
            checkinerest();
        }

        if (Hawk.get("aboutme") != null) {
            about.setText(Hawk.get("aboutme", ""));
        }

    }
//=====================================================================================================

//======================================== CHECKBOX DATA ==============================================
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        switch(buttonView.getId()){
            case R.id.checkBox:
                if (isChecked)
                {
                    interestlist.add(String.valueOf(Beauty.getText().toString()));
                }
                if (!isChecked)
                {
                    interestlist.remove(Beauty.getText().toString());
                }
//                Toast.makeText(EditProfile.this,""+interestlist,Toast.LENGTH_SHORT).show();
                break;
            case R.id.checkBox5:
                if (isChecked)
                {
                    interestlist.add(String.valueOf(events.getText().toString()));
                }
                 if (!isChecked)
                {
                    interestlist.remove(events.getText().toString());
                }
//                Toast.makeText(EditProfile.this,""+interestlist,Toast.LENGTH_SHORT).show();
                break;
            case R.id.checkBox8:
                if (isChecked)
                {
                    interestlist.add(String.valueOf(interior.getText().toString()));
                }
                if (!isChecked)
                {
                    interestlist.remove(interior.getText().toString());
                }
//                Toast.makeText(EditProfile.this,""+interestlist,Toast.LENGTH_SHORT).show();
                break;
            case R.id.checkBox2:
                if (isChecked)
                {
                    interestlist.add(String.valueOf(cosmetic.getText().toString()));
                }
                if (!isChecked)
                {
                    interestlist.remove(cosmetic.getText().toString());
                }
//                Toast.makeText(EditProfile.this,""+interestlist,Toast.LENGTH_SHORT).show();
                break;
            case R.id.checkBox6:
                if (isChecked)
                {
                    interestlist.add(String.valueOf(fashion.getText().toString()));
                }
                if (!isChecked)
                {
                    interestlist.remove(fashion.getText().toString());
                }
//                Toast.makeText(EditProfile.this,""+interestlist,Toast.LENGTH_SHORT).show();
                break;

            case R.id.checkBox9:
                if (isChecked)
                {
                    interestlist.add(String.valueOf(lifestyle.getText().toString()));
                }
                if (!isChecked)
                {
                    interestlist.remove(lifestyle.getText().toString());
                }
//                Toast.makeText(EditProfile.this,""+interestlist,Toast.LENGTH_SHORT).show();
                break;
            case R.id.checkBox12:
                if (isChecked)
                {
                    interestlist.add(String.valueOf(travel.getText().toString()));
                }
                if (!isChecked)
                {
                    interestlist.remove(travel.getText().toString());
                }
//                Toast.makeText(EditProfile.this,""+interestlist,Toast.LENGTH_SHORT).show();
                break;
            case R.id.checkBox11:
                if (isChecked)
                {
//                    interestlist.add(String.valueOf(sports.getText().toString()));
                }
                if (!isChecked)
                {
                    interestlist.remove(sports.getText().toString());
                }
//                Toast.makeText(EditProfile.this, "" + interestlist, Toast.LENGTH_SHORT).show();
                break;

            case R.id.checkBox3:
                if (isChecked)
                {
                    interestlist.add(String.valueOf(entertain.getText().toString()));
                }
                if (!isChecked)
                {
                    interestlist.remove(entertain.getText().toString());
                }
//                Toast.makeText(EditProfile.this, "" + interestlist, Toast.LENGTH_SHORT).show();
                break;
            case R.id.checkBox7:
                if (isChecked)
                {
//                    interestlist.add(String.valueOf(fooddrink.getText().toString()));
                }
                if (!isChecked)
                {
                    interestlist.remove(fooddrink.getText().toString());
                }
//                Toast.makeText(EditProfile.this, "" + interestlist, Toast.LENGTH_SHORT).show();
                break;

            case R.id.checkBox10:
                if (isChecked)
                {
                    interestlist.add(String.valueOf(photography.getText().toString()));
                }
                if (!isChecked)
                {
                    interestlist.remove(photography.getText().toString());
                }
//                Toast.makeText(EditProfile.this, "" + interestlist, Toast.LENGTH_SHORT).show();
                break;


        }


    }


//======================================================================================================

    private void checkinerest() {

      /*  String intrestArray[] = Hawk.get("tinterest").toString().split(",");

        for (String s : intrestArray) {
            Intrest intrest = new Intrest(s, false);
        }
*/
        String a = Hawk.get("tinterest");

        if (a.contains("Beauty")) {
            Beauty.setChecked(true);
            interestlist.add(Beauty.getText().toString());
        }
        if (a.contains("Events")) {
            events.setChecked(true);
            interestlist.add(events.getText().toString());
        }
        if (a.contains("Interior")) {
            interior.setChecked(true);
            interestlist.add(interior.getText().toString());
        }
        if (a.contains("Cosmetic")) {
            cosmetic.setChecked(true);
            interestlist.add(cosmetic.getText().toString());
        }
        if (a.contains("Fashion")) {
            fashion.setChecked(true);
            interestlist.add(fashion.getText().toString());
        }
        if (a.contains("Photography")) {
            photography.setChecked(true);
            interestlist.add(photography.getText().toString());
        }
        if (a.contains("Lifestyle")) {
            lifestyle.setChecked(true);
            interestlist.add(lifestyle.getText().toString());
        }
        if (a.contains("Travel")) {
            travel.setChecked(true);
            interestlist.add(travel.getText().toString());
        }
        if (a.contains("Sports")) {
            sports.setChecked(true);
            interestlist.add(sports.getText().toString());
        }
        if (a.contains("Entertainment")) {
            entertain.setChecked(true);
            interestlist.add(entertain.getText().toString());
        }
        if (a.contains("Food/Drink")) {
            fooddrink.setChecked(true);
            interestlist.add(fooddrink.getText().toString());
        }
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


    private void spinnerlist() {
        for (int i = 1; i < 101; i++) {
            age.add(i);
        }
        ArrayAdapter<Integer> spinnerArrayAdapter = new ArrayAdapter<Integer>(
                this, android.R.layout.simple_spinner_item, age);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ageratio.setAdapter(spinnerArrayAdapter);
        agefrom.setAdapter(spinnerArrayAdapter);
        ageto.setAdapter(spinnerArrayAdapter);

    }

    @OnClick(R.id.editText7)
    public void openclander() {

        new DatePickerDialog(EditProfile.this, R.style.DialogTheme, date, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void updateLabel() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        dateofbirth.setText(sdf.format(myCalendar.getTime()));
    }

    @OnClick(R.id.imageView10)
    public void backclick() {
        finish();

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
                            profilepic.setImageBitmap(r.getBitmap());

                            loadimage=r.getPath();





                        } else {
//                            Toast.makeText(getContext(), r.getError().getMessage(), Toast.LENGTH_LONG).show();
//                            isImageAttached = false;
                        }
                    }
                })
                .setOnPickCancel(new IPickCancel() {
                    @Override
                    public void onCancelClick() {
                    }
                }).show(EditProfile.this);
    }

    @OnClick(R.id.textView34)
    public void savebtnclick(){
//        String imagepath= loadimage;
//
//        String gender = "";
//        final String interest = String.valueOf(interestlist).replace("[","").replace("]","");
//        if (male.isChecked())
//        {
//            gender=male.getText().toString();
//        }
//        else if (female.isChecked())
//        {
//            gender=female.getText().toString();
//        }
//        if (imagepath==null)
//        {
//            imagepath=Hawk.get(Contants.PROGILEIMG);
//            File file = new File(imagepath);
//            multipart = MultipartBody.Part.createFormData("file", file.getName(), RequestBody.create(MediaType.parse("image/*"), file));
//        }
//        else
//        {
//            File file = new File(imagepath);
//            multipart = MultipartBody.Part.createFormData("file", file.getName(),RequestBody.create(MediaType.parse("image/*"), file));
//        }
//
//        final String name=edname.getText().toString();
//        final String dob=dateofbirth.getText().toString();
//        final String languag=language.getSelectedItem().toString();
//        final String ageratios=ageratio.getSelectedItem().toString();
//       final String agerange= agefrom+","+ageto;
//
//        final String abouts=about.getText().toString();
//        if (abouts.isEmpty()) {
//            about.setError("Can't Empty");
//        }
//        else if (name.isEmpty())
//        {
//            edname.setError("Can't Empty");
//        }
//        else if(languag.equals("Selectlanguage"))
//        {
//            Toast.makeText(EditProfile.this,"Please Select Language",Toast.LENGTH_SHORT).show();
//        }
//        else
//        {
//           RestClient.GitApiInterface restClient = RestClient.getClient();
//            final String gndr = gender;
//            final String about;
//            restClient.updateprofile(Hawk.get(Contants.INFLUENCERID,""),name,multipart,gender,languag,dob,interest,ageratios,agerange,abouts).enqueue(new Callback<UpdateProfileApi>() {
//               @Override
//               public void onResponse(Call<UpdateProfileApi> call, Response<UpdateProfileApi> response) {
//
//                   if(response.body().getSuccess())
//                   {
//
//                       Toast.makeText(EditProfile.this,response.body().getMessage(),Toast.LENGTH_SHORT).show();
//                       changevalue(name,multipart, gndr,languag,dob,interest,ageratios,abouts);
//                   }
//               }
//
//
//               @Override
//               public void onFailure(Call<UpdateProfileApi> call, Throwable t) {
//
//               }
//           });
//
//        }
    }

private void changevalue(String name,MultipartBody.Part file,String gendr,String languages,String dob,String interest,String ageratio, String about)
{
//  Hawk.put(Contants.PROGILEIMG,file);
    Hawk.put(Contants.PROFILENAME,name);
    Hawk.put("gndr",gendr);
    Hawk.put("tlanguage",languages);
    Hawk.put("tdob",dob);
    Hawk.put("tinterest",interest);
    Hawk.put("tratio",ageratio);
    Hawk.put("tagefrom",agefrom.getSelectedItem().toString());
    Hawk.put("tageto",ageto.getSelectedItem().toString());
    Hawk.put("aboutme",about);
}

}
