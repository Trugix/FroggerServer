import javax.imageio.ImageIO;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class FroggerModel implements Serializable
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
	
	public int getPoints() {
		return points;
	}
	
	public void setPoints(int points) {
		this.points = points;
	}
	
	public int points = 0;
	public static BufferedImage[] spritesFrog;
	public static BufferedImage[] spritesFrogMov;
	public static BufferedImage spriteCarro;
	public static final BufferedImage spriteAutoSport;
	public static final BufferedImage spriteBulldozer;
	public static final BufferedImage spriteFormula1;
	public static final BufferedImage spriteFormula2;
	public static final BufferedImage spritePolice;
	public static final BufferedImage spriteLog6;
	public static final BufferedImage spriteLog3;
	public static final BufferedImage spriteLog4;
	public static final BufferedImage[] spritesTurtle;
	public static final BufferedImage spriteFly;
	public static final BufferedImage spriteLilyPad;
	public static final BufferedImage spriteFrogLily;
	public static final BufferedImage spriteSkull;
	public static final BufferedImage spriteVoid;
	
	
	static {
		try {
			spritesFrog = new BufferedImage[]{ImageIO.read(new File(PATH_SPRITE + "frogUp.png")), ImageIO.read(new File(PATH_SPRITE + "frogRight.png")),
					ImageIO.read(new File(PATH_SPRITE + "frogDown.png")), ImageIO.read(new File(PATH_SPRITE + "frogLeft.png"))};
			spritesFrogMov = new BufferedImage[]{ImageIO.read(new File(PATH_SPRITE + "frogMovementUp.png")), ImageIO.read(new File(PATH_SPRITE + "frogMovementRight.png")),
					ImageIO.read(new File(PATH_SPRITE + "frogMovementDown.png")), ImageIO.read(new File(PATH_SPRITE + "frogMovementLeft.png"))};
			spriteCarro = ImageIO.read(new File(PATH_SPRITE + "carro1.png"));
			spriteAutoSport = ImageIO.read(new File(PATH_SPRITE + "autoSport.png"));
			spriteBulldozer = ImageIO.read(new File(PATH_SPRITE + "bulldozer.png"));
			spriteFormula1 = ImageIO.read(new File(PATH_SPRITE + "carFormula1.png"));
			spriteFormula2 = ImageIO.read(new File(PATH_SPRITE + "carFormula2.png"));
			spritePolice = ImageIO.read(new File(PATH_SPRITE + "carPolice.png"));
			spriteLog6 = ImageIO.read(new File(PATH_SPRITE + "log6.png"));
			spriteLog3 = ImageIO.read(new File(PATH_SPRITE + "log3.png"));
			spriteLog4 = ImageIO.read(new File(PATH_SPRITE + "log4.png"));
			spritesTurtle = new BufferedImage[]{ImageIO.read(new File(PATH_SPRITE + "turtle1.png")), ImageIO.read(new File(PATH_SPRITE + "turtle2.png")), ImageIO.read(new File(PATH_SPRITE + "turtle3.png"))};
			spriteFly = ImageIO.read(new File(PATH_SPRITE + "fly.png"));
			spriteLilyPad = ImageIO.read(new File(PATH_SPRITE + "lily.png"));
			spriteFrogLily = ImageIO.read(new File(PATH_SPRITE + "frogAtRest.png"));
			spriteSkull = ImageIO.read(new File(PATH_SPRITE + "skull.png"));
			spriteVoid = ImageIO.read(new File(PATH_SPRITE + "void.png"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Frog frog = new Frog(STARTING_FROGX, STARTING_FROGY, 70, "froUp", 75, 75);
	
	private final NPC carroA = new NPC(500, 510, -7, "truck", 200, 85, true);
	private final NPC carroB = new NPC(981, 510, -7, "truck", 200, 85, true);
	private final NPC autoSportA = new NPC(235, 410, 10, "autoSport", 100, 85, true);
	private final NPC autoSportB = new NPC(884, 410, 10, "autoSport", 100, 85, true);
	private final NPC bulldozerA = new NPC(15, 310, -5, "bulldozer", 130, 85, true);
	private final NPC bulldozerB = new NPC(354, 310, -5, "bulldozer", 130, 85, true);
	private final NPC bulldozerC = new NPC(846, 310, -5, "bulldozer", 130, 85, true);
	private final NPC carFormula1 = new NPC(123, 210, 12, "formula1", 100, 85, true);
	private final NPC carFormula2 = new NPC(724, 210, 12, "formula2", 100, 85, true);
	private final NPC carPoliceA = new NPC(684, 110, -10, "police", 110, 85, true);
	private final NPC carPoliceB = new NPC(215, 110, -10, "police", 110, 85, true);
	
	private final NPC log6A = new NPC(1000, 1110, 11, "log6", 595, 85, false);
	private final NPC log6B = new NPC(100, 1110, 11, "log6", 595, 85, false);
	private final NPC log4A = new NPC(987, 810, 9, "log4", 395, 85, false);
	private final NPC log4B = new NPC(-144, 810, 9, "log4", 395, 85, false);
	private final NPC log4C = new NPC(394, 810, 9, "log4", 395, 85, false);
	private final NPC log3A = new NPC(-217, 910, 5, "log3", 295, 85, false);
	private final NPC log3B = new NPC(397, 910, 5, "log3", 295, 85, false);
	private final NPC log3C = new NPC(943, 910, 5, "log3", 295, 85, false);
	
	private final NPC turtleA = new Turtle(698, 710, -6, "turtle1", 110, 85, false);
	private final Turtle turtleB = new Turtle(254, 710, -6, "turtle1", 110, 85, false);
	private final Turtle turtleC = new Turtle(1104, 710, -6, "turtle1", 110, 85, false);
	private final Turtle turtleD = new Turtle(57, 1010, -5, "turtle1", 110, 85, false);
	private final Turtle turtleE = new Turtle(348, 1010, -5, "turtle1", 110, 85, false);
	private final NPC turtleF = new Turtle(698, 1010, -5, "turtle1", 110, 85, false);
	private final Turtle turtleG = new Turtle(1004, 1010, -5, "turtle1", 110, 85, false);
	
	private final Prize lilyPadA = new Prize(30, 1210, 0, "lilyPad", 100, 100, false, 50);
	private final Prize lilyPadB = new Prize(240, 1210, 0, "lilyPad", 100, 100, false, 50);
	private final Prize lilyPadC = new Prize(450, 1210, 0, "lilyPad", 100, 100, false, 50);
	private final Prize lilyPadD = new Prize(660, 1210, 0, "lilyPad", 100, 100, false, 50);
	private final Prize lilyPadE = new Prize(870, 1210, 0, "lilyPad", 100, 100, false, 50);
	
	private final Prize fly = new Prize(465, 1215, 0, "fly", 100, 100, true, 200);
	
	
	public FroggerModel(int i) throws IOException
	{
	
	}
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
				frog.rotate(3);
				Sound.soundHop();
			}
			case KeyEvent.VK_RIGHT -> {
				frog.setDirection(1);
				if (frog.p.getX() > 920)
					frog.p.setX(920);
				frog.rotate(1);
				Sound.soundHop();
			}
			case KeyEvent.VK_DOWN -> {
				frog.setDirection(2);
				if (frog.p.getY() < 10)
					frog.p.setY(10);
				frog.rotate(2);
				Sound.soundHop();
			}
			case KeyEvent.VK_UP -> {
				frog.setDirection(0);
				if (frog.p.getY() > 1210)
					frog.p.setY(1210);
				frog.rotate(0);
				Sound.soundHop();
			}
			default -> {}
		}
		frog.updateHitbox();
	}
	
	
	public void transferToModel(Transfer transfer)
	{
		this.points = transfer.punteggio;
		this.entities = transfer.entities;
		this.tempo = transfer.time;
		this.frog.setVite(transfer.vite);
	}
}
