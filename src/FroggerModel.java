import javax.imageio.ImageIO;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class FroggerModel implements Serializable   //modello del gioco
{
	
	//posizione iniziale della rana
	private static final int STARTING_FROGX = 460;
	private static final int STARTING_FROGY = 10;

	
	private static final String PATH_SPRITE = "src/../sprites/"; //cartella degli sprite
	
	//liste dei vari oggetti sullo schermo
	private ArrayList<Entity> entities = new ArrayList<>();
	private ArrayList<NPC> NPCs = new ArrayList<>();
	private ArrayList<Prize> prizes = new ArrayList<>();
	private ArrayList<Turtle> turtles = new ArrayList<>();
	private ArrayList<Skull> skulls = new ArrayList<>();
	
	private int tempo = 500; //tempo di partenza
	
	private int destinazioni =0;
	
	private int punteggioAvversario;    //punteggio dell'avversario
	
	public int getPoints()
	{
		return points;
	}
	
	public void setPoints(int points)
	{
		this.points = points;
	}
	
	public int points = 0;  //proprio punteggio
	//elenco degli sprite
	public static final BufferedImage[] spritesFrog;
	public static final BufferedImage[] spritesFrogMov;
	public static final BufferedImage spriteCarro;
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
	public static final BufferedImage spriteLilFrog;
	public static final BufferedImage spriteVoid;
	
	private Frog frog = new Frog(STARTING_FROGX, STARTING_FROGY, 70, "froUp", 75, 75);
	
	static
	{
		try //leggo tutte le sprite
		{
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
			spriteLilFrog = ImageIO.read(new File(PATH_SPRITE + "frogSmall.png"));
			spriteVoid = ImageIO.read(new File(PATH_SPRITE + "void.png"));
		}
		catch (IOException e)
		{
			throw new RuntimeException(e);
		}
	}
	
	public int getDestinazioni()
	{
		return destinazioni;
	}
	
	public void setDestinazioni(int destinazioni)
	{
		this.destinazioni = destinazioni;
	}
	
	public ArrayList<Entity> getEntities()
	{
		return entities;
	}
	
	
	public ArrayList<NPC> getNPCs()
	{
		return NPCs;
	}
	
	
	public ArrayList<Prize> getPrizes()
	{
		return prizes;
	}
	
	public ArrayList<Turtle> getTurtles()
	{
		return turtles;
	}
	
	
	public ArrayList<Skull> getSkulls()
	{
		return skulls;
	}
	
	public int getTempo()
	{
		return tempo;
	}
	
	public void setTempo(int tempo)
	{
		this.tempo = tempo;
	}
	
	public Frog getFrog()
	{
		return frog;
	}
	
	public FroggerModel(int i) //costruttore vuoto
	{
	}
	
	/**
	 *      creo tutti gli oggetti sullo schermo e li aggiungo alle rispettive liste
	 */
	public FroggerModel()
	{
		entities.add(frog);
		
		NPC carroA = new NPC(500, 510, -7, "truck", 200, 85, true);
		NPCs.add(carroA);
		NPC carroB = new NPC(981, 510, -7, "truck", 200, 85, true);
		NPCs.add(carroB);
		NPC autoSportA = new NPC(235, 410, 10, "autoSport", 100, 85, true);
		NPCs.add(autoSportA);
		NPC autoSportB = new NPC(884, 410, 10, "autoSport", 100, 85, true);
		NPCs.add(autoSportB);
		NPC bulldozerA = new NPC(15, 310, -5, "bulldozer", 130, 85, true);
		NPCs.add(bulldozerA);
		NPC bulldozerB = new NPC(354, 310, -5, "bulldozer", 130, 85, true);
		NPCs.add(bulldozerB);
		NPC bulldozerC = new NPC(846, 310, -5, "bulldozer", 130, 85, true);
		NPCs.add(bulldozerC);
		NPC carFormula1 = new NPC(123, 210, 12, "formula1", 100, 85, true);
		NPCs.add(carFormula1);
		NPC carFormula2 = new NPC(724, 210, 12, "formula2", 100, 85, true);
		NPCs.add(carFormula2);
		NPC carPoliceA = new NPC(684, 110, -10, "police", 110, 85, true);
		NPCs.add(carPoliceA);
		NPC carPoliceB = new NPC(215, 110, -10, "police", 110, 85, true);
		NPCs.add(carPoliceB);
		
		NPC log6A = new NPC(1000, 1110, 11, "log6", 595, 85, false);
		NPCs.add(log6A);
		NPC log6B = new NPC(100, 1110, 11, "log6", 595, 85, false);
		NPCs.add(log6B);
		NPC log4A = new NPC(987, 810, 9, "log4", 395, 85, false);
		NPCs.add(log4A);
		NPC log4B = new NPC(-144, 810, 9, "log4", 395, 85, false);
		NPCs.add(log4B);
		NPC log4C = new NPC(394, 810, 9, "log4", 395, 85, false);
		NPCs.add(log4C);
		NPC log3A = new NPC(-217, 910, 5, "log3", 295, 85, false);
		NPCs.add(log3A);
		NPC log3B = new NPC(397, 910, 5, "log3", 295, 85, false);
		NPCs.add(log3B);
		NPC log3C = new NPC(943, 910, 5, "log3", 295, 85, false);
		NPCs.add(log3C);
		
		NPC turtleA = new Turtle(698, 710, -6, "turtle1", 110, 85, false);
		NPCs.add(turtleA);
		Turtle turtleB = new Turtle(254, 710, -6, "turtle1", 110, 85, false);
		turtles.add(turtleB);
		Turtle turtleD = new Turtle(57, 1010, -5, "turtle1", 110, 85, false);
		turtles.add(turtleD);
		Turtle turtleC = new Turtle(1104, 710, -6, "turtle1", 110, 85, false);
		turtles.add(turtleC);
		Turtle turtleE = new Turtle(348, 1010, -5, "turtle1", 110, 85, false);
		turtles.add(turtleE);
		NPC turtleF = new Turtle(698, 1010, -5, "turtle1", 110, 85, false);
		NPCs.add(turtleF);
		Turtle turtleG = new Turtle(1004, 1010, -5, "turtle1", 110, 85, false);
		turtles.add(turtleG);
		
		Prize lilyPadA = new Prize(30, 1210, 0, "lilyPad", 100, 100, false, 50);
		prizes.add(lilyPadA);
		Prize lilyPadB = new Prize(240, 1210, 0, "lilyPad", 100, 100, false, 50);
		prizes.add(lilyPadB);
		Prize lilyPadC = new Prize(450, 1210, 0, "lilyPad", 100, 100, false, 50);
		prizes.add(lilyPadC);
		Prize lilyPadD = new Prize(660, 1210, 0, "lilyPad", 100, 100, false, 50);
		prizes.add(lilyPadD);
		Prize lilyPadE = new Prize(870, 1210, 0, "lilyPad", 100, 100, false, 50);
		prizes.add(lilyPadE);
		Prize fly = new Prize(465, 1215, 0, "fly", 100, 100, true, 200);
		prizes.add(fly);
		
		
		NPCs.addAll(turtles);
		entities.addAll(NPCs);
		entities.addAll(prizes);
	}
	
	public int getPunteggioAvversario()
	{
		return punteggioAvversario;
	}
	
	public void setPunteggioAvversario(int punteggioAvversario)
	{
		this.punteggioAvversario = punteggioAvversario;
	}
	
	/**
	 * ruoto la rana per i movimenti
	 * @param direzione direzione in cui mi sto muovendo
	 */
	public void moveFrog(int direzione)
	{
		switch (direzione)
		{
			case KeyEvent.VK_LEFT ->
			{
				frog.setMoving(true);
				frog.rotate(3);
				Sound.soundStart("hop");
			}
			case KeyEvent.VK_RIGHT ->
			{
				frog.setMoving(true);
				frog.rotate(1);
				Sound.soundStart("hop");
			}
			case KeyEvent.VK_DOWN ->
			{
				frog.setMoving(true);
				frog.rotate(2);
				Sound.soundStart("hop");
			}
			case KeyEvent.VK_UP ->
			{
				frog.setMoving(true);
				frog.rotate(0);
				Sound.soundStart("hop");
			}
			default ->
			{
			}
		}
		frog.updateHitbox();
	}
	
	/**
	 * salvo i dati ricevuti online
	 * @param transfer dati ricevuti
	 */
	public void transferToModel(Transfer transfer)
	{
		this.points = transfer.getPunteggio();
		this.entities = transfer.getEntities();
		this.tempo = transfer.getTime();
		this.frog.setVite(transfer.getVite());
		this.destinazioni = transfer.getDestinazioni();
	}
}
