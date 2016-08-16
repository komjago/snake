package jkomjago.snake.common;

/**
 * przyporządkuje liczbę kierunkowi
 * @author J.Komjago
 */
public class Direction {
	/**	zmiana położenia do góry*/
	public static final Position UP = new Position(0,-1);
	/**	zmiana położenia w dół */
	public static final Position DOWN = new Position(0,1);
	/**	zmiana położenia w prawo */
	public static final Position RIGHT = new Position(1,0);
	/**	zmiana położenia w lewo */
	public static final Position LEFT = new Position(-1,0);
	/** przechowuje zmianę położenia*/
	private Position direction;
	
	/**
	 * Konstruktor
	 * 
	 * @param dir
	 */
	public Direction(final Position dir) {
		direction = new Position(dir);
	}
	/**
	 * @return direction
	 */
	public Position getDirection() {
		return direction;
	}
	/**
	 * @param direction
	 */
	public void setDirection(final Position direction) {
		this.direction = direction;
	}
	
}
