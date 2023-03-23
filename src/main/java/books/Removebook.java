package books;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/DeleteBook")
public class Removebook extends HttpServlet {
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
		String ID = req.getParameter("id");
		int Id = Integer.parseInt(ID);
		
		PreparedStatement pstmt = null;
		
		String query = "Delete from bookstore where book_id=?";
		
		try {
			pstmt = con.prepareStatement(query);
            pstmt.setInt(1, Id);
            int count = pstmt.executeUpdate();
            
            PrintWriter pw = resp.getWriter();
            pw.print("<h1>"+count+ " Record Deleted</h1>");
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
