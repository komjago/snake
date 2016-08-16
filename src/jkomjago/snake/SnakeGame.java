package jkomjago.snake;

import java.util.concurrent.LinkedBlockingQueue;

import jkomjago.snake.controller.Controller;
import jkomjago.snake.events.Event;
import jkomjago.snake.view.View;

/**
 * Snake game
 * @author J.Komjago
 */
public class SnakeGame {
	private static Thread thread;

	/**
	 * MAIN 
	 * @param args
	 */
	public static void main(String[] args){
		LinkedBlockingQueue<Event> eventQ = new LinkedBlockingQueue<Event>();
		View view = new View(eventQ);
		Controller control = new Controller(eventQ,view);
		thread = new Thread(control,"Game Loop");
		thread.start();
	}
}
