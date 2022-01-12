import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class PnlFrog extends JPanel implements KeyListener{

    FroggerCtrl ctrl;
    Graphics2D g2;

    BufferedImage lilFrog = (BufferedImage) ImageIO.read(new File("src/frogSmall.png"));
   // public Rectangle2D rec = new Rectangle2D.Double(x, y, 30, 10);

    public void setEntities(ArrayList<Entity> entities) {
        this.entities = entities;
    }

    ArrayList<Entity> entities;

    public PnlFrog(ArrayList<Entity> entities, FroggerCtrl ctrl) throws IOException {
        this.entities = entities;
        this.ctrl=ctrl;
        this.addKeyListener(this);
        this.setFocusable(true);
    }

	   /* public void shoot(KeyEvent e){
	        if (e.getKeyCode() == KeyEvent.VK_SPACE){
	       // my -= 7;
	        repaint();
	        }
	    }*/


    // Colori go brrrr
    private static final Color COLORE_STRADA = new Color(40,40,40);
    private static final Color COLORE_ACQUA = new Color(25,25,180);
    private static final Color COLORE_CHECHKPOINT = new Color(119,121,63);
    private static final Color COLORE_ARRIVO = new Color(30,220,30);
    private static final Color COLORE_RIGHE = new Color(200,200,200);

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g2 = (Graphics2D) g;
        double s = Math.min(getWidth(), getHeight() / 1.5) / 1000.;
        g2.scale(s, -s);
        g2.translate(0, -1500);

        g2.setColor(Color.BLACK);    //Sfondo nero neutro, il primo layer
        g2.fillRect(0, 0, 1000, 1500);

        paintBackground(g2);  //Sfondo giocabile, secondo layer


        for (Entity e: entities)
        {
                g2.drawImage(e.sprite, e.p.getX(), e.p.getY(), null);
        }
        g2.drawImage(entities.get(0).sprite, entities.get(0).p.x, entities.get(0).p.y, null);

        printHud(g2);
    }

    /**
     * Metodo che si occupa della creazione dello sfondo giocabile
     * @param g2
     */
    private void paintBackground (Graphics2D g2)
    {
        g2.setColor(COLORE_CHECHKPOINT);   //Riga di partenza, colore marrone
        g2.fillRect(0, 0, 1000, 100);

        g2.setColor(COLORE_STRADA);   //Strade, colore grigio
        paintRiga(g2,100,5);
        
        g2.setColor(COLORE_RIGHE);   //Righe tra le corsie
        paintLinee(g2,100,5);
        
        g2.setColor(COLORE_CHECHKPOINT);   //Riga di riposo, colore marrone
        paintRiga(g2,600,1);

        g2.setColor(COLORE_ACQUA);   //Acqua, colore blu
        paintRiga(g2,700,5);

        g2.setColor(COLORE_ARRIVO);  //Contorno destinazione, colore verde
        g2.fillRect(0, 1200, 1000, 160);

        g2.setColor(COLORE_ACQUA); // Destinazione, colore blu
        paintArrivo(g2,120);
    }


    private void paintLinee (Graphics2D g2,int inizio,int nCaselle)
    {
        for(int c=1 ;c<nCaselle;c++)
            for (int i=20;i<1000;i+=100)
                g2.fillRect(i,inizio+100*c , 50, 10);
        
    }
    
    private void paintRiga(Graphics2D g2,int inizio,int nCaselle)
    {
        for(int c=0 ;c<nCaselle;c++)
            g2.fillRect(0, inizio+10*c, 100, 10);
    }

    private void paintArrivo(Graphics2D g2, int inizio)
    {
        int x=2;
        for (int c=0;c<5;c++)
        {
            if(c!=0)
                x+=21;
            g2.fillRect(x, inizio, 12, 12);
        }
    }

    /**
     * Metodo che crea l'Hud
     * @param g2
     */
    private void printHud (Graphics2D g2)
    {
        
        printVite(g2,ctrl.model.frog.vite);
        printTempo(g2);
    }
    
    private final static int MAX_VITE=6;
    private void printVite (Graphics2D g2,int vite)
    {
        for (int c=0;c<vite;c++)

            g2.drawImage(lilFrog, 1+c*5, 145, null);
    }
    
    private void printTempo (Graphics2D g2)
    {
        g2.setColor(COLORE_ARRIVO);
        int t=ctrl.model.tempo; //Fattore temporale iniziale
        g2.fillRect(83-t,145,t,4); //Barra della vita
        g2.scale(1,-1);
        g2.setFont(new Font("calibri",1,6));
        g2.drawString("TIME",85,-145); // Scritta TIME circa formatta //todo sistemare sto robo
        g2.scale(1,-1);
    }


    @Override
    public void keyTyped(KeyEvent e) {
        //    System.out.println("2");
        //        shoot(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        ctrl.model.moveFrog(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}