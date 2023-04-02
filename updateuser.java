package view;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/updateuser")
public class updateuser extends GenericServlet{

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = req.getParameter("myname");
		String email = req.getParameter("myemail");
		String number = req.getParameter("mynumber");
		String password = req.getParameter("mypassword");
		
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/miniproj","root","root");
			
			PreparedStatement ps=con.prepareStatement("update signup set FNAME=?,PWORD=?, NUMB=? where EMAIL=? ");
			
			ps.setString(1, name);
			ps.setString(4, email);
			ps.setString(2, password);
			ps.setString(3, number);
			
	        int a =	ps.executeUpdate();
		
		if(a>0)
		{
			PrintWriter pw=res.getWriter();
			res.setContentType("text/html");
			pw.print("Details Updated ........");
			RequestDispatcher rd=req.getRequestDispatcher("home.html");
			rd.include(req, res);
		}

		}	
		
			catch(Exception e)
			{
				e.printStackTrace();
				PrintWriter pw=res.getWriter();
				res.setContentType("text/html");
				pw.print("ERROR ...retry");
				RequestDispatcher rd=req.getRequestDispatcher("home.html");
				rd.include(req, res);
				
			}
		
	}

	
	

		
		
	}
