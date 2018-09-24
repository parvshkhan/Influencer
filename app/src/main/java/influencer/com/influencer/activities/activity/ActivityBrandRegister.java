package influencer.com.influencer.activities.activity;

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
import com.mobsandgeeks.saripaar.annotation.ConfirmPassword;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import influencer.com.influencer.R;

public class ActivityBrandRegister extends AppCompatActivity implements Validator.ValidationListener {

    @BindView(R.id.login_move)
    TextView tvLogin;

    @BindView(R.id.loginbtn)
    Button btRegister;


    private Validator validator;


    @BindView(R.id.login_password)
    @NotEmpty
    @Email(message = "Please enter the valid email")
    EditText edEmail;

    @BindView(R.id.register_password2)
    @NotEmpty
    @Password(min = 8, scheme = Password.Scheme.ALPHA_NUMERIC_MIXED_CASE,message = "A minimum 8 characters  contains a combination of uppercase and lowercase letter and number are required")
    EditText edPassword;

    @BindView(R.id.register_passwordconfirm)
    @NotEmpty
    @ConfirmPassword()
    EditText confirmPassword;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_register);

        ButterKnife.bind ( this );
        validator = new Validator ( this );
        validator.setValidationListener ( this );
    }

    @Override
    public void onValidationSucceeded() {


    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {

        for (ValidationError error : errors) {
            View view = error.getView ( );
            String message = error.getCollatedErrorMessage ( this );


            if(view instanceof EditText) {
                ((EditText) view).setError ( message );
            } else {
                Toast.makeText ( this, message, Toast.LENGTH_LONG ).show ( );


            }
        }

    }

//    private void hidingTheStatusBar() {
//        requestWindowFeature ( Window.FEATURE_NO_TITLE );
//        this.getWindow ( ).setFlags ( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );
//    }

    @OnClick(R.id.login_move)
    public void openLoginActivity() {
        finish ( );
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
    }

    @OnClick(R.id.loginbtn)
    public void openMainAcitivity() {
//        validator.validate ( );
        startActivity(new Intent(getApplicationContext(),ActivityBrandUserInfo.class));
        overridePendingTransition(R.anim.fadein, R.anim.slide_to_right);
        finish();


    }
}
