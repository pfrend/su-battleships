package com.su.android.battleship.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.su.android.battleship.R;

/**
 * First application screen. The main menu - Play, Options, Credits, Quit
 * 
 * @author Nikola
 *
 */
public class MainMenuScreen extends Activity implements OnClickListener {
	
	/**
	 * Called when activity is starting
	 * 
	 * @param savedInstanceState
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_menu);
		
		Button buttonPlay = (Button) findViewById(R.id.ButtonPlay);
		buttonPlay.setOnClickListener(this);
		
		Button buttonOptions = (Button) findViewById(R.id.ButtonOptions);
		buttonOptions.setOnClickListener(this);
		
		Button buttonAbout = (Button) findViewById(R.id.ButtonAbout);
		buttonAbout.setOnClickListener(this);
		
		Button buttonQuit = (Button) findViewById(R.id.ButtonQuit);
		buttonQuit.setOnClickListener(this);
	}

	/**
	 * Called when a menu button has been clicked
	 * 
	 * @param v button instance
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ButtonPlay:
			Intent intent = new Intent(this, GameMenuScreen.class);
			startActivity(intent);
			break;
		case R.id.ButtonOptions:
			break;
		case R.id.ButtonAbout:
			break;
		case R.id.ButtonQuit:
			finish();
			break;
		}
	}
	
}
