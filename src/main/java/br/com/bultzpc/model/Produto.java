package br.com.bultzpc.model;

public class Produto {
    
    private int codigo, categoria;
    private String nome, descricao;
    private float preco;
    
    // Construtores
     public Produto(){
        
    }
    
    public Produto(int codigo, int categoria, String nome, String descricao, float preco) {
        this.codigo = codigo;
        this.categoria = categoria;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
    }
    
    // ToString

    @Override
    public String toString() {
        return nome;

    }
    
    
    // Equals e HashCode

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + Float.floatToIntBits(this.preco);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Produto other = (Produto) obj;

        if (this.codigo != other.codigo) {
            return false;
        }

        return Float.floatToIntBits(this.preco) == Float.floatToIntBits(other.preco);
    }
    
    // Getters e Setters

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }
    
    
}
