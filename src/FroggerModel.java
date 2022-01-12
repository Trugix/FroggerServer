import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class FroggerModel {

	private static final int STARTING_FROGX = 46;
	private static final int STARTING_FROGY = 1;
	public ArrayList<Entity> entities = new ArrayList<>();
	public ArrayList<NPC> NPCs = new ArrayList<>();
	BufferedImage spriteFrog = ImageIO.read(new File("src/frog.png"));
	BufferedImage spriteCarro =  ImageIO.read(new File("src/carro1.png"));
	BufferedImage spriteLog6 = ImageIO.read(new File("src/log6.png"));
	BufferedImage spriteLog3 = ImageIO.read(new File("src/log3.png"));
	BufferedImage spriteLog4 = ImageIO.read(new File("src/log4.png"));
	BufferedImage spriteLilyPad = ImageIO.read(new File("src/lilyPad.png"));
	
	Frog frog = new Frog(STARTING_FROGX, STARTING_FROGY, 10, spriteFrog, 8, 8);
	
	NPC carroA = new NPC(100,11, -1, spriteCarro, 16,110,2,true);
	NPC carroB = new NPC(50,11, -1, spriteCarro, 16,10,2,true);
	NPC log6A = new NPC(100,111,1,spriteLog6,48,10,1,false);
	NPC log6B = new NPC(10,111,1,spriteLog6,48,10,1,false);
	NPC log4A = new NPC(100,81,1,spriteLog4,32,10,1,false);
	NPC log4B = new NPC(5,81,1,spriteLog4,32,10,1,false);
	NPC log4C = new NPC(55,81,1,spriteLog4,32,10,1,false);
	NPC log3A = new NPC(105,91,1,spriteLog3,24,10,2,false);
	NPC log3B = new NPC(67,91,1,spriteLog3,24,10,2,false);
	NPC log3C = new NPC(38,91,1,spriteLog3,24,10,2,false);
	NPC log3D = new NPC(0,91,1,spriteLog3,24,10,2,false);
	NPC lilyPadA = new NPC(3,121,0,spriteLilyPad,10,10,0,false);
	NPC lilyPadB = new NPC(24,121,0,spriteLilyPad,10,10,0,false);
	NPC lilyPadC = new NPC(45,121,0,spriteLilyPad,10,10,0,false);
	NPC lilyPadD = new NPC(66,121,0,spriteLilyPad,10,10,0,false);
	NPC lilyPadE = new NPC(87,121,0,spriteLilyPad,10,10,0,false);

	public FroggerModel() throws IOException {
		entities.add(frog);
		
		NPCs.add(carroA);
		NPCs.add(carroB);
		NPCs.add(log6A);
		NPCs.add(log6B);
		NPCs.add(log4A);
		NPCs.add(log4B);
		NPCs.add(log4C);
		NPCs.add(log3A);
		NPCs.add(log3B);
		NPCs.add(log3C);
		NPCs.add(log3D);
		NPCs.add(lilyPadA);
		NPCs.add(lilyPadB);
		NPCs.add(lilyPadC);
		NPCs.add(lilyPadD);
		NPCs.add(lilyPadE);
		
		for (NPC n:NPCs)
		{
			entities.add(n);
		}
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
