package com.su.android.battleship.ui.tutorials;

import java.util.ArrayList;
import java.util.List;

import com.su.android.battleship.R;
import com.su.android.battleship.data.BoardFieldStatus;
import com.su.android.battleship.data.Game;
import com.su.android.battleship.data.GameAi;
import com.su.android.battleship.data.Ship;
import com.su.android.battleship.data.ShipPositionGenerator;
import com.su.android.battleship.data.ai.AiFactory;
import com.su.android.battleship.data.ai.iface.AiPlayer;
import com.su.android.battleship.ui.adapter.GameBoardImageAdapter;
import com.su.android.battleship.ui.adapter.MinimapImageAdapter;

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

	private GameAi game;
	private GridView minimapGrid;
	private MinimapImageAdapter minimapImageAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
	}

	protected void displayGameScreen() {
		setContentView(R.layout.fixed_ship_game);
		
		fireButton = (ImageView) findViewById(R.id.ImageViewFB);

		boardGrid = (GridView) findViewById(R.id.GridViewAFD);
		boardImageAdapter = new GameBoardImageAdapter(this);
		boardGrid.setAdapter(boardImageAdapter);

		minimapGrid = (GridView) findViewById(R.id.GridViewMiniMap);
		short[] playerShipFields = getPlayerShipFields();
		minimapImageAdapter = new MinimapImageAdapter(this, playerShipFields);
		minimapGrid.setAdapter(minimapImageAdapter);
	}

	protected void attachActionListeners() {		
		// boardGame GridView listener sets the aimedField property and changes
		// aim color
		boardGrid.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				ImageView _iv = (ImageView) v;
				// means that there is second click on the same button - FIRE !
				if (aimedField == position) {
					executeFire(_iv);
					continueGameProcess();
				} else {
					if (aimedField != NO_FIELD_IS_AIMED) {
						ImageView _oldSelectedField = (ImageView) boardImageAdapter
								.getItem(aimedField);
						_oldSelectedField.setImageResource(R.drawable.blue);
					}
					_iv.setImageResource(R.drawable.yellow);
					aimedField = position;
				}
			}
		});

		fireButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if (aimedField == NO_FIELD_IS_AIMED) {					
					Toast.makeText(FixShipGameTutorial.this,
							"Aim board field in order to fire.", 1000).show();
				} else {
					ImageView field = (ImageView) boardGrid
							.getItemAtPosition(aimedField);
					executeFire(field);
					if(game.isGameOver()){
						Toast.makeText(FixShipGameTutorial.this,
								"Game over.Winner is the player.", 1000).show();
						return;
					}else{
						continueGameProcess();
					}					
				}
			}
		});
	}

	private void executeFire(ImageView _iv) {
		short newFieldStatus = game.executeMove((short) 0, (short) aimedField);
		if (BoardFieldStatus.isShipAttackedStatus(newFieldStatus)) {
			_iv.setImageResource(R.drawable.red);// mark as fired
		} else {
			_iv.setImageResource(R.drawable.grey);// mark as fired
		}
		_iv.setClickable(false);// make imageView unclickable
		aimedField = NO_FIELD_IS_AIMED;
	}

	protected void initGame() {
		ShipPositionGenerator generator = new ShipPositionGenerator();

//		Ship[] generatedShipPosition1 = generator.getHardcodedShipPosition();
//		Ship[] generatedShipPosition2 = generator.getHardcodedShipPosition();
		
		Ship[] generatedShipPosition1 = generator.getRandomShipsPosition();
		Ship[] generatedShipPosition2 = generator.getRandomShipsPosition();

		game = new GameAi((short) 0, generatedShipPosition1,
				generatedShipPosition2);
	}

	private void continueGameProcess() {
		// TODO : here should be implemented logic that communicates with a
		// GameProcessWrapper
		// in order to continue the game process - to obtain second players move
		// from a
		// configurate place - Ai , BlueTooth , Http client/server communication
		GameAi.AiMoveResponse moveResponse = game.makeMoveForAi();
		short fieldAttackedByAi = moveResponse.getMoveField();
		short fieldStatus = game.getPlayerBoard(GameAi.PLAYER_INDEX)[fieldAttackedByAi];

		ImageView _iv = (ImageView) minimapImageAdapter
				.getItem(fieldAttackedByAi);
		if (BoardFieldStatus.isShipAttackedStatus(fieldStatus)) {
			// change minimap field color to red - ship is hit
			_iv.setImageResource(R.drawable.red);
			// other way is to use public resource ids stored in the adapter as
			// it should be responsible for the gridView's rendering
		} else {
			// change it to grey - water is hit
			_iv.setImageResource(R.drawable.grey);
		}
		
		if(game.isGameOver()){
			Toast.makeText(FixShipGameTutorial.this,
					"Game over.Winner is the AI.", 1000).show();
		}
	}

	private short[] getPlayerShipFields() {
		List<Short> resultList = new ArrayList<Short>();
		Ship[] playerShips = game.getPlayerShips(GameAi.PLAYER_INDEX);
		Ship tempShip;
		for (short i = 0; i < playerShips.length; i++) {
			tempShip = playerShips[i];
			for (short j = 0; j < tempShip.getLength(); j++) {
				resultList.add(tempShip.getBoardFields()[j]);
			}
		}
		short[] result = new short[resultList.size()];
		for (short k = 0; k < result.length; k++) {
			result[k] = resultList.get(k);
		}
		return result;
	}
}
