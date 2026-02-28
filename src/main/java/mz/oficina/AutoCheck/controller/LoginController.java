package mz.oficina.AutoCheck.controller;

import mz.oficina.AutoCheck.dao.UsuarioDAO;
import mz.oficina.AutoCheck.model.Usuario;
import mz.oficina.AutoCheck.application.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML private TextField txtUser;
    @FXML private PasswordField txtPassword;

    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    @FXML
    private void handleLogin(ActionEvent event) {
        String username = txtUser.getText();
        String senha = txtPassword.getText();

        Usuario usuario = usuarioDAO.autenticar(username, senha);

        if (usuario != null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Login realizado com sucesso!");
            alert.setContentText("Bem-vindo, " + usuario.getNome() + "\nNível: " + usuario.getNivelAcesso());
            alert.showAndWait();

            // Abre o dashboard
            new App().abrirDashboard(usuario);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Falha no login");
            alert.setContentText("Usuário ou senha incorretos!");
            alert.show();
        }
    }
}
