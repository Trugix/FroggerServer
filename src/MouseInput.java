import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

public class MouseInput implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(Menu.playButton.contains(e.getX(), e.getY())){
            PnlFrog.state = PnlFrog.STATE.GAME;
        }
        System.out.println(Menu.playButton.contains(e.getX(), e.getY()));
        System.out.printf("%d, %d\n", e.getX(), e.getY());

        if(e.getX() >= 169 && e.getX() <= 393){
            if(e.getY() >= 224 && e.getY() <= 280) {
                PnlFrog.state = PnlFrog.STATE.GAME;
                
            }
        }

        System.out.println(PnlFrog.state);
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
