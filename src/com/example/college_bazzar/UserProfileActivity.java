package com.example.college_bazzar;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class UserProfileActivity extends Activity {

	String s1,s2,s3,s4,s5,UserLoggedIn,objectid="";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_profile);
		
		final ConnectivityManager cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
		final EditText e1=(EditText) findViewById(R.id.editText1);
		final EditText e2=(EditText) findViewById(R.id.editText2);
		final EditText e3=(EditText) findViewById(R.id.editText3);
		final AutoCompleteTextView aw1=(AutoCompleteTextView) findViewById(R.id.autoCompleteTextView1);
		final TextView tv1=(TextView) findViewById(R.id.textView1);
		final TextView tv6=(TextView) findViewById(R.id.textView6);

		Button b1=(Button) findViewById(R.id.button1);
		final CheckBox cb1=(CheckBox) findViewById(R.id.checkBox1);
		Parse.initialize(this, "9eUTc1alcdvucSIY2mQqeuElZIg9wzQhHE3YoJjB", "oVbQfwXDLEBwMGXhAaZpmf38CJ0IVb57PIV4JmLx");
		
		/*ParseUser currentUser = ParseUser.getCurrentUser();
		if (currentUser != null) {
			s5=currentUser.getString("username");
			Toast.makeText(getApplicationContext(), s5,Toast.LENGTH_SHORT).show();
		}*/ 
		SharedPreferences details = getSharedPreferences("PhoboPrefsFile", 0);
		
		UserLoggedIn = details.getString("username", "");
		//objectid=details.getString("objectid", "");
		//Toast.makeText(getApplicationContext(),objectid,Toast.LENGTH_SHORT).show();
		
		
		
		ParseQuery<ParseObject> query = ParseQuery.getQuery("Regestered_users");
		query.whereEqualTo("user_name",UserLoggedIn);
		
		query.findInBackground(new FindCallback<ParseObject>() {
		    public void done(List<ParseObject> scoreList, ParseException e) {
		        if (e == null) {
		        	for (ParseObject dealsObject : scoreList) {
		                
		    			//objectid=dealsObject.getObjectId();
		    			//Toast.makeText(getApplicationContext(),objectid,Toast.LENGTH_SHORT).show();
		        		// use dealsObject.get('columnName') to access the properties of the Deals object.
		        		if(dealsObject.getBoolean("check")==false)
		        		{
		        			//Toast.makeText(getApplicationContext(),"Level2", Toast.LENGTH_SHORT).show();

		        		s1=dealsObject.getString("Name");
		        		tv1.setText("Welcome "+s1);
			            s2=dealsObject.getString("Email_id");
			            e1.setText(s1);
			            e2.setText(s2);
			            
		        		}
		        		else
		        		{
		        			//Toast.makeText(getApplicationContext(),"Level3", Toast.LENGTH_SHORT).show();

		        			
		        			ParseQuery<ParseObject> q2 = ParseQuery.getQuery("Updates");
		        			q2.whereEqualTo("user_name",UserLoggedIn);

			    			
		        			q2.findInBackground(new FindCallback<ParseObject>() {
		        			    public void done(List<ParseObject> scoreL, ParseException e22) {
		        			        if (e22 == null) {
		        			        	for (ParseObject obj2:scoreL)
		        			        	{
		        			        		objectid=obj2.getObjectId();
		        			    			//Toast.makeText(getApplicationContext(),objectid,Toast.LENGTH_SHORT).show();
		        			        		
		        			        		s1=obj2.getString("Name");
		        			        		tv1.setText("Welcome ->"+s1);
		        			        		e1.setText(obj2.getString("Name"));
		        			        		e2.setText(obj2.getString("Email"));
		        			        		e3.setText(obj2.getString("phone"));
		        			        		s4=obj2.getString("college");
		        			        		if(s4.equals("none"))
		        			        		{
		        			        			cb1.setChecked(false);
		        			        			aw1.setVisibility(View.INVISIBLE);
		        			        			tv6.setVisibility(View.INVISIBLE);
		        			        		}
		        			        		else
		        			        		{
		        			        			cb1.setChecked(true);
		        			        			aw1.setVisibility(View.VISIBLE);
		        			        			tv6.setVisibility(View.VISIBLE);
		        			        			aw1.setText(s4);
		        			        		}
		        			        	}
		        			        }
		        		}});
			            //Toast.makeText(getApplicationContext(),"fuck",Toast.LENGTH_LONG).show();
		        	}
		        		dealsObject.put("check",true);
	        			dealsObject.saveInBackground();
		        		}}
		           // s1=(scoreList).getString("Name");
		            //s2=(scoreList).getString("email");
		        	//int i=scoreList.size();
		        	//s2=Integer.toString(i);
		            //Toast.makeText(getApplicationContext(), s2 ,Toast.LENGTH_LONG).show();
		         else {
		            Log.d("score", "Error: " + e.getMessage());
		        }
		    }
		});
		
		
		/*NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
		boolean isConnected = activeNetwork != null &&  activeNetwork.isConnectedOrConnecting();
		
		if(! isConnected) Toast.makeText(getApplicationContext(),"Connection Error. Post cannot be created.", Toast.LENGTH_SHORT).show();
		
		else
		{ 		
		
		ParseQuery<ParseObject> query = ParseQuery.getQuery("User");
		query.getInBackground("pMlblKj9V2", new GetCallback<ParseObject>() {
		  public void done(ParseObject object, ParseException e) {
		    if (e == null) {
		    	s1=object.getString("Name");
	            s2=object.getString("email");
	            Toast.makeText(getApplicationContext(),s1+" fuck "+s2,Toast.LENGTH_LONG).show();
        	
		    } else {
		      // something went wrong
		    }
		  }
		});*/
	/*e1.setText(s1);
	e2.setText(s2);*/
	s1=e1.getText().toString();
	s2=e2.getText().toString();
	s3=e3.getText().toString();
	cb1.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if(cb1.isChecked()==true)
			{
				aw1.setVisibility(View.VISIBLE);
				tv6.setVisibility(View.VISIBLE);
				
			}
			else
			{
				aw1.setVisibility(View.INVISIBLE);
				tv6.setVisibility(View.INVISIBLE);
				s4="none";
			}
		}
	});
	
	
	//---------auto for college
	ParseQuery<ParseObject> query3 = ParseQuery.getQuery("Updates");
	//query.whereEqualTo("user_name",UserLoggedIn);
	
	query3.findInBackground(new FindCallback<ParseObject>() {
	    public void done(List<ParseObject> scoreList1, ParseException e) {
	        if (e == null) {
	    		//Toast.makeText(getApplicationContext(),scoreList.size(),Toast.LENGTH_SHORT).show();
	        	int len=scoreList1.size(),i=0;
	        	String[] items=new String[len];
	        	for(ParseObject obj1:scoreList1)
	        	{
	        	items[i]=obj1.getString("college");
	        	i++;
	        	}
	        	items=removeDuplicates(items);
	        		
	        		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
	        				R.layout.blank,items );
	        		aw1.setAdapter(adapter);
	        		
	        		aw1.setOnItemClickListener(new OnItemClickListener() {

	        			@Override
	        			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
	        					long arg3) {
	        				//Toast.makeText(getApplicationContext(), "The winner is:" + arg0.getAdapter().getItem(arg2), Toast.LENGTH_LONG).show();
	        				
	        			}
	        		});
	        	}
	        		 else {
	 		            Log.d("score", "Error: " + e.getMessage());
	 		        }
	 		    }
	 		});
	//ParseObject obj_big = ParseObject.createWithoutData("Updates", objectid);
	//ParseQuery query_big = new ParseQuery("Updates");        
	//ParseObject obj_big = query.get(objectid);
	// Set a new value on quantity
	
	b1.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			
			if(cb1.isChecked()==true)
			{
				s4=aw1.getText().toString();
				
			}
			else
			{
				//e4.setVisibility(View.INVISIBLE);
				s4="none";
			}
			s1=e1.getText().toString();
			s2=e2.getText().toString();
			s3=e3.getText().toString();
			
			/*ParseQuery<ParseObject> query4 = ParseQuery.getQuery("Updates");
			query4.whereEqualTo("user_name",UserLoggedIn);
			
			query4.findInBackground(new FindCallback<ParseObject>() {
			    public void done(List<ParseObject> scoreList1, ParseException e) {
			        if (e == null) {
			    		//Toast.makeText(getApplicationContext(),scoreList.size(),Toast.LENGTH_SHORT).show();
			        	for(ParseObject obj:scoreList1)
			        	{obj.deleteInBackground();}
			        	}
			        		 else {
			 		            Log.d("score", "Error: " + e.getMessage());
			 		        }
			 		    }
			 		});*/
			
			if(objectid.equals(""))
			{
				ParseObject obj1=new ParseObject("Updates");
				obj1.put("user_name",UserLoggedIn);
				obj1.put("Name", s1);
				obj1.put("Email",s2);
				obj1.put("phone",s3);
				obj1.put("college",s4);
				obj1.saveInBackground();
				
			}
			else
			{
				ParseQuery query_big = new ParseQuery("Updates");        
				ParseObject obj_big;
				try {
					obj_big = query_big.get(objectid);
					obj_big.put("Name", s1);
					obj_big.put("Email",s2);
					obj_big.put("phone",s3);
					obj_big.put("college",s4);
					obj_big.save();
									
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			
			// TODO Auto-generated method stub
			/*ParseObject obj1=new ParseObject("Updates");
			//obj1.put("user_name",UserLoggedIn);
			obj1.put("Name", s1);
			obj1.put("Email",s2);
			obj1.put("phone",s3);
			obj1.put("college",s4);
			obj1.saveInBackground();*/
			
			/*ParseQuery<ParseObject> query_up = ParseQuery.getQuery("Updates");
			query_up.whereEqualTo("user_name", UserLoggedIn);
			query_up.findInBackground(new FindCallback<ParseObject>() {
			    public void done(List<ParseObject> scoreList, ParseException e) {
			        if (e == null) {
			        	for(ParseObject obj1:scoreList)
			        	{
			        		obj1.put("Name", s1);
			    			obj1.put("Email",s2);
			    			obj1.put("phone",s3);
			    			obj1.put("college",s4);
			    			obj1.saveInBackground();	
			        	}
			        } else {
			            Log.d("score", "Error: " + e.getMessage());
			        }
			    }
			});*/
			
			/*ParseObject obj1 = ParseObject.createWithoutData("Updates", objectid);

			// Set a new value on quantity
			obj1.put("Name", s1);
			obj1.put("Email",s2);
			obj1.put("phone",s3);
			obj1.put("college",s4);
			obj1.saveInBackground();*/

			// Save
			
			
			/*ParseQuery query = new ParseQuery("Updates");        
			try {
				ParseObject obj1 = query.get(objectid);
				obj1.put("Name", s1);
    			obj1.put("Email",s2);
    			obj1.put("phone",s3);
    			obj1.put("college",s4);
    			obj1.saveInBackground();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			
			Toast.makeText(getApplicationContext(),"Profile Updated", Toast.LENGTH_SHORT).show();
			Intent move = new Intent(UserProfileActivity.this,
					AfterLogActivity.class);	
			startActivity(move);
			//finish();
			
		}
	});}
	
	 public static String[] removeDuplicates(String []dupArray)
	    {
	        HashSet<String> mySet = new HashSet<String>(Arrays.asList(dupArray));
	        dupArray = new String[mySet.size()];
	        mySet.toArray(dupArray);
	        return dupArray;
	    }  

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user_profile, menu);
		return true;
	}

}
