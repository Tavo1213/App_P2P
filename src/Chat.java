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
    public JLabel Monto_field;
    public JLabel Client_request;

    public void Chat(Hilo_server serverThread, String username){
        Hilo_server s_thread = serverThread;
        setContentPane(Jpanel);
        setTitle(username);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Titulo.setText(username);

        Cliente p = new Cliente();

        Calc_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Valor = Valor_field.getText();
                String Peso = Peso_field.getText();
                String impuestos = Impuesto_field.getText();
                Paquete pack = new Paquete();
                /*pack.setValor(Valor);
                pack.setPeso(Peso);
                pack.setImpuesto(impuestos);*/

                if (Valor != null && Peso != null && impuestos!=null){
                    String Monto = s_thread.Calculo(Valor, Peso, impuestos);
                    s_thread.sendMessage(Monto);
                    Monto_field.setText(Monto);


                }

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
