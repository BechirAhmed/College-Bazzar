package com.example.college_bazzar;



import java.util.Date;
import java.util.concurrent.TimeUnit;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Slide1Activity extends ActionBarActivity {

	private Handler mHandler = new Handler();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.slide1);
		ActionBar actionbar=getSupportActionBar();
		actionbar.hide();
		 mHandler.postDelayed(new Runnable() {
	            public void run() {
	                doStuff();
	            }
	        }, 1500);
	    }

	    private void doStuff() {
	        //Toast.makeText(this, "Delayed Toast!", Toast.LENGTH_SHORT).show();
	        Intent move12 = new Intent(Slide1Activity.this,
					Slide2Activity.class);
			
				
				startActivity(move12);
	    }
	
		
	
		

	
		
		/*Intent move12 = new Intent(Slide1Activity.this,
				Slide2Activity.class);
		startActivity(move12);*/
		
		
		/*Button b1= (Button) findViewById(R.id.button1);
		b1.setOnClickListener(new OnClickListener() {
			
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent move12 = new Intent(Slide1Activity.this,
						Slide2Activity.class);
				startActivity(move12);
				
			}
		});*/
	/*Thread closeActivity = new Thread(new Runnable() {	
	public void run() {
		
		
			try {
				Thread.sleep(1000);
				Intent move12 = new Intent(Slide1Activity.this,
						Slide2Activity.class);
				startActivity(move12);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}});*/
	
	public void onStart() {
		super.onStart();
		
			
		
		
	}
		


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		
		
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.slide1, menu);
		return true;
	}

}
