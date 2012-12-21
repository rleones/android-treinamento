package br.com.robertoleones;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;

public class ListaSelecaoActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lista_selecao);

		if (getIntent().getExtras() != null
				&& getIntent().getExtras().getString("NOME_PRODUTO") != null) {
			final String nomeProduto = getIntent().getExtras().getString(
					"NOME_PRODUTO");
			final ListView listViewListaSelecao = (ListView) findViewById(R.id.listViewListaSelecao);

			new AsyncTask<Void, Void, BuscapeAdapter>() {
				@Override
				protected BuscapeAdapter doInBackground(Void... params) {
					String url = "http://sandbox.buscape.com/service/findProductList/72577349624e6c685068513d/?keyword="
							+ nomeProduto + "&format=json";
					String jsonRetorno = HttpUtil.doGet(url);
					try {
						JSONObject json = new JSONObject(jsonRetorno);
						JSONArray jsonArray = json.getJSONArray("product");
						return new BuscapeAdapter(ListaSelecaoActivity.this,
								jsonArray);
					} catch (JSONException e) {
						return null;
					}
				}

				@Override
				protected void onPostExecute(BuscapeAdapter result) {
					listViewListaSelecao.setAdapter(result);
				}
			}.execute();

		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_lista_selecao, menu);
		return true;
	}

}
