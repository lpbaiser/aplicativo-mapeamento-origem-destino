package com.example.raphael.projeto_tcc.bancoDados;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BDCore extends SQLiteOpenHelper {
	private static final String NOME_BD = "tcc_banco";
	private static final int VERSAO_BD =9 ;
	
	
	public BDCore(Context ctx){
		super(ctx, NOME_BD, null, VERSAO_BD);
	}
	
	
	@Override
	public void onCreate(SQLiteDatabase bd) {
		String tableOrigemDestino = "create table origemDestino(_id integer primary key autoincrement," +
				"descricaoOrigem text not null," +
				"descricaoDestino text not null," +
				"latOrigem text not null," +
				"lonOrigem text not null," +
				"latDestino text not null," +
				"lonDestino text not null);";

		String tableEmbarqueDesembarque = "create table embarqueDesembarque(_id integer primary key autoincrement," +
				"embarque text not null," +
				"desembarque text not null," +
				"latOrigem text not null," +
				"lonOrigem text not null," +
				"latDestino text not null," +
				"lonDestino text not null);";

		bd.execSQL(tableOrigemDestino);

	}

	@Override
	public void onUpgrade(SQLiteDatabase bd, int arg1, int arg2) {
		bd.execSQL("drop table origemDestino;");
		onCreate(bd);
	}

}
