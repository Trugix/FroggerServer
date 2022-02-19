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

	private final BufferedImage[] sprites;

	public boolean isMoving() {
		return isMoving;
	}

	public void setMoving(boolean moving) {
		isMoving = moving;
	}



	private static final int STARTING_FROGX = 460;
	private static final int STARTING_FROGY = 10;

	
	public Frog(int x, int y, int dx, BufferedImage[] sprites, int dimx, int dimy)
	{
		super(x, y, dx, sprites[0], dimx, dimy);
		this.sprites = sprites;
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
	
	public void rotate(String targetDir)
	{
		switch (targetDir) {
			case "UP" -> this.sprite = sprites[0];
			case "LEFT" -> this.sprite = sprites[3];
			case "DOWN" -> this.sprite = sprites[2];
			case "RIGHT" -> this.sprite = sprites[1];
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
		rotate("UP");
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
					p.y+=20;
					if (p.getY() > 1210)
						p.setY(1210);
					break;
				case 1:
					p.x+=14;
					if (p.getX() > 920)
						p.setX(920);
					break;
				case 2:
					p.y-=20;
					if (p.getY() < 10)
						p.setY(10);
					break;
				case 3:
					p.x-=14;
					if (p.getX() < 0)
						p.setX(0);
					break;
			}
		}
		hitbox = (new Rectangle(this.p.x, this.p.y, this.dimx,this.dimy));
	}
}
