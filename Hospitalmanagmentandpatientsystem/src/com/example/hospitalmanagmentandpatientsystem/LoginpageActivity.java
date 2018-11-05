package com.example.hospitalmanagmentandpatientsystem;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginpageActivity extends Activity {
	EditText username, password;
	Button login;
	 String u;
	 String p;
	 SQLiteDatabase db;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final GlobalClass globalvariabel = (GlobalClass)getApplicationContext();
		setContentView(R.layout.activity_loginpage);
		username = (EditText) findViewById(R.id.user);
		password = (EditText) findViewById(R.id.pass);
		login=(Button) findViewById(R.id.log1);
		db=openOrCreateDatabase("HMS", Context.MODE_PRIVATE, null);
		login.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub			
				if(username.getText().toString().trim().length()==0||
						password.getText().toString().trim().length()==0){
					
					Toast.makeText(LoginpageActivity.this, "PLz enter the fields..!", Toast.LENGTH_LONG).show();
				}else{	 
					 u = username.getText().toString();
					 p = password.getText().toString();
					 try{
						 db=openOrCreateDatabase("HMS", Context.MODE_PRIVATE, null);					    
					        }catch(Exception exception){
					            exception.printStackTrace();
					        }try{
					        	 Cursor dr = db.rawQuery("select * from ecil where username = '"+u+"' and password = '"+p+"' ", null);
					        	Cursor tr = db.rawQuery("select * from doct where username = '"+u+"' and password = '"+p+"' ", null);
					        	 //Cursor mr = db.rawQuery("select * from tst where id = '"+u+"' and password = '"+p+"' ", null);
					        	 if(username.getText().toString().equals("admin")&& password.getText().toString().equals("admin")){
					        		 Toast.makeText(LoginpageActivity.this, "Welcome To Admin Home Page "  + u , Toast.LENGTH_LONG).show();
					        		 globalvariabel.Setusername(username.getText().toString().trim());
					    				globalvariabel.Setpassword(password.getText().toString().trim());	
					        		 Intent i = new Intent(LoginpageActivity.this,AdminhomepageActivity.class);
					            		startActivity(i);
					            		return;
									}
					     
					        	 // patient Login
					        	 if(dr.moveToFirst())
					        		 {String temp="";					       
						            if (dr != null) {
						            	if(dr.getCount() > 0)
						            	{
						            	//return true;
						           loginmain g=new loginmain();
						            g.execute();
						            
						            		Toast.makeText(LoginpageActivity.this, "Welcome To user patient home Page "  + u , Toast.LENGTH_LONG).show();
						            		globalvariabel.Setusername(username.getText().toString().trim());
						    				globalvariabel.Setpassword(password.getText().toString().trim());
						            		Intent i = new Intent(LoginpageActivity.this,PatienthomepageActivity.class);
						            		startActivity(i);
						            		return;
						            	}else{
						            		 Toast.makeText(LoginpageActivity.this, "Login Fails..!", Toast.LENGTH_LONG).show();
						            	}
						            	}
					        		 }
					        	 //doctors login
					        	 if(tr.moveToFirst())
				        		 {String temp="";					       
					            if (tr != null) {
					            	if(tr.getCount() > 0)
					            	{
					            	//return true;
					           loginmain g=new loginmain();
					            g.execute();
					            
					            		Toast.makeText(LoginpageActivity.this, "Welcome To user doctor Home Page "  + u , Toast.LENGTH_LONG).show();
					            		globalvariabel.Setusername(username.getText().toString().trim());
					    				globalvariabel.Setpassword(password.getText().toString().trim());
					            		Intent i = new Intent(LoginpageActivity.this,DoctorshomepageActivity.class);
					            		startActivity(i);
					            		return;
					            	}else{
					            		 Toast.makeText(LoginpageActivity.this, "Login Fails..!", Toast.LENGTH_LONG).show();
					            	}
					            	}
				        		 }
					        	// tester Login
					        	 
							            	 // manager Login
							            	
							            	
						            //	return false;
							            	else{
							            		 Toast.makeText(LoginpageActivity.this, "Login Fails..!", Toast.LENGTH_LONG).show();
							            	}
							            	
							             }catch(Exception exception){
					            exception.printStackTrace();
					        }
							clear(); 	
				}}
		});
		
	
	

	}
	public class loginmain extends AsyncTask<String, String, String>{
		private ProgressDialog pd;

		protected void onPreExecute() {
			super.onPreExecute();
		 pd = new ProgressDialog(LoginpageActivity.this);
		 pd.setTitle("Please Wait");
		 pd.setMessage("Logging....");
		 pd.setMax(200);
		 pd.show();
		}
		
		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.loginpage, menu);
		return true;
	}
	public void clear()
	{
		username.setText("");
		password.setText("");
			}


}
