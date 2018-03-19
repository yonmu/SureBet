package SB_Model;
import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class StartupSQL {
    public static boolean create_newDB() throws Exception {
        Connection connection=null;
        try {
        	//Establish connection:
            MysqlDataSource ds = new MysqlConnectionPoolDataSource();
            ds.setServerName("localhost");
            connection=ds.getConnection("root","");
            Statement statement=connection.createStatement();
            //create the new DB:
            statement.executeUpdate("CREATE DATABASE surebetdb");
            //create new table:
            Startup_tableSQL.create_new_table();
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