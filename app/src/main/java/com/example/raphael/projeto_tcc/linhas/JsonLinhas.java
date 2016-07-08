package com.example.raphael.projeto_tcc.linhas;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by raphael on 08/10/15.
 */
public class JsonLinhas extends FragmentActivity {
    //  private ListView list;
    // private List<Linhas> lista = new ArrayList<>();
    private List<JSONObject> listas;
    private static final String SERVICE_URL = "http://transporteservico.urbs.curitiba.pr.gov.br/getLinhas.php?c=3525b";
    private static final String LOG_TAG = "TCC";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        setContentView(R.layout.activity_teste);
//
//        list = (ListView) findViewById(R.id.list);
//        list.setOnItemClickListener(new OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> adapter, View view, int pos, long id) {
//
//                Intent i = new Intent(JsonLinhas.this, LinhasActivity.class);
//                startActivity(i);
//            }
//        });

        new DownloadJsonAsyncTask()
                .execute("http://transporteservico.urbs.curitiba.pr.gov.br/getLinhas.php?c=3525b");
    }



    //  @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        //  super.onListItemClick(l, v, position, id);

        Linhas linha = (Linhas) l.getAdapter().getItem(position);

        Intent intent = new Intent(this, LinhasActivity.class);
        intent.putExtra("linha", (Serializable) linha);
        startActivity(intent);
    }

    class DownloadJsonAsyncTask extends AsyncTask<String, Void, List<Linhas>> {
        ProgressDialog dialog;
        List<Linhas> linhas = new ArrayList<Linhas>();

        //Exibe pop-up indicando que está sendo feito o download do JSON
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = ProgressDialog.show(JsonLinhas.this, "Aguarde",
                    "Fazendo download do JSON");
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
                        JsonLinhas.this,
                        // mapsActivity.customAddMarker(new objetoPessoa.getLat(),objetoPessoa.getLon(),"teste");
                        android.R.layout.simple_list_item_1, result);
                try {
                    JsonLinhas.this.teste2(result);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                /**   try {
                 teste2(JsonLinhas.this.toString());
                 } catch (JSONException e) {
                 // switch (Log.e("Error processing JSON")) {
                 // }
                 }**/

                //   list = (ListView) findViewById (R.id.list);
            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(
                        JsonLinhas.this)
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
                    Log.i("Linha ENCONTRADA: ",
                            "NOME=" + linha.getString("NOME"));

                    Linhas objetoLinhas = new Linhas();
                    objetoLinhas.setNOME(linha.getString("NOME"));
                    objetoLinhas.setCATEGORIA_SERVICO(linha.getString("CATEGORIA_SERVICO"));
                    objetoLinhas.setCOD(linha.getString("COD"));
                    objetoLinhas.setSOMENTE_CARTAO(linha.getString("SOMENTE_CARTAO"));
                    linhas.add(objetoLinhas);
                }

            } catch (JSONException e) {
                Log.e("Erro", "Erro no parsing do JSON", e);
            }
            return linhas;
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




    private void teste2(List<Linhas> linhas) throws JSONException {
        //List<Linhas> linhas = new ArrayList<Linhas>();

       // Linhas l = (Linhas) linhas;

//        JSONArray jsonArray = new JSONArray(linhas);
//        JSONObject linha;
        for (Linhas l: linhas) {

            Log.i("Linha---------- ENCONTRADA: ",
                    "NOME=" + l.getNOME());

//            JSONArray jsonObj = jsonArray.getJSONArray(i);
            //   JSONObject jsonObj = jsonArray.toJSONObject(jsonArray);

//            Linhas objetoLinhas = new Linhas();
//            objetoLinhas.setNOME(jsonObj.getString((Integer.parseInt("NOME"))));
//            objetoLinhas.setCATEGORIA_SERVICO(jsonObj.getString((Integer.parseInt("CATEGORIA_SERVICO"))).replace(",", "."));
//            objetoLinhas.setCOD(jsonObj.getString((Integer.parseInt("COD"))).replace(",", "."));
//            objetoLinhas.setSOMENTE_CARTAO(jsonObj.getString((Integer.parseInt("SOMENTE_CARTAO"))).replace(",", "."));
//            linhas.add(objetoLinhas);


          //  setContentView(R.layout.activity_linhas);
        //    ListView listView = (ListView) findViewById(R.id.listView);
            //String[] l = new String[] { objetoLinhas.setNOME(linha.getString("NOME")), objetoLinhas.setCATEGORIA_SERVICO(linha.getString("CATEGORIA_SERVICO")), objetoLinhas.setCOD(linha.getString("COD")), objetoLinhas.setSOMENTE_CARTAO(linha.getString("SOMENTE_CARTAO")) };
            ArrayAdapter<Linhas> adapter = new ArrayAdapter<Linhas>(this, android.R.layout.simple_list_item_1, linhas  );

//            listView.setAdapter(adapter);

//            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

//                @Override
//                public void onItemClick(AdapterView<?> adapter, View view, int pos, long id) {

                  Intent i = new Intent(JsonLinhas.this, LinhasActivity.class);
             startActivity(i);


                }
          //  });


        }
 //   }
    private List<Linhas> gerarZombies() {
        List<Linhas> linhas = new ArrayList<Linhas>();


        return linhas;
    }

    private Linhas criarLinhas(String NOME, String CATEGORIA_SERVICO, String COD, String SOMENTE_CARTAO) {
        Linhas linha = new Linhas();
        return linha;
    }

    protected void retrieveAndAddLinhas() throws IOException {
        HttpURLConnection conn = null;
        final StringBuilder json = new StringBuilder();
        try {
            // conectra com o web service
            URL url = new URL(SERVICE_URL);
            conn = (HttpURLConnection) url.openConnection();
            InputStreamReader in = new InputStreamReader(conn.getInputStream());

            // Le os dados JSON para o StringBuilder
            int read;
            char[] buff = new char[1024];
            while ((read = in.read(buff)) != -1) {
                json.append(buff, 0, read);
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error connecting to service", e);
            throw new IOException("Error connecting to service", e);
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
// Cria os marcadores.
//        runOnUiThread(new Runnable() {
//            public void run() {
//                try {
//                    teste2(json.toString());
//                } catch (JSONException s) {
//                    Log.e(LOG_TAG, "Error processing JSON", );
//                }
//            }
//
//            private void teste2(String s) {
//            }
//        });
//    }
}}
