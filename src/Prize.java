import java.awt.*;
import java.awt.image.BufferedImage;
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
		this.hitbox = new Rectangle(x+dimx/4, y+dimy/4, dimx/2, dimy/2);
	}
	
	public boolean isBonus()
	{
		return bonus;
	}
	
	public int getPoint()
	{
		return point;
	}
	
	public void stepNext(ArrayList <Position> desination)
	{
		if(this.bonus)
		{
			Random random = new Random();
			Position p=desination.get(random.nextInt(desination.size()));
			this.p.setX(p.getX());
			this.p.setY(p.getY());
			this.hitbox=new Rectangle(this.p.getX(), this.p.getY(), dimx, dimy);
		}
	}
}
