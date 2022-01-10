import java.awt.Image;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {
	public class Position{
		protected int x, y;
		public Position(int x, int y) {
			this.x=x;
			this.y=y;
		}
			
	}
	protected Position p;
	protected BufferedImage sprite;
	protected int dx;
	protected Area hitbox;
	
	public Entity(int x, int y, int dx, BufferedImage sprite, int dimx, int dimy)
	{
		this.p=new Position(x, y);
		this.dx = dx;
		this.sprite=sprite;
		hitbox = new Area(new Rectangle(dimx,dimy));
	}
	
	public void stepNext() {
		p.x += dx;
	}
	
}
