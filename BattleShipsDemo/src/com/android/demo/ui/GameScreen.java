package com.android.demo.ui;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.android.demo.R;
import com.android.demo.data.Game;
import com.android.demo.data.Game.AiMoveResponse;
import com.android.demo.ui.adapter.AiImageAdapter;
import com.android.demo.ui.adapter.ImageAdapter;

public class GameScreen extends Activity {

	private Game game;
	private AiImageAdapter aiImageAdapter;
	private ImageAdapter playerImageAdapter;
	
	private ProgressBar progressBar;
	private LoadScreenAsyncTask progressBarTask;
	
	private boolean isLoadingScreen;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);			
		isLoadingScreen = getIntent().getBooleanExtra("loading_screen", false);
		// start gameLoading thread
		GameLoadingThread gameLoadingThread = new GameLoadingThread(handler);
		gameLoadingThread.execute("Loading...");

		// start progressBar worker thread if Activiry is configured to display the loading_screen
		if(isLoadingScreen){
			progressBarTask = new LoadScreenAsyncTask();
			progressBarTask.execute("Loading");
		}else{
			displayGameScreen();
		}
	}

	private void aiMakeMove() {
		AiMoveResponse aiMoveResponse = game.makeMoveForAi();
		ImageView _iv = (ImageView) playerImageAdapter.getItem(aiMoveResponse
				.getMoveField());
		if (aiMoveResponse.isItaHit()) {
			_iv.setImageResource(R.drawable.red);
		} else {
			_iv.setImageResource(R.drawable.grey);
		}

	}
	
	// Define the Handler that receives messages from the gameLoading thread and
	// update the
	// speed of the progressBar thread
	final Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			if(!isLoadingScreen){
				return;
			}
			boolean isLoaded = msg.getData().getBoolean("isLoaded");
			if (isLoaded) {
				progressBarTask.setStatus(LoadScreenAsyncTask.STATUS_FINISHED);
			}
		}
	};
	
	private void displayGameScreen(){
		setContentView(R.layout.main);
		
		GridView gridview = (GridView) findViewById(R.id.GridView01);			
		gridview.setAdapter(aiImageAdapter);

		gridview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,int position, long id) {
				ImageView _iv = (ImageView) v;
//				ImageView _iv = (ImageView) aiImageAdapter.getItem(position);
//				if(!_iv.isClickable()){
//					Toast.makeText(GameScreen.this,
//							"WTF. "+_iv.isClickable(),
//							Toast.LENGTH_SHORT).show();
//					return;
//				}
				
				boolean isOpponentShipHit = game.updatePlayerMove(position);
				if (isOpponentShipHit) {
					_iv.setImageResource(R.drawable.red);
				} else {
					_iv.setImageResource(R.drawable.grey);
				}
				_iv.setClickable(false);

				if (game.isGameOver()) {
					Toast.makeText(GameScreen.this,
							"Player is the winner.Game over.",
							Toast.LENGTH_SHORT).show();
				} else {
					aiMakeMove();
					if (game.isGameOver()) {
						Toast.makeText(GameScreen.this,
								"Ai is the winner.Game over.",
								Toast.LENGTH_SHORT).show();
					}
				}
			}
		});

		GridView playerGridView = (GridView) findViewById(R.id.GridView02);			
		playerGridView.setAdapter(playerImageAdapter);
		playerGridView.setOnItemClickListener(new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int arg2,
					long arg3) {
				if(!view.isClickable()){
					return;
				}
				Toast.makeText(GameScreen.this, "The position clicked is: "+arg2, Toast.LENGTH_SHORT).show();
			}			
		});
		
		Toast.makeText(GameScreen.this, "Player should make the first move",
				Toast.LENGTH_SHORT).show();
	}

	private class GameLoadingThread extends AsyncTask<Object, Void, Void> {

		private Handler mHandler;

		public GameLoadingThread(Handler h) {
			mHandler = h;
		}

		@Override
		protected Void doInBackground(Object... params) {
			GameScreen.this.game = new Game();
			int[] aiShipFields = { 0, 1, 2 };
			int[] playerShipFields = { 0, 1, 2 };

			game.setAiShip(3, aiShipFields);
			game.setPlayerShip(3, playerShipFields);			
			
			//set imageAdapters that will be passed to the gameboards' gridViews in the UI thread 
			aiImageAdapter = new AiImageAdapter(GameScreen.this);
			playerImageAdapter = new ImageAdapter(GameScreen.this, game.getPlayerShip().getBoardFields());
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}

			Message msg = mHandler.obtainMessage();
			Bundle b = new Bundle();
			b.putBoolean("isLoaded", true);
			msg.setData(b);
			mHandler.sendMessage(msg);			
			return null;
		}

	}

	private class LoadScreenAsyncTask extends AsyncTask<String, Integer, String> {

		private static final int MAX_PROGRESS_STEPS = 15;
		private int status = STATUS_RUNNING_NORMAL;
		public static final int STATUS_RUNNING_NORMAL = 1;;
		public static final int STATUS_RUNNING_FAST = 2;
		public static final int STATUS_FINISHED = 3;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			setContentView(R.layout.progressbar_loadscreen);
			progressBar = (ProgressBar) findViewById(R.id.progress_horizontal);
			progressBar.setMax(MAX_PROGRESS_STEPS);
			progressBar.setProgressDrawable(getResources().getDrawable(
					R.drawable.progress_horizontal));
		}

		protected void onProgressUpdate(Integer... progress) {
			progressBar.setProgress(progress[0]);
		}

		@Override
		protected String doInBackground(String... params) {
			int i = 0;
			while (status == STATUS_RUNNING_NORMAL) {
				try {
					Thread.sleep(200);
					i++;					
				} catch (InterruptedException e) {

				}
				publishProgress(i);
			}
			while (i < MAX_PROGRESS_STEPS) {
				try {
					Thread.sleep(20);
					i++;					
				} catch (InterruptedException e) {

				}
				publishProgress(i);
			}			
			return "MyAsyncTask done successfully";
		}

		/**
		 * Executed by UI thread after task has finished its job
		 */
		protected void onPostExecute(String result) {		
			displayGameScreen();
		}

		/**
		 * This method is used to tell the thread whether it is time to force
		 * faster progress of the progressBar and whether it is time to
		 * terminate
		 * 
		 * @param status
		 */
		public void setStatus(int status) {
			this.status = status;
		}

	}

}
