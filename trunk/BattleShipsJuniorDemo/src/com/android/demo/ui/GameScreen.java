package com.android.demo.ui;

import com.android.demo.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.android.demo.data.Game;
import com.android.demo.data.Game.AiMoveResponse;
import com.android.demo.ui.adapter.AiImageAdapter;
import com.android.demo.ui.adapter.ImageAdapter;

public class GameScreen extends Activity {
	
	
	private Game game;
	private AiImageAdapter aiImageAdapter;
	private ImageAdapter playerImageAdapter;
	
	 /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {   	
    	this.game = new Game();
        int[] aiShipFields = {0,1,2};
        int[] playerShipFields = {0,1,2};
        
        game.setAiShip(3, aiShipFields);
        game.setPlayerShip(3, playerShipFields);
        
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
                        
        GridView gridview = (GridView) findViewById(R.id.GridView01);
        aiImageAdapter = new AiImageAdapter(this);
        gridview.setAdapter(aiImageAdapter);
        
        gridview.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                ImageView _iv = (ImageView)v;
                boolean isOpponentShipHit = game.updatePlayerMove(position);
                if(isOpponentShipHit){
                	_iv.setImageResource(R.drawable.red);
                }else{
                	_iv.setImageResource(R.drawable.grey);
                }
                
                if(game.isGameOver()){
                	Toast.makeText(GameScreen.this, "Player is the winner.Game over.", Toast.LENGTH_SHORT).show();
                }else{
                	aiMakeMove();
                	if(game.isGameOver()){
                		Toast.makeText(GameScreen.this, "Ai is the winner.Game over.", Toast.LENGTH_SHORT).show();
                	}
                }
            }
        });
        
        
        GridView playerGridView = (GridView) findViewById(R.id.GridView02);
        playerImageAdapter = new ImageAdapter(this,playerShipFields);
        playerGridView.setAdapter(playerImageAdapter);        
        
        Toast.makeText(GameScreen.this, "Player should make the first move", Toast.LENGTH_SHORT).show();
    }
    
    private void aiMakeMove(){    	
    	AiMoveResponse aiMoveResponse = game.makeMoveForAi();
    	ImageView _iv = (ImageView)playerImageAdapter.getItem(aiMoveResponse.getMoveField());
    	if(aiMoveResponse.isItaHit()){    		
    		_iv.setImageResource(R.drawable.red);
    	}else{
    		_iv.setImageResource(R.drawable.grey);
    	}
    	
    }
    
}
