package br.com.robertoleones;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;

/**
 * O uso da classe-pai FragmentActivity garante que a aplicação seja portável
 * para dispositivos que usam a versão 2 do Android.
 * 
 * @author Avansys
 * 
 */
public class MainAula2Activity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_aula2);

		// getSupportFragmentManager(); // Para versão 2.X
		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager
				.beginTransaction();

		fragmentTransaction.add(R.id.fragment_container, new Fragment1());
		
		fragmentTransaction.addToBackStack(null);
		fragmentTransaction.commit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main_aula2, menu);
		return true;
	}

}
