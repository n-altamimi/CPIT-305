import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws Exception {
        int i = 1;
        try (ServerSocket server = new ServerSocket(9000)) {
            System.out.println("Server started");

            while (true) {
                Socket client = server.accept();
                if (client.isConnected()) {
                    System.out.println("client " + i++ + " is connected");
                }
                ThreadedServer tr = new ThreadedServer(client);
                Thread t = new Thread(tr);
                t.start();
            }
        }

    }
}
