package sock;
import java.io.*;
import java.net.*;

import javax.swing.JOptionPane;
 
public class Server extends ServerSocket {
    private static final int SERVER_PORT =3333;
 
    public Server()throws IOException {
        super(SERVER_PORT);
 
        try {
            while (true) {
                Socket socket = accept();
                new CreateServerThread(socket);//当有请求时，启一个线程处理
            }
        }catch (IOException e) {
        }finally {
            close();
        }
    }
 
    //线程类
    class CreateServerThread extends Thread {
        private Socket client;
        private BufferedReader bufferedReader;
        private PrintWriter printWriter;
 
        public CreateServerThread(Socket s)throws IOException {
            client = s;
 
            bufferedReader =new BufferedReader(new InputStreamReader(client.getInputStream()));
             
            printWriter =new PrintWriter(client.getOutputStream(),true);
            System.out.println("Client(" + getName() +") come in...");
             
            start();
        }
 
        public void run() {
            try {
                String line = bufferedReader.readLine();
                char[] charArray = line.toCharArray();
                
              for(int i=charArray.length-1;i<=0;i--){
                System.out.println("Client("+getName()+charArray[i]);
                printWriter.close();
                bufferedReader.close();
                client.close();
              }
            }catch (IOException e) {
            }
        }
    }
 
    public static void main(String[] args)throws IOException {
        new Server();
    }
}
