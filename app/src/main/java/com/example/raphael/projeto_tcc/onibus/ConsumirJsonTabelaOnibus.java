package com.example.raphael.projeto_tcc.onibus;

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
public class ConsumirJsonTabelaOnibus extends ListActivity {

    private Onibus onibus;

    public String codLinha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        codLinha = this.getIntent().getStringExtra("prefixoOnibus");


        new DownloadJsonAsyncTask()
       // http://transporteservico.urbs.curitiba.pr.gov.br/getVeiculosLinha.php?linha=666&c=3525b
        .execute("http://transporteservico.urbs.curitiba.pr.gov.br/getVeiculosLinha.php?linha=" + codLinha + "&c=3525b");
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

        Onibus onibus = (Onibus) l.getAdapter().getItem(position);

        Intent intent = new Intent(this, InformacoesOnibusActivity.class);
        intent.putExtra("onibus", onibus.toString());

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


    class DownloadJsonAsyncTask extends AsyncTask<String, Void, List<Onibus>> {
        ProgressDialog dialog;

        //Exibe pop-up indicando que está sendo feito o download do JSON
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = ProgressDialog.show(ConsumirJsonTabelaOnibus.this, "Aguarde",
                    "Fazendo download do JSON");
        }

        //Acessa o serviço do JSON e retorna a lista de pessoas
        @Override
        protected List<Onibus> doInBackground(String... params) {
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
                    List<Onibus> todosOnibus = getOnibus(json);
                    return todosOnibus;
                }
            } catch (Exception e) {
                Log.e("Erro", "Falha ao acessar Web service", e);
            }
            return null;
        }


        //Depois de executada a chamada do serviço
        @Override
        protected void onPostExecute(List<Onibus> result) {
            super.onPostExecute(result);
            dialog.dismiss();
            if (result.size() > 0) {
                ArrayAdapter<Onibus> adapter = new ArrayAdapter<Onibus>(
                        ConsumirJsonTabelaOnibus.this,
                        android.R.layout.simple_list_item_1, result);

                setContentView(R.layout.informacoes_tabela_horario);
                setListAdapter(adapter);


            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(
                        ConsumirJsonTabelaOnibus.this)
                        .setTitle("Erro")
                        .setMessage("Não foi possível acessar as informações!!")
                        .setPositiveButton("OK", null);
                builder.create().show();
            }
        }

        //Retorna uma lista de onibus com as informações retornadas do JSON
        public List<Onibus> getOnibus(String jsonString) {
            List<Onibus> todosOnibus = new ArrayList<Onibus>();
            try {
                JSONArray onibusJson = new JSONArray(jsonString);
                JSONObject onibus;

                for (int i = 0; i < onibusJson.length(); i++) {
                    onibus = new JSONObject(onibusJson.getString(i));
                    Log.i("Onibus encontrado: ",
                            "HORA=" + onibus.getString("HORA"));

                    Onibus objetoOnibus = new Onibus();
                    objetoOnibus.setPREFIXO(onibus.getString("PREFIXO"));
                    objetoOnibus.setLAT(onibus.getString("LAT"));
                    objetoOnibus.setLON(onibus.getString("LON"));
                    objetoOnibus.setHORA(onibus.getString("HORA"));
                    todosOnibus.add(objetoOnibus);
                }


            } catch (JSONException e) {
                Log.e("Erro", "Erro no parsing do JSON", e);
            }


                return todosOnibus;
            }


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

    public void PegaCodLinha(){


    }
}


