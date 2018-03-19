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
import SB_Model.SigninSQL;

//Servlet:
@WebServlet (name = "/SigninSVL", urlPatterns = "/SigninSVL")
public class SigninSVL extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost (HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	response.setContentType("text/html;charset=UTF-8");
	PrintWriter out = response.getWriter();
	
	//Find the max rates and agencies:
	try
	{	
		//Get parameters form HTML:
		String username;
		String password;
		String repassword;
		username = request.getParameter("username-signup");
		password = request.getParameter("password-signup");
		repassword = request.getParameter("re-password-signup");
		
		//Save new user to sql via sql-java code:
		boolean legal = SigninSQL.insertUser(username, password);
		if (!password.equals(repassword))
		{
	         legal = false; 
		}
		if (legal == false)
		{
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<meta http-equiv=\"refresh\" content=\"2; URL=HomePage.html\">");	//The redirect
			out.println("<body bgcolor=\"#0a4a6f\">");
			out.println("<center>");
			out.println("<h1> Username already exists or passwords don't match </h1>");
			out.println("</map>");
			out.println("\r\n");
			out.println("</center>");
			out.println("</head>");
			out.println("</html>"); 
		}
		else {
		HttpSession session=request.getSession();  
	    session.setAttribute("username",username);
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset=\"windows-1255\">");
		out.println("<body bgcolor=\"#0a4a6f\">");
		out.println("<title>Welcome to SureBet</title>");
		out.println("<center>");
		out.println("<img src=\"ChoPage.jpg\" width=\"850\" height=\"579\" alt=\"Planets\"");
		out.println("usemap=\"#planetmap\">");
		out.println("<map name=\"planetmap\">");
		out.println("  <area shape=\"circle\" coords=\"423,361,67\" href=\"place_B_bet.html\" alt=\"basketball\">");
		out.println("  <area shape=\"circle\" coords=\"225,359,71\" href=\"place_F_bet.html\" alt=\"football\">");
		out.println("  <area shape=\"circle\" coords=\"619,365,70\" href=\"Stats.html\" alt=\"statistic\">");
		out.println("</map>");
		out.println("\r\n");
		out.println("<form method=\"get\" action = \"LogOutSVL\">\r\n" + 
				" <input type=\"Submit\" value=\"Log Out\" /><p>");
		out.println("</center>");
		out.println("</head>");
		out.println("</html>"); 
		}
	}
	
	catch(Exception bad_input){
		out.println("Illegal input!" + "" + bad_input);
	}
	finally {
		out.close();
		}	
	}
}