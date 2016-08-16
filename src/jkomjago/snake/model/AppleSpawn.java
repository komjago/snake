package jkomjago.snake.model;

import java.util.LinkedList;

import jkomjago.snake.common.Position;

/**Klasa tworzy i randomizuje położenie jabłka 
 * @author J.Komjago
 *
 */
class AppleSpawn {
	private LinkedList<Position> apples;
	
	/**
	 * konstruktor klasy
	 */
	public AppleSpawn(){
		apples = new LinkedList<Position>();
	}
	/**
	 * tworzymy jabłko
	 * @param pos
	 */
	public void spawn(final Position pos){
		Position apple = new Position(pos);
		apples.add(apple);
	}
	/**
	 * @param a
	 * @return pozycja jabłka
	 */
	public Position getApple(final int a){
		if (!apples.isEmpty())
			return apples.get(a);
		else
			return null;
	}
	/**
	 * usuwamy jabłko
	 * @param a
	 */
	public void remove(final int a){
		apples.remove(a);
	}
	/**
	 * @return rozmiar jabłka
	 */
	public int getCount(){
		return apples.size();
	}
	public LinkedList<Position> getPosition() {
		return apples;
	}
	public boolean isEmpty() {
		return apples.isEmpty();
	}
}
