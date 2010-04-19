package com.android.demo.data;

import com.android.demo.ai_factory.AiFactory;

public class Game {

	public static final String NOT_ATTACKED = "na";
	public static final String ATTACKED = "a";
	public static final int BOARD_SIZE = 9;
	//private static final int GAME_OVER = 0;
	//private static final int GAME_NOT_OVER = 1;
	
	//board with player's ships
	private String[] playerBoard;
	
	//board with ai's ships
	private String[] aiBoard;
	
	private Ship playerShip;
	private Ship aiShip;
	
	private AiPlayer bot;
	
	/**
	 * @return the playerBoard
	 */
	public String[] getPlayerBoard() {
		return playerBoard;
	}

	/**
	 * @param playerBoard the playerBoard to set
	 */
	public void setPlayerBoard(String[] playerBoard) {
		this.playerBoard = playerBoard;
	}

	/**
	 * @return the aiBoard
	 */
	public String[] getAiBoard() {
		return aiBoard;
	}

	/**
	 * @param aiBoard the aiBoard to set
	 */
	public void setAiBoard(String[] aiBoard) {
		this.aiBoard = aiBoard;
	}
	
	public Game() {
		playerBoard = new String[BOARD_SIZE];
		aiBoard = new String[BOARD_SIZE];
		for(int i = 0 ; i < BOARD_SIZE ; i++){
			playerBoard[i] = NOT_ATTACKED;
			aiBoard[i] = NOT_ATTACKED;
		}
		
		bot = AiFactory.getInstance().produceAiPlayer(0,this);
	}
	
	public void setPlayerShip(int len,int[] fields){
		playerShip = new Ship(len,fields);
	}
	
	public void setAiShip(int len,int[] fields){
		aiShip = new Ship(len,fields);
	}
	/**
	 * @return the playerShip
	 */
	public Ship getPlayerShip() {
		return playerShip;
	}

	/**
	 * @return the aiShip
	 */
	public Ship getAiShip() {
		return aiShip;
	}

	//return true if shot hit a ship
	public boolean updatePlayerMove(int field){
		aiBoard[field] = ATTACKED;
		return playerShip.updateShipState(field);
	}

	//return true if shot hit a ship
	public AiMoveResponse makeMoveForAi(){
		int field = bot.generateMove();
		playerBoard[field] = ATTACKED;
		boolean isHit = aiShip.updateShipState(field);
		return new AiMoveResponse(field,isHit);
	}
	
	public boolean isGameOver(){
		if(playerShip.isShipDestroyed()){
			return true;
		}
		if(aiShip.isShipDestroyed()){
			return true;
		}
		return false;
	}

	public class Ship {
		private int length;
		private int hitCounts = 0;
		private int[] boardFields;
		private boolean[] fieldsState;// true-not hit / false - hit
		
		/**
		 * @return the boardFields
		 */
		public int[] getBoardFields() {
			return boardFields;
		}
		
		public Ship(int length,int[] _fields) {
			this.length = length;
			//TODO : check for correctness of _fields length
			this.boardFields = _fields;
			this.fieldsState = new boolean[length];
			for(int i = 0 ; i < this.fieldsState.length ; i++){
				this.fieldsState[i] = true;
			}
		}
		/**
		 * 
		 * @param attackedField - the attacked field 
		 * @return weather ship was hit or missed
		 */
		public boolean updateShipState(int attackedField){
			for(int i = 0 ; i < boardFields.length ; i++){
				if(boardFields[i] == attackedField){
					hitCounts++;
					fieldsState[i] = false;
					return true;
				}
			}
			return false;
		}
				
		public boolean isShipDestroyed(){
			return this.length == hitCounts;
		}
	}
	
	public class AiMoveResponse{
		private int moveField;
		private boolean isItaHit;
		
		public AiMoveResponse(int m,boolean h) {
			this.moveField = m;
			this.isItaHit = h;
		}

		/**
		 * @return the moveField
		 */
		public int getMoveField() {
			return moveField;
		}

		/**
		 * @return the isItaHit
		 */
		public boolean isItaHit() {
			return isItaHit;
		}
		
		
	}

}
