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
	
	public static final String PATH_SPRITE = "src/../sprites/";
	
	public ArrayList<Entity> entities = new ArrayList<>();
	public ArrayList<NPC> NPCs = new ArrayList<>();
	public ArrayList<Prize> prizes = new ArrayList<>();
	public ArrayList<Turtle> turtles = new ArrayList<>();
	public ArrayList<Skull> skulls = new ArrayList<>();
	
	public int tempo = 500;
	
	public BufferedImage[] spritesFrog = {ImageIO.read(new File(PATH_SPRITE + "frogUp.png")), ImageIO.read(new File(PATH_SPRITE + "frogRight.png")),
			ImageIO.read(new File(PATH_SPRITE + "frogDown.png")), ImageIO.read(new File(PATH_SPRITE + "frogLeft.png"))};

	
	public final BufferedImage spriteCarro = ImageIO.read(new File(PATH_SPRITE + "carro1.png"));
	public final BufferedImage spriteAutoSport = ImageIO.read(new File(PATH_SPRITE + "autoSport.png"));
	public final BufferedImage spriteBulldozer = ImageIO.read(new File(PATH_SPRITE + "bulldozer.png"));
	public final BufferedImage spriteFormula1 = ImageIO.read(new File(PATH_SPRITE + "carFormula1.png"));
	public final BufferedImage spriteFormula2 = ImageIO.read(new File(PATH_SPRITE + "carFormula2.png"));
	public final BufferedImage spritePolice = ImageIO.read(new File(PATH_SPRITE + "carPolice.png"));
	
	public final BufferedImage spriteLog6 = ImageIO.read(new File(PATH_SPRITE + "log6.png"));
	public final BufferedImage spriteLog3 = ImageIO.read(new File(PATH_SPRITE + "log3.png"));
	public final BufferedImage spriteLog4 = ImageIO.read(new File(PATH_SPRITE + "log4.png"));
	
	public final BufferedImage spriteTurtle1 = ImageIO.read(new File(PATH_SPRITE + "turtle1.png"));
	public final BufferedImage spriteTurtle2 = ImageIO.read(new File(PATH_SPRITE + "turtle2.png"));
	public final BufferedImage spriteTurtle3 = ImageIO.read(new File(PATH_SPRITE + "turtle3.png"));
	
	public final BufferedImage spriteFly = ImageIO.read(new File(PATH_SPRITE + "fly.png"));
	
	public final BufferedImage spriteLilyPad = ImageIO.read(new File(PATH_SPRITE + "lily.png"));
	public final BufferedImage spriteFrogLily = ImageIO.read(new File(PATH_SPRITE + "frogAtRest.png"));
	
	public final BufferedImage spriteSkull = ImageIO.read(new File(PATH_SPRITE + "skull.png"));
	
	public final BufferedImage spriteVoid = ImageIO.read(new File(PATH_SPRITE + "void.png"));
	
	public Frog frog = new Frog(STARTING_FROGX, STARTING_FROGY, 70, spritesFrog, 75, 75);
	
	private final NPC carroA = new NPC(500, 510, -7, spriteCarro, 200, 85, true);
	private final NPC carroB = new NPC(981, 510, -7, spriteCarro, 200, 85, true);
	private final NPC autoSportA = new NPC(235, 410, 10, spriteAutoSport, 100, 85, true);
	private final NPC autoSportB = new NPC(884, 410, 10, spriteAutoSport, 100, 85, true);
	private final NPC bulldozerA = new NPC(15, 310, -5, spriteBulldozer, 130, 85, true);
	private final NPC bulldozerB = new NPC(354, 310, -5, spriteBulldozer, 130, 85, true);
	private final NPC bulldozerC = new NPC(846, 310, -5, spriteBulldozer, 130, 85, true);
	private final NPC carFormula1 = new NPC(123, 210, 12, spriteFormula1, 100, 85, true);
	private final NPC carFormula2 = new NPC(724, 210, 12, spriteFormula2, 100, 85, true);
	private final NPC carPoliceA = new NPC(684, 110, -10, spritePolice, 110, 85, true);
	private final NPC carPoliceB = new NPC(215, 110, -10, spritePolice, 110, 85, true);
	
	private final NPC log6A = new NPC(1000, 1110, 11, spriteLog6, 595, 85, false);
	private final NPC log6B = new NPC(100, 1110, 11, spriteLog6, 595, 85, false);
	private final NPC log4A = new NPC(987, 810, 9, spriteLog4, 395, 85, false);
	private final NPC log4B = new NPC(-144, 810, 9, spriteLog4, 395, 85, false);
	private final NPC log4C = new NPC(394, 810, 9, spriteLog4, 395, 85, false);
	private final NPC log3A = new NPC(-217, 910, 5, spriteLog3, 295, 85, false);
	private final NPC log3B = new NPC(397, 910, 5, spriteLog3, 295, 85, false);
	private final NPC log3C = new NPC(943, 910, 5, spriteLog3, 295, 85, false);
	
	private final NPC turtleA = new Turtle(698, 710, -6, spriteTurtle1, 110, 85, false);
	private final Turtle turtleB = new Turtle(254, 710, -6, spriteTurtle1, 110, 85, false);
	private final Turtle turtleC = new Turtle(1104, 710, -6, spriteTurtle1, 110, 85, false);
	private final Turtle turtleD = new Turtle(57, 1010, -5, spriteTurtle1, 110, 85, false);
	private final Turtle turtleE = new Turtle(348, 1010, -5, spriteTurtle1, 110, 85, false);
	private final NPC turtleF = new Turtle(698, 1010, -5, spriteTurtle1, 110, 85, false);
	private final Turtle turtleG = new Turtle(1004, 1010, -5, spriteTurtle1, 110, 85, false);
	
	private final Prize lilyPadA = new Prize(30, 1210, 0, spriteLilyPad, 100, 100, false, 50);
	private final Prize lilyPadB = new Prize(240, 1210, 0, spriteLilyPad, 100, 100, false, 50);
	private final Prize lilyPadC = new Prize(450, 1210, 0, spriteLilyPad, 100, 100, false, 50);
	private final Prize lilyPadD = new Prize(660, 1210, 0, spriteLilyPad, 100, 100, false, 50);
	private final Prize lilyPadE = new Prize(870, 1210, 0, spriteLilyPad, 100, 100, false, 50);
	
	private final Prize fly = new Prize(465, 1215, 0, spriteFly, 100, 100, true, 200);
	
	
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
		
		NPCs.add(turtleA);
		turtles.add(turtleB);
		turtles.add(turtleD);
		turtles.add(turtleC);
		turtles.add(turtleE);
		NPCs.add(turtleF);
		turtles.add(turtleG);

		turtleB.addSprites(spriteTurtle2);
		turtleB.addSprites(spriteTurtle3);
		turtleC.addSprites(spriteTurtle2);
		turtleC.addSprites(spriteTurtle3);
		turtleD.addSprites(spriteTurtle2);
		turtleD.addSprites(spriteTurtle3);
		turtleE.addSprites(spriteTurtle2);
		turtleE.addSprites(spriteTurtle3);
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
	}

	public void moveFrog(int direzione) throws IOException
	{
		frog.setMoving(true);
		switch (direzione) {
			case KeyEvent.VK_LEFT -> {
				frog.setDirection(3);
				if (frog.p.getX() < 0)
					frog.p.setX(0);
				frog.rotate("LEFT");
				Sound.soundHop();
			}
			case KeyEvent.VK_RIGHT -> {
				frog.setDirection(1);
				if (frog.p.getX() > 920)
					frog.p.setX(920);
				frog.rotate("RIGHT");
				Sound.soundHop();
			}
			case KeyEvent.VK_DOWN -> {
				frog.setDirection(2);
				if (frog.p.getY() < 10)
					frog.p.setY(10);
				frog.rotate("DOWN");
				Sound.soundHop();
			}
			case KeyEvent.VK_UP -> {
				frog.setDirection(0);
				if (frog.p.getY() > 1210)
					frog.p.setY(1210);
				frog.rotate("UP");
				Sound.soundHop();
			}
			default -> {}
		}
		frog.updateHitbox();
	}
}
