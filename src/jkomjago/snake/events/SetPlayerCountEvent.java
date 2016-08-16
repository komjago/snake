package jkomjago.snake.events;

public class SetPlayerCountEvent extends Event{
	int players;

	public SetPlayerCountEvent(int players)
	{
		this.players=players;
	}
	
	public boolean twoPlayers(){
		return (players > 1);
	}
}
