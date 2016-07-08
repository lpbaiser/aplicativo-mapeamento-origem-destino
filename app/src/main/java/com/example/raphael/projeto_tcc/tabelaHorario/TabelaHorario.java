package com.example.raphael.projeto_tcc.tabelaHorario;

import java.io.Serializable;

/**
 * Created by raphael on 30/09/15.
 */
public class TabelaHorario implements Serializable {

    private static final long serialVersionUID = 1L;
    private String HORA;
    private String PONTO;
    private String NUM;
    public String codLinha;

    public enum dia{

        diaUtil(1),
        sabado(2),
        domingo(3),
        feriado(4);

        private int value;

        private dia(int value) {
            this.value = value;

        }

        public int getValue(){
            return this.value;
        }
    };

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getHORA() {
        return HORA;
    }

    public void setHORA(String HORA) {
        this.HORA = HORA;
    }

    public String getPONTO() {
        return PONTO;
    }

    public void setPONTO(String PONTO) {
        this.PONTO = PONTO;
    }

    public String getNUM() {
        return NUM;
    }

    public void setNUM(String NUM) {
        this.NUM = NUM;
    }

    @Override
    public String toString() {
        return  " NÚMERO DO PONTO: " + NUM + "\r\n" +
                " PONTO: " + PONTO + "\r\n" +
                " HORA: " + HORA;
    }
}
