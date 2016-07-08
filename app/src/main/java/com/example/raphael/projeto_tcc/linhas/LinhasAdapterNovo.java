package com.example.raphael.projeto_tcc.linhas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.raphael.projeto_tcc.R;

import java.util.ArrayList;

/**
 * Created by raphael on 01/04/2016.
 */
public class LinhasAdapterNovo extends BaseAdapter {

    Context context;
    ArrayList<Linhas> listData;

    public LinhasAdapterNovo(Context context,ArrayList<Linhas> listData){
        this.context = context;
        this.listData = listData;
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    class ViewHolder {
        private TextView textViewCityName;
    }
    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.linhas_item_novo,null);
            viewHolder = new ViewHolder();
            viewHolder.textViewCityName = (TextView) view.findViewById(R.id.txtViewCityName);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }
        Linhas linhas = listData.get(position);
        String linhasNOME = linhas.getNOME();
        viewHolder.textViewCityName.setText(linhasNOME);
        return view;
    }
}
