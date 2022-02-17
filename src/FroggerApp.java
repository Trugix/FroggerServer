import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class FroggerApp
{
	
	private JFrame frame;
	
	private static ServerSocket serverPort;
	
	static
	{
		try
		{
			serverPort = new ServerSocket(1234);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) throws IOException
	{
		/*Socket socket = serverPort.accept();
		Client client1 = new Client(socket);
		client1.draw();
		 InputStream input = socket.getInputStream();
		InputStreamReader reader = new InputStreamReader(input);
		
		reader.read();*/
		
		EventQueue.invokeLater(() ->
		{
			try
			{
				FroggerApp window = new FroggerApp();
				window.frame.setVisible(true);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		});
	}
	
	/**
	 * Create the application.
	 */
	public FroggerApp() throws IOException
	{
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() throws IOException
	{
		frame = new JFrame();
		frame.setBounds(500, 0, 656, 1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		
		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(Color.WHITE);
		
		FroggerModel model = new FroggerModel();
		FroggerCtrl control = new FroggerCtrl(model);
		
		
		frame.add(control.frogView, BorderLayout.CENTER);
		control.frogView.setVisible(true);
		frame.setVisible(true);
	}
	
}
