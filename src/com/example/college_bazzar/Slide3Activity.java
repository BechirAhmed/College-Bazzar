package com.example.college_bazzar;


import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Slide3Activity extends Activity {
     
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.slide3);
		
		TextView txt1 = (TextView) findViewById(R.id.textView3);
		TextView txt2 = (TextView) findViewById(R.id.textView1);
		Button b1=(Button) findViewById(R.id.button1);
		
		ConnectivityManager connMgr = (ConnectivityManager) 
		        getSystemService(Context.CONNECTIVITY_SERVICE);
		    NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
		    if (networkInfo != null && networkInfo.isConnected()) {
		    	Toast.makeText(getApplicationContext(), "Network Connected Successfully",
		    			   Toast.LENGTH_LONG).show();
		    	Intent move34 = new Intent(Slide3Activity.this,
						AfterLogActivity.class);
				startActivity(move34);
		    } else {
		    	Toast.makeText(getApplicationContext(), "Unable To connect network :( Try Again",
		    			   Toast.LENGTH_LONG).show();
		    	txt1.setVisibility(View.VISIBLE);
		    	b1.setVisibility(View.VISIBLE);
		    	txt2.setText("Error");
		    	
		     }
		    
		 b1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent move33 = new Intent(Slide3Activity.this,
						Slide3Activity.class);	
				startActivity(move33);
			}
		});
		 
		}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.slide3, menu);
		return true;
	}

}
