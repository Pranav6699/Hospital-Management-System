package com.example.hospitalmanagmentandpatientsystem;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Booking extends Activity implements OnClickListener{
	TextView id;
	EditText spe,hn,ad;
	Button sub;
	SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_booking);
		final GlobalClass globalvariabel = (GlobalClass)getApplicationContext();
			id=(TextView)findViewById(R.id.textView2);		
			id.setText(globalvariabel.GetUsername().toString());
			spe=(EditText)findViewById(R.id.editText2);
			hn=(EditText)findViewById(R.id.editText3);
			ad=(EditText)findViewById(R.id.editText4);
			sub=(Button)findViewById(R.id.button1);
			sub.setOnClickListener(this);
			db=openOrCreateDatabase("HMS", Context.MODE_PRIVATE, null);
			db.execSQL("create table if not exists docde(did varchar,dspe varchar,dhn varchar,davd varchar);");
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v==sub)
    	{
    	
    		if(spe.getText().toString().trim().length()==0||
    		   hn.getText().toString().trim().length()==0||
    		   ad.getText().toString().trim().length()==0)
    		{
    			showMessage("Error", "Please enter all values");
    			return;
    		}
    		//
    		db.execSQL("INSERT INTO docde VALUES('"+id.getText()+"','"+spe.getText()+
    				   "','"+hn.getText()+"','"+ad.getText()+"');");
    		showMessage("Success", "Doctor Details Added");
    		clearText();
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
    	spe.setText("");
    	hn.setText("");
    	ad.setText("");
    }
	

}
