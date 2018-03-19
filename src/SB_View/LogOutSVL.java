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

//Servlet:
@WebServlet (name = "/LogOutSVL", urlPatterns = "/LogOutSVL")
public class LogOutSVL extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet (HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	response.setContentType("text/html;charset=UTF-8");
	PrintWriter out = response.getWriter();
	
	try
	{
		//Kill current session:
	    HttpSession theSession = request.getSession(false);
		theSession.invalidate();
		//Go to home page:
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta http-equiv=\"refresh\" content=\"2; URL=HomePage.html\">");	//The redirect
		out.println("<body bgcolor=\"#0a4a6f\">");
		out.println("<center>");
		out.println("<h1> You are now logged out </h1>");
		out.println("</map>");
		out.println("\r\n");
		out.println("</center>");
		out.println("</head>");
		out.println("</html>"); 
		
	} 
	catch(Exception bad_input){
		out.println("Illegal input!");
	}
	finally {
		out.close();
		}	
	}
}