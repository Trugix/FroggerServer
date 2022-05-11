import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Prize extends Entity
{
	private final boolean bonus;
	private final int point;

	public Prize(int x, int y, int dx, String spriteID, int dimx, int dimy, boolean bonus, int point)
	{
		super(x, y, dx, spriteID, dimx, dimy);
		this.bonus=bonus;
		this.point = point;
		this.hitbox = new Rectangle(x+dimx/6+15,y+dimy/6,dimx/3,dimy/3);
	}


	public boolean isBonus()
	{
		return bonus;
	}

	public int getPoint()
	{
		return point;
	}

	public void stepNext(ArrayList <Position> destination)
	{
		if(this.bonus)
		{
			if(destination.size()>0)
			{
				Random random = new Random();
				Position p = destination.get(random.nextInt(destination.size()));
				this.p.setX(p.getX());
				this.p.setY(p.getY());
				this.hitbox = new Rectangle(this.p.getX()+dimx/6+5, this.p.getY(), dimx / 3, dimy / 3);
				setSprite("fly");
			}
			else
			{
				setSprite("void");
				this.hitbox = null;
			}
		}
	}
}
