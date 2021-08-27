import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class Hilo_cliente extends Thread{
    private BufferedReader bufferedReader;

    public Hilo_cliente(Socket sc) throws IOException{
        bufferedReader = new BufferedReader(new InputStreamReader(sc.getInputStream()));
    }

    public void run(){
        boolean flag = true;
        while(flag){
            try {
                System.out.println(bufferedReader.readLine());
            }catch (Exception e){
                flag = false;
                interrupt();
            }
        }
    }
}
