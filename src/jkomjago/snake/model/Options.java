package jkomjago.snake.model;

import jkomjago.snake.common.Position;
import jkomjago.snake.common.Direction;


/**
 * ustawienia gry
 * 
 * @author J.Komjago
 */
class Options {
  	/** położenie początkowe gracza 1*/
  	public final static Position snakeStartPos = new Position(1,2);
  	/** położenie początkowe gracza 2*/
  	public final static Position snake2StartPos = new Position(19,18);
  	/**startowy rozmiar żmii gracza 1*/
  	public final static int snakeSize = 3;
  	/**startowy rozmiar żmii gracza 2*/
	public final static int snake2Size = 3;
	/**startowy kierunek żmii gracza 1*/
  	public final static Position snakeStartDir = Direction.RIGHT;
  	/**startowy kierunek żmii gracza 2*/
  	public final static Position snake2StartDir = Direction.LEFT;
  	//zmienne dotyczące pola
  	/**pole początkowe*/
  	public final static int firstField = 1;
  	/**ilość klatek wszerz*/
  	public final static int fieldLength = 19;
  	/**ilość klatek wwyż*/
  	public final static int fieldHeight = 19;
}
