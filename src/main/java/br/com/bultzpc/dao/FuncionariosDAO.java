package br.com.bultzpc.dao;

import br.com.bultzpc.model.Funcionario;
import br.com.bultzpc.conexao.Banco;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class FuncionariosDAO implements DAO <Funcionario> {
    
    //variaveis auxiliares
    private Funcionario funcionario;
    //auxiliares para acesso aos dados
    
    //para conter os comandos DML
    private PreparedStatement pst; //pacote java.sql
    //para conter os dados vindos do BD
    private ResultSet rs; //pacote java.sql
    
    
    @Override
    public boolean insere(Funcionario model) throws SQLException {
        
        String sql = "INSERT INTO funcionario (cpf, nome, datanasc, cargo, salario) "
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
        pst.setString(4, model.getCargo());
        pst.setFloat(5, model.getSalario());
        
        //executa o comando
        if(pst.executeUpdate() >= 1) { //tudo certo
            Banco.desconectar();
            return true;
        }
        else {
            Banco.desconectar();
            return false;
        }
    }

    @Override
    public boolean remove(Funcionario model) throws SQLException {
        String sql = "DELETE FROM funcionario WHERE cpf = ?;";
        
        //Abre a conexao
        Banco.conectar();
        
        //cria o comando preparado
        pst = Banco.obterConexao().prepareStatement(sql);
        
        //coloca os valores dentro do comando
        //substitui as '?' por dados
        pst.setString(1, model.getCpf());
        
        //executa o comando
        if(pst.executeUpdate() >= 1) { //tudo certo
            Banco.desconectar();
            return true;
        }
        else {
            Banco.desconectar();
            return false;
        }
    }

    @Override
    public boolean altera(Funcionario model) throws SQLException {
        String sql = "UPDATE funcionario SET nome = ? "
        + "WHERE cpf = ?;";
        
        //Abre a conexao
        Banco.conectar();
        
        //cria o comando preparado
        pst = Banco.obterConexao().prepareStatement(sql);
        
        //coloca os valores dentro do comando
        //substitui as '?' por dados
        pst.setString(1, model.getNome());
        pst.setString(2, model.getCpf());
        
        //executa o comando
        if(pst.executeUpdate() >= 1) { //tudo certo
            Banco.desconectar();
            return true;
        }
        else {
            Banco.desconectar();
            return false;
        }
    }

    @Override
    public Funcionario buscaID(Funcionario model) throws SQLException {
        
        funcionario = null;
        
        //Comando SELECT
        String sql = "SELECT * FROM funcionario WHERE cpf = ?;";
        
        //conecta ao banco
        Banco.conectar();
        
        //cria o comando preparado
        pst = Banco.obterConexao().prepareStatement(sql);
        
        //troca a ?
        pst.setString(1, model.getCpf());
        
        //Executa o comando SELECT
        rs = pst.executeQuery();
        
        //le o próximo regitro
        if(rs.next()) { //achou 1 registro
            funcionario = new Funcionario();
            
            pst.setString(1, model.getCpf());
            pst.setString(2, model.getNome());
            pst.setDate(3, model.getDataNasc());
            pst.setString(4, model.getCargo());
            pst.setFloat(5, model.getSalario());
        }
        
        Banco.desconectar();
        
        return funcionario;        
    }
    
     @Override
    public Collection<Funcionario> lista(String criterio) throws SQLException {
        //criar uma coleção
        Collection<Funcionario> listagem = new ArrayList<>();
        
        funcionario = null;
        
        //Comando SELECT
        String sql = "SELECT * FROM funcionario ";
        //colocar filtro ou nao
        if(criterio.length() != 0) {
            sql += "WHERE " + criterio;
        }
        
        //conecta ao banco
        Banco.conectar();
        
        pst = Banco.obterConexao().prepareStatement(sql);
        
        //Executa o comando SELECT
        rs = pst.executeQuery();
        
        //le o próximo regitro
        while(rs.next()) { //achou 1 registro
            
            funcionario = new Funcionario();
            
            funcionario.setCpf(rs.getString("cpf"));
            funcionario.setNome(rs.getString("nome"));
            funcionario.setDataNasc(rs.getDate("datanasc"));
            funcionario.setCargo(rs.getString("cargo"));
            funcionario.setSalario(rs.getFloat("salario"));
                     
            //adicionar na coleção
            listagem.add(funcionario);
        }
        
        Banco.desconectar();
        
        return listagem;
    }
}
