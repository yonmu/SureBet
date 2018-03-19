package SB_Model;
import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SigninSQL {
    public static boolean insertUser(String new_user, String password) {
        Connection connection=null;
        try {
        	//Establish connection:
            MysqlDataSource ds = new MysqlConnectionPoolDataSource();
            ds.setServerName("localhost");
            ds.setDatabaseName("surebetdb");
            connection=ds.getConnection("root","");
            Statement statement=connection.createStatement();
            String name=new_user;
            String pass=password;
            //Check if username exists:
            ResultSet rs=statement.executeQuery("SELECT * FROM surebetdb.sb_users1 WHERE Name='"+new_user+"';");
            if (rs.next()) 
            {    
            	return false;
            }
            //Set values of new user:
            statement.executeUpdate("INSERT INTO surebetdb.sb_users1 (Name, Password, Number_FG, Number_BG, Average_Profit )"
            			+ " " + "VALUES ('"+name+"', '"+pass+"',0, 0, 0)");

        } catch (SQLException e) {
            e.printStackTrace();
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
