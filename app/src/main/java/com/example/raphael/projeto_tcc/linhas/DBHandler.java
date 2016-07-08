package com.example.raphael.projeto_tcc.linhas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by raphael on 01/04/2016.
 */
public class DBHandler extends SQLiteOpenHelper implements LinhasListener{

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "LinhasDatabase.db";
    private static final String TABLE_NAME = "linhas_table";
  //  private static final String KEY_ID = "_id";
    private static final String KEY_NAME = "_name";
    private static final String KEY_CATEGORIA_SERVICO = "_categoria_servico";
    private static final String KEY_COD = "_cod";
    private static final String KEY_SOMENTE_CARTAO = "_somente_cartao";

    String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+" ("+KEY_COD+" TEXT PRIMARY KEY,"+KEY_NAME+" TEXT,"+KEY_CATEGORIA_SERVICO+" TEXT,"+KEY_SOMENTE_CARTAO+" TEXT)";
    String DROP_TABLE = "DROP TABLE IF EXISTS "+TABLE_NAME;
    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }

    @Override
    public void addLinhas(Linhas linhas) {
        SQLiteDatabase db = this.getWritableDatabase();
        try{
            ContentValues values = new ContentValues();
            values.put(KEY_COD,linhas.getCOD());
            values.put(KEY_NAME, linhas.getNOME());
            values.put(KEY_SOMENTE_CARTAO,linhas.getSOMENTE_CARTAO());
            values.put(KEY_CATEGORIA_SERVICO, linhas.getCATEGORIA_SERVICO());

            db.insert(TABLE_NAME, null, values);
            db.close();
        }catch (Exception e){
            Log.e("problem",e+"");
        }
    }

    @Override
    public ArrayList<Linhas> getAllLinhas() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Linhas> linhasList = null;
        try{
            linhasList = new ArrayList<Linhas>();
            String QUERY = "SELECT * FROM "+TABLE_NAME;
            Cursor cursor = db.rawQuery(QUERY, null);
            if(!cursor.isLast())
            {
                while (cursor.moveToNext())
                {
                    Linhas linhas = new Linhas();
                    linhas.setCOD(cursor.getString(0));
                    linhas.setNOME(cursor.getString(1));
                    linhas.setSOMENTE_CARTAO(cursor.getString(3));
                    linhas.setCATEGORIA_SERVICO(cursor.getString(2));
                    linhasList.add(linhas);
                }
            }
            db.close();
        }catch (Exception e){
            Log.e("error", e + "");
        }
        return linhasList;
    }

    @Override
    public int getLinhasCount() {
        int num = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        try{
            String QUERY = "SELECT * FROM "+TABLE_NAME;
            Cursor cursor = db.rawQuery(QUERY, null);
            num = cursor.getCount();
            db.close();
            return num;
        }catch (Exception e){
            Log.e("error",e+"");
        }
        return 0;
    }
}



