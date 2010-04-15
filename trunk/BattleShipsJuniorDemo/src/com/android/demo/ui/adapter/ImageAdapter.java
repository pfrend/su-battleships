package com.android.demo.ui.adapter;

import com.android.demo.*;
import com.android.demo.data.Game;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
	private Context mContext;
	private int[] shipPositions;

    public ImageAdapter(Context c,int[] shipPositions) {
        mContext = c;
        this.shipPositions = shipPositions; 
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return aiFields[position];
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {  // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(45, 45));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(2, 2, 2, 2);
        } else {
            imageView = (ImageView) convertView;
        }      
        
        if(isShipPosition(position)){
        	imageView.setImageResource(R.drawable.yellow);
        }else{
        	imageView.setImageResource(R.drawable.blue);
        }
        
        imageView.setClickable(false);
        
        aiFields[position] = imageView;
        return imageView;
    }

    // references to our images
    private Integer[] mThumbIds = {
    		R.drawable.sample_0, R.drawable.sample_1,
            R.drawable.sample_2, R.drawable.sample_3,
            R.drawable.sample_4, R.drawable.sample_5,
            R.drawable.sample_6, R.drawable.sample_7,
            R.drawable.sample_0
    };
    
    private ImageView[] aiFields = new ImageView[Game.BOARD_SIZE];

    private boolean isShipPosition(int position){
    	for(int i = 0 ; i < shipPositions.length ; i++){
    		if(position == shipPositions[i]){
    			return true;
    		}
    	}
    	return false;
    }
}