package br.com.cptm.gefimobile.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class Equipamento implements Serializable {

    private long id;
    private String descricao;
    private String modelo;
    private String fabricante;
    private String codigoCPTM;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getCodigoCPTM() {
        return codigoCPTM;
    }

    public void setCodigoCPTM(String codigoCPTM) {
        this.codigoCPTM = codigoCPTM;
    }

    @Override
    public String toString() {
        return "Equipamento{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", modelo='" + modelo + '\'' +
                ", fabricante='" + fabricante + '\'' +
                ", codigoCPTM='" + codigoCPTM + '\'' +
                '}';
    }
}
