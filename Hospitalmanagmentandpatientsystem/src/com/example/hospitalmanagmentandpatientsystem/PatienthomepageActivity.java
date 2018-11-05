package com.example.hospitalmanagmentandpatientsystem;

import android.os.Bundle;
import android.view.View.OnClickListener;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PatienthomepageActivity extends Activity implements OnClickListener{
	Button m,n,l,vout;
	TextView pid;
	SQLiteDatabase db;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_patienthomepage);
		final GlobalClass globalvariabel = (GlobalClass)getApplicationContext();
		pid=(TextView)findViewById(R.id.textView2);
		pid.setText(globalvariabel.GetUsername().toString());
		vout=(Button)findViewById(R.id.button2);
		vout.setOnClickListener(this);
		db=openOrCreateDatabase("HMS", Context.MODE_PRIVATE, null);
		m=(Button)findViewById(R.id.docts);
		m.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent g=new Intent(PatienthomepageActivity.this,Doctorsbooking.class);
				startActivity(g);
				
			}
		});
		n=(Button)findViewById(R.id.feedback);
		n.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent t=new Intent(PatienthomepageActivity.this,Patientfeedback.class);
				startActivity(t);
				
			}
		});
		l=(Button)findViewById(R.id.logout1);
		l.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent r=new Intent(PatienthomepageActivity.this,MainActivity.class);
				startActivity(r);
				
			}
		});
	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v==vout)
    	{
    		if(pid.getText().toString().trim().length()==0)
    		{
    			showMessage("Error", "Please enter Rollno");
    			return;
    		}
    		Cursor c=db.rawQuery("SELECT * FROM out WHERE pid='"+pid.getText()+"'", null);
    		if(c.getCount()==0)
    		{
    			showMessage("Error", "No records found");
    			return;
    		}
    		StringBuffer buffer=new StringBuffer();
    		while(c.moveToNext())
    		{
    			buffer.append("Doctor Id: "+c.getString(0)+"\n");
    			buffer.append("Patient Id: "+c.getString(1)+"\n");
    			buffer.append("In Date: "+c.getString(2)+"\n");
    			buffer.append("Out Date: "+c.getString(3)+"\n");
    			buffer.append("Total Fee: "+c.getString(4)+"\n");
    			buffer.append("Room No: "+c.getString(5)+"\n");
    		}
    		showMessage("Student Details", buffer.toString());
    		
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
	

}
