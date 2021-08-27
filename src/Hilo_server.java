import java.io.IOException;
import java.io.ObjectOutputStream;
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

    void sendMessage(String mensaje){
        try {
            hilo_server2s.forEach(t-> t.getPrintWriter().println(mensaje));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String Calculo(String Valor, String Peso, String Impuesto){
        double Monto = 0;

        double valor = Double.valueOf(Valor);
        double peso = Double.valueOf(Peso);
        double impuesto = Double.valueOf(Impuesto);

        Monto = (valor*impuesto/100)+(peso*0.25);

        return String.valueOf(Monto);
    }
    public Set<Hilo_server2> getServerThreadThreads(){
        return hilo_server2s;
    }

}
