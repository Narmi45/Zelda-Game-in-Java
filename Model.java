//Name: Imran Khan
//Date: 3/8/2022
//Assignment Description: In this assignment, I created a Link Character for my Game and allow users control him through MouseEvent
//						  I gave him animations, and created physics so he can collide with bricks. I also made it to where when he
//						  enters a new room, the view shifts to the room he entered. 

import java.util.ArrayList;
import java.util.Iterator;

public class Model
{
	//Member Variables/Instance Variables/Class Variables
	int x;
	int y;
	int cameraposX = 0;
	int cameraposY = 0;
	ArrayList<Brick> bricks;
	Link link;

	Model()
	{
		bricks = new ArrayList<Brick>();
		link = new Link();
		//Brick b = new Brick(10, 300, 100, 100);	//Test Brick (Hard Coded)
		//bricks.add(b);							//Test Adding Brick to Array
		Json j = Json.load("map.json");
		j.save("temp.json");
		this.unmarshal(j);
	}

	public void update()
	{
		link.toString();
		bricks.toString();
		link.update();
		roomChange();
		checkCollisions();

	}

	Json marshal()
	{
		Json ob = Json.newObject();
		ob.add("cameraPosX", cameraposX);
		ob.add("cameraPosY", cameraposY);
		Json tmpList = Json.newList();
		ob.add("bricks", tmpList);
		for(int i = 0; i < bricks.size(); i++)
		{
			tmpList.add(bricks.get(i).marshal());
		}
		return ob;
	}

	void unmarshal(Json ob)
	{
		bricks = new ArrayList<Brick>();
		Json tmpList = ob.get("bricks");
		for(int i = 0; i < tmpList.size(); i++)
		{
			bricks.add(new Brick(tmpList.get(i)));
		}
	}


	void roomChange()
	{
		//Going Right
		if(link.x + link.w > 1010 && cameraposX < 1010)
		{
			
			cameraposX += 10;
		}
			
		//Going Left
		if(link.x < 1010 && cameraposX > 0)
		{
			cameraposX -= 10;
		}	
		
		//Going Down
		if(link.y + link.h > 780 && cameraposY < 780)
		{
			cameraposY += 10;
		}	

		//Going Up
		if(link.y < 780 && cameraposY > 0)
		{
			cameraposY -= 10;
		}	
	}

	void checkCollisions()
	{
		Iterator<Brick> iter = bricks.iterator();
		while(iter.hasNext())
		{
			Brick b = iter.next();
			
		int LinkTop = link.y;
        int LinkBot = link.y + link.h;
        int LinkLeft = link.x;
        int LinkRight = link.x + link.w;

		int BrickTop = b.y;
        int BrickBot = b.y + b.h;
        int BrickLeft = b.x;
        int BrickRight = b.x + b.w;

			if(LinkRight < BrickLeft || LinkLeft > BrickRight ||
			LinkBot < BrickTop || LinkTop > BrickBot)
			{
				//System.out.println("False");
			}
			else
			{
				//System.out.println("True");
				int LeftCollision = Math.abs(LinkLeft - BrickRight);
				int RightCollision = Math.abs(LinkRight - BrickLeft);
				int BotCollision = Math.abs(LinkTop - BrickBot);
				int TopCollision = Math.abs(LinkBot - BrickTop);
				int MaxCollision = Math.max(Math.max(LeftCollision, RightCollision), Math.max(BotCollision, TopCollision));

				if(LeftCollision == MaxCollision)
				{
					//System.out.println("LEFT COLLISION");
					link.x = BrickLeft - link.w;
				}
				else if(RightCollision == MaxCollision)
				{
					//System.out.println("RIGHT COLLISION");
					link.x = BrickRight;
				}
				else if(TopCollision == MaxCollision)
				{
					//System.out.println("TOP COLLISION");
					link.y = BrickBot;
				}
				else if(BotCollision == MaxCollision)
				{
					//System.out.println("Bot COLLISION");
					link.y = BrickTop - link.h;
				}
			}
		}
	}
}