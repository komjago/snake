package jkomjago.snake.controller;

import jkomjago.snake.common.Direction;
import jkomjago.snake.events.ChangeSpeedEvent;
import jkomjago.snake.events.Event;
import jkomjago.snake.events.DirectionKeyPressedEvent;
import jkomjago.snake.events.PlayerReadyEvent;
import jkomjago.snake.events.SetPlayerCountEvent;
import jkomjago.snake.events.ShowScoreEvent;
import jkomjago.snake.events.TimerEvent;

/**
 * obsługa zdarzeń
 * @author J.Komjago
 */
class EventHandler {
	Controller control;
	/**
	 * konstruktor
	 */
	public EventHandler(Controller c){
		control=c;
	}
	/**
	 * rozpoznaje typ zdarzenia
	 */
	public void handle(Event e){
		if (e instanceof TimerEvent){
			handleTimerEvent();
		}
		else if (e instanceof DirectionKeyPressedEvent){
			handleDirEvent((DirectionKeyPressedEvent)e);
		}	
		else if (e instanceof ShowScoreEvent){
			control.showHighScore();
		}
		else if (e instanceof PlayerReadyEvent){
			handlePlayerReadyEvent();
		}	
		else if (e instanceof ChangeSpeedEvent){
			control.setSpeed(((ChangeSpeedEvent) e).getSpeed());
		}
		else if (e instanceof SetPlayerCountEvent){
			control.setTwoPlayers(((SetPlayerCountEvent) e).twoPlayers());
		}
	}
	private void handlePlayerReadyEvent() {
		control.restart();
	}
	/**
	 * zdarzenie to naciśnięty klawisz
	 */
	private void handleDirEvent(DirectionKeyPressedEvent e) {
		Direction dir=e.getDir();
		control.dirChange(dir.getDirection(),e.getPlayerId());
	}
	/**
	 * zdarzenie to zegar
	 */
	private void handleTimerEvent() {
		control.refresh();	
	}
}
