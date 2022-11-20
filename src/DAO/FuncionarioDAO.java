package DAO;

import DTO.FuncionarioDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class FuncionarioDAO {
    private Connection conn;
    private PreparedStatement pstm;
    private ResultSet rs;
    private ArrayList<FuncionarioDTO> lista;
    
    public void cadastrar(FuncionarioDTO funcionarioDTO) {
        String sql = "insert into funcionarios (nome, endereco) values (?, ?)";
        conn = ConexaoDAO.getInstancy().getConexao();
        
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, funcionarioDTO.getNome());
            pstm.setString(2, funcionarioDTO.getEndereco());
            pstm.execute();
            pstm.close();
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, "FuncionarioDAO cadastrar: " + e);
        }
    }
    
    public ArrayList<FuncionarioDTO> listar() {
        String sql = "select * from funcionarios";
        conn = ConexaoDAO.getInstancy().getConexao();
        
        try {
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            lista = new ArrayList<>();
            
            while(rs.next()) {
                FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
                funcionarioDTO.setId(rs.getInt("id"));
                funcionarioDTO.setNome(rs.getString("nome"));
                funcionarioDTO.setEndereco(rs.getString("endereco"));
                
                lista.add(funcionarioDTO);
            } 
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "FuncionarioDAO listar: " + e);
        }
        return lista;
    }
    
    public void alterar(FuncionarioDTO funcionarioDTO) {
        String sql = "update funcionarios set nome = ?, endereco = ? where id = ?";
        conn = ConexaoDAO.getInstancy().getConexao();
        
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, funcionarioDTO.getNome());
            pstm.setString(2, funcionarioDTO.getEndereco());
            pstm.setInt(3, funcionarioDTO.getId());
            pstm.execute();
            pstm.close();
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, "FuncionarioDAO alterar: " + e);
        }
    }
    
    public void excluir(FuncionarioDTO funcionarioDTO) {
        String sql = "delete from funcionarios where id = ?";
        conn = ConexaoDAO.getInstancy().getConexao();
        
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, funcionarioDTO.getId());
            pstm.execute();
            pstm.close();
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, "FuncionarioDAO excluir: " + e);
        }
    }
}
