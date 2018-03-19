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

import SB_Model.StatsSQL;

//Servlet:
@WebServlet (name = "/StatsMySVL", urlPatterns = "/StatsMySVL")
public class StatsMySVL extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet (HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	String out_str = "";
	response.setContentType("text/html;charset=UTF-8");
	PrintWriter out = response.getWriter();
	
	try
	{	
        HttpSession session=request.getSession(false);  
		String cur_username = (String)session.getAttribute("username");
		out_str = StatsSQL.GetMyStats(cur_username);
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
				"  <img src=\"StatsPage.jpg\" usemap=\"#image-map\" width=\"850\" height=\"579\" alt=\"Stats\" >\r\n" + 
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
