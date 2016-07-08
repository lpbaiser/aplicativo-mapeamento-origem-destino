package com.example.raphael.projeto_tcc.bancoDados;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class BD {
	private SQLiteDatabase bd;
	
	public BD(Context context){
		BDCore auxBd = new BDCore(context);
		bd = auxBd.getWritableDatabase();
	}
	
	
	public void inserir(OringemDestino origemDestino){
		ContentValues valores = new ContentValues();
		valores.put("descricaoOrigem", origemDestino.getDescricaoOrigem());
		valores.put("descricaoDestino", origemDestino.getDescricaoDestino());
		valores.put("latOrigem", origemDestino.getLatOrigem());
		valores.put("lonOrigem", origemDestino.getLonOrigem());
		valores.put("latDestino", origemDestino.getLatDestino());
		valores.put("lonDestino", origemDestino.getLonDestino());
		
		bd.insert("origemDestino", null, valores);

	}

	public void inserirEmbDes(EmbarqueDesembarqueClasse embarqueDesembarqueClasse){
		ContentValues valores = new ContentValues();
		valores.put("embarque_desembarque", embarqueDesembarqueClasse.getEmbarque_desembarque());
		valores.put("meio_transporte", embarqueDesembarqueClasse.getMeio_transporte());

		bd.insert("embarqueDesembarqueClasse", null, valores);
	}
	
	
	public void atualizar(OringemDestino origemDestino){
		ContentValues valores = new ContentValues();
		valores.put("descricaoOrigem", origemDestino.getDescricaoOrigem());
		valores.put("descricaoDestino", origemDestino.getDescricaoDestino());
		valores.put("latOrigem", origemDestino.getLatOrigem());
		valores.put("lonOrigem", origemDestino.getLonOrigem());
		valores.put("latDestino", origemDestino.getLatDestino());
		valores.put("lonDestino", origemDestino.getLonDestino());

		bd.update("origemDestino", valores, "_id = ?", new String[]{"" + origemDestino.getId()});

	}

	public void atualizarEmbDes(EmbarqueDesembarqueClasse embarqueDesembarqueClasse){
		ContentValues valores = new ContentValues();
		valores.put("embarque_desembarque", embarqueDesembarqueClasse.getEmbarque_desembarque());
		valores.put("meio_transporte", embarqueDesembarqueClasse.getMeio_transporte());
		bd.update("embarqueDesembarqueClasse", valores, "_id = ?", new String[]{""+embarqueDesembarqueClasse.getId()});
	}
	
	public void deletar(OringemDestino origemDestino){
		bd.delete("origemDestino", "_id = " + origemDestino.getId(), null);

	}

	public void deletarEmbdes(EmbarqueDesembarqueClasse embarqueDesembarqueClasse){
		bd.delete("embarqueDesembarqueClasse", "_id = "+embarqueDesembarqueClasse.getId(), null);
	}



	public List<OringemDestino> buscar(){
		List<OringemDestino> list = new ArrayList<OringemDestino>();
		String[] colunas = new String[]{"_id", "descricaoOrigem","descricaoDestino","latOrigem", "lonOrigem" , "latDestino", "lonDestino" };
		
		Cursor cursor = bd.query("origemDestino", colunas, null, null, null, null, "descricaoOrigem ASC");
		
		if(cursor.getCount() > 0){
			cursor.moveToFirst();
			
			do{

				OringemDestino ori = new OringemDestino();
				ori.setId((int) cursor.getLong(0));
				ori.setDescricaoOrigem(cursor.getString(1));
				ori.setDescricaoDestino(cursor.getString(2));
				ori.setLatOrigem(cursor.getString(3));
				ori.setLonOrigem(cursor.getString(4));
				ori.setLatDestino(cursor.getString(5));
				ori.setLonDestino(cursor.getString(6));
				list.add(ori);
				
			}while(cursor.moveToNext());
		}
		
		return(list);
	}
}
