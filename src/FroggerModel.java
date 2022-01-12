import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class FroggerModel {

	private static final int STARTING_FROGX = 460;
	private static final int STARTING_FROGY = 10;
	public ArrayList<Entity> entities = new ArrayList<>();
	public ArrayList<NPC> NPCs = new ArrayList<>();
	public int tempo=500;
	BufferedImage spriteFrog = ImageIO.read(new File("src/frog.png"));
	BufferedImage spriteCarro =  ImageIO.read(new File("src/carro1.png"));
	BufferedImage spriteLog6 = ImageIO.read(new File("src/log6.png"));
	BufferedImage spriteLog3 = ImageIO.read(new File("src/log3.png"));
	BufferedImage spriteLog4 = ImageIO.read(new File("src/log4.png"));
	BufferedImage spriteLilyPad = ImageIO.read(new File("src/lilyPad.png"));
	
	Frog frog = new Frog(STARTING_FROGX, STARTING_FROGY, 100, spriteFrog, 80, 80);
	
	NPC carroA = new NPC(1000,110, -10, spriteCarro, 160,1100,2,true);
	NPC carroB = new NPC(500,110, -10, spriteCarro, 160,100,2,true);
	NPC log6A = new NPC(1000,1110,10,spriteLog6,480,100,1,false);
	NPC log6B = new NPC(100,1110,10,spriteLog6,480,100,1,false);
	NPC log4A = new NPC(1000,810,10,spriteLog4,320,100,1,false);
	NPC log4B = new NPC(50,810,1,spriteLog4,320,100,1,false);
	NPC log4C = new NPC(550,810,1,spriteLog4,320,100,1,false);
	NPC log3A = new NPC(1050,910,1,spriteLog3,240,100,2,false);
	NPC log3B = new NPC(670,910,1,spriteLog3,240,100,2,false);
	NPC log3C = new NPC(380,910,1,spriteLog3,240,100,2,false);
	NPC log3D = new NPC(0,910,1,spriteLog3,240,100,2,false);
	NPC lilyPadA = new NPC(30,1210,0,spriteLilyPad,100,100,0,false);
	NPC lilyPadB = new NPC(240,1210,0,spriteLilyPad,100,100,0,false);
	NPC lilyPadC = new NPC(450,1210,0,spriteLilyPad,1000,1000,0,false);
	NPC lilyPadD = new NPC(66,1210,0,spriteLilyPad,1000,1000,0,false);
	NPC lilyPadE = new NPC(870,1210,0,spriteLilyPad,100,100,0,false);

	public FroggerModel() throws IOException {
		entities.add(frog);
		
		/*NPCs.add(carroA);
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
		}*/
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
				if (frog.p.getX() > 920)
					frog.p.setX(920);
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
				if (frog.p.getY() > 1210)
					frog.p.setY(1210);
				frog.rotate(Frog.rotazione.UP);
				break;
		}
		frog.updateHitbox();
	}
}
