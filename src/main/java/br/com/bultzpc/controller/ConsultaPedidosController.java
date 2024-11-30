package br.com.bultzpc.controller;

import br.com.bultzpc.dao.ItensPedidoDAO;
import br.com.bultzpc.model.ItensPedido;
import br.com.bultzpc.dao.PedidosDAO;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Collection;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class ConsultaPedidosController implements Initializable {

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
    private Button btnSair;
    @FXML
    private Button btnPedidos;
    @FXML
    private TextField txtCodigo;
    @FXML
    private ComboBox<String> cmbCondicao;
    @FXML
    private Button btnConsultar;
    @FXML
    private TableView<ItensPedido> tabelaPedidos;
    @FXML
    private TableColumn<ItensPedido, String> colCPFCliente;
    @FXML
    private TableColumn<ItensPedido, Integer> colIdPedido;
    @FXML
    private TableColumn<ItensPedido, String> colItemPedido;
    @FXML
    private TableColumn<ItensPedido, String> colDataPedido;
    @FXML
    private TableColumn<ItensPedido, Float> colPreco;
    
    private PedidosDAO pedidosDAO;
    private ItensPedidoDAO itensPedidoDAO;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {       
        itensPedidoDAO = new ItensPedidoDAO();
        
        // Configuração das colunas da tabela
        colCPFCliente.setCellValueFactory(new PropertyValueFactory<>("cpfCliente"));
        colIdPedido.setCellValueFactory(new PropertyValueFactory<>("pedidoId"));
        colItemPedido.setCellValueFactory(new PropertyValueFactory<>("nomeProduto"));
        colDataPedido.setCellValueFactory(new PropertyValueFactory<>("dataPedido"));
        colPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
        
        // Configurando ComboBox
        cmbCondicao.getItems().addAll("CPF do Cliente", "Data do Pedido");
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
    private void btnConsultar_click(ActionEvent event) {
        String condicaoSelecionada = cmbCondicao.getValue(); // Obtém a condição selecionada
        String inputValor = txtCodigo.getText(); // Valor inserido pelo usuário
        String criterio = "";

        try {
            if ("CPF do Cliente".equals(condicaoSelecionada)) {
                if (!inputValor.isEmpty()) {
                    criterio = "p.cpfCliente = '" + inputValor + "'"; // Inclui alias da tabela
                }
            } else if ("Data do Pedido".equals(condicaoSelecionada)) {
                if (!inputValor.isEmpty()) {
                    criterio = "p.dataPedido = '" + inputValor + "'"; // Inclui alias da tabela
                }
            }

            // Chama o método lista com o critério
            Collection<ItensPedido> resultados = itensPedidoDAO.lista(criterio);

            // Atualiza a tabela com os resultados
            atualizarTabela(resultados);

        } catch (SQLException e) {
            e.printStackTrace();
            mensagem("Erro ao consultar os pedidos." + e.getMessage());
        }
    }
    
    private void atualizarTabela(Collection<ItensPedido> resultados) {
        tabelaPedidos.getItems().clear(); // Limpa os itens existentes na tabela
        tabelaPedidos.getItems().addAll(resultados); // Adiciona os resultados retornados
    }
    
    private void mensagem(String msg) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Mensagem");
        alerta.setHeaderText(msg);
        alerta.setContentText("");
        
        alerta.showAndWait();
    }
}
