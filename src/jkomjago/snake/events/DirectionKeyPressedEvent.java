package jkomjago.snake.events;

import jkomjago.snake.common.Direction;

/**
 * Ten event tworzy się gdy naciścniemy klawisz
 * @author J.Komjago
 */
public class DirectionKeyPressedEvent extends Event {
	private int playerId;
	private Direction dir;

	/**
	 * konstruktor
	 */
	public DirectionKeyPressedEvent(Direction d,int playerId){
		dir = d;
		this.playerId=playerId;
	}
	/**
	 * @return kierunek
	 */
	public Direction getDir() {
		return dir;
	}
	/**
	 * @return playerId - id gracza
	 */
	public int getPlayerId() {
		return playerId;
	}

}
