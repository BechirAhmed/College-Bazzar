package com.example.college_bazzar;

import java.util.List;

import com.parse.FindCallback;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Slide4Activity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.slide4);
		Parse.initialize(this, "9eUTc1alcdvucSIY2mQqeuElZIg9wzQhHE3YoJjB", "oVbQfwXDLEBwMGXhAaZpmf38CJ0IVb57PIV4JmLx");
		
		
		TextView t1= (TextView) findViewById(R.id.textView5);
		Button b1=(Button) findViewById(R.id.button1);
		final EditText user=(EditText) findViewById(R.id.editText2);
		final EditText pass=(EditText) findViewById(R.id.editText1);
		final ImageView im1=(ImageView) findViewById(R.id.imageView2);
		final TextView tv1=(TextView) findViewById(R.id.textView6);
		
		
		
		
		b1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				final String s1=user.getText().toString();
				final String s2=pass.getText().toString();
				/*ParseUser.logInInBackground(s1,s2,new LogInCallback() {
					  public void done(ParseUser user, ParseException e) {
					    if (user != null) {
					    	//Log.i(TAG,"Logged in");
					    	Toast.makeText(getApplicationContext(),"Sucessfully Logged in", Toast.LENGTH_SHORT).show();
					    	Intent move = new Intent(Slide4Activity.this,
									AfterLogActivity.class);	
							startActivity(move);
					    } else {
					    	Toast.makeText(getApplicationContext(), "Error in Logging.", Toast.LENGTH_SHORT).show();
					    	tv1.setVisibility(View.VISIBLE);
					    	im1.setVisibility(View.VISIBLE);
					    }
					  }
					});*/
				
				ParseQuery<ParseObject> query = ParseQuery.getQuery("Regestered_users");
				query.whereEqualTo("user_name", s1);
				query.whereEqualTo("Password", s2);
				query.findInBackground(new FindCallback<ParseObject>() {
				    public void done(List<ParseObject> query_list, ParseException e) {
				        if (e == null) {
				            //Log.d("score", "Retrieved " + scoreList.size() + " scores");
				        	if(query_list.size()!=0)
				        	{
				        	for(ParseObject obj:query_list)
				        	{
				        		Toast.makeText(getApplicationContext(),"Sucessfully Logged in", Toast.LENGTH_SHORT).show();
				        		SharedPreferences settings = getSharedPreferences("PhoboPrefsFile", 0);
			            	    SharedPreferences.Editor editor = settings.edit();
			            	    
			            	    editor.putString("username", 	   s1);
			            	    editor.putString("objectid", 	obj.getObjectId() );
			            	    
			            	    // Commit the edits!
			            	    editor.commit();
			            	    //finish();
				        		Intent move = new Intent(Slide4Activity.this,
										AfterLogActivity.class);	
								startActivity(move);	
								finish();
								
				        	}}
				        	else{
				        		Toast.makeText(getApplicationContext(),"Invalid user name or password", Toast.LENGTH_SHORT).show();
						    	
				        	}
				        } else {
				            Log.d("score", "Error: " + e.getMessage());
				        }
				    }
				});
				
			}
		});
		
		
		//-----Sign up----------
		t1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent move4_register = new Intent(Slide4Activity.this,
						RegisterTimeActivity.class);	
				startActivity(move4_register);
			}
		});
	}
	
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.slide4, menu);
		return true;
	}

}
