package view;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/signup")
public class backend extends GenericServlet
{

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException 
	{
		String fname = req.getParameter("fullname");
		String usermail = req.getParameter("umail");
		String numbb = req.getParameter("numb");
		String password = req.getParameter("pass1");

		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/miniproj","root","root");
			PreparedStatement ps=con.prepareStatement("insert into signup values(?,?,?,?)");
			ps.setString(1, fname);
			ps.setString(2, usermail);
			ps.setInt(3, Integer.parseInt(numbb));
			ps.setString(4, password);
			ps.executeUpdate();
			con.close();
		}
		
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		RequestDispatcher rd = req.getRequestDispatcher("loginn.html");
		rd.forward(req, res);
	}

}
