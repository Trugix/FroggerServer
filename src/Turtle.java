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
	private ArrayList <BufferedImage> sprites = new ArrayList<>();
	
	public Turtle(int x, int y, int dx, BufferedImage sprite, int dimx, int dimy, boolean deathTouch)
	{
		super(x, y, dx, sprite, dimx, dimy, deathTouch);
		addSprites(sprite);
	}
	
	public void addSprites (BufferedImage r)
	{
		sprites.add(r);
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
				this.sprite = sprites.get(2);
				deathTouch = true;
				goDown=false;
				timer=MAX_TIMER;
			}
			else
			{
				this.sprite = sprites.get(0);
				deathTouch = false;
				goDown=true;
				timer=MAX_TIMER;
			}
		}
		if(timer == 50)
		{
			this.sprite = sprites.get(1);
			deathTouch = false;
		}
		
	}
}
