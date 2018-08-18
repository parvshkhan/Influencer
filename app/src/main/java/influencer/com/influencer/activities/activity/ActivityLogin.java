package influencer.com.influencer.activities.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
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
import influencer.com.influencer.activities.apiResponses.registerAPI.LoginAPI;
import influencer.com.influencer.activities.apiResponses.registerAPI.RegisterAPI;
import influencer.com.influencer.activities.callback.IRegisterCallback;
import influencer.com.influencer.activities.retrofit.RetrofitUtil;
import retrofit2.Response;

public class ActivityLogin extends AppCompatActivity implements Validator.ValidationListener, IRegisterCallback {

    @BindView(R.id.register_move)
    TextView tvregisterNow;

    @BindView(R.id.loginbtn)
    Button btLogin;

    View view;


    Validator validator;

    @BindView(R.id.login_email)
    @NotEmpty
    @Email(message = "Please enter the valid email")
    EditText edLogin;

    @BindView(R.id.login_password)
    @Password(min = 6, scheme = Password.Scheme.ALPHA_NUMERIC_MIXED_CASE)
    EditText edPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        hidingTheStatusBar();


        setContentView(R.layout.activity_login_infuencer);

        ButterKnife.bind(this);
        validator = new Validator(ActivityLogin.this);
        validator.setValidationListener(this);


        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(edPassword.getWindowToken(), 0);


    }

    private void hidingTheStatusBar() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


    }


    @Override
    public void onValidationSucceeded() {


        Intent intent = new Intent(getApplicationContext(), ActivitySelectIntrest.class);
        startActivity(intent);

//        RetrofitUtil retrofitUtil = new RetrofitUtil (ActivityLogin.this);
//        retrofitUtil.LoginResponse (edLogin.getText().toString(),edPassword.getText().toString());

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


    @OnClick(R.id.loginbtn)
    public void openMainAcivity() {

        validator.validate();

    }

    @OnClick(R.id.register_move)
    public void openRegisterActivity() {

        Intent intent = new Intent(getApplicationContext(), ActivityRegister.class);
        startActivity(intent);

    }

    @OnClick(R.id.textView3)
    public void openforgetpassword() {


        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

        view = LayoutInflater.from(ActivityLogin.this).inflate(R.layout.activity_forget_password, null);


        dialogBuilder.setView(view);

        final AlertDialog alertDialog = dialogBuilder.create();
        final EditText etemail = view.findViewById(R.id.forgotemail);

        view.findViewById(R.id.forgotcencelbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                alertDialog.dismiss();

            }
        });

        view.findViewById(R.id.btnforgotpwd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = etemail.getText().toString();

                if (email.matches("[a-zA-Z0-9._-]+@[a-z]+.[ a-z]+") && email.length()>0)

                {
                    Toast.makeText(getApplicationContext(), "done", 0).show();






                } else {
                    etemail.setError("Invalid ");
                }


            }
        });

        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        alertDialog.show();
    }

    @Override
    public void getRegisterResponse(Object response) {

        if (response instanceof Throwable) {

            Toast.makeText(getApplicationContext(), "Api crashed", Toast.LENGTH_SHORT).show();
        } else if (response instanceof Response) {
            Response<LoginAPI> forgotpwdrAPI = (Response<LoginAPI>) response;


        }

    }
}
