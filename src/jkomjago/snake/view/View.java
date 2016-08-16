package jkomjago.snake.view;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingQueue;

import javax.swing.JOptionPane;

import jkomjago.snake.common.Direction;
import jkomjago.snake.common.Position;
import jkomjago.snake.events.DirectionKeyPressedEvent;
import jkomjago.snake.events.Event;
import jkomjago.snake.events.PlayerReadyEvent;

/**
 * Widok programu
 * 
 * @author J.Komjago
 */
public class View {
 private SwingFrame frame;
 private GamePanel panel;
 private int id;
 private boolean twoPlayers;
 LinkedBlockingQueue<Event> eventQ;
 
 	/**
 	 * konstruktor klasy
 	 * 
 	 * @param eventQ
 	 */
 	public View(LinkedBlockingQueue<Event> eventQ){
 		KeyboardControls key = new KeyboardControls();
 		this.eventQ = eventQ;
 		frame = new SwingFrame(eventQ);
 		panel = frame.getPanel();
 	 	panel.addKeyListener(key);
 	}
 	/**
	 * set 2 players mode
	 * 
	 * @param twoPlayers
	 */
	public void setTwoPlayers(boolean twoPlayers) {
		this.twoPlayers = twoPlayers;
	}
	/**
 	* @param gameOver
 	*/
	public void setGameOver(boolean gameOver) {
		panel.setGameOver(gameOver);
	}
	/**
	* rysuje to co trzeba na planszy
 	*/
	public void render(LinkedList<LinkedList<Position>> snakeDummies,LinkedList<Position> appleDummy) {
		panel.setSnakeDummies(snakeDummies);
		panel.setAppleDummy(appleDummy);
		panel.repaint();
	}
	/**
	 * tworzy okno informacyjne
	 * @param infoMessage komunikat w oknie
	 * @param name nazwa okna
	 */
	public static void infoBox(String infoMessage, String name)
    {
        JOptionPane.showMessageDialog(null, infoMessage, name, JOptionPane.INFORMATION_MESSAGE);
    }
	/** Wyświetla okno informacyjne z najlepszym osiągnięciem */
	public void highScore(String score) {
		infoBox("High Score is: "+ score,"High Score");	
	}
	/**
	 * Klasa odpowiada za komendy z klawiatury.
	 * 
	 * @author J.Komjago
	 */
	private class KeyboardControls implements KeyListener {
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				//dla 1 gracza
				if(key == KeyEvent.VK_D) {
					eventQ.add(new DirectionKeyPressedEvent(new Direction(Direction.RIGHT),1));
				}
				else if(key == KeyEvent.VK_A) {
					eventQ.add(new DirectionKeyPressedEvent(new Direction(Direction.LEFT),1));
				}
				else if(key == KeyEvent.VK_W) {
					eventQ.add(new DirectionKeyPressedEvent(new Direction(Direction.UP),1));
				}
				else if(key == KeyEvent.VK_S) {
					eventQ.add(new DirectionKeyPressedEvent(new Direction(Direction.DOWN),1));
				}
				// dla 2 gracza
				if(key == KeyEvent.VK_RIGHT && twoPlayers) {
					eventQ.add(new DirectionKeyPressedEvent(new Direction(Direction.RIGHT),2));
				}
				else if(key == KeyEvent.VK_LEFT && twoPlayers) {
					eventQ.add(new DirectionKeyPressedEvent(new Direction(Direction.LEFT),2));
				}
				else if(key == KeyEvent.VK_UP && twoPlayers) {
					eventQ.add(new DirectionKeyPressedEvent(new Direction(Direction.UP),2));
				}
				else if(key == KeyEvent.VK_DOWN && twoPlayers) {
					eventQ.add(new DirectionKeyPressedEvent(new Direction(Direction.DOWN),2));
				}
				else if(key == KeyEvent.VK_SPACE){
					eventQ.add(new PlayerReadyEvent(id));
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {
			}
			@Override
			public void keyTyped(KeyEvent e) {	
			}
	}
}
