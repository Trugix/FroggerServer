import java.awt.*;
import java.awt.image.BufferedImage;

public class NPC extends Entity {

	int v;  //ogni quanti frame si muove
	boolean deathTouch;
	public NPC(int x, int y, int dx, BufferedImage sprite, int dimx, int dimy, boolean deathTouch) {
		super(x, y, dx, sprite, dimx, dimy);
		this.v = v;
		this.deathTouch=deathTouch;
	}

	@Override
	public void stepNext()
	{
		this.p.setX(this.p.getX() + this.dx);
		this.hitbox=new Rectangle(this.p.getX(), this.p.getY(), dimx, dimy);
	}

}
