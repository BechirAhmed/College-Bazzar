package com.example.college_bazzar;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;

public class MainQueryActivity extends Activity{

	String maintext="",spin1,spin2,image_button;
	
	ListView listview;
    List<ParseObject> ob;
    ProgressDialog mProgressDialog;
    ListViewAdapter adapter;
    private List<Post_Show> postshow = null;
    
 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_query);
		
		final ConnectivityManager cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
		Parse.initialize(this, "9eUTc1alcdvucSIY2mQqeuElZIg9wzQhHE3YoJjB", "oVbQfwXDLEBwMGXhAaZpmf38CJ0IVb57PIV4JmLx");
		
		
		Bundle gotbasket=getIntent().getExtras();
		maintext=gotbasket.getString("key");

		
		final Spinner sp1=(Spinner) findViewById(R.id.spinner1);
		final Spinner sp2=(Spinner) findViewById(R.id.spinner2);
		TextView tv1=(TextView) findViewById(R.id.textView1);
		ImageView im1=(ImageView) findViewById(R.id.imageView1);
		final AutoCompleteTextView aw1=(AutoCompleteTextView) findViewById(R.id.autoCompleteTextView1);
		ImageButton ib1=(ImageButton) findViewById(R.id.imageButton1);
		
		
		ArrayAdapter<CharSequence> adapter1=ArrayAdapter.createFromResource(this,R.array.spinner_cate,R.layout.blank2);
		sp1.setAdapter(adapter1);
		sp1.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,
					int pos, long id) {
				//code here
				spin1=sp1.getItemAtPosition(pos).toString();
				//Toast.makeText(getApplicationContext(), spin1,Toast.LENGTH_SHORT).show();
				
			
			}

			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
		
		//Item 2
		
		ArrayAdapter<CharSequence> adapter2=ArrayAdapter.createFromResource(this,R.array.spinner_sort,R.layout.blank2);
		sp2.setAdapter(adapter2);
		sp2.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,
					int pos, long id) {
				spin2=sp2.getItemAtPosition(pos).toString();

			}

			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
		
		if(maintext.equals("Books"))
		{
			tv1.setText("Category : "+maintext);
			im1.setImageResource(R.drawable.book);
		}
		else if(maintext.equals("Electronics"))
		{
			tv1.setText("Category : "+maintext);
			im1.setImageResource(R.drawable.electronics);
		}
		else if(maintext.equals("Notes"))
		{
			tv1.setText("Category : "+maintext);
			im1.setImageResource(R.drawable.notes);
		}
		else if(maintext.equals("Stationary"))
		{
			tv1.setText("Category : "+maintext);
			im1.setImageResource(R.drawable.stationary);
		}
		else if(maintext.equals("Vehicles"))
		{
			tv1.setText("Category : "+maintext);
			im1.setImageResource(R.drawable.vehicles);
		}
		else if(maintext.equals("Others"))
		{
			tv1.setText("Category : "+maintext);
			im1.setImageResource(R.drawable.othors);
		}
		
		//auto_text for City
		ParseQuery<ParseObject> query2 = ParseQuery.getQuery("Post_created");
		//query.whereEqualTo("user_name",UserLoggedIn);
		
		query2.findInBackground(new FindCallback<ParseObject>() {
		    public void done(List<ParseObject> scoreList, ParseException e) {
		        if (e == null) {
		    		//Toast.makeText(getApplicationContext(),scoreList.size(),Toast.LENGTH_SHORT).show();
		        	int len=scoreList.size(),i=0;
		        	String[] cities=new String[len];
		        	for(ParseObject obj1:scoreList)
		        	{
		        	cities[i]=obj1.getString(spin1);
		        	i++;
		        	}
		        	cities=removeDuplicates(cities);
    				
		        	//Toast.makeText(getApplicationContext(), "i="+Integer.toString(i), Toast.LENGTH_SHORT).show();

		        		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
		        				R.layout.blank,cities );
		        		aw1.setAdapter(adapter);

		        		aw1.setOnItemClickListener(new OnItemClickListener() {

		        			@Override
		        			

		        			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
		        					long arg3) {
		        				//Toast.makeText(getApplicationContext(), "hhjjj", Toast.LENGTH_SHORT).show();
			 		            Log.d("score", "autocomplete working");

		        			}
		        		});
		        	}
		        		 else {
		 		            Log.d("score", "Error: " + e.getMessage());
		 		        }
		    }});

		
		
		
				ib1.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						image_button=aw1.getText().toString();
						new RemoteDataTask().execute();
					}
				});
		
		//new RemoteDataTask().execute();
	}
	
	private class RemoteDataTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressdialog
            mProgressDialog = new ProgressDialog(MainQueryActivity.this);
            // Set progressdialog title
            mProgressDialog.setTitle("Wait for Some Time");
            // Set progressdialog message
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            // Show progressdialog
            mProgressDialog.show();
        }
        
        @Override
        protected Void doInBackground(Void... params) {
            // Create the array
            postshow = new ArrayList<Post_Show>();
            try {
                // Locate the class table named "Country" in Parse.com
                ParseQuery<ParseObject> query = new ParseQuery<ParseObject>(
                        "Post_created");
                // Locate the column named "ranknum" in Parse.com and order list
                // by ascending
                //query.orderByAscending("");
    			query.whereEqualTo("Category", maintext);
    			//query.orderByAscending(spin1);
    			if(!spin1.equals("Price"))
    			{
    				query.orderByAscending(spin1);
    			}
    			else 
    			{
    				query.orderByDescending(spin1);
    			}
    			/*if(spin2.equals("Recent View"))
    			{
    				query.addDescendingOrder("createdAt");
    			}
    			else 
    			{
    				query.addAscendingOrder(spin1);
    			}*/
        		
                ob = query.find();
                for (ParseObject country : ob) {
                    // Locate images in flag column
                    ParseFile image = (ParseFile) country.get("ImageFile");
 
                    Post_Show map = new Post_Show();
                    map.setItem((String) country.get("Item"));
                    map.setPrice((String) country.get("Price"));
                    map.setUsername((String) country.get("user_name"));
                    map.setLocation((String) country.get("City"));
                    map.setCollege((String) country.get("college"));
                    map.setObjectid((String) country.getObjectId());
            		//Toast.makeText(getApplicationContext(),(String) country.get("objectId"), Toast.LENGTH_SHORT).show();

                   // map.setDate((String) country.get("createdAt"));
                   // map.setUsername((String) country.get("user_name"));

                    map.setPic(image.getUrl());
                    postshow.add(map);
                }
            } catch (ParseException e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return null;
        }
 
	
	
	 @Override
     protected void onPostExecute(Void result) {
         // Locate the listview in listview_main.xml
         listview = (ListView) findViewById(R.id.listView1);
         // Pass the results into ListViewAdapter.java
         adapter = new ListViewAdapter(MainQueryActivity.this,
                 postshow);
         // Binds the Adapter to the ListView
         listview.setAdapter(adapter);
         // Close the progressdialog
         mProgressDialog.dismiss();
     }
	}
	
	
	 public static String[] removeDuplicates(String []dupArray)
	    {
	        HashSet<String> mySet = new HashSet<String>(Arrays.asList(dupArray));
	        dupArray = new String[mySet.size()];
	        mySet.toArray(dupArray);
	        return dupArray;
	    } 

	//@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_query, menu);
		return true;
	}

}
