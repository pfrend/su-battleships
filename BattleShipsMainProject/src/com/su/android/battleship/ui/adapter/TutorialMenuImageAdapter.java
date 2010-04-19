package com.su.android.battleship.ui.adapter;

import com.su.android.battleship.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class TutorialMenuImageAdapter extends BaseAdapter {
	
	private static final int FIELDS_COUNT = 100;
	
	private Context mContext;	
	private ImageView[] boardFields = new ImageView[FIELDS_COUNT];

    public TutorialMenuImageAdapter(Context c) {
        mContext = c;        
    }

    /**
     * Very important method - this tells android UI framework how many times to call getView
     */
    public int getCount() {
        return FIELDS_COUNT;
    }

    public Object getItem(int position) {
        return boardFields[position];
    }

    public long getItemId(int position) {
        return position;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {  // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(25, 25));            
            imageView.setPadding(1, 1, 1, 1);
        } else {
            imageView = (ImageView) convertView;
        }
        imageView.setImageResource(boardPictures[0]);
        
        boardFields[position] = imageView;
        return imageView;
    }

    // references to our images
    private Integer[] boardPictures = {
    		R.drawable.blue, R.drawable.red
    };
}
