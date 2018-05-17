package prorepor.home;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnection {
    public Connection Connect(){
        try{
        String url="jdbc:mysql://localhost:3307/prorepor";
        String user="root";
        String password="mabvuto@534173!";
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn=(Connection) DriverManager.getConnection(url, user, password);
        return conn;
                }
        catch(ClassNotFoundException | SQLException ex){
        Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null,ex);
        }
        return null;
    }
}
