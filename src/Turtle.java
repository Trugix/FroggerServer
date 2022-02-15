import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Turtle extends NPC
{
	
	private boolean sub;
	private boolean goDown = true;
	Random random = new Random();
	private int MAX_TIMER = random.nextInt(100)+150;
	private int timer=MAX_TIMER;
	private Rectangle2D hitboxUp;
	
	public Turtle(int x, int y, int dx, BufferedImage sprite, int dimx, int dimy, boolean deathTouch, boolean sub)
	{
		super(x, y, dx, sprite, dimx, dimy, deathTouch);
		this.sub=sub;
		this.hitboxUp=new Rectangle(x, y, dimx, dimy);
	}
	
	
	public boolean isSub()
	{
		return sub;
	}
	
	/**
	 * faccio immergere le tartarughe
	 * @throws IOException
	 */
	public void immersion() throws IOException
	{
		timer--;
		if(timer==0)
		{
			if(goDown)
			{
				setSprite(ImageIO.read(new File("src/../sprites/r1.png")));
				deathTouch = true;
				goDown=false;
				timer=MAX_TIMER;
			}
			else
			{
				setSprite(ImageIO.read(new File("src/../sprites/r3.png")));
				deathTouch = false;
				goDown=true;
				timer=MAX_TIMER;
			}
		}
		if(timer == 50)
		{
			setSprite(ImageIO.read(new File("src/../sprites/r2.png")));
			deathTouch = false;
		}
		
	}
}
