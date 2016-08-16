package jkomjago.snake.events;

/**
 * @author J.Komjago
 *
 */
public class PlayerReadyEvent extends Event {
	private int id;

	public PlayerReadyEvent(final int id)
	{
		this.setId(id);
	}

	public int getId() {
		return id;
	}

	private void setId(int id) {
		this.id = id;
	}
}
