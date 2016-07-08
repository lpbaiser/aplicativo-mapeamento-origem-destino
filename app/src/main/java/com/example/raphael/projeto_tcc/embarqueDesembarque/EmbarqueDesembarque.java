package com.example.raphael.projeto_tcc.embarqueDesembarque;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.raphael.projeto_tcc.R;
import com.example.raphael.projeto_tcc.bancoDados.EmbarqueDesembarqueClasse;
import com.example.raphael.projeto_tcc.socket.SocketTask;

import java.io.IOException;
import java.text.SimpleDateFormat;

public class EmbarqueDesembarque extends Activity {

    private EmbarqueDesembarqueClasse embarqueDesembarqueClasse = new EmbarqueDesembarqueClasse();
    private SocketTask st;
    public String resultEmbarqueDesembarque;
    public String resultMeioTransporte;
    private AlertDialog alerta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_embarque_desembarque);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        // Banco de Dados
        Intent intent = getIntent();
        if(intent != null){
            Bundle bundle = intent.getExtras();
            if(bundle != null){

                embarqueDesembarqueClasse.setId((int) bundle.getLong("id"));
                embarqueDesembarqueClasse.setEmbarque_desembarque(bundle.getString("embarque_desembarque"));
                embarqueDesembarqueClasse.setMeio_transporte(bundle.getString("meio_transporte"));

            }

        }

        //RadioButton
        final RadioGroup trans = (RadioGroup)findViewById(R.id.meioTransporte);

        trans.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int id) {
                // TODO Auto-generated method stub
                if(id == R.id.checkBike)
                    resultMeioTransporte = "bike";
                if (id == R.id.radioCaminhando)
                    resultMeioTransporte = "caminhando";
                if (id == R.id.radioMoto)
                    resultMeioTransporte = "moto";
                if (id == R.id.radioCarro)
                    resultMeioTransporte = "carro";
                if (id == R.id.radioTaxi)
                    resultMeioTransporte = "taxi";
                if (id == R.id.radioMetro)
                    resultMeioTransporte = "metro";

            }
        });

        //RadioButton
        final RadioGroup embDes = (RadioGroup)findViewById(R.id.embDes);

        embDes.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int id) {
                // TODO Auto-generated method stub
                if(id == R.id.checkEmbarque)
                    resultEmbarqueDesembarque = "embarque";
                if (id == R.id.checkDesembarque)
                    resultEmbarqueDesembarque = "desembarque";
            }
        });

    }

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();
        TextView editText = (TextView) findViewById(R.id.txtEmbarqueDesembarque);
        String mensagem = editText.getText().toString();
        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.checkEmbarque:
                if (checked)
                    editText.setText(editText.getText() + "Até o ponto irei?");
                //  SalvarEmbarque();
                resultEmbarqueDesembarque = "embarque";
                break;
            case R.id.checkDesembarque:
                if (checked)
                    editText.setText(editText.getText() + "Do desembarque até o destino irei?");
                resultEmbarqueDesembarque = "desembarque";
                break;
        }
    }



    public void enviarDadosSocket(View view) throws IOException {
        socketEmbarqueDesembarque();
    }

    private void socketEmbarqueDesembarque() {


        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Pesquisa Origem Destino");
        builder.setMessage("Deseja Contribuir com a Pesquisa Origem e Destino?");

        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                Toast.makeText(EmbarqueDesembarque.this, "Obrigado pela Contribuição ", Toast.LENGTH_SHORT).show();
                final String host = "10.10.86.141";
                final String port = "2016";

                // Instancia a classe de conexão com socket
                st = new SocketTask(host, Integer.parseInt(port), 5000) {
                    @Override
                    protected void onProgressUpdate(String... progress) {
                        SimpleDateFormat sdf = new SimpleDateFormat(
                                "dd/MM/yyyy HH:mm:ss");

                    }

                };

                String salvarSocket = resultEmbarqueDesembarque
                        + "," + resultMeioTransporte;

                st.execute(salvarSocket);

            }
        });
        //define um botão como negativo.
        builder.setNegativeButton("Não", new DialogInterface.OnClickListener()

        {
            public void onClick(DialogInterface arg0, int arg1) {
                Toast.makeText(EmbarqueDesembarque.this, "Ok Obrigado! " , Toast.LENGTH_SHORT).show();
            }
        });
        alerta = builder.create();
        //Exibe
        alerta.show();

      //  onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        st.cancel(true);
    }
}
