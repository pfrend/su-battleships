package com.su.android.battleship.ui.tutorials;

import com.su.android.battleship.R;
import com.su.android.battleship.data.BoardFieldStatus;
import com.su.android.battleship.data.Game;
import com.su.android.battleship.data.GameAi;
import com.su.android.battleship.data.Ship;
import com.su.android.battleship.data.ShipPositionGenerator;
import com.su.android.battleship.data.ai.AiFactory;
import com.su.android.battleship.data.ai.iface.AiPlayer;
import com.su.android.battleship.ui.adapter.TutorialMenuImageAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class FixShipGameTutorial extends AimAndFireTutorial {
	
	protected Game game;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		super.displayGameScreen();
		attachActionListeners();
		initGame();
	}	
	
	protected void attachActionListeners(){		
		//boardGame GridView listener sets the aimedField property and changes aim color
		boardGrid.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,int position, long id) {
				ImageView _iv = (ImageView) v;
				//means that there is second click on the same button - FIRE !
				if(aimedField == position){
					executeFire(_iv);
				}else{
					if(aimedField != NO_FIELD_IS_AIMED){
						ImageView _oldSelectedField = (ImageView)imageAdapter.getItem(aimedField);
						_oldSelectedField.setImageResource(R.drawable.blue);
					}
					_iv.setImageResource(R.drawable.yellow);
					aimedField = position;
				}				
			}
		});
		
		fireButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				if(aimedField == NO_FIELD_IS_AIMED){
					Toast.makeText(FixShipGameTutorial.this, "Aim board field in order to fire.", 1000).show();
				}else{
					ImageView field = (ImageView) boardGrid.getItemAtPosition(aimedField);
					executeFire(field);
				}
			}
		});		
	}
	
	private void executeFire(ImageView _iv){
		short newFieldStatus = game.executeMove((short)0, (short)aimedField);
		if(BoardFieldStatus.isShipAttackedStatus(newFieldStatus)){
			_iv.setImageResource(R.drawable.red);//mark as fired
		}else{
			_iv.setImageResource(R.drawable.grey);//mark as fired
		}		
		_iv.setClickable(false);//make imageView unclickable
		aimedField = NO_FIELD_IS_AIMED;
	}
	
	protected void initGame(){
		ShipPositionGenerator generator = new ShipPositionGenerator();
		
		Ship[] generatedShipPosition1 = generator.getHardcodedShipPosition();
		Ship[] generatedShipPosition2 = generator.getHardcodedShipPosition();
		
		game = new GameAi((short)0,generatedShipPosition1,generatedShipPosition2);		
	}
}
