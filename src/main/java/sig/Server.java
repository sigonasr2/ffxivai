package sig;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {
    ServerSocket socket;
    public void start(int port) {
        try {
            socket = new ServerSocket(port);
            while (true) {
                new ClientHandler(socket.accept()).start();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void stop() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
