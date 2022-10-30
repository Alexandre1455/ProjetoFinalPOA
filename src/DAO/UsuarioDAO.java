package DAO;

import DTO.UsuarioDTO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

public class UsuarioDAO {
    Connection conn;
    
    public ResultSet autenticar(UsuarioDTO usuarioDTO) {
        conn = new ConexaoDAO().conectar();
        
        try {
            String sql = "Select * from usuarios where nome = ? and senha = ?";
            
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, usuarioDTO.getNome());
            pstm.setString(2, usuarioDTO.getSenha());
            
            ResultSet rs = pstm.executeQuery();
            return rs;
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
}
