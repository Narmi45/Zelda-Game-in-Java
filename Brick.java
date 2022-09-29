//Name: Imran Khan
//Date: 3/8/2022
//Assignment Description: In this assignment, I created a Link Character for my Game and allow users control him through MouseEvent
//						  I gave him animations, and created physics so he can collide with bricks. I also made it to where when he
//						  enters a new room, the view shifts to the room he entered. 

import java.awt.Graphics;
import java.awt.image.BufferedImage;
public class Brick {
    
    int x; int y; int w; int h; //Member Variables/Instance Variables/Class Variables
    static BufferedImage image = null;

    public Brick(int pos_x, int pos_y, int pos_w, int pos_h)
    {                                                    
        x = pos_x;           
        y = pos_y;          
        w = pos_w;           
        h = pos_h;          
        loadImage();
    }

    public boolean detect(int final_X, int final_Y)
	{
		boolean detected = false;                          
		if(x == final_X && y == final_Y)                 
			{
				detected = true;               
			}
		return detected;                   
	}

    public Brick(Json ob)
    {
        x = (int)ob.getLong("x");
        y = (int)ob.getLong("y");
        w = (int)ob.getLong("w");
        h = (int)ob.getLong("h");
        loadImage();
    }

    void loadImage()
    {
        if (image == null)
        {
            image = View.loadImage("brick.jpg");
        }
    }

    Json marshal()
    {
        Json ob = Json.newObject();
        ob.add("x", x);
        ob.add("y", y);
        ob.add("w", w);
        ob.add("h", h);
        return ob;
    }

    void drawYourself(Graphics g, int cameraposX, int cameraposY)
    {
        g.drawImage(image, x - cameraposX, y - cameraposY, w, h, null);
    }

    @Override
    public String toString()
    {
        return "Brick located at (" + x + ", " + y  + ") with a width = " + w + " and a height = " + h;
    }
}
