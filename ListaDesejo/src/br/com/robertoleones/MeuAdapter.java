package br.com.robertoleones;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MeuAdapter extends BaseAdapter {

	Cursor cursor;
	Context context;

	public MeuAdapter(Context context, Cursor cursor) {
		this.context = context;
		this.cursor = cursor;
	}

	public int getCount() {
		return cursor.getCount();
	}

	public Object getItem(int position) {
		cursor.moveToPosition(position);
		return cursor;
	}

	public long getItemId(int position) {
		Cursor cursor = ((Cursor) getItem(position));
		return cursor.getInt(cursor.getColumnIndex("_id"));
	}

	public View getView(final int position, View convertView,
			ViewGroup convertGroup) {
		if (convertView == null) {
			LayoutInflater inflater = LayoutInflater.from(context);

			convertView = inflater.inflate(R.layout.adapter_linha, null);
		}

		final Cursor cursor = (Cursor) getItem(position);
		TextView textView = (TextView) convertView
				.findViewById(R.id.adapterTextView);

		textView.setText(cursor.getString(cursor.getColumnIndex("nome")));

		/*Button button = (Button) convertView.findViewById(R.id.adapterButton);

		button.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				Cursor cursorToast = (Cursor) getItem(position);
				Toast.makeText(
						context,
						"Produto: "
								+ cursorToast.getString(cursorToast
										.getColumnIndex("nome"))
								+ " \nPreço: "
								+ cursorToast.getDouble(cursorToast
										.getColumnIndex("valor")),
						Toast.LENGTH_SHORT).show();
			}
		});*/

		return convertView;
	}

}
