package com.example.raphael.projeto_tcc.tabelaHorario;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.raphael.projeto_tcc.R;

/**
 * Created by raphael on 13/03/2016.
 */
public class InformacoesTabelaHorarioActivity extends Activity {

    private TextView txtHora;
    private TextView txtPonto;
    private TextView txtNum;
    private TabelaHorario tabelaHorario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabela_horario);


//        TextView textView = new TextView(this);
//        Intent intent = getIntent();

//        Bundle params = intent.getExtras();
//
//        if(params!=null)
//        {
//            String mostraTexto = params.getString("mensagem");
//            textView.setText(mostraTexto);
//            setContentView(textView);
//        }


        tabelaHorario = (TabelaHorario) getIntent().getSerializableExtra("tabelaHorario");

        txtHora = (TextView) findViewById(R.id.txtHora);
        txtPonto = (TextView) findViewById(R.id.txtPonto);
        txtNum = (TextView) findViewById(R.id.txtNum);

        txtHora.setText(tabelaHorario.getHORA());
        txtPonto.setText(tabelaHorario.getPONTO());
        txtNum.setText(tabelaHorario.getNUM());
    }


}
