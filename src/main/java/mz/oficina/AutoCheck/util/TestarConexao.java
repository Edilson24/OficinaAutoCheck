package mz.oficina.AutoCheck.util;

public class TestarConexao {

    static void main() {
        Database connect = new Database();
        connect.getConnection();
    }

}
