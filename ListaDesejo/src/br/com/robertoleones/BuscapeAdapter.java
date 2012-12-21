package br.com.robertoleones;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class BuscapeAdapter extends BaseAdapter {

	JSONArray jsonArray;
	private Context context;

	public BuscapeAdapter(Context context, JSONArray jsonArray) {
		this.context = context;
		this.jsonArray = jsonArray;
	}

	public int getCount() {
		return jsonArray.length();
	}

	public JSONObject getItem(int position) {
		try {
			return jsonArray.getJSONObject(position);
		} catch (JSONException e) {
			return null;
		}
	}

	public long getItemId(int position) {
		return getItem(position).hashCode();
	}

	public View getView(int position, View view, ViewGroup viewGroup) {
		if (view == null) {
			LayoutInflater inflater = LayoutInflater.from(context);

			view = inflater.inflate(R.layout.adapter_buscape, null);
		}

		final View viewJSON = view; 
		JSONObject jsonProduct;
		try {
			jsonProduct = getItem(position).getJSONObject("product");
			String nomeProdutoJSON = jsonProduct.getString("productname");
			String precoMaximoJSON = jsonProduct.getString("pricemax");
			String precoMinimoJSON = jsonProduct.getString("pricemin");
			JSONObject thumbnail = jsonProduct.getJSONObject("thumbnail");
			String urlImagem = thumbnail.getString("url");
			
			new AsyncTask<String, Void, Bitmap>() {

				@Override
				protected Bitmap doInBackground(String... params) {
					Bitmap bitmap = HttpUtil.getBitmap(params[0]);
					return bitmap;
				}
				
				@Override
				protected void onPostExecute(Bitmap result) {
					ImageView imageView = (ImageView) viewJSON.findViewById(R.id.imagemProduto);
					imageView.setImageBitmap(result);
				}
			}.execute(urlImagem);

			TextView nomeProduto = (TextView) view
					.findViewById(R.id.nomeProduto);
			TextView precoMinimo = (TextView) view
					.findViewById(R.id.precoMinimo);
			TextView precoMaximo = (TextView) view
					.findViewById(R.id.precoMaximo);
			

			nomeProduto.setText(nomeProdutoJSON);
			precoMaximo.setText(precoMaximoJSON);
			precoMinimo.setText(precoMinimoJSON);
		} catch (JSONException e) {
			Toast.makeText(context, "Ocorreu um erro ao obter o objeto JSON.", Toast.LENGTH_SHORT).show();
		}

		return view;
	}

}
