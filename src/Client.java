import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.net.Socket;

public class Client implements KeyListener, MouseListener
{
    private Socket sock;
    private BufferedReader input;
    private ObjectOutputStream output;
    private JFrame frame;
    private PnlFrog panel;
    public Client() throws IOException {
        sock = new Socket("127.0.0.1", 9001);
        input = new BufferedReader(new InputStreamReader(sock.getInputStream()));
        output = new ObjectOutputStream(sock.getOutputStream());
        createGUI();
    }

    private void createGUI (){
        frame = new JFrame();
        frame.setBounds(500, 0, 656, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
    }
    public void setPanel(PnlFrog newPanel)
    {
        this.panel = newPanel;
        newPanel.addKeyListener(this);
        newPanel.addMouseListener(this);
        panel.repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        try {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT -> {
                    output.writeObject(KeyEvent.VK_LEFT);
                }
                case KeyEvent.VK_RIGHT -> {
                    output.writeObject(KeyEvent.VK_RIGHT);
                }
                case KeyEvent.VK_DOWN -> {
                    output.writeObject(KeyEvent.VK_DOWN);
                }
                case KeyEvent.VK_UP -> {
                    output.writeObject(KeyEvent.VK_UP);
                }
                default -> {
                    //output.writeObject(null);
                }
            }
            output.flush();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

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
