import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
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
		
		public int getY()
		{
			return y;
		}
		
		public void setY(int y)
		{
			this.y = y;
		}
	}
	
	protected Position p;
	protected BufferedImage sprite;
	protected int dx;
	protected int dimx;
	protected int dimy;
	protected Rectangle2D hitbox;
	
	public Entity ()
	{
	
	}
	
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
	/*protected boolean isAlive = true;
	public boolean isAlive(){
		return isAlive;
	}*/
	
	protected Shape shape;
	
	public Shape getShape(){
		AffineTransform t = new AffineTransform();
		t.translate(p.getX(), p.getY());
		return t.createTransformedShape(shape);
	}
	
	public boolean checkCollision(Entity e){
		Area a = new Area(this.getShape());
		a.intersect(new Area(e.getShape()));
		
		return !a.isEmpty();
	}
	
	/*public void collisionDetected(Entity e)
	
	}
	*/
}
