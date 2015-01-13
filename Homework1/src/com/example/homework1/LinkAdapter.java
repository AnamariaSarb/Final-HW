package com.example.homework1;



import java.util.List;

import com.example.homework1.R;
import com.example.homework1.Link;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class LinkAdapter extends ArrayAdapter<Link> {

	private Context mContext;
	private List<Link> mTasks;
	
	public LinkAdapter(Context context, List<Link> objects) {
		super(context, R.layout.link_row_item, objects);
		this.mContext = context;
		this.mTasks = objects;
	}

	public View getView(int position, View convertView, ViewGroup parent){
		if(convertView == null){
			LayoutInflater mLayoutInflater = LayoutInflater.from(mContext);
			convertView = mLayoutInflater.inflate(R.layout.link_row_item, null);
		}
		
		Link task = mTasks.get(position);
		
		TextView descriptionView = (TextView) convertView.findViewById(R.id.link_description);
		
		descriptionView.setText(task.getDescription());
		
		if(task.isVisited()){
			descriptionView.setPaintFlags(descriptionView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
		}else{
			descriptionView.setPaintFlags(descriptionView.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
		}
		
		return convertView;
	}

}
