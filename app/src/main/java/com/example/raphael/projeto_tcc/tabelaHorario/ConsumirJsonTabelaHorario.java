package com.example.raphael.projeto_tcc.tabelaHorario;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.raphael.projeto_tcc.R;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by raphael on 13/03/2016.
 */
public class ConsumirJsonTabelaHorario extends ListActivity {

    private TabelaHorario tabelaHorario;

    public String codLinha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        codLinha = this.getIntent().getStringExtra("mensagem");


        new DownloadJsonAsyncTask()
                .execute("http://transporteservico.urbs.curitiba.pr.gov.br/getTabelaLinha.php?linha="+codLinha+"&c=3525b");
      //  http://transporteservico.urbs.curitiba.pr.gov.br/getTabelaLinha.php?linha=666&c=3525b


//        Intent intent = getIntent();
//
//        Bundle params = intent.getExtras();
//
//        if(params!=null)
//        {
//            codLinha = params.getString("mensagem");
////            textView.setText(mostraTexto);
////            setContentView(textView);
//        }
    }





    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        TabelaHorario tabelaHorario = (TabelaHorario) l.getAdapter().getItem(position);

        Intent intent = new Intent(this, InformacoesTabelaHorarioActivity.class);
        intent.putExtra("tabelaHorario", tabelaHorario);

//        Bundle params = intent.getExtras();
//
//        if(params!=null)
//        {
//            codLinha = params.getString("mensagem");
////            textView.setText(mostraTexto);
////            setContentView(textView);
//        }

        startActivity(intent);
    }


    class DownloadJsonAsyncTask extends AsyncTask<String, Void, List<TabelaHorario>> {
        ProgressDialog dialog;

        //Exibe pop-up indicando que está sendo feito o download do JSON
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = ProgressDialog.show(ConsumirJsonTabelaHorario.this, "Aguarde",
                    "Fazendo download do JSON");
        }

        //Acessa o serviço do JSON e retorna a lista de pessoas
        @Override
        protected List<TabelaHorario> doInBackground(String... params) {
            String urlString = params[0];
            HttpClient httpclient = new DefaultHttpClient();
            HttpGet httpget = new HttpGet(urlString);
            try {
                HttpResponse response = httpclient.execute(httpget);
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    InputStream instream = entity.getContent();
                    String json = getStringFromInputStream(instream);
                    instream.close();
                    List<TabelaHorario> tabelaHorarios = getTabelaHorarios(json);
                    return tabelaHorarios;
                }
            } catch (Exception e) {
                Log.e("Erro", "Falha ao acessar Web service", e);
            }
            return null;
        }


        //Depois de executada a chamada do serviço
        @Override
        protected void onPostExecute(List<TabelaHorario> result) {
            super.onPostExecute(result);
            dialog.dismiss();
            if (result.size() > 0) {
                ArrayAdapter<TabelaHorario> adapter = new ArrayAdapter<TabelaHorario>(
                        ConsumirJsonTabelaHorario.this,
                        android.R.layout.simple_list_item_1, result);

                setContentView(R.layout.informacoes_tabela_horario);
                setListAdapter(adapter);


            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(
                        ConsumirJsonTabelaHorario.this)
                        .setTitle("Erro")
                        .setMessage("Não foi possível acessar as informações!!")
                        .setPositiveButton("OK", null);
                builder.create().show();
            }
        }

        //Retorna uma lista de pessoas com as informações retornadas do JSON
        private List<TabelaHorario> getTabelaHorarios(String jsonString) {
            List<TabelaHorario> tabelaHorarios = new ArrayList<TabelaHorario>();
            try {
                JSONArray tabelaHorarioJson = new JSONArray(jsonString);
                JSONObject tabelaHorario;

                for (int i = 0; i < tabelaHorarioJson.length(); i++) {
                    tabelaHorario = new JSONObject(tabelaHorarioJson.getString(i));
                    Log.i("TABELA ENCONTRADA: ",
                            "HORA=" + tabelaHorario.getString("HORA"));

                    TabelaHorario objetoTabelaHorario = new TabelaHorario();
                    objetoTabelaHorario.setPONTO(tabelaHorario.getString("PONTO"));
                    objetoTabelaHorario.setNUM(tabelaHorario.getString("NUM"));
                    objetoTabelaHorario.setHORA(tabelaHorario.getString("HORA"));
                    tabelaHorarios.add(objetoTabelaHorario);
                }

            } catch (JSONException e) {
                Log.e("Erro", "Erro no parsing do JSON", e);
            }
            return tabelaHorarios;
        }


        //Converte objeto InputStream para String
        private String getStringFromInputStream(InputStream is) {

            BufferedReader br = null;
            StringBuilder sb = new StringBuilder();

            String line;
            try {

                br = new BufferedReader(new InputStreamReader(is));
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            return sb.toString();

        }

    }

    public void PegaCodLinha(){



    }
}


