package influencer.com.influencer.activities;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
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
import com.mobsandgeeks.saripaar.annotation.Password;

import java.util.List;

import influencer.com.influencer.R;

public class ActivityLoginInfluencer extends AppCompatActivity implements View.OnClickListener,Validator.ValidationListener {
TextView move_register;
Button loginuserbtn;
Validator validator;
ProgressDialog progressDialog;

@Email (message = "Please enter the valid email")
    EditText login_email;

    @Password(min = 6, scheme = Password.Scheme.ANY)
    private EditText login_etpassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        hidingTheStatusBar();


        setContentView(R.layout.activity_infuencer);

        validator = new Validator(this);

        findid();

        move_register.setOnClickListener(this);
        loginuserbtn.setOnClickListener(this);

    }

    private void hidingTheStatusBar() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


    }

    private void findid() {

        move_register=findViewById(R.id.register_move);
        login_email=findViewById(R.id.login_email);
        login_etpassword=findViewById(R.id.login_password);
        loginuserbtn=findViewById(R.id.loginbtn);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.register_move:
                Intent intent=new Intent(ActivityLoginInfluencer.this,ActivityRegisterInfluencer.class);
                startActivity(intent);
                finish();
                break;
            case R.id.loginbtn:
                loginbtnclick();
                break;
        }



    }

    private void loginbtnclick() {

        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);



    }

    @Override
    public void onValidationSucceeded() {




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
