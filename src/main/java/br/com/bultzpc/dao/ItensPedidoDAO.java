package br.com.bultzpc.dao;

import br.com.bultzpc.model.ItensPedido;
import br.com.bultzpc.conexao.Banco;
import br.com.bultzpc.model.Pedido;
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
public class ItensPedidoDAO implements DAO<ItensPedido> {

    //variaveis auxiliares
    private ItensPedido itenspedido;
    private Pedido pedido;
    private Produto produto;
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
        Collection<ItensPedido> listagem = new ArrayList<>();

        // Comando SQL com joins
        String sql = "SELECT i.id, i.pedidoId, i.produtoId, i.quantidade, i.preco, " +
                     "p.cpfCliente, p.dataPedido, pr.nome AS nome_produto " +
                     "FROM itenspedido i " +
                     "INNER JOIN pedido p ON i.pedidoId = p.codigo " +
                     "INNER JOIN produtos pr ON i.produtoId = pr.codigo ";

        // Adicionar filtro, se necessário
        if (criterio != null && !criterio.isEmpty()) {
            sql += "WHERE " + criterio;
        }

        // Conectar ao banco
        Banco.conectar();
        pst = Banco.obterConexao().prepareStatement(sql);
        rs = pst.executeQuery();

        // Preencher os resultados
        while (rs.next()) {
            // Criar um novo objeto Pedido
            Pedido pedido = new Pedido();
            pedido.setCpfCliente(rs.getString("cpfCliente"));
            pedido.setDataPedido(rs.getDate("dataPedido"));

            // Criar um novo objeto ItensPedido
            ItensPedido itenspedido = new ItensPedido();
            itenspedido.setId(rs.getInt("id"));
            itenspedido.setPedidoId(rs.getInt("pedidoId"));
            itenspedido.setProdutoId(rs.getInt("produtoId"));
            itenspedido.setQuantidade(rs.getInt("quantidade"));
            itenspedido.setPreco(rs.getFloat("preco"));

            // Adicionar o nome do produto
            itenspedido.setNomeProduto(rs.getString("nome_produto"));

            // Associar Pedido ao ItensPedido
            itenspedido.setPedido(pedido);

            // Adicionar na lista
            listagem.add(itenspedido);
        }

        Banco.desconectar();
        return listagem;
    }
}
