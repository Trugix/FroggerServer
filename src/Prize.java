import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Prize extends Entity
{
	private final boolean bonus;
	private final int point;
	
	public Prize(int x, int y, int dx, BufferedImage sprite, int dimx, int dimy, boolean bonus, int point)
	{
		super(x, y, dx, sprite, dimx, dimy);
		this.bonus=bonus;
		this.point = point;
	}
	
	
	public boolean isBonus()
	{
		return bonus;
	}
	
	public int getPoint()
	{
		return point;
	}
	
	public void stepNext(ArrayList <Position> destination) throws IOException
	{
		if(this.bonus)
		{
			if(destination.size()>0)
			{
				Random random = new Random();
				Position p = destination.get(random.nextInt(destination.size()));
				this.p.setX(p.getX());
				this.p.setY(p.getY());
				this.hitbox = new Rectangle(this.p.getX(), this.p.getY(), dimx / 2, dimy / 2);
				setSprite(ImageIO.read(new File("src/../sprites/fly.png")));
			}
			else
			{
				setSprite(ImageIO.read(new File("src/../sprites/void.png")));
				this.hitbox = null;
			}
		}
	}
}
