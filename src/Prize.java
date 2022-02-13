import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class Prize extends Entity
{
	private boolean bonus;
	
	public Prize(int x, int y, int dx, BufferedImage sprite, int dimx, int dimy, boolean bonus)
	{
		super(x, y, dx, sprite, dimx, dimy);
		this.bonus=bonus;
	}
	
	public boolean isBonus()
	{
		return bonus;
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
