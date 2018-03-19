package SB_Model;
import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginSQL {
    public static boolean checkUser(String new_user, String password) throws Exception {
        Connection connection=null;
        try {
            MysqlDataSource ds = new MysqlConnectionPoolDataSource();
            ds.setServerName("localhost");
            ds.setDatabaseName("surebetdb");
            connection=ds.getConnection("root","");
            Statement statement=connection.createStatement();
            ResultSet rs2=statement.executeQuery("SELECT * FROM surebetdb.sb_users1;");
        } 
        catch (SQLException e) {
        	StartupSQL.create_newDB();
            e.printStackTrace();
    	}
        
        try {
        	//Establish connection:
            MysqlDataSource ds = new MysqlConnectionPoolDataSource();
            ds.setServerName("localhost");
            ds.setDatabaseName("surebetdb");
            connection=ds.getConnection("root","");
            Statement statement=connection.createStatement();
            String Uname=new_user;
            String pass=password;
            //Check if user exists and if password matches:
            ResultSet rs=statement.executeQuery("SELECT * FROM surebetdb.sb_users1 WHERE Name='"+Uname+"';");
  	        rs.last();
  	        //Get var's with sessions:
            String rsUsername = rs.getString("Name");
            String rsPassword = rs.getString("Password");

            if (rsPassword.equals(pass) && rsUsername != null) //username exists and password matches
            {
            }
            else	//Bad inputs
            {
            	return false;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
    		return false;
            
        } finally {
        	//Close connection:
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}