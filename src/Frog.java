import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Frog extends Entity {

	public enum rotazione{
		UP,
		RIGHT,
		DOWN,
		LEFT;
	};
	rotazione r;
	int dy;
	public rotazione next(rotazione r) {
		switch (r) {
			case UP:
				return rotazione.LEFT;
			case LEFT:
				return rotazione.DOWN;
			case DOWN:
				return rotazione.RIGHT;
			case RIGHT:
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


	public void rotate(rotazione targetDir){
		while(targetDir!=r)
		{
			sprite=rotateSprite(sprite);
			r=next(r);
		}
	}

	public BufferedImage rotateSprite( BufferedImage img )
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
