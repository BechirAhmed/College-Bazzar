package com.example.college_bazzar;

import java.lang.reflect.Field;

import com.parse.Parse;
import com.parse.ParseUser;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewConfiguration;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AfterLogActivity extends ActionBarActivity  {

	int k=0;
	String s1,UserLoggedIn;
	String tobesent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.after_log);
		
		TextView t1= (TextView) findViewById(R.id.textView2);
		TextView t2= (TextView) findViewById(R.id.textView3);
		TextView t3= (TextView) findViewById(R.id.textView4);
		TextView t4= (TextView) findViewById(R.id.textView5);
		Button bt1=(Button) findViewById(R.id.button1);
		
		Button books=(Button) findViewById(R.id.button3);
		Button electronic=(Button) findViewById(R.id.Button02);
		Button notes=(Button) findViewById(R.id.Button01);
		Button stationary=(Button) findViewById(R.id.Button05);
		Button vehicles=(Button) findViewById(R.id.Button04);
		Button others=(Button) findViewById(R.id.Button03);
		
		
		Parse.initialize(this, "9eUTc1alcdvucSIY2mQqeuElZIg9wzQhHE3YoJjB", "oVbQfwXDLEBwMGXhAaZpmf38CJ0IVb57PIV4JmLx");
		/*ParseUser currentUser = ParseUser.getCurrentUser();
		if (currentUser != null) {
			Toast.makeText(getApplicationContext(), "Current user is "+currentUser.getString("username"),Toast.LENGTH_LONG).show();
		    s1=currentUser.getString("username");
			k=1;
		} else {
		  k=0;
		}*/
		
		SharedPreferences details = getSharedPreferences("PhoboPrefsFile", 0);
		
		UserLoggedIn = details.getString("username", "");
		if(UserLoggedIn.equals("")) {
			k=0;
		}
		else 
		{
		String	UserLogId = details.getString("objectid", "");
		Toast.makeText(getApplicationContext(), "Current user is "+UserLoggedIn,Toast.LENGTH_SHORT).show();

		k=1;
		}
		
		bt1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent after_get = new Intent(AfterLogActivity.this,
						GetDetailsActivity.class);	
				startActivity(after_get);
				
			}
		});
		
	    bt1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent mov = new Intent(AfterLogActivity.this,
						GetDetailsActivity.class);
				
				startActivity(mov);
				
			}
		});
		
		books.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				tobesent="Books";
				Bundle basket=new Bundle();
				basket.putString("key", tobesent);
				//Toast.makeText(getApplicationContext(), "Tost1",Toast.LENGTH_SHORT).show();

				Intent booke = new Intent(AfterLogActivity.this,
						MainQueryActivity.class);
				booke.putExtras(basket);
				startActivity(booke);
				
			}
		});
		
	electronic.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				tobesent="Electronics";
				Bundle basket=new Bundle();
				basket.putString("key", tobesent);
				Intent booke = new Intent(AfterLogActivity.this,
						MainQueryActivity.class);
				booke.putExtras(basket);
				startActivity(booke);
				
			}
		});
		
notes.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				tobesent="Notes";
				Bundle basket=new Bundle();
				basket.putString("key", tobesent);
				Intent booke = new Intent(AfterLogActivity.this,
						MainQueryActivity.class);
				booke.putExtras(basket);
				startActivity(booke);
				
			}
		});

stationary.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		tobesent="Stationary";
		Bundle basket=new Bundle();
		basket.putString("key", tobesent);
		Intent booke = new Intent(AfterLogActivity.this,
				MainQueryActivity.class);
		booke.putExtras(basket);
		startActivity(booke);
		
	}
});

vehicles.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		tobesent="Vehicles";
		Bundle basket=new Bundle();
		basket.putString("key", tobesent);
		Intent booke = new Intent(AfterLogActivity.this,
				MainQueryActivity.class);
		booke.putExtras(basket);
		startActivity(booke);
		
	}
});

others.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		tobesent="Others";
		Bundle basket=new Bundle();
		basket.putString("key", tobesent);
		Intent booke = new Intent(AfterLogActivity.this,
				MainQueryActivity.class);
		booke.putExtras(basket);
		startActivity(booke);
		
	}
});
	
			}
	
	
	@SuppressLint("NewApi")
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		MenuInflater inflater=getMenuInflater();
		if(k==0)
		{
		inflater.inflate(R.menu.action_signin, menu);
		inflater.inflate(R.menu.refresh, menu);
		
		}
		else
		{
			menu.add(0, 23, 0, UserLoggedIn)
            .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM|MenuItem.SHOW_AS_ACTION_WITH_TEXT);
			inflater.inflate(R.menu.action_signup, menu);
			inflater.inflate(R.menu.refresh, menu);
			
			
			
		}
		// Inflate the menu; this adds items to the action bar if it is present.
		return true;
	}
	
	@Override
	protected void onStart() {
	    super.onStart();  // Always call the superclass method first
		Parse.initialize(this, "9eUTc1alcdvucSIY2mQqeuElZIg9wzQhHE3YoJjB", "oVbQfwXDLEBwMGXhAaZpmf38CJ0IVb57PIV4JmLx");
		
		
		SharedPreferences details = getSharedPreferences("PhoboPrefsFile", 0);
		
		UserLoggedIn = details.getString("username", "");
		if(UserLoggedIn.equals("")) {
			k=0;
		}
		else 
		{
		String	UserLogId = details.getString("objectid", "");
		//Toast.makeText(getApplicationContext(), "Current user is "+UserLoggedIn,Toast.LENGTH_SHORT).show();

		k=1;
		}  }
	
	
	@Override
	protected void onStop() {
	    super.onStop();
	}
	
	@Override
	protected void onRestart() {
	    super.onRestart();  // Always call the superclass method first
	    
	    // Activity being restarted from stopped state    
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
		
	    switch (item.getItemId()) {
	        case R.id.sign_in:
	        	Intent move33 = new Intent(AfterLogActivity.this,
						Slide4Activity.class);	
				startActivity(move33);
				finish();
				return true;
	            
	        case R.id.sign_up:
	        	Intent move34 = new Intent(AfterLogActivity.this,
						RegisterTimeActivity.class);	
				startActivity(move34);
				finish();
				return true;
	            
	        case R.id.sign_out:
	        	
				
				SharedPreferences details = getSharedPreferences("PhoboPrefsFile", 0);
				details.edit().clear().commit();
				Toast.makeText(getApplicationContext(), "Sucessfully Logged out",Toast.LENGTH_LONG).show();
				
				Intent refresh = new Intent(this, AfterLogActivity.class);
	        	startActivity(refresh);
	        	this.finish();
				k=0;
				return true;
				
	        case 23:
	        	//Toast.makeText(getApplicationContext(), "got id",Toast.LENGTH_SHORT).show();
	        	Intent move1234 = new Intent(AfterLogActivity.this,UserProfileActivity.class);
	        	startActivity(move1234);
	        	return true;
	        	
	        case 22:
	        	//Toast.makeText(getApplicationContext(), "got id",Toast.LENGTH_SHORT).show();
	        	Intent move123 = new Intent(AfterLogActivity.this,UserProfileActivity.class);
	        	startActivity(move123);
	        	finish();
	        	return true;
	        case R.id.re_fresh: 
	        	Toast.makeText(getApplicationContext(), "Refreshed",Toast.LENGTH_LONG).show();
	        	Intent refresh1 = new Intent(this, AfterLogActivity.class);
	        	startActivity(refresh1);
	        	this.finish();
	        	return true;
	        case R.id.re_about: 
	        	Toast.makeText(getApplicationContext(), "About",Toast.LENGTH_LONG).show();
	        	return true;
	        case R.id.re_help: 
	        	Toast.makeText(getApplicationContext(), "help",Toast.LENGTH_LONG).show();
	        	return true;	
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}

}
