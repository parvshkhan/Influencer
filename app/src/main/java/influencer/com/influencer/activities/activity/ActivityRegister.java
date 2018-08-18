package influencer.com.influencer.activities.activity;

import android.app.ProgressDialog;
import android.content.Intent;
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

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import influencer.com.influencer.R;
import influencer.com.influencer.activities.apiResponses.registerAPI.RegisterAPI;
import influencer.com.influencer.activities.restclient.RestClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityRegister extends AppCompatActivity implements Validator.ValidationListener {

    @BindView(R.id.login_move)
    TextView tvLogin;

    @BindView(R.id.loginbtn)
    Button btRegister;


    Validator validator;

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
        final ProgressDialog progressDialog = new ProgressDialog ( ActivityRegister.this );
        progressDialog.setMessage ( "Pleaser Wait..." );
        progressDialog.show ( );

        RestClient.GitApiInterface restClient = RestClient.getClient ( );
//        HashMap <String, String> stringStringHashMap = new HashMap <> ( );
//        stringStringHashMap.put ( "Email", edEmail.getText ( ).toString ( ) );
//        stringStringHashMap.put ( "Password", edPassword.getText ( ).toString ( ) );
//        stringStringHashMap.put ( "UserName", edUserName.getText ( ).toString ( ) );


        String email = edEmail.getText ( ).toString ( ).trim ( );
        String password = edPassword.getText ( ).toString ( ).trim ( );
        String username = edUserName.getText ( ).toString ( ).trim ( );




        restClient.registerAPI ( email, password, username ).enqueue ( new Callback <RegisterAPI> ( ) {
            @Override
            public void onResponse(Call <RegisterAPI> call, Response <RegisterAPI> response) {

                if(response.body ( ).getSuccess ( )) {

//                    Toast.makeText ( getApplicationContext (),""+response,Toast.LENGTH_SHORT ).show ();


                    Toast.makeText ( getApplicationContext ( ), response.body ( ).getMessage ( ), Toast.LENGTH_LONG ).show ( );

                    progressDialog.hide ( );


                } else {


                    Toast.makeText ( getApplicationContext ( ), response.body ( ).getMessage ( ), Toast.LENGTH_LONG ).show ( );
                    progressDialog.hide ( );


                }


            }

            @Override
            public void onFailure(Call <RegisterAPI> call, Throwable t) {


                System.out.println ( "ActivityRegister.onFailure"  + t);
                Toast.makeText ( getApplicationContext ( ), "" + t, Toast.LENGTH_LONG ).show ( );
                progressDialog.hide ( );


            }
        } );


     /*   Intent intent=new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);*/
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
}
