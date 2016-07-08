package com.example.raphael.projeto_tcc.bancoDados;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.raphael.projeto_tcc.R;

import java.util.List;

public class OrigemDestinoAdapter extends BaseAdapter {
	private Context context;
	private List<OringemDestino> list;
	
	public OrigemDestinoAdapter(Context context, List<OringemDestino> list){
		this.context = context;
		this.list = list;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return list.get(arg0).getId();
	}

	@Override
	public View getView(int position, View arg1, ViewGroup arg2) {
		final int auxPosition = position;
		
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		final LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.usuario, null);



//		TextView tv4 = (TextView) layout.findViewById(R.id.latitudeBd);
//		tv4.setText(list.get(position).getLatOrigem());
//
//		TextView tv = (TextView) layout.findViewById(R.id.longitudeBd);
//		tv.setText(list.get(position).getLonOrigem());
//
//
//		TextView tv5 = (TextView) layout.findViewById(R.id.latitudeBdOrigem);
//		tv5.setText(list.get(position).getLatDestino());
//
//		TextView tv1 = (TextView) layout.findViewById(R.id.longitudeBdDestino);
//		tv1.setText(list.get(position).getLonDestino());


		TextView tv2 = (TextView) layout.findViewById(R.id.origin);
		tv2.setText(list.get(position).getDescricaoOrigem());

		TextView tv3 = (TextView) layout.findViewById(R.id.destination);
		tv3.setText(list.get(position).getDescricaoDestino());
		
		
//		Button editarBt = (Button) layout.findViewById(R.id.editar);
//		editarBt.setOnClickListener(new Button.OnClickListener(){
//			@Override
//			public void onClick(View arg0) {
//				Intent intent = new Intent(context, NewUserActivity.class);
//				intent.putExtra("nome", list.get(auxPosition).getNome());
//				intent.putExtra("email", list.get(auxPosition).getEmail());
//				intent.putExtra("id", list.get(auxPosition).getId());
//				context.startActivity(intent);
//			}
//		});
		
		Button deletarBt = (Button) layout.findViewById(R.id.deletar);
		deletarBt.setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View arg0) {
				BD bd = new BD(context);
				bd.deletar(list.get(auxPosition));

				layout.setVisibility(View.GONE);
			}
		});

		return layout;
	}

}
