import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.Socket;

public class Client
{
    private Socket sock;
    private BufferedReader input;
    private PrintStream output;
    private JFrame frame;
    private PnlFrog panel;
    public Client() throws IOException {
        sock = new Socket("127.0.0.1", 9001);
        input = new BufferedReader(new InputStreamReader(sock.getInputStream()));
        output = new PrintStream(sock.getOutputStream(), true);
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
        panel.repaint();
    }

}
