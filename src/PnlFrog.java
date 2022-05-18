import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;


public class PnlFrog extends JPanel //pannello grafico
{
	//colori usati
	private static final Color COLORE_STRADA = new Color(40, 40, 40);
	private static final Color COLORE_ACQUA = new Color(25, 25, 180);
	private static final Color COLORE_CHECHKPOINT = new Color(119, 121, 63);
	private static final Color COLORE_ARRIVO = new Color(30, 220, 30);
	private static final Color COLORE_RIGHE = new Color(200, 200, 200);
	
	//numero di righe
	private static final int NUMERO_CASELLE = 5;
	private static final int NUMERO_CASELLE_RIPOSO = 1;
	
	private static final Font ftn = new Font("arial", Font.BOLD, 100);
	private static final Font ftn1 = new Font("arial", Font.BOLD, 60);
	private static final Font ftn2 = new Font("arial", Font.BOLD, 50);
	
	//pulsanti del menu
	private static final Rectangle playButton = new Rectangle(300, -1100, 400, 100);
	private static final Rectangle multiButton = new Rectangle(300, -900, 400, 100);
	private static final Rectangle quitButton = new Rectangle(300, -700, 400, 100);
	private static final Rectangle quitButtonMulti = new Rectangle(300, -300, 400, 100);
	
	private static final String FROGGER = "FROGGER";
	private static final String PLAYER1 = "1 PLAYER";
	private static final String PLAYER2 = "2 PLAYERS";
	private static final String QUIT = "QUIT GAME";
	private static final String LOADING = "WAITING FOR CONNECTION...";
	private static final String GAME_OVER = "GAME OVER";
	
	private boolean first = true;
	
	private ArrayList<Entity> entities;
	
	private FroggerModel modelToDraw;
	private ArrayList<Entity.Position> destinations = new ArrayList<>();   //destinazioni possibili per le lilypad e le mosche
	
	private double scale;
	
	public enum STATE   //stati possibili del pannello grafico
	{
		MENU,
		LOADING,
		GAME,
		GAME_OVER,
		GAME_OVER_MULTI
	}
	
	private STATE state = STATE.MENU;    //stato di partenza
	
	public void setState(STATE state)
	{
		this.state = state;
	}
	
	public STATE getState()
	{
		return state;
	}
	
	public double getScale()
	{
		return scale;
	}
	
	public ArrayList<Entity.Position> getDestinations()
	{
		return destinations;
	}
	
	public Rectangle getPlayButton()
	{
		return playButton;
	}
	
	public Rectangle getMultiButton()
	{
		return multiButton;
	}
	
	public Rectangle getQuitButton()
	{
		return quitButton;
	}
	
	public Rectangle getQuitButtonMulti()
	{
		return quitButtonMulti;
	}
	
	public void setEntities(ArrayList<Entity> entities)
	{
		this.entities = entities;
	}
	
	public PnlFrog(FroggerModel model)
	{
		this.modelToDraw = model;
		this.entities = modelToDraw.getEntities();
		this.setFocusable(true);
	}
	
	/**
	 * metodo che si occupa di scegliere cosa disegnare
	 *
	 * @param g the <code>Graphics</code> object to protect
	 */
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g21 = (Graphics2D) g;
		scale = Math.min(getWidth(), getHeight() / 1.5) / 1000.;    //calcolo la scala
		g21.scale(scale, -scale);
		g21.translate(0, -1500);     //sposto l'origine in basso a sinistra
		
		g21.setColor(Color.BLACK);    //Sfondo nero neutro, il primo layer
		g21.fillRect(0, 0, 1000, 1500);
		
		
		Graphics2D g2 = (Graphics2D) g;
		switch (state)
		{
			case MENU ->
			{  //disegno il menu
				
				g2.scale(1, -1);
				g2.setFont(ftn);
				g2.setColor(Color.magenta);
				g2.drawString(FROGGER, 250, -1200);
				g2.setColor(Color.yellow);
				g2.drawString(FROGGER, 255, -1205);
				g2.setColor(Color.GREEN);
				g2.drawString(FROGGER, 260, -1210);
				g2.setColor(Color.yellow);
				g2.setFont(ftn1);
				g2.drawString(PLAYER1, 360, -1030);
				g2.draw(playButton);
				g2.drawString(PLAYER2, 340, -830);
				g2.draw(multiButton);
				g2.drawString(QUIT, 330, -630);
				g2.draw(quitButton);
				g2.setFont(ftn2);
				g2.drawString("USA LE FRECCIE PER MUOVERTI", 110, -50);
				g2.scale(1, -1);
			}
			case LOADING ->
			{   //schermata di loading
				paintArrivo(g2,1200);
				g2.scale(1, -1);
				g2.setFont(ftn2);
				g2.setColor(Color.YELLOW);
				g2.drawString(LOADING, 175, -755);
				g2.scale(1, -1);
			}
			case GAME ->
			{  //disegno il gioco
				paintBackground(g2);  //Sfondo giocabile, secondo layer
				for (Entity e : entities)
					g2.drawImage(FroggerCtrl.associaSprite(e.getSpriteID()), e.p.getX(), e.p.getY(), null);
				
				g2.drawImage(FroggerCtrl.associaSprite(entities.get(0).getSpriteID()), entities.get(0).p.x, entities.get(0).p.y, null);
				printHud(g2);
				g2.setColor(Color.WHITE);
				g2.fillRect(1000, 0, 2000, 1500);
			}
			case GAME_OVER ->
			{     //schermata finale
				paintGameOver(g2);
				g2.setColor(Color.YELLOW);
				g2.draw(quitButtonMulti);
				g2.drawString(QUIT,330,-230);
				g2.scale(1, -1);
			}
			case GAME_OVER_MULTI ->
			{   //schermata finale multiplayer
				paintGameOver(g2);
				if (modelToDraw.getPoints() > modelToDraw.getPunteggioAvversario()) //scrivo il risultato
					g2.drawString("Hai vinto", 170, -400);
				else if (modelToDraw.getPoints() < modelToDraw.getPunteggioAvversario())
					g2.drawString("Hai perso", 170, -400);
				else
					g2.drawString("Parità", 170, -400);
				g2.setColor(Color.YELLOW);
				g2.draw(quitButtonMulti);
				g2.drawString(QUIT,330,-230);
				g2.scale(1, -1);
			}
		}
	}
	
	private void paintGameOver(Graphics2D g2){
		g2.scale(1, -1);
		g2.setFont(ftn);
		g2.setColor(Color.magenta);
		g2.drawString(GAME_OVER, 180, -755);
		g2.setColor(Color.yellow);
		g2.drawString(GAME_OVER, 185, -760);
		g2.setColor(Color.GREEN);
		g2.drawString(GAME_OVER, 190, -765);
		g2.setFont(ftn1);
		g2.drawString("Il tuo punteggio è: " + modelToDraw.getPoints(), 170, -500);
	}
	
	/**
	 * Metodo che si occupa della creazione dello sfondo giocabile
	 *
	 * @param g2 oggetto grafico
	 */
	private void paintBackground(Graphics2D g2)
	{
		g2.setColor(COLORE_CHECHKPOINT);   //Riga di partenza, colore marrone
		g2.fillRect(0, 0, 1000, 100);
		
		g2.setColor(COLORE_STRADA);   //Strade, colore grigio
		paintRiga(g2, 100, NUMERO_CASELLE);
		
		g2.setColor(COLORE_RIGHE);   //Righe tra le corsie
		paintLinee(g2, 100, NUMERO_CASELLE);
		
		g2.setColor(COLORE_CHECHKPOINT);   //Riga di riposo, colore marrone
		paintRiga(g2, 600, NUMERO_CASELLE_RIPOSO);
		
		g2.setColor(COLORE_ACQUA);   //Acqua, colore blu
		paintRiga(g2, 700, NUMERO_CASELLE);
		
		g2.setColor(COLORE_ARRIVO);  //Contorno destinazione, colore verde
		g2.fillRect(0, 1200, 1000, 160);
		
		g2.setColor(COLORE_ACQUA); // Destinazione, colore blu
		paintArrivo(g2, 1200);
		
		g2.setColor(COLORE_RIGHE); //Righe continue ai bordi della strada
		g2.fillRect(0, 100 - 5, 1000, 9);
		g2.fillRect(0, 600 - 5, 1000, 9);
	}
	
	/**
	 * disegna le linee dell'asfalto
	 *
	 * @param g2 oggetto grafico
	 * @param inizio punto d'inizio
	 * @param nCaselle numero di caselle da disegnare
	 */
	private void paintLinee(Graphics2D g2, int inizio, int nCaselle)
	{
		for (int c = 1; c < nCaselle; c++)
			for (int i = 20; i < 1000; i += 100)
				g2.fillRect(i, inizio + 100 * c - 5, 50, 9);
		
	}
	
	/**
	 *disegno le righe in cui si muovono le entità
	 *
	 * @param g2 oggetto grafico
	 * @param inizio punto d'inizio
	 * @param nCaselle numero di caselle da disegnare
	 */
	private void paintRiga(Graphics2D g2, int inizio, int nCaselle)
	{
		for (int c = 0; c < nCaselle; c++)
			g2.fillRect(0, inizio + 100 * c, 1000, 100);
	}
	
	/**
	 * disegno il punto di arrivo
	 *
	 * @param g2 oggetto grafico
	 * @param inizio punto d'inizio
	 */
	private void paintArrivo(Graphics2D g2, int inizio)
	{
		int x = 20;
		for (int c = 0; c < 5; c++)
		{
			if (c != 0)
				x += 210;
			g2.fillRect(x, inizio, 120, 120);
			if (first)
				destinations.add((new Entity()).new Position(x + 25, inizio + 15)); //riempio la lista delle possibili destinazioni
		}
		first = false;
	}
	
	/**
	 * Metodo che crea l'Hud
	 *
	 * @param g2 oggetto grafico
	 */
	private void printHud(Graphics2D g2)
	{
		
		printVite(g2, modelToDraw.getFrog().getVite());
		printTempo(g2);
		printPoint(g2, modelToDraw.getPoints());
	}
	
	/**
	 * stampo le vite
	 * @param g2 oggetto grafico
	 * @param vite numero di vite correnti
	 */
	private void printVite(Graphics2D g2, int vite)
	{
		for (int c = 0; c < vite; c++)
			g2.drawImage(FroggerModel.spriteLilFrog, 1 + c * 54, 1447, null);
	}
	
	/**
	 * stampo il tempo rimasto
	 *
	 * @param g2 oggetto grafico
	 */
	private void printTempo(Graphics2D g2)
	{
		g2.setColor(COLORE_ARRIVO);
		int t = modelToDraw.getTempo(); //Fattore temporale iniziale
		g2.fillRect(830 - t, 1450, t, 40); //Barra della vita
		g2.scale(1, -1);
		g2.setFont(new Font("calibri", Font.BOLD, 60));
		g2.drawString("TIME", 850, -1450); // Scritta TIME circa formatta
		g2.scale(1, -1);
	}
	
	/**
	 * stampo il punteggio attuale
	 *
	 * @param g2 oggetto grafico
	 * @param point punti attuali
	 */
	private void printPoint(Graphics2D g2, int point)
	{
		g2.scale(1, -1);
		g2.setFont(new Font("calibri", Font.BOLD, 60));
		g2.drawString(String.format("POINTS: %05d", point), 1, -1380);
		g2.scale(1, -1);
	}
}