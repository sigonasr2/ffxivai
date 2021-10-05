package sig;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler extends Thread{
    Socket socket;
    PrintWriter out;
    BufferedReader in;
    public ClientHandler(Socket socket) {
        this.socket=socket;
    }

    public void run() {
        try {
            out = new PrintWriter(socket.getOutputStream(),true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line;
            while ((line=in.readLine())!=null) {
                if (line.equals("EOF")) {
                    out.println("Goodbye.");
                }
                out.println(line);
            }

            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
