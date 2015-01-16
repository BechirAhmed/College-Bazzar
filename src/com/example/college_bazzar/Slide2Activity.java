package com.example.college_bazzar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;

public final class Slide2Activity extends ActionBarActivity {

	private Handler mHandler = new Handler();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.slide2);
		ActionBar actionbar=getSupportActionBar();
		actionbar.hide();
		 mHandler.postDelayed(new Runnable() {
	            public void run() {
	                doStuff();
	            }
	        }, 1000);
	    }

	    private void doStuff() {
	        //Toast.makeText(this, "Delayed Toast!", Toast.LENGTH_SHORT).show();
	        Intent move23 = new Intent(Slide2Activity.this,
					Slide3Activity.class);
			
				
				startActivity(move23);
	    }
	
	
	
	

}