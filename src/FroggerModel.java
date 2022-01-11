import javax.imageio.ImageIO;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class FroggerModel {

	public ArrayList<Entity> entities = new ArrayList<>();
	public ArrayList<NPC> NPCs = new ArrayList<>();
	BufferedImage sprite = (BufferedImage) ImageIO.read(new File("src/frog.png"));
	BufferedImage spriteCarro = (BufferedImage) ImageIO.read(new File("src/carro1.png"));
	Frog frog = new Frog(46, 0, 8, sprite, 8, 8);
	NPC carro1 = new NPC(100,8, -1, spriteCarro, 16,8);

	public FroggerModel() throws IOException {
		entities.add(frog);
		entities.add(carro1);
		NPCs.add(carro1);
	}

	public void moveFrog(KeyEvent e) {
		switch (e.getKeyCode()) {       //todo capire why è al contrario
			case KeyEvent.VK_LEFT:    //è perché l'algoritmo routa attorno all'angolo top left
				frog.p.setX(frog.p.getX() - frog.dx);
				if (frog.p.getX() < 0) {
					frog.p.setX(0);
				}
				frog.rotate(Frog.rotazione.LEFT);
				break;

			case KeyEvent.VK_RIGHT:
				frog.p.setX(frog.p.getX() + frog.dx);
				if (frog.p.getX() > 92) {
					frog.p.setX(92);
				}
				frog.rotate(Frog.rotazione.RIGHT);
				break;

			case KeyEvent.VK_DOWN:
				frog.p.y -= frog.dy;
				if (frog.p.y < 0) {
					frog.p.y = 0;
				}
				frog.rotate(Frog.rotazione.DOWN);
				break;
			case KeyEvent.VK_UP:
				frog.p.y += frog.dy;
				if (frog.p.y > 142) {
					frog.p.y = 142;
				}
				frog.rotate(Frog.rotazione.UP);
				break;
		}
	}
}
