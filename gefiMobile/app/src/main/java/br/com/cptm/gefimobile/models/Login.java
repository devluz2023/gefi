package br.com.cptm.gefimobile.models;

public class Login {
    private int id;
    private String senha;
    private String login;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public String toString() {
        return "Login{" +
                "id=" + id +
                ", senha='" + senha + '\'' +
                ", login='" + login + '\'' +
                '}';
    }
}
