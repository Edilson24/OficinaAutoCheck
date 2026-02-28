package mz.oficina.AutoCheck.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    public static Connection getConnection()  {
        String url, user, password;

        try {
            url = "jdbc:mysql://localhost:3306/globalnet_db?useSSL=false&serverTimezone=UTC";
            user = "root";
            password = "root";

            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("CONEXAO EFETUADA-JOVEM DIFERENTE!");
            return conn;
        } catch (Exception e) {
            System.err.println("ERRO DE CONEXAO");
            e.printStackTrace();
            return null;
        }
    }
}
