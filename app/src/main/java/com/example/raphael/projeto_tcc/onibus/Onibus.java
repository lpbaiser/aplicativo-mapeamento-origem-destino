package com.example.raphael.projeto_tcc.onibus;

/**
 * Created by raphael on 25/03/2016.
 */
public class Onibus {

 //   private String LINHA;
    private String PREFIXO;
    private String LAT;
    private String LON;
    private String HORA;
  //  private String ADAP;
  //  private String LINHA;


    public String getPREFIXO() {
        return PREFIXO;
    }

    public void setPREFIXO(String PREFIXO) {
        this.PREFIXO = PREFIXO;
    }

    public String getLAT() {
        return LAT;
    }

    public void setLAT(String LAT) {
        this.LAT = LAT;
    }

    public String getLON() {
        return LON;
    }

    public void setLON(String LON) {
        this.LON = LON;
    }

    public String getHORA() {
        return HORA;
    }

    public void setHORA(String HORA) {
        this.HORA = HORA;
    }

    @Override
    public String toString() {
        return  " Dados do ônibus: "+ "\r\n" +
                " Prefixo: " + PREFIXO + "\r\n" +
                " Latitude: " + LAT + "\r\n" +
                " Logitude: " + LON + "\r\n" +
                " Hora: " + HORA;
    }
}
