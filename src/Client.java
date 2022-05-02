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
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private JFrame frame;
    private PnlFrog panel;

    private static Client client;
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        client = new Client();
    }

    public Client() throws IOException, ClassNotFoundException {
        sock = new Socket("127.0.0.1", 9001);
        input = new ObjectInputStream(sock.getInputStream());
        output = new ObjectOutputStream(sock.getOutputStream());
        checkIncoming.run();
        createGUI();
    }

    private void createGUI (){
        frame = new JFrame();
        frame.setBounds(500, 0, 656, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    Runnable checkIncoming = new Runnable() {
        @Override
        public void run() {
            while (true)
            {
                try {
                    panel = (PnlFrog) input.readObject();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                panel.repaint();
            }
        }
    };

    private Thread receiverThread = new Thread(checkIncoming);
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
