package mz.oficina.AutoCheck.controller;

import mz.oficina.AutoCheck.dao.VeiculoDAO;
import mz.oficina.AutoCheck.model.Cliente;
import mz.oficina.AutoCheck.model.Veiculo;
import mz.oficina.AutoCheck.dao.ClienteDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class VeiculoController {

    @FXML
    private TableView<Veiculo> tableVeiculos;

    @FXML
    private TableColumn<Veiculo, Integer> colId;

    @FXML
    private TableColumn<Veiculo, String> colMatricula;

    @FXML
    private TableColumn<Veiculo, String> colMarca;

    @FXML
    private TableColumn<Veiculo, String> colModelo;

    @FXML
    private TableColumn<Veiculo, String> colCliente;

    @FXML
    private TextField txtMatricula;

    @FXML
    private TextField txtMarca;

    @FXML
    private TextField txtModelo;

    @FXML
    private ComboBox<Cliente> cbCliente;

    private VeiculoDAO veiculoDAO = new VeiculoDAO();
    private ClienteDAO clienteDAO = new ClienteDAO();
    private ObservableList<Veiculo> listaVeiculos = FXCollections.observableArrayList();
    private ObservableList<Cliente> listaClientes = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        colId.setCellValueFactory(cell -> new javafx.beans.property.SimpleIntegerProperty(cell.getValue().getId()).asObject());
        colMatricula.setCellValueFactory(new PropertyValueFactory<>("matricula"));
        colMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        colModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        colCliente.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getCliente().getNome()));

        listaClientes.setAll(clienteDAO.listarTodos());
        cbCliente.setItems(listaClientes);

        atualizarTabela();
    }

    @FXML
    private void salvarVeiculo() {
        Veiculo v = new Veiculo();
        v.setMatricula(txtMatricula.getText());
        v.setMarca(txtMarca.getText());
        v.setModelo(txtModelo.getText());
        v.setCliente(cbCliente.getSelectionModel().getSelectedItem());

        if (veiculoDAO.salvar(v)) {
            atualizarTabela();
            limparCampos();
            exibirAlerta(Alert.AlertType.INFORMATION, "Sucesso", "Veículo salvo com sucesso!");
        } else {
            exibirAlerta(Alert.AlertType.ERROR, "Erro", "Falha ao salvar veículo.");
        }
    }

    @FXML
    private void deletarVeiculo() {
        Veiculo selecionado = tableVeiculos.getSelectionModel().getSelectedItem();
        if (selecionado != null && veiculoDAO.deletar(selecionado)) {
            atualizarTabela();
            exibirAlerta(Alert.AlertType.INFORMATION, "Sucesso", "Veículo deletado com sucesso!");
        } else {
            exibirAlerta(Alert.AlertType.ERROR, "Erro", "Selecione um veículo ou ocorreu um erro.");
        }
    }

    private void atualizarTabela() {
        listaVeiculos.setAll(veiculoDAO.listarTodos());
        tableVeiculos.setItems(listaVeiculos);
    }

    private void limparCampos() {
        txtMatricula.clear();
        txtMarca.clear();
        txtModelo.clear();
        cbCliente.getSelectionModel().clearSelection();
    }

    private void exibirAlerta(Alert.AlertType tipo, String titulo, String msg) {
        Alert alert = new Alert(tipo);
        alert.setHeaderText(titulo);
        alert.setContentText(msg);
        alert.show();
    }
}
