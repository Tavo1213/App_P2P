import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;

public class Cliente {

    public boolean flag = true;

    /*public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        Interfaz vent = new Interfaz();

        //System.out.println("enter username and port for peer:");
        ServerThread serverThread = new ServerThread(port);
        serverThread.start();
        new Peer().updateListeToPeers(bufferedReader, Username_field.getText(), serverThread);
    }*/

    public void starConnection(String port){

    }

    public void updateListeToPeers(BufferedReader bufferedReader, String username, Hilo_server serverThread, String port) throws IOException{
        System.out.println("Client connecting");
        //String input = bufferedReader.readLine();
        //String[] InputValues = input.split(" ");
        Socket sc = null;
        /*if (!input.equals("s")) for(int i = 0; i < InputValues.length; i ++){
            String[] address = InputValues[i].split(":");
            Socket sc = null;*/
            try{
                sc = new Socket("localhost", Integer.valueOf(port));
                new Hilo_cliente(sc).start();
                System.out.println("Client conencted");

            } catch (Exception e){
                if (sc != null) sc.close();
                else System.out.println("Invalid input");
            }
        //communicate(bufferedReader, username, serverThread);
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
}
