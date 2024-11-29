package br.com.bultzpc.dao;

import br.com.bultzpc.model.Produto;
import br.com.bultzpc.conexao.Banco;
import br.com.bultzpc.model.Categoria;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class ProdutosDAO implements DAO<Produto> {

    //variaveis auxiliares
    private Produto produtos;
    //auxiliares para acesso aos dados

    //para conter os comandos DML
    private PreparedStatement pst; //pacote java.sql
    //para conter os dados vindos do BD
    private ResultSet rs; //pacote java.sql

    @Override
    public boolean insere(Produto model) throws SQLException {

        String sql = "INSERT INTO produtos (codigo, nome, preco, categoria, descricao) "
                + "VALUES (?, ?, ?, ?, ?);";

        //Abre a conexao
        Banco.conectar();

        //cria o comando preparado
        pst = Banco.obterConexao().prepareStatement(sql);

        //coloca os valores dentro do comando
        //substitui as '?' por dados
                //coloca os valores dentro do comando
        //substitui as '?' por dados
        pst.setInt(1, model.getCodigo());
        pst.setString(2, model.getNome());
        pst.setFloat(3, model.getPreco());
        pst.setInt(4, model.getCategoria());
        pst.setString(5, model.getDescricao());


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
    public boolean remove(Produto model) throws SQLException {
        String sql = "DELETE FROM produtos WHERE codigo = ?;";

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
    public boolean altera(Produto model) throws SQLException {
        String sql = "UPDATE produtos SET nome = ?, preco = ?, categoria = ?, descricao = ? WHERE codigo = ?;";


        //Abre a conexao
        Banco.conectar();

        //cria o comando preparado
        pst = Banco.obterConexao().prepareStatement(sql);

        //coloca os valores dentro do comando
        //substitui as '?' por dados
        pst.setString(1, model.getNome());
        pst.setFloat(2, model.getPreco());
        pst.setInt(3, model.getCategoria());
        pst.setString(4, model.getDescricao());
        pst.setInt(5, model.getCodigo());
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
    public Produto buscaID(Produto model) throws SQLException {
        produtos = null;

        //Comando SELECT
        String sql = "SELECT * FROM produtos WHERE codigo = ?;";

        //conecta ao banco
        Banco.conectar();

        //cria o comando preparado
        try (PreparedStatement pst = Banco.obterConexao().prepareStatement(sql)) {
            // troca a? 
            pst.setInt(1, model.getCodigo());

            //Executa o comando SELECT
            try (ResultSet rs = pst.executeQuery()) {
                //le o próximo regitro
                if (rs.next()) { //achou 1 registro
                    produtos = new Produto();

                    produtos.setCodigo(rs.getInt("codigo"));
                    produtos.setNome(rs.getString("nome"));
                    produtos.setPreco(rs.getFloat("preco"));
                    produtos.setCategoria(rs.getInt("categoria"));
                    produtos.setDescricao(rs.getString("descricao"));

                }
            }
        } finally {
            Banco.desconectar();

        }
        return produtos;
    }

    @Override
    public Collection<Produto> lista(String criterio) throws SQLException {
        //criar uma coleção
        Collection<Produto> listagem = new ArrayList<>();

        produtos = null;

        //Comando SELECT
        String sql = "SELECT * FROM produtos";
        //colocar filtro ou nao
        if (criterio.length() != 0) {
            sql += "WHERE " + criterio;
        }

        //conecta ao banco
        Banco.conectar();

        pst = Banco.obterConexao().prepareStatement(sql);

        //Executa o comando SELECT
        rs = pst.executeQuery();

        //le o próximo regitro
        while (rs.next()) { //achou 1 registro
            //cria o objeto veiculo
            produtos = new Produto();

            //move os dados do resultSet para o objeto veiculo
            produtos.setCodigo(rs.getInt("codigo"));
            produtos.setNome(rs.getString("nome"));
            produtos.setPreco(rs.getFloat("preco"));
            produtos.setCategoria(rs.getInt("categoria"));
            produtos.setDescricao(rs.getString("descricao"));


            //adicionar na coleção
            listagem.add(produtos);
        }

        Banco.desconectar();

        return listagem;
    }
    
    public Collection<Categoria> listarCategorias() throws SQLException {
        Collection<Categoria> categorias = new ArrayList<>();
        String sql = "SELECT id, nome FROM categorias";

        Banco.conectar();
        pst = Banco.obterConexao().prepareStatement(sql);
        rs = pst.executeQuery();

        while (rs.next()) {
            Categoria categoria = new Categoria();
            categoria.setId(rs.getInt("id"));
            categoria.setNome(rs.getString("nome"));
            categorias.add(categoria);
        }

        Banco.desconectar();
        return categorias;
    }
}
