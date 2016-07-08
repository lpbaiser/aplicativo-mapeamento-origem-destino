package com.example.raphael.projeto_tcc.bancoDados;

/**
 * Created by raphael on 04/06/2016.
 */
public class EmbarqueDesembarqueClasse {


    private long id;
    String embarque_desembarque;
    String meio_transporte;

    public EmbarqueDesembarqueClasse()
    {

    }
    public EmbarqueDesembarqueClasse(long id, String embarque_desembarque, String meio_transporte) {
        this.id = id;
        this.embarque_desembarque = embarque_desembarque;
        this.meio_transporte = meio_transporte;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmbarque_desembarque() {
        return embarque_desembarque;
    }

    public void setEmbarque_desembarque(String embarque_desembarque) {
        this.embarque_desembarque = embarque_desembarque;
    }

    public String getMeio_transporte() {
        return meio_transporte;
    }

    public void setMeio_transporte(String meio_transporte) {
        this.meio_transporte = meio_transporte;
    }
}
