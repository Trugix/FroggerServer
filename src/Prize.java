import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

public class Prize extends Entity   //entità che danno un punteggio se colpite
{
	private final boolean bonus;    //definisce se questo premio è una mosca o una lilypad
	private final int point;    //valore in punti del premio

	public Prize(int x, int y, int dx, String spriteID, int dimx, int dimy, boolean bonus, int point)
	{
		super(x, y, dx, spriteID, dimx, dimy);
		this.bonus=bonus;
		this.point = point;
		this.setHitbox(new Rectangle(x+dimx/6+15,y+dimy/6,dimx/3,dimy/3));
	}


	public boolean isBonus()
	{
		return bonus;
	}

	public int getPoint()
	{
		return point;
	}
	
	/**
	 * metodo che aggiorna la posizione e l'hitbox della mosca ad ogni frame
	 */
	public void stepNext(ArrayList <Position> destination)
	{
		if(this.bonus)  //solo le mosche si devono spostare
		{
			if(destination.size()>0)    // se c'è spazio la mosca si muovo
			{
				Random random = new Random();
				Position p = destination.get(random.nextInt(destination.size()));   //randomizzo la posizione
				this.p.setX(p.getX());
				this.p.setY(p.getY());
				this.setHitbox(new Rectangle(this.p.getX()+this.getDimx()/6+5, this.p.getY(), this.getDimx() / 3, this.getDimy() / 3));
				setSprite("fly");
			}
			else    //altrimenti la mosca non appare
			{
				setSprite("void");
				this.setHitbox(null);
			}
		}
	}
}
