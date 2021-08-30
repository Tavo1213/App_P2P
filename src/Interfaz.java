/**
 *      INSTITUTO TECNOLOGICO DE COSTA RICA
 *        AREA INGENIERIA EN COMPUTADORES
 *  Clase: Chat
 *  Lenguaje: Java (JDK 11.0.12)
 *  @author Gustavo Alvarado Aburto
 *  @version 1.0
 *  Descripción: Crea la interfaz para ingresar datos de usuario y realiza la conexion entre clientes.
 */
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Interfaz extends JFrame{
    //elementos de la ventana
    private JTextField Port_field;
    private JButton Connect_btn;
    private JPanel Jpanel;
    private JTextField Username_field;
    private JButton Listen_btn;
    private JTextField Port_listen_field;
    private JLabel Error_port;
    private JLabel Message_area;

    Cliente cli = new Cliente(); //instancia de la clase cliente para utilizar sus metodos

    /**
     * Crea al interfaz para ingreso de datos de usuario
     */
    public Interfaz(){
        //propiedades de la ventana
        setContentPane(Jpanel);
        setSize(820, 350);
        setVisible(true);
        setTitle("App Cliente Servidor");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        addWindowListener(new WindowAdapter() {

                @Override
                public void windowClosing(WindowEvent e){ //Evento de cierre de ventana
                    super.windowClosing(e);
                    cli.flag = false;

                }
            });

        /**
         * Detecta cuando se presiona el boton connect en la ventana.
         */
        Connect_btn.addActionListener(new ActionListener() {
            /**
             * Detecta cuando el boton se pulsa y crea la el cliente y server.
             * @param e evento de pulsacion del boton
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in)); //lector de entradas a cliente.

                    String port = Port_field.getText();
                    String username = Username_field.getText();

                    if (new Chat().validation(port) != false){ //validacion de entrada de puerto tipo entero
                        System.out.println("Connection started");
                        Hilo_server serverThread = new Hilo_server(port); //crea hilo de servidor con el puerto ingresado
                        serverThread.start();

                        /**
                         *  Detecta cuando se presiona el boton Listen en la ventana.
                         */
                        Listen_btn.addActionListener(new ActionListener() {
                            /**
                             * Detecta cuando se pulsa el boton en la ventana, lanza la segunda interfaz y crea la conexion con el puerto a escuchar.
                             * @param e evento de pulsacion del boton en la ventana
                             */
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                String port_listen = Port_listen_field.getText();

                                if (bufferedReader != null && username!= null && serverThread!= null && port_listen != null){ //validacion de variables no nulas.
                                    try{
                                        cli.conexion_clientes(port_listen); //crea la conexion entre clientes.
                                        Chat vent2 = new Chat(); //instancia de la segunda interfaz
                                        vent2.Chat(serverThread, username); //se crea la interfaz y se trasladan parametros hacia la otra clase.
                                        setVisible(false);

                                    }catch (Exception i){ //Manejo de excepciones.
                                        System.out.println("Error");
                                    }
                                }else System.out.println("Error");
                            }
                    });

                    }else Error_port.setText("Error, ingrese numeros enteros como puerto");

                }catch (Exception i){
                    i.printStackTrace();
                }

            }
        });

    }


}

