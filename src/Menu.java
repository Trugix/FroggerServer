import java.awt.*;
import java.awt.event.MouseListener;

public class Menu{

    public static Rectangle playButton = new Rectangle(300, -1100, 400, 100);
    public static Rectangle scoreButton = new Rectangle(300, -900, 400, 100);
    public static Rectangle quitButton = new Rectangle(300, -700, 400, 100);


    public void render(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.scale(1, -1);

        Font ftn = new Font("arial", Font.BOLD, 100);
        Font ftn1 = new Font("arial", Font.BOLD, 60);
        g.setFont(ftn);

        g.setColor(Color.magenta);
        g.drawString("FROGGER", 250, -1200);
        g.setColor(Color.yellow);
        g.drawString("FROGGER", 255, -1205);
        g.setColor(Color.GREEN);
        g.drawString("FROGGER", 260, -1210);

        g2.setColor(Color.yellow);
        g2.setFont(ftn1);

        g2.drawString("1 PLAYER", 360, -1030);
        g2.draw(playButton);
        g2.drawString("SCOREBOARD", 360, -830);
        g2.draw(scoreButton);
        g2.draw(quitButton);
    }
}
