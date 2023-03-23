package books;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UpdateBook")

public class UpdateBook extends HttpServlet {
	
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
		
		String ID    =  req.getParameter("id");
		String Name  =  req.getParameter("name");
		String Stock =  req.getParameter("stock");
		String Price =  req.getParameter("price");
		
		int Id = Integer.parseInt(ID);
		int stock = Integer.parseInt(Stock);
		double price = Double.parseDouble(Price);
		
		PreparedStatement pstmt = null;
		
		String query = "update bookstore set book_name=?, book_stock=?, book_price=? where book_id = ?";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, Name);
			pstmt.setInt(2, stock); 	
			pstmt.setDouble(3, price);
			pstmt.setInt(4, Id);
			int count = pstmt.executeUpdate();
			
			PrintWriter pw= resp.getWriter();
			pw.print("<h1>"+count+ " Record Updated Successfully</h1>");
			
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		
	}
	

}
