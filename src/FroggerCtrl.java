import javax.swing.Timer;
import java.awt.geom.Rectangle2D;
import java.io.IOException;


public class FroggerCtrl {
	PnlFrog frogView;
	FroggerModel model;
	int nframe=0;
	public FroggerCtrl(FroggerModel model/*,PnlFrog frogView*/) throws IOException {
		this.model=model;
		this.frogView = new PnlFrog(model.entities,this);
		Timer t = new Timer(33, (e) -> {
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
		
		if (nframe==15)
		{
			model.tempo-=5;
			nframe=0;
		}else
		{
			nframe++;
		}
		for (NPC n:	 model.NPCs) {
			n.stepNext();
			if(n.dx>0){
				if(n.p.getX() - n.getDimx()>1020){
					n.p.setX(-n.getDimx()-20);
				}
			}else{
				if(n.p.getX() + n.getDimx()<-20){
					n.p.setX(1020);
				}
			}
			/*if(n.p.getY() == model.frog.p.getY())
				checkCollision(model.frog,n);*/
		}
		checkCollisionRiga(model.frog);
		model.frog.setStable(false);
		frogView.setEntities(model.entities);
		frogView.repaint();
	}
	
	private void checkCollisionRiga (Frog frog) throws IOException
	{
		int c=0;
		for (NPC n:	 model.NPCs)
		{
			if(n.p.getY() == model.frog.p.getY())
			{
				checkCollision(frog, n);
				c++;
			}
		}
		
		if(!frog.isStable() &&c!=0)
		{
			model.frog.morte();
			resetTempo();
		}
		c=0;
	}
   
    private void checkCollision (Frog frog, NPC entity) throws IOException
    {
	   
		boolean collisione= frog.hitbox.intersects(entity.hitbox);
		
		if(!entity.deathTouch) //acqua
	    {
		    if (!collisione)
			    frog.setStable(true);
	    }
	    else //macchine
		{
			frog.setStable(true);
			if (collisione)
			{
				model.frog.morte();
				resetTempo();
			}
		}
		
		
		
		/*
		int nobjs = model.entities.size();
		if(nobjs < 2)
			return;
		Entity [] ent = new Entity[nobjs];
		model.entities.toArray(ent);
		for(int i=0; i< nobjs-1; i++)
		{
			for(int j=i+1; j<nobjs; j++)
			{
				if(!ent[i].isAlive() || !ent[j].isAlive())
					continue;
				
				if(ent[i].checkCollision(ent[j]))
				{
					//ent[i].collisionDetected();
				}
			}
		}
		
		boolean collisione= frog.hitbox.intersects(entity.hitbox);
		if(!entity.deathTouch) //acqua
		{
			if (!collisione)
				model.frog.morte();
			resetTempo();
		}
		else //macchine
	        if(collisione)
	        {
		        model.frog.morte();
		        resetTempo();
	        }*/
    }
	
	/**
	 * Resetta il tempo ogni volta che la rana muore o riempie uno spazio
	 */
	private void resetTempo()
	{
		model.tempo =500;
	}

}
