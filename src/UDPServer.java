import java.io.IOException;
import java.net.*;


public class UDPServer {
    private DatagramSocket server;

    public UDPServer() throws SocketException {
        initializeServer();
    }

    private void initializeServer() throws SocketException {
        server = new DatagramSocket(25722);
        System.out.println("Server listens on: " + server.getLocalPort());
    }

    private void service() throws IOException {
        byte[] buff = new byte[1024];
        final DatagramPacket datagram = new DatagramPacket(buff, buff.length);

        server.receive(datagram);

        new Thread(() -> {
            InetAddress clientAddress = datagram.getAddress();
            int clientPort = datagram.getPort();
            String clientRequest = getStringDatagram(datagram.getData(), datagram.getLength());

            byte[] respBuff = "Ok".getBytes();

            sendToClientUDP(server, respBuff, clientAddress, clientPort);
        }).start();
    }

    private int getNumberDatagram(byte[] datagram, int datagramLength) {
        int clientRequest = Integer.parseInt(new String(datagram, 0, datagramLength));
        System.out.println("INFO: I've got: " + clientRequest);
        return clientRequest;
    }

    private String getStringDatagram(byte[] datagram, int datagramLength) {
        String clientRequest = new String(datagram, 0, datagramLength);
        System.out.println("INFO: I've got: " + clientRequest);
        return clientRequest;
    }

    private String removeSymbols(String string, String toRemove) {
        return string.replaceAll(toRemove, "");
    }

    private void sendToClientUDP(DatagramSocket server, byte[] bytesToSend, InetAddress clientAddress, int clientPort) {
        DatagramPacket resp = new DatagramPacket(bytesToSend, bytesToSend.length, clientAddress, clientPort);
        try {
            server.send(resp);
            System.out.println("INFO: I've sent " + new String(bytesToSend));
        } catch (IOException e) {
            System.out.println("ERROR: Cannot send response to client...");
            e.printStackTrace();
        }
    }

    private String concatenateSymbols(String string, int concatenateNumber) {
        StringBuilder updatedString = new StringBuilder();
        while (concatenateNumber != 0) {
            updatedString.append(string);
            concatenateNumber--;
        }
        return updatedString.toString();
    }

    public void listen() {
        while (true) {
            try {
                service();
            } catch (IOException IOError) {
                System.out.println("Some error occurs while listen");
                IOError.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        try {
            new UDPServer().listen();
        } catch (SocketException e) {
            System.out.println("Could not set up the server");
        }
    }
}