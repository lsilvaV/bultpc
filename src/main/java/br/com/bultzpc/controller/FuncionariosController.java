/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.bultzpc.controller;

import br.com.bultzpc.estado.Estado;
import br.com.bultzpc.model.Funcionario;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 *
 * @author lucas
 */
public class FuncionariosController implements Initializable{

    @FXML
    private Button btnSair;
    @FXML
    private Button btnMenu;
    @FXML
    private Button btnFuncionarios;
    @FXML
    private Button btnProdutos;
    @FXML
    private Button btnPedidos;
    @FXML
    private Button btnClientes;
    @FXML
    private Button btnConsulta;
    @FXML
    private TextField txtCPF;
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtDataNasc;
    @FXML
    private TextField txtSalario;
    @FXML
    private Button btnDeletar;
    @FXML
    private Button btnAlterar;
    @FXML
    private Button btnPesquisar;
    @FXML
    private Button btnCadastrar;

    private ArrayList<Funcionario> funcionarios = new ArrayList<>();
    private Funcionario funcionario;
    @FXML
    private TextField txtCargo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        funcionarios = Estado.getInstance().getFuncionarios();
        funcionarios.add(new Funcionario("12345678900", "João Silva", criarData(1990, 5, 15), "Gerente", 8500.00f));
        funcionarios.add(new Funcionario("98765432100", "Maria Oliveira", criarData(1985, 10, 25), "Analista", 4500.00f));
        funcionarios.add(new Funcionario("11122233344", "Carlos Santos", criarData(1995, 3, 12), "Técnico", 3200.00f));
        funcionarios.add(new Funcionario("55566677788", "Ana Costa", criarData(2000, 7, 30), "Estagiária", 1200.00f));
        funcionarios.add(new Funcionario("99988877766", "Pedro Almeida", criarData(1988, 1, 5), "Supervisor", 5200.00f));
        Estado.getInstance().setFuncionarios(funcionarios);
    }

    @FXML
    private void btnProdutos_Click(ActionEvent event) {
        try {
            // Carregar o arquivo FXML da tela "menu.fxml"
            Parent produtosRoot = FXMLLoader.load(getClass().getResource("/br/com/bultzpc/view/produtos.fxml"));

            // Criar uma nova cena
            Scene produtosScene = new Scene(produtosRoot);

            // Obter o Stage a partir do evento
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Configurar a nova cena
            window.setScene(produtosScene);
            window.setTitle("Tela de Produtos");
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnPedidos_Click(ActionEvent event) {
        try {
            // Carregar o arquivo FXML da tela "menu.fxml"
            Parent pedidosRoot = FXMLLoader.load(getClass().getResource("/br/com/bultzpc/view/pedidos.fxml"));

            // Criar uma nova cena
            Scene pedidosScene = new Scene(pedidosRoot);

            // Obter o Stage a partir do evento
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Configurar a nova cena
            window.setScene(pedidosScene);
            window.setTitle("Tela de Pedidos");
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void btnClientes_Click(ActionEvent event) {
        try {
            // Carregar o arquivo FXML da tela "menu.fxml"
            Parent clientesRoot = FXMLLoader.load(getClass().getResource("/br/com/bultzpc/view/clientes.fxml"));

            // Criar uma nova cena
            Scene clientesScene = new Scene(clientesRoot);

            // Obter o Stage a partir do evento
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Configurar a nova cena
            window.setScene(clientesScene);
            window.setTitle("Tela de Clientes");
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnConsulta_Click(ActionEvent event) {
        try {
            // Carregar o arquivo FXML da tela "menu.fxml"
            Parent consultaRoot = FXMLLoader.load(getClass().getResource("/br/com/bultzpc/view/consultaPedidos.fxml"));

            // Criar uma nova cena
            Scene consultaScene = new Scene(consultaRoot);

            // Obter o Stage a partir do evento
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Configurar a nova cena
            window.setScene(consultaScene);
            window.setTitle("Tela de Consulta");
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void btnMenu_Click(ActionEvent event) {
        try {
            // Carregar o arquivo FXML da tela "menu.fxml"
            Parent menuRoot = FXMLLoader.load(getClass().getResource("/br/com/bultzpc/view/menu.fxml"));

            // Criar uma nova cena
            Scene menuScene = new Scene(menuRoot);

            // Obter o Stage a partir do evento
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Configurar a nova cena
            window.setScene(menuScene);
            window.setTitle("Menu");
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnSair_click(ActionEvent event) {
        Stage stage = (Stage) btnSair.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void btnPesquisar_Click(ActionEvent event) {
        boolean encontrado = false; // Variável de controle

        try {
            for (Funcionario f : funcionarios) {
                if (f.getCpf().equals(txtCPF.getText())) {
                    encontrado = true;
                    txtNome.setText(f.getNome());
                    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                    txtDataNasc.setText(formato.format(f.getDataNasc()));
                    txtSalario.setText(String.valueOf(f.getSalario()));
                    txtCargo.setText(f.getCargo()); // Caso precise exibir o cargo
                    btnDeletar.setDisable(false);
                    btnAlterar.setDisable(false);
                    break; // CPF encontrado, não precisa continuar o loop
                }
            }

            // Após o loop, se não encontrado, exibe a mensagem de erro
            if (!encontrado) {
                mensagem("Não há nenhum funcionário com o CPF digitado. Faça o cadastro do funcionário.");
                btnCadastrar.setDisable(false);
                btnDeletar.setDisable(true);
                btnAlterar.setDisable(true);
            }

        } catch (Exception ex) {
            mensagem("Erro na busca\n" + ex.getMessage());
        }
    }

    @FXML
    private void btnDeletar_Click(ActionEvent event) {
    }

    @FXML
    private void btnAlterar_Click(ActionEvent event) {
    }

    @FXML
    private void btnCadastrar_Click(ActionEvent event) {
    }

    private void mensagem(String msg) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Mensagem");
        alerta.setHeaderText(msg);
        alerta.setContentText("");

        alerta.showAndWait(); //exibe a mensage
    }

    private static java.util.Date criarData(int ano, int mes, int dia) {
        Calendar calendario = Calendar.getInstance();
        calendario.set(ano, mes - 1, dia); // Meses em Calendar começam do 0 (Janeiro = 0)
        return calendario.getTime();
    }

}
