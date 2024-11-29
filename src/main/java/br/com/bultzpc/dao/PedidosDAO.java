package br.com.bultzpc.dao;

import br.com.bultzpc.model.Pedido;

import br.com.bultzpc.conexao.Banco;
import br.com.bultzpc.model.Categoria;
import br.com.bultzpc.model.Produto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Aluno
 */
public class PedidosDAO implements DAO<Pedido> {

    //variaveis auxiliares
    private Pedido pedido;
    
    //auxiliares para acesso aos dados

    //para conter os comandos DML
    private PreparedStatement pst; //pacote java.sql
    //para conter os dados vindos do BD
    private ResultSet rs; //pacote java.sql

    @Override
    public boolean insere(Pedido model) throws SQLException {

        String sql = "INSERT INTO pedido (cpfCliente, dataPedido, total) "
                + "VALUES (?, ?, ?);";

        //Abre a conexao
        Banco.conectar();

        //cria o comando preparado
        pst = Banco.obterConexao().prepareStatement(sql);

        //coloca os valores dentro do comando
        //substitui as '?' por dados
        pst.setString(1, model.getCpfCliente());
        pst.setDate(2, model.getDataPedido());
        pst.setFloat(3, model.getTotal());

        //executa o comando
        if (pst.executeUpdate() >= 1) {
            Banco.desconectar();
            return true;
        } else {
            Banco.desconectar();
            return false;
        }
    }

    @Override
    public boolean remove(Pedido model) throws SQLException {
        String sql = "DELETE FROM pedido WHERE codigo = ?;";

        //Abre a conexao
        Banco.conectar();

        //cria o comando preparado
        pst = Banco.obterConexao().prepareStatement(sql);

        //coloca os valores dentro do comando
        //substitui as '?' por dados
        pst.setInt(1, model.getCodigo());

        //executa o comando
        if (pst.executeUpdate() >= 1) { //tudo certo
            Banco.desconectar();
            return true;
        } else {
            Banco.desconectar();
            return false;
        }
    }

    @Override
    public boolean altera(Pedido model) throws SQLException {
        String sql = "UPDATE pedido SET cpfCliente = ?, dataPedido = ?, total = ? "
                + "WHERE codigo = ?;";

        //Abre a conexao
        Banco.conectar();

        //cria o comando preparado
        pst = Banco.obterConexao().prepareStatement(sql);

        //coloca os valores dentro do comando
        //substitui as '?' por dados
        pst.setString(1, model.getCpfCliente());
        pst.setDate(2, model.getDataPedido());
        pst.setFloat(3, model.getTotal());
        pst.setInt(4, model.getCodigo());

        //executa o comando
        if (pst.executeUpdate() >= 1) { //tudo certo
            Banco.desconectar();
            return true;
        } else {
            Banco.desconectar();
            return false;
        }
    }

    @Override
    public Pedido buscaID(Pedido model) throws SQLException {
        return null;
    }

    public Collection<Pedido> lista(String criterio) throws SQLException {
        return null;
    }
    
    public Collection<Produto> buscarProdutosPorCategoria(int categoriaId) throws SQLException {
        // Lista para armazenar os produtos encontrados
        Collection<Produto> produtos = new ArrayList<>();

        // Consulta SQL para buscar produtos por categoria
        String sql = "SELECT codigo, categoria, nome, descricao, preco FROM produtos WHERE categoria = ?";

        // Conexão com o banco de dados
        Banco.conectar();
        pst = Banco.obterConexao().prepareStatement(sql);
        pst.setInt(1, categoriaId); // Define o ID da categoria no parâmetro da consulta
        rs = pst.executeQuery();

        // Itera pelos resultados e cria objetos Produto
        while (rs.next()) {
            Produto produto = new Produto(
                rs.getInt("codigo"),       // Código do produto
                rs.getInt("categoria"),   // Categoria do produto
                rs.getString("nome"),     // Nome do produto
                rs.getString("descricao"),// Descrição do produto
                rs.getFloat("preco")      // Preço do produto
            );
            produtos.add(produto); // Adiciona o produto à coleção
        }

        // Desconecta do banco de dados
        Banco.desconectar();

        // Retorna a lista de produtos
        return produtos;
    }
}
