import java.awt.image.BufferedImage;

public class Skull extends Entity
{
	private int timeToLive = 15;

	public Skull(int x, int y, int dx, String spriteID, int dimx, int dimy)
	{
		super(x, y, dx, spriteID, dimx, dimy);
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
