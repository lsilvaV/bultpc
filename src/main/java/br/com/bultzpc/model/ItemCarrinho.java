package br.com.bultzpc.model;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ItemCarrinho {
    private final SimpleIntegerProperty produtoId;
    private final SimpleStringProperty nome;
    private final SimpleFloatProperty precoUnitario;
    private final SimpleIntegerProperty quantidade;
    private final SimpleFloatProperty precoTotal;

    // Construtor aceitando valores simples
    public ItemCarrinho(int produtoId, String nome, float precoUnitario, int quantidade) {
        this.produtoId = new SimpleIntegerProperty(produtoId);
        this.nome = new SimpleStringProperty(nome);
        this.precoUnitario = new SimpleFloatProperty(precoUnitario);
        this.quantidade = new SimpleIntegerProperty(quantidade);
        this.precoTotal = new SimpleFloatProperty(precoUnitario * quantidade); // Calcula o total
    }

    // Getters para vinculação à TableView
    public SimpleStringProperty nomeProperty() {
        return nome;
    }

    public SimpleFloatProperty precoUnitarioProperty() {
        return precoUnitario;
    }

    public SimpleIntegerProperty quantidadeProperty() {
        return quantidade;
    }

    public SimpleFloatProperty precoTotalProperty() {
        return precoTotal;
    }

    public SimpleIntegerProperty produtoIdProperty() {
        return produtoId;
    }

    // Métodos para valores simples (opcional)
    public String getNome() {
        return nome.get();
    }

    public float getPrecoUnitario() {
        return precoUnitario.get();
    }

    public int getQuantidade() {
        return quantidade.get();
    }

    public float getPrecoTotal() {
        return precoTotal.get();
    }

    public int getProdutoId() {
        return produtoId.get();
    }
}
