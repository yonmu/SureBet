package SB_Controller;
public class basketballGame 
{
	public static double x,y,w,z,money_To_put1,money_To_put2,profit_home_team,profit_away_team;
    public static String string_to_send = null ;
    public static final double max_sum = 100;
   
	public static String checkSureBet (double max_home_win, double max_out_win, String name_home_agency,  String name_away_agency)
	{
	//Check if we have a sure bet for this rates:
	//The idea - can we get 100 with less than 100 (if we can - we have a sure bet):	w = 100 / max_home_win;
	z = 100 / max_out_win;
	if (( w+z ) > max_sum)
	{
	string_to_send = "No sure bet for this rates";
	return string_to_send;
	}
	
	//Check the profit:
	x = max_home_win/(max_out_win + max_home_win);
	y = max_out_win/(max_out_win + max_home_win);
	money_To_put1 = 100*y;
	money_To_put2 = 100*x;
	profit_home_team = ((money_To_put1*max_home_win) / 100);
	profit_away_team = ((money_To_put2*max_out_win) / 100);
	
	
	//Create string to print:
	string_to_send = "Put " + ((int)Math.round(money_To_put1 * 100)/(double)100) + "% of your money in agency " + name_home_agency +
			" for the home team " +
			" and put "+  ((int)Math.round(money_To_put2 * 100)/(double)100) + "% of your money in agency " + name_away_agency +
			" for the out team. Expected profit of " + ((int)Math.round(((profit_away_team-1)*100) * 100)/(double)100) + "%." ;
	return string_to_send;
	}
}