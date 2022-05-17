import java.util.Random;

public class Turtle extends NPC //classe npc particolare che si immerge
{

	private boolean goDown = true;  //rappresenta come si sta muovendo la tartaruga
	private final Random RANDOM = new Random();
	private final int MAX_TIMER = RANDOM.nextInt(200)+150;  //randomizzo il timer d'immersione
	private int timer=MAX_TIMER;

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
				this.spriteID = "turtle3";  //se stava scendendo allo scadere del timer la faccio immergere
				deathTouch = true;
				goDown=false;
			}
			else
			{
				this.spriteID = "turtle1"; //se non stava scendendo allo scadere del timer la faccio emergere
				deathTouch = false;
				goDown=true;
			}
			timer=MAX_TIMER;    //reset timer
		}
		if(timer == 50) //il giocatore ha 50 frame per capire che la tartaruga si sta abbassando
		{
			this.spriteID = "turtle2";
			deathTouch = false;
		}

	}
}
