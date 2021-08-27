import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashSet;
import java.util.Set;

public class Hilo_server extends Thread{
    private ServerSocket ss;
    private Set<Hilo_server2> hilo_server2s = new HashSet<Hilo_server2>();

    public Hilo_server(String portNumb) throws IOException{
        ss = new ServerSocket(Integer.valueOf(portNumb));
    }

    public void run(){
        try{
            while(true){
                Hilo_server2 hilo_server2 = new Hilo_server2(ss.accept(), this);
                hilo_server2s.add(hilo_server2);
                hilo_server2.start();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    void sendMessage(String message){
        try {
            hilo_server2s.forEach(t-> t.getPrintWriter().println(message));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Set<Hilo_server2> getServerThreadThreads(){
        return hilo_server2s;
    }

}
