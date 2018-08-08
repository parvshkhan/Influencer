package influencer.com.influencer.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;

import java.util.List;

import influencer.com.influencer.R;

public class ActivityRegisterInfluencer extends AppCompatActivity implements View.OnClickListener, Validator.ValidationListener {

    TextView login_move;
    Button registerbtn;

    Validator validator;

    @NotEmpty
    EditText register_etusername;
    @Email(message = "Please enter the valid email")
    private EditText register_etemail;

    @Password(min = 6, scheme = Password.Scheme.ANY)
    private EditText register_etpassword;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hidingTheStatusBar();
        setContentView(R.layout.activity_register_influencer);


        validator = new Validator(this);
        validator.setValidationListener(this);


        findid();

        login_move.setOnClickListener(this);
        registerbtn.setOnClickListener(this);
    }


    private void hidingTheStatusBar() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


    }


    private void findid() {
        login_move = findViewById(R.id.login_move);
        register_etusername=findViewById(R.id.login_email);
        register_etemail=findViewById(R.id.login_password);
        register_etpassword=findViewById(R.id.register_password);
        registerbtn=findViewById(R.id.loginbtn);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.login_move:
                Intent intent = new Intent(ActivityRegisterInfluencer.this, ActivityLoginInfluencer.class);
                startActivity(intent);
                break;
            case R.id.loginbtn:
                registerbtnclick();
                break;
        }

    }

    private void registerbtnclick() {
//        String username,email,password;
//        username=register_etusername.getText().toString();



        validator.validate();
    }

    @Override
    public void onValidationSucceeded() {

        Toast.makeText(this, "Yay! we got it right!", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {


        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);

            // Display error messages ;)
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();



            }
        }


    }
}
