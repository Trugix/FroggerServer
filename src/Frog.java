import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Frog extends Entity {
	
	private static final int MAX_VITE = 6;
	
	
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
	
	public Frog(int x, int y, int dx, BufferedImage sprite, int dimx, int dimy)
	{
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
		String dir = switch (targetDir)
				{
					case "UP" -> "src/../sprites/frogUp.png";
					case "LEFT" -> "src/../sprites/frogLeft.png";
					case "DOWN" -> "src/../sprites/frogDown.png";
					case "RIGHT" -> "src/../sprites/frogRight.png";
					default -> "";
				};
		this.sprite = ImageIO.read(new File(dir));
	}
	
	
	public void updateHitbox ()
	{
		this.hitbox = new Rectangle(this.p.x, this.p.y, this.dimx, this.dimy);
	}
	
	public void morte() throws IOException
	{
		resetPosition ();
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
		hitbox = (new Rectangle(this.p.x, this.p.y, this.dimx,this.dimy));
	}
}
