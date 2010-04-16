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

	private ProgressBar progressBar;
	private TextView textView;

	private MyAsyncTask progressBarTask;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.first_screen);

		Button continueButton = (Button) findViewById(R.id.Button01);
		continueButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				// Intent mIntent = new Intent(view.getContext(),
				// GameScreen.class);
				// startActivity(mIntent);

				// start gameLoading thread
				GameLoadingThread gameLoadingThread = new GameLoadingThread(handler);
				gameLoadingThread.execute("Loading...");

				// start progressBar worker thread
				progressBarTask = new MyAsyncTask();
				progressBarTask.execute("Loading");
			}
		});
	}

	// Define the Handler that receives messages from the gameLoading thread and
	// update the
	// speed of the progressBar thread
	final Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			boolean isLoaded = msg.getData().getBoolean("isLoaded");
			if (isLoaded) {
				progressBarTask.setStatus(MyAsyncTask.STATUS_FINISHED);
			}
		}
	};

	private class GameLoadingThread extends AsyncTask<Object, Void, Void> {

		private Handler mHandler;

		public GameLoadingThread(Handler h) {
			mHandler = h;
		}

		@Override
		protected Void doInBackground(Object... params) {
			// TODO : load game here

			// Game load will consume some time and meanwhile the progressbar
			// will
			// progress slowly.After game is loaded message will be send to this
			// thread's handler and
			// the handler will force the progressBar thread to finish its work
			// faster.

			// TODO : remove this in beta version
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
			Log.d(GameLoadingThread.class.toString(), "GAME LOADED");
			return null;
		}

	}

	private class MyAsyncTask extends AsyncTask<String, Integer, String> {

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
					Log.d(MyAsyncTask.class.toString(), "INTERVAL OF 1000");
				} catch (InterruptedException e) {

				}
				publishProgress(i);
			}
			while (i < MAX_PROGRESS_STEPS) {
				try {
					Thread.sleep(20);
					i++;
					Log.d(MyAsyncTask.class.toString(), "INTERVAL OF 100");
				} catch (InterruptedException e) {

				}
				publishProgress(i);
			}
			Log.d(MyAsyncTask.class.toString(), "PROGRESS DONE");
			return "MyAsyncTask done successfully";
		}

		protected void onPostExecute(String result) {
			Log.d(MyAsyncTask.class.toString(), "BEFORE LOADING GAME_SCREEN");			
			Intent mIntent = new Intent(DemoMenu.this, GameScreen.class);
			startActivity(mIntent);
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
