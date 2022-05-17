public class Skull extends Entity   //scheletro che appare quando la rana muore
{
	private int timeToLive = 15;    //quanto tempo deve rimanere sullo schermo

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
