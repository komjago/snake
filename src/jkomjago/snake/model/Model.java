package jkomjago.snake.model;

import java.util.LinkedList;

import jkomjago.snake.common.Position;

/**
 * Główna klasa modelu
 * 
 * @author J.Komjago
 */
public class Model {
	//is game running
	private boolean running;
	private boolean twoPlayersMode;
  	//game speed
  	private int speed = 1;
  	//snake variables 	
  	private LinkedList<Snake> snakes;
  	//apple variables
  	private AppleSpawn apple;
  	private Fields free;	
  
  	/**
   	* konstruktor klasy
  	*/
  	public Model(){
  		//pojemnik na żmije
	 	snakes = new LinkedList<Snake>();
  	}
  	/** 
  	 * wykonuje ruch modelem 
  	 */
  	public void tick() {
  		int snakeCount = snakes.size();
  		for(Snake snake:snakes){
  			if (snake.isDead()){
  				snakeCount--;
  				continue;
  			}
  			snake.moveHead(snake.getCurrentDir());
  			//move 1 step
  			if(snake.size() < snake.getMaxSize()){
		 	snake.grow();
  			}
  			else{
	 		snake.move();
  			}
	 	//żmija je jabłko
	 		if(snake.getHeadCoord().equals(apple.getApple(0))) {
	 			snake.incMaxSize();
	 			snake.incScore(speed);
	 			apple.remove(0);
	 			apple.spawn(free.getFreeField());
	 		}
  		}
  		if (snakeCount==0){
  			setRunning(false);
  		}
  			
  	}
 	/** 
 	 * usuwa resztki zeszłej gry i tworzy nową 
 	 */
	public void restart(){
		 snakes.clear();
		 snakes.add(new Snake(Options.snakeStartPos,Options.snakeStartDir));
		 if (twoPlayersMode){
			 snakes.add(new Snake(Options.snake2StartPos,Options.snake2StartDir));
		 }
		 free  = new Fields();
		 apple = new AppleSpawn();
		 apple.spawn(free.getFreeField());
		 setRunning(true);
		 
	}
	/**
	 * @return prędkość gry
	 */
	public int getSpeed() {
		return speed;
	}
	/**
	 * ustawia prędkość gry
	 * @param speed - prędkość
	 */
	public void setSpeed(final int speed) {
		this.speed = speed;
	}
	/**
	 * @return score - wyniki graczy
	 */
	public final int[] getScore() {
		if (twoPlayersMode){
			return new int[] {snakes.get(0).getScore(),snakes.get(1).getScore()};
		}
		else{
			return new int[] {snakes.get(0).getScore()};
		}
	}
	/**
	 * ustawia kierunek żmii
	 * 
	 * @param dir
	 * @param index 
	 */
	public void setSnakeCurrentDir(Position dir, int index){
		snakes.get(index).setCurrentDir(dir);
	}
	/**
	 * @return czy model jest uruchomiony
	 */
	public boolean isRunning() {
		return running;
	}
	/**
	 * @param running
	 */
	private void setRunning(final boolean b) {
		running = b;
	}
	/**
	 * zwraca pozycję żmii dla rysowania
	 * 
	 * @param i
	 * @return pozycja wybranego człona żmii
	 */
	public LinkedList<LinkedList<Position>> getSnakeDummies(){
		LinkedList<LinkedList<Position>> snakeDummies = new LinkedList<LinkedList<Position>>();
		for(Snake snake:snakes){
			LinkedList<Position> list = new LinkedList<Position>(snake.getPosition());
			snakeDummies.add(list);
		}
		return snakeDummies;
	}
	/**
	 * zwraca pozycję jabłka dla rysowania
	 * 
	 * @param i
	 * @return pozycja jabłka
	 */
	public LinkedList<Position> getAppleDummy(){
		LinkedList<Position> appleDummy;
		if (!apple.isEmpty())
			appleDummy = new LinkedList<Position>(apple.getPosition());
		else
			appleDummy = null;
		return appleDummy;
	}
	/**
	 * @return twoPlayersMode - czy włączony tryb 2 graczy
	 */
	public boolean twoPlayersMode() {
		return twoPlayersMode;
	}
	/**
	 * Ustawia tryb 2 graczy
	 * 
	 * @param twoPlayers 
	 */
	public void setTwoPlayersMode(final boolean twoPlayers) {
		twoPlayersMode = twoPlayers;
	}
}