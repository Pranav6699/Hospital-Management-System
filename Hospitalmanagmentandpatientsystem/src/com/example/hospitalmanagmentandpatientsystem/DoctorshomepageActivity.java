package com.example.hospitalmanagmentandpatientsystem;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DoctorshomepageActivity extends Activity {	
	TextView did;
	Button g,z,y,k;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_doctorshomepage);
		final GlobalClass globalvariabel = (GlobalClass)getApplicationContext();
		did = (TextView)findViewById(R.id.textView2);
		did.setText(globalvariabel.GetUsername().toString());
		g=(Button)findViewById(R.id.out);
		g.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent f=new Intent(DoctorshomepageActivity.this,Outpatientlist.class);
				startActivity(f);
				
			}
		});
		z=(Button)findViewById(R.id.add);
		z.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent s=new Intent(DoctorshomepageActivity.this,Booking.class);
				startActivity(s);
				
			}
		});
		y=(Button)findViewById(R.id.feed);
		y.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent r=new Intent(DoctorshomepageActivity.this,FeedbackActivity.class);
				startActivity(r);
				
			}
		});
		k=(Button)findViewById(R.id.logout);
		k.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent f=new Intent(DoctorshomepageActivity.this,MainActivity.class);
				startActivity(f);
				
			}
		});
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.doctorshomepage, menu);
		return true;
	}

}
