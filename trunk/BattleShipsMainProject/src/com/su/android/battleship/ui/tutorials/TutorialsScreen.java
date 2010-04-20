package com.su.android.battleship.ui.tutorials;


import java.util.List;


import com.su.android.battleship.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class TutorialsScreen extends Activity {

	private GridView mGrid;

	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tutorials_menu);
						
		mGrid = (GridView) findViewById(R.id.myGrid);
        mGrid.setAdapter(new ImageAdapter(TutorialsScreen.this)); 
        
        mGrid.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View v,int position, long id) {
				Intent mIntent = null;			
				if(position == 0){
					mIntent = new Intent(v.getContext(),AimAndFireTutorial.class);
					startActivity(mIntent);
				}				
			}        	
        });        
	}
	
	public class ImageAdapter extends BaseAdapter {
        private static final int TUTORIAL_MENU_BUTTONS_COUNT = 2;
        private Context mContext;
        private ImageView[] menuItems = new ImageView[TUTORIAL_MENU_BUTTONS_COUNT];

        private Integer[] menuPictures = {
        		R.drawable.aim_and_fire_tutorial,
        		R.drawable.view_minimap
        };

		
		public ImageAdapter(Context c) {
            mContext = c;
        }

        public int getCount() {
            return menuPictures.length;
        }

        public Object getItem(int position) {
            return menuItems[position];
        }       
       
        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
        	ImageView imageView;
            if (convertView == null) {  // if it's not recycled, initialize some attributes
                imageView = new ImageView(mContext);
                imageView.setLayoutParams(new GridView.LayoutParams(185, 65));                
                imageView.setPadding(2, 2, 2, 2);
            } else {
                imageView = (ImageView) convertView;
            }
            
            //ALWAYS use setImageResource and not setBackground - it disables click event somehow...
            imageView.setImageResource(menuPictures[position]);
            
            menuItems[position] = imageView;
            return imageView;
        }
    }
}
