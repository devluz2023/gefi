package br.com.cptm.gefimobile.services;

import java.util.List;

import br.com.cptm.gefimobile.models.Controle;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ControleService {

    @GET("listacontrolepordepartamento")
    Call<List<Controle>> getControlesByDepartamento(@Query("controle_status") int controle_status,
                                                    @Query("departamento_fk") int departamento_fk);

    @GET("listacontroleporusuario")
    Call<List<Controle>> getControlesByUsuario(@Query("controle_status") int controle_status,
                                                   @Query("usuario_fk") int usuario_fk);


    @PATCH("controle")
    Call<Void> atualizaControle(@Body Controle controle);

    @POST("controle")
    Call<Controle> adicionaControle(@Body Controle controle);


}
