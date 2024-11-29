package br.com.bultzpc.dao;

import br.com.bultzpc.model.Cliente;
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
public class ClientesDAO implements DAO<Cliente> {

    //variaveis auxiliares
    private Cliente cliente;
    //auxiliares para acesso aos dados

    //para conter os comandos DML
    private PreparedStatement pst; //pacote java.sql
    //para conter os dados vindos do BD
    private ResultSet rs; //pacote java.sql

    @Override
    public boolean insere(Cliente model) throws SQLException {

        String sql = "INSERT INTO cliente (cpf, nome, datanasc, endereco, email) "
                + "VALUES (?, ?, ?, ?, ?);";

        //Abre a conexao
        Banco.conectar();

        //cria o comando preparado
        pst = Banco.obterConexao().prepareStatement(sql);

        //coloca os valores dentro do comando
        //substitui as '?' por dados
        pst.setString(1, model.getCpf());
        pst.setString(2, model.getNome());
        pst.setDate(3, model.getDataNasc());
        pst.setString(4, model.getEndereco());
        pst.setString(5, model.getEmail());

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
    public boolean remove(Cliente model) throws SQLException {
        String sql = "DELETE FROM cliente WHERE cpf = ?;";

        //Abre a conexao
        Banco.conectar();

        //cria o comando preparado
        pst = Banco.obterConexao().prepareStatement(sql);

        //coloca os valores dentro do comando
        //substitui as '?' por dados
        pst.setString(1, model.getCpf());

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
    public boolean altera(Cliente model) throws SQLException {
        String sql = "UPDATE cliente SET nome = ?, datanasc = ?, endereco = ?, email = ? "
                + "WHERE cpf = ?;";

        //Abre a conexao
        Banco.conectar();

        //cria o comando preparado
        pst = Banco.obterConexao().prepareStatement(sql);

        //coloca os valores dentro do comando
        //substitui as '?' por dados
        pst.setString(1, model.getNome());
        pst.setDate(2, model.getDataNasc());
        pst.setString(3, model.getEndereco());
        pst.setString(4, model.getEmail());
        pst.setString(5, model.getCpf());

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
    public Cliente buscaID(Cliente model) throws SQLException {
        cliente = null;

        String sql = "SELECT * FROM cliente WHERE cpf = ?;";

        Banco.conectar();

        try (PreparedStatement pst = Banco.obterConexao().prepareStatement(sql)) {
            pst.setString(1, model.getCpf());

            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) { // Achou 1 registro
                    cliente = new Cliente();

                    // Move os dados do ResultSet para o objeto Cliente
                    cliente.setCpf(rs.getString("cpf"));
                    cliente.setNome(rs.getString("nome"));
                    cliente.setDataNasc(rs.getDate("datanasc"));
                    cliente.setEndereco(rs.getString("endereco"));
                    cliente.setEmail(rs.getString("email"));
                }
            }
        } finally {
            Banco.desconectar();
        }

        return cliente;
    }

    public Collection<Cliente> lista(String criterio) throws SQLException {
        //criar uma coleção
        Collection<Cliente> listagem = new ArrayList<>();

        cliente = null;

        //Comando SELECT
        String sql = "SELECT * FROM cliente ";
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
            cliente = new Cliente();

            //move os dados do resultSet para o objeto veiculo
            cliente.setCpf(rs.getString("cpf"));
            cliente.setNome(rs.getString("nome"));
            cliente.setDataNasc(rs.getDate("datanasc"));
            cliente.setEndereco(rs.getString("endereco"));
            cliente.setEmail("email");

            //adicionar na coleção
            listagem.add(cliente);
        }

        Banco.desconectar();

        return listagem;
    }
}
