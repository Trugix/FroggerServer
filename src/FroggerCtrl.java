import javax.imageio.ImageIO;
import javax.swing.Timer;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;


public class FroggerCtrl
{
	
	PnlFrog frogView;
	FroggerModel model;
	private int nframe = 0;
	Random random = new Random();
	private int timerPrize = random.nextInt(150) + 100;
	
	public FroggerCtrl(FroggerModel model/*,PnlFrog frogView*/) throws IOException
	{
		this.model = model;
		this.frogView = new PnlFrog(model.entities, this);
		Timer t = new Timer(33, (e) ->
		{
			try
			{
				nextFrame();
			}
			catch (IOException ex)
			{
				ex.printStackTrace();
			}
		});
		
		t.start();
	}
	
	private void nextFrame() throws IOException
	{
		boolean contact = false;
		NPC npc=model.NPCs.get(0);
		
		if (nframe == 15)
		{
			model.tempo -= 5;
			nframe = 0;
		}
		else
			nframe++;
		
		
		for (NPC n : model.NPCs)
		{
			n.stepNext();
			if (n.dx > 0)
			{
				if (n.p.getX() - n.getDimx() > 1020)
				{
					n.p.setX(-n.getDimx() - 20);
				}
			}
			else
			{
				if (n.p.getX() + n.getDimx() < -20)
				{
					n.p.setX(1020);
				}
			}
			
			if (model.frog.hitbox.intersects(n.hitbox))
			{
				contact = true;
				npc=n;
			}
		}
		
		checkCollision(model.frog,contact,npc);
		if (!npc.deathTouch && contact)
		{
			model.frog.stepNext(npc.dx);
		}
		
		if (model.frog.vite <= 0)
		{
			//todo fare finire il gioco
		}
		
		checkTime(model.frog);
		if (model.frog.p.getY() >= 1200)
			checkPrize(model.frog);
		//checkCollision(model.frog);
		updatePrize();
		
		frogView.setEntities(model.entities);
		frogView.repaint();
		
	}
	
	private void checkCollision(Frog frog,boolean contact, NPC npc) throws IOException
	{
		if ((contact && npc.deathTouch) || (!contact && frog.p.getY() >= 701 && frog.p.getY() <= 1200))
		{
			frog.morte();
			resetTempo();
		}
		
		/*if ((contact && frog.p.getY() >= 0 && frog.p.getY() <= 600) || (!contact && frog.p.getY() >= 701 && frog.p.getY() <= 1200))
		{
			frog.morte();
			resetTempo();
		}*/
	}
	
	
	private void updatePrize() throws IOException
	{
		timerPrize--;
		if (timerPrize <= 40) //todo definire quanti bonus ci sono
		{
			if (timerPrize % 6 >= 3)
			{
				for (Prize p : model.prizes)
				{
					if (p.isBonus())
					{
						p.setSprite(ImageIO.read(new File("src/../sprites/fly.png")));
					}
				}
			}
			else
			{
				for (Prize p : model.prizes)
				{
					if (p.isBonus())
					{
						p.setSprite(ImageIO.read(new File("src/../sprites/void.png")));
					}
				}
			}
			if (timerPrize <= 0)
			{
				timerPrize = random.nextInt(150) + 100;
				
				for (Prize p : model.prizes)
				{
					if (p.isBonus())
					{
						p.stepNext(frogView.destinations);
						p.setSprite(ImageIO.read(new File("src/../sprites/fly.png")));
					}
				}
			}
		}
	}
	
	private void checkTime(Frog frog) throws IOException
	{
		if (model.tempo <= 0)
		{
			frog.morte();
			resetTempo();
		}
	}
	
	
	/*private void checkCollision(Frog frog) throws IOException
	{
		// Per ora questo è l'unico metodo che funziona anche se non è il più efficiente
		boolean collisione = false;
		
		for (int i = 0; i < model.NPCs.size(); i++)
		{
			if (frog.hitbox.intersects(model.NPCs.get(i).hitbox))
			{
				collisione = true;
				if (!model.NPCs.get(i).deathTouch)
				{
					frog.stepNext(model.NPCs.get(i).dx);
				}
				break;
			}
		}
		
		if ((collisione && frog.p.getY() >= 0 && frog.p.getY() <= 600) || (!collisione && frog.p.getY() >= 701 && frog.p.getY() <= 1200))
		{
			frog.morte();
			resetTempo();
		}
		
		if (frog.p.getY() >= 1200)
			checkPrize(frog);
		
	}*/
	
	private void checkPrize(Frog frog) throws IOException
	{
		
		boolean save = false;
		
		for (Prize p : model.prizes)
		{
			if (frog.hitbox.intersects(p.hitbox))
			{
				updatePoint(frog, p.getPoint());
				frog.resetPosition();
				resetTempo();
				save = true;
				break;
			}
		}
		
		if (!save)
		{
			frog.morte();
			resetTempo();
		}
	}
	
	
	/**
	 * Aggiorno il punteggio della rana in base a quello che ha fatto
	 *
	 * @param frog,  La rana da aggiornare
	 * @param point, I punti base dello sprite raggiunto
	 */
	private void updatePoint(Frog frog, int point)
	{
		frog.setPoint(frog.getPoint() + point + 100 * frog.vite + 5 * model.tempo);
	}
	
	
	/**
	 * Resetta il tempo ogni volta che viene chiamato
	 */
	private void resetTempo()
	{
		model.tempo = 500;
	}
	
}
