package influencer.com.influencer.activities.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import influencer.com.influencer.R;
import influencer.com.influencer.activities.apiResponses.registerAPI.RegisterAPI;
import influencer.com.influencer.activities.callback.IRegisterCallback;
import influencer.com.influencer.activities.retrofit.RetrofitUtil;
import retrofit2.Response;

public class ActivityRegister extends AppCompatActivity implements Validator.ValidationListener,IRegisterCallback {

    @BindView(R.id.login_move)
    TextView tvLogin;

    @BindView(R.id.loginbtn)
    Button btRegister;


  private Validator validator;

    @BindView(R.id.login_email)
    @NotEmpty
    EditText edUserName;

    @BindView(R.id.login_password)
    @NotEmpty
    @Email(message = "Please enter the valid email")
    EditText edEmail;

    @BindView(R.id.register_password)
    @NotEmpty
    @Password(min = 6, scheme = Password.Scheme.ANY)
    EditText edPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        hidingTheStatusBar ( );
        setContentView ( R.layout.activity_register_influencer );
        ButterKnife.bind ( this );
        validator = new Validator ( this );
        validator.setValidationListener ( this );
    }


    private void hidingTheStatusBar() {
        requestWindowFeature ( Window.FEATURE_NO_TITLE );
        this.getWindow ( ).setFlags ( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );
    }


    @Override
    public void onValidationSucceeded() {

        RetrofitUtil retrofitUtil = new RetrofitUtil (ActivityRegister.this);
        retrofitUtil.RegisterResponse ( edUserName.getText ().toString (),edEmail.getText ().toString (),edPassword.getText ().toString ());
    }

    @Override
    public void onValidationFailed(List <ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView ( );
            String message = error.getCollatedErrorMessage ( this );

            // Display error messages ;)
            if(view instanceof EditText) {
                ((EditText) view).setError ( message );
            } else {
                Toast.makeText ( this, message, Toast.LENGTH_LONG ).show ( );


            }
        }


    }


    @OnClick(R.id.login_move)
    public void openLoginActivity() {
        finish ( );
    }

    @OnClick(R.id.loginbtn)
    public void openMainAcitivity() {

        validator.validate ( );
    }

    @Override
    public void getRegisterResponse(Object response) {


        if(response instanceof Throwable)
        {

            Toast.makeText ( getApplicationContext (), "Api crashed", Toast.LENGTH_SHORT ).show ( );
        }
        else
        if(response instanceof Response)
        {
            Response <RegisterAPI> registerAPI = (Response<RegisterAPI>) response;




        }



    }
}
