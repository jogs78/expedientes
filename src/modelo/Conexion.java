/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author moisesvelasco
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
public class Conexion {
    Connection conec;
    
    String url="jdbc:mysql://127.0.0.1:3306/ExpedientesMedicos";
    String user="root";
    String pass="";
      
    public Connection Conectar(){
        try{
            
            Class.forName("com.mysql.jdbc.Driver");
            //Class.forName("com.mysql.cj.jdbc.Driver");
            //
            conec= (Connection) DriverManager.getConnection(url, user, pass);
            
        }
        catch(ClassNotFoundException | SQLException e){
              JOptionPane.showMessageDialog(null, "Error de en conexion: " + e.getMessage() );
       
        }
    
    return conec;
    
    }
    
}
