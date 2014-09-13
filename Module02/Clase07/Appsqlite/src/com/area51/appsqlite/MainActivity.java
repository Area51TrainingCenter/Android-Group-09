package com.area51.appsqlite;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

	EditText txtusuario;
	EditText txtclave;
	Button btningresa;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//Relacionamos variables con los componentes
		txtusuario = (EditText)findViewById(R.id.txtusuario);
		txtclave = (EditText)findViewById(R.id.txtclave);
		btningresa = (Button)findViewById(R.id.btningresa);
				
	}

}
