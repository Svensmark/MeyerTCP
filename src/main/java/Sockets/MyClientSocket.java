package Sockets;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class MyClientSocket {
    
    private Socket socket;
    private Scanner scanner;
    private static Scanner sc = new Scanner(System.in);
    
    private MyClientSocket(InetAddress serverAddress, int serverPort) throws Exception {
        this.socket = new Socket(serverAddress, serverPort);
        this.scanner = new Scanner(System.in);
    }
    
    private void start() throws IOException {
        String input;
        while (true) {
            input = scanner.nextLine();
            PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
            out.println(input);
            out.flush();            
        }
    }
    
    public static void main(String[] args) throws Exception {
        System.out.println("Which IP do you wish to connect to?");
        String ipInput = sc.nextLine();
        System.out.println("On which port?");
        String portInput = sc.nextLine();
        MyClientSocket client = new MyClientSocket(
                InetAddress.getByName(ipInput), 
                Integer.parseInt(portInput));
        
        System.out.println("\r\nConnected to Server: " + client.socket.getInetAddress());
        client.start();                
    }
}
