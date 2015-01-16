package com.example.college_bazzar;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
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
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

public class GetDetailsActivity extends Activity {
	
	TextView tv1;
	EditText ed11,ed1,ed2;
	AutoCompleteTextView aw1,aw2;
	Button bt11,bt1;
	CheckBox cb1;
	ImageView im1;
	private static int RESULT_LOAD_IMAGE = 1;
	String picturePath="Nill",UserLoggedIn;
	Uri selectedImage ;
	int position,count;

	String phone,city,item,description,price,category,college;
	//int image_uploaded=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.get_details);
		
		tv1=(TextView) findViewById(R.id.textView15);
		ed11=(EditText) findViewById(R.id.editText11);
		 aw1=(AutoCompleteTextView) findViewById(R.id.autoCompleteTextView11);
		bt11=(Button) findViewById(R.id.button11);
		aw2=(AutoCompleteTextView) findViewById(R.id.autoCompleteTextView1);
		ed1=(EditText) findViewById(R.id.editText1);
		ed2=(EditText) findViewById(R.id.editText2);
		cb1=(CheckBox) findViewById(R.id.checkBox1);
		bt1=(Button) findViewById(R.id.button1);
		im1=(ImageView) findViewById(R.id.imageView1);
		

		final ConnectivityManager cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
		Parse.initialize(this, "9eUTc1alcdvucSIY2mQqeuElZIg9wzQhHE3YoJjB", "oVbQfwXDLEBwMGXhAaZpmf38CJ0IVb57PIV4JmLx");
		
		
		final Spinner sp1=(Spinner) findViewById(R.id.spinner11);
		ArrayAdapter<CharSequence> adapter1=ArrayAdapter.createFromResource(this,R.array.category,R.layout.blank);
		sp1.setAdapter(adapter1);
		sp1.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,
					int pos, long id) {
				//code here
				//Toast.makeText(getApplicationContext(),pos,Toast.LENGTH_SHORT).show();
				//adapter1=sp1.getSelectedItem();
				//category=getString(pos);
				//Toast.makeText(getApplicationContext(),category,Toast.LENGTH_SHORT).show();
				category=sp1.getItemAtPosition(pos).toString();
			}
		
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
		
		
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
		        	cities[i]=obj1.getString("City");
		        	i++;
		        	}
		        	cities=removeDuplicates(cities);
		        		
		        		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
		        				R.layout.blank,cities );
		        		aw1.setAdapter(adapter);
        				//Toast.makeText(getApplicationContext(), "step 1", Toast.LENGTH_SHORT).show();

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
		 		    }
		 		});
		
		//---------------------auto_text for Item
				ParseQuery<ParseObject> query3 = ParseQuery.getQuery("Post_created");
				//query.whereEqualTo("user_name",UserLoggedIn);
				
				query3.findInBackground(new FindCallback<ParseObject>() {
				    public void done(List<ParseObject> scoreList1, ParseException e) {
				        if (e == null) {
				    		//Toast.makeText(getApplicationContext(),scoreList.size(),Toast.LENGTH_SHORT).show();
				        	int len=scoreList1.size(),i=0;
				        	String[] items=new String[len];
				        	for(ParseObject obj1:scoreList1)
				        	{
				        	items[i]=obj1.getString("Item");
				        	i++;
				        	}
				        	items=removeDuplicates(items);
				        		
				        		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
				        				R.layout.blank,items );
				        		aw2.setAdapter(adapter);
				        		
				        		aw2.setOnItemClickListener(new OnItemClickListener() {

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
				//-----------------------------autocomplete complete
		
		
		final TabHost tabhost=(TabHost) findViewById(R.id.tabhost);
		tabhost.setup();
		
		TabSpec spec1=tabhost.newTabSpec("Tab1");
	    spec1.setContent(R.id.tab1);
	    spec1.setIndicator("Form1",null);
	 
	    TabSpec spec2=tabhost.newTabSpec("Tab2");
	    spec2.setContent(R.id.tab2);
	    spec2.setIndicator("Form2",null);
	    
	    tabhost.addTab(spec1);
	    tabhost.addTab(spec2);
	    
	    //------------------Checking Logged in user
	    SharedPreferences details = getSharedPreferences("PhoboPrefsFile", 0);
		
		UserLoggedIn = details.getString("username", "");
		
		//Toast.makeText(getApplicationContext(),UserLoggedIn,Toast.LENGTH_SHORT).show();

		
		//--------------Retrieving previous stored data
		
		ParseQuery<ParseObject> query = ParseQuery.getQuery("Updates");
		query.whereEqualTo("user_name",UserLoggedIn);
		
		query.findInBackground(new FindCallback<ParseObject>() {
		    public void done(List<ParseObject> scoreList, ParseException e) {
		        if (e == null) {
		    		//Toast.makeText(getApplicationContext(),scoreList.size(),Toast.LENGTH_SHORT).show();

		        	for (ParseObject dealsObject : scoreList) {
		        		
		        		phone=dealsObject.getString("phone");
		        		ed11.setText(phone);
		        	}}
		        		 else {
		 		            Log.d("score", "Error: " + e.getMessage());
		 		        }
		 		    }
		 		});
		
		
		//-----------------To modifi price 
		cb1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(cb1.isChecked()==true)
				{
					ed2.setVisibility(View.INVISIBLE);
					//tv6.setVisibility(View.VISIBLE);
					//Toast.makeText(getApplicationContext(), "InVisible",Toast.LENGTH_SHORT).show();

					
				}
				else
				{
					ed2.setVisibility(View.VISIBLE);
					//Toast.makeText(getApplicationContext(), "InVisible",Toast.LENGTH_SHORT).show();

					//tv6.setVisibility(View.INVISIBLE);
					//s4="none";
				}
			}
		});
		
		
		//----------query for college
		ParseQuery<ParseObject> query_col = ParseQuery.getQuery("Updates");
		query_col.whereEqualTo("user_name", UserLoggedIn);
		//query.whereEqualTo("Password", s2);
		query_col.findInBackground(new FindCallback<ParseObject>() {
		    public void done(List<ParseObject> query_list, ParseException e) {
		        if (e == null) {
		            
		        	for(ParseObject obj:query_list)
		        	{
		        	college=obj.getString("college");	
						
		        	}
		        	
		        } else {
		            Log.d("score", "Error: " + e.getMessage());
		        }
		    }
		});
	    
		
		//--------------------------for submit
	    bt1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				phone=ed11.getText().toString();
				city=aw1.getText().toString();
				item=aw2.getText().toString();
				price=ed2.getText().toString();
				description=ed1.getText().toString();
				
			
		
				if(cb1.isChecked()==false)
				{
					price=ed2.getText().toString();
					
				}
				else
				{
					//e4.setVisibility(View.INVISIBLE);
					price="Free";
				}
				if(phone.equals("")||city.equals("")||item.equals("")||price.equals("")||description.equals(""))
				{
			
				Toast.makeText(getApplicationContext(),"INVALID INPUTS",Toast.LENGTH_LONG).show();

			    }
				else
				{
					 picturePath = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("picture_Path", "");
					    if(!picturePath.equals(""))
					    {

							Uri uri = Uri.parse(picturePath);
							Bitmap bitmap;
							
								bitmap = ShrinkBitmap(picturePath, 150, 150);
							    // bitmap = BitmapFactory.decodeFile(picturePath);
								ByteArrayOutputStream blob = new ByteArrayOutputStream();
								bitmap.compress(CompressFormat.PNG, 0 /*ignored for PNG*/, blob);
								byte[] image = blob.toByteArray();
								///*Toast.makeText(getApplicationContext(),picturePath,Toast.LENGTH_SHORT).show();

								

					                // Create the ParseFile
					                ParseFile file = new ParseFile("display_pics.png", image);
					                // Upload the image into Parse Cloud
					                file.saveInBackground();
									//Toast.makeText(getApplicationContext(),"Image saved",Toast.LENGTH_SHORT).show();

					                // Create a New Class called "ImageUpload" in Parse
					                ParseObject imgupload = new ParseObject("Post_created");
					 
					                // Create a column named "ImageName" and set the string
					                imgupload.put("Category",category);
					                imgupload.put("City",city);
					                imgupload.put("Phone",phone);
					                imgupload.put("Item",item);
					                imgupload.put("Price",price);
					                imgupload.put("Description",description);
					                imgupload.put("Image_Path",picturePath);
					                imgupload.put("user_name",UserLoggedIn);
					                imgupload.put("college", college);

					                
					                //ImageLoader imageLoader = new ImageLoader(this);

					                // Create a column named "ImageFile" and insert the image
					                imgupload.put("ImageFile", file);
					 
					                // Create the class and the columns
					                imgupload.saveInBackground();
									//Toast.makeText(getApplicationContext(),"step1",Toast.LENGTH_SHORT).show();

					               
					                
					 
					                // Show a simple toast message
									Toast.makeText(getApplicationContext(),"Image uploaded",Toast.LENGTH_LONG).show();
									Intent move = new Intent(GetDetailsActivity.this,
											AfterLogActivity.class);	
									startActivity(move);
									finish();
						
					    }        
					    else 
					    {
							Toast.makeText(getApplicationContext(),"Upload Valid Image",Toast.LENGTH_SHORT).show();

					    }
				
					
				}
			
			
			
			}
		});
	    
	    bt11.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View arg0) {
                 
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                 
                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });
	    
	}
	
	
	   @Override
	    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	        super.onActivityResult(requestCode, resultCode, data);
	         
	        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
	            Uri selectedImage = data.getData();
	            String[] filePathColumn = { MediaStore.Images.Media.DATA };
	 
	            Cursor cursor = getContentResolver().query(selectedImage,
	                    filePathColumn, null, null, null);
	            cursor.moveToFirst();
	 
	            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
	            picturePath = cursor.getString(columnIndex);
	            PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("picture_Path", picturePath).commit();

	            cursor.close();
	             
	            ImageView imageView = (ImageView) findViewById(R.id.imageView1);
	            tv1=(TextView) findViewById(R.id.textView15);
	           // TextView tva=(TextView) findViewById(R.id.textView15);
	            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
	            
	            //tva.setTextColor(R.drawable.RED);
	            tv1.setText("Image uploaded");
	            
				
	           /* Bitmap bitmap = BitmapFactory.decodeFile(picturePath);
	            // Convert it to byte
	            ByteArrayOutputStream stream = new ByteArrayOutputStream();
	            // Compress image to lower quality scale 1 - 100
	            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
	            byte[] image = stream.toByteArray();

	            // Create the ParseFile
	            ParseFile file = new ParseFile("picturePath", image);
	            // Upload the image into Parse Cloud
	            file.saveInBackground();
	            */
	            
	         
	        }
	     
	     
	    }
	   
	 public  Bitmap ShrinkBitmap(String file, int width, int height){
		   
	     BitmapFactory.Options bmpFactoryOptions = new BitmapFactory.Options();
	        bmpFactoryOptions.inJustDecodeBounds = true;
	        Bitmap bitmap = BitmapFactory.decodeFile(file, bmpFactoryOptions);
	         
	        int heightRatio = (int)Math.ceil(bmpFactoryOptions.outHeight/(float)height);
	        int widthRatio = (int)Math.ceil(bmpFactoryOptions.outWidth/(float)width);
	         
	        if (heightRatio > 1 || widthRatio > 1)
	        {
	         if (heightRatio > widthRatio)
	         {
	          bmpFactoryOptions.inSampleSize = heightRatio;
	         } else {
	          bmpFactoryOptions.inSampleSize = widthRatio; 
	         }
	        }
	         
	        bmpFactoryOptions.inJustDecodeBounds = false;
	        bitmap = BitmapFactory.decodeFile(file, bmpFactoryOptions);
	     return bitmap;
	    }
	 
	 
	   public String getPath(Uri uri)  
		{ 
		    Cursor cursor = getContentResolver().query(uri, null, null, null, null);
		    cursor.moveToFirst();
		    int idx = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
		    cursor.moveToFirst();
		    return cursor.getString(idx);
		} 
	   
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
		getMenuInflater().inflate(R.menu.get_details, menu);
		return true;
	}
	
}