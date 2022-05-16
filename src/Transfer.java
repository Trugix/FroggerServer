import java.io.Serializable;
import java.util.ArrayList;

public class Transfer implements Serializable
{
    ArrayList<Entity> entities;
    int time;
    int punteggio;

    int vite;
    
    public Transfer (ArrayList<Entity> entities, int time, int punteggio, int vite)
    {
        this.entities= entities;
        this.time = time;
        this.punteggio= punteggio;
        this.vite= vite;
    }
}

