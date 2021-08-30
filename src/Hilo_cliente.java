/**
 *      INSTITUTO TECNOLOGICO DE COSTA RICA
 *        AREA INGENIERIA EN COMPUTADORES
 *  Clase: Hilo_cliente
 *  Lenguaje: Java (JDK 11.0.12)
 *  @author Gustavo Alvarado Aburto
 *  @version 1.0
 *  Descripción: ejecuta el hilo de cliente, se mantiene atento a entradas de datos.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;


public class Hilo_cliente extends Thread{
    private BufferedReader bufferedReader; //lector de entradas de datos

    /**
     * Habilita el lector de entradas al cliente.
     * @param sc Socket del cliente.
     * @throws IOException Manejo de excepciones en caso de fallar.
     */
    public Hilo_cliente(Socket sc) throws IOException{
        bufferedReader = new BufferedReader(new InputStreamReader(sc.getInputStream()));
    }

    /**
     * Ejecuta el hilo del cliente, se mantiene atento a entradas de dato/mensajes e imprime el mensaje en consola.
     */
    public void run(){
        boolean flag = true;
        while(flag){
            try {
                System.out.println(bufferedReader.readLine()); //lee e imprime la entrada de datos al cliente.
            }catch (Exception e){
                flag = false; //termina el ciclo de lectura
                interrupt();
            }
        }
    }
}
