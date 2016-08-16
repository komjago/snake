package jkomjago.snake.controller;

import java.util.TimerTask;
import java.util.concurrent.LinkedBlockingQueue;

import jkomjago.snake.events.Event;
import jkomjago.snake.events.TimerEvent;

class TimerEventTask extends TimerTask {
Thread thread;
LinkedBlockingQueue<Event> events;
	/**
	 * @param events
	 */
	TimerEventTask(LinkedBlockingQueue<Event> events){
		this.events = events;
	}
	/* (non-Javadoc)
	 * @see java.util.TimerTask#run()
	 */
	@Override
	public void run() {
		events.add(new TimerEvent());
	}
}