import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Chat extends JFrame{
    private JPanel Jpanel;
    private JTextField Message_field;
    private JButton Send_btn;
    private JLabel Titulo;
    private JTextField Valor_field;
    private JTextField Peso_field;
    private JTextField Impuesto_field;
    private JButton Calc_btn;
    private JLabel Monto_field;

    public void Chat(Hilo_server serverThread, String username){
        Hilo_server s_thread = serverThread;
        setContentPane(Jpanel);
        setTitle(username);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Titulo.setText(username);

        Cliente p = new Cliente();

        Calc_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                serverThread.sendMessage("a");
            }
        });
    }

}

class Paquete{
    private String Valor, peso, impuesto;

    public String getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(String impuesto) {
        this.impuesto = impuesto;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getValor() {
        return Valor;
    }

    public void setValor(String valor) {
        Valor = valor;
    }
}