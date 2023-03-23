package books;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/DisplayBooks")
public class ShowBook extends HttpServlet {
	Connection con;
	
	@Override
	public void init() throws ServletException {
        try {
	    	Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/1emj9", "root", "Dadu@1699");
		}
        catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Statement stmt = null;
		ResultSet rs   = null;
		
		
		String query = "Select * from bookstore";
		
		PrintWriter pw = resp.getWriter();
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			
			pw.print("<h1> Book details are : </h1>");
			pw.print("<table border='1'>");
			pw.print("<tr>");
			  pw.print("<th> Book ID </th>");
			  pw.print("<th> Book NAME </th>");
			  pw.print("<th> Book STOCK </th>");
			  pw.print("<th> Book PRICE </th>");
			pw.print("</tr>");
         
			while (rs.next()) {
				pw.print("<tr>");
				
				    pw.print("<td>" +rs.getInt(1)+ "</td>");
				    pw.print("<td>" +rs.getString(2)+ "</td>");
				    pw.print("<td>" +rs.getInt(3)+ "</td>");
				    pw.print("<td>" +rs.getDouble(4)+ "</td>");
				
				pw.print("</tr>");
			}
			pw.print("</table>");
		}
		

		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}