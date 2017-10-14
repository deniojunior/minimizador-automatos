package view;

import model.Estado;
import model.Transicao;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

/**
 * @author deniojunior
 */
public class TelaVisualizarAutomato extends JFrame{
    
    private JPanel painel;
    private JTable tabela;
    private JScrollPane barraRolagem;
    
    private String[][] dados;
    private String[] titulo;
    
    private DefaultTableCellRenderer aEstados, aTransicoes;
    
    public TelaVisualizarAutomato(){
        painel = new JPanel();
        
        //tabela = new JTable();
        
        setBounds(450, 200, 200, 300);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        painel.setLayout(new GridLayout(1,1));
    }
    
    public void ConfigTela(int numAlfa, int numQ, ArrayList<Estado> estados, Transicao[][] matriz){
        //dados = matriz;
        dados = new String[numQ][numQ+1];
        titulo = new String[numQ+1];
        
        titulo[0] = "";
        
        for (int i = 1; i <= numQ; i++){
            titulo[i] = estados.get(i-1).getNome();
            dados[i-1][0] = estados.get(i-1).getNome();
        }
        
       
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
        painel.add(barraRolagem);
        
        TableColumn tc = tabela.getColumn("");
        tc.setCellRenderer(aEstados);
        
        for (int i = 0; i < numQ; i++){
            tabela.getColumn("Q"+i).setCellRenderer(aTransicoes);
        }
        
        getContentPane().add(painel);
        
        int tamanhoX = (int) (25 * (numQ*2));
        int tamanhoY = (int) (52 + (8 * (numQ*2)));
        
        setBounds(450, 200, tamanhoX, tamanhoY);
        setLocationRelativeTo(null);
    }
}