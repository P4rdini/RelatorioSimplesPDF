package com.example.RelatorioPDF.model;

import java.time.LocalDateTime;

public class Ocorrencia {


    private String cliente;
    private String solicitante;
    private String tipoContato;
    private LocalDateTime horaOcorrencia;
    private String descrição;

    private LocalDateTime horaInicial;
    private LocalDateTime horaFinal;
    private long kmInicial;
    private long kmFinal;



    public Ocorrencia(String cliente, String solicitante, String tipoContato, LocalDateTime horaOcorrencia, String descrição, LocalDateTime horaInicial, LocalDateTime horaFinal, long kmInicial, long kmFinal) {
        this.cliente = cliente;
        this.solicitante = solicitante;
        this.tipoContato = tipoContato;
        this.horaOcorrencia = horaOcorrencia;
        this.descrição = descrição;
        this.horaInicial = horaInicial;
        this.horaFinal = horaFinal;
        this.kmInicial = kmInicial;
        this.kmFinal = kmFinal;
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

    public String getDescrição() {
        return descrição;
    }

    public void setDescrição(String descrição) {
        this.descrição = descrição;
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
}
