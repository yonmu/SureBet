package SB_Model;
import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateSQL {
    
	public static void update_BBG(String user) {
        Connection connection=null;
        try {
        	//Establish connection:
            MysqlDataSource ds = new MysqlConnectionPoolDataSource();
            ds.setServerName("localhost");
            ds.setDatabaseName("surebetdb");
            connection=ds.getConnection("root","");
            Statement statement=connection.createStatement();
            //Increment number of Basketball games:
            statement.executeUpdate("UPDATE surebetdb.sb_users1 SET Number_BG = Number_BG + 1 WHERE Name='"+user+"';");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
	
	public static void update_FBG(String user) {
        Connection connection=null;
        try {
        	//Establish connection:
            MysqlDataSource ds = new MysqlConnectionPoolDataSource();
            ds.setServerName("localhost");
            ds.setDatabaseName("surebetdb");
            connection=ds.getConnection("root","");
            Statement statement=connection.createStatement();
            //Increment number of Football games:
            statement.executeUpdate("UPDATE surebetdb.sb_users1 SET Number_FG = Number_FG + 1 WHERE Name='"+user+"';");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
	
	public static void update_AP(String user, double cur_profit) {
        Connection connection=null;
        try {
        	//Establish connection:
            MysqlDataSource ds = new MysqlConnectionPoolDataSource();
            ds.setServerName("localhost");
            ds.setDatabaseName("surebetdb");
            connection=ds.getConnection("root","");
            Statement statement=connection.createStatement();
            //Update value of average profit:
            ResultSet rs=statement.executeQuery("SELECT * FROM surebetdb.sb_users1 WHERE Name='"+user+"';");
  	        rs.last();
            double old_AP = rs.getDouble("Average_Profit");
  	        int user_BG = rs.getInt("Number_BG");
  	        int user_FG = rs.getInt("Number_FG");
  	        double now_AP = (old_AP*(user_BG+user_FG-1)+cur_profit)/(user_BG+user_FG);
            statement.executeUpdate("UPDATE surebetdb.sb_users1 SET Average_Profit = '"+now_AP+"' WHERE Name='"+user+"';");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}