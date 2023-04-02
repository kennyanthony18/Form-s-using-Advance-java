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

@WebServlet("/deleteuser")
public class deletedata extends GenericServlet{

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email = req.getParameter("myemail");
		
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/miniproj","root","root");
			
			PreparedStatement ps=con.prepareStatement("delete from signup where EMAIL=? ");
			
			ps.setString(1, email);
			
	        int a =	ps.executeUpdate();
		
		if(a>0)
		{
			PrintWriter pw=res.getWriter();
			res.setContentType("text/html");
			pw.print("Details are Deleted ........");
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
