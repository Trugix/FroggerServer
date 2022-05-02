import java.awt.event.KeyEvent;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private ServerSocket sock;
    private Socket s;
    private BufferedReader input;
    private ObjectOutputStream output;
    private FroggerCtrl ctrl;

    Runnable checkIncoming = new Runnable() {
        @Override
        public void run() {
            while (true)
            {
                try {
                  //  ctrl.model.moveFrog(Integer.parseInt(input.readLine()));
                    System.out.println(input.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    };

    private Thread receiverThread = new Thread(checkIncoming);

    public Server(FroggerCtrl ctrl) throws IOException {
        this.sock = new ServerSocket(9001);
        s = null;
        this.ctrl = ctrl;
    }

    public void start() throws IOException {
        s = sock.accept();
        input = new BufferedReader(new InputStreamReader(s.getInputStream()));
        output = new ObjectOutputStream(s.getOutputStream());
        receiverThread.start();
    }

    public void sender() throws IOException {
        output.writeObject(ctrl.frogView);
        output.flush();
    }



}
