package br.com.robertoleones;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button btnSalvar = (Button) findViewById(R.id.salvar);

		Button btnListar = (Button) findViewById(R.id.verlista);

		btnSalvar.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				SQLiteDatabase db = DBOpenHelper.getInstance(MainActivity.this)
						.getWritableDatabase();

				EditText editText = (EditText) findViewById(R.id.nomeproduto);
				EditText precoproduto = (EditText) findViewById(R.id.precoproduto);
				Spinner categoria = (Spinner) findViewById(R.id.categoria);
				EditText estabelecimento = (EditText) findViewById(R.id.estabelecimento);
				EditText endereco = (EditText) findViewById(R.id.endereco);
				EditText precoMinimo = (EditText) findViewById(R.id.precominimo);
				EditText precoMaximo = (EditText) findViewById(R.id.precomaximo);
				CheckBox aviso = (CheckBox) findViewById(R.id.on);

				ContentValues values = new ContentValues();

				values.put("nome", editText.getText().toString());
				values.put("valor", precoproduto.getText().toString());
				values.put("categoria", categoria.getSelectedItem().toString());
				values.put("estabelecimento", estabelecimento.getText()
						.toString());
				values.put("endereco", endereco.getText().toString());
				values.put("preco_minimo", precoMinimo.getText().toString());
				values.put("preco_maximo", precoMaximo.getText().toString());
				values.put("aviso", aviso.isChecked() ? 1 : 0);

				db.insert("desejo", null, values);

				finish();
			}
		});

		btnListar.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});
	}

	@Override
	protected void onStart() {
		super.onStart();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onRestart() {
		super.onRestart();
	}

	@Override
	protected void onStop() {
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

}
