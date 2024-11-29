package br.com.bultzpc.model;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author
 */
public class ItensPedido {

    private final SimpleBooleanProperty selecionado;
    private final SimpleIntegerProperty id;
    private final SimpleIntegerProperty pedidoId;
    private final SimpleIntegerProperty produtoId;
    private final SimpleIntegerProperty quantidade;
    private final SimpleFloatProperty preco;
    
    
    // Construtor padrão
    public ItensPedido() {
        this.selecionado = new SimpleBooleanProperty(false);
        this.id = new SimpleIntegerProperty(0);
        this.pedidoId = new SimpleIntegerProperty(0);
        this.produtoId = new SimpleIntegerProperty(0);
        this.quantidade = new SimpleIntegerProperty(0);
        this.preco = new SimpleFloatProperty(0.0f);
    }

    // Construtor com parâmetros
    public ItensPedido(int id, int pedidoId, int produtoId, int quantidade, float preco) {
        this.selecionado = new SimpleBooleanProperty(false);
        this.id = new SimpleIntegerProperty(id);
        this.pedidoId = new SimpleIntegerProperty(pedidoId);
        this.produtoId = new SimpleIntegerProperty(produtoId);
        this.quantidade = new SimpleIntegerProperty(quantidade);
        this.preco = new SimpleFloatProperty(preco);
    }

    // Retorna as propriedades
    public SimpleBooleanProperty selecionadoProperty() {
        return selecionado;
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public SimpleIntegerProperty pedidoIdProperty() {
        return pedidoId;
    }

    public SimpleIntegerProperty produtoIdProperty() {
        return produtoId;
    }

    public SimpleIntegerProperty quantidadeProperty() {
        return quantidade;
    }

    public SimpleFloatProperty precoProperty() {
        return preco;
    }

    // Getters e Setters do model
    public boolean isSelecionado() {
        return selecionado.get();
    }

    public void setSelecionado(boolean selecionado) {
        this.selecionado.set(selecionado);
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public int getPedidoId() {
        return pedidoId.get();
    }

    public void setPedidoId(int pedidoId) {
        this.pedidoId.set(pedidoId);
    }

    public int getProdutoId() {
        return produtoId.get();
    }

    public void setProdutoId(int produtoId) {
        this.produtoId.set(produtoId);
    }

    public int getQuantidade() {
        return quantidade.get();
    }

    public void setQuantidade(int quantidade) {
        this.quantidade.set(quantidade);
    }

    public float getPreco() {
        return preco.get();
    }

    public void setPreco(float preco) {
        this.preco.set(preco);
    }
}
