package view;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
@WebServlet("/loginn")
public class logbackend extends GenericServlet
{

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email = req.getParameter("usrname");
		String password = req.getParameter("pass2");
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/miniproj","root","root");
			PreparedStatement ps=con.prepareStatement("select * from signup where email=? and pword=?");
			
			ps.setString(1, email);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			
			
			while(rs.next()) {
				res.setContentType("text/html");
				PrintWriter pw = res.getWriter();// used to display in the webpage.........
				pw.print("sucessful..........!!!!!!");
				RequestDispatcher rd = req.getRequestDispatcher("home.html");
				rd.forward(req, res);
			}

		}
		
		catch(Exception e) {
			e.printStackTrace();
		}
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();// used to display in the webpage.........
		pw.print("INCORRECT PASSWORD OR EMAIL..........!!!!!!");
		
		RequestDispatcher rd = req.getRequestDispatcher("loginn.html");
		rd.include(req, res);// it is for response................
		
	}

}
