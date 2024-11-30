package br.com.bultzpc.controller;

import br.com.bultzpc.dao.ProdutosDAO;
import br.com.bultzpc.dao.PedidosDAO;
import br.com.bultzpc.dao.ItensPedidoDAO;
import br.com.bultzpc.model.Categoria;
import br.com.bultzpc.model.Pedido;
import br.com.bultzpc.model.Produto;
import br.com.bultzpc.model.ItemCarrinho;
import br.com.bultzpc.model.ItensPedido;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Collection;
import java.util.ResourceBundle;

import javafx.beans.property.*;

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
import javafx.scene.control.TableView;
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
    private Button btnAdicionarCarrinho;
    
    @FXML
    private TableColumn<ItemCarrinho, String> colunaItem;
    @FXML
    private TableColumn<ItemCarrinho, String> colunaPreco;
    @FXML
    private TableColumn<ItemCarrinho, String> colunaQtd;
    
    @FXML
    private TableView<ItemCarrinho> tableView;
    
    private Produto produto;
    private Categoria categoria;
    private ProdutosDAO produtosDAO = new ProdutosDAO();
    private PedidosDAO pedidosDAO = new PedidosDAO();
    
    private float somaTotal;
    
    private ObservableList<Categoria> listaProdutos
            = FXCollections.observableArrayList();
    private ObservableList<Produto> listaProdutosPorCategoria = FXCollections.observableArrayList();
    private ObservableList<ItemCarrinho> carrinho = FXCollections.observableArrayList();
    @FXML
    private Button btnLimpar;
    @FXML
    private TextField txtCpfCliente;

    /**
     * Initializes the controller class.
     */
    @Override
    
    public void initialize(URL url, ResourceBundle rb) {
        carregarCombo();

        // Listener para carregar os produtos ao selecionar uma categoria
        cmbCategoria.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                try {
                    carregarProdutosPorCategoria(newVal.getId());
                } catch (SQLException ex) {
                    mensagem(ex.getMessage());
                }
            }
        });

        // Listener para atualizar o preço ao selecionar um item
        cmbItem.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                Produto produtoSelecionado = newVal;
                txtPreco.setText(String.valueOf(produtoSelecionado.getPreco()));
            }
        });

        // Configuração das colunas da TableView
        colunaItem.setCellValueFactory(cellData -> cellData.getValue().nomeProperty());
        colunaPreco.setCellValueFactory(cellData -> new SimpleStringProperty(
                String.format("%.2f", cellData.getValue().getPrecoTotal())));
        colunaQtd.setCellValueFactory(cellData -> new SimpleStringProperty(
                String.valueOf(cellData.getValue().getQuantidade())));

        // Configura a TableView para usar o carrinho
        tableView.setItems(carrinho);
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
        String cpfCliente = txtCpfCliente.getText(); // Obtém o CPF do cliente

        // Validações
        if (cpfCliente == null || cpfCliente.isEmpty()) {
            mensagem("Por favor, insira o CPF do cliente.");
            return;
        }

        if (carrinho.isEmpty()) {
            mensagem("O carrinho está vazio. Adicione itens antes de cadastrar.");
            return;
        }

        try {
            // Cria o objeto Pedido
            Pedido pedido = new Pedido();
            pedido.setCpfCliente(cpfCliente);
            pedido.setDataPedido(new java.sql.Date(System.currentTimeMillis())); // Data atual
            pedido.setTotal((float) somaTotal);

            // Insere o pedido na tabela 'pedido'
            PedidosDAO pedidosDAO = new PedidosDAO();
            if (!pedidosDAO.insere(pedido)) {
                mensagem("Erro ao cadastrar o pedido.");
                return;
            }

            // Obtém o ID do último pedido inserido
            int pedidoId = pedidosDAO.buscarUltimoIdPedido();
            if (pedidoId == 0) {
                mensagem("Erro ao obter o ID do pedido.");
                return;
            }

            // Insere os itens do carrinho na tabela 'itenspedido'
            ItensPedidoDAO itensPedidoDAO = new ItensPedidoDAO();
            for (ItemCarrinho item : carrinho) {
                
                ItensPedido itensPedido = new ItensPedido();
                itensPedido.setPedidoId(pedidoId); // Define o ID do pedido obtido
                itensPedido.setProdutoId(item.getProdutoId());
                itensPedido.setQuantidade(item.getQuantidade());
                itensPedido.setPreco(item.getPrecoUnitario());

                if (!itensPedidoDAO.insere(itensPedido)) {
                    mensagem("Erro ao cadastrar o item: " + item.getNome());
                    return;
                }
            }

            // Sucesso
            mensagem("Pedido cadastrado com sucesso!");

            // Limpeza
            carrinho.clear(); // Limpa o carrinho
            limparDados(); // Limpa os campos
            txtTotal.setText(""); // Zera o total exibido
            somaTotal = 0.0f; // Reinicia a soma total
            
        } catch (SQLException ex) {
            mensagem("Erro ao cadastrar pedido: " + ex.getMessage());
        }
    }

    
    @FXML
    private void btnLimpar_Click(ActionEvent event) {
        // Limpa o ObservableList do carrinho
        carrinho.clear();
        limparDados();
        mensagem("Carrinho limpo com sucesso!");
    }
    
    @FXML
    private void btnAdicionarCarrinho_Click(ActionEvent event) {
        Produto produtoSelecionado = cmbItem.getSelectionModel().getSelectedItem();

        if (produtoSelecionado != null) {
            try {
                int quantidade = Integer.parseInt(txtQuantidade.getText());
                if (quantidade <= 0) {
                    mensagem("A quantidade deve ser maior que zero.");
                    return;
                }

                // Cria o item do carrinho com base no produto selecionado
                ItemCarrinho itemCarrinho = new ItemCarrinho(
                        produtoSelecionado.getCodigo(), // Produto ID
                        produtoSelecionado.getNome(),   // Nome do produto
                        produtoSelecionado.getPreco(),  // Preço unitário
                        quantidade                      // Quantidade
                );

                // Adiciona o item ao carrinho
                carrinho.add(itemCarrinho);
                
                btnCadastrar.setDisable(false);
                
                // Atualiza o total
                somaTotal += produtoSelecionado.getPreco() * quantidade;
                txtTotal.setText(String.format("%.2f", somaTotal));
                mensagem("Produto adicionado ao carrinho.");
                
                limpaProdutoAdicionado();

            } catch (NumberFormatException ex) {
                mensagem("Por favor, insira um número válido para a quantidade.");
            }
        } else {
            mensagem("Por favor, selecione um produto antes de adicionar.");
        }
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
    
    private void limparDados() {
        txtPreco.setText("");
        txtTotal.setText("");
        txtQuantidade.setText("");
        cmbItem.setValue(null);
        cmbCategoria.setValue(null);        
    }
    
    private void limpaProdutoAdicionado() {
        txtPreco.setText("");
        txtQuantidade.setText("");
        cmbItem.setValue(null);
        cmbCategoria.setValue(null);        
    }
    
    private void carregarProdutosPorCategoria(int categoriaId) throws SQLException {
        listaProdutosPorCategoria.clear(); // Limpa a lista de produtos antes de carregar
        Collection<Produto> produtos = pedidosDAO.buscarProdutosPorCategoria(categoriaId);
        listaProdutosPorCategoria.addAll(produtos); // Adiciona os produtos encontrados à lista
        cmbItem.setItems(listaProdutosPorCategoria); // Atualiza a ComboBox de itens
    }
}