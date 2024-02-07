package br.com.cptm.gefimobile.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LoginResponse implements Serializable {

    private int id;
    private String login;
    private int role_fk;
    private int trocarSenha;
    private int departamento;
    private String token;
    private boolean auth;
    private String privilegio;
    private int status;
    private String senha;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDepartamento() {
        return departamento;
    }

    public void setDepartamento(int departamento) {
        this.departamento = departamento;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public boolean isAuth() {
        return auth;
    }

    public void setAuth(boolean auth) {
        this.auth = auth;
    }

    public String getPrivilegio() {
        return privilegio;
    }

    public void setPrivilegio(String privilegio) {
        this.privilegio = privilegio;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getRole_fk() {
        return role_fk;
    }

    public void setRole_fk(int role_fk) {
        this.role_fk = role_fk;
    }

    public int getTrocarSenha() {
        return trocarSenha;
    }

    public void setTrocarSenha(int trocarSenha) {
        this.trocarSenha = trocarSenha;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "id=" + id +
                ", departamento='" + departamento + '\'' +
                ", token='" + token + '\'' +
                ", login='" + login + '\'' +
                ", auth=" + auth +
                ", privilegio='" + privilegio + '\'' +
                ", status=" + status +
                ", senha='" + senha + '\'' +
                ", role_fk=" + role_fk +
                ", trocar_senha=" + trocarSenha +
                '}';
    }
}