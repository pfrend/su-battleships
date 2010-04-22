package com.su.data;

public class ShipFieldsHolder {
	
	private int length;
	private int firstField;
	private Direction direction;
	
	public ShipFieldsHolder(int length, int firstField, Direction direction) {
		super();
		this.length = length;
		this.firstField = firstField;
		this.direction = direction;
	}

	/**
	 * @return the length
	 */
	public int getLength() {
		return length;
	}

	/**
	 * @param length the length to set
	 */
	public void setLength(int length) {
		this.length = length;
	}

	/**
	 * @return the firstField
	 */
	public int getFirstField() {
		return firstField;
	}

	/**
	 * @param firstField the firstField to set
	 */
	public void setFirstField(int firstField) {
		this.firstField = firstField;
	}

	/**
	 * @return the direction
	 */
	public Direction getDirection() {
		return direction;
	}

	/**
	 * @param direction the direction to set
	 */
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null){
			return false;
		}
		
		if(!(obj instanceof ShipFieldsHolder)){
			return false;
		}
		
		ShipFieldsHolder sfh = (ShipFieldsHolder)obj;
		if(this.length == sfh.length && this.firstField == sfh.firstField
				&& this.direction.equals(sfh.direction)){
			return true;
		}
		return false;
	}
	
	
}
