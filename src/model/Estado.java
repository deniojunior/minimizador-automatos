package model;

/**
 * @author deniojr
 */
public class Estado {
    private String nome;
    private int id;
    private boolean eFinal;
    
    public Estado(){
        nome = "";
        eFinal = false;
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }
    
    public String getNome(){
        return nome;
    }
    
    public void seteFinal(boolean eFinal){
        this.eFinal = eFinal;
    }
    
    public boolean geteFinal(){
        return eFinal;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public int getId(){
        return id;
    }   
}