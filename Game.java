//Name: Imran Khan
//Date: 3/8/2022
//Assignment Description: In this assignment, I created a Link Character for my Game and allow users control him through MouseEvent
//						  I gave him animations, and created physics so he can collide with bricks. I also made it to where when he
//						  enters a new room, the view shifts to the room he entered. 

import javax.swing.JFrame;			//This is an import of a class that creates the GUI/Window, and components of it
import java.awt.Toolkit;			//Same utility

public class Game extends JFrame	//JFrame is parent class, Game class inherits methods and functions from JFrame
{
	Controller controller;			//Declaring class objects as member variables so I can access them anywhere in this file
	Model model;					//Passing them in so I can use .update() which will basically run their full functionality
	View view;						//Question: Why don't we pass in brick? does brick have an update? or is it because brick is
									//called within these classes and is updated through there? 
	public Game()
	{
		model = new Model();

		controller = new Controller(model);
		view = new View(controller, model);		

		view.addMouseListener(controller);
		this.addKeyListener(controller);

		this.setTitle("A4 - Link's Adventure");
		this.setSize(1000, 750);
		this.setFocusable(true);
		this.getContentPane().add(view);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}
	public void run()
	{

		while(true)					//Infinite Loop
		{
			controller.update();	//public void update() in Controller class is constantly being called
			model.update();			//public void update() in Model class is constantly being called
			view.repaint(); 		//Indirectly calls View.paintComponent
			Toolkit.getDefaultToolkit().sync(); // Updates the Screen 

			try
			{
				Thread.sleep(16);		//Sleep for 16 ms
			} catch(Exception e) {		//Catch the Exception
				e.printStackTrace();
				System.exit(1);			//Exit
			}
		}
	}

	public static void main(String[] args)
	{
		Game g = new Game();		//Declare and Initializing Game
		g.run();					//Run everything in Game.java, so all the methods of this class and all void methods are enabled
	}
}
