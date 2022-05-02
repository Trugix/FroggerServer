import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket sock;
    private Socket s;
    private BufferedReader input;
    private ObjectOutputStream output;
    private FroggerCtrl ctrl;

    public Server(FroggerCtrl ctrl) throws IOException {
        this.sock = new ServerSocket(9001);
        s = null;
        this.ctrl = ctrl;
    }

    public void start() throws IOException {

        s = sock.accept();
        input = new BufferedReader(new InputStreamReader(s.getInputStream()));
        output = new ObjectOutputStream(s.getOutputStream());
    }

    public void sender() throws IOException {
        output.writeObject(ctrl.frogView);
        output.flush();
    }


}
