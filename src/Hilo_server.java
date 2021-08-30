/**
 *      INSTITUTO TECNOLOGICO DE COSTA RICA
 *        AREA INGENIERIA EN COMPUTADORES
 *  Clase: Hilo_server
 *  Lenguaje: Java (JDK 11.0.12)
 *  @author Gustavo Alvarado Aburto
 *  @version 1.0
 *  Descripción: Habilita el hilo de servidor para cada cliente, envia mensajes y conecta cliente con servidor.
 */
import javax.swing.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashSet;
import java.util.Set;

public class Hilo_server extends Thread{
    private ServerSocket ss; //crea socket del servidor
    private Set<Hilo_server2> hilo_server2s = new HashSet<Hilo_server2>(); //crea el hilo del servidor

    /**
     * Conecta el cliente con el servidor mediante un numero de puerto.
     * @param portNumb Numero de puerto del cliente.
     * @throws IOException Manejo de excepciones en caso de fallo en conexion.
     */
    public Hilo_server(String portNumb) throws IOException{
        ss = new ServerSocket(Integer.valueOf(portNumb));

    }

    /**
     * Ejecucion del hilo de servidor, agrega el hilo al conjunto de hilos de servidor (hilo_server2s).
     */
    public void run(){
        try{
            while(true){ //mantiene corriendo el servidor
                Hilo_server2 hilo_server2 = new Hilo_server2(ss.accept(), this);
                hilo_server2s.add(hilo_server2); //añade el hilo de servidor a coleccion de hilos
                hilo_server2.start(); //inicia la ejecucion del hilo
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Envia un mensaje de cliente a cliente.
     * @param mensaje String del mensaje que se desea enviar.
     */
    void sendMessage(String mensaje){
        try {
            hilo_server2s.forEach(t-> t.getPrintWriter().println(mensaje)); //toma los hilos del servidor y envia el mensaje

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Calcula el monto de un producto con base a su Valor, Peso e impuesto.
     * @param Valor Valor del producto (string).
     * @param Peso Peso del producto (string).
     * @param Impuesto Impuesto del producto (string).
     * @return Monto del producto en base a parametros ingresados.
     */
    public String Calculo(String Valor, String Peso, String Impuesto){
        double Monto = 0;

        //conversion de string a double
        double valor = Double.valueOf(Valor);
        double peso = Double.valueOf(Peso);
        double impuesto = Double.valueOf(Impuesto);

        Monto = (valor * impuesto /100)+(peso*0.25); //formula para calcular el monto

        return String.valueOf(Monto);
    }

    /**
     * Toma los hilos de servidores almacenados en hilo_server2s.
     * @return hilos almacenados en hilo_server2s.
     */
    public Set<Hilo_server2> getServerThreadThreads(){
        return hilo_server2s;
    }

    /**
     * Actualiza un area de texto con base a un string ingresado como parametro.
     * @param chat Area de texto que se desea actualizar.
     * @param a String para actualizar el area de texto.
     */
    public void updateTexArea(JTextArea chat, String a){
        chat.append(a); //agrega al area de texto el string ingresado.
    }

}
