import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Hilo_server2 extends Thread{
    private Hilo_server serverThread;
    private Socket socket;
    private PrintWriter printWriter;

    public Hilo_server2(Socket socket, Hilo_server serverThread){
        this.serverThread = serverThread;
        this.socket = socket;
    }
    public PrintWriter getPrintWriter() {
        return printWriter;
    }

    public void run() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            this.printWriter = new PrintWriter(socket.getOutputStream(), true);
            while (true) serverThread.sendMessage(bufferedReader.readLine());

        } catch (Exception e) {
            serverThread.getServerThreadThreads().remove(this);
        }
    }

}

