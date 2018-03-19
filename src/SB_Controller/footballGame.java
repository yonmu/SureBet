package SB_Controller;
public class footballGame 
{
	public static double x,y,z,w,money_To_put1,money_To_put2,money_To_put3,profit_home_team,profit_away_team,profit_draw_team;
    public static final double max_sum = 100;
    public static String string_to_send = null;
    
	public static String checkSureBet (double max_home_win, double max_draw_win, double max_out_win, String name_home_agency, String name_draw_agency, String name_away_agency)
	{
	//Check if we have a sure bet for this rates:
	//The idea - can we get 100 with less than 100 (if we can - we have a sure bet):
	x = 100 / max_home_win;
	y = 100 / max_out_win;
	w = 100 / max_draw_win;
	if (( x + y + w) > max_sum) 
	{
	string_to_send = "No sure bet for this rates";
	return string_to_send;
	}
	//Check the profit:
	z = (100 - (x + y + w));
	z = z / 3;
	money_To_put1 = x + z;
	money_To_put2 = y + z;
	money_To_put3 = w + z;
	profit_home_team = ((money_To_put1*max_home_win) / 100);
	profit_away_team = ((money_To_put2*max_out_win) / 100);
	profit_draw_team = ((money_To_put3*max_draw_win) / 100);
	
	//Create string to print:
	string_to_send = "Put " +((int)Math.round(money_To_put1 * 100)/(double)100)+"% of your money in agency " + name_home_agency +
			" for the home team, put "+ ((int)Math.round(money_To_put3 * 100)/(double)100)+ "% of your money in agency " + name_draw_agency +
			" for the draw option. and put "+ ((int)Math.round(money_To_put2 * 100)/(double)100)+ "% of your money in agency " + name_away_agency +
			" for the out team. Expected  average profit of " + ((int)Math.round(((((profit_away_team+profit_home_team+profit_draw_team)/3)-1)*100)* 100)/(double)100) + "%." ;
	return string_to_send;
	}
}