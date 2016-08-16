package jkomjago.snake.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingQueue;

import javax.swing.ImageIcon;
import javax.swing.JPanel;


import jkomjago.snake.common.Position;
import jkomjago.snake.events.Event;


/**
 * Klasa tworzy zawartość okna i wątki oraz imi zarządza
 * 
 * @author J.Komjago
 */
class GamePanel extends JPanel{
	private static final long serialVersionUID = 1L;
	private boolean gameOver;
	//screen variables
	public static final int WIDTH = 420, HEIGHT= 420, unitSize = 20;
	//dummies
	private LinkedList<LinkedList<Position>> snakeDummies;
	private LinkedList<Position> appleDummy;
	//event queue
	LinkedBlockingQueue<Event> eventQ;

	//image handling
	final ImageIcon grass = (new ImageIcon(getClass().getResource("/textures/grass.png")));
	final Image grassImg = grass.getImage();
	final public ImageIcon apl = (new ImageIcon(getClass().getResource("/textures/apple.gif")));
	private final Image appleImg = apl.getImage();
 	/**
 	 * konstruktor klasy tworzy keylistener i plik properties
 	 * 
 	 * @param twoplayers 
 	 */
 	public GamePanel(){
 		setFocusable(true);
 		setPreferredSize(new Dimension(WIDTH,HEIGHT));
 		setGameOver(true);
	}
	//methods
	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
	public void paint(final Graphics g){
		Position pos=null;
		//draw map
		g.clearRect(0, 0, WIDTH, HEIGHT);
		g.setColor(new Color(139,69,19));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		g.setColor(Color.BLACK);
		for(int i = 0; i < WIDTH / 20; i++) 
		{
			g.drawLine(i * 20, 0, i * 20, HEIGHT);
		}
		for(int i = 0; i < HEIGHT / 20; i++) 
		{
			g.drawLine(0, i * 20, WIDTH, i * 20);
		}
		g.drawImage(grassImg, 21, 21, null);
		// rysujemy snake
		if (snakeDummies != null){
			for(LinkedList<Position> snakeDummy : snakeDummies)
			while(!snakeDummy.isEmpty()){
				pos=snakeDummy.pop();
				this.drawSnakePart(g,pos);
				if(snakeDummy.isEmpty()){
					g.setColor(Color.BLACK);//głowa
					g.fillOval(pos.getX()*unitSize+8, pos.getY()*unitSize+8,4,4);
				}
			}
		}
		//rysujemy apple
		if(appleDummy != null && !appleDummy.isEmpty()){
			pos = appleDummy.pop();
			this.drawApple(g, pos);
		}
		// gdy nie gramy wyświetla komunikat 'press space'
		if(isGameOver()){ 
			g.setColor(Color.BLACK);
			g.fillRect(130, 180, 155, 30);
			g.setColor(new Color(139,69,19));
			g.fillRect(131, 181, 153, 28);
			g.setColor(Color.WHITE);
			g.drawString("Press Space to Begin", 140,200);
		}
	}
	/**
	 * rysuje człon żmii
	 * @param g klasa rysująca
	 * @param pos pozycja,gdzie rysujemy
	 */
	public void drawSnakePart(Graphics g,Position pos){
		g.setColor(Color.BLACK);
		g.fillRect(pos.getX()*unitSize,pos.getY()*unitSize, unitSize, unitSize);
		if(!gameOver)
		{
			g.setColor(Color.GREEN);
		}
		else 
		{
			g.setColor(Color.RED);
		}
		g.fillRect(pos.getX() * unitSize + 2, pos.getY() * unitSize + 2, unitSize - 4, unitSize - 4);
	}
	/**
	 * @param g
	 * @param pos
	 */
	public void drawApple(Graphics g, Position pos){
		if(pos!=null)
			g.drawImage(appleImg, pos.getX()* unitSize, pos.getY() * unitSize, null);
	}
	/**
	 * @param snakeDummy
	 */
	public void setSnakeDummies(LinkedList<LinkedList<Position>> snakeDummies) {
		this.snakeDummies = snakeDummies;
	}
	/**
	 * @param appleDummy
	 */
	public void setAppleDummy(LinkedList<Position> appleDummy) {
		this.appleDummy = appleDummy;
	}
	/**
	 * @return czy gra jest ukończona
	 */
	private boolean isGameOver() {
		return gameOver;
	}
	/**
	 * @param gameOver
	 */
	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}
}
