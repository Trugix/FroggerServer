import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class FroggerApp //main
{
	private JFrame frame;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(() ->
		{
			try
			{
				FroggerApp window = new FroggerApp();
				window.frame.setVisible(true);
				window.frame.setTitle("Frogger");
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
	public FroggerApp()
	{
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		frame = new JFrame();
		frame.setBounds(500, 0, 656, 1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		
		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(Color.WHITE);
		
		FroggerModel model;
		FroggerCtrl control = null;
		
		try
		{
			model = new FroggerModel();
			control = new FroggerCtrl(model);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		assert control != null;
		frame.add(control.frogView, BorderLayout.CENTER);
		control.frogView.setVisible(true);
		frame.setVisible(true);
	}
	
}
