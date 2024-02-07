package br.com.cptm.gefimobile.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import br.com.cptm.gefimobile.R;
import br.com.cptm.gefimobile.models.LoginResponse;
import br.com.cptm.gefimobile.models.Roles;

public class SessionManager {

    private SharedPreferences pref;
    public static String TOKEN = "gefi_token";
    public static String LOGIN = "gefi_login";
    public static String AUTH = "gefi_auth";
    public static String ID = "gefi_id";
    public static String SENHA = "gefi_senha";
    public static String PRIVILEGIO = "gefi_privilegio";
    public static String DEPARTAMENTO_FK ="departamento_fk";


   public SessionManager (Context context) {
        pref = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE);
    }


    public void saveAuthToken(LoginResponse loginResponse) {
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(TOKEN, loginResponse.getToken());
        editor.putString(LOGIN, loginResponse.getLogin());
        editor.putBoolean(AUTH, loginResponse.isAuth());
        editor.putInt(ID, loginResponse.getId());
        editor.putString(SENHA, loginResponse.getSenha());
        editor.putInt(DEPARTAMENTO_FK, loginResponse.getDepartamento());
        editor.putString(PRIVILEGIO, loginResponse.getPrivilegio());
        editor.commit();
    }

    public void atualizaSenha(String senha){
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(SENHA, senha);
        editor.commit();
    }



    public LoginResponse getLoginResponse(){
       LoginResponse loginResponse = new LoginResponse();
       loginResponse.setAuth(pref.getBoolean(AUTH, false));
       loginResponse.setLogin(pref.getString(LOGIN, null));
       loginResponse.setToken(pref.getString(TOKEN, null));
       loginResponse.setId(pref.getInt(ID, 0));
       loginResponse.setSenha(pref.getString(SENHA, null));
       loginResponse.setPrivilegio(pref.getString(PRIVILEGIO, null));
       loginResponse.setDepartamento(pref.getInt(DEPARTAMENTO_FK, 0));
       return loginResponse;
    }


    public void logout(){
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.commit();
    }


    public boolean isAdmin(){
        try {
            String privilegio = getLoginResponse().getPrivilegio();
            if(privilegio!=null){
                return getLoginResponse().getPrivilegio().equals(Roles.ADMIN);
            }
        }catch (NullPointerException e){
            Log.e("SessionAdminIsAdmin", e.getMessage());
        }
            return false;
    }

    public boolean isAuth() {
       try {
           return getLoginResponse().isAuth();
       }catch (NullPointerException e){
           Log.e("SessionLogin", e.getMessage());
       }
           return false;
    }

}
