package com.example.raphael.projeto_tcc.linhas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.raphael.projeto_tcc.MapsActivity;
import com.example.raphael.projeto_tcc.R;
import com.example.raphael.projeto_tcc.onibus.ConsumirJsonTabelaOnibus;
import com.example.raphael.projeto_tcc.onibus.Onibus;
import com.example.raphael.projeto_tcc.tabelaHorario.ConsumirJsonTabelaHorario;

public class LinhasActivity extends Activity {


    private String CATEGORIA_SERVICO;

    private String COD;

    private String SOMENTE_CARTAO;

    private String NOME;

    TextView txtCATEGORIA_SERVICO;
    TextView txtCOD;
    TextView txtSOMENTE_CARTAO;
    TextView txtNOME;
    EditText campoTexto;

    private Linhas linha;
    private Onibus onibus;


    LinhasActivity(View v){
        txtCATEGORIA_SERVICO = (TextView) findViewById(R.id.txtCATEGORIA_SERVICO);
        txtCOD = (TextView) findViewById(R.id.txtCOD);
        txtNOME = (TextView) findViewById(R.id.txtNOME);
        txtSOMENTE_CARTAO = (TextView) findViewById(R.id.txtSOMENTE_CARTAO);
    }

    public LinhasActivity() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linhas);


        linha = (Linhas) getIntent().getSerializableExtra("linha");

        txtCATEGORIA_SERVICO = (TextView) findViewById(R.id.txtCATEGORIA_SERVICO);
        txtCOD = (TextView) findViewById(R.id.txtCOD);
        txtNOME = (TextView) findViewById(R.id.txtNOME);
        txtSOMENTE_CARTAO = (TextView) findViewById(R.id.txtSOMENTE_CARTAO);

        txtCATEGORIA_SERVICO.setText(linha.getCATEGORIA_SERVICO());
        txtCOD.setText(linha.getCOD());
        txtNOME.setText(linha.getNOME());
        txtSOMENTE_CARTAO.setText(linha.getSOMENTE_CARTAO());
    }

    public void startHorarioLinhas(final View view) {

        Button btnHorario = (Button) view.findViewById(R.id.btnHorario);
        final EditText campoTexto = (EditText) findViewById(R.id.editCodLinha);
        // String codLinha = campoTexto.getText().toString(

        btnHorario.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                Intent intent = new Intent(LinhasActivity.this, ConsumirJsonTabelaHorario.class);
                //Passar valor por parametro
                Bundle params = new Bundle();

                String codLinha = linha.getCOD();

                Log.i("Codigo da linha para retornar: ",
                        "Linhaaaaa--->>=" + codLinha);

                params.putString("mensagem", codLinha);
                intent.putExtras(params);

                startActivity(intent);


            }
        });
    }

    public void startCheckIn(final View view) {

        Button buttonCheckIn = (Button) view.findViewById(R.id.buttonCheckIn);

        buttonCheckIn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent=new Intent(LinhasActivity.this, MapsActivity.class);

                Bundle params = new Bundle();

                String codLinha = linha.getCOD();

                params.putString("linhaCodigoMapa", codLinha);
                intent.putExtras(params);

                startActivity(intent);


            }
        });
    }

    public void todosOnibus(final View view) {

        Button buttonOnibus = (Button) view.findViewById(R.id.buttonOnibus);

        buttonOnibus.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent=new Intent(LinhasActivity.this, ConsumirJsonTabelaOnibus.class);

                Bundle params = new Bundle();

                String codLinha = linha.getCOD();

//                String prefixo = onibus.getPREFIXO();
//                String latitude = onibus.getLAT();
//                String longitude = onibus.getLON();
//                String hora = onibus.getHORA();
//
                params.putString("prefixoOnibus", codLinha);
//                params.putString("latOnibus", latitude);
//                params.putString("lonOnibus", longitude);
//                params.putString("horaOnibus", hora);
                intent.putExtras(params);

                startActivity(intent);


            }
        });
    }


}

