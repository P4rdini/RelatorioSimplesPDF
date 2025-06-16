package com.example.RelatorioPDF.model;

import java.time.LocalDateTime;

public class Ocorrencia {


    private String cliente;
    private String solicitante;
    private String tipoContato;
    private String tipoAcionamento;
    private LocalDateTime horaOcorrencia;
    private String descricao;
    private LocalDateTime horaInicial;
    private LocalDateTime horaFinal;
    private long kmInicial;
    private long kmFinal;
    private Veiculo cavalo;
    private Veiculo carreta;
    private int qtdAgente;



    public Ocorrencia(String cliente,Veiculo cavalo,Veiculo carreta, String solicitante, String tipoContato,String tipoAcionamento,LocalDateTime horaOcorrencia, String descricao, LocalDateTime horaInicial, LocalDateTime horaFinal, long kmInicial, long kmFinal) {
        this.cliente = cliente;
        this.cavalo=cavalo;
        this.carreta=carreta;
        this.solicitante = solicitante;
        this.tipoContato = tipoContato;
        this.tipoAcionamento = tipoAcionamento;
        this.horaOcorrencia = horaOcorrencia;
        this.descricao = descricao;
        this.horaInicial = horaInicial;
        this.horaFinal = horaFinal;
        this.kmInicial = kmInicial;
        this.kmFinal = kmFinal;
    }

    public int getQtdAgente() {
        return qtdAgente;
    }

    public void setQtdAgente(int qtdAgente) {
        this.qtdAgente = qtdAgente;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Veiculo getCavalo() {
        return cavalo;
    }

    public void setCavalo(Veiculo cavalo) {
        this.cavalo = cavalo;
    }

    public Veiculo getCarreta() {
        return carreta;
    }

    public void setCarreta(Veiculo carreta) {
        this.carreta = carreta;
    }

    public String getTipoAcionamento() {
        return tipoAcionamento;
    }

    public void setTipoAcionamento(String tipoAcionamento) {
        this.tipoAcionamento = tipoAcionamento;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(String solicitante) {
        this.solicitante = solicitante;
    }

    public String getTipoContato() {
        return tipoContato;
    }

    public void setTipoContato(String tipoContato) {
        this.tipoContato = tipoContato;
    }

    public LocalDateTime getHoraOcorrencia() {
        return horaOcorrencia;
    }

    public void setHoraOcorrencia(LocalDateTime horaOcorrencia) {
        this.horaOcorrencia = horaOcorrencia;
    }

    public LocalDateTime getHoraInicial() {
        return horaInicial;
    }

    public void setHoraInicial(LocalDateTime horaInicial) {
        this.horaInicial = horaInicial;
    }

    public LocalDateTime getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(LocalDateTime horaFinal) {
        this.horaFinal = horaFinal;
    }

    public long getKmInicial() {
        return kmInicial;
    }

    public void setKmInicial(long kmInicial) {
        this.kmInicial = kmInicial;
    }

    public long getKmFinal() {
        return kmFinal;
    }

    public void setKmFinal(long kmFinal) {
        this.kmFinal = kmFinal;
    }

    @Override
    public String toString() {
        return "Ocorrencia{" +
                "cliente='" + cliente + '\'' +
                ", solicitante='" + solicitante + '\'' +
                ", tipoContato='" + tipoContato + '\'' +
                ", tipoAcionamento='" + tipoAcionamento + '\'' +
                ", horaOcorrencia=" + horaOcorrencia +
                ", descricao='" + descricao + '\'' +
                ", horaInicial=" + horaInicial +
                ", horaFinal=" + horaFinal +
                ", kmInicial=" + kmInicial +
                ", kmFinal=" + kmFinal +
                ", cavalo=" + cavalo.toString() +
                ", carreta=" + carreta.toString() +
                '}';
    }
}
