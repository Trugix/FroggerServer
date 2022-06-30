import javax.swing.Timer;
import java.awt.EventQueue;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class FroggerCtrl implements KeyListener, MouseListener    //clase controller che gestisce il gioco
{
	//model e view
	private PnlFrog frogView;
	private FroggerModel model;
	private int nFrame=0; //variabile usata come contatore
	private final Random random = new Random();
	private int timerPrize = randTemp();    //randomizzo il tempo che ci impiega la mosca per spostarsi
	private boolean first = true;
	private NPC npcContact; //npc che sto toccando
	private boolean contact; //se la rana sta toccando qualcosa
	
	private Prize precedente;

	private Server server;
	
	private static boolean multiplayer = false; //se stiamo giocando in multiplayer o no

	private static final int  MAX_TIME = 500;
	private static final int  PRIZE_TIME_LOWER_BOUND = 100;
	private static final int  PRIZE_TIME_VARIATION = 150;
	private static final int  MAX_DISTANCE_FROM_PRIZE = 100;
	private static final int  SOUND_TIME_THRESHOLD = 110;
	private static final int  WATER_LOWER_BOUND = 700;
	private static final int  WATER_HIGHER_BOUND = 1200;
	private static final int  PRIZE_BLINK_THRESHOLD = 40;
	private static final int  SLIDING_FRAMES = 5;
	
	private static final int NPC_RESET = 1020;
	
	public PnlFrog getFrogView()
	{
		return frogView;
	}
	
	public FroggerModel getModel()
	{
		return model;
	}
	
	
	//timer che gestisce lo scorrere dei frame
	private Timer timer = new Timer(33, (e) ->
	{
		
		nextFrame();
		if (this.first)
		{
			initialization();
		}
	});
	
	public FroggerCtrl(FroggerModel model)
	{
		this.model = model;
		this.frogView = new PnlFrog(model);
		frogView.addKeyListener(this);
		frogView.addMouseListener(this);
		
		try
		{
			server = new Server(this);
		}
		catch (IOException e)
		{
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * inizializzo i premi la prima volta che il gioco viene avviato
	 */
	private void initialization()
	{
		this.first = false;
		for (int j = 0; j < model.getPrizes().size(); j++)
		{
			Prize prize1 = model.getPrizes().get(j);
			if (prize1.isBonus())   //cerco la mosca
			{
				prize1.stepNext(frogView.getDestinations());

				for (int i = 0; i < model.getPrizes().size(); i++)   //tolgo la lilypad e metto la rana
				{
					Prize prize2 = model.getPrizes().get(i);
					if (prize1.getHitbox().intersects(prize2.getHitbox()) && prize1.p.getX() != prize2.p.getX())
						precedente = prize2;
				}
				swapPrize(prize1); //randomizzo la sua posizione
			}
		}
	}
	
	/**
	 * metodo che aggiorna ogni frame
	 */
	private void nextFrame()  {
		model.setTempo(model.getTempo()-1);

		contact = false;

		npcContact = model.getNPCs().get(0);

		if(model.getFrog().isMoving())   //gestisco il cambio di sprite quando la rana si sta muovendo
		{
			nFrame++;
			model.getFrog().nextSlide();
			if (nFrame>=SLIDING_FRAMES)  //usa 5 frame per compiere un movimento
			{
				nFrame = 0;
				model.getFrog().setMoving(false);
			}
		}
		else
		{
			model.getFrog().rotate(model.getFrog().getDirection());
		}

		for (Turtle t : model.getTurtles())  //faccio immerge od emergere le tartarughe
		{
			t.immersion();
		}

		// uso i thread per processare la lista in quarti in parallelo al posto di scorrerla linearmente
		int size = model.getNPCs().size();
		ExecutorService service = Executors.newFixedThreadPool(4);
		
		service.submit(() -> moveNpc(0, size / 4));
		service.submit(() -> moveNpc(size / 4, size / 2));
		service.submit(() -> moveNpc(size / 2, size * 3 / 4));
		service.submit(() -> moveNpc(size * 3 / 4, size));
		
		service.shutdown();

		try
		{
			service.awaitTermination(3, TimeUnit.MILLISECONDS);
			checkCollision(model.getFrog()); //controllo le collisione
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}

		if (!npcContact.isDeathTouch() && this.contact)
		{
			model.getFrog().stepNext(npcContact.getDx());
		}
		
		if (model.getFrog().getVite() <= 0 || frogView.getDestinations().isEmpty()) //controllo che le vite siano finite o che le destinazioni siano vuote
		{
			frogView.setState(PnlFrog.STATE.GAME_OVER);
			if (multiplayer)
			{
				if (server.getClientView().getState() == PnlFrog.STATE.GAME_OVER)
				{
					frogView.setState(PnlFrog.STATE.GAME_OVER_MULTI);
					frogView.repaint();
					server.getClientView().repaint();
					server.send();
				}
			}
			timer.stop();   //fermo il timer
		}
		
		checkTime(model.getFrog()); //controllo che ci sia ancora tempo disponibile
		
		if (model.getFrog().p.getY() >= WATER_HIGHER_BOUND)
			checkPrize(model.getFrog()); //check per vedere se ho raggiunto la destinazione
		

		updatePrize();  //sposto la mosca
		
		updateSkull();  //controllo se mettere lo scheletro o no
		
		frogView.setEntities(model.getEntities());
		
		if (multiplayer)
			server.send(); //in caso sia in multiplayer invio quello che è successo in questo frame
		
		frogView.repaint();
	}
	
	/**
	 * cambio le posizioni degli npc
	 * @param start npc di partenza
	 * @param end npc di fine
	 */
	private void moveNpc(int start, int end)
	{
		for (int i = start; i < end; i++)
		{
			NPC npc = model.getNPCs().get(i);
			npc.stepNext(); //sposto ogni npc
			if (npc.getDx() > 0)
			{
				if (npc.p.getX() - npc.getDimx() > WATER_HIGHER_BOUND)
				{
					npc.p.setX(-npc.getDimx() - 20);
				}
			}
			else
			{
				if (npc.p.getX() + npc.getDimx() < -20)
				{
					npc.p.setX(NPC_RESET);
				}
			}
			
			if (model.getFrog().getHitbox().intersects(npc.getHitbox())) //controllo se un npc sta toccando la rana
			{
				this.contact = true;
				this.npcContact = npc;
			}
		}
	}
	
	/**
	 * metto lo scheletro in caso di morte
	 */
	private void updateSkull()
	{
		for (Skull s : model.getSkulls())
		{
			if (s.getTimeToLive() > 0)
			{
				model.getEntities().add(s);
			}
			else
			{
				model.getEntities().remove(s);
			}
			s.setTimeToLive(s.getTimeToLive() - 1);//aggiorno il numero di frame per cui deve rimanere
		}
	}
	
	/**
	 * controllo se la rana deve morire ed in caso la uccido
	 * @param frog rana giocante
	 */
	private void checkCollision(Frog frog)
	{
		if ((this.contact && this.npcContact.isDeathTouch()) || (!this.contact && frog.p.getY() >= WATER_LOWER_BOUND && frog.p.getY() <= WATER_HIGHER_BOUND))
		{
			updateMorte(frog);
		}
	}
	
	/**
	 * sposto la mosca e tolgo la lilypad che ha la stessa posizione
	 */
	private void updatePrize()
	{
		timerPrize--;   //aggiorno il tempo della mosca
		if(model.getPrizes().size()==0) //se ho finito gli slot di arrivo il gioco finisce
		{
			frogView.setState(PnlFrog.STATE.GAME_OVER);
		}
		if (timerPrize <= PRIZE_BLINK_THRESHOLD)   //if che fa lampeggiare la mosca
		{
			if (timerPrize % 6 >= 3)
			{
				for (Prize p : model.getPrizes())
				{
					if (p.isBonus())
					{
						p.setSprite("fly");
					}
				}
			}
			else
			{
				for (Prize p : model.getPrizes())
				{
					if (p.isBonus())
					{
						p.setSprite("void");
					}
				}
			}
			if (timerPrize <= 0) //quando il tempo è finito la mosca cambia posizione
			{
				timerPrize = randTemp();  //randomizzo un nuovo tempo
				
				for (int i = 0; i < model.getPrizes().size(); i++) //cerco la mosca e la sposto
				{
					if (model.getPrizes().get(i).isBonus())
					{
						model.getPrizes().get(i).stepNext(frogView.getDestinations());
						swapPrize(model.getPrizes().get(i));
					}
				}
			}
		}
	}
	
	/**
	 * scambio fisicamente mosca e lilypad
	 * @param bonus mosca
	 */
	private void swapPrize(Prize bonus)
	{
		model.getPrizes().add(precedente);
		model.getEntities().add(precedente); //riaggiungo la lilypad sulla quale stava la mosca
		for (int i = 0; i < model.getPrizes().size(); i++)
		{
			if (bonus.getHitbox().intersects(model.getPrizes().get(i).getHitbox()) && bonus.p.getX() != model.getPrizes().get(i).p.getX())
			{
				precedente = model.getPrizes().get(i);
				model.getPrizes().remove(precedente);    //tolgo la lilypad sulla quale la mosca è arrivata
				model.getEntities().remove(precedente);
			}
		}
	}
	
	/**
	 * gestisco la morte della rana
	 * @param frog rana giocante
	 */
	private void updateMorte(Frog frog)
	{
		model.getSkulls().add(new Skull(frog.p.getX(), frog.p.getY(), 0, "skull", 0, 0)); //aggiungo uno scheletro
		if (frog.p.getY() > WATER_LOWER_BOUND && frog.p.getY() < WATER_HIGHER_BOUND)    //riproduco il suono adeguato
		{
			Sound.soundStart("acqua");
		}
		else
		{
			Sound.soundStart("auto");
		}
			nFrame=0; //fermo il movimento
			frog.morte();
		
		resetTempo();
	}
	
	/**
	 * controllo se c`è ancora tempo disponibile
	 *
	 * @param frog rana giocante
	 */
	private void checkTime(Frog frog)
	{
		if (model.getTempo() == SOUND_TIME_THRESHOLD)
		{
			Sound.soundStart("tempo"); //riproduco il suono quando il tempo sta finendo
		}
		if (model.getTempo() <= 0)   //quando il tempo scade la rana muore
			updateMorte(frog);
		
	}
	
	/**
	 * metodo che cambia lo sprite quando la rana arriva a destinazione
	 * @param frog rana giocante
	 */
	private void checkPrize(Frog frog)
	{
		
		boolean save = false;
		
		for (Prize p : model.getPrizes())
		{
			if (frog.getHitbox().intersects(p.getHitbox()))   //controlla che la rana stia toccando un obbiettivo
			{
				
				updatePoint(frog, p.getPoint()); //aggiorno il punteggio
				model.setDestinazioni(model.getDestinazioni()+1);
				
				for (int i = 0; i < frogView.getDestinations().size(); i++)
				{
					if (distance(frog.p, frogView.getDestinations().get(i)) <= MAX_DISTANCE_FROM_PRIZE)
						frogView.getDestinations().remove(i); //tolgo dalle destinazioni gli obbiettivi già colpiti
				}
				
				if (p.isBonus())
				{
					resetBonus(p); //sposto la mosca
				}
				else
				{
					p.setSprite("lilyPadFrog"); //cambio lo sprite
					p.setHitbox(null);
					model.getPrizes().remove(p);
				}
				
				
				frog.resetPosition(); //riporto la rana all'inizio
				
				resetTempo();   //resetto il tempo
				
				save = true;
				
				break;
			}
		}
		
		if (!save)  // se ho mancato tutte le destinazioni la rana muore
			updateMorte(frog);
	}
	
	/**
	 * aggiorno la posizione della mosca quando viene presa
	 *
	 * @param bonus mosca
	 */
	private void resetBonus(Prize bonus)
	{

		bonus.stepNext(frogView.getDestinations());

		timerPrize = randTemp(); //nuovo tempo
		model.getEntities().add(precedente);
		precedente.setSprite("lilyPadFrog");
		precedente.setHitbox(null);
		
		for (int i = 0; i < model.getPrizes().size(); i++)
		{
			if (model.getPrizes().size() == 1) //caso in cui ci sia solo un posto
			{
				model.getPrizes().add(precedente);
				model.getEntities().add(precedente);
				model.getPrizes().remove(bonus);
				model.getEntities().remove(bonus);
			}
			else if (bonus.getHitbox().intersects(model.getPrizes().get(i).getHitbox()) && bonus.p.getX() != model.getPrizes().get(i).p.getX()) //caso generale, controllo quale lilypad la mosca sta toccando
			{
				precedente = model.getPrizes().get(i);
				model.getPrizes().remove(precedente);
				model.getEntities().remove(precedente);
			}
		}
	}
	
	/**
	 * genera un tempo randomico per la durata delle mosche
	 */
	
	private int randTemp()
	{
		return random.nextInt(PRIZE_TIME_VARIATION) + PRIZE_TIME_LOWER_BOUND;
	}
	
	/**
	 * calcola la distanza fra due punti
	 * @param p1 primo punto
	 * @param p2 secondo punto
	 * @return torno la distanza
	 */
	private double distance(Entity.Position p1, Entity.Position p2)
	{
		return Math.sqrt(Math.pow((p1.getX() - p2.getX()), 2) + Math.pow((p1.getY() - p2.getY()), 2));
	}
	
	
	/**
	 * Aggiorno il punteggio della rana in base a quello che ha fatto
	 *
	 * @param frog,  La rana da aggiornare
	 * @param point, I punti base dello sprite raggiunto
	 */
	private void updatePoint(Frog frog, int point)
	{
		model.setPoints(model.getPoints() + point + 100 * frog.getVite() + 5 * model.getTempo());
		Sound.soundStart("point");
	}
	
	
	/**
	 * Resetta il tempo ogni volta che viene chiamato
	 */
	private void resetTempo()
	{
		model.setTempo(MAX_TIME);
	}
	
	/**
	 * controlla se viene premuto un tasto sulla tastiera e inoltra il codice dell'evento a moveFrog, se la rana non si sta già muovendo
	 * @param e the event to be processed
	 */
	@Override
	public void keyPressed(KeyEvent e)
	{
			if (!model.getFrog().isMoving())
				model.moveFrog(e.getKeyCode());
	}
	
	
	/**
	 * metodo che controlla per il menù che tasto viene premuto e setta quindi la rispettiva modalità di gioco
	 * @param e l'evento da processare
	 */
	@Override
	public void mousePressed(MouseEvent e)
	{
		switch (frogView.getState())
		{
			case MENU:
				if (frogView.getPlayButton().contains(e.getX() / frogView.getScale(), e.getY() / (frogView.getScale()) - 1500)) //single player
				{
					frogView.setState(PnlFrog.STATE.GAME);
					frogView.repaint();
					timer.start();
				}
				if (frogView.getMultiButton().contains(e.getX() / frogView.getScale(), e.getY() / (frogView.getScale()) - 1500))//2 giocatori
				{
					frogView.setState(PnlFrog.STATE.LOADING);
					frogView.repaint();
					multiplayer = true; //siamo in multiplayer
					EventQueue.invokeLater(() ->
					{   //uso invokeLater perchè la connessione è più veloce del repaint della schermata di caricamento
						server.connessione();       //in questo modo server.connessione() aspetta che la schermata sia cambiata
						timer.start();
					});
				}
				if (frogView.getQuitButton().contains(e.getX() / frogView.getScale(), e.getY() / (frogView.getScale()) - 1500)) //buttone quit
					System.exit(0);
			break;
			case GAME_OVER_MULTI:
			case GAME_OVER:
				if (frogView.getQuitButtonMulti().contains(e.getX() / frogView.getScale(), e.getY() / (frogView.getScale()) - 1500))
				{
					server.setStop(true);
					System.exit(0);
				}
			break;
		}
	}
	
	
	/**
	 * metodo che colla ogni Id delle entità alla rispettiva sprite
	 * @param spriteID id dello sprite
	 * @return  sprite vero e proprio
	 */
	public static BufferedImage associaSprite (String spriteID)
	{
		return switch (spriteID)
			{
				case "frogUp" -> FroggerModel.spritesFrog[0];
				case "frogRight" -> FroggerModel.spritesFrog[1];
				case "frogDown" -> FroggerModel.spritesFrog[2];
				case "frogLeft" -> FroggerModel.spritesFrog[3];
				case "frogMovUp" -> FroggerModel.spritesFrogMov[0];
				case "frogMovRight" -> FroggerModel.spritesFrogMov[1];
				case "frogMovDown" -> FroggerModel.spritesFrogMov[2];
				case "frogMovLeft" -> FroggerModel.spritesFrogMov[3];
				case "truck" -> FroggerModel.spriteCarro;
				case "bulldozer" -> FroggerModel.spriteBulldozer;
				case "autoSport" -> FroggerModel.spriteAutoSport;
				case "police" -> FroggerModel.spritePolice;
				case "formula1" -> FroggerModel.spriteFormula1;
				case "formula2" -> FroggerModel.spriteFormula2;
				case "log3" -> FroggerModel.spriteLog3;
				case "log4" -> FroggerModel.spriteLog4;
				case "log6" -> FroggerModel.spriteLog6;
				case "turtle1" -> FroggerModel.spritesTurtle[0];
				case "turtle2" -> FroggerModel.spritesTurtle[1];
				case "turtle3" -> FroggerModel.spritesTurtle[2];
				case "fly" -> FroggerModel.spriteFly;
				case "lilyPad" -> FroggerModel.spriteLilyPad;
				case "lilyPadFrog" -> FroggerModel.spriteFrogLily;
				case "skull" -> FroggerModel.spriteSkull;
				case "void"-> FroggerModel.spriteVoid; //non faccio collassare il caso void nel default per ordine mentale
				default -> FroggerModel.spriteVoid;
			};
	}
	
	/**
	 * returna una nuova instanza di Transfer costruita del model attuale
	 * @param model model da inviare
	 */
	public Transfer modelToTransfer (FroggerModel model)
	{
		return new Transfer(model.getEntities(), model.getTempo(), model.getPoints(),model.getFrog().getVite(),model.getDestinazioni());
	}
	
	public void startGame ()
	{
		frogView.setState(PnlFrog.STATE.GAME);
	}
	
	//metodi di interfacce non usati
	@Override
	public void keyTyped(KeyEvent e) {}
	@Override
	public void keyReleased(KeyEvent e) {}
	@Override
	public void mouseClicked(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
}
