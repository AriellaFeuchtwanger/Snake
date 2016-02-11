package snakeGame;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;


public class SnakeJFrame extends JFrame{
	private static final long serialVersionUID = -2542001418764869760L;
	public static ArrayList<ArrayList<DataOfSquare>> Grid;
	private int width = 20;
	private int height = 20;
	public SnakeJFrame(){
		
		
		// Creates the arraylist that'll contain the threads
		Grid = new ArrayList<ArrayList<DataOfSquare>>();
		ArrayList<DataOfSquare> data;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		// Creates Threads and its data and adds it to the arrayList
		for(int i=0;i<width;i++){
			data= new ArrayList<DataOfSquare>();
			for(int j=0;j<height;j++){
				DataOfSquare c = new DataOfSquare(2);
				data.add(c);
			}
			Grid.add(data);
		}
		
		// Setting up the layout of the panel
		getContentPane().setLayout(new GridLayout(20,20,0,0));
		
		// Start & pauses all threads, then adds every square of each thread to the panel
		for(int i=0;i<width;i++){
			for(int j=0;j<height;j++){
				getContentPane().add(Grid.get(i).get(j).square);
			}
		}
		
		// initial position of the snake
		Tuple position = new Tuple(10,10);
		// passing this value to the controller
		ThreadsController c = new ThreadsController(position);
		c.setHeight(height);
		c.setWidth(width);
		//Let's start the game now..
		c.start();

		// Links the window to the keyboardlistenner.
		this.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e) {
				int directionSnake = c.getDirectionSnake();
				switch (e.getKeyCode()) {
				case 39: // -> Right
							// if it's not the opposite direction
					if (directionSnake != 2)
						c.setDirectionSnake(1);
					break;
				case 38: // -> Top
					if (directionSnake != 4)
						c.setDirectionSnake(3);
					break;

				case 37: // -> Left
					if (directionSnake != 1)
						c.setDirectionSnake(2);
					break;

				case 40: // -> Bottom
					if (directionSnake != 3)
						c.setDirectionSnake(4);
					break;

				default:
					break;
				}
			}
		});

		//To do : handle multiplayers .. The above works, test it and see what happens
		
		//Tuple position2 = new Tuple(13,13);
		//ControlleurThreads c2 = new ControlleurThreads(position2);
		//c2.start();
		
	}
}
