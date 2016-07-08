package com.example.raphael.projeto_tcc.bancoDados;

/**
 * Created by raphael on 06/03/2016.
 */
public class OringemDestino {

    private long id;
    private String descricaoOrigem;
    private String descricaoDestino;
    private String latOrigem;
    private String lonOrigem;
    private String latDestino;
    private String lonDestino;
    private String origem;
    private String destino;



    public OringemDestino(int id, String descricaoOrigem, String descricaoDestino,
                           String latOrigem, String lonOrigem,
                           String latDestino, String lonDestino) {
        this.id = id;
        this.descricaoOrigem = descricaoOrigem;
        this.descricaoDestino = descricaoDestino;
        this.latOrigem = latOrigem;
        this.lonOrigem = lonOrigem;
        this.latDestino = latDestino;
        this.lonDestino = lonDestino;


    }

    public OringemDestino() {

    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescricaoOrigem() {
        return descricaoOrigem;
    }

    public void setDescricaoOrigem(String descricaoOrigem) {
        this.descricaoOrigem = descricaoOrigem;
    }

    public String getDescricaoDestino() {
        return descricaoDestino;
    }

    public void setDescricaoDestino(String descricaoDestino) {
        this.descricaoDestino = descricaoDestino;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLatOrigem() {
        return latOrigem;
    }

    public void setLatOrigem(String latOrigem) {
        this.latOrigem = latOrigem;
    }

    public String getLonOrigem() {
        return lonOrigem;
    }

    public void setLonOrigem(String lonOrigem) {
        this.lonOrigem = lonOrigem;
    }

    public String getLatDestino() {
        return latDestino;
    }

    public void setLatDestino(String latDestino) {
        this.latDestino = latDestino;
    }

    public String getLonDestino() {
        return lonDestino;
    }

    public void setLonDestino(String lonDestino) {
        this.lonDestino = lonDestino;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }



    @Override
    public String toString() {
        return "OringemDestino{" +
                "id=" + id +
                ", Descricao Origem='" + descricaoOrigem + '\'' +
                ", Descricao Destino ='" + descricaoDestino + '\'' +
                ", Latitude Origem ='" + latOrigem + '\'' +
                ", Longitude Origem ='" + lonOrigem + '\'' +
                ", Latitude Destino ='" + latDestino + '\'' +
                ", Longitude Destino ='" + lonDestino + '\'' +
                '}';
    }
}
