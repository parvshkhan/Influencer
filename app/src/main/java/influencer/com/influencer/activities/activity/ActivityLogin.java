package influencer.com.influencer.activities.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import influencer.com.influencer.R;

public class ActivityLogin extends AppCompatActivity implements Validator.ValidationListener {

    @BindView(R.id.register_move)
    TextView tvregisterNow;

    @BindView(R.id.loginbtn)
    Button btLogin;


    Validator validator;

    @BindView(R.id.login_email)
    @NotEmpty
    @Email (message = "Please enter the valid email")
    EditText edLogin;

    @BindView(R.id.login_password)
    @Password(min = 6, scheme = Password.Scheme.ANY)
    EditText edPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        hidingTheStatusBar();


        setContentView(R.layout.activity_login_infuencer);

        ButterKnife.bind(this);
        validator = new Validator(ActivityLogin.this);
        validator.setValidationListener(this);


    }

    private void hidingTheStatusBar() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


    }


    @Override
    public void onValidationSucceeded() {
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(edPassword.getWindowToken(), 0);

        Intent intent=new Intent(ActivityLogin.this,MainActivity.class);
        startActivity(intent);
        finish();

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


        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(edPassword.getWindowToken(), 0);




    }




    @OnClick(R.id.loginbtn)
    public void openMainAcivity()
    {

        validator.validate();

    }
    @OnClick(R.id.register_move)
    public void openRegisterActivity()
    {

        Intent intent=new Intent(getApplicationContext(),ActivityRegister.class);
        startActivity(intent);

    }

}
