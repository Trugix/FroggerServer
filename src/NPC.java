import java.awt.Rectangle;

public class NPC extends Entity //entità non giocabile
{
	boolean deathTouch; //definisce se toccando questo npc la rana muoia o no
	
	public NPC(int x, int y, int dx, String spriteID, int dimx, int dimy, boolean deathTouch)
	{
		super(x, y, dx, spriteID, dimx, dimy);
		this.deathTouch = deathTouch;
		
	}
	
	/**
	 * metodo che aggiorna la posizione e l'hitbox dell'npc ad ogni frame
	 */
	@Override
	public void stepNext()
	{
		this.p.setX(this.p.getX() + this.dx);
		this.hitbox = new Rectangle(this.p.getX(), this.p.getY(), dimx, dimy);
	}
	
}
