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

public class Outpatientlist extends Activity implements OnClickListener{
	TextView id3;
	EditText pid,pida,poda,fee,room;
	Button vb,sub;
	SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_outpatientlist);
		final GlobalClass globalvariabel = (GlobalClass)getApplicationContext();
		setContentView(R.layout.activity_outpatientlist);
		id3=(TextView)findViewById(R.id.textView2);
		id3.setText(globalvariabel.GetUsername().toString());
		pid=(EditText)findViewById(R.id.editText1);
		pida=(EditText)findViewById(R.id.editText2);
		poda=(EditText)findViewById(R.id.editText3);
		fee=(EditText)findViewById(R.id.editText4);
		room=(EditText)findViewById(R.id.editText5);
		vb=(Button)findViewById(R.id.button2);
		vb.setOnClickListener(this);
		sub=(Button)findViewById(R.id.button1);
		sub.setOnClickListener(this);
		db=openOrCreateDatabase("HMS", Context.MODE_PRIVATE, null);
		db.execSQL("create table if not exists out(did varchar,pid varchar,idate varchar,odate varchar,fee varchar,room varchar);");
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generaif(view==btnSearchbyid)
		if(v==vb)
    	{
    		if(id3.getText().toString().trim().length()==0)
    		{
    			showMessage("Error", "Please enter Rollno");
    			return;
    		}
    		Cursor c=db.rawQuery("SELECT * FROM bookdocde WHERE did='"+id3.getText()+"'", null);
    		if(c.getCount()==0)
    		{
    			showMessage("Error", "No records found");
    			return;
    		}
    		StringBuffer buffer=new StringBuffer();
    		while(c.moveToNext())
    		{
    			buffer.append("Patient Id: "+c.getString(0)+"\n");
    			buffer.append("Booking Date: "+c.getString(2)+"\n\n");
    		}
    		showMessage("Patient Details", buffer.toString());
    		
    	}
		//outttttttt
		if(v==sub)
    	{
    	
    		if(pid.getText().toString().trim().length()==0||
    		   pida.getText().toString().trim().length()==0||
    		   poda.getText().toString().trim().length()==0||
    		   room.getText().toString().trim().length()==0||
    		   fee.getText().toString().trim().length()==0
    				)
    		{
    			showMessage("Error", "Please enter all values");
    			return;
    		}
    		// checking user id avaliable or not+
    		Cursor c=db.rawQuery("SELECT * FROM bookdocde WHERE pid='"+pid.getText()+"'", null);
    		if(c.moveToFirst())
    		{

        		db.execSQL("INSERT INTO out VALUES('"+id3.getText()+"','"+pid.getText()+
        				   "','"+pida.getText()+"','"+poda.getText()+"','"+fee.getText()+"','"+room.getText()+"');");
        		showMessage("Success", "Patient OutDetails added");
        		clearText();
        		return;
    		}
    		else
    		{
    			showMessage("Error", "Patient id not avaliable");
    		}
    		//
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
    	pid.setText("");
    	pida.setText("");
    	poda.setText("");
    	fee.setText("");
    	room.setText("");
    }
}
