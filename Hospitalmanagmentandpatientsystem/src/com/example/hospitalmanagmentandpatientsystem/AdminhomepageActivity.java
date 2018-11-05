package com.example.hospitalmanagmentandpatientsystem;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AdminhomepageActivity extends Activity implements OnClickListener {
	TextView id;
	Button d,view,vd,dd,du;
	SQLiteDatabase db;
	EditText eid;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final GlobalClass globalvariabel = (GlobalClass)getApplicationContext();
		setContentView(R.layout.activity_adminhomepage);
		id=(TextView)findViewById(R.id.textView2);
		id.setText(globalvariabel.GetUsername().toString());
		view=(Button)findViewById(R.id.button1 );
		view.setOnClickListener(this);
		d=(Button)findViewById(R.id.logout2);
		vd=(Button)findViewById(R.id.button2);
		vd.setOnClickListener(this);
		dd=(Button)findViewById(R.id.button3);
		dd.setOnClickListener(this);
		du=(Button)findViewById(R.id.button6);
		du.setOnClickListener(this);
		eid=(EditText)findViewById(R.id.editText1);
		db=openOrCreateDatabase("HMS", Context.MODE_PRIVATE, null);
		d.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent e=new Intent(AdminhomepageActivity.this,MainActivity.class);
	            startActivity(e);
			}
		});
		
		}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v==view)
    	{
    		Cursor c=db.rawQuery("SELECT * FROM ecil", null);
    		if(c.getCount()==0)
    		{
    			showMessage("Error", "No records found");
    			return;
    		}
    		StringBuffer buffer=new StringBuffer();
    		while(c.moveToNext())
    		{
    			buffer.append("username: "+c.getString(0)+"\n");
    			buffer.append("email: "+c.getString(1)+"\n");
    			buffer.append("phoneno: "+c.getString(2)+"\n");
    			buffer.append("address: "+c.getString(3)+"\n\n");
    		}
    		showMessage("patient Details", buffer.toString());
    	}
		
		//view
		if(v==vd)
    	{
    		Cursor c=db.rawQuery("SELECT * FROM doct", null);
    		if(c.getCount()==0)
    		{
    			showMessage("Error", "No records found");
    			return;
    		}
    		StringBuffer buffer=new StringBuffer();
    		while(c.moveToNext())
    		{
    			buffer.append("Doctor name: "+c.getString(0)+"\n");
    			buffer.append("email: "+c.getString(1)+"\n");
    			buffer.append("phoneno: "+c.getString(2)+"\n");
    			buffer.append("address: "+c.getString(3)+"\n\n");
    		}
    		showMessage("Doctor Details", buffer.toString());
    	}
		//delete docotor
		if(v==dd)
    	{
    		if(eid.getText().toString().trim().length()==0)
    		{
    			showMessage("Error", "Please Doctor Id");
    			return;
    		}
    		Cursor c=db.rawQuery("SELECT * FROM doct WHERE username='"+eid.getText()+"'", null);
    		if(c.moveToFirst())
    		{
    			db.execSQL("DELETE FROM doct WHERE username='"+eid.getText()+"'");
    			showMessage("Success", "Record Deleted");
    		}
    		else
    		{
    			showMessage("Error", "Invalid Doctor id");
    		}
    	}
		//dele user
		if(v==du)
    	{
    		if(eid.getText().toString().trim().length()==0)
    		{
    			showMessage("Error", "Please Doctor Id");
    			return;
    		}
    		Cursor c=db.rawQuery("SELECT * FROM ecil WHERE username='"+eid.getText()+"'", null);
    		if(c.moveToFirst())
    		{
    			db.execSQL("DELETE FROM ecil WHERE username='"+eid.getText()+"'");
    			showMessage("Success", "Record Deleted");
    		}
    		else
    		{
    			showMessage("Error", "Invalid Doctor id");
    		}
    	}
		
		
	}
		;		
				
			
public void showMessage(String title,String message)
{
	Builder builder=new Builder(this);
	builder.setCancelable(true);
	builder.setTitle(title);
	builder.setMessage(message);
	builder.show();
}

}	


