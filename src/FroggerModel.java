import javax.imageio.ImageIO;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class FroggerModel {

	public ArrayList<Entity> entities = new ArrayList<>();
	public ArrayList<NPC> NPCs = new ArrayList<>();
	BufferedImage spriteFrog = ImageIO.read(new File("src/frog.png"));
	BufferedImage spriteCarro =  ImageIO.read(new File("src/carro1.png"));
	BufferedImage spriteLog6 = ImageIO.read(new File("src/log6.png"));
	BufferedImage spriteLog3 = ImageIO.read(new File("src/log3.png"));
	BufferedImage spriteLog4 = ImageIO.read(new File("src/log4.png"));
	Frog frog = new Frog(STARTING_FROGX, STARTING_FROGY, 10, spriteFrog, 8, 8);
	NPC carro1 = new NPC(100,11, -1, spriteCarro, 16,10,true);
	NPC log6 = new NPC(100,111,1,spriteLog6,48,10,false);
	NPC log4 = new NPC(100,91,1,spriteLog4,32,10,false);
	NPC log3 = new NPC(100,81,1,spriteLog3,24,10,false);

	public FroggerModel() throws IOException {
		entities.add(frog);
		entities.add(carro1);
		entities.add(log6);
		entities.add(log4);
		entities.add(log3);
		NPCs.add(carro1);
	}

	public void moveFrog(KeyEvent e) {
		switch (e.getKeyCode()) {       //todo capire why è al contrario
			case KeyEvent.VK_LEFT:    //è perché l'algoritmo routa attorno all'angolo top left
				frog.p.setX(frog.p.getX() - frog.dx);
				if (frog.p.getX() < 0)
					frog.p.setX(0);
				frog.rotate(Frog.rotazione.LEFT);
				break;
			case KeyEvent.VK_RIGHT:
				frog.p.setX(frog.p.getX() + frog.dx);
				if (frog.p.getX() > 92)
					frog.p.setX(92);
				frog.rotate(Frog.rotazione.RIGHT);
				break;
			case KeyEvent.VK_DOWN:
				frog.p.setY(frog.p.getY()-frog.dy);
				if (frog.p.getY() < 0)
					frog.p.setY(0);
				frog.rotate(Frog.rotazione.DOWN);
				break;
			case KeyEvent.VK_UP:
				frog.p.setY(frog.p.getY()+frog.dy);
				if (frog.p.getY() > 122)
					frog.p.setY(122);
				frog.rotate(Frog.rotazione.UP);
				break;
		}
		frog.updateHitbox();
	}
}
