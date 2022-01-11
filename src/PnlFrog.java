import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class PnlFrog extends JPanel implements KeyListener{
    int x = 46;
    int y = 142;

    int dx = 8;
    int dy = 8;

    FroggerCtrl ctrl;
    //int my = 300;
    Graphics2D g2;
    // Image frog = Toolkit.getDefaultToolkit().getImage("src/frog.png");
    Image carro1 = Toolkit.getDefaultToolkit().getImage("src/carro1.png");

    BufferedImage lilFrog = (BufferedImage) ImageIO.read(new File("src/frogSmall.png"));
    BufferedImage sprite = (BufferedImage) ImageIO.read(new File("src/frog.png"));
    public Rectangle2D rec = new Rectangle2D.Double(x, y, 30, 10);
    int xc1 = 100;
    int yc1 = 134;

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
    private static final Color COLORE_ACQUA = new Color(30,30,180);
    private static final Color COLORE_CHECHKPOINT = new Color(85,25,25);
    private static final Color COLORE_ARRIVO = new Color(30,220,30);

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g2 = (Graphics2D) g;
        double s = Math.min(getWidth(), getHeight() / 1.5) / 100.;
        g2.scale(s, -s);
        g2.translate(0, -150);

        g2.setColor(Color.BLACK);    //Sfondo nero neutro, il primo layer
        g2.fillRect(0, 0, 100, 150);

        paintBackground(g2);  //Sfodno giocabile, secondo layer


        for (Entity e: entities) {
            g2.drawImage(e.sprite, e.p.getX(), e.p.y, null);
        }
      /*  g2.drawImage(entities.get(0).sprite, entities.get(0).p.x, entities.get(0).p.y, null);
        g2.drawImage(carro1, xc1, yc1, null);*/

        printHud(g2);
    }

    /**
     * Metodo che si occupa della creazione dello sfondo giocabile
     * @param g2
     */
    private void paintBackground (Graphics2D g2)
    {
        g2.setColor(COLORE_CHECHKPOINT);   //Riga di partenza, colore marrone
        g2.fillRect(0, 0, 100, 8);

        g2.setColor(COLORE_STRADA);   //Strade, colore grigio
        paintRiga(g2,8,5);

        g2.setColor(COLORE_CHECHKPOINT);   //Riga di riposo, colore marrone
        paintRiga(g2,48,1);

        g2.setColor(COLORE_ACQUA);   //Acqua, colore blu
        paintRiga(g2,56,5);

        g2.setColor(COLORE_ARRIVO);  //Contorno destinazione, colore verde
        g2.fillRect(0, 96, 100, 14);

        g2.setColor(COLORE_ACQUA); // Destinazione, colore blu
        paintArrivo(g2,96);
    }


    private void paintRiga(Graphics2D g2,int inizio,int nCaselle)
    {
        for(int c=0 ;c<nCaselle;c++)
            g2.fillRect(0, inizio+8*c, 100, 8);
    }

    private void paintArrivo(Graphics2D g2, int inizio)
    {
        int x=2;
        for (int c=0;c<5;c++)
        {
            if(c!=0)
                x+=21;
            g2.fillRect(x, inizio, 12, 10);
        }
    }

    /**
     * Metodo che crea l'Hud
     * @param g2
     */
    private void printHud (Graphics2D g2)
    {
        
        printVite(g2,6);
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
        int t=50; //Fattore temporale iniziale
        g2.fillRect(33,145,t,4); //Barra della vita
        g2.scale(0.45,-0.45);
        g2.drawString("TIME",190,-322); // Scritta TIME circa formatta
        g2.scale(2.222222222222222222,-2.222222222222222222); //todo sistemare sto schifo
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