import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

public class Frog extends Entity {

	private enum rotazione{
		UP,
		RIGHT,
		DOWN,
		LEFT;


	};
	rotazione r;
	int dy;
	private rotazione next(rotazione r) {
		switch (r) {
			case UP:
				return rotazione.RIGHT;
			case RIGHT:
				return rotazione.DOWN;
			case DOWN:
				return rotazione.LEFT;
			case LEFT:
				return rotazione.UP;
		}
		return null;
	}
	public Frog(int x, int y, int dx, BufferedImage sprite, int dimx, int dimy) throws IOException {
		super(x, y, dx, sprite, dimx, dimy);
		dy=dx;
		r=rotazione.UP;
		sprite = (BufferedImage) ImageIO.read(new File("src/frog.png"));
	}

	public void moveFrog(KeyEvent e, Graphics2D g2){
		switch(e.getKeyCode()){       //todo capire why è al contrario
			case KeyEvent.VK_LEFT:
				p.x -= dx;
				if (p.x<0) {
					p.x=0;
				}
				rotate(rotazione.RIGHT);

				break;
			case KeyEvent.VK_RIGHT:
				p.x += dx;
				if (p.x>92) {
					p.x=92;
				}
				rotate(rotazione.LEFT);


				break;
			case KeyEvent.VK_DOWN:
				p.y -= dy;
				if (p.y<0) {
					p.y=0;
				}
				rotate(rotazione.DOWN);


				break;
			case KeyEvent.VK_UP:
				p.y += dy;
				if (p.y>142) {
					p.y=142;
				}
				rotate(rotazione.UP);

				break;

		}


		//PnlFrog.repaint();
	}
	private void rotate(rotazione targetDir){
		while(targetDir!=r)
		{
			sprite=rotateCw(sprite);
			r=next(r);
		}
	}

	public static BufferedImage rotateCw( BufferedImage img )
	{
		int         width  = img.getWidth();
		int         height = img.getHeight();
		BufferedImage   newImage = new BufferedImage( height, width, img.getType() );

		for( int i=0 ; i < width ; i++ )
			for( int j=0 ; j < height ; j++ )
				newImage.setRGB( height-1-j, i, img.getRGB(i,j) );

		return newImage;
	}
}
