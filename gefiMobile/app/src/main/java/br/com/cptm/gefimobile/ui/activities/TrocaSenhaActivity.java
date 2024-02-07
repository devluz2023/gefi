package br.com.cptm.gefimobile.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import br.com.cptm.gefimobile.R;
import br.com.cptm.gefimobile.models.Login;
import br.com.cptm.gefimobile.models.LoginRequest;
import br.com.cptm.gefimobile.models.LoginResponse;
import br.com.cptm.gefimobile.services.RetrofitConfig;
import br.com.cptm.gefimobile.util.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrocaSenhaActivity extends AppCompatActivity {

    private Button btnAlterarSenha;
    private EditText senhaAtual;
    private EditText novaSenha;
    private EditText novaSenhaRepetida;
    private SessionManager sessionManager;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sessionManager = new SessionManager(this);

        setContentView(R.layout.activity_troca_senha);
        btnAlterarSenha = (Button) findViewById(R.id.btn_alterar_senha);
        senhaAtual = (EditText) findViewById(R.id.edtxt_senha_antiga);
        novaSenha = (EditText) findViewById(R.id.edtxt_senha_nova);
        novaSenhaRepetida =(EditText)findViewById(R.id.edtxt_senha_nova_repetida);
        intent = new Intent(this, MainActivity.class);


        btnAlterarSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String senha = senhaAtual.getText().toString();
                if(confereSenha(senha)){

                    if(novaSenha.getText().toString().equals(novaSenhaRepetida.getText().toString())){
                        String novaSenhaServer = novaSenha.getText().toString();
                        enviaParaOservidor(novaSenhaServer);
                        Log.e("enviando", "enviando");
                        startActivity(intent);
                        finish();
                    }else{
                        showAviso("Senhas naõ conferem");
                    }
                }else{
                   showAviso("Senha Atual não confere");
                }
            }
        });


    }

    private boolean confereSenha(String senha) {
        return sessionManager.getLoginResponse().getSenha().equals(senha);
    }

    private void enviaParaOservidor(String novaSenha) {
        RetrofitConfig retrofitInitializer = new RetrofitConfig();
        retrofitInitializer.getUsuarioService();

         LoginResponse loginResponse =
                 new SessionManager(this).getLoginResponse();
        Login usuario = new Login();
        usuario.setId(loginResponse.getId());
        usuario.setSenha(novaSenha);
        usuario.setLogin(loginResponse.getLogin());

        Call<LoginResponse> updatePassword = retrofitInitializer.getUsuarioService().
                atualizarSenha(usuario);

        updatePassword.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                sessionManager.atualizaSenha(novaSenha);
                Toast.makeText(getApplicationContext(),
                        "Senha atualizada com sucesso", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
               Log.d("onFailureTroca", t.getMessage());
            }
        });

    }


    private boolean validaCampo(String campo){
        return campo.length() > 5;
    }
    private void showAviso(String mensagem){
        Toast.makeText(getApplicationContext(), mensagem, Toast.LENGTH_LONG).show();
    }



}