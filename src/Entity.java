import java.awt.geom.Area;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity
{
	public int getDimx()
	{
		return dimx;
	}
	
	public void setDimx(int dimx)
	{
		this.dimx = dimx;
	}
	
	public class Position
	{
		protected int x;
		protected int y;
		
		public Position(int x, int y)
		{
			this.setX(x);
			this.y = y;
		}
		
		public int getX()
		{
			return x;
		}
		
		public void setX(int x)
		{
			this.x = x;
		}
	}
	
	protected Position p;
	protected BufferedImage sprite;
	protected int dx;
	private int dimx;
	protected int dimy;
	protected Area hitbox;
	
	public Entity(int x, int y, int dx, BufferedImage sprite, int dimx, int dimy)
	{
		this.p = new Position(x, y);
		this.dx = dx;
		this.setDimx(dimx);
		this.dimy = dimy;
		this.sprite = sprite;
		hitbox = new Rectangle(x, y, dimx, dimy);
	}
	
	public void stepNext()
	{
		p.setX(p.getX() + dx);
		hitbox = (new Rectangle(this.p.x, this.p.y, this.dimx,this.dimy));
	}
	
}
