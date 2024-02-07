package br.com.cptm.gefimobile.services;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {

    private final Retrofit retrofit;
   // private static final String API_URL = "http://201.55.47.45:8083/";
   private static final String API_URL = "http://10.0.2.2:8083/";
    OkHttpClient client = new OkHttpClient();

    public RetrofitConfig() {
        retrofit =  new Retrofit.Builder()
                .baseUrl(API_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create()).build();
    }


    public ControleService getControleService(){
        return this.retrofit.create(ControleService.class);
    }

    public UsuarioService getUsuarioService(){
        return this.retrofit.create(UsuarioService.class);
    }

    public RevisaoService getRevisaoService(){
        return retrofit.create(RevisaoService.class);
    }

}