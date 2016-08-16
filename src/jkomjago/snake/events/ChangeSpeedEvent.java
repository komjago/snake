package jkomjago.snake.events;

/**
 * zmieniono prędkość w widoku
 * 
 * @author J.Komjago
 */
public class ChangeSpeedEvent extends Event {
	private int speed;
	/**
	 * konstruktor
	 */
	public ChangeSpeedEvent(final int s){
		speed=s;
	}
	/**
	 * @return żądana prędkość
	 */
	public int getSpeed() {
		return speed;
	}
}
