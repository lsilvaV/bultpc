/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.bultzpc.controller;

import br.com.bultzpc.estado.Estado;
import br.com.bultzpc.model.Funcionario;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.time.LocalDate;
import java.time.ZoneId;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author lucas
 */
public class FuncionariosController implements Initializable {

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
    @FXML
    private DatePicker dpDataNasc;

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

        if (txtCPF.getText().length() != 11) {
            mensagem("Digite um CPF válido");
        }
        boolean encontrado = false; // Variável de controle

        try {
            for (Funcionario f : funcionarios) {
                if (f.getCpf().equals(txtCPF.getText())) {
                    encontrado = true;
                    txtNome.setText(f.getNome());

                    Date dataNasc = f.getDataNasc(); // A data que está em Date
                    LocalDate localDate = dataNasc.toInstant() // Converte para Instant
                            .atZone(ZoneId.systemDefault()) // Converte para o fuso horário do sistema
                            .toLocalDate(); // Converte para LocalDate

                    dpDataNasc.setValue(localDate); // Define o valor no DatePicker
                    txtCargo.setText(f.getCargo()); // Caso precise exibir o cargo
                    txtSalario.setText(String.valueOf(f.getSalario()));
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
        if (funcionario == null) {
            funcionario = new Funcionario();
        }
        boolean encontrado = false;

        try {
            for (Funcionario f : funcionarios) {
                if (f.getCpf().equals(txtCPF.getText())) {
                    encontrado = true;
                    funcionarios.remove(f);
                    limparDados();
                    desativarBotoes();
                    break;
                }
            }
            if (encontrado) {
                mensagem("Usuário excluído com sucesso!");

            }
        } catch (Exception ex) {
            mensagem("Erro na exclusão\n" + ex.getMessage());
        }

    }

    @FXML
    private void btnAlterar_Click(ActionEvent event) {
        if (funcionario == null) {
            funcionario = new Funcionario();
        }
        boolean alterado = false;

        try {
            for (Funcionario f : funcionarios) {
                if (f.getCpf().equals(txtCPF.getText())) {
                    if (validarDados() == false){
                        return;
                    }
                    f.setCpf(txtCPF.getText());
                    f.setNome(txtNome.getText());
                    f.setCargo(txtCargo.getText());
                    LocalDate localDate = dpDataNasc.getValue();
                    Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
                    f.setDataNasc(date);
                    f.setCpf(txtCPF.getText());
                    alterado = true;
                    limparDados();
                    desativarBotoes();
                }
            }
            if (alterado) {
                mensagem("Usuário alterado com sucesso!");
            }
        } catch (Exception ex) {
            mensagem("Erro na alteração do funcionário \n" + ex.getMessage());
        }
    }

    @FXML
    private void btnCadastrar_Click(ActionEvent event) {
        
        
        if (validarDados() == false) {
            return;
        }
        if (funcionario == null) {
            funcionario = new Funcionario();
        }
        
        try {
            funcionario.setCpf(txtCPF.getText());
            funcionario.setCargo(txtCargo.getText());
            LocalDate localDate = dpDataNasc.getValue();
            Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            funcionario.setDataNasc(date);
            funcionario.setNome(txtNome.getText());
            funcionario.setSalario(Float.parseFloat(txtSalario.getText()));
            funcionarios.add(funcionario);
            limparDados();
            desativarBotoes();
            mensagem("Funcionário cadastrado com sucesso");
        }catch(Exception ex){
            mensagem("Não foi possível cadastrar o funcionário\n" + ex.getMessage());
        }

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

    public void limparDados() {
        txtCPF.setText("");
        txtNome.setText("");
        txtCargo.setText("");
        dpDataNasc.setValue(null);
        txtSalario.setText("");
        txtCPF.requestFocus();
    }

    public void desativarBotoes() {
        btnAlterar.setDisable(true);
        btnDeletar.setDisable(true);
        btnCadastrar.setDisable(true);
    }

    public boolean validarDados() {
        if (txtCPF.getText().length() == 0
                || txtNome.getText().length() == 0
                || dpDataNasc.getValue() == null
                || txtSalario.getText().length() == 0) {
            mensagem("Por favor, preencha todos os dados");
            return false;
        } else if (txtCPF.getText().length() != 11) {
            mensagem("Digite um CPF válido");
            return false;
        } else {
            return true;
        }
    }
}
