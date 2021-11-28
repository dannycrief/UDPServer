# UDPServer

SKJ (pl. Computer Networks and Network Programming in Java) project of UDP server.

## How it works
1. The class TCPClient has been created to send requests to the TCP server. Determine the address and port of the server to which requests will be sent. For example:
```java
TCPClient tcpClient = new TCPClient("172.21.48.26", 16777);
```
2. Since we know at what address and port is the server, we should in turn send a request to it:
```java
tcpClient.sendTCP("asd");
```
3. After sending a request to TCP server we should define exactly the same for our UDP server, i.e. we have to determine on what address and port our UDP server will be defined, e.g:
```java
try {
    new UDPServer("172.0.0.1", 27684).listen();
} catch (SocketException e) {
    System.out.println("Could not set up the server");
}

```
Once the above server UDP is defined, it will be started. A given UDP server is defined based on threads, that is, it is able to serve several clients at once.
