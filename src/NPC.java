import java.awt.Image;
import java.awt.image.BufferedImage;

public class NPC extends Entity {

	int v;  //ogni quanti frame si muove
	public NPC(int x, int y, int dx, BufferedImage sprite, int dimx, int dimy, int v) {
		super(x, y, dx, sprite, dimx, dimy);
		this.v = v;
	}
	

}
