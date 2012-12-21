package br.com.robertoleones;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity2 extends Activity {

	private ListView listView;
	private MeuAdapter meuAdapter;
	String selected;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_activity2);

		listView = (ListView) findViewById(R.id.listview);

		SQLiteDatabase db = DBOpenHelper.getInstance(MainActivity2.this)
				.getReadableDatabase();
		Cursor cursor = db.query("desejo", null, null, null, null, null, null);
		meuAdapter = new MeuAdapter(this, cursor);

		registerForContextMenu(listView);

		listView.setOnItemLongClickListener(new OnItemLongClickListener() {
			public boolean onItemLongClick(AdapterView<?> adapterView,
					View view, int position, long id) {
				openContextMenu(listView);
				selected = (String) listView.getItemAtPosition(position);
				return true;
			}
		});

		listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

		listView.setAdapter(meuAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.menu_add) {
			Intent intent = new Intent(MainActivity2.this, MainActivity.class);

			startActivity(intent);
		} else if (item.getItemId() == R.id.menu_search) {
			SQLiteDatabase db = DBOpenHelper.getInstance(MainActivity2.this)
					.getReadableDatabase();
			Cursor cursor = db.query("desejo", null, null, null, null, null,
					null);
			meuAdapter = new MeuAdapter(MainActivity2.this, cursor);

			listView.setAdapter(meuAdapter);
		} else if (item.getItemId() == R.id.menu_settings) {
			Toast.makeText(this, "Não há configurações disponíveis.",
					Toast.LENGTH_SHORT).show();
		}

		return true;
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.menu_delete) {
			Toast.makeText(this, "Removeu: " + selected, Toast.LENGTH_LONG)
					.show();
		}
		return true;
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		getMenuInflater().inflate(R.menu.activity_main_activity2, menu);
	}

	@Override
	public void openContextMenu(View view) {
		super.openContextMenu(view);
	}

}
