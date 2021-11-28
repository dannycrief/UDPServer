import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPClient {
    private final String address;
    private final int port;

    public TCPClient(String address, int port) {
        this.address = address;
        this.port = port;
    }

    public void sendTCP(String message) throws IOException {
        Socket socket = null;
        PrintWriter out = null;
        BufferedReader in = null;

        try {
            socket = new Socket(this.address, this.port);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (UnknownHostException e) {
            System.out.println("Unknown host.");
            System.exit(-1);
        } catch (IOException e) {
            System.out.println("No I/O");
            System.exit(-1);
        }

        String addressPort = address + ":" + port;

        out.println(message);
        System.out.println("INFO: Sending " + message + " to " + addressPort);
        String serverAnswer = in.readLine();
        System.out.println("INFO: Server response: " + serverAnswer);

        try {
            socket.close();
        } catch (IOException e) {
            System.out.println("Cannot close the socket.");
            System.exit(-1);
        }
    }
}
