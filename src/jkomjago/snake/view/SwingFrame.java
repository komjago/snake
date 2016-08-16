package jkomjago.snake.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.concurrent.LinkedBlockingQueue;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

import jkomjago.snake.events.ChangeSpeedEvent;
import jkomjago.snake.events.Event;
import jkomjago.snake.events.SetPlayerCountEvent;
import jkomjago.snake.events.ShowScoreEvent;
/**
 * Klasa Wyświetla Okno Programu
 * 
 * @author J.Komjago
 */
class SwingFrame extends JFrame{
	/**versja*/
	private static final long serialVersionUID = 1L;
	private static final String version = "V3.7 Final";
	//Menu
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenu submenu;
	private GamePanel panel;
	//Menu items
	private JMenuItem exitMenuItem;
	private JMenuItem hScoreMenuItem;
	private JRadioButtonMenuItem rbMenuItem;
	private LinkedBlockingQueue<Event> eventQ;
	/**
	 *konstruktor 
	 */
	public SwingFrame(LinkedBlockingQueue<Event> eventQ) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setName("Snake");
		setTitle("Snake! " + version);
		setResizable(false);
		this.eventQ=eventQ;
		init();
	}
	/**
	 * inicjalizacja, tworzenie menu
	 */
	private void init(){
		menuBar = new JMenuBar();
		menu = new JMenu("Game");
		menu.setMnemonic(KeyEvent.VK_G);
		menuBar.add(menu);
		hScoreMenuItem = new JMenuItem("High Score");
		hScoreMenuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				eventQ.add(new ShowScoreEvent());
			}
		});   
		exitMenuItem = new JMenuItem("Exit");
		exitMenuItem.setMnemonic(KeyEvent.VK_E);
		exitMenuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});     
 
		submenu = new JMenu("Speed");
		submenu.setMnemonic(KeyEvent.VK_S);
		
		//radio button menu items
		ButtonGroup group = new ButtonGroup();
		rbMenuItem = new JRadioButtonMenuItem("Low");
		rbMenuItem.setSelected(true);
		rbMenuItem.setMnemonic(KeyEvent.VK_L);
		rbMenuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				eventQ.add(new ChangeSpeedEvent(1));
        	}
		});
		group.add(rbMenuItem);
		submenu.add(rbMenuItem);
		rbMenuItem = new JRadioButtonMenuItem("Medium");
		rbMenuItem.setMnemonic(KeyEvent.VK_M);
		rbMenuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				eventQ.add(new ChangeSpeedEvent(2));
			}
		});
		group.add(rbMenuItem);
		submenu.add(rbMenuItem);
		menu.add(submenu);
		
		submenu = new JMenu("Players");
		submenu.setMnemonic(KeyEvent.VK_P);
		
		//radio button menu items
		group = new ButtonGroup();
		rbMenuItem = new JRadioButtonMenuItem("1 Player");
		rbMenuItem.setSelected(true);
		rbMenuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				eventQ.add(new SetPlayerCountEvent(1));
        	}
		});
		group.add(rbMenuItem);
		submenu.add(rbMenuItem);
		rbMenuItem = new JRadioButtonMenuItem("2 Players");
		rbMenuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				eventQ.add(new SetPlayerCountEvent(2));
			}
		});
		group.add(rbMenuItem);
		submenu.add(rbMenuItem);
		
		menu.add(submenu);
		menu.add(hScoreMenuItem);
		menu.add(exitMenuItem);
		panel = new GamePanel();
		setJMenuBar(menuBar);
		add(panel,BorderLayout.SOUTH);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	/**
	 * @return panel z grą
	 */
	public GamePanel getPanel() {
		return panel;
	}
}
