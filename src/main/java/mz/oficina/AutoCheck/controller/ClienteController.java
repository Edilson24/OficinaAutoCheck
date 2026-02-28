package mz.oficina.AutoCheck.controller;

import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import mz.oficina.AutoCheck.dao.ClienteDAO;
import mz.oficina.AutoCheck.model.Cliente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.fxml.FXML;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class ClienteController implements Initializable {

    @FXML
    private TableView<Cliente> tableClientes;

    @FXML
    private TableColumn <Cliente, Integer> colId;

    @FXML
    private TableColumn <Cliente, String> colNome;

    @FXML
    private TableColumn <Cliente, String> colTelefone;

    @FXML
    private TableColumn<Cliente, String> colEmail;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtTelefone;

    @FXML
    private TextField txtEmail;

    private ClienteDAO clienteDAO = new ClienteDAO();
    private ObservableList<Cliente> listaClientes = FXCollections.observableArrayList();



    @FXML
    private void salvarCliente() {
        Cliente c = new Cliente();
        c.setNome(txtNome.getText());
        c.setTelefone(txtTelefone.getText());
        c.setEmail(txtEmail.getText());

        if (clienteDAO.salvar(c)) {
            atualizarTabela();
            limparCampos();
            exibirAlerta(Alert.AlertType.INFORMATION, "Sucesso", "Cliente salvo com sucesso!");
        } else {
            exibirAlerta(Alert.AlertType.ERROR, "Erro", "Falha ao salvar cliente.");
        }
    }

    @FXML
    private void deletarCliente() {
        Cliente selecionado = tableClientes.getSelectionModel().getSelectedItem();
        if (selecionado != null && clienteDAO.deletar(selecionado)) {
            atualizarTabela();
            exibirAlerta(Alert.AlertType.INFORMATION, "Sucesso", "Cliente deletado com sucesso!");
        } else {
            exibirAlerta(Alert.AlertType.ERROR, "Erro", "Selecione um cliente ou ocorreu um erro.");
        }
    }

    private void atualizarTabela() {
        listaClientes.setAll(clienteDAO.listarTodos());
        tableClientes.setItems(listaClientes);
    }

    private void limparCampos() {
        txtNome.setText("");
        txtTelefone.setText("");
        txtEmail.setText("");
    }

    private void exibirAlerta(Alert.AlertType tipo, String titulo, String msg) {
        Alert alert = new Alert(tipo);
        alert.setHeaderText(titulo);
        alert.setContentText(msg);
        alert.show();
    }

    @FXML
    private void initializ() {
        // Corrigido TableColumn de int para IntegerProperty
        colId.setCellValueFactory(cell -> new javafx.beans.property.SimpleIntegerProperty(cell.getValue().getId()).asObject());
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        atualizarTabela();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
