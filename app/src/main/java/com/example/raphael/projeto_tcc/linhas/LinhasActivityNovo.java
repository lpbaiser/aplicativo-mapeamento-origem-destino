package com.example.raphael.projeto_tcc.linhas;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.raphael.projeto_tcc.R;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by raphael on 01/04/2016.
 */
public class LinhasActivityNovo extends Activity {

    ListView listView;
    LinhasAdapterNovo adapter;
    ArrayList<Linhas> linhasArrayList;
    DBHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listview);
        handler = new DBHandler(this);
        NetworkUtils utils = new NetworkUtils(LinhasActivityNovo.this);
        if(handler.getLinhasCount() == 0 && utils.isConnectingToInternet())
        {
            new DataFetcherTask().execute();
        }
        else
        {
            ArrayList<Linhas> linhasList = handler.getAllLinhas();
            adapter = new LinhasAdapterNovo(LinhasActivityNovo.this,linhasList);
            listView.setAdapter(adapter);
        }
    }

    class DataFetcherTask extends AsyncTask<Void,Void,Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            String serverData = null;// String object to store fetched data from server
// Http Request Code start
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet("http://transporteservico.urbs.curitiba.pr.gov.br/getLinhas.php?c=3525b");
            try {
                HttpResponse httpResponse = httpClient.execute(httpGet);
                HttpEntity httpEntity = httpResponse.getEntity();
                serverData = EntityUtils.toString(httpEntity);
                Log.d("response", serverData);
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
// Http Request Code end
// Json Parsing Code Start
            try {
                linhasArrayList = new ArrayList<Linhas>();
                JSONObject jsonObject = new JSONObject(serverData);
                JSONArray jsonArray = jsonObject.getJSONArray("LINHAS");
                for (int i=0;i<jsonArray.length();i++)
                {
                    JSONObject jsonObjectLinhas = jsonArray.getJSONObject(i);
                    String linhasCOD = jsonObjectLinhas.getString("COD");
                    String linhasNOME = jsonObjectLinhas.getString("NOME");
                    String linhasCATEGORIA_SERVICO = jsonObjectLinhas.getString("CATEGORIA_SERVICO");
                    String linhasSOMENTE_CARTAO = jsonObjectLinhas.getString("SOMENTE_CARTAO");
                    Linhas linhas = new Linhas();
                    linhas.setCOD(linhasCOD);
                    linhas.setNOME(linhasNOME);
                    linhas.setCATEGORIA_SERVICO(linhasCATEGORIA_SERVICO);
                    linhas.setSOMENTE_CARTAO(linhasSOMENTE_CARTAO);
                    handler.addLinhas(linhas);// Inserting into DB
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
//Json Parsing code end
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            ArrayList<Linhas> linhasList = handler.getAllLinhas();
            adapter = new LinhasAdapterNovo(LinhasActivityNovo.this,linhasList);
            listView.setAdapter(adapter);
        }
    }

//
}


