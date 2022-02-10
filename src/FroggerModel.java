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
	
	public static final String PATH ="src/../sprites/";
	
	public ArrayList<Entity> entities = new ArrayList<>();
	public ArrayList<NPC> NPCs = new ArrayList<>();
	public int tempo=500;
	BufferedImage spriteFrog = ImageIO.read(new File(PATH + "frogUp.png"));
	BufferedImage spriteCarro =  ImageIO.read(new File(PATH+"carro1.png"));
	BufferedImage spriteLog6 = ImageIO.read(new File(PATH+"log6.png"));
	BufferedImage spriteLog3 = ImageIO.read(new File(PATH+"log3.png"));
	BufferedImage spriteLog4 = ImageIO.read(new File(PATH+"log4.png"));
	BufferedImage spriteFly = ImageIO.read(new File(PATH+"fly.png"));
	//BufferedImage spriteLilyPad = ImageIO.read(new File(PATH+"lilyPad.png"));
	
	Frog frog = new Frog(STARTING_FROGX, STARTING_FROGY, 100, spriteFrog, 80, 80);
	
	NPC carroA = new NPC(1000,110, -5, spriteCarro, 160,100,true);
	//NPC carroB = new NPC(500,110, -5, spriteCarro, 160,100,true);
	
	NPC carroB = new NPC(500,210, -5, spriteCarro, 160,100,true);
	NPC carroC = new NPC(100,310, -5, spriteCarro, 160,100,true);
	NPC carroD = new NPC(235,410, -5, spriteCarro, 160,100,true);
	NPC carroE = new NPC(500,510, -5, spriteCarro, 160,100,true);
	
	
	NPC log6A = new NPC(1000,1110,12,spriteLog6,480,100,false);
	NPC log6B = new NPC(100,1110,12,spriteLog6,480,100,false);
	NPC log4A = new NPC(1000,810,10,spriteLog4,320,100,false);
	NPC log4B = new NPC(50,810,10,spriteLog4,320,100,false);
	NPC log4C = new NPC(550,810,10,spriteLog4,320,100,false);
	NPC log3A = new NPC(1050,910,5,spriteLog3,240,100,false);
	NPC log3B = new NPC(668,910,5,spriteLog3,240,100,false);
	NPC log3C = new NPC(300,910,5,spriteLog3,240,100,false);
	NPC log3D = new NPC(-10,910,5,spriteLog3,240,100,false);
	/*NPC lilyPadA = new NPC(30,1210,0,spriteLilyPad,100,100,false);
	NPC lilyPadB = new NPC(240,1210,0,spriteLilyPad,100,100,false);
	NPC lilyPadC = new NPC(450,1210,0,spriteLilyPad,1000,1000,false);
	NPC lilyPadD = new NPC(66,1210,0,spriteLilyPad,1000,1000,false);
	NPC lilyPadE = new NPC(870,1210,0,spriteLilyPad,100,100,false);*/
	
	NPC fly = new NPC(465,1215,0,spriteFly,100,100,false);
	
	public FroggerModel() throws IOException {
		entities.add(frog);
		
		
		NPCs.add(carroC);
		NPCs.add(carroD);
		NPCs.add(carroE);

		
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
		/*NPCs.add(lilyPadA);
		NPCs.add(lilyPadB);
		NPCs.add(lilyPadC);
		NPCs.add(lilyPadD);
		NPCs.add(lilyPadE);*/
		
		NPCs.add(fly);
		
		for (NPC n:NPCs)
		{
			entities.add(n);
		}
	}

	public void moveFrog(KeyEvent e) throws IOException
	{
		switch (e.getKeyCode()) {       //todo capire why è al contrario
			case KeyEvent.VK_LEFT:    //è perché l'algoritmo routa attorno all'angolo top left
				frog.p.setX(frog.p.getX() - frog.dx);
				if (frog.p.getX() < 0)
					frog.p.setX(0);
				frog.rotate("LEFT" );
				break;
			case KeyEvent.VK_RIGHT:
				frog.p.setX(frog.p.getX() + frog.dx);
				if (frog.p.getX() > 920)
					frog.p.setX(920);
				frog.rotate("RIGHT");
				break;
			case KeyEvent.VK_DOWN:
				frog.p.setY(frog.p.getY()-frog.dy);
				if (frog.p.getY() < 10)
					frog.p.setY(10);
				frog.rotate("DOWN");
				break;
			case KeyEvent.VK_UP:
				frog.p.setY(frog.p.getY()+frog.dy);
				if (frog.p.getY() > 1210)
					frog.p.setY(1210);
				frog.rotate("UP");
				break;
		}
		frog.updateHitbox();
	}
}
