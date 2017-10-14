package view;

import model.Estado;
import model.Transicao;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

/**
 * @author deniojunior
 */

public class TelaCriar extends JFrame implements ActionListener{
    private int numAlfa, numQ;
    private String alfa1, alfa2 = "";
    private ArrayList<Estado> estados;
    private Transicao[][] matriz;
    private boolean removiAlfa1 = false, removiAlfa2 = false;
    
    private JLabel jlbAlfabeto, jlbNumAlfa, jlbNumQ; 
    private JLabel jlbCirc1, jlbCirc2, jlbTraco, jlbSeta, jlbDesQ1, jlbDesQ2, jlbDesAlfa, jlbFinal1, jlbFinal2;
    private JComboBox jcbxAlfa, jcbxQ1, jcbxQ2;
    private JPanel jpnPainel;
    private JButton jbtCancel, jbtMinimizar, jbtAdd, jbtTabela;
    private JCheckBox jckbFinal1, jckbFinal2;
    
    //table
    private JTable tabela;
    private JScrollPane barraRolagem;
    private String[][] dados;
    private String[] titulo;
    private DefaultTableCellRenderer aEstados, aTransicoes;
    
    public TelaCriar(){
        super("Transições do Autômato");
        
        estados = new ArrayList<Estado>();
        
        jlbCirc1 = new JLabel("", new ImageIcon("./src/view/img/circulo1.png"), JLabel.CENTER);
        jlbCirc2 = new JLabel("", new ImageIcon("./src/view/img/circulo2.png"), JLabel.CENTER);
        jlbFinal1 = new JLabel("", new ImageIcon("./src/view/img/final1.gif"), JLabel.CENTER);
        jlbFinal2 = new JLabel("", new ImageIcon("./src/view/img/final2.gif"), JLabel.CENTER);
        jlbTraco = new JLabel("_____________________________________________");
        jlbSeta = new JLabel("", new ImageIcon("./src/view/img/seta.png"), JLabel.CENTER);
        jlbDesAlfa = new JLabel("");
        jlbDesQ1 = new JLabel("");
        jlbDesQ2 = new JLabel("");
        
        jlbAlfabeto = new JLabel();
        jlbNumAlfa = new JLabel();
        jlbNumQ = new JLabel();
        
        jbtAdd = new JButton("+ADD");
        jbtCancel = new JButton("Cancelar");
        jbtMinimizar = new JButton("Minimizar");
        jbtTabela = new JButton("Tabela");
        
        jcbxAlfa = new JComboBox();
        jcbxAlfa.addItem("Selecione");
        
        jcbxQ1 = new JComboBox();
        jcbxQ1.addItem("Selecione");
        
        jcbxQ2 = new JComboBox();
        jcbxQ2.addItem("Selecione");
        
        jckbFinal1 = new JCheckBox("Final");
        jckbFinal2 = new JCheckBox("Final");
        
        jpnPainel = new JPanel();
        
        setVisible(true);
        setBounds(450, 100, 500, 470);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        jpnPainel.setLayout(null);
    }
    
    public void ConfigTela(){
        Font fonte = new Font("Times New Roman", Font.PLAIN, 20);
        
        jlbNumAlfa.setBounds(20, 20, 100, 20);
        jlbNumAlfa.setText("|∑|  =  " + Integer.toString(numAlfa));
        jlbNumAlfa.setFont(fonte);
        jpnPainel.add(jlbNumAlfa);
        
        jlbAlfabeto.setBounds(205, 20, 100, 20);
            if(alfa2.equals("")){
                jlbAlfabeto.setText("∑  :  (" + alfa1 + ")");
            }
            else{
                jlbAlfabeto.setText("∑  :  (" + alfa1 + ", " + alfa2 + ")");
            }
        jlbAlfabeto.setFont(fonte);
        jpnPainel.add(jlbAlfabeto);
        
        jlbNumQ.setBounds(400, 20, 100, 20);
        jlbNumQ.setText("Q  =  " + Integer.toString(numQ));
        jlbNumQ.setFont(fonte);
        jpnPainel.add(jlbNumQ);
        
        jcbxAlfa.setBounds(190, 150, 110, 20);
        jcbxAlfa.addActionListener(this);
        jpnPainel.add(jcbxAlfa);
        
        Estado e;
        for (int i = 0; i < numQ; i++){
            e = new Estado();
            e.setNome("Q" + i);
            e.setId(i);
            estados.add(e);
            jcbxQ1.addItem(estados.get(i).getNome());
            jcbxQ2.addItem(estados.get(i).getNome());
        }
        
        jcbxQ1.setBounds(20, 150, 110, 20);
        jcbxQ1.addActionListener(this);
        jpnPainel.add(jcbxQ1);
        
        jcbxQ2.setBounds(370, 150, 110, 20);
        jcbxQ2.addActionListener(this);
        jpnPainel.add(jcbxQ2);
        
        //---------------------Desenho
        
        jlbCirc1.setBounds(30, 70, 50, 50);
        jlbCirc1.setVisible(false);
        jpnPainel.add(jlbCirc1);
        
        jlbFinal1.setBounds(30, 70, 50, 50);
        jlbFinal1.setVisible(false);
        jpnPainel.add(jlbFinal1);
        
        jlbCirc2.setBounds(410, 70, 50, 50);
        jlbCirc2.setVisible(false);
        jpnPainel.add(jlbCirc2);
        
        jlbFinal2.setBounds(410, 70, 50, 50);
        jlbFinal2.setVisible(false);
        jpnPainel.add(jlbFinal2);
        
        jlbTraco.setBounds(77, 78, 400, 20);
        jlbTraco.setVisible(false);
        jpnPainel.add(jlbTraco);
        
        jlbSeta.setBounds(373, 70, 50, 50);
        jlbSeta.setVisible(false);
        jpnPainel.add(jlbSeta);
        
        jlbDesAlfa.setBounds(230, 73, 50, 20);
        jlbDesAlfa.setText((String) jcbxAlfa.getSelectedItem());
        jlbDesAlfa.setVisible(false);
        jpnPainel.add(jlbDesAlfa);
        
        jlbDesQ1.setBounds(45, 85, 50, 20);
        jlbDesQ1.setText((String) jcbxQ1.getSelectedItem());
        jlbDesQ1.setVisible(false);
        jpnPainel.add(jlbDesQ1);
        
        jlbDesQ2.setBounds(425, 85, 50, 20);
        jlbDesQ2.setText((String) jcbxQ2.getSelectedItem());
        jlbDesQ2.setVisible(false);
        jpnPainel.add(jlbDesQ2);
        
        //---------------------Desenho
        
        jbtAdd.setBounds(205, 190, 80, 25);
        jbtAdd.addActionListener(this);
        jpnPainel.add(jbtAdd);
        
        jbtCancel.setBounds(20, 405, 110, 25);
        jbtCancel.addActionListener(this);
        jpnPainel.add(jbtCancel);
        
        jbtMinimizar.setBounds(380, 405, 110, 25);
        jbtMinimizar.addActionListener(this);
        jpnPainel.add(jbtMinimizar);
        
        jbtTabela.setBounds(200, 405, 110, 25);
        jbtTabela.addActionListener(this);
        jpnPainel.add(jbtTabela);
        
        jckbFinal1.setBounds(15, 180, 80, 25);
        jckbFinal1.addActionListener(this);
        jpnPainel.add(jckbFinal1);
        
        jckbFinal2.setBounds(365, 180, 80, 25);
        jckbFinal2.addActionListener(this);
        jpnPainel.add(jckbFinal2);
        
        //table
        dados = new String[numQ][numQ+1];
        titulo = new String[numQ+1];
        
        titulo[0] = "";
        
        for (int i = 1; i <= numQ; i++){
            titulo[i] = estados.get(i-1).getNome();
            dados[i-1][0] = estados.get(i-1).getNome();
        }
        
        ConfigTable();
        //table
        
        add(jpnPainel);
    }
    
    public void ConfigTable(){
        //table
        
        for (int i = 0; i < numQ; i++){
            for (int j = 1; j <= numQ; j++){
                if (matriz[i][j-1].t2 != ""){
                    dados[i][j] = matriz[i][j-1].t1+", "+matriz[i][j-1].t2;
                }else{
                    dados[i][j] = matriz[i][j-1].t1;
                }
            }
        }
        
        tabela = new JTable(dados, titulo);
        
        aEstados = new DefaultTableCellRenderer(){
            public void setValue(Object value){
                setBackground(new Color(238, 238, 238));
                setForeground(Color.BLACK);
                setHorizontalAlignment(JLabel.CENTER);
                super.setValue(value);
            }
        };
        
        aTransicoes = new DefaultTableCellRenderer(){
          public void setValue(Object value){
              setHorizontalAlignment(JLabel.CENTER);
              super.setValue(value);
          }  
        };
        
        tabela.setEnabled(false);
        
        barraRolagem = new JScrollPane(tabela);
        barraRolagem.setVisible(true);
        
        int tamanhoX = (int) (25 * (numQ*2));
       
        if (tamanhoX > 460){
            tamanhoX = 460;
        }
        
        int tamanhoY = (int) (22 + (8 * (numQ*2)));
        
        if (tamanhoY > 150){
            tamanhoY = 150;
        }
        
        int localX = 245; //220
        int localY = 286; //280
        
        for(int i = 0; i < numQ; i++){
            localX -= 25;
            localY -= 6;
        }
        
        if (localX < 20){
            localX = 20;
        }
        
        if(localY < 230){
            localY = 230;
        }
        
        barraRolagem.setBounds(localX, localY, tamanhoX, tamanhoY);
        jpnPainel.add(barraRolagem); 
        
        TableColumn tc = tabela.getColumn("");
        tc.setCellRenderer(aEstados);
        
        for (int i = 0; i < numQ; i++){
            tabela.getColumn("Q"+i).setCellRenderer(aTransicoes);
        }
        
        //table
        add(jpnPainel);
    }
    
    public void setNumAlfa(int numAlfa){
        this.numAlfa = numAlfa;
    }
    
    public int getNumAlfa(){
        return (numAlfa);
    }
    
    public void setNumQ(int numQ){
        this.numQ = numQ;
        
        matriz = new Transicao[this.numQ][this.numQ];
        
        for(int i = 0; i < numQ; i++){
            for(int j = 0; j < numQ; j++){
                matriz[i][j] = new Transicao();
                matriz[i][j].t1 = "-";
                matriz[i][j].t2 = "";
            }
        }
    }
    
    public int getNumQ(){
        return (numQ);
    }
    
    public void setAlfa1(String alfa1){
        this.alfa1 = alfa1;
    }
    
    public String getAlfa1(){
        return (alfa1);
    }
    
    public void setAlfa2(String alfa2){
        this.alfa2 = alfa2;
    }
    
    public String getAlfa2(){
        return (alfa2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jcbxQ1){
            
            jckbFinal1.setEnabled(true);
            jckbFinal1.setSelected(false);
            jlbFinal1.setVisible(false);
            
            jcbxAlfa.removeItem(getAlfa1());
            jcbxAlfa.removeItem(getAlfa2());
            
            if("Selecione" == (String)jcbxQ1.getSelectedItem()){
                jlbCirc1.setVisible(false);
                jlbDesQ1.setVisible(false);
                jlbFinal1.setVisible(false);
                jckbFinal1.setSelected(false);
            }
            else{
                removiAlfa1 = false;
                removiAlfa2 = false;
                
                String aux = (String)jcbxQ1.getSelectedItem();
                
                for(int i = 0; i < estados.size(); i++){
                    if(estados.get(i).getNome().equals(aux)){
                        if(estados.get(i).geteFinal()){
                            jckbFinal1.setSelected(true);
                            jckbFinal1.setEnabled(false);
                            jlbFinal1.setVisible(true);
                        }
                    }
                }
                
                String x = "";
                x = aux.substring(1);
                
                int i = Integer.parseInt(x);
                
                for(int j = 0; j < numQ; j++){
                    if(matriz[i][j].t1.equals(alfa1) || matriz[i][j].t2.equals(alfa1)){
                        removiAlfa1 = true;
                    }
                    if(matriz[i][j].t1.equals(alfa2) || matriz[i][j].t2.equals(alfa2)){
                        removiAlfa2 = true;
                    }
                }
                
                if (removiAlfa1){
                    jcbxAlfa.removeItem(getAlfa1());
                }
                else{
                    jcbxAlfa.addItem(getAlfa1());
                }
                
                if (removiAlfa2){
                    jcbxAlfa.removeItem(getAlfa2());
                }
                else{
                    jcbxAlfa.addItem(getAlfa2());
                }
                
                if ((removiAlfa1) && (removiAlfa2)){
                    JOptionPane.showMessageDialog(null, "Todos os estados partindo desta origem já foram usados", "Erro!", JOptionPane.OK_OPTION);
                    
                    jcbxAlfa.setSelectedItem("Selecione");
                    jcbxQ1.setSelectedItem("Selecione");
                    jcbxQ2.setSelectedItem("Selecione");
                    jcbxAlfa.setSelectedItem("Selecione");
                    jckbFinal1.setSelected(false);
                    jckbFinal2.setSelected(false);
                    jlbCirc1.setVisible(false);
                    jlbCirc2.setVisible(false);
                    jlbDesAlfa.setVisible(false);
                    jlbDesQ1.setVisible(false);
                    jlbDesQ2.setVisible(false);
                    jlbFinal1.setVisible(false);
                    jlbFinal2.setVisible(false);
                    jlbTraco.setVisible(false);
                    jlbSeta.setVisible(false);
                }
                
                if (!("Selecione".equals(jcbxQ1.getSelectedItem()))){

                    jlbDesQ1.setText((String)jcbxQ1.getSelectedItem());
                    jlbCirc1.setVisible(true);
                    jlbDesQ1.setVisible(true);

                    if(jckbFinal1.isSelected()){
                        jlbFinal1.setVisible(true);
                    }
                }
            }
        }
        else if(e.getSource() == jcbxQ2){
            
            jckbFinal2.setEnabled(true);
            jckbFinal2.setSelected(false);
            jlbFinal2.setVisible(false);
            
            if("Selecione" == (String)jcbxQ2.getSelectedItem()){
                jlbCirc2.setVisible(false);
                jlbDesQ2.setVisible(false);
                jlbFinal2.setVisible(false);
                jckbFinal2.setSelected(false);
            }
            else{
                jlbDesQ2.setText((String)jcbxQ2.getSelectedItem());
                jlbCirc2.setVisible(true);
                jlbDesQ2.setVisible(true);
                
                if(jckbFinal2.isSelected()){
                    jlbFinal2.setVisible(true);
                }
            }
            
            String aux = (String)jcbxQ2.getSelectedItem();
                
                for(int i = 0; i < estados.size(); i++){
                    if(estados.get(i).getNome().equals(aux)){
                        if(estados.get(i).geteFinal()){
                            jckbFinal2.setSelected(true);
                            jckbFinal2.setEnabled(false);
                            jlbFinal2.setVisible(true);
                        }
                    }
                }
        }
        else if(e.getSource() == jcbxAlfa){
            if("Selecione" == (String)jcbxAlfa.getSelectedItem()){
                jlbTraco.setVisible(false);
                jlbSeta.setVisible(false);
                jlbDesAlfa.setVisible(false);
            }
            else{
                jlbDesAlfa.setText((String)jcbxAlfa.getSelectedItem());
                jlbTraco.setVisible(true);
                jlbSeta.setVisible(true);
                jlbDesAlfa.setVisible(true);
            }
        }   
        else if(e.getSource() == jckbFinal1){
            if("Selecione" != (String)jcbxQ1.getSelectedItem()){ 
                if (jckbFinal1.isSelected()){
                    jlbFinal1.setVisible(true);
                }
                else{
                    jlbFinal1.setVisible(false);
                }
            }
        }
        else if(e.getSource() == jckbFinal2){
            if("Selecione" != (String)jcbxQ2.getSelectedItem()){
                if (jckbFinal2.isSelected()){
                    jlbFinal2.setVisible(true);
                }
                else{
                    jlbFinal2.setVisible(false);
                }
            }
        }
        else if(e.getSource() == jbtCancel){
            int op = JOptionPane.showConfirmDialog(null, "Deseja realmente sair?", "Sair", JOptionPane.YES_NO_OPTION);
            
            if (op == 0){
                this.dispose();
            }
        }
        else if(e.getSource() == jbtTabela){
            TelaVisualizarAutomato telaVA = new TelaVisualizarAutomato();
            telaVA.ConfigTela(numAlfa, numQ, estados, matriz);
        }
        else if(e.getSource() == jbtAdd){
            if("Selecione".equals((String)jcbxAlfa.getSelectedItem())||
                    "Selecione".equals((String)jcbxQ1.getSelectedItem())||
                        "Selecione".equals((String)jcbxQ2.getSelectedItem())){
                
                JOptionPane.showMessageDialog(null, "Selecione todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
            }else{   
                boolean erro = false;
                //Adiciona na matriz
                for (int i = 0; i < numQ; i++){
                    if (estados.get(i).getNome().equals((String)jcbxQ1.getSelectedItem())){
                        for (int j = 0; j < numQ; j++){
                            if (estados.get(j).getNome().equals((String)jcbxQ2.getSelectedItem())){
                                
                                if(!(matriz[i][j].t1.equals((String)jcbxAlfa.getSelectedItem()))){
                                        
                                    if(jckbFinal1.isSelected()){
                                        estados.get(i).seteFinal(true);
                                    }

                                    if(jckbFinal2.isSelected()){
                                        estados.get(j).seteFinal(true);
                                    }

                                    estados.get(i).setId(i);
                                    estados.get(j).setId(j);

                                    if(matriz[i][j].t1 != "-"){
                                        matriz[i][j].t2 = (String)jcbxAlfa.getSelectedItem();
                                    }else{
                                        matriz[i][j].t1 = (String)jcbxAlfa.getSelectedItem();
                                    }
                                }else{
                                    erro = true;
                                }
                            }
                        }
                    }
                }
                
                if (erro){
                        JOptionPane.showMessageDialog(null, "O símbolo selecionado já está contido no estado.\nLembre-se, o autômato deve ser determinístico!", "Erro", JOptionPane.ERROR_MESSAGE);
                }else{
                
                    ConfigTable();
                    
                    //Zera os componentes
                    jcbxQ1.setSelectedItem("Selecione");
                    jcbxQ2.setSelectedItem("Selecione");
                    jcbxAlfa.setSelectedItem("Selecione");
                    jckbFinal1.setSelected(false);
                    jckbFinal2.setSelected(false);
                    jlbCirc1.setVisible(false);
                    jlbCirc2.setVisible(false);
                    jlbDesAlfa.setVisible(false);
                    jlbDesQ1.setVisible(false);
                    jlbDesQ2.setVisible(false);
                    jlbFinal1.setVisible(false);
                    jlbFinal2.setVisible(false);
                    jlbTraco.setVisible(false);
                    jlbSeta.setVisible(false);
                }
                jcbxAlfa.removeAllItems();
                jcbxAlfa.addItem("Selecione");
            }
        }
        else if(e.getSource() == jbtMinimizar){
            //Verificar os 3 requisitos básicos
            
           /* if (erroDeterministico()){
                JOptionPane.showMessageDialog(null, "O Autômato não é Determinístico!", "Erro", JOptionPane.ERROR_MESSAGE);
            }*/
            
            if(erroTransicaoTotal()){
                JOptionPane.showMessageDialog(null, "A função de transição não é total!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
            
            else if (erroAutomatoConexao()){
                JOptionPane.showMessageDialog(null, "O Autômato não é conexo!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
            
            else{
                JOptionPane.showMessageDialog(null, "Autômato criado com sucesso!", "Concluído", JOptionPane.INFORMATION_MESSAGE);
                //this.dispose();
                TelaAutomatoFinal af = new TelaAutomatoFinal();
                af.setDados(numAlfa, numQ, estados, matriz, alfa1, alfa2);
                af.ConfigTela();
            }
        }
    }
    
  /*  public boolean erroDeterministico(){
            boolean erro = false;
            int contAlfa1 = 0;
            int contAlfa2 = 0;
            
            for (int i = 0; i < numQ; i++){
                contAlfa1 = 0;
                contAlfa2 = 0;
                
                for(int j = 0; j < numQ; j++){
                    if ((matriz[i][j].t1.equals(alfa1))||((matriz[i][j].t2.equals(alfa1)))){
                        contAlfa1 += 1;
                    }
                    if ((matriz[i][j].t1.equals(alfa2))||((matriz[i][j].t2.equals(alfa2)))){
                        contAlfa2 += 1;
                    }   
                }
                
                if((contAlfa1 > 1)||(contAlfa2 > 1)){
                     erro = true;
                 }
            }
        return(erro);
    }*/
    
    public boolean erroTransicaoTotal(){
            boolean erro = false;
            int contAlfa = 0;
            
            if(alfa2 != ""){
                for (int i = 0; i < numQ; i++){
                    contAlfa = 0;

                    for(int j = 0; j < numQ; j++){
                        if (matriz[i][j].t1 != "-"){
                            contAlfa += 1;
                        }

                        if(matriz[i][j].t2 != ""){
                            contAlfa += 1;
                        }
                    }

                    if(contAlfa < 2){
                            erro = true;    
                    }
                }
            }else{
                for (int i = 0; i < numQ; i++){
                    contAlfa = 0;

                    for(int j = 0; j < numQ; j++){
                        if (matriz[i][j].t1 != "-"){
                            contAlfa += 1;
                        }
                    }

                    if(contAlfa < 1){
                            erro = true;    
                    }
                }
            }
        return(erro);
    }
    
    public boolean erroAutomatoConexao(){
        
        ArrayList<Integer> estadosA = new ArrayList<Integer>();
        int i = 0;
        boolean aux = true;
        
        estadosA.add(0); //Insere o Q0 que é o inicial
        
        while((estadosA.size() != numQ)&&(i < numQ)){
            
            for(int j = 0; j < numQ; j++){
                if(matriz[i][j].t1 != "-"){
                    
                    if(!(estadosA.contains(j))){
                        estadosA.add(j);
                    }
                }
            } 
            
            if(estadosA.size() == numQ){    
                aux = false;
                break;
            }
            
            i++;
        }
        
        return(aux);
    }
}