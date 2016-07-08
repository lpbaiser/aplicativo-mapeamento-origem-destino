package com.example.raphael.projeto_tcc.linhas;

import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URI;

public class JsonParserLinhas {

    private final String URL =
            "http://transporteservico.urbs.curitiba.pr.gov.br/getLinhas.php?c=3525b";

    private InputStream getJSONData(String url) {
        // faz a requisição e retorna um InputStream
        DefaultHttpClient httpClient = new DefaultHttpClient();
        URI uri;
        InputStream data = null;
        try {
            uri = new URI(url);
            HttpGet method = new HttpGet(uri);
            HttpResponse response = httpClient.execute(method);
            data = response.getEntity().getContent();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public com.example.raphael.projeto_tcc.linhas.Linhas getFeedsPhotosPublic() {

        com.example.raphael.projeto_tcc.linhas.Linhas linhas = null;
        try {
            Reader reader = new InputStreamReader(getJSONData(URL));

            // Faz o parser do JSON para o objeto Flickr
       //     Gson gson = new Gson();

            // Aqui o Gson faz o parse do JSON para o objeto flick
            // Simples assim!
         //   linhas = gson.fromJson(reader, com.example.raphael.projeto_tcc.linhas.Linhas.class);

        } catch (Exception ex) {
            Log.i("LINHAS", ex.getCause().getMessage());
        }
        return linhas;
    }

}
