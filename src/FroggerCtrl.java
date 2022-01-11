import javax.swing.Timer;
import java.awt.geom.Rectangle2D;
import java.io.IOException;


public class FroggerCtrl {
	PnlFrog frogView;
	FroggerModel model;

	public FroggerCtrl(FroggerModel model/*,PnlFrog frogView*/) throws IOException {
		this.model=model;
		this.frogView = new PnlFrog(model.entities,this);
		Timer t = new Timer(30, (e) -> {
			nextFrame();
		});
		
		t.start();
	}
	
	private void nextFrame() {
		for (NPC n:	 model.NPCs) {
			n.stepNext();
			if(n.dx>0){
				if(n.p.getX() - n.getDimx()>102){
					n.p.setX(-n.getDimx()-2);
				}
			}else{
				if(n.p.getX() + n.getDimx()<-2){
					n.p.setX(102);
				}
			}
			if(n.p.getY() == model.frog.p.getY())
				checkCollision(model.frog,n);
		}
		frogView.setEntities(model.entities);
		frogView.repaint();
	}

    private void checkCollision (Entity frog, NPC entity )
    {
		boolean collisione= frog.hitbox.intersects(entity.hitbox);
		if(!entity.deathTouch) //acqua
		{
			if (!collisione)
				model.frog.morte();
		}
		else //macchine
	        if(collisione)
				model.frog.morte();
    }

}
