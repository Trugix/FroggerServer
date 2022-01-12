import java.awt.*;
import java.awt.image.BufferedImage;

public class NPC extends Entity {

	int v;  //ogni quanti frame si muove
	boolean deathTouch;
	int c=0;
	public NPC(int x, int y, int dx, BufferedImage sprite, int dimx, int dimy, int v, boolean deathTouch) {
		super(x, y, dx, sprite, dimx, dimy);
		this.v = v;
		this.deathTouch=deathTouch;
	}

	@Override
	public void stepNext()
	{
		if(++c==v)
		{
			this.p.setX(this.p.getX() + this.dx);
			this.hitbox=new Rectangle(this.p.getX(), this.p.getY(), dimx, dimy);
			c=0;
		}
	}

}
