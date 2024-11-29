/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.com.bultzpc.controller;

import br.com.bultzpc.dao.ProdutosDAO;
import br.com.bultzpc.dao.PedidosDAO;
import br.com.bultzpc.model.Categoria;
import br.com.bultzpc.model.Pedido;
import br.com.bultzpc.model.Produto;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Collection;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class PedidosController implements Initializable {

    @FXML
    private Button btnFuncionarios;
    @FXML
    private Button btnConsulta;
    @FXML
    private Button btnClientes;
    @FXML
    private Button btnProdutos;
    @FXML
    private Button btnMenu;
    @FXML
    private Button btnPedidos;
    @FXML
    private Button btnSair;
    @FXML
    private Button btnCadastrar;
    @FXML
    private Button btnDeletar;
    @FXML
    private ComboBox<Categoria> cmbCategoria;
    @FXML
    private TextField txtPreco;
    @FXML
    private ComboBox<Produto> cmbItem;
    @FXML
    private TextField txtTotal;
    @FXML
    private TextField txtQuantidade;
    @FXML
    private Button btnRemoverCarrinho;
    @FXML
    private Button btnAdicionarCarrinho;
    @FXML
    private TableColumn<?, ?> colunaItem;
    @FXML
    private TableColumn<?, ?> colunaPreco;
    @FXML
    private TableColumn<?, ?> colunaQtd;
    
    
    private Produto produto;
    private Categoria categoria;
    private ProdutosDAO produtosDAO = new ProdutosDAO();
    private PedidosDAO pedidosDAO = new PedidosDAO();
    private ObservableList<Categoria> listaProdutos =  
            FXCollections.observableArrayList();
    private ObservableList<Produto> listaProdutosPorCategoria = FXCollections.observableArrayList();

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarCombo();
        
        // Listener para carregar os produtos ao selecionar uma categoria
        cmbCategoria.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) { // Verifica se há uma nova categoria selecionada
                try {
                    carregarProdutosPorCategoria(newVal.getId()); // Carrega produtos da categoria selecionada
                } catch (SQLException ex) {
                    mensagem(ex.getMessage());
                }
            }
        });
    }    

    @FXML
    private void btnFuncionarios_Click(ActionEvent event) {
        try {
            // Carregar o arquivo FXML da tela "menu.fxml"
            Parent funcionariosRoot = FXMLLoader.load(getClass().getResource("/br/com/bultzpc/view/funcionarios.fxml"));

            // Criar uma nova cena
            Scene funcionariosScene = new Scene(funcionariosRoot);

            // Obter o Stage a partir do evento
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Configurar a nova cena
            window.setScene(funcionariosScene);
            window.setTitle("Tela de Funcionários");
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
    private void btnCadastrar_Click(ActionEvent event) {
    }


    @FXML
    private void btnDeletar_Click(ActionEvent event) {
    }

    @FXML
    private void txtCodigo_lostFocusTab(KeyEvent event) {
    }

    @FXML
    private void btnRemoverCarrinho_Click(ActionEvent event) {
    }

    @FXML
    private void btnAdicionarCarrinho_Click(ActionEvent event) {
    }
    
    
    private void carregarCombo() {
        ProdutosDAO prodDAO = new ProdutosDAO();
        
        try {
            //busca todos os registros no banco para uma Coleção
            Collection<Categoria> listaProd = prodDAO.listarCategorias();
            //colocar a lista gerada pela DAO dentro da COMBO
            listaProdutos.addAll(listaProd);
            //informa que a combo possui uma lista
            cmbCategoria.setItems(listaProdutos);
        } catch (SQLException ex) {
            mensagem(ex.getMessage());
        }
    }
    
    private void mensagem(String msg) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Mensagem");
        alerta.setHeaderText(msg);
        alerta.setContentText("");

        alerta.showAndWait(); //exibe a mensage
    }
    
    private void carregarProdutosPorCategoria(int categoriaId) throws SQLException {
        listaProdutosPorCategoria.clear(); // Limpa a lista de produtos antes de carregar
        Collection<Produto> produtos = pedidosDAO.buscarProdutosPorCategoria(categoriaId);
        listaProdutosPorCategoria.addAll(produtos); // Adiciona os produtos encontrados à lista
        cmbItem.setItems(listaProdutosPorCategoria); // Atualiza a ComboBox de itens
    }
    
}
