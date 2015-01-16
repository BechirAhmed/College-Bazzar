package com.example.college_bazzar;

import java.util.List;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.GetDataCallback;
import com.parse.Parse;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseException;

import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class PostEnlarge2Activity extends Activity {
	
	String image;
	String  object_id,price,location,user,phone,college
    ,description,item,email,category,number,send_data,name,UserLoggedIn;
    ProgressDialog  progressDialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.post_enlarge2);

        final ImageView image = (ImageView) findViewById(R.id.imageView1);
        final TextView tv1=(TextView) findViewById(R.id.textView1);
        final TextView tv2=(TextView) findViewById(R.id.textView2);
        final TextView tv3=(TextView) findViewById(R.id.textView4);
        final TextView tv4=(TextView) findViewById(R.id.textView7);
        final TextView tv5=(TextView) findViewById(R.id.textView8);
        final TextView tv6=(TextView) findViewById(R.id.textView10);
        final TextView tv7=(TextView) findViewById(R.id.textView12);
        final TextView tv8=(TextView) findViewById(R.id.textView13);

        
        

        /*final String object_id,price;
		ProgressDialog
		final String location, user, phone;
		final String college;*/
        
		//final String description, item, name, email, category, number, send_data;
        final Button b1=(Button) findViewById(R.id.button1);
        final  Button b2=(Button) findViewById(R.id.button2);
        //final Button b3=(Button) findViewById(R.id.button3);


		final ConnectivityManager cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
		Parse.initialize(this, "9eUTc1alcdvucSIY2mQqeuElZIg9wzQhHE3YoJjB", "oVbQfwXDLEBwMGXhAaZpmf38CJ0IVb57PIV4JmLx");
		
		SharedPreferences det = getSharedPreferences("PhoboPrefsFile", 0);
		
		UserLoggedIn = det.getString("username", "");
		
		SharedPreferences details = getSharedPreferences("last_work", 0);
		
		object_id = details.getString("objectid", "");
		if(!object_id.equals("")) {
			
			 progressDialog = ProgressDialog.show(PostEnlarge2Activity.this, "",
                     "Downloading Image...", true);
			
			ParseQuery<ParseObject> query = ParseQuery.getQuery("Post_created");
			query.getInBackground(object_id, new GetCallback<ParseObject>() {
			  public void done(ParseObject object, ParseException e) {
			    if (e == null) {
			    	college=object.getString("college");
			    	category=object.getString("Category");
			    	location=object.getString("City");
			    	description=object.getString("Description");
			    	item=object.getString("Item");
			    	phone=object.getString("Phone");
			    	price=object.getString("Price");
			    	user=object.getString("user_name");
			    	
			    	if(user.equals(UserLoggedIn))
			    	{
			    		tv8.setVisibility(View.VISIBLE);
			    	}
			    	
			    	 ParseFile fileObj = (ParseFile) object
                             .get("ImageFile");
                     fileObj
                             .getDataInBackground(new GetDataCallback() {

                                 public void done(byte[] data,
                                         ParseException e) {
                                     if (e == null) {
                                         Log.d("test",
                                                 "We've got data in data.");
                                         // Decode the Byte[] into
                                         // Bitmap
                                         Bitmap bmp = BitmapFactory
                                                 .decodeByteArray(
                                                         data, 0,
                                                         data.length);

                                         // Get the ImageView from
                                         // main.xml

                                         // Set the Bitmap into the
                                         // ImageView
                                         image.setImageBitmap(bmp);

                                         // Close progress dialog
                                         progressDialog.dismiss();

                                     } else {
                                         Log.d("test",
                                                 "There was a problem downloading the data.");
                                     }
                                 }
                             });
                  
                     ParseQuery<ParseObject> query2 = ParseQuery.getQuery("Updates");
                     query2.whereEqualTo("user_name", user);
                     query2.findInBackground(new FindCallback<ParseObject>() {
                         public void done(List<ParseObject> scoreList, ParseException e) {
                             if (e == null) {
                            	 
                            	 for(ParseObject obj:scoreList)
                            	 {
                            		 name=obj.getString("Name");
                            		 email=obj.getString("Email");
                            	 }
                             } else {
                                 Log.d("score", "Error: " + e.getMessage());
                             }
                         }
                     });
         			
         			 tv1.setText(item+"("+category+")"); 
                      tv3.setText(name);
                      tv4.setText(location);
                      tv5.setText(user);
                      tv6.setText(college);
                      tv2.setText(description); 
                      tv7.setText(price);
                     
                   
			    }
			      
			     else {
			      // something went wrong
			    }
			  }
			});
			
		 	   
            tv8.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					ParseQuery<ParseObject> query = ParseQuery.getQuery("Post_created");
					query.getInBackground(object_id, new GetCallback<ParseObject>() {
					  public void done(ParseObject object, ParseException e) {
					    if (e == null) {
					    object.deleteInBackground();
		                   
					    }
					      
					     else {
					      // something went wrong
					    }
					  }
					});
					
					
				}
			});
		
			b2.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					number="tel:"+phone.trim();
					Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse(number)); 
			        startActivity(callIntent);
				}
			});
			
			b1.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Bundle basket=new Bundle();
					basket.putString("key", email);
					Intent callIntent = new Intent(PostEnlarge2Activity.this, EmailActivity.class); 
					callIntent.putExtras(basket);
					startActivity(callIntent);
					
				}
			});
		
		
		
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.post_enlarge2, menu);
		return true;
	}

}
