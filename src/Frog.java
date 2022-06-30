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
	private static final int Y_LOWER_BOUND = 10;
	private static final int Y_HIGHER_BOUND = 1210;
	private static final int X_LEFT_BOUND = 0;
	private static final int X_RIGHT_BOUND = 920;
	
	private int vite;   // numero di vite attuali
	
	private int direction = 0;  //direzione in cui la rana sta guardando
	
	private boolean isMoving = false;
	
	public int getDirection()
	{
		return direction;
	}
	
	public void setDirection(int direction)
	{
		this.direction = direction;
	}
	
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
			case 0 ->
			{
				this.setSpriteID("frogUp");
				setDirection(targetDir);
			}
			case 1 ->
			{
				this.setSpriteID("frogRight");
				setDirection(targetDir);
			}
			case 2 ->
			{
				this.setSpriteID("frogDown");
				setDirection(targetDir);
			}
			case 3 ->
			{
				this.setSpriteID("frogLeft");
				setDirection(targetDir);
			}
		}
	}
	
	public void setVite(int vite)
	{
		this.vite = vite;
	}
	
	public void updateHitbox()
	{
		this.setHitbox(new Rectangle(this.p.x, this.p.y, this.getDimx(), this.getDimy()));
	}
	
	/**
	 * metodo che si occupa della gestione della morte della rana
	 */
	public void morte()
	{
		resetPosition(); //resetta la posizione
		isMoving = false; //ovviamente il movimento deve fermarsi
		this.vite--;    //tolgo una vita
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
		if (p.getX() > X_RIGHT_BOUND) //per evitare che la rana se ne vada dalla schermo
			p.setX(X_RIGHT_BOUND);
		if (p.getX() < X_LEFT_BOUND) //per evitare che la rana se ne vada dalla schermo
			p.setX(X_LEFT_BOUND);
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
				case 0 ->
				{
					setSpriteID("frogMovUp");
					p.y += DY;
					if (p.getY() > Y_HIGHER_BOUND)
						p.setY(Y_HIGHER_BOUND);
				}
				case 1 ->
				{
					setSpriteID("frogMovRight");
					p.x += DX;
					if (p.getX() > X_RIGHT_BOUND)
						p.setX(X_RIGHT_BOUND);
				}
				case 2 ->
				{
					setSpriteID("frogMovDown");
					p.y -= DY;
					if (p.getY() < Y_LOWER_BOUND)
						p.setY(Y_LOWER_BOUND);
				}
				case 3 ->
				{
					setSpriteID("frogMovLeft");
					p.x -= DX;
					if (p.getX() < X_LEFT_BOUND)
						p.setX(X_LEFT_BOUND);
				}
			}
		}
		updateHitbox();
	}
}
