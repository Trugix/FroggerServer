import javax.imageio.ImageIO;
import javax.swing.Timer;
import java.awt.*;
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
	private int timerPrize = randTemp();
	private boolean first = true;
	
	private Prize precedente;
	
	public FroggerCtrl(FroggerModel model/*,PnlFrog frogView*/) throws IOException
	{
		this.model = model;
		this.frogView = new PnlFrog(model.entities, this);
		Timer t = new Timer(33, (e) ->
		{
			try
			{
				nextFrame();
				if(first)
				{
					first=false;
					for (int j=0; j<model.prizes.size();j++)
					{
						if(model.prizes.get(j).isBonus())
						{
							model.prizes.get(j).stepNext(frogView.destinations);
							for (int i=0; i<model.prizes.size();i++)
							{
								if (model.prizes.get(j).hitbox.intersects(model.prizes.get(i).hitbox) && model.prizes.get(j).p.getX()!=model.prizes.get(i).p.getX())
									precedente=model.prizes.get(i);
								
							}
							swapPrize (model.prizes.get(j));
						}
					}
				}
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
				timerPrize = randTemp();
				
				for (int i=0; i<model.prizes.size();i++)
				{
					if (model.prizes.get(i).isBonus())
					{
						model.prizes.get(i).stepNext(frogView.destinations);
						swapPrize (model.prizes.get(i));
					}
				}
			}
		}
	}
	
	private void swapPrize (Prize bonus) throws IOException
	{
		model.prizes.add(precedente);
		model.entities.add(precedente);
		for (int i=0; i<model.prizes.size();i++)
		{
			if (bonus.hitbox.intersects(model.prizes.get(i).hitbox) && bonus.p.getX()!=model.prizes.get(i).p.getX())
			{
				precedente=model.prizes.get(i);
				model.prizes.remove(precedente);
				model.entities.remove(precedente);
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
	
	private void checkPrize(Frog frog) throws IOException
	{
		
		boolean save = false;
		
		for (Prize p : model.prizes)
		{
			if (frog.hitbox.intersects(p.hitbox))
			{
				
				updatePoint(frog, p.getPoint());
				
				for (int i=0; i<frogView.destinations.size();i++)
				{
					if(distance(frog.p,frogView.destinations.get(i))<=100)
						frogView.destinations.remove(i);
				}
				
				if(p.isBonus())
				{
					resetBonus(p);
					/*timerPrize=0;
					updatePrize();
					precedente.setSprite(ImageIO.read(new File("src/../sprites/tempD.png")));
					precedente.setHitbox(null);
					model.prizes.remove(precedente);*/
				}
				else
				{
					p.setSprite(ImageIO.read(new File("src/../sprites/tempD.png")));
					p.setHitbox(null);
					model.prizes.remove(p);
				}
				
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
	
	private void resetBonus(Prize bonus) throws IOException
	{
		bonus.stepNext(frogView.destinations);
		timerPrize = randTemp();
		model.entities.add(precedente);
		precedente.setSprite(ImageIO.read(new File("src/../sprites/tempD.png")));
		precedente.setHitbox(null);
		
		for (int i=0; i<model.prizes.size();i++)
		{
			if(model.prizes.size()==1)
			{
				model.prizes.add(precedente);
				model.entities.add(precedente);
				model.prizes.remove(bonus);
				model.entities.remove(bonus);
				//todo fermare il gioco perchè si ha vinto
			}
			else
				if (bonus.hitbox.intersects(model.prizes.get(i).hitbox) && bonus.p.getX()!=model.prizes.get(i).p.getX())
				{
					precedente=model.prizes.get(i);
					model.prizes.remove(precedente);
					model.entities.remove(precedente);
				}
		}
	}
	
	
	private int randTemp ()
	{
		return random.nextInt(150) + 100;
	}
	
	
	private double distance (Entity.Position p1, Entity.Position p2)
	{
		return Math.sqrt(Math.pow((p1.getX()-p2.getX()),2)+Math.pow((p1.getY()-p2.getY()),2));
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
		model.tempo = 500; //todo mettere costanti ovunque
	}
	
}
