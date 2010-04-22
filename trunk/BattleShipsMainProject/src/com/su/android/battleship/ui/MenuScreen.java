package com.su.android.battleship.ui;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.su.android.battleship.R;
import com.su.android.battleship.ui.tutorials.TutorialsScreen;

public class MenuScreen extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_menu);
		
		Button continueButton = (Button) findViewById(R.id.ButtonPlay);
		continueButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				 Intent mIntent = new Intent(view.getContext(),TutorialsScreen.class);
				 //intent is configured so that the next Activity will display load_screen in the beginning
//				 mIntent.putExtra("loading_screen", true);
				 startActivity(mIntent);				
			}
		});
	}
}
