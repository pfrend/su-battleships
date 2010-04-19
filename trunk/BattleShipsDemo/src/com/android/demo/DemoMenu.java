package com.android.demo;

import java.util.concurrent.Executors;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.demo.ui.GameScreen;

public class DemoMenu extends Activity {
	/** Called when the activity is first created. */

	
	private TextView textView;	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.first_screen);

		Button continueButton = (Button) findViewById(R.id.Button01);
		continueButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				 Intent mIntent = new Intent(view.getContext(),GameScreen.class);
				 //intent is configured so that the next Activity will display load_screen in the beginning
				 mIntent.putExtra("loading_screen", true);
				 startActivity(mIntent);				
			}
		});
	}

	

}
