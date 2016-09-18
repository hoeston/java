package sock;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketClient {
  public static void main(String[] args){
    try {
      Socket socket = new Socket("127.0.0.1",3333);
      socket.setSoTimeout(3000);
      
      PrintWriter printWriter = new PrintWriter(socket.getOutputStream(),true);
      BufferedReader sysBuff = new BufferedReader (new InputStreamReader(System.in));
      printWriter.println(sysBuff.readLine());
      printWriter.flush();
      
      BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(socket.getInputStream()));
      String result = bufferedReader.readLine();
      System.out.println("enter a string: "+result);
      
      printWriter.close();
      bufferedReader.close();
      socket.close();
    }catch (Exception e){
      System.out.println("Exception:"+e);
    }
  }
}
