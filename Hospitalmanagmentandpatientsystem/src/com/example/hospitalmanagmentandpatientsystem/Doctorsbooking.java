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

public class Doctorsbooking extends Activity implements OnClickListener{
	TextView bdid;
	EditText did,ad;
	Button vd,sub;
	SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_doctorsbooking);
		final GlobalClass globalvariabel = (GlobalClass)getApplicationContext();
			bdid=(TextView)findViewById(R.id.textView2);		
			bdid.setText(globalvariabel.GetUsername().toString());
			did=(EditText)findViewById(R.id.editText1);
			ad=(EditText)findViewById(R.id.editText2);
			vd=(Button)findViewById(R.id.button1);
			vd.setOnClickListener(this);
			sub=(Button)findViewById(R.id.button2);
			sub.setOnClickListener(this);
			db=openOrCreateDatabase("HMS", Context.MODE_PRIVATE, null);
			db.execSQL("create table if not exists bookdocde(pid varchar,did varchar,aval varchar);");
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v==vd)
    	{
    		Cursor c=db.rawQuery("SELECT * FROM docde", null);
    		if(c.getCount()==0)
    		{
    			showMessage("Error", "No records found");
    			return;
    		}
    		StringBuffer buffer=new StringBuffer();
    		while(c.moveToNext())
    		{
    			buffer.append("Doctor Id: "+c.getString(0)+"\n");
    			buffer.append("Doctor Speciliaztion: "+c.getString(1)+"\n");
    			buffer.append("Hospital Name: "+c.getString(2)+"\n");
    			buffer.append("Avaliability Date: "+c.getString(3)+"\n\n");
    		}
    		showMessage("Doctor Details", buffer.toString());
    	}
		
		//sub
		if(v==sub)
    	{
    	
    		if(did.getText().toString().trim().length()==0||
    		   ad.getText().toString().trim().length()==0)
    		{
    			showMessage("Error", "Please enter all values");
    			return;
    		}
    		// checking user id avaliable or not+
    		Cursor c=db.rawQuery("SELECT * FROM docde WHERE did='"+did.getText()+"' and davd='"+ad.getText()+"'", null);
    		if(c.moveToFirst())
    		{
    			db.execSQL("INSERT INTO bookdocde VALUES('"+bdid.getText()+"','"+did.getText()+
     				   "','"+ad.getText()+"');");
     		showMessage("Success", "Booking Confirmed");
     		clearText();
     		return;
    		}
    		else
    		{
    			showMessage("Error", "Doctor Not Avaliable");
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
    	did.setText("");
    	ad.setText("");
    }
}
