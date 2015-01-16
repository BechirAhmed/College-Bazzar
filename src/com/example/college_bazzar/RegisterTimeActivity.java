package com.example.college_bazzar;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterTimeActivity extends Activity {

	private static final Bundle savedInstanceSidetate = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceSidetate);
		setContentView(R.layout.register_time);
		
		final ConnectivityManager cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
		Parse.initialize(this, "9eUTc1alcdvucSIY2mQqeuElZIg9wzQhHE3YoJjB", "oVbQfwXDLEBwMGXhAaZpmf38CJ0IVb57PIV4JmLx");
		
		final Button b1=(Button) findViewById(R.id.button1);
		final EditText user_id=(EditText) findViewById(R.id.editText1);
		final EditText pass=(EditText) findViewById(R.id.editText2);
		final EditText name=(EditText) findViewById(R.id.editText3);
		final EditText email=(EditText) findViewById(R.id.editText4);
		final TextView tv1=(TextView) findViewById(R.id.textView6);
		final ImageView im1=(ImageView) findViewById(R.id.imageView2);
		
		b1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// Check Connectivity
				NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
				boolean isConnected = activeNetwork != null &&  activeNetwork.isConnectedOrConnecting();
				
				if(! isConnected) Toast.makeText(getApplicationContext(),"Connection Error. Post cannot be created.", Toast.LENGTH_SHORT).show();
    			
				else
				{ 			
					String s1=user_id.getText().toString();
					String s2=pass.getText().toString();
					String s3=name.getText().toString();
					String s4=email.getText().toString();
					int k=0;
					
					if(s1=="" || s2=="" || s3=="" || s4=="")
					{k=1;}
					if(k==1)
					{
						tv1.setVisibility(View.VISIBLE);
				    	im1.setVisibility(View.VISIBLE);
						
							
						}
						else
						{
						
							 	ParseObject CreatePost = new ParseObject("Regestered_users");
		    					CreatePost.put("user_name", user_id.getText().toString());
		    					CreatePost.put("Password",   pass.getText().toString());
		    					CreatePost.put("Name",  name.getText().toString());
		    					CreatePost.put("Email_id",  email.getText().toString());
		    					CreatePost.put("check",false);
		    					CreatePost.saveInBackground();
						
		    			 
		    			 
		    			 // ParseAnalytics.trackAppOpened(getIntent());
		    					Toast.makeText(getApplicationContext(),"Regestered_sucessfully.", Toast.LENGTH_SHORT).show();
							Intent move = new Intent(RegisterTimeActivity.this,
									Slide4Activity.class);	
							startActivity(move);
		    			 
		    			}
		    			 finish();
						}
						
						
					
					
    			  
			}
		});
	}
		
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.register_time, menu);
		return true;
	}

}
