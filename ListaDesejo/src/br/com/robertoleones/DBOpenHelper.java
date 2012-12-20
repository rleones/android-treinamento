package br.com.robertoleones;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public final class DBOpenHelper extends SQLiteOpenHelper {

	private static final int VERSION = 1;
	private static DBOpenHelper instance;

	public static DBOpenHelper getInstance(Context context) {
		if (instance == null) {
			instance = new DBOpenHelper(context, "listadesejo.db", null,
					VERSION);
		}

		return instance;
	}

	private DBOpenHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);

	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		StringBuilder sql = new StringBuilder();
		
		sql.append("CREATE TABLE desejo (");
		sql.append("_id INTEGER PRIMARY KEY AUTOINCREMENT,");
		sql.append("nome TEXT,");
		sql.append("valor REAL,");
		sql.append("categoria TEXT,");
		sql.append("estabelecimento TEXT,");
		sql.append("endereco TEXT,");
		sql.append("preco_minimo REAL,");
		sql.append("preco_maximo REAL,");
		sql.append("aviso INTEGER)");

		db.execSQL(sql.toString());
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}
