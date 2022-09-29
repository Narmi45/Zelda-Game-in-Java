//Name: Imran Khan
//Date: 3/8/2022
//Assignment Description: In this assignment, I created a Link Character for my Game and allow users control him through MouseEvent
//						  I gave him animations, and created physics so he can collide with bricks. I also made it to where when he
//						  enters a new room, the view shifts to the room he entered. 

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Link {
    int x; int y;
    final int w = 60; final int h = 65;
    double speed;
    static BufferedImage[] images = null;
    int imageNum;

    public Link()
    {
        x = 100;
        y = 60;
        speed = 5;

        images = new BufferedImage[44];
        for(int i = 0; i < images.length; i++)
        {
            images[i] = View.loadImage("./linkSprite/link (" + i + ").png");
            //System.out.println(i);
        }
    }

    void update()
    {
    }

    void drawYourself(Graphics g, int cameraposX, int cameraposY)
    {
        g.drawImage(images[imageNum], x - cameraposX, y - cameraposY, w, h, null);
    }

    @Override
    public String toString()
    {
        return "Link located at (" + x + ", " + y  + ") with a width = " + w + " and a height = " + h;
    }

}
