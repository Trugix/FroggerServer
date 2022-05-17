import java.util.Random;

public class Turtle extends NPC //classe npc particolare che si immerge
{
	
	private boolean goDown = true;  //rappresenta come si sta muovendo la tartaruga
	private final Random RANDOM = new Random();
	private final int MAX_TIMER = RANDOM.nextInt(200)+150;  //randomizzo il timer d'immersione
	private int timer=MAX_TIMER;
	private static final int GRACE_TIME=50;

	public Turtle(int x, int y, int dx, String spriteID, int dimx, int dimy, boolean deathTouch)
	{
		super(x, y, dx, spriteID, dimx, dimy, deathTouch);
	}


	/**
	 * faccio immergere od emergere le tartarughe
	 */
	public void immersion()
	{
		timer--;    //aggiorno il timer
		if(timer==0)
		{
			if(goDown)
			{
				this.setSpriteID("turtle3");   //se stava scendendo allo scadere del timer la faccio immergere
				this.setDeathTouch(true);
				goDown=false;
			}
			else
			{
				this.setSpriteID("turtle1");  //se non stava scendendo allo scadere del timer la faccio emergere
				this.setDeathTouch(false);
				goDown=true;
			}
			timer=MAX_TIMER;    //reset timer
		}
		if(timer == GRACE_TIME) //il giocatore ha 50 frame per capire che la tartaruga si sta abbassando
		{
			this.setSpriteID("turtle2");
			this.setDeathTouch(false);
		}

	}
}
