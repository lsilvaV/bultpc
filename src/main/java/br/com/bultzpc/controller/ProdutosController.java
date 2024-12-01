package br.com.bultzpc.controller;

import br.com.bultzpc.dao.ProdutosDAO;
import br.com.bultzpc.model.Produto;
import br.com.bultzpc.model.Categoria;

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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author lucas
 */
public class ProdutosController implements Initializable {

    @FXML
    private Button btnFuncionarios;
    @FXML
    private Button btnConsulta;
    @FXML
    private Button btnClientes;
    @FXML
    private Button btnPedidos;
    @FXML
    private Button btnMenu;
    @FXML
    private Button btnSair;
    @FXML
    private TextField txtCodigo;
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtPreco;
    @FXML
    private ComboBox<Categoria> cmbCategoria;
    @FXML
    private Button btnCadastrar;
    @FXML
    private Button btnAlterar;
    @FXML
    private Button btnDeletar;
    @FXML
    private Button btnPesquisar;

    private Produto produto;
    private ProdutosDAO produtosDAO = new ProdutosDAO();
    private ObservableList<Categoria> listaProdutos =  
            FXCollections.observableArrayList(); 
    @FXML
    private Button btnProdutos;
    @FXML
    private TextArea txtDescricao;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarCombo();
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

        if (validarDados() == false) {
            return;
        }
        produto = carregarModel();

        try {
            if (produtosDAO.insere(produto)) {
                mensagem("Produto incluido com sucesso");
                limparDados();
                desativarBotoes();
            }
        } catch (SQLException ex) {
            mensagem("Erro na inclusão\n" + ex.getMessage());
        }
    }

    @FXML
    private void btnAlterar_Click(ActionEvent event) {

        if (validarDados() == false) {
            return;
        }

        produto = carregarModel();

        try {
            if (produtosDAO.altera(produto)) {
                mensagem("Produto alterado com sucesso");
                limparDados();
                desativarBotoes();
            } else {
                mensagem("Erro na alteração");
            }
        } catch (SQLException ex) {
            mensagem("Erro na Gravação\n" + ex.getMessage());
        }
    }

    @FXML
    private void btnDeletar_Click(ActionEvent event) {
        
        produto = new Produto();
        produto.setCodigo(Integer.parseInt(txtCodigo.getText()));
        
        try{
            if(produtosDAO.remove(produto)){
                mensagem("Produto excluido com sucesso");
                limparDados();
                btnCadastrar.setDisable(true);
                btnAlterar.setDisable(true);
                btnDeletar.setDisable(true);
                
            }else{
                mensagem("Ocorreu algum erro para exclusão");
            }
        }catch (SQLException ex){
            mensagem("Erro na exclusão\n" + ex.getMessage());
        }
    }

    @FXML
    private void btnPesquisar_Click(ActionEvent event) {
        try {
            if (produto == null) {
                produto = new Produto();
                
            }

            produto.setCodigo(Integer.parseInt(txtCodigo.getText()));

            // Captura o retorno do método buscaID
            produto = produtosDAO.buscaID(produto);

            if (produto != null) {
                txtNome.setText(produto.getNome());
                txtPreco.setText(String.valueOf(produto.getPreco()));

                // Localiza a categoria correspondente pelo ID na lista da ComboBox
                for (Categoria cat : cmbCategoria.getItems()) {
                    if (cat.getId() == produto.getCategoria()) { // Compara IDs
                        cmbCategoria.setValue(cat); // Define a categoria correspondente como selecionada
                        break;
                    }
                }  
                txtDescricao.setText(produto.getDescricao());
                btnAlterar.setDisable(false);
                btnDeletar.setDisable(false);
                
            } else {
                mensagem("Produto não encontrado. Faça o cadastro do produto.");
                desativarBotoes();
                btnCadastrar.setDisable(false);
                limparDadosPesquisa();
            }
        } catch (SQLException ex) {
            mensagem("Erro na busca\n" + ex.getMessage());
        } catch (NumberFormatException ex) {
            mensagem("Código inválido. Por favor, insira um número.");
        }
    }


    private Produto carregarModel() {
        Produto model = new Produto();
        model.setCodigo(Integer.parseInt(txtCodigo.getText()));
        model.setNome(txtNome.getText());
        model.setPreco(Float.parseFloat(txtPreco.getText()));

        Categoria categoriaSelecionada = cmbCategoria.getValue(); // Obtém o objeto selecionado
        if (categoriaSelecionada != null) {
            model.setCategoria(categoriaSelecionada.getId()); // Usa o ID da categoria
        }
        model.setDescricao(txtDescricao.getText());

        return model;
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

    private boolean validarDados() {
        if (txtCodigo.getText().length() == 0
                || txtPreco.getText().length() == 0
                || txtNome.getText().length() == 0
                || cmbCategoria.getValue() == null
                || txtDescricao.getText().length() == 0) {

            mensagem("Por favor, preencha todos os dados");
            return false;
        } else {
            return true;
        }
    }

    private void limparDados() {
        txtCodigo.setText("");
        txtPreco.setText("");
        txtNome.setText("");
        cmbCategoria.setValue(null);
        txtDescricao.setText("");
        txtCodigo.requestFocus();
    }
    
    private void limparDadosPesquisa(){
        txtPreco.setText("");
        txtNome.setText("");
        cmbCategoria.setValue(null);
        txtDescricao.setText("");
        txtNome.requestFocus();
    }
    
    public void desativarBotoes(){
        btnAlterar.setDisable(true);
        btnDeletar.setDisable(true);
        btnCadastrar.setDisable(true);
    }

}
