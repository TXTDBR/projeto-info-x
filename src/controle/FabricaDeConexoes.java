
package controle;

import java.sql.*;

public class FabricaDeConexoes {
    
    public static Connection getConnection(){
        java.sql.Connection con = null;
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/dbsistema";
        String user = "root";
        String senha = "";
        try {
          Class.forName(driver);
          con = DriverManager.getConnection(url,user,senha);
          return con;
        } catch (Exception e) {
            //System.out.println(e);
            return null;
        }           
    }
}
