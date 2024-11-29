package br.com.bultzpc.model;

public class Categoria {
    private int id;
    private String nome;
    
    // Construtores
    public Categoria() {
    }
     
    public Categoria(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }
    
    // To String
    @Override
    public String toString() {
        return nome;
    }
    
   
    // Getters e Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    
    
}
