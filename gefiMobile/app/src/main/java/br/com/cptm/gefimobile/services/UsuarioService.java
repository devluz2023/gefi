package br.com.cptm.gefimobile.services;

import java.util.List;

import br.com.cptm.gefimobile.models.Login;
import br.com.cptm.gefimobile.models.LoginRequest;
import br.com.cptm.gefimobile.models.LoginResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UsuarioService {

    @PATCH("usuarios")
    Call<LoginResponse> atualizarSenha(@Body Login loginRequest);

    @POST("autenticar")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);

    @POST("logout")
    Call<LoginResponse> logout(@Body LoginRequest loginRequest);

}
