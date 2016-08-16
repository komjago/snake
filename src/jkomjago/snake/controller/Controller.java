package jkomjago.snake.controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.LinkedBlockingQueue;

import jkomjago.snake.common.Position;
import jkomjago.snake.events.Event;
import jkomjago.snake.model.Model;
import jkomjago.snake.view.View;

/**
 * tutaj są linki do modelu i obsługa eventów
 * 
 * @author J.Komjago
 */
public class Controller implements Runnable {
	LinkedBlockingQueue<Event> eventQ;
	Model model;
	View view;
	private TimerTask timerTask;
	private Timer timer;
	private EventHandler handler;
	private Properties properties = new Properties();
	private boolean running = false;
 
	/**
	 * konstruktor
	 * 
	 * @param eventQ 
	 * @param gamePanel 
	 */
	public Controller(LinkedBlockingQueue<Event> eventQ, View view){
	 
		this.eventQ = eventQ;
		this.view = view;
		model = new Model();
		handler = new EventHandler(this);
		try {
		  properties.load(new FileInputStream(".s"));
		} 
		catch (IOException e) {
			try {
				properties.store(new FileOutputStream(".s"), "score");
				properties.load(new FileInputStream(".s"));
			}
			catch (IOException x) {
				x.printStackTrace();
			}
		}
		if (properties.getProperty("score")==null){
			properties.setProperty("score", String.valueOf(currentScore()));
		}
	}
	/**
	 * @param i
	 * @return współrzędne jabłka
	 */
	private LinkedList<Position> getAppleDummy() {
		return model.getAppleDummy();	
	}
	/**
	 * @return score z properties
	 */
	public String score(){
		return properties.getProperty("score");
	}
	/**
	 * @return obecny wynik gry z modelu
	 */
	public int[] currentScore() {
		return model.getScore();
	}
	/**
	 * @param i
	 * @return pozycja żmii
	 */
	private  LinkedList<LinkedList<Position>> getSnakeDummies() {
		return model.getSnakeDummies();
	}
	/**
	 * alert z highscorem
	 */
	public void showHighScore() {
		view.highScore(score());	
	}
	/**
	 * zmienia prędkość gry
	 * 
	 * @param i
	 */
	public void setSpeed(int i) {
		model.setSpeed(i);
	}
	/**
	 * sprzątamy i startujemy ponownie
	 */
	public void restart() {
		if (isRunning())
			return;
		eventQ.clear();
		model.restart();
		start();
	}
	/**
	 * @return prędkość żmii z modelu
	 */
	public int getSpeed() {
		return model.getSpeed();
	}
	/**
	 * zmienna jest true gdy toczy się gra
	 */
	public boolean isRunning() {
		return running;
	}
	/**
	 * zmiana statusu gry
	 */
	private void setRunning(boolean running) {
		this.running = running;
	}
	/**
	 * obsługa zdarzeń zmiany położenia
	 * 
	 * @param player 
	 */
	public void dirChange(Position key, int player) {
		model.setSnakeCurrentDir(key,player-1);
	}
	/**
	 * Uruchamia nowy wątek gry
	 */
	private void start(){
		setRunning(true);
		view.setGameOver(false);
		timerTask=new TimerEventTask(eventQ);
		timer = new Timer();
		timer.schedule(timerTask,120-17*getSpeed(),120-17*getSpeed());
	}
	/** 
	 * Kończy grę i wyświetla wynik gry
	 */
	private void stop(){
		timer.cancel();
		setRunning(false);
		view.setGameOver(true);
		checkScore();
	}
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		while(true){
			handleEvents();
		}
	}
	/**
	 * pobranie elementu z kolejki
	 */
	public void handleEvents() {
		Event event;
		try {
			event = eventQ.take();
		} 
		catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		if(event != null)
		handler.handle(event);
	}
	/**
	 * Sprawdza score z properties
	 */
	public void checkScore(){
		if (!model.twoPlayersMode()){
			if (Integer.valueOf(properties.getProperty("score")) < currentScore()[0]){
			     properties.setProperty("score", String.valueOf(currentScore()));
			     View.infoBox("Congratulations Player 1!\nYour Score is: "+ currentScore()[0] ,"New High Score!");
			}
			else{
				View.infoBox("Your Score is: "+ currentScore()[0] ,"Game Over!");
			}
			try {
			     properties.store(new FileOutputStream(".s"), null);
			} 
			catch (IOException e) {
			    throw new RuntimeException(e);
			}
		}
		else{
			View.infoBox("Player1's score is: "+ currentScore()[0] + "\nPlayer2's score is: " + currentScore()[1] ,"Game Over!");
		}
		
	}
	/**
	 * Odświeża stan
	 */
	public void refresh() {
	 	model.tick();
	 	view.render(this.getSnakeDummies(),this.getAppleDummy()); //zmiany na ekranie
		if (!model.isRunning()) { 
			stop();
		}
	}
	public void setTwoPlayers(boolean twoPlayers) {
		view.setTwoPlayers(twoPlayers);
		model.setTwoPlayersMode(twoPlayers);
		
	}
}
