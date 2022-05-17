import java.awt.Rectangle;
import java.io.Serializable;

public class NPC extends Entity implements Serializable //entità non giocabile
{
	 private boolean deathTouch; //definisce se toccando questo npc la rana muoia o no
	
	public NPC(int x, int y, int dx, String spriteID, int dimx, int dimy, boolean deathTouch)
	{
		super(x, y, dx, spriteID, dimx, dimy);
		this.deathTouch = deathTouch;
		
	}
	
	public boolean isDeathTouch()
	{
		return deathTouch;
	}
	
	public void setDeathTouch(boolean deathTouch)
	{
		this.deathTouch = deathTouch;
	}
	
	/**
	 * metodo che aggiorna la posizione e l'hitbox dell'npc ad ogni frame
	 */
	@Override
	public void stepNext()
	{
		this.p.setX(this.p.getX() + this.getDx());
		this.setHitbox(new Rectangle(this.p.getX(), this.p.getY(), this.getDimx(), this.getDimy()));
	}
	
}
