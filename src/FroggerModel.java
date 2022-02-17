import javax.imageio.ImageIO;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class FroggerModel
{
	
	private static final int STARTING_FROGX = 460;
	private static final int STARTING_FROGY = 10;
	
	public static final String PATH = "src/../sprites/";
	
	public ArrayList<Entity> entities = new ArrayList<>();
	public ArrayList<NPC> NPCs = new ArrayList<>();
	public ArrayList<Prize> prizes = new ArrayList<>();
	public ArrayList<Turtle> turtles = new ArrayList<>();
	public ArrayList<Skull> skulls = new ArrayList<>();
	
	public int tempo = 500;
	
	BufferedImage spriteFrog = ImageIO.read(new File(PATH + "frogUp.png"));
	
	BufferedImage spriteCarro = ImageIO.read(new File(PATH + "carro1.png"));
	BufferedImage spriteAutoSport = ImageIO.read(new File(PATH + "autoSport.png"));
	BufferedImage spriteBulldozer = ImageIO.read(new File(PATH + "bulldozer.png"));
	BufferedImage spriteFormula1 = ImageIO.read(new File(PATH + "carFormula1.png"));
	BufferedImage spriteFormula2 = ImageIO.read(new File(PATH + "carFormula2.png"));
	BufferedImage spritePolice = ImageIO.read(new File(PATH + "carPolice.png"));
	
	BufferedImage spriteLog6 = ImageIO.read(new File(PATH + "log6.png"));
	BufferedImage spriteLog3 = ImageIO.read(new File(PATH + "log3.png"));
	BufferedImage spriteLog4 = ImageIO.read(new File(PATH + "log4.png"));
	
	BufferedImage spriteTurtle1 = ImageIO.read(new File(PATH + "turtle1.png"));
	BufferedImage spriteTurtle2 = ImageIO.read(new File(PATH + "turtle2.png"));
	BufferedImage spriteTurtle3 = ImageIO.read(new File(PATH + "turtle3.png"));
	
	BufferedImage spriteFly = ImageIO.read(new File(PATH + "fly.png"));

	BufferedImage spriteLilyPad = ImageIO.read(new File(PATH + "lily.png"));
	BufferedImage spriteFrogLily = ImageIO.read(new File(PATH + "frogAtRest.png"));
	
	BufferedImage spriteSkull = ImageIO.read(new File(PATH + "skull.png"));

	BufferedImage spriteVoid = ImageIO.read(new File(PATH + "void.png"));
	
	Frog frog = new Frog(STARTING_FROGX, STARTING_FROGY, 70, spriteFrog, 75, 75);
	
	NPC carroA = new NPC(500, 510, -7, spriteCarro, 200, 85, true);
	NPC carroB = new NPC(981, 510, -7, spriteCarro, 200, 85, true);
	NPC autoSportA = new NPC(235, 410, 10, spriteAutoSport, 100, 85, true);
	NPC autoSportB = new NPC(884, 410, 10, spriteAutoSport, 100, 85, true);
	NPC bulldozerA = new NPC(15, 310, -5, spriteBulldozer, 130, 85, true);
	NPC bulldozerB = new NPC(354, 310, -5, spriteBulldozer, 130, 85, true);
	NPC bulldozerC = new NPC(846, 310, -5, spriteBulldozer, 130, 85, true);
	NPC carFormula1 = new NPC(123, 210, 12, spriteFormula1, 100, 85, true);
	NPC carFormula2 = new NPC(724, 210, 12, spriteFormula2, 100, 85, true);
	NPC carPoliceA = new NPC(684, 110, -10, spritePolice, 110, 85, true);
	NPC carPoliceB = new NPC(215, 110, -10, spritePolice, 110, 85, true);
	
	NPC log6A = new NPC(1000, 1110, 11, spriteLog6, 595, 85, false);
	NPC log6B = new NPC(100, 1110, 11, spriteLog6, 595, 85, false);
	NPC log4A = new NPC(987, 810, 9, spriteLog4, 395, 85, false);
	NPC log4B = new NPC(-144, 810, 9, spriteLog4, 395, 85, false);
	NPC log4C = new NPC(394, 810, 9, spriteLog4, 395, 85, false);
	NPC log3A = new NPC(-217, 910, 5, spriteLog3, 295, 85, false);
	NPC log3B = new NPC(397, 910, 5, spriteLog3, 295, 85, false);
	NPC log3C = new NPC(943, 910, 5, spriteLog3, 295, 85, false);
	
	Turtle turtleA = new Turtle(698, 710, -6, spriteTurtle1, 110, 85, false, false);
	Turtle turtleB = new Turtle(254, 710, -6, spriteTurtle1, 110, 85, false, true);
	Turtle turtleC = new Turtle(1104, 710, -6, spriteTurtle1, 110, 85, false, true);
	Turtle turtleD = new Turtle(57, 1010, -5, spriteTurtle1, 110, 85, false, true);
	Turtle turtleE = new Turtle(348, 1010, -5, spriteTurtle1, 110, 85, false, true);
	Turtle turtleF = new Turtle(698, 1010, -5, spriteTurtle1, 110, 85, false, false);
	Turtle turtleG = new Turtle(1004, 1010, -5, spriteTurtle1, 110, 85, false, true);
	
	Prize lilyPadA = new Prize(30, 1210, 0, spriteLilyPad, 100, 100, false, 50);
	Prize lilyPadB = new Prize(240, 1210, 0, spriteLilyPad, 100, 100, false, 50);
	Prize lilyPadC = new Prize(450, 1210, 0, spriteLilyPad, 100, 100, false, 50);
	Prize lilyPadD = new Prize(660, 1210, 0, spriteLilyPad, 100, 100, false, 50);
	Prize lilyPadE = new Prize(870, 1210, 0, spriteLilyPad, 100, 100, false, 50);
	
	Prize fly = new Prize(465, 1215, 0, spriteFly, 74, 75, true, 200);
	
	Sound sound;
	
	public FroggerModel() throws IOException
	{
		entities.add(frog);
		
		NPCs.add(carroA);
		NPCs.add(carroB);
		NPCs.add(autoSportA);
		NPCs.add(autoSportB);
		NPCs.add(bulldozerA);
		NPCs.add(bulldozerB);
		NPCs.add(bulldozerC);
		NPCs.add(carFormula1);
		NPCs.add(carFormula2);
		NPCs.add(carPoliceA);
		NPCs.add(carPoliceB);
		
		NPCs.add(log6A);
		NPCs.add(log6B);
		NPCs.add(log4A);
		NPCs.add(log4B);
		NPCs.add(log4C);
		NPCs.add(log3A);
		NPCs.add(log3B);
		NPCs.add(log3C);
		
		turtles.add(turtleA);
		turtles.add(turtleB);
		turtles.add(turtleD);
		turtles.add(turtleC);
		turtles.add(turtleE);
		turtles.add(turtleF);
		turtles.add(turtleG);
		turtleA.addSprites(spriteTurtle2);
		turtleA.addSprites(spriteTurtle3);
		turtleB.addSprites(spriteTurtle2);
		turtleB.addSprites(spriteTurtle3);
		turtleC.addSprites(spriteTurtle2);
		turtleC.addSprites(spriteTurtle3);
		turtleD.addSprites(spriteTurtle2);
		turtleD.addSprites(spriteTurtle3);
		turtleE.addSprites(spriteTurtle2);
		turtleE.addSprites(spriteTurtle3);
		turtleF.addSprites(spriteTurtle2);
		turtleF.addSprites(spriteTurtle3);
		turtleG.addSprites(spriteTurtle2);
		turtleG.addSprites(spriteTurtle3);
		
		
		prizes.add(lilyPadA);
		prizes.add(lilyPadB);
		prizes.add(lilyPadC);
		prizes.add(lilyPadD);
		prizes.add(lilyPadE);
		prizes.add(fly);
		
		
		NPCs.addAll(turtles);
		entities.addAll(NPCs);
		entities.addAll(prizes);
		
		sound = new Sound();
	}
	
	public void moveFrog(KeyEvent e) throws IOException
	{
		switch (e.getKeyCode())
		{       //todo capire why è al contrario
			case KeyEvent.VK_LEFT:    //è perché l'algoritmo routa attorno all'angolo top left
				frog.p.setX(frog.p.getX() - frog.dx);
				if (frog.p.getX() < 0)
					frog.p.setX(0);
				frog.rotate("LEFT");
				sound.soundHop();
				break;
			case KeyEvent.VK_RIGHT:
				frog.p.setX(frog.p.getX() + frog.dx);
				if (frog.p.getX() > 920)
					frog.p.setX(920);
				frog.rotate("RIGHT");
				sound.soundHop();
				break;
			case KeyEvent.VK_DOWN:
				frog.p.setY(frog.p.getY() - frog.dy);
				if (frog.p.getY() < 10)
					frog.p.setY(10);
				frog.rotate("DOWN");
				sound.soundHop();
				break;
			case KeyEvent.VK_UP:
				frog.p.setY(frog.p.getY() + frog.dy);
				if (frog.p.getY() > 1210)
					frog.p.setY(1210);
				frog.rotate("UP");
				sound.soundHop();
				break;
		}
		frog.updateHitbox();
	}
}
