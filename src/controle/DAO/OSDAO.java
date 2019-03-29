
package controle.DAO;

import controle.FabricaDeConexoes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.OS;

public class OSDAO {
    private Connection con;
    private PreparedStatement stmt;
    private ResultSet rs;
    
    public OSDAO(){
        con = new FabricaDeConexoes().getConnection();
    }
    
    public int registrarOS(OS os) throws SQLException{
        int retorno = 0;
        String sql = "INSERT INTO tblos (tipo,situacao,equipamento,defeito,servico,tecnico,valor,idcliente) VALUES(?,?,?,?,?,?,?,?)";
        stmt = con.prepareStatement(sql);
        stmt.setString(1, os.getTipo());
        stmt.setString(2, os.getSituacao());
        stmt.setString(3, os.getEquipamento());
        stmt.setString(4, os.getDefeito());
        stmt.setString(5, os.getServico());        
        stmt.setString(6, os.getTecnico());
        stmt.setString(7, os.getValor());
        stmt.setInt(8, os.getIdCliente());
        
        
        retorno = stmt.executeUpdate();
        
        stmt.close();
        con.close();
        return retorno;
    }
    
     public int removerRegistro(int id) throws SQLException{
        int retorno = 0;
        String sql = "DELETE FROM tblos WHERE id=?";
        stmt = con.prepareStatement(sql);
        stmt.setInt(1, id);
        
        retorno = stmt.executeUpdate();
        
        stmt.close();
        con.close();
        return retorno;
    }
     
      public int editarRegistro (OS os) throws SQLException{
        int retorno = 0;
        String sql = "update tblos set tipo=?, situacao=?, equipamento=?, defeito=?, servico=?, tecnico=?, valor=? where id=?";
        stmt = con.prepareStatement(sql);  
        stmt.setString(1, os.getTipo());
        stmt.setString(2, os.getSituacao());
        stmt.setString(3, os.getEquipamento());
        stmt.setString(4, os.getDefeito());
        stmt.setString(5, os.getServico());        
        stmt.setString(6, os.getTecnico());
        stmt.setString(7, os.getValor());
        stmt.setInt(8, os.getId());
        
        retorno = stmt.executeUpdate();
        System.out.print(retorno);
        stmt.close();
        con.close();
        return retorno;
    }
      
      public OS buscarOS(int id) throws SQLException{
          String sql = "SELECT * FROM tblos WHERE id=?";
          stmt = con.prepareStatement(sql);
          stmt.setInt(1, id);
          rs = stmt.executeQuery();
          OS os = null;
          if(rs.next()){
              os = new OS();
              os.setId(rs.getInt("id"));
              os.setData(rs.getString("data"));
              os.setTipo(rs.getString("tipo"));
              os.setSituacao(rs.getString("situacao"));
              os.setEquipamento(rs.getString("equipamento"));
              os.setDefeito(rs.getString("defeito"));
              os.setServico(rs.getString("servico"));
              os.setTecnico(rs.getString("tecnico"));
              os.setValor(rs.getString("valor"));
              os.setIdCliente(rs.getInt("idcliente"));
              
          }
          return os;
      }
}
