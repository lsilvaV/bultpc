package br.com.bultzpc.controller;

import br.com.bultzpc.model.Cliente;
import java.sql.Date;
import java.time.LocalDate;
import br.com.bultzpc.dao.ClientesDAO;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ClientesController implements Initializable {

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
    private TextField txtCPF;
    @FXML
    private TextField txtNome;
    @FXML
    private Button btnCadastrar;
    @FXML
    private Button btnAlterar;
    @FXML
    private Button btnDeletar;
    @FXML
    private TextField txtEndereco;
    @FXML
    private TextField txtEmail;
    @FXML
    private DatePicker dpDataNasc;

    private ClientesDAO clienteDAO = new ClientesDAO();
    private Cliente cliente;
    @FXML
    private Button btnPesquisar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

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
    private void btnPesquisar_Click(ActionEvent event) {
        if(txtCPF.getText().length() != 11)  {
            mensagem("Digite um CPF válido");
        }
        try {
            // Inicializa o objeto cliente se ele for nulo
            if (cliente == null) {
                cliente = new Cliente();
            }

            cliente.setCpf(txtCPF.getText()); // Preenche o CPF

            cliente = clienteDAO.buscaID(cliente); // Busca no banco

            if (cliente != null) {
                // Preenche os campos com os dados do cliente encontrado
                txtNome.setText(cliente.getNome());
                txtEndereco.setText(cliente.getEndereco());
                txtEmail.setText(cliente.getEmail());
                dpDataNasc.setValue(cliente.getDataNasc().toLocalDate());
                
                // Habilitando os botões de alteração de um cliente já existente
                btnDeletar.setDisable(false);
                btnAlterar.setDisable(false);
                
            } else {
                mensagem("CPF não encontrado. Faça o cadastro do cliente");
                limparDadosPesquisa();
                btnCadastrar.setDisable(false);
                btnDeletar.setDisable(true);
                btnAlterar.setDisable(true);
            }
        } catch (SQLException ex) {
            mensagem("Erro na busca\n" + ex.getMessage());
        }
    }

    @FXML
    private void btnCadastrar_Click(ActionEvent event) {
        //verifica se os dados estão preenchidos
        if (validarDados() == false) {
            return;
        }

        //move dados da tela para model
        cliente = carregarModel();

        try {
            if (clienteDAO.insere(cliente)) {
                mensagem("Cliente incluido com sucesso!");
                limparDados();
                desativarBotoes();

            } else {
                mensagem("Erro na Inclusão");
            }
        } catch (SQLException ex) {
            mensagem("Erro na Gravação\n" + ex.getMessage());
        }
    }

    @FXML
    private void btnAlterar_Click(ActionEvent event) {
        //verifica se os dados estão preenchidos
        if (validarDados() == false) {
            return;
        }

        //move dados da tela para model
        cliente = carregarModel();

        try {
            if (clienteDAO.altera(cliente)) {
                mensagem("Cliente alterado com sucesso!");
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
        cliente = new Cliente();
        cliente.setCpf(txtCPF.getText());
        
        try {
            if(clienteDAO.remove(cliente)) {
                mensagem("Cliente excluido com sucesso");
                limparDados();
            } 
            else {
                mensagem("Ocorreu algum erro para exclusão");
            }
        } catch (SQLException ex) {
            mensagem("Erro na Exclusão\n" + ex.getMessage());
        }
    }
    
    /**
     * Produz um modelo de mensagem em formato de popup
     * @param msg 
     */
    private void mensagem(String msg) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Mensagem");
        alerta.setHeaderText(msg);
        alerta.setContentText("");

        alerta.showAndWait(); //exibe a mensage
    }
    
    /**
     * Valida os dados de todos os campos da tela de clientes
     * @return 
     */
    private boolean validarDados() {
        if (txtCPF.getText().length() == 0
                || txtNome.getText().length() == 0
                || dpDataNasc.getValue() == null
                || txtEndereco.getText().length() == 0
                || txtEmail.getText().length() == 0) {
            mensagem("Por favor, preencha todos os dados");
            return false;

        } else if (txtCPF.getText().length() != 11) {
            mensagem("Digite um CPF válido");
            return false;

        } else {
            return true;
        }
    }
    
    /**
     * Carrega a model dos clientes
     * @return 
     */
    private Cliente carregarModel() {
        Cliente model = new Cliente();
        //model.setPlaca(txtPlaca.getText());
        //model.setProprietario(cbProprietario.getValue());
        //model.setModelo(txtModelo.getText());
        //model.setValor(Double.parseDouble(txtValor.getText()));

        // Convertendo data
        LocalDate dataNasc = dpDataNasc.getValue();
        Date dataConvertida = Date.valueOf(dataNasc);

        model.setCpf(txtCPF.getText());
        model.setNome(txtNome.getText());
        model.setDataNasc(dataConvertida);
        model.setEndereco(txtEndereco.getText());
        model.setEmail(txtEmail.getText());

        //devolve o model
        return model;
    }

    public void limparDados() {
        // Limpa os campos de texto
        txtCPF.setText("");
        txtNome.setText("");
        txtEndereco.setText("");
        txtEmail.setText("");

        // Limpa o campo de Data de Nascimento (DatePicker)
        dpDataNasc.setValue(null);

        // Habilita a inclusão dos botões
        //habilitarBotoes(true);
        // Manda o foco para o campo CPF
        txtCPF.requestFocus();
    }
    
    public void limparDadosPesquisa() {
        // Limpa os campos de texto
        txtNome.setText("");
        txtEndereco.setText("");
        txtEmail.setText("");

        // Limpa o campo de Data de Nascimento (DatePicker)
        dpDataNasc.setValue(null);

        // Habilita a inclusão dos botões
        //habilitarBotoes(true);
        // Manda o foco para o campo CPF
        txtNome.requestFocus();
    }
    
    public void desativarBotoes(){
        btnAlterar.setDisable(true);
        btnDeletar.setDisable(true);
        btnCadastrar.setDisable(true);
    }
}