import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Turtle extends NPC
{

	private boolean goDown = true;
	private Random random = new Random();
	private final int MAX_TIMER = random.nextInt(200)+150;
	private int timer=MAX_TIMER;

	public Turtle(int x, int y, int dx, String spriteID, int dimx, int dimy, boolean deathTouch)
	{
		super(x, y, dx, spriteID, dimx, dimy, deathTouch);
	}


	/**
	 * faccio immergere le tartarughe
	 */
	public void immersion()
	{
		timer--;
		if(timer==0)
		{
			if(goDown)
			{
				this.spriteID = "turtle3";
				deathTouch = true;
				goDown=false;
				timer=MAX_TIMER;
			}
			else
			{
				this.spriteID = "turtle1";
				deathTouch = false;
				goDown=true;
				timer=MAX_TIMER;
			}
		}
		if(timer == 50)
		{
			this.spriteID = "turtle2";
			deathTouch = false;
		}

	}
}
