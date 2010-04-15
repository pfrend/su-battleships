package com.android.demo;

import com.android.demo.ui.GameScreen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DemoMenu extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.first_screen);

		Button continueButton = (Button) findViewById(R.id.Button01);
		continueButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Intent mIntent = new Intent(view.getContext(), GameScreen.class);
				startActivity(mIntent);
			}
		});
	}

}
