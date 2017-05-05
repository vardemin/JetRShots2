package vardemin.com.jetrshots2.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import javax.inject.Inject;


import butterknife.ButterKnife;
import butterknife.OnClick;
import vardemin.com.jetrshots2.App;
import vardemin.com.jetrshots2.R;
import vardemin.com.jetrshots2.contract.LoginContract;
import vardemin.com.jetrshots2.di.component.DaggerLoginComponent;
import vardemin.com.jetrshots2.di.component.LoginComponent;
import vardemin.com.jetrshots2.di.module.LoginModule;
import vardemin.com.jetrshots2.presenter.LoginPresenter;
import vardemin.com.jetrshots2.util.Constant;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    @Inject
    LoginPresenter presenter;

    private LoginComponent loginComponent;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginComponent = DaggerLoginComponent.builder()
                .applicationComponent(App.getApplicationComponent())
                .loginModule(new LoginModule())
                .build();
        loginComponent.inject(this);
        ButterKnife.bind(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setIndeterminate(true);
        progressDialog.setCanceledOnTouchOutside(false);

    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onAttach(this);
        Uri uri = getIntent().getData();
        if (uri != null && uri.toString().startsWith(Constant.REDIRECT_URI)) {
            String code = uri.getQueryParameter("code");
            Log.d("INTERCEPT URI",code);
            if (code != null) {
                setLoading(true);
                presenter.login(code);
            } else if (uri.getQueryParameter("error") != null) {
                showError(uri.getQueryParameter("error"));
            }
        }
    }

    @Override
    public  void onPause() {
        super.onPause();
        presenter.onDetach();
    }

    @Override
    public void setLoading(boolean state) {
        if (state && !progressDialog.isShowing())
            progressDialog.show();
        else {
            if (progressDialog.isShowing())
                progressDialog.cancel();
        }

    }


    @OnClick(R.id.login_button)
    @Override
    public void login() {
        Intent intent = new Intent(
                Intent.ACTION_VIEW,
                Uri.parse(Constant.AUTH_URL + "authorize" + "?client_id=" + Constant.CLIENT_ID + "&redirect_uri=" + Constant.REDIRECT_URI+"&scope=public+write+comment"));
        startActivity(intent);
    }

    @Override
    public void onAuthorized() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void showError(String errorMessage) {
        Toast.makeText(this,errorMessage,Toast.LENGTH_SHORT).show();
    }

    public LoginComponent getLoginComponent() {
        return loginComponent;
    }

}
