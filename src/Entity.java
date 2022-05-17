import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

public class Entity //entità generica presente nel gioco
{
	public int getDimx()
	{
		return dimx;
	}

	public void setDimx(int dimx)
	{
		this.dimx = dimx;
	}

	protected class Position implements Serializable //posizione dell'entità espressa in x,y
	{
		protected int x;
		protected int y;

		public Position(int x, int y)
		{
			this.setX(x);
			this.setY(y);
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
	protected String spriteID;  //stringa che verrà usata per linkare ogni entità al suo sprite
	protected int dx; //numero di pixel che l'entità percorre in un frame
	protected int dimx; //larghezza
	protected int dimy; //altezza
	protected Rectangle2D hitbox;   //hitbox usata per calcolare le collisioni

	public Entity () {} //costruttore usato per generare delle posizioni senza creare un'entità

	public Entity(int x, int y, int dx, String spriteID, int dimx, int dimy)
	{
		this.p = new Position(x, y);
		this.dx = dx;
		this.setDimx(dimx);
		this.dimy = dimy;
		this.spriteID = spriteID;
		this.hitbox = new Rectangle(x, y, dimx, dimy);
	}

	public void setHitbox(Rectangle2D hitbox)
	{
		this.hitbox = hitbox;
	}

	public void setSprite(String spriteID)
	{
		this.spriteID = spriteID;
	}
	
	/**
	 * metodo che aggiorna la posizione e l'hitbox dell'entità ad ogni frame
	 */
	public void stepNext()
	{
		p.setX(p.getX() + dx);
		hitbox = (new Rectangle(this.p.x, this.p.y, this.dimx,this.dimy));
	}

}
