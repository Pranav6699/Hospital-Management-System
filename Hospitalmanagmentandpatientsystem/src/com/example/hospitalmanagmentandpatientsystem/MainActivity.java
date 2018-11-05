package com.example.hospitalmanagmentandpatientsystem;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	Button d,g,n;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        d=(Button)findViewById(R.id.log);
        d.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent c=new Intent(MainActivity.this,LoginpageActivity.class);
				startActivity(c);
				
			}
		});
        g=(Button)findViewById(R.id.patient);
        g.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent f=new Intent(MainActivity.this,RegistrationpageActivity.class);
				startActivity(f);
				
			}
		});
        n=(Button)findViewById(R.id.doctor);
        n.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent j=new Intent(MainActivity.this,DoctorsregistrationActivity.class);
				startActivity(j);
				
			}
		});}
       
				

        
        
    


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
