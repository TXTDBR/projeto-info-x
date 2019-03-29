
package controle.DAO;

import controle.FabricaDeConexoes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.Usuario;

public class UsuarioDAO {
    private Connection con;
    private PreparedStatement stmt;
    private ResultSet rs;
    
    public UsuarioDAO(){
        con = FabricaDeConexoes.getConnection();
    }
    
    public int registrarUsuario(Usuario user) throws SQLException{
        int retorno = 0;
        String sql = "INSERT INTO usuario (usuario,telefone,login,senha,perfil) VALUES(?,?,?,?,?)";
        stmt = con.prepareStatement(sql);
        stmt.setString(1, user.getUsuario());
        stmt.setString(2, user.getTelefone());
        stmt.setString(3, user.getLogin());
        stmt.setString(4, user.getSenha());
        stmt.setString(5, user.getPerfil());
        
        retorno = stmt.executeUpdate();
        
        stmt.close();
        con.close();
        return retorno;
    }
    
    public int removerRegistro(int id) throws SQLException{
        int retorno = 0;
        String sql = "DELETE FROM usuario WHERE id=?";
        stmt = con.prepareStatement(sql);
        stmt.setInt(1, id);
        
        retorno = stmt.executeUpdate();
        
        stmt.close();
        con.close();
        return retorno;
    }
    
    public int editarRegistro (Usuario user) throws SQLException{
        int retorno = 0;
        String sql = "UPDATE usuario SET usuario=?, telefone=?, login=?, senha=?, perfil=? WHERE id=?";
        stmt = con.prepareStatement(sql);
        stmt.setString(1, user.getUsuario());
        stmt.setString(2, user.getTelefone());
        stmt.setString(3, user.getLogin());
        stmt.setString(4, user.getSenha());
        stmt.setString(5, user.getPerfil());
        stmt.setInt(6, user.getId());
        retorno = stmt.executeUpdate();
        
        stmt.close();
        con.close();
        return retorno;
    }
    
    public boolean logar(String login, String senha) throws SQLException{
        boolean status = false;
        String sql = "SELECT * FROM usuario WHERE login=? AND senha=?";
        stmt = con.prepareStatement(sql);
        stmt.setString(1, login);
        stmt.setString(2, senha);
        
        rs = stmt.executeQuery();
        
        if(rs.next()){
            status = true;
    }
        stmt.close();
        rs.close();
        con.close();
       return status;
    }
    
    public Usuario getUsuarioPorLogin(String usuario) throws SQLException{
        String sql = "SELECT * FROM usuario WHERE login=?";
        stmt = con.prepareStatement(sql);
        stmt.setString(1, usuario);
        
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
        rs.close();
        con.close();
        return u;
    }
    
    public Usuario PesquisarPorId(int id) throws SQLException{
        String sql = "SELECT * FROM usuario WHERE id=?";
        stmt = con.prepareStatement(sql);
        stmt.setInt(1, id);
        
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
        rs.close();
        con.close();
        return u;
    }
}
