
package controle.DAO;

import controle.FabricaDeConexoes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.Cliente;
import modelo.Usuario;

public class ClienteDAO {
    private Connection con;
    private PreparedStatement stmt;
    private ResultSet rs;
    
    public ClienteDAO(){
        con = FabricaDeConexoes.getConnection();
    }
    
    public int registrarCliente(Cliente u) throws SQLException{
        int retorno = 0;
        String sql = "INSERT INTO cliente (nome,endereco,telefone,email) VALUES(?,?,?,?)";
        stmt = con.prepareStatement(sql);
        stmt.setString(1, u.getNome());
        stmt.setString(2, u.getEndereco());        
        stmt.setString(3, u.getTelefone());
        stmt.setString(4, u.getEmail());
        
        
        retorno = stmt.executeUpdate();
        
        stmt.close();
        con.close();
        return retorno;
    }
    
    public int removerRegistro(int id) throws SQLException{
        int retorno = 0;
        String sql = "DELETE FROM cliente WHERE id=?";
        stmt = con.prepareStatement(sql);
        stmt.setInt(1, id);
        
        retorno = stmt.executeUpdate();
        
        stmt.close();
        con.close();
        return retorno;
    }
    
    public int editarRegistro (Cliente u) throws SQLException{
        int retorno = 0;
        String sql = "UPDATE cliente SET nome=?, endereco=?, telefone=?, email=? WHERE id=?";
        stmt = con.prepareStatement(sql);
        stmt = con.prepareStatement(sql);
        stmt.setString(1, u.getNome());
        stmt.setString(2, u.getEndereco());        
        stmt.setString(3, u.getTelefone());
        stmt.setString(4, u.getEmail());
        stmt.setInt(5, u.getId());
        retorno = stmt.executeUpdate();
        
        stmt.close();
        con.close();
        return retorno;
    }
   
    
    public Usuario PesquisarPorId(String nome) throws SQLException{
        String sql = "SELECT * FROM usuario WHERE id LIKE ?";
        stmt = con.prepareStatement(sql);
        stmt.setString(1, nome + "%");
        
        rs = stmt.executeQuery();
        Usuario u = null;
        if(rs.next()){
            u = new Usuario();
            
            u.setId(rs.getInt("id"));
            u.setUsuario(rs.getString("usuario"));
            u.setTelefone(rs.getString("telefone"));
            u.setLogin(rs.getString("login"));
            u.setPerfil(rs.getString("perfil"));
            u.setSenha(rs.getString("senha"));
            
        }
        stmt.close();
        con.close();
        return u;
    }

    public ResultSet listaCliente(String nome) throws SQLException {
          String sql = "SELECT id as ID, nome as Nome, telefone as Telefone FROM cliente WHERE nome LIKE ?";
        stmt = con.prepareStatement(sql);
        stmt.setString(1, nome + "%");
        
        rs = stmt.executeQuery();
       
        return rs;
    }
}
