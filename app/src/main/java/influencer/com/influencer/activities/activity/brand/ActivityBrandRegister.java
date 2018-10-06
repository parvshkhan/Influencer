package influencer.com.influencer.activities.activity.brand;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
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
import com.orhanobut.hawk.Hawk;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import influencer.com.influencer.R;
import influencer.com.influencer.activities.activity.MainActivity;
import influencer.com.influencer.activities.apiResponses.registerAPI.brand.BrandRegisterApi;
import influencer.com.influencer.activities.callback.ICallback;
import influencer.com.influencer.activities.retrofit.RetrofitUtil;

public class ActivityBrandRegister extends AppCompatActivity implements Validator.ValidationListener ,ICallback {

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

    Dialog progressDialog;

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
        progressDialog = new Dialog(ActivityBrandRegister.this);
        progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        progressDialog.setContentView(R.layout.custom_dialog_progress);
        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        progressDialog.setCancelable(false);
        progressDialog.show();

        RetrofitUtil retrofitUtil = new RetrofitUtil( ActivityBrandRegister.this );
        retrofitUtil.BrandRegister(edEmail.getText ( ).toString ( ),edPassword.getText ( ).toString ( ),confirmPassword.getText().toString());

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
       validator.validate ( );


    }

    @Override
    public void getresponse(Object response) {

        if (response instanceof BrandRegisterApi) {
            if (((BrandRegisterApi) response).getSuccess()) {
                progressDialog.hide();
                progressDialog.cancel();
                progressDialog.dismiss();

                Hawk.put("brandid",((BrandRegisterApi) response).getBrandID());
                Toast.makeText(getApplicationContext(), ((BrandRegisterApi) response).getMessage(), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(),ActivityBrandUserInfo.class);
                startActivity(intent);
                finish();


            } else {


                progressDialog.hide();
                progressDialog.cancel();
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), ((BrandRegisterApi) response).getMessage(), Toast.LENGTH_LONG).show();



            }
        }

    }

    @Override
    public void getfailerresponse(Throwable throwable) {

    }
}
