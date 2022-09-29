//Name: Imran Khan
//Date: 3/8/2022
//Assignment Description: In this assignment, I created a Link Character for my Game and allow users control him through MouseEvent
//						  I gave him animations, and created physics so he can collide with bricks. I also made it to where when he
//						  enters a new room, the view shifts to the room he entered. 

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import java.awt.Color;

class View extends JPanel
{
	BufferedImage brickImage;
	Model model;

	View(Controller c, Model m)
	{
		model = m;
		c.setView(this);
		try
		{
			this.brickImage = ImageIO.read(new File("brick.jpg"));
		} catch(Exception e) {
			e.printStackTrace(System.err);
			System.exit(1);
		}
	}

	static BufferedImage loadImage(String filename)
	{
		BufferedImage im = null;
		try
		{
			im = ImageIO.read(new File(filename));
			//System.out.println(filename + " loaded");
		} catch(Exception e) {
			e.printStackTrace(System.err);
			System.exit(1);
		}
		return im;
	}

	public void paintComponent(Graphics g)
	{
		g.setColor(new Color(128, 255, 255));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		g.setColor(new Color(255, 0, 255));									
		g.fillRect(0 - model.cameraposX, 0 - model.cameraposY, 1000, 750);	

		g.setColor(new Color(128, 0, 255));										
		g.fillRect(1000 - model.cameraposX, 0 - model.cameraposY, 1000, 750);	

		g.setColor(new Color(255, 0, 0));										
		g.fillRect(0 - model.cameraposX, 750 - model.cameraposY, 1000, 750);	

		g.setColor(new Color(0, 255, 0));										
		g.fillRect(1000 - model.cameraposX, 750 - model.cameraposY, 1000, 750);	

		for (int i = 0; i < model.bricks.size(); i++)
		{
			Brick b = model.bricks.get(i);
			//Debugging tools: 
			//System.out.println("This is b.x: " + b.x);
			//System.out.println("This is b.x: " + model.cameraposX);

			b.drawYourself(g, model.cameraposX, model.cameraposY);
		}	
		model.link.drawYourself(g, model.cameraposX, model.cameraposY);
	}
}			