/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.bultzpc.controller;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 *
 * @author lucas
 */
public class FuncionariosController {

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
    private Button btnDeletar;
    @FXML
    private Button btnAlterar;
    @FXML
    private Button btnCadastrar;
    @FXML
    private TableColumn<?, ?> colunaNome;
    @FXML
    private TableColumn<?, ?> colunaCPF;
    @FXML
    private TableColumn<?, ?> colunaDataNasc;
    @FXML
    private TableColumn<?, ?> colunaCargo;
    @FXML
    private TableColumn<?, ?> colunaSalario;

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
    private void btnDeletar_Click(ActionEvent event) {
    }

    @FXML
    private void btnAlterar_Click(ActionEvent event) {
    }

    @FXML
    private void btnCadastrar_Click(ActionEvent event) {
    }

}
