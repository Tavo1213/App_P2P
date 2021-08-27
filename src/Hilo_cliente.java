import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;


public class Hilo_cliente extends Thread{
    private BufferedReader bufferedReader;
    //private PrintWriter out;

    public Hilo_cliente(Socket sc) throws IOException{
        bufferedReader = new BufferedReader(new InputStreamReader(sc.getInputStream()));
        //out = new PrintWriter(sc.getOutputStream(), true);
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
