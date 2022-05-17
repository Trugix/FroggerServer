import java.awt.Rectangle;
import java.io.Serializable;

public class Frog extends Entity implements Serializable   //rana giocabile
{
	
	private static final int MAX_VITE = 6;
	
	//di quanto si muove la rana ogni frame
	private static final int DX = 14;
	private static final int DY = 20;
	
	//coordinate di start della rana
	private static final int STARTING_FROGX = 460;
	private static final int STARTING_FROGY = 10;
	
	private int vite;   // numero di vite attuali
	
	public int getDirection()
	{
		return direction;
	}
	
	public void setDirection(int direction)
	{
		this.direction = direction;
	}
	
	private int direction = 0;  //direzione in cui la rana sta guardando
	
	private boolean isMoving = false;
	
	public boolean isMoving()
	{
		return isMoving;
	}
	
	public void setMoving(boolean moving)
	{
		isMoving = moving;
	}
	
	
	public Frog(int x, int y, int dx, String spriteID, int dimx, int dimy)
	{
		super(x, y, dx, spriteID, dimx, dimy);
		vite = MAX_VITE;
	}
	
	public int getVite()
	{
		return vite;
	}
	
	/**
	 * ruota lo sprite della rana quando si cambia direzione
	 *
	 * @param targetDir direzione in cui voglio guardare
	 */
	public void rotate(int targetDir)
	{
		switch (targetDir)
		{
			case 0 -> this.spriteID = "frogUp";
			case 1 -> this.spriteID = "frogRight";
			case 2 -> this.spriteID = "frogDown";
			case 3 -> this.spriteID = "frogLeft";
		}
	}
	
	public void setVite(int vite)
	{
		this.vite = vite;
	}
	
	public void updateHitbox()
	{
		this.hitbox = new Rectangle(this.p.x + 10, this.p.y + 5, this.dimx - 20, this.dimy - 10);
	}
	
	/**
	 *
	 */
	public void morte()
	{
		resetPosition();
		isMoving = false;
		this.vite--;
	}
	
	public void resetPosition()
	{
		this.p.setX(STARTING_FROGX);
		this.p.setY(STARTING_FROGY);
		updateHitbox();
		rotate(0);
	}
	
	/**
	 * metodo che aggiorna la posizione e l'hitbox della rana ad ogni frame
	 */
	public void stepNext(int tempDx)
	{
		p.setX(p.getX() + tempDx);
		if (p.getX() > 920) //per evitare che la rana se ne vada dalla schermo
			p.setX(920);
		if (p.getX() < 0) //per evitare che la rana se ne vada dalla schermo
			p.setX(0);
		updateHitbox();
	}
	
	/**
	 * metodo che fa muovere la rana in più frame al posto che in un singolo frame
	 * in questo modo il movimento è più fluido e si possono usare altri sprite in movimento
	 */
	public void nextSlide()
	{
		if (isMoving)
		{
			switch (direction)
			{
				case 0:
					spriteID = "frogMovUp";
					p.y += DY;
					if (p.getY() > 1210)
						p.setY(1210);
					break;
				case 1:
					spriteID = "frogMovRight";
					p.x += DX;
					if (p.getX() > 920)
						p.setX(920);
					break;
				case 2:
					spriteID = "frogMovDown";
					p.y -= DY;
					if (p.getY() < 10)
						p.setY(10);
					break;
				case 3:
					spriteID = "frogMovLeft";
					p.x -= DX;
					if (p.getX() < 0)
						p.setX(0);
					break;
			}
		}
		hitbox = (new Rectangle(this.p.x, this.p.y, this.dimx, this.dimy));
	}
}
