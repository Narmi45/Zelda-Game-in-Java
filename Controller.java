//Name: Imran Khan
//Date: 3/8/2022
//Assignment Description: In this assignment, I created a Link Character for my Game and allow users control him through MouseEvent
//						  I gave him animations, and created physics so he can collide with bricks. I also made it to where when he
//						  enters a new room, the view shifts to the room he entered. 

import java.awt.event.MouseListener;
import java.util.concurrent.ConcurrentHashMap.KeySetView;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.lang.Math;

class Controller implements ActionListener, MouseListener, KeyListener
{
	View view;
	Model model;
	boolean keyA; boolean keyW; boolean keyD; boolean keyX;
	boolean edit = false;

	int initialX;	//This is the exact X coordinate when I click
	int initialY;	//This is the exact Y coordinate when I click

	int finalX;		//This is the X position AFTER we do modulo math
	int finalY;		//This is the Y position AFTER we do modulo math

	Controller(Model m)
	{
		model = m;
	}

	public void mouseReleased(MouseEvent e) {    }
	public void mouseEntered(MouseEvent e) {    }
	public void mouseExited(MouseEvent e) {    }
	public void actionPerformed(ActionEvent e) {	}
	public void mousePressed(MouseEvent e) {	}
	public void keyTyped(KeyEvent e) {	  }

	void setView(View v)
	{
		view = v;
	}

	public void keyPressed(KeyEvent e)
	{
		if(!edit){
			switch(e.getKeyCode())
			{
				case KeyEvent.VK_W: keyW = true; break;	//Scroll will occur if key W is pressed
				case KeyEvent.VK_A: keyA = true; break;	//Scroll will occur if key Q is pressed
				case KeyEvent.VK_D: keyD = true; break;	//Scroll will occur if key D is pressed
				case KeyEvent.VK_X: keyX = true; break;	//Scroll will occur if key X is pressed
			}
		}
	}

	public void keyReleased(KeyEvent e)
	{
		switch(e.getKeyCode())
		{	
			case KeyEvent.VK_Q: 
			System.exit(0); 
			break;	//Quit will not occur if the key is released

			case KeyEvent.VK_ESCAPE: 
			System.exit(0); 
			break;	//Quit will not occur if the key is released

			case KeyEvent.VK_S: 			
			if(edit)
			{
				model.marshal().save("map.json");
				System.out.println("Game saved"); 
			}
			break; 	//Save will not occur if the key is released

			case KeyEvent.VK_E:
			edit = !edit;
			System.out.println("Edit Mode Toggled");
			break;

			case KeyEvent.VK_W: 
			if(!edit)
			{
				keyW = false; 
				model.link.imageNum = 2;
				break;
			}

			case KeyEvent.VK_A: 
			if(!edit)
			{
				keyA = false; 
				model.link.imageNum = 1;
				break;
			}

			case KeyEvent.VK_D: 
			if(!edit)
			{
				keyD = false; 
				model.link.imageNum = 3;
				break;
			}

			case KeyEvent.VK_X: 
			if(!edit)
			{
				keyX = false; 
				model.link.imageNum = 0;
				break;
			}
		}

	

	}
	
	void update()
	{
		if(!edit)
		{			
			if(keyW) 	
			{		
				if(model.link.imageNum < 24 || model.link.imageNum >= 33)
				{
					model.link.imageNum = 24;
				}
				model.link.imageNum++;
				//System.out.println(model.link.imageNum);
				model.link.y -= model.link.speed; 
			}
			else if(keyA)
			{
				if(model.link.imageNum < 14 || model.link.imageNum >= 23)
				{
					model.link.imageNum = 14;
				}
				model.link.imageNum++;
				//System.out.println(model.link.imageNum);
				model.link.x -= model.link.speed; 

		
			}
			else if(keyD) 
			{				
				if(model.link.imageNum < 34 || model.link.imageNum >= 43)
				{
					model.link.imageNum = 34;
				}
				model.link.imageNum++;
				//System.out.println(model.link.imageNum);
				model.link.x += model.link.speed; 
				
			}
			else if(keyX)								
			{	
				if(model.link.imageNum < 4 || model.link.imageNum >= 13)
				{
					model.link.imageNum = 4;
				}
				model.link.imageNum++;
				//System.out.println(model.link.imageNum);
				model.link.y += model.link.speed; 
			}
		}
	}

	public void mouseClicked(MouseEvent e) 
	{   
		if(e.getY() < 100)
		{
			System.out.println("break here");					//Breakpoint
		}

		if(edit){
		initialX = e.getX() + model.cameraposX;	
		initialY = e.getY() + model.cameraposY;				

		//Debugging tools I used:
		// System.out.println("This is Initial X: " + initialX);
		// System.out.println("This is Initial Y: " + initialY);

		finalX = initialX - initialX % 50;		//This variable is where we want to spawn the BRICK due to our clicking
		finalY = initialY - initialY % 50;		//Modulo Math that makes the brick spawn in a "gridlike fashion." This works because of PEMDAS.

		//Debugging tools I used:
		//System.out.println("This is Modulo X: " + finalX);
		//System.out.println("This is Modulo Y: " + finalY);

		boolean exists = false;							//Boolean is initialy set to false, if it was true, it'd just immediately delete the brick due to if(exists) on line 172
		Brick temp = null;								//Declaring temp object here so we can access it anywhere
		for (int i = 0; i < model.bricks.size(); i++)	//Looping through all the bricks so we can check each brick's information like X and Y starting with the first brick i = 0, then brick i = 1, then so on
		{	
			Brick b = model.bricks.get(i);				//Declare and Initialize Brick b object, "get" every index and assign brick's X and Y based on the index to Brick b object
			if(b.detect(finalX, finalY))				//Call the detect function from Brick.java, this actually check if brick's X and Y matches with finalX and finalY 
			{
				exists = true;							//If there is a match, that means there exists a brick where the user is clicking
				temp = b;								//Set object temp to b, which is essentially tells the temp object every brick that NEEDS TO BE DELETED.
			}											//object b contains all brick's X and Y info, whereas object temp contains only the SPECIFIC brick's X and Y that "exists already," AKA, needs to be deleted (This is the purpose of this whole conditional statement)
		}
		if(exists)										//If the brick exists due to the Detection method from Brick.java returning true because brick's X and Y match up with finalX and finalY, then we....
		{
			model.bricks.remove(temp);					//Remove the brick from object temp, not object b because that would just delete the LAST brick you spawned
		}
		else											//Otherwise, this means no brick exists, so instead we should....
		{
			model.bricks.add(new Brick(finalX, finalY, 50, 50));	//Create a new brick at finalX and finalY and add it to the array (the last 2 parameters are width and height)
		}
	}

		//Debugging tools I used:
		//System.out.println("This is X: " + e.getX());	//This is the exact X coordinate when I click 
		//System.out.println("This is Y: " + e.getY()); //This is the exact Y coordinate when I click
	}
}
