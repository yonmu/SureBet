package SB_Model;
import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StatsSQL {
	
	public static String GetAllStats () {
        Connection connection=null;
        String out_str = "";
        try {
        	//Establish connection:
            MysqlDataSource ds = new MysqlConnectionPoolDataSource();
            ds.setServerName("localhost");
            ds.setDatabaseName("surebetdb");
            connection=ds.getConnection("root","");
            Statement statement=connection.createStatement();
            //Get full data:
            ResultSet rs_n_users=statement.executeQuery("SELECT MAX(ID) AS ID FROM surebetdb.sb_users1;");
            rs_n_users.last();
            int num_of_users = rs_n_users.getInt("ID");
            ResultSet rs_AP=statement.executeQuery("SELECT AVG(Average_Profit) AS Average_Profit FROM surebetdb.sb_users1;");
            rs_AP.last();
            double all_AP = rs_AP.getDouble("Average_Profit");
            ResultSet rs_n_BG=statement.executeQuery("SELECT SUM(Number_BG) AS Number_BG FROM surebetdb.sb_users1;");
            rs_n_BG.last();
            int num_BG = rs_n_BG.getInt("Number_BG");
            ResultSet rs_n_FG=statement.executeQuery("SELECT SUM(Number_FG) AS Number_FG FROM surebetdb.sb_users1;");
            rs_n_FG.last();
            int num_FG = rs_n_FG.getInt("Number_FG");
            out_str = "Site stats: We have " + num_of_users + 
            		" users. Total Football games of " + num_FG + " and total Basketball games of " + num_BG +
            		" . The Average profit of a user in our site is " + all_AP;
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
        return out_str;
    }
	
	public static String GetMyStats (String user) {
        Connection connection=null;
        String user_stats_out = "";
        try {
        	//Establish connection:
            MysqlDataSource ds = new MysqlConnectionPoolDataSource();
            ds.setServerName("localhost");
            ds.setDatabaseName("surebetdb");
            connection=ds.getConnection("root","");
            Statement statement=connection.createStatement();
            //Get user from DB:
            ResultSet rs=statement.executeQuery("SELECT * FROM surebetdb.sb_users1 WHERE Name='"+user+"';");
  	        rs.last();
  	        //Get user stats:
  	        int userID = rs.getInt("ID");
            int UN_BG = rs.getInt("Number_BG");
            int UN_FG = rs.getInt("Number_FG");
            double UN_AP = rs.getDouble("Average_Profit");
            user_stats_out = "You are " + user + ". Your SureBet ID is " + userID 
            + ". You have " + UN_BG + " basketball games in your history, and " + UN_FG + " football games in your history."
            + " Your average profit is " + UN_AP + " . Good Luck!";
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
        return user_stats_out;
    }
}