import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class FroggerModel {

	private static final int STARTING_FROGX = 460;
	private static final int STARTING_FROGY = 10;
	
	public static final String PATH ="src/../sprites/";
	
	public ArrayList<Entity> entities = new ArrayList<>();
	public ArrayList<NPC> NPCs = new ArrayList<>();
	public ArrayList<Prize> prizes = new ArrayList<>();
	public ArrayList<Turtle> turtles = new ArrayList<>();
	
	public int tempo=500;
	
	
	BufferedImage spriteFrog = ImageIO.read(new File(PATH + "frogUp.png"));
	
	BufferedImage spriteCarro =  ImageIO.read(new File(PATH+"carro1.png"));
	BufferedImage spriteAutoSport = ImageIO.read(new File(PATH+"autoSport.png"));
	
	BufferedImage spriteLog6 = ImageIO.read(new File(PATH+"log6.png"));
	BufferedImage spriteLog3 = ImageIO.read(new File(PATH+"log3.png"));
	BufferedImage spriteLog4 = ImageIO.read(new File(PATH+"log4.png"));
	
	BufferedImage spriteTurtle1 = ImageIO.read(new File(PATH+"r1.png"));
	BufferedImage spriteTurtle2 = ImageIO.read(new File(PATH+"r2.png"));
	BufferedImage spriteTurtle3 = ImageIO.read(new File(PATH+"r3.png"));
	
	BufferedImage spriteFly = ImageIO.read(new File(PATH+"fly.png"));
	BufferedImage spriteLilyPad = ImageIO.read(new File(PATH+"temp.png"));
	
	Frog frog = new Frog(STARTING_FROGX, STARTING_FROGY, 100, spriteFrog, 75, 75);
	/*
	NPC carroE = new NPC(1000,110, -5, spriteCarro, 160,100,true);
	NPC carroC = new NPC(500,210, -5, spriteCarro, 160,100,true);
	*/
	
	NPC carroA = new NPC(500,510, -5, spriteCarro, 200,85,true);
	NPC carroB = new NPC(981,510, -5, spriteCarro, 200,85,true);
	NPC autoSportA = new NPC(235,410, 12, spriteAutoSport, 100,85,true);
	NPC autoSportB = new NPC(884,410, 12, spriteAutoSport, 100,85,true);
	
	
	
	NPC log6A = new NPC(1000,1110,11,spriteLog6,595,85,false);
	NPC log6B = new NPC(100,1110,11,spriteLog6,595,85,false);
	NPC log4A = new NPC(987,810,9,spriteLog4,395,85,false);
	NPC log4B = new NPC(-144,810,9,spriteLog4,395,85,false);
	NPC log4C = new NPC(394,810,9,spriteLog4,395,85,false);
	NPC log3A = new NPC(-217,910,5,spriteLog3,295,85,false);
	NPC log3B = new NPC(397,910,5,spriteLog3,295,85,false);
	NPC log3C = new NPC(943,910,5,spriteLog3,295,85,false);
	NPC log3D = new NPC(1005,910,5,spriteLog3,295,85,false);
	
	Turtle t1 = new Turtle(1000,710,6,spriteTurtle3,95,85,false,false);
	Turtle t2 = new Turtle(500,710,6,spriteTurtle3,95,85,false,true);
	
	Prize lilyPadA = new Prize(30,1210,0,spriteLilyPad,100,85,false,50);
	Prize lilyPadB = new Prize(240,1210,0,spriteLilyPad,100,85,false,50);
	Prize lilyPadC = new Prize(450,1210,0,spriteLilyPad,100,85,false,50);
	Prize lilyPadD = new Prize(660,1210,0,spriteLilyPad,100,85,false,50);
	Prize lilyPadE = new Prize(870,1210,0,spriteLilyPad,100,85,false,50);
	
	NPC log6T1 = new NPC(1000,710,-12,spriteLog6,595,85,false);
	NPC log6T2 = new NPC(1000,1010,-12,spriteLog6,595,85,false);
	
	Prize fly = new Prize(465,1215,0,spriteFly,74,75,true,200);
	
	
	public FroggerModel() throws IOException {
		entities.add(frog);
		
		/*
		NPCs.add(carroC);
		NPCs.add(carroE);
		*/
		
		NPCs.add(carroA);
		NPCs.add(carroB);
		NPCs.add(autoSportA);
		NPCs.add(autoSportB);
		
		NPCs.add(log6A);
		NPCs.add(log6B);
		NPCs.add(log4A);
		NPCs.add(log4B);
		NPCs.add(log4C);
		NPCs.add(log3A);
		NPCs.add(log3B);
		NPCs.add(log3C);
		//NPCs.add(log3D);
		
		turtles.add(t1);
		turtles.add(t2);
		
		prizes.add(lilyPadA);
		prizes.add(lilyPadB);
		prizes.add(lilyPadC);
		prizes.add(lilyPadD);
		prizes.add(lilyPadE);
		prizes.add(fly);
		
		//NPCs.add(log6T1);
		NPCs.add(log6T2);
		
		NPCs.addAll(turtles);
		entities.addAll(NPCs);
		entities.addAll(prizes);
		
		
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
