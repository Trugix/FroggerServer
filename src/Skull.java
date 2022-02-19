import java.awt.image.BufferedImage;

public class Skull extends Entity
{
	private int timeToLive = 15;
	
	public Skull(int x, int y, int dx, BufferedImage sprite, int dimx, int dimy)
	{
		super(x, y, dx, sprite, dimx, dimy);
	}
	
	public int getTimeToLive()
	{
		return timeToLive;
	}
	
	public void setTimeToLive(int timeToLive)
	{
		this.timeToLive = timeToLive;
	}
}
