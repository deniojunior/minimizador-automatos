package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author deniojunior
 */
public class TelaRequisitosCriar extends JFrame implements ActionListener{
    private JTextField jtxfAlfabeto1, jtxfAlfabeto2, jtxfEstados;
    private JButton jbtOk, jbtCancel;
    private JLabel jlbTamanhoE, jlbAlfabeto, jlbEstados;
    private JPanel jpnPainel;
    private JRadioButton jrbE1, jrbE2;
    private ButtonGroup bt;
    
    public TelaRequisitosCriar(){
        super("Requisitos");
        jlbTamanhoE = new JLabel("Tamanho do alfabeto:");
        jlbAlfabeto = new JLabel("Insira o Alfabeto:");
        jlbEstados = new JLabel("Número de Estados:");
        
        jrbE1 = new JRadioButton("1");
        jrbE2 = new JRadioButton("2", true);
        
        bt = new ButtonGroup();
        
        jtxfEstados = new JTextField();
        jtxfAlfabeto1 = new JTextField();
        jtxfAlfabeto2 = new JTextField();
        jtxfAlfabeto2.setEnabled(true);
         
        jbtOk = new JButton("OK");
        jbtCancel = new JButton("Cancelar");
        
        jpnPainel = new JPanel();
        
        setBounds(550, 200, 300, 210);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        jpnPainel.setLayout(null);
    }
    
    public void ConfigTela(){
        jlbTamanhoE.setBounds(10, 10, 200, 20);
        jpnPainel.add(jlbTamanhoE);
        
        jrbE1.setBounds(180, 10, 50, 20);
        jrbE2.setBounds(250, 10, 50, 20);
        bt.add(jrbE1);
        bt.add(jrbE2);
        jrbE1.addActionListener(this);
        jrbE2.addActionListener(this);
        
        jpnPainel.add(jrbE1);
        jpnPainel.add(jrbE2);
        
        jtxfAlfabeto1.setBounds(165, 50, 50, 20);
        jpnPainel.add(jtxfAlfabeto1);
        
        jtxfAlfabeto2.setBounds(235, 50, 50, 20);
        jpnPainel.add(jtxfAlfabeto2);
        
        jlbAlfabeto.setBounds(10, 50, 200, 20);
        jpnPainel.add(jlbAlfabeto);
        
        jlbEstados.setBounds(10, 95, 200, 20);
        jpnPainel.add(jlbEstados);
        
        jtxfEstados.setBounds(165, 95, 50, 20);
        jpnPainel.add(jtxfEstados);
        
        jbtOk.setBounds(60, 140, 70, 25);
        jbtOk.addActionListener(this);
        jpnPainel.add(jbtOk);
        
        jbtCancel.setBounds(160, 140, 100, 25);
        jbtCancel.addActionListener(this);
        jpnPainel.add(jbtCancel);
        
        add(jpnPainel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (jrbE1.isSelected()){
            //jtxfAlfabeto2.setEnabled(false);
            //jtxfAlfabeto2.setText("");
            JOptionPane.showMessageDialog(null, "O Professor mudou de ideia! Voltando para |∑| = 2", "Voltando...", JOptionPane.OK_OPTION);
            jrbE2.setSelected(true);
        }
        else if (jrbE2.isSelected()){
            jtxfAlfabeto2.setEnabled(true);
        }
        
        if (e.getSource() == jbtOk){
            if(!temErro()){
                //pega o alfabeto e o número de estados e armazena em suas respctivas variáveis
                
                this.dispose(); //Fecha essa tela
                
                TelaCriar telaC = new TelaCriar();
                
                telaC.setAlfa1(jtxfAlfabeto1.getText());
                
                if (jrbE2.isSelected()){
                    telaC.setNumAlfa(2);
                    telaC.setAlfa2(jtxfAlfabeto2.getText());
                }
                else{
                    telaC.setNumAlfa(1);
                }
                    
                telaC.setNumQ(Integer.parseInt(jtxfEstados.getText()));
                
                telaC.ConfigTela();
            }
        }
        else if(e.getSource() == jbtCancel){
            this.dispose();
        }
    }
    
    public boolean temErro(){
        boolean e = false;
        
        if (jtxfAlfabeto1.getText().isEmpty()){
                e = true;
                JOptionPane.showMessageDialog(null, "Preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
            }

            if (jrbE2.isSelected()){
                if (!(jtxfAlfabeto1.getText().isEmpty())){
                    if (jtxfAlfabeto2.getText().isEmpty()){
                        e = true;
                        JOptionPane.showMessageDialog(null, "Preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
            
            if (jtxfEstados.getText().isEmpty()){
                e = true;
                JOptionPane.showMessageDialog(null, "Preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
            
            if ((Integer.parseInt((String) jtxfEstados.getText())) <= 0){
                e = true;
                JOptionPane.showMessageDialog(null, "O número de estados tem que ser maior que zero!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
            
            if (jtxfAlfabeto1.getText().equals(jtxfAlfabeto2.getText())){
                e = true;
                JOptionPane.showMessageDialog(null, "Os alfabetos não podem ser iguais!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
            
            return(e);
    }
}