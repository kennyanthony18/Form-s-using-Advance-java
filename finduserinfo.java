package view;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

/**
 * Servlet implementation class finduserinfo
 */
@WebServlet("/finduser")
public class finduserinfo extends GenericServlet {
	private static final long serialVersionUID = 1L;
	
	
   
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email = req.getParameter("myemail");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/miniproj","root","root");
			PreparedStatement ps=con.prepareStatement("Select * signup where EMAIL=? ");
				ps.setString(1, email);
				ResultSet rs =	ps.executeQuery();
				
				if(rs.next())
				{
					PrintWriter pw=res.getWriter();
					res.setContentType("text/html");
					pw.print("NAME: "+rs.getString(1)+"<br>Email: "+rs.getString(2)+ "<br>Number: "+rs.getInt(3)+"<br>PASSSWORD: "+rs.getString(4));
					RequestDispatcher rd=req.getRequestDispatcher("home.html");
					rd.include(req, res);
				}
				else
				{
				PrintWriter pw=res.getWriter();
				res.setContentType("text/html");
				pw.print("INVALID USER");
				RequestDispatcher rd=req.getRequestDispatcher("home.html");
				rd.include(req, res);
				}
				
				
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		
	}

}