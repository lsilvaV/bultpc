package br.com.bultzpc.dao;

import br.com.bultzpc.model.ItensPedido;
import br.com.bultzpc.conexao.Banco;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Aluno
 */
public class ItensPedidoDAO implements DAO<ItensPedido> {

    //variaveis auxiliares
    private ItensPedido itenspedido;
    //auxiliares para acesso aos dados

    //para conter os comandos DML
    private PreparedStatement pst; //pacote java.sql
    //para conter os dados vindos do BD
    private ResultSet rs; //pacote java.sql

    @Override
    public boolean insere(ItensPedido model) throws SQLException {

        String sql = "INSERT INTO itenspedido (pedidoId, produtoId, quantidade, preco) "
                + "VALUES (?, ?, ?, ?);";

        //Abre a conexao
        Banco.conectar();

        //cria o comando preparado
        pst = Banco.obterConexao().prepareStatement(sql);

        //coloca os valores dentro do comando
        //substitui as '?' por dados
        pst.setInt(1, model.getPedidoId());
        pst.setInt(2, model.getProdutoId());
        pst.setInt(3, model.getQuantidade());
        pst.setFloat(4, model.getPreco());

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
    public boolean remove(ItensPedido model) throws SQLException {
        String sql = "DELETE FROM itenspedido WHERE id = ?;";

        //Abre a conexao
        Banco.conectar();

        //cria o comando preparado
        pst = Banco.obterConexao().prepareStatement(sql);

        //coloca os valores dentro do comando
        //substitui as '?' por dados
        pst.setInt(1, model.getId());

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
    public boolean altera(ItensPedido model) throws SQLException {
        return false;
    }

    @Override
    public ItensPedido buscaID(ItensPedido model) throws SQLException {
        return null;
    }

    public Collection<ItensPedido> lista(String criterio) throws SQLException {
        //criar uma coleção
        Collection<ItensPedido> listagem = new ArrayList<>();

        itenspedido = null;

        //Comando SELECT
        String sql = "SELECT * FROM itenspedido ";
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
            itenspedido = new ItensPedido();

            //move os dados do resultSet para o objeto veiculo
            itenspedido.setId(rs.getInt("id"));
            itenspedido.setPedidoId(rs.getInt("pedidoId"));
            itenspedido.setProdutoId(rs.getInt("produtoId"));
            itenspedido.setQuantidade(rs.getInt("quantidade"));
            itenspedido.setPreco(rs.getFloat("preco"));

            //adicionar na coleção
            listagem.add(itenspedido);
        }

        Banco.desconectar();

        return listagem;
    }
}
