package com.su.data;

/**
 * this class is implemented with the default understanding that the fields index order
 * is the natural order of a table's fileds - from left to right , and from first row to the last.
 * The same default logic is used in {@link ShipFieldsHolder}
 * @author vasil.konstantinov
 *
 */
public class BoardField {
		
	public int x;
	public int y;

	public BoardField(int x, int y) {
		this.x = x;
		this.y = y;		
	}
	
	public static BoardField getBoardFieldFromIndex(int index,int boardSideSize) {
		int x = index % boardSideSize;
		int y = index / boardSideSize;

		return new BoardField(x, y);
	}

	public static int getPositionFromBoardField(BoardField bf,int boardSideSize) {
		int position = bf.y * boardSideSize + bf.x;
		return position;
	}
}