package influencer.com.influencer.activities.activity.influencer;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.annotation.ConfirmPassword;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;
import java.util.List;

import com.mobsandgeeks.saripaar.Validator;
import com.orhanobut.hawk.Hawk;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import influencer.com.influencer.R;
import influencer.com.influencer.activities.apiResponses.registerAPI.influencerAPI.ChangePwdApi;
import influencer.com.influencer.activities.constants.Contants;
import influencer.com.influencer.activities.restclient.RestClient;
import influencer.com.influencer.activities.retrofit.RetrofitUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePassword extends AppCompatActivity implements Validator.ValidationListener {



    com.mobsandgeeks.saripaar.Validator listener ;
    TextView textView34;
    private Context context;




    @BindView(R.id.editText8)
    @NotEmpty
    EditText oldpwd;

    @BindView(R.id.editText10)
    @NotEmpty
    @Password(min = 6, scheme = Password.Scheme.ALPHA_NUMERIC_MIXED_CASE)
    EditText newpwd;
    @BindView(R.id.editText9)
    @NotEmpty
    @ConfirmPassword
    EditText cnfrnpwd;

    Dialog dialog;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        ButterKnife.bind(this);
        textView34 = findViewById(R.id.textView34);
        listener  = new Validator(ChangePassword.this);
        listener.setValidationListener(this);


        textView34.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pwd=newpwd.getText().toString();
                String cpwd=cnfrnpwd.getText().toString();

                listener.validate();


            }
        });









    }

    @Override
    public void onValidationSucceeded() {

        String pwd=oldpwd.getText().toString();
        String cpwd=cnfrnpwd.getText().toString();

        dialog = new Dialog(ChangePassword.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custom_dialog_progress);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCancelable(false);
        dialog.show();

        RestClient.GitApiInterface restClient = RestClient.getClient();
        restClient.changepwd(Hawk.get(Contants.INFLUENCERID,""),pwd,cpwd).enqueue(new Callback<ChangePwdApi>() {
            @Override
            public void onResponse(Call<ChangePwdApi> call, Response<ChangePwdApi> response) {
                dialog.dismiss();
                finish();
                Toast.makeText(getApplicationContext(),response.body().getMessage(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ChangePwdApi> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
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

    @OnClick(R.id.imageView10)
    public void backbtnclick()
    {
        finish();
    }
}
