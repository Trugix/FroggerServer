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
			nextFrame();
		});
		
		t.start();
	}
	
	private void nextFrame() {
		
		if (nframe==33)
		{
			model.tempo--;
			nframe=0;
		}else
		{
			nframe++;
		}
		for (NPC n:	 model.NPCs) {
			n.stepNext();
			if(n.dx>0){
				if(n.p.getX() - n.getDimx()>102){
					n.p.setX(-n.getDimx()-2);
				}
			}else{
				if(n.p.getX() + n.getDimx()<-20){
					n.p.setX(1020);
				}
			}
			if(n.p.getY() == model.frog.p.getY())
				checkCollision(model.frog,n);
		}
		frogView.setEntities(model.entities);
		frogView.repaint();
	}

    private void checkCollision (Entity frog, NPC entity)
    {
	
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
	        }
    }
	
	/**
	 * Resetta il tempo ogni volta che la rana muore o riempie uno spazio
	 */
	private void resetTempo()
	{
		model.tempo =50;
	}

}
