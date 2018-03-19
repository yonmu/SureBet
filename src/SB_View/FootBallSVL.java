//Imports and such:
package SB_View;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import SB_Controller.footballGame;
import SB_Model.UpdateSQL;

//Servlet:
@WebServlet (name = "/FootBallSVL", urlPatterns = "/FootBallSVL")
public class FootBallSVL extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet (HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	response.setContentType("text/html;charset=UTF-8");
	PrintWriter out = response.getWriter();
	
	//Find the max rates and agencies:
	try
	{	
		//Get parameters form HTML:
		double[] Home_win = new double [5];
		double[] Tie = new double [5];
		double[] Away_win = new double [5];
		String[] Agency = new String [5];
		for(int i=0;i<5;i++)
		{
			Agency[i] = request.getParameter("cell_r"+i+"c0");
			Home_win[i] = Double.parseDouble(request.getParameter("cell_r"+i+"c1"));
			Tie[i] = Double.parseDouble(request.getParameter("cell_r"+i+"c2"));
			Away_win[i] = Double.parseDouble(request.getParameter("cell_r"+i+"c3"));
		}
		double Best_AW = Home_win[0];
		double Best_Tie = Tie[0];
		double Best_HW = Away_win[0];
		String bestAW_agency = Agency[0];
		String bestTie_agency = Agency[0];
		String bestHW_agency = Agency[0];

		//Check best - home and away:
		for (int i = 0; i<5; i++)
		{			
			if (Home_win[i]>Best_HW)
			{
				Best_HW = Home_win[i];
				bestHW_agency = Agency[1];
			}
		}
		for (int i = 0; i<5; i++)
		{			
			if (Tie[i]>Best_Tie)
			{
				Best_Tie = Tie[i];
				bestTie_agency = Agency[1];
			}
		}
		for (int i = 0; i<5; i++)
		{			
			if (Away_win[i]>Best_AW)
			{
				Best_AW = Away_win[i];
				bestAW_agency = Agency[1];
			}
		}
	
		//Call compute-function with our parameters:
		String out_str=footballGame.checkSureBet(Best_HW, Best_Tie, Best_AW, bestHW_agency, bestTie_agency, bestAW_agency);
		if (out_str.equals("No sure bet for this rates"))
		{
		//no need to update DB
		}
		else
		{
		//get the current profit:
		String temp1 = out_str.substring(out_str.length() - 7);
		String temp2 = temp1.substring(0, (temp1.length()-2));
		double cur_profit = Double.parseDouble(temp2); //up cast the end of string to a double
		//Update user stats:
        HttpSession session=request.getSession(false);  
		String cur_username = (String)session.getAttribute("username");
		UpdateSQL.update_FBG(cur_username);	
		UpdateSQL.update_AP(cur_username, cur_profit);
		}
		out.println("<!DOCTYPE html>\r\n" + 
				"<html>\r\n" + 
				"<head>\r\n" + 
				"<meta charset=\"windows-1255\">\r\n" + 
				"<!DOCTYPE html>\r\n" + 
				"<meta charset=\"windows-1255\">\r\n" + 
				"\r\n" + 
				"<body bgcolor=\"#0a4a6f\">\r\n" + 
				"<style>\r\n" + 
				".container {\r\n" + 
				"    position: relative;\r\n" + 
				"    text-align: center;\r\n" + 
				"    color: white;\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				".center {\r\n" + 
				"    position: absolute;\r\n" + 
				"    top: 37%;\r\n" + 
				"    left: 25%;\r\n" + 
				"    right: 25%;\r\n" + 
				"\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				"</style>\r\n" + 
				"<title>Stats user</title>\r\n" + 
				"<center>\r\n" + 
				"<div class=\"container\">\r\n" + 
				"  <img src=\"FTpage.jpg\" usemap=\"#image-map\" width=\"850\" height=\"579\" alt=\"Stats\" >\r\n" + 
				"    <map name=\"image-map\">\r\n" + 
				"    <area title=\"Choose Page\" href=\"ChoosePage.html\" coords=\"220,30,620,150\" shape=\"rect\">\r\n" + 
				" <div class=\"center\">\r\n"); 
				out.println("<h3> \""+out_str +"\" </h3>");
				out.println("</center>");
				out.println("</head>"); 
				out.println("</html>");	
	} 
	catch(Exception bad_input){
		out.println("<!DOCTYPE html>\r\n" + 
				"<html>\r\n" + 
				"<head>\r\n" + 
				"<meta charset=\"windows-1255\">\r\n" + 
				"<!DOCTYPE html>\r\n" + 
				"<meta charset=\"windows-1255\">\r\n" + 
				"\r\n" + 
				"<body bgcolor=\"#0a4a6f\">\r\n" + 
				"<style>\r\n" + 
				".container {\r\n" + 
				"    position: relative;\r\n" + 
				"    text-align: center;\r\n" + 
				"    color: white;\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				".center {\r\n" + 
				"    position: absolute;\r\n" + 
				"    top: 37%;\r\n" + 
				"    left: 18%;\r\n" + 
				"\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				"</style>\r\n" + 
				"<title>Stats user</title>\r\n" + 
				"<center>\r\n" + 
				"<div class=\"container\">\r\n" + 
				"  <img src=\"StatsPage.jpg\" usemap=\"#image-map\" width=\"850\" height=\"579\" alt=\"Stats\" >\r\n" + 
				"    <map name=\"image-map\">\r\n" + 
				"    <area title=\"Choose Page\" href=\"ChoosePage.html\" coords=\"220,30,620,150\" shape=\"rect\">\r\n" + 
				" <div class=\"center\">\r\n" + 
				"<h1> Illegal input! </h1>" +
				"</center>\r\n" + 
				"</head>\r\n" + 
				"</html>");
	}
	finally {
		out.close();
		}	
	}
}
