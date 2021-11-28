import java.io.IOException;
import java.net.SocketException;

public class Main {
    public static void main(String[] args) throws IOException {
        TCPClient tcpClient = new TCPClient("172.21.48.26", 16777);
        tcpClient.sendTCP("asd");
        try {
            new UDPServer("172.0.0.1", 27684).listen();
        } catch (SocketException e) {
            System.out.println("Could not set up the server");
        }
    }
}
