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

public class Patientfeedback extends Activity implements OnClickListener{
	TextView id2;
	EditText fed;
	Button sub,view;
	SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_patientfeedback);
		final GlobalClass globalvariabel = (GlobalClass)getApplicationContext();
		id2=(TextView)findViewById(R.id.textView2);		
		id2.setText(globalvariabel.GetUsername().toString());
		fed=(EditText)findViewById(R.id.editText1);
		sub=(Button)findViewById(R.id.button1);
		sub.setOnClickListener(this);
		view=(Button)findViewById(R.id.button2);
		view.setOnClickListener(this);
		db=openOrCreateDatabase("HMS", Context.MODE_PRIVATE, null);
		db.execSQL("create table if not exists feedback(pid varchar,feed varchar,reply varchar);");

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v==sub)
    	{
    	
    		if(fed.getText().toString().trim().length()==0)
    		{
    			showMessage("Error", "Please enter all values");
    			return;
    		}
    		
    		db.execSQL("INSERT INTO feedback VALUES('"+id2.getText()+"','"+fed.getText()+
    				   "','"+null+"');");
    		showMessage("Success", "Feedback added Successfully");
    		clearText();
    	}
		//
		if(v==view)
    	{
    		if(id2.getText().toString().trim().length()==0)
    		{
    			showMessage("Error", "Please enter Rollno");
    			return;
    		}
    		Cursor c=db.rawQuery("SELECT * FROM feedback WHERE pid='"+id2.getText()+"'", null);
    		if(c.getCount()==0)
    		{
    			showMessage("Error", "No records found");
    			return;
    		}
    		StringBuffer buffer=new StringBuffer();
    		while(c.moveToNext())
    		{
    			buffer.append("Patient Id: "+c.getString(0)+"\n");
    			buffer.append("Feedback: "+c.getString(1)+"\n");
    			buffer.append("Status: "+c.getString(2)+"\n\n");
    		}
    		showMessage("Feedback Details", buffer.toString());
    		
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
    	fed.setText("");
    }
}