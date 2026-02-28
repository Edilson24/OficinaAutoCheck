package mz.oficina.AutoCheck.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mz.oficina.AutoCheck.model.Usuario;

import java.io.IOException;

public class App extends Application {

    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        abrirLogin();
    }

    public void abrirLogin() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/mz/oficina/AutoCheck/views/login.fxml"));
        Scene scene = new Scene(loader.load());
        primaryStage.setScene(scene);
        primaryStage.setTitle("AutoCheck - Login");
        primaryStage.show();
    }

    public void abrirDashboard(Usuario usuario) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/mz/oficina/AutoCheck/views/dashboard.fxml"));
            Scene scene = new Scene(loader.load());

            // Passa o usuário para o DashboardController
            mz.oficina.AutoCheck.controller.DashboardController controller = loader.getController();
            controller.setUsuario(usuario);

            primaryStage.setScene(scene);
            primaryStage.setTitle("AutoCheck - Dashboard");
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
