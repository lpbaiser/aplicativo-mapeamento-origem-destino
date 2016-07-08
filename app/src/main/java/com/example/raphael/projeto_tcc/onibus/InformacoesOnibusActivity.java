package com.example.raphael.projeto_tcc.onibus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.raphael.projeto_tcc.MapsActivity;
import com.example.raphael.projeto_tcc.R;

/**
 * Created by raphael on 13/03/2016.
 */
public class InformacoesOnibusActivity extends Activity {

    private TextView txtPREFIXO;
    private TextView txtLAT_BUS;
    private TextView txtLON_BUS;
    private TextView txtHORA_ONIBUS;
    private Onibus onibus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabela_horario);

        onibus = (Onibus) getIntent().getSerializableExtra("onibus");

        txtPREFIXO = (TextView) findViewById(R.id.txtPREFIXO);
        txtLAT_BUS = (TextView) findViewById(R.id.txtLAT_BUS);
        txtLON_BUS = (TextView) findViewById(R.id.txtNum);
        txtHORA_ONIBUS = (TextView) findViewById(R.id.txtHORA_ONIBUS);

        txtPREFIXO.setText(onibus.getPREFIXO());
        txtLAT_BUS.setText(onibus.getLAT());
        txtLON_BUS.setText(onibus.getLON());
        txtHORA_ONIBUS.setText(onibus.getHORA());
    }

    public void todosOnibus(final View view) {

        Button buttonOnibus = (Button) view.findViewById(R.id.buttonOnibus);

        buttonOnibus.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent=new Intent(InformacoesOnibusActivity.this, MapsActivity.class);

                Bundle params = new Bundle();

                String prefixo = onibus.getPREFIXO();
                String latitude = onibus.getLAT();
                String longitude = onibus.getLON();
                String hora = onibus.getHORA();

                params.putString("prefixoOnibus", prefixo);
                params.putString("latOnibus", latitude);
                params.putString("lonOnibus", longitude);
                params.putString("horaOnibus", hora);
                intent.putExtras(params);

                startActivity(intent);


            }
        });
    }



}
