package br.com.cptm.gefimobile.services;

import java.util.List;

import br.com.cptm.gefimobile.models.Revisao;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.Query;

public interface RevisaoService {

    @GET("listaRevisaoPorDepartamento")
    Call<List<Revisao>>
    getRevisaoByDepartamento(@Query("revisao_status") int revisao_status,
                             @Query("departamento_fk") int departamento_fk);

    @GET("listaRevisaoPorUsuario")
    Call<List<Revisao>>
    getRevisaoByUsuario(@Query("revisao_status") int revisao_status,
                        @Query("usuario_fk") int usuario_fk);


    @PATCH("revisao")
    Call<Void> atualizaRevisao(@Body Revisao revisao);

}
