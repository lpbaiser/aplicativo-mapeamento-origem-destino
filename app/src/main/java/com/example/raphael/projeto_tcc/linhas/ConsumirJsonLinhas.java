package com.example.raphael.projeto_tcc.linhas;

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


    public class ConsumirJsonLinhas extends ListActivity {

    ListView list;
    LinhasAdapter linhasAdapter;
    ArrayList<Linhas> linhasArrayList;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            setContentView(R.layout.informacoes_linhas_activity);
            list = (ListView) findViewById(R.id.list);
            linhasArrayList = new ArrayList<Linhas>();

            new DownloadJsonAsyncTask()
                    .execute("http://transporteservico.urbs.curitiba.pr.gov.br/getLinhas.php?c=3525b");

        }

        @Override
        protected void onListItemClick(ListView l, View v, int position, long id) {
            super.onListItemClick(l, v, position, id);

            Linhas linha = (Linhas) l.getAdapter().getItem(position);

            Intent intent = new Intent(this, LinhasActivity.class);
            intent.putExtra("linha",  linha);
            startActivity(intent);
        }

        class DownloadJsonAsyncTask extends AsyncTask<String, Void, List<Linhas>> {
            ProgressDialog dialog;

            //Exibe pop-up indicando que está sendo feito o download do JSON
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                dialog = ProgressDialog.show(ConsumirJsonLinhas.this, "Aguarde",
                        "Fazendo download dos dados");
            }

            //Acessa o serviço do JSON e retorna a lista de pessoas
            @Override
            protected List<Linhas> doInBackground(String... params) {
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
                        List<Linhas> linhas = getLinhas(json);
                        return linhas;

                    }
                } catch (Exception e) {
                    Log.e("Erro", "Falha ao acessar Web service", e);
                }
                return null;
            }

            //Depois de executada a chamada do serviço
            @Override
            protected void onPostExecute(List<Linhas> result) {
                super.onPostExecute(result);
                dialog.dismiss();
                if (result.size() > 0) {
                    ArrayAdapter<Linhas> adapter = new ArrayAdapter<Linhas>(
                            ConsumirJsonLinhas.this,
                            android.R.layout.simple_list_item_1,
                            result);


                  // setContentView(R.layout.informacoes_linhas_activity);
//                  LinhasAdapter linhasAdapter = new LinhasAdapter(adapter,null);
                   setListAdapter(adapter);
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(
                            ConsumirJsonLinhas.this)
                            .setTitle("Erro")
                            .setMessage("Não foi possível acessar as informações!!")
                            .setPositiveButton("OK", null);
                    builder.create().show();
                }
            }
            //Retorna uma lista de pessoas com as informações retornadas do JSON
            private List<Linhas> getLinhas(String jsonString) {
                List<Linhas> linhas = new ArrayList<Linhas>();
                try {
                    JSONArray linhasJson = new JSONArray(jsonString);
                    JSONObject linha;

                    for (int i = 0; i < linhasJson.length(); i++) {
                        linha = new JSONObject(linhasJson.getString(i));
                        Log.i("LINHA ENCONTRADA: ",
                                "CATEGORIA=" + linha.getString("CATEGORIA_SERVICO"));

                        Linhas objetoLinhas = new Linhas();
                        objetoLinhas.setCOD(linha.getString("COD"));
                        objetoLinhas.setNOME(linha.getString("NOME"));
                        objetoLinhas.setCATEGORIA_SERVICO(linha.getString("CATEGORIA_SERVICO"));
                        objetoLinhas.setSOMENTE_CARTAO(linha.getString("SOMENTE_CARTAO"));
                        linhas.add(objetoLinhas);


                    }

                } catch (JSONException e) {
                    Log.e("Erro", "Erro no parsing do JSON", e);
                }
                return linhas;
            }


            //Converte objeto In;putStream para String
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
}


