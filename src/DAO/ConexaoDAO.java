package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ConexaoDAO {
    private Connection conn;
    private String url = "jdbc:mysql://localhost:3306/projeto_final?user=root&password=";
    private static ConexaoDAO instancia;
    
    private ConexaoDAO() {
        try {
            this.conn = DriverManager.getConnection(url);
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public static ConexaoDAO getInstancy() {
        if(instancia == null) {
            instancia = new ConexaoDAO();
        }
        return instancia;
    }
    
    public Connection getConexao() {
        return this.conn;
    }
}
