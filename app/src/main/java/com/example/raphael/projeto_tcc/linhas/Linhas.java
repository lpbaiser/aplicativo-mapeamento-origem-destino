package com.example.raphael.projeto_tcc.linhas;

import java.io.Serializable;

/**
 * Created by raphael on 06/10/15.
 */
public class Linhas implements Serializable {

        private String CATEGORIA_SERVICO;
        private String COD;
        private String SOMENTE_CARTAO;
        private String NOME;

    public Linhas(String CATEGORIA_SERVICO, String COD, String SOMENTE_CARTAO, String NOME) {

        this.CATEGORIA_SERVICO = CATEGORIA_SERVICO;
        this.COD = COD;
        this.SOMENTE_CARTAO = SOMENTE_CARTAO;
        this.NOME = NOME;
    }

    public Linhas() {

    }


    public String getCATEGORIA_SERVICO ()
        {
            return CATEGORIA_SERVICO;
        }

        public void setCATEGORIA_SERVICO (String CATEGORIA_SERVICO)
        {
            this.CATEGORIA_SERVICO = CATEGORIA_SERVICO;
        }

        public String getCOD ()
        {
            return COD;
        }

        public void setCOD (String COD)
        {
            this.COD = COD;
        }

        public String getSOMENTE_CARTAO ()
        {
            return SOMENTE_CARTAO;
        }

        public void setSOMENTE_CARTAO (String SOMENTE_CARTAO)
        {
            this.SOMENTE_CARTAO = SOMENTE_CARTAO;
        }

        public String getNOME ()
        {
            return NOME;
        }

        public void setNOME (String NOME)
        {
            this.NOME = NOME;
        }

    @Override
    public String toString() {
        return  "Código da Linha: " + COD  + "\r\n" +
                "Nome: " + NOME + "\r\n" +
                "Categoria: " + CATEGORIA_SERVICO  + "\r\n" +
                "Somente Cartão ?? " + SOMENTE_CARTAO;
    }


}
