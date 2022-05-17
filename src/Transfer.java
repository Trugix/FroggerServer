import java.io.Serializable;
import java.util.ArrayList;

public class Transfer implements Serializable   //classe di servizio per inviare dati tra server e client
{
	ArrayList<Entity> entities; //tutte le entità sullo schermo
	int time;   //tempo rimasto
	int punteggio;  //punteggio attuale
	int vite;   //vite attuali
	
	public ArrayList<Entity> getEntities()
	{
		return entities;
	}
	
	public int getTime()
	{
		return time;
	}
	
	public int getPunteggio()
	{
		return punteggio;
	}
	
	public int getVite()
	{
		return vite;
	}
	
	public Transfer(ArrayList<Entity> entities, int time, int punteggio, int vite)
	{
		this.entities = entities;
		this.time = time;
		this.punteggio = punteggio;
		this.vite = vite;
	}
}

