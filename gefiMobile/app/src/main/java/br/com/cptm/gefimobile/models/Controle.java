package br.com.cptm.gefimobile.models;

import java.io.Serializable;

public class Controle implements Serializable {

    private long id;
    private String dataRetirada;
    private String dataDevolucao;
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

    public String getDataRetirada() {
        return dataRetirada;
    }

    public void setDataRetirada(String dataRetirada) {
        this.dataRetirada = dataRetirada;
    }

    public String getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(String dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
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
        return "Controle{" +
                "id=" + id +
                ", dataRetirada='" + dataRetirada + '\'' +
                ", dataDevolucao='" + dataDevolucao + '\'' +
                ", equipamento=" + equipamento +
                ", usuario=" + usuario +
                ", departamento=" + departamento +
                ", status=" + status +
                '}';
    }
}
