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

public class FeedbackActivity extends Activity implements OnClickListener{
	TextView id1;
	EditText pid,re;
	Button view,sub;
	SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_feedback);
	final GlobalClass globalvariabel = (GlobalClass)getApplicationContext();
		id1=(TextView)findViewById(R.id.textView2);		
		id1.setText(globalvariabel.GetUsername().toString());
		pid=(EditText)findViewById(R.id.editText1);
		re=(EditText)findViewById(R.id.editText2);
		view=(Button)findViewById(R.id.button1);
		view.setOnClickListener(this);
		sub=(Button)findViewById(R.id.button2);
		sub.setOnClickListener(this);
		db=openOrCreateDatabase("HMS", Context.MODE_PRIVATE, null);
		db.execSQL("create table if not exists feedback(pid varchar,feed varchar,reply varchar);");
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v==view)
    	{
    		Cursor c=db.rawQuery("SELECT * FROM feedback", null);
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
		
		//reply
		if(v==sub)
    	{
    		if(id1.getText().toString().trim().length()==0)
    		{
    			showMessage("Error", "Please enter Rollno");
    			return;
    		}
    		Cursor c=db.rawQuery("SELECT * FROM feedback WHERE pid='"+pid.getText()+"'", null);
    		if(c.moveToFirst())
    		{
    			db.execSQL("UPDATE feedback SET reply='"+re.getText()+"'  WHERE pid='"+pid.getText()+"'");
    			showMessage("Success", "Replay sent successfully");
    		}
    		else
    		{
    			showMessage("Error", "Invalid Patient Id");
    		}
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
    	pid.setText("");
    	re.setText("");
    	
    }
	
}
