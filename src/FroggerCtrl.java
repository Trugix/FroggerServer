import javax.swing.Timer;
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
		}
		//checkCollision();
		frogView.setEntities(model.entities);
		frogView.repaint();
	}

//	private

}
