package br.com.cptm.gefimobile.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Calendar;

public class Revisao implements Serializable {


    private long id;
    private String dataRevisao;
    private Equipamento equipamento;
    private Usuario usuario;
    private Departamento departamento;
    private int status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDataRevisao() {
        return dataRevisao;
    }

    public void setDataRevisao(String dataRevisao) {
        this.dataRevisao = dataRevisao;
    }

    public Equipamento getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Revisao{" +
                "id=" + id +
                ", dataRevisao=" + dataRevisao +
                ", equipamento=" + equipamento +
                ", usuario=" + usuario +
                ", departamento=" + departamento +
                ", status=" + status +
                '}';
    }
}
