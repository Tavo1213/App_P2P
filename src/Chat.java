/**
 *      INSTITUTO TECNOLOGICO DE COSTA RICA
 *        AREA INGENIERIA EN COMPUTADORES
 *  Clase: Chat
 *  Lenguaje: Java (JDK 11.0.12)
 *  @author Gustavo Alvarado Aburto
 *  @version 1.0
 *  Descripción: Crea una ventana para cada cliente, habilita la opción de solicitar el calculo de un Monto a base de 3 parametros y realiza envio de mensajes entre clientes.
 */
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;

public class Chat extends JFrame{
    //Elementos de la ventana
    private JPanel Jpanel;
    private JTextField Message_field;
    private JButton Send_btn;
    private JLabel Titulo;
    private JTextField Valor_field;
    private JTextField Peso_field;
    private JTextField Impuesto_field;
    private JButton Calc_btn;
    public JLabel Monto_field;
    private JTextArea Chat;
    private JScrollPane Scroll;

    /**
     * Crea una ventana_cliente para solicitar calculos de monto en base a los parametro ingresados
     * @param serverThread Hilo en el cual corre el server de cada cliente
     * @param username Nombre de usuario ingresado
     */
    public void Chat(Hilo_server serverThread, String username){

        Hilo_server s_thread = serverThread;

        //Propiedades de la ventana
        setContentPane(Jpanel);
        setTitle(username);
        setSize(450, 400);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Chat.setLineWrap(true);

        Titulo.setText("Cliente: "+username);//titulo de la ventana

        //detector de presionar el boton calcular en la ventana
        Calc_btn.addActionListener(new ActionListener() {
            /**
             * Detecta cuando se presiona el boton calcular en la ventana
             * @param e
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                String Valor = Valor_field.getText();
                String Peso = Peso_field.getText();
                String impuestos = Impuesto_field.getText();

                if (validation(Valor) != false && validation(Peso) != false && validation(impuestos) != false){ //validacion de entradas tipo entero
                    String Monto = s_thread.Calculo(Valor, Peso, impuestos); //solicitud de calculo al servidor
                    s_thread.sendMessage("El cliente: " + username + " solicito calculo = " + Monto);//envia mensaje a cliente conectado por el terminal
                    Monto_field.setText(Monto);
                    s_thread.updateTexArea(Chat, "-Calculo solicitado con exito \n"+ "-Enviado con exito a terminal del otro cliente \n");//actualiza la ventana con el monto calculado

                }else{
                    s_thread.updateTexArea(Chat,"-Error, ingrese numeros enteros \n");
                }

            }
        });
    }

    /**
     * Valida que el string ingresado contenga solo enteros.
     * @param value String a validar.
     * @return valor booleano true en caso de contener solo enteros, false en caso de contener elemento no enteros.
     */
    public boolean validation(String value){
        int chars = value.length();//catidad de caracteres del string
        boolean judge = false; //valor de retorno

        for(int i = 0; i < chars; i++){ //validacion de cada caracter tipo entero
           if (value.charAt(i) >= '0' && value.charAt(i) <= '9'){
               judge = true;

           }else{ //encuentra caracter no entero, termina el ciclo
               judge = false;
               break;
           }
        }
        return judge;
    }

}