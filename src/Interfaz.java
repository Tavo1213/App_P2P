import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class Interfaz extends JFrame{
    private JTextField Port_field;
    private JButton Connect_btn;
    private JPanel Jpanel;
    private JTextField Username_field;
    private JButton Listen_btn;
    private JTextField Port_listen_field;
    private JLabel Message_area;

    Cliente p = new Cliente();

    public Interfaz(){
        setContentPane(Jpanel);
        setSize(800, 400);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        Socket sc = null;

        addWindowListener(new WindowAdapter() {

                @Override
                public void windowClosing(WindowEvent e){ //Evento de cierre de ventana
                    super.windowClosing(e);
                    p.flag = false;

                }
            });

        Connect_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

                    String port = Port_field.getText();
                    String username = Username_field.getText();

                    Hilo_server serverThread = new Hilo_server(port);
                    serverThread.start();
                    System.out.println("Connection started");

                    Listen_btn.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String port_listen = Port_listen_field.getText();

                            if (bufferedReader != null && username!= null && serverThread!= null && port_listen != null){
                                try{
                                    p.updateListeToPeers(bufferedReader, username, serverThread, port_listen);
                                    Chat vent2 = new Chat();
                                    vent2.Chat(serverThread, username);
                                    setVisible(false);

                                }catch (Exception i){
                                    System.out.println("Error");
                                }
                            }else System.out.println("Error");
                        }

                    });

                }catch (Exception i){
                    i.printStackTrace();
                }

            }
        });

    }


}
