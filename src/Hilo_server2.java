/**
 *      INSTITUTO TECNOLOGICO DE COSTA RICA
 *        AREA INGENIERIA EN COMPUTADORES
 *  Clase: Hilo_server2
 *  Lenguaje: Java (JDK 11.0.12)
 *  @author Gustavo Alvarado Aburto
 *  @version 1.0
 *  Descripción: Almacena los hilos de servidores ejecutandose, se mantiene atento a llegada de mensajes de los clientes
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Hilo_server2 extends Thread{
    private Hilo_server serverThread; //hilo del servidor
    private Socket socket; //socket del servidor
    private PrintWriter printWriter; //transforma las entradas de mensajes en texto

    /**
     * Crea un socket e hilo de servidor
     * @param socket socket que se desea crear
     * @param serverThread hilo de servidor que se pueda crear
     */
    public Hilo_server2(Socket socket, Hilo_server serverThread){
        this.serverThread = serverThread;
        this.socket = socket;
    }

    /**
     * Brinda la posibilidad de transformar las entradas recibidas a texto.
     * @return printWriter, transformador de entradas recibidas a texto.
     */
    public PrintWriter getPrintWriter() {
        return printWriter;
    }

    /**
     * Ejecuta el hilo que se mantiene alerta de entradas de datos
     */
    public void run() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.socket.getInputStream())); //lector de entradas de datos
            this.printWriter = new PrintWriter(socket.getOutputStream(), true); //convierte las salidas de datos a texto
            while (true){
                serverThread.sendMessage(bufferedReader.readLine()); //envia a cliente conectado la entrada recibida.

            }

        } catch (Exception e) {
            serverThread.getServerThreadThreads().remove(this);
        }
    }

}

