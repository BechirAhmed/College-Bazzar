package com.example.college_bazzar;


import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ListViewAdapter extends BaseAdapter {

	// Declare Variables
	Context context;
	LayoutInflater inflater;
	ImageLoader imageLoader;
	private List<Post_Show> worldpopulationlist = null;
	private ArrayList<Post_Show> arraylist;
	String object_id;

	public ListViewAdapter(Context context,
			List<Post_Show> worldpopulationlist) {
		this.context = context;
		this.worldpopulationlist = worldpopulationlist;
		inflater = LayoutInflater.from(context);
		this.arraylist = new ArrayList<Post_Show>();
		this.arraylist.addAll(worldpopulationlist);
		imageLoader = new ImageLoader(context);
	}

	public class ViewHolder {
		TextView item;
		TextView price;
		TextView location;
		TextView date;
		TextView college;
		TextView username;

		ImageView pic;
	}

	@Override
	public int getCount() {
		return worldpopulationlist.size();
	}

	@Override
	public Object getItem(int position) {
		return worldpopulationlist.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public View getView(final int position, View view, ViewGroup parent) {
		final ViewHolder holder;
		if (view == null) {
			holder = new ViewHolder();
			view = inflater.inflate(R.layout.list_sample, null);
			// Locate the TextViews in listview_item.xml
			holder.item = (TextView) view.findViewById(R.id.textView1);
			holder.price = (TextView) view.findViewById(R.id.textView5);
			holder.username = (TextView) view.findViewById(R.id.textView3);
			holder.location = (TextView) view.findViewById(R.id.textView4);
			holder.college = (TextView) view.findViewById(R.id.textView2);
			//holder.date = (TextView) view.findViewById(R.id.textView6);

			
			// Locate the ImageView in listview_item.xml
			holder.pic = (ImageView) view.findViewById(R.id.imageView1);
			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}
		// Set the results into TextViews
		holder.item.setText(" "+worldpopulationlist.get(position).getItem());
		holder.price.setText("Price "+worldpopulationlist.get(position).getPrice());
		holder.location.setText("City "+worldpopulationlist.get(position).getLocation());
		holder.username.setText("User "+worldpopulationlist.get(position).getUsername());
		holder.college.setText(worldpopulationlist.get(position).getCollege());
		//object_id=worldpopulationlist.get(position).getObjectid();
		//Toast.makeText(context, worldpopulationlist.get(position).getObjectid(), Toast.LENGTH_SHORT).show();

		//holder.date.setText(worldpopulationlist.get(position)
			//	.getDate().toString());
		// Set the results into ImageView
		imageLoader.DisplayImage(worldpopulationlist.get(position).getPic(),
				holder.pic);
		// Listen for ListView Item Click
		view.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// Send single item click data to SingleItemView Class
				object_id=worldpopulationlist.get(position).getObjectid();
				SharedPreferences settings = context.getSharedPreferences("last_work",0);
        	    SharedPreferences.Editor editor = settings.edit();
        	    
        	   // editor.putString("username", 	   s1);
        	    editor.putString("objectid", object_id );
	        	//Toast.makeText(context, object_id, Toast.LENGTH_SHORT).show();

        	    // Commit the edits!
        	    editor.commit();
				Intent intent = new Intent(context, PostEnlarge2Activity.class);
				
				
				// Pass all data rank
				/*intent.putExtra("rank",
						(worldpopulationlist.get(position).getRank()));
				// Pass all data country
				intent.putExtra("country",
						(worldpopulationlist.get(position).getCountry()));
				// Pass all data population
				intent.putExtra("population",
						(worldpopulationlist.get(position).getPopulation()));
				// Pass all data flag
				intent.putExtra("flag",
						(worldpopulationlist.get(position).getFlag()));
				// Start SingleItemView Class*/
				context.startActivity(intent);
			}

		});
		
	return view;}
}
	
