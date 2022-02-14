import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Frog extends Entity {
	
	private static final int MAX_VITE = 6;
	
	private boolean stable = true;
	
	public boolean isStable()
	{
		return stable;
	}
	
	public void setStable(boolean stable)
	{
		this.stable = stable;
	}
	
	/*public enum rotazione{
		UP,
		RIGHT,
		DOWN,
		LEFT;
	};
	
	public rotazione next(rotazione r) {
		switch (r) {
			case UP:
				return rotazione.LEFT;
			case LEFT:
				return rotazione.DOWN;
			case DOWN:
				return rotazione.RIGHT;
			case RIGHT:
				return rotazione.UP;
		}
		return null;
	}*/
	
	int dy;
	int vite;
	private int point=0;
	private static final int STARTING_FROGX = 460;
	private static final int STARTING_FROGY = 10;
	
	public Frog(int x, int y, int dx, BufferedImage sprite, int dimx, int dimy) throws IOException {
		super(x, y, dx, sprite, dimx, dimy);
		dy=dx;
		vite=MAX_VITE;
	}
	
	public int getPoint()
	{
		return point;
	}
	
	public void setPoint(int point)
	{
		this.point = point;
	}
	
	public void rotate(String targetDir) throws IOException
	{
		String dir="";
		switch (targetDir)
		{
			case "UP":
				dir = "src/../sprites/frogUp.png";
				break;
			case "LEFT":
				dir = "src/../sprites/frogLeft.png";
				break;
			case "DOWN":
				dir = "src/../sprites/frogDown.png";
				break;
			case "RIGHT":
				dir = "src/../sprites/frogRight.png";
				break;
		}
			this.sprite = ImageIO.read(new File(dir));
	}
	
	
	public void updateHitbox ()
	{
		this.hitbox = new Rectangle(this.p.x, this.p.y, this.dimx, this.dimy);
	}
	
	public void morte() throws IOException
	{
		this.p.setX(STARTING_FROGX);
		this.p.setY(STARTING_FROGY);
		updateHitbox();
		rotate("UP");
		this.vite--;
	}
	
	public void stepNext(int tempDx)
	{
		p.setX(p.getX() + tempDx);
		hitbox = (new Rectangle(this.p.x, this.p.y, this.dimx,this.dimy));
	}
	
}
