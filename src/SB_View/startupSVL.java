//NOT USED CURRENTLY!
//Imports and such:
package SB_View;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import SB_Model.StartupSQL;

//Servlet:
@WebServlet (name = "/SigninSVL", urlPatterns = "/SigninSVL")
public class startupSVL extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost (HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	response.setContentType("text/html;charset=UTF-8");
	PrintWriter out = response.getWriter();
	//Create new DB:
	try
	{	
		StartupSQL.create_newDB();
	}
	catch(Exception bad_input){
		out.println("Illegal input!" + "" + bad_input);
	}
	finally {
		out.close();
		}	
	}
}