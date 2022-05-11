import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Frog extends Entity {

	private static final int MAX_VITE = 6;

	final int dy=100;
	private int vite;
	private int point=0;

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	private int direction = 0;

	private boolean isMoving=false;

	public boolean isMoving() {
		return isMoving;
	}

	public void setMoving(boolean moving) {
		isMoving = moving;
	}

	private static final int STARTING_FROGX = 460;
	private static final int STARTING_FROGY = 10;


	public Frog(int x, int y, int dx, String spriteID, int dimx, int dimy)
	{
		super(x, y, dx, spriteID, dimx, dimy);
		vite=MAX_VITE;
	}

	public int getVite()
	{
		return vite;
	}

	public int getPoint()
	{
		return point;
	}

	public void setPoint(int point)
	{
		this.point = point;
	}

	public void rotate(int targetDir)
	{
		switch (targetDir) {
			case 0:
				this.spriteID = "frogUp";
				break;
			case 1:
				this.spriteID = "frogRight";
				break;
			case 2:
				this.spriteID = "frogDown";
				break;
			case 3:
				this.spriteID = "frogLeft";
				break;
		}
	}


	public void updateHitbox ()
	{
		this.hitbox = new Rectangle(this.p.x+10, this.p.y+5, this.dimx-20, this.dimy-10);
	}

	public void morte() throws IOException
	{
		resetPosition ();
		isMoving=false;
		this.vite--;
	}

	public void resetPosition () throws IOException
	{
		this.p.setX(STARTING_FROGX);
		this.p.setY(STARTING_FROGY);
		updateHitbox();
		rotate(0);
	}

	public void stepNext(int tempDx)
	{
		p.setX(p.getX() + tempDx);
		if (p.getX() > 920) //per evitare che la rana se ne vada dalla schermo
			p.setX(920);
		if (p.getX() < 0)
			p.setX(0);
		//hitbox = (new Rectangle(this.p.x, this.p.y, this.dimx,this.dimy));
		updateHitbox();
	}

	public void nextSlide()
	{
		if (isMoving)
		{
			switch (direction)
			{
				case 0:
					spriteID = "frogMovUp";
					p.y+=20;
					if (p.getY() > 1210)
						p.setY(1210);
					break;
				case 1:
					spriteID = "frogMovRight";
					p.x+=14;
					if (p.getX() > 920)
						p.setX(920);
					break;
				case 2:
					spriteID = "frogMovDown";
					p.y-=20;
					if (p.getY() < 10)
						p.setY(10);
					break;
				case 3:
					spriteID = "frogMovLeft";
					p.x-=14;
					if (p.getX() < 0)
						p.setX(0);
					break;
			}
		}
		hitbox = (new Rectangle(this.p.x, this.p.y, this.dimx,this.dimy));
	}
}
