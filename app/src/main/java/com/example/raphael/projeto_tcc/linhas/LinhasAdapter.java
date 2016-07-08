package com.example.raphael.projeto_tcc.linhas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.raphael.projeto_tcc.R;

import java.util.ArrayList;

/**
 * Created by raphael on 12/10/15.
 */
public class LinhasAdapter extends ArrayAdapter<Linhas> {

    private Context context;
    private ArrayList<Linhas> LinhasArrayList;
    int Resource;

    public LinhasAdapter(Context context,int resource, ArrayList<Linhas> linhas){
        super(context,resource,linhas);
        LinhasArrayList = linhas;
        Resource = resource;
        this.context = context;

    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Linhas itemPosicao = this.LinhasArrayList.get(position);
        convertView = LayoutInflater.from(this.context).inflate(R.layout.activity_linhas, null);

        TextView textViewSomenteCartao = (TextView) convertView.findViewById(R.id.txtSOMENTE_CARTAO);
        textViewSomenteCartao.setText(itemPosicao.getCATEGORIA_SERVICO());

        TextView textViewCategoria = (TextView) convertView.findViewById(R.id.txtCATEGORIA_SERVICO);
        textViewSomenteCartao.setText(itemPosicao.getCATEGORIA_SERVICO());

        TextView textViewNome = (TextView) convertView.findViewById(R.id.txtNOME);
        textViewNome.setText(itemPosicao.getNOME());

        TextView textViewCod = (TextView) convertView.findViewById(R.id.txtCOD);
        textViewCod.setText(itemPosicao.getCOD());

        return convertView;

    }
}


