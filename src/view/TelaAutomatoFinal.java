package view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import model.Estado;
import model.Transicao;

/**
 * @author deniojunior
 */
public class TelaAutomatoFinal extends JFrame{
    
    private JPanel painel;
    private JTable tabela;
    private JScrollPane barraRolagem;
    private JLabel jlbMinimo;
    
    private String[][] dados;
    private String[] titulo;
    
    private DefaultTableCellRenderer aEstados, aTransicoes;
    
    private int numAlfa, numQ;
    private String alfa1, alfa2 = "";
    private ArrayList<Estado> estados;
    private Transicao[][] matriz;
    
    public TelaAutomatoFinal(){
        super("Autômato Mínimo");
        painel = new JPanel();
        jlbMinimo = new JLabel("Autômato Mínimo:");
        //tabela = new JTable();
        
        setBounds(450, 200, 200, 300);
        setVisible(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        painel.setLayout(new GridLayout(2,1));
    }
    
    public void setDados(int numAlfa, int numQ, ArrayList<Estado> estados, Transicao[][] matriz, String alfa1, String alfa2){
        this.numAlfa = numAlfa;
        this.numQ = numQ;
        this.estados = estados;
        this.matriz = matriz;
        this.alfa1 = alfa1;
        this.alfa2 = alfa2;
    }
    
    public void ConfigTela(){
        //dados = matriz
        
        //Cria e preenche tabla de equialêntes/distinguíveis
        //---------------------------------------------------
        String[][] tabelaED = new String[estados.size()-1][estados.size()-1];
        
        String aux = "";
        
        for(int i = 1; i < estados.size(); i++){
            aux = aux + "\n|";
           for(int j = 0; j < i; j++){
               if(estados.get(i).geteFinal() == estados.get(j).geteFinal()){
                   tabelaED[i-1][j] = "O";
                   aux = aux + tabelaED[i-1][j] + "|";
               }else{
                   tabelaED[i-1][j] = "X";
                   aux = aux + tabelaED[i-1][j] + "|";;
               }
           }
        }
        //---------------------------------------------------
        
        JOptionPane.showMessageDialog(null, aux, "Tabela de Equivalência Inicial", JOptionPane.DEFAULT_OPTION);
                      
        //Para T = 1
        //E T = 2
        boolean t1Equivalente = false, t2Equivalente = false;
        
        for(int i = 1; i < estados.size(); i++){
           for(int j = 0; j < i; j++){
     
               if(tabelaED[i-1][j].equals("O")){ //para o equivalente na tabelaED
                    
                    t1Equivalente = verificaT1(i, j);
                    
                    if(t1Equivalente){
                        t2Equivalente = verificaT2(i, j);
                        if(t2Equivalente){
                            tabelaED[i-1][j] = "O";
                        }else{
                            tabelaED[i-1][j] = "X";
                        }
                    }
                    else{
                        tabelaED[i-1][j] = "X";
                    }
               }
           }
        }
        
        //----------------------------------------------------------------------
        aux = "";
        
        for(int i = 1; i < estados.size(); i++){
            aux = aux + "\n|";
           for(int j = 0; j < i; j++){
               aux = aux + tabelaED[i-1][j] + "|";
           }
        }
        
        JOptionPane.showMessageDialog(null, aux, "Tabela de Equivalência Final", JOptionPane.DEFAULT_OPTION);
        
        
        //Junta os estados para a minimização
        ArrayList<Estado> estadosAux = new ArrayList<>();
        int tamanhoEstados = estados.size();
        
        for(int i = 1; i < tamanhoEstados; i++){
           for(int j = 0; j < i; j++){
               
               if(tabelaED[i-1][j].equals("O")){
                   Estado e = new Estado();
                   e.setId(estados.size()+1);
                   e.setNome(estados.get(j).getNome() + estados.get(i).getNome());
                   
                   if(estados.get(i).geteFinal() || estados.get(j).geteFinal()){
                       e.seteFinal(true);
                   }else{
                       e.seteFinal(false);
                   }
                   
                   if(!(estadosAux.isEmpty())){
                        boolean ligouEstados = false;
                        for(int k = 0; k < estadosAux.size(); k++){

                            if(estadosAux.get(k).getNome().contains("Q"+i)){ //se contem o i e naõ contem o j adiciona o j
                                if(!(estadosAux.get(k).getNome().contains("Q"+j))){
                                    estadosAux.get(k).setNome(estadosAux.get(k).getNome()+"Q"+j);
                                    ligouEstados = true;
                                }
                            }
                            else if(estadosAux.get(k).getNome().contains("Q"+j)){ //se contem o j e não contem o i adiciona o i
                                if(!(estadosAux.get(k).getNome().contains("Q"+i))){
                                    estadosAux.get(k).setNome(estadosAux.get(k).getNome()+"Q"+i);
                                    ligouEstados = true;
                                }
                            }
                        }
                        
                        for(int k = 0; k < estadosAux.size(); k++){
                            if (estadosAux.get(k).getNome().contains("Q"+i) && (estadosAux.get(k).getNome().contains("Q"+j))){
                                ligouEstados = true; //se os dois já esticverem contidos em outro estado, não adiciona um novo
                            }
                        }
                        
                        if(!(ligouEstados)){
                            estadosAux.add(e);
                        }
                   }else{
                       estadosAux.add(e);
                   }
               }
           }
        }
        
        ArrayList<Estado> estadosAuxAux = new ArrayList<>();
        for(int i = 1; i < tamanhoEstados; i++){
           for(int j = 0; j < i; j++){
                if(tabelaED[i-1][j].equals("O")){
                    Estado e = new Estado();
                    
                    e = estados.get(i);
                    estadosAuxAux.add(e);
                    
                    e = estados.get(j);
                    estadosAuxAux.add(e);
                }
           }
        }
        
        //Remove os estados equivalentes
        for(Estado est : estadosAuxAux){
            if(estados.contains(est)){
                estados.remove(est);
            }
        }   
        
        //Adiciona os novos estados
        for(int i = 0; i < estadosAux.size(); i++){
            estados.add(estadosAux.get(i));
        }
        
        //tirar depois ---------------------------------------------------------
        aux = "";
        for(int j = 0; j < estados.size(); j++){
            aux = aux + estados.get(j).getNome() + "\n";
        }
        JOptionPane.showMessageDialog(null, aux);
        //----------------------------------------------------------------------
        
        Transicao[][] matrizFinal = new Transicao[estados.size()][estados.size()];
        
        for(int i = 0; i < estados.size(); i++){
            for(int j = 0; j < estados.size(); j++){
                matrizFinal[i][j] = new Transicao();
                matrizFinal[i][j].t1 = "-";
                matrizFinal[i][j].t2 = "";
            }
        }
        
        for(int i = 0; i < numQ; i++){
            for(int j = 0; j < numQ; j++){
                
                for(int d = 0; d < estados.size(); d++){
                    
                    if(estados.get(d).getNome().contains("Q"+i)){

                        if(matriz[i][j].t1 == alfa1 || matriz[i][j].t2 == alfa1){  

                            for(int k = 0; k < estados.size(); k++){
                                if(estados.get(k).getNome().contains("Q"+j)){
                                    if(matrizFinal[d][k].t1 == "-"){  
                                        matrizFinal[d][k].t1 = alfa1;
                                        break;
                                    }else if(matrizFinal[d][k].t1 == alfa1){
                                        //Faz Nada!
                                    }
                                    else{
                                        matrizFinal[d][k].t2 = alfa1;
                                        break;
                                    }
                                }
                            }
                        }

                        if(matriz[i][j].t1 == alfa2 || matriz[i][j].t2 == alfa2){

                            for(int k = 0; k < estados.size(); k++){
                                if(estados.get(k).getNome().contains("Q"+j)){
                                    if(matrizFinal[d][k].t1 == "-"){  
                                        matrizFinal[d][k].t1 = alfa2;
                                        break;
                                    }else if(matrizFinal[d][k].t1 == alfa2){
                                        //Faz Nada!
                                    }
                                    else{
                                        matrizFinal[d][k].t2 = alfa2;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        
        //----------------------------------------------------------------------
        jlbMinimo.setBounds(5, 2, 120, 20);
        painel.add(jlbMinimo);
        
        dados = new String[estados.size()][estados.size()+1];
        titulo = new String[estados.size()+1];
        
        titulo[0] = "";
        
        for (int i = 1; i <= estados.size(); i++){
            if(estados.get(i-1).geteFinal()){
                titulo[i] = estados.get(i-1).getNome()+"*";
                dados[i-1][0] = estados.get(i-1).getNome()+"*";
            }else{
                titulo[i] = estados.get(i-1).getNome();
                dados[i-1][0] = estados.get(i-1).getNome();
            }
        }
        
       
        for (int i = 0; i < estados.size(); i++){
            for (int j = 1; j <= estados.size(); j++){
                if (matrizFinal[i][j-1].t2 != ""){
                    dados[i][j] = matrizFinal[i][j-1].t1+", "+matrizFinal[i][j-1].t2;
                }else{
                    dados[i][j] = matrizFinal[i][j-1].t1;
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
        painel.add(barraRolagem);
        
        TableColumn tc = tabela.getColumn("");
        tc.setCellRenderer(aEstados);
        
        for (int i = 0; i < estados.size(); i++){
            if(estados.get(i).geteFinal()){
                tabela.getColumn(estados.get(i).getNome()+"*").setCellRenderer(aTransicoes);
            }else{
                tabela.getColumn(estados.get(i).getNome()).setCellRenderer(aTransicoes);
            }
        }
        
        getContentPane().add(painel);
        
        int tamanhoX = (int) (25 * (numQ*2));
        int tamanhoY = (int) (55 + (52 + (8 * (numQ*2))));
        
        if(tamanhoX < 150){
            tamanhoX = 150;
        }
        
        setBounds(450, 200, tamanhoX, tamanhoY);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    public boolean verificaT1(int i, int j){
        String bin1 = "", bin2 = "";
        
        for(int k = 0; k < numQ; k++){
             if(matriz[i][k].t1 == alfa1 || matriz[i][k].t2 == alfa1){

                 if(estados.get(k).geteFinal()){
                     bin1 = bin1 + "1";
                 }else{
                     bin1 = bin1 + "0";
                 }
             }
        }   
         for(int k = 0; k < numQ; k++){
             if(matriz[i][k].t1 == alfa2 || matriz[i][k].t2 == alfa2){

                 if(estados.get(k).geteFinal()){
                     bin1 = bin1 + "1";
                 }else{
                     bin1 = bin1 + "0";
                 }
             }
         }
         for(int k = 0; k < numQ; k++){
             if(matriz[j][k].t1 == alfa1 || matriz[j][k].t2 == alfa1){

                 if(estados.get(k).geteFinal()){
                     bin2 = bin2 + "1";
                 }else{
                     bin2 = bin2 + "0";
                 }
             }
         }
         for(int k = 0; k < numQ; k++){
             if(matriz[j][k].t1 == alfa2 || matriz[j][k].t2 == alfa2){

                 if(estados.get(k).geteFinal()){
                     bin2 = bin2 + "1";
                 }else{
                     bin2 = bin2 + "0";
                 }
             }
         }
         
         if(bin1.equals(bin2)){
             return(true);
         }else{
             return(false);
         }
    }
    
    public boolean verificaT2(int i, int j){
        String bin1 = "", bin2 = "";
        
        for(int k = 0; k < numQ; k++){          
            if(matriz[i][k].t1 == alfa1 || matriz[i][k].t2 == alfa1){                       
                for(int t = 0; t < numQ; t++){       
                    if(matriz[k][t].t1 == alfa1 || matriz[k][t].t2 == alfa1){
                         if(estados.get(t).geteFinal()){
                              bin1 = bin1 + "1";
                          }else{
                              bin1 = bin1 + "0";
                          }
                    }
              
                    if(matriz[k][t].t1 == alfa2 || matriz[k][t].t2 == alfa2){
                         if(estados.get(t).geteFinal()){
                              bin1 = bin1 + "1";
                          }else{
                              bin1 = bin1 + "0";
                          }
                    }
                }     
            }
        }
        for(int k = 0; k < numQ; k++){  
            if(matriz[i][k].t1 == alfa2 || matriz[i][k].t2 == alfa2){
                for(int t = 0; t < numQ; t++){
                    if(matriz[k][t].t1 == alfa1 || matriz[k][t].t2 == alfa1){ 
                        if(estados.get(t).geteFinal()){
                             bin1 = bin1 + "1";
                        }else{
                             bin1 = bin1 + "0";
                        }
                    }

                    if(matriz[k][t].t1 == alfa2 || matriz[k][t].t2 == alfa2){
                         if(estados.get(t).geteFinal()){
                              bin1 = bin1 + "1";
                          }else{
                              bin1 = bin1 + "0";
                          }
                    }
                }     
            }
        }
        for(int k = 0; k < numQ; k++){  
            if(matriz[j][k].t1 == alfa1 || matriz[j][k].t2 == alfa1){
                for(int t = 0; t < numQ; t++){
                    if(matriz[k][t].t1 == alfa1 || matriz[k][t].t2 == alfa1){
                         if(estados.get(t).geteFinal()){
                             bin2 = bin2 + "1";
                         }else{
                             bin2 = bin2 + "0";
                         }
                    }

                    if(matriz[k][t].t1 == alfa2 || matriz[k][t].t2 == alfa2){
                         if(estados.get(t).geteFinal()){
                              bin2 = bin2 + "1";
                          }else{
                              bin2 = bin2 + "0";
                          }
                    }
                }
            }
        }
        for(int k = 0; k < numQ; k++){  
            if(matriz[j][k].t1 == alfa2 || matriz[j][k].t2 == alfa2){
                for(int t = 0; t < numQ; t++){
                    if(matriz[k][t].t1 == alfa1 || matriz[k][t].t2 == alfa1){
                         if(estados.get(t).geteFinal()){
                             bin2 = bin2 + "1";
                         }else{
                             bin2 = bin2 + "0";
                         }
                    }

                    if(matriz[k][t].t1 == alfa2 || matriz[k][t].t2 == alfa2){
                         if(estados.get(t).geteFinal()){
                              bin2 = bin2 + "1";
                          }else{
                              bin2 = bin2 + "0";
                          }
                    }
                }
            }
        }
        
        if(bin1.equals(bin2)){
            return(true);
        }else{
            return(false);
        }
    }
}
