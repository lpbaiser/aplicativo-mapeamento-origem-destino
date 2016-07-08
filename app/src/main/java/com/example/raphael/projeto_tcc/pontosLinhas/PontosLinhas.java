package com.example.raphael.projeto_tcc.pontosLinhas;

import java.io.Serializable;

/**
 * Created by raphael on 01/10/15.
 */
public class PontosLinhas implements Serializable {

    private String nome;
    private String num;
    private String lat;
    private String lon;
    private String seq;
    private String grupo;
    private String tipo;
    private String sentido;



    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getSentido() {
        return sentido;
    }

    public void setSentido(String sentido) {
        this.sentido = sentido;
    }

    @Override
    public String toString() {
        return "PontosLinhas{" +
                "nome='" + nome + '\'' +
                ", num='" + num + '\'' +
                ", lat='" + lat + '\'' +
                ", lon='" + lon + '\'' +
                ", seq='" + seq + '\'' +
                ", grupo='" + grupo + '\'' +
                ", tipo='" + tipo + '\'' +
                ", sentido='" + sentido + '\'' +
                '}';
    }
}
