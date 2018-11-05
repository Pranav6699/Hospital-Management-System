package com.example.hospitalmanagmentandpatientsystem;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.view.View.OnClickListener;

public class DoctorsregistrationActivity extends Activity implements OnClickListener{
	EditText username,password,email,phoneno;
	Button register;
	SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_doctorsregistration);
        username=(EditText)findViewById(R.id.user);
        email=(EditText)findViewById(R.id.mail);
        password=(EditText)findViewById(R.id.pass);
        phoneno=(EditText)findViewById(R.id.phone);
        register=(Button)findViewById(R.id.button1);
        register.setOnClickListener(this);
        db=openOrCreateDatabase("HMS", Context.MODE_PRIVATE, null);
		db.execSQL("CREATE TABLE IF NOT EXISTS doct(username VARCHAR ,email VARCHAR,password VARCHAR, phoneno NUMBER);");
	}
	public void onClick(View ad)
    {
    	if(ad==register)
    	{
    		if(username.getText().toString().trim().length()==0||
    		   email.getText().toString().trim().length()==0||
    		   password.getText().toString().trim().length()==0||
    		   
    		   phoneno.getText().toString().trim().length()==0)
    		{
    			showMessage("Error", "Please enter all values");
    			return;
    		}
    		else if(phoneno.getText().toString().length()!=10){
				  Toast.makeText(DoctorsregistrationActivity.this, "Enter 10 digits",Toast.LENGTH_LONG).show();
			  }else{
    		db.execSQL("INSERT INTO doct VALUES('"+username.getText()+"','"+email.getText()+
    				   "','"+password.getText()+"','"+phoneno.getText()+"');");
    		showMessage("Success", "Doctor Registred Success");
    		clearText();
			  }
    	}
    }
	
	public void showMessage(String title,String message)
    {
    	Builder builder=new Builder(this);
    	builder.setCancelable(true);
    	builder.setTitle(title);
    	builder.setMessage(message);
    	builder.show();
	}
    public void clearText()
    {
    	username.setText("");
    	email.setText("");
    	password.setText("");
    	phoneno.setText("");
    	username.requestFocus();
    }

}
