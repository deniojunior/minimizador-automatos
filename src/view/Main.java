package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author deniojunior
 */
public class Main extends JFrame implements ActionListener{
    private JButton jbtCriar, jbtSair;
    private JPanel jpnPainel;
    
    public Main(){
        super("Minimizador de Autômatos Finitos");
        
        jbtCriar = new JButton("Criar Autômato");
        jbtSair = new JButton("Sair");
        
        jpnPainel = new JPanel();
        
        setBounds(500, 200, 400, 210);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        jpnPainel.setLayout(null);
    }
    
    public void ConfigTela(){
        jbtCriar.setBounds(95, 40, 200, 30);
        jbtCriar.addActionListener(this);
        jpnPainel.add(jbtCriar);
        
        jbtSair.setBounds(95, 120, 200, 30);
        jbtSair.addActionListener(this);
        jpnPainel.add(jbtSair);
        
        this.setResizable(false);
        
        add(jpnPainel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       if (e.getSource() == jbtSair){
           this.dispose();
       }
       
       else if (e.getSource() == jbtCriar){
           TelaRequisitosCriar telaR = new TelaRequisitosCriar();
           telaR.ConfigTela();
       }

    }
    
    public static void main(String[] args) {
        Main tPrincipal = new Main();
        tPrincipal.ConfigTela();
    }
}
