import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class Client
{
	/*public JFrame frame;
	
	private Socket socket;
	
	public Client(Socket s) throws IOException
	{
		this.socket=s;
		InputStream n= new InputStream(socket.getInputStream());
	}
	
	public void in() throws IOException
	{
		frame = new JFrame();
		frame.setBounds(500, 0, 656, 1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		
		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(Color.WHITE);
		
		FroggerModel model = new FroggerModel();
		FroggerCtrl control = new FroggerCtrl(model/*, frogView*///);
		
	/*
		frame.add(control.frogView, BorderLayout.CENTER);
		control.frogView.setVisible(true);
		frame.setVisible(true);
	}*/
	
	/*public void draw ()
	{
		EventQueue.invokeLater(() ->
		{
			try
			{
				this.in();
				this.frame.setVisible(true);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		});
	}*/
}
