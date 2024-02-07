package br.com.cptm.gefimobile.ui.activities;

import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import br.com.cptm.gefimobile.R;
import br.com.cptm.gefimobile.models.Equipamento;
import br.com.cptm.gefimobile.models.LoginRequest;
import br.com.cptm.gefimobile.models.LoginResponse;
import br.com.cptm.gefimobile.models.Roles;
import br.com.cptm.gefimobile.services.RetrofitConfig;
import br.com.cptm.gefimobile.util.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {

    private EditText uname, pwd;
    private Button loginBtn;
    private SessionManager sessionManager;
    private Intent  intent;
    private LoginResponse loginResponse;

    @Override
    protected void onStart() {
        super.onStart();
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION|
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        uname = (EditText) findViewById(R.id.txtName);
        pwd = (EditText) findViewById(R.id.txtPwd);
        loginBtn = (Button) findViewById(R.id.btnLogin);

        intent = new Intent(this, MainActivity.class);
        sessionManager = new SessionManager(this);



        this.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = uname.getText().toString().toUpperCase();
                String password = pwd.getText().toString().toUpperCase();
                loginServer(username, password);
            }
        });

    }


    public void loginServer(String username, String password){

        RetrofitConfig retrofitInitializer = new RetrofitConfig();
        Call<LoginResponse> sendLogin = retrofitInitializer.getUsuarioService().
                login(new LoginRequest(username, password) );

        sendLogin.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                 loginResponse = response.body();

                 if(loginResponse!=null){
                     if (response.code()==200 &&
                             loginResponse.getLogin() != null && loginResponse.isAuth()) {

                         loginResponse.setSenha(password);
                         sessionManager.saveAuthToken(loginResponse);
                         startActivity(intent);
                         finish();
                         Toast.makeText(getApplicationContext(),
                                 "Usuário autenticado com sucesso", Toast.LENGTH_SHORT).show();
                     } else {

                         Toast.makeText(getApplicationContext(),
                                 "Usuário ou senha inválido",Toast.LENGTH_SHORT).show();
                     }
                 }

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.d("onFailureLoginActivy" , t.getMessage());

            }
        });



    }




}