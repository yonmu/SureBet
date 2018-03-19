package SB_Model;
import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Startup_tableSQL {
    public static boolean create_new_table() throws Exception {
        Connection connection=null;
        try {
        	//Establish connection and create DB table:
            MysqlDataSource ds = new MysqlConnectionPoolDataSource();
            ds.setServerName("localhost");
            ds.setDatabaseName("surebetdb");
            connection=ds.getConnection("root","");
            Statement statement=connection.createStatement();
            statement.executeUpdate("CREATE TABLE sb_users1 (\r\n" + 
            		"    ID int NOT NULL AUTO_INCREMENT,\r\n" + 
            		"    Name varchar(255) NOT NULL,\r\n" + 
            		"    Password varchar(255),\r\n" + 
            		"    Number_BG int,\r\n" + 
            		"    Number_FG int,\r\n" + 
            		"    Average_Profit double,\r\n" + 
            		"    PRIMARY KEY (ID)\r\n" + 
            		");");
            //create first user
            statement.executeUpdate("INSERT INTO surebetdb.sb_users1 (Name, Password, Number_FG, Number_BG, Average_Profit )"
        			+ " " + "VALUES ('SureBet', '123456', 0, 0, 0)");

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