import java.io.Serializable;
import java.util.ArrayList;

public class Transfer implements Serializable   //classe di servizio per inviare dati tra server e client
{
	private ArrayList<Entity> entities; //tutte le entità sullo schermo
	private int time;   //tempo rimasto
	private int punteggio;  //punteggio attuale
	private int vite;   //vite attuali
	
	private int destinazioni;
	
	public int getDestinazioni()
	{
		return destinazioni;
	}
	
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
	
	public Transfer(ArrayList<Entity> entities, int time, int punteggio, int vite, int destinazioni)
	{
		this.entities = entities;
		this.time = time;
		this.punteggio = punteggio;
		this.vite = vite;
		this.destinazioni = destinazioni;
	}
	
}

