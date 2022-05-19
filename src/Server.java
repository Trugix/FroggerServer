import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server
{
	private static final String ERROR ="ERRORE NELLA TRASMISSIONE DEI DATI TRA CLIENT E SERVER";
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private FroggerModel clientModel = new FroggerModel(0);  //crea un'istanza di model che verrà usata per la finestra del client
	private PnlFrog clientView;
	private boolean first = true;
	private boolean stop = false;
	private FroggerCtrl ctrl;
	
	public void setStop(boolean stop)
	{
		this.stop = stop;
	}
	
	public PnlFrog getClientView()
	{
		return clientView;
	}
	
	public Server(FroggerCtrl ctrl) throws IOException
	{
		this.ctrl = ctrl;
	}
	
	/**
	 * Thread che ascolta per update da parte client
	 */
	Thread ricezione = new Thread(new Runnable()
	{
		@Override
		public void run()
		{
			while (!stop)
			{
				try
				{
					Transfer statoClient = (Transfer) in.readObject();   //cast dell'input come Transfer
					clientModel.transferToModel(statoClient);   //chiamata che passa i dati di transfer al model usato per disegnare la schermata del 2ndo giocatore
					clientModel.setPunteggioAvversario(ctrl.getModel().getPoints());
					if (first)  //nella prima iterazione crea il nuovo panel e la nuova finestra, inizializzandola allo stato GAME
					{
						clientView = new PnlFrog(clientModel);
						clientView.setState( PnlFrog.STATE.GAME);
						first=false;
						newWindow();
					}
					clientView.setEntities(clientModel.getEntities());
					if (clientModel.getFrog().getVite()<=0 || clientModel.getDestinazioni()==5) //se l'avversario finisce le vite o vince il suo panel passa a GAME_OVER e il suo punteggio viene salvato
					{
						clientView.setState(PnlFrog.STATE.GAME_OVER);
						ctrl.getModel().setPunteggioAvversario(statoClient.getPunteggio()); //aggiorna la variabile usata per calcolare chi ha vinto alla fine del gioco
						stop = true;
					}
					if(ctrl.getFrogView().getState()== PnlFrog.STATE.GAME_OVER && clientView.getState()== PnlFrog.STATE.GAME_OVER ) //se entrambi i giocatori sono a GAME_OVER allora si passa
					{                                                                                                               //alla schermata finale
						ctrl.getFrogView().setState(PnlFrog.STATE.GAME_OVER_MULTI);
						ctrl.getFrogView().repaint();
					}
					clientView.repaint();
				}
				catch (Exception e)
				{
					System.out.println(ERROR);
					e.printStackTrace();
					System.exit(0);
				}
			}
		}
	});
	
	/**
	 * Metodo che inizializza la connessione con il client
	 */
	public void connessione()
	{
		try
		{
			int porta = 1234;
			System.out.println("\nDati per la connessione:\nIP: " + InetAddress.getLocalHost().getHostAddress() + "\tPORTA: "+ porta +"\n");
			System.out.println("Inizializzazione del server...");
			ServerSocket server = new ServerSocket(porta);
			System.out.println("Server in ascolto sulla porta " + porta);
			Socket socketClient = server.accept();
			ctrl.startGame();
			System.out.println("Connessione riuscita");
			server.close();
			InputStream inputStream = socketClient.getInputStream();
			OutputStream outputStream = socketClient.getOutputStream();
			out = new ObjectOutputStream(outputStream);
			in = new ObjectInputStream(inputStream);
			ricezione.start(); //parte il thread di ascolto
		}
		catch (IOException e)
		{
			System.out.println("CONNESSIONE NON RIUSCITA");
			e.printStackTrace();
		}
	}
	
	/**
	 * crea una nuova finestra con la vista del client
	 */
	public void newWindow()
	{
		JFrame clientFrame = new JFrame();
		clientFrame.setBounds(750, 0, 493, 750);
		clientFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		clientFrame.setLayout(new BorderLayout());
		clientFrame.setTitle("Avversario");
		
		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(Color.WHITE);
		
		clientFrame.add(clientView);
		clientFrame.setIconImage(FroggerModel.spritesFrog[2]);
		clientFrame.setVisible(true);
		clientFrame.setFocusable(false);
	}
	
	/**
	 * manda in output i dati necessari a disegnare la finestra del server sul lato client
	 */
	public void send()
	{
		Transfer statoCorrente = ctrl.modelToTransfer(ctrl.getModel()); //transforma il model attuale in un trasfer da scriver in output
        try
        {
            out.writeObject(statoCorrente);
            out.reset();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
}