package br.com.bultzpc.model;

public class ItensPedido {
    private int id, pedidoId, produtoId, quantidade;
    private float preco;
    
    private String nomeProduto; // Nome do Produto
    private Pedido pedido;      // Referência ao Pedido

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(int pedidoId) {
        this.pedidoId = pedidoId;
    }

    public int getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(int produtoId) {
        this.produtoId = produtoId;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    // Métodos intermediários para acessar dados de Pedido
    public String getCpfCliente() {
        return pedido != null ? pedido.getCpfCliente() : null;
    }

    public String getDataPedido() {
        return pedido != null ? pedido.getDataPedido().toString() : null; // Convertendo para String
    }
}
