import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;

public class Cliente extends Thread{

    public boolean flag = true;

    public void updateListeToPeers(BufferedReader bufferedReader, String username, Hilo_server serverThread, String port) throws IOException{
        System.out.println("Client connecting");
        Socket sc = null;
            try{
                sc = new Socket("localhost", Integer.valueOf(port));
                new Hilo_cliente(sc).start();
                System.out.println("Client conencted");

            } catch (Exception e){
                if (sc != null) sc.close();
                else System.out.println("Invalid input");
            }
    }

    public void communicate(BufferedReader bufferedReader, String username, Hilo_server serverThread) { //communication between peers
        try{
            System.out.println("you can communicate");
            while (flag){
                String msg = bufferedReader.readLine();
                if (msg.equals("e")){
                    flag = false;
                    break;
                } else{
                    serverThread.sendMessage(username+ ":" + msg);
                    String mensaje = username + ":" + msg;

            }
        }
            System.exit(0);

    }catch (Exception e){}
}
    public void run(BufferedReader bufferedReader, String username, Hilo_server serverThread){
        communicate(bufferedReader, username, serverThread);
    }
}
