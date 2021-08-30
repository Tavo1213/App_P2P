/**
 *      INSTITUTO TECNOLOGICO DE COSTA RICA
 *        AREA INGENIERIA EN COMPUTADORES
 *  Clase: Cliente
 *  Lenguaje: Java (JDK 11.0.12)
 *  @author Gustavo Alvarado Aburto
 *  @version 1.0
 *  Descripción: Lanza la interfaz para ingresar puertos y nombre de usuario
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;

public class Cliente extends Thread{

    public boolean flag = true;

    /**
     * Crea la conexion entre clientes.
     * @param port numero de puerto al cual se quiere escuchar
     * @throws IOException
     */
    public void conexion_clientes(String port) throws IOException{
        System.out.println("Client connecting");
        Socket sc = null; //crea el socket a escuchar
            try{
                sc = new Socket("localhost", Integer.valueOf(port)); //crea la conexion entre clientes.
                new Hilo_cliente(sc).start(); //Inicia el hilo de cliente.
                System.out.println("Client conencted");

            } catch (Exception e){
                if (sc != null) sc.close();
                else System.out.println("Invalid input");
            }
    }

}
