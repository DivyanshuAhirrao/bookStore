package books;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/SearchBook")
public class SearchBook extends HttpServlet {
	Connection con;
	
	@Override
	public void init() throws ServletException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/1emj9", "root", "Dadu@1699");
		} 
		catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}	
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String Name = req.getParameter("name");
	
		PreparedStatement pstmt = null;
		ResultSet rs   = null;
		
		String query = "select * from bookstore where book_name=?";
		
		try {
			pstmt= con.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			
			int count = pstmt.executeUpdate();
			
			PrintWriter pw = resp.getWriter();
			
			if (rs.next()) {
			pstmt.setString(1, Name);
			pw.print("<h1> This is "+count+"</h1>");
			}
			else {
				pw.print("<h1> book Not Found...</h1>");
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	 }
}
	

    
