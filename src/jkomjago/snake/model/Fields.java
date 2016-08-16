package jkomjago.snake.model;

import java.util.LinkedList;
import java.util.Random;

import jkomjago.snake.common.Position;

class Fields {
	private static LinkedList<Position> freeList;
	private Random random;
	/**
	 * @param length
	 * @param height
	 */
	public Fields(){
		random = new Random();
		freeList = new LinkedList<Position>();
		for(int x = Options.firstField; x<=Options.fieldLength; x++){
			for(int y=Options.firstField; y<=Options.fieldHeight; y++){
				freeList.add(new Position(x,y));
			}	
		}
	}
	/**
	 * @return randomowa pusta pozycja
	 */
	public Position getFreeField(){
		
		int rand=random.nextInt(freeList.size()-1);
		Position pos=freeList.get(rand);
		return pos;
	}
	/**
	 * zwalniamy pozycję
	 */
	public static void giveBack(Position pos){
		freeList.add(pos);
	}
	/**
	 * zabieramy miejsce 
	 */
	public static void occupy(Position pos){
		freeList.remove(pos);
	}
	/**
	 * @return czy zawiera współrzędne
	 */
	public static boolean contains(Position pos){
		return freeList.contains(pos);
	}
}
