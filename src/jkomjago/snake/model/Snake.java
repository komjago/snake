package jkomjago.snake.model;
import java.util.LinkedList;

import jkomjago.snake.common.Position;

/**
 * Klasa Snake repezentuje żmiję na ekranie.
 * Tak naprawdę to kontener dla części ciała żmii typu Position
 * @author J.Komjago
 */
class Snake {
	private int maxSize;
	private int score;
	//położenie
	Position headCoord;
	//kierunek
	private Position prevDir;
	private Position currentDir;
	//kolizja
	private boolean isDead;
	//Lista typu LinkedList
	private LinkedList<Position> links;
	
	/**
	 * konstruktor
	 */
	public Snake(Position pos,Position dir){
		links = new LinkedList<Position>();
		maxSize = Options.snakeSize;
		headCoord = new Position(pos);
		currentDir = new Position(dir);
		prevDir = new Position(dir);
		isDead = false;
		score = 0;
	}	
	/**
	 * żmija róśnie
	 */
	public void grow(){
		if (Fields.contains(headCoord)){
			Fields.occupy(headCoord);
		}
		else{
			isDead = true;
	    }
		if (links.contains(headCoord)){
			isDead = true;
		}
		if (!isDead){
			links.add(new Position(headCoord));
		}	
	}
	/**
	 * żmija rusza do przodu
	 */
	public void move(){
		if (Fields.contains(headCoord)){
			Fields.occupy(headCoord);
		}
		else{
			isDead = true;
		}
		if (links.contains(headCoord)){
			isDead = true;
		}
		if (!isDead){
			links.add(new Position(headCoord));
			remove(0);
		}	
		
	}
	/**
	 * usuwa człon żmii
	 * @param i 
	 */
	private void remove(int i){
		Fields.giveBack(links.get(i));
		links.remove(i);
	}
	/**
	 * @return rozmiar żmii
	 */
	public int size(){
		return links.size();
	}
	/**daje dostęp do funkcji człona
	 * @param i
	 * @return links
	 */
	public LinkedList<Position> getPosition(){
		return links;
	}
	/**
	 * żmija skręca w lewo
	 */
	public void moveHead(Position p){
		headCoord.addCoords(p);
		prevDir = new Position(currentDir);
	}
	/**
	 * @return current snake direction
	 */
	public Position getCurrentDir() {
		return currentDir;
	}
	/**
	 * @param currentDir ustaw kierunek ruchu
	 */
	public void setCurrentDir(Position currentDir) {
		if(!prevDir.equals(currentDir.invert()))
			this.currentDir = currentDir;
	}
	/**
	 * @return czy żmija umarła
	 */
	public boolean isDead() {
		return isDead;
	}
	/**
	 * @return położenie żmii;
	 */
	public Position getHeadCoord() {
		return headCoord;
	}
	/**
	 * @return czy pusta
	 */
	public boolean isEmpty(){
		return links.isEmpty();
	}
	/**
	 * @return
	 */
	public int getMaxSize() {
		return maxSize;
	}
	public void incMaxSize() {
		maxSize++;
	}
	public int getScore() {
		return score;
	}
	public void incScore(int score) {
		this.score += score;
	}
}
