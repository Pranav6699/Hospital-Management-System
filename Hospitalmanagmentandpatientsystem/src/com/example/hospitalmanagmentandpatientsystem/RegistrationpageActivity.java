package com.example.hospitalmanagmentandpatientsystem;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrationpageActivity extends Activity implements OnClickListener{
	Button register;
	EditText username,email,password,phoneno,address;
	SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registrationpage);
		username=(EditText)findViewById(R.id.editText1); 
		email=(EditText)findViewById(R.id.mail);
		password=(EditText)findViewById(R.id.pass);
		phoneno=(EditText)findViewById(R.id.phone);
		address=(EditText)findViewById(R.id.ads);
		register=(Button)findViewById(R.id.reg);
		register.setOnClickListener(this);
		db=openOrCreateDatabase("HMS", Context.MODE_PRIVATE, null);
		db.execSQL("create table if not exists ecil(username varchar,email varchar,password varchar,phoneno number,address varchar);");
		
	}	

@Override
public void onClick(View v) {
	if(username.getText().toString().trim().length()==0||email.getText().toString().trim().length()==0||
			password.getText().toString().trim().length()==0||phoneno.getText().toString().trim().length()==0||address.getText().toString().trim().length()==0){
	
		Toast.makeText(RegistrationpageActivity.this, "please enter details", Toast.LENGTH_LONG).show();
	}
	else if(phoneno.getText().toString().length()!=10)
	{
		Toast.makeText(RegistrationpageActivity.this,"please enter 10 digits",Toast.LENGTH_LONG).show();
		}

	else{
		db.execSQL("INSERT INTO ecil VALUES('"+username.getText()+"','"+email.getText()+
				   "','"+password.getText()+"','"+phoneno.getText()+"','"+address.getText()+"');");
		
		Toast.makeText(RegistrationpageActivity.this,"Registration is sucessfully completed",Toast.LENGTH_LONG).show();
		clearText();
	
		
	}

}
		
	
	    public void clearText()
	    {
	    	username.setText("");
	    	password.setText("");
	    	email.setText("");
	    	phoneno.setText("");
	    	address.setText("");

	    }
}

	
		
	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	


