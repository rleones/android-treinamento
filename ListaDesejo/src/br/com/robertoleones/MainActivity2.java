package br.com.robertoleones;

import java.text.ChoiceFormat;

import android.R.string;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
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

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, android.R.id.text1);
		
		adapter.add("Item 1");
		adapter.add("Item 2");
		adapter.add("Item 3");
 		
		listView.setAdapter(adapter);
		Button button = (Button) findViewById(R.id.button);
		Button recarregar = (Button) findViewById(R.id.recarregar);

		button.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				Intent intent = new Intent(MainActivity2.this,
						MainActivity.class);

				startActivity(intent);
			}
		});
		recarregar.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				SQLiteDatabase db = DBOpenHelper
						.getInstance(MainActivity2.this).getReadableDatabase();
				Cursor cursor = db.query("desejo", null, null, null, null,
						null, null);
				meuAdapter = new MeuAdapter(MainActivity2.this, cursor);

				listView.setAdapter(meuAdapter);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.menu_delete){
			Toast.makeText(this, "Removeu: " + selected, Toast.LENGTH_LONG).show();
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
