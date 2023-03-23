package books;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/PurchaseBook")

public class PurchaseBook extends HttpServlet {
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
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String ID    =  req.getParameter("id");
		int Id = Integer.parseInt(ID);
		String qty = req.getParameter("quantity");
		int Qty = Integer.parseInt(qty);
		
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		
		String query = "select book_price,book_stock from bookstore where book_id = ?";
		String query1 = "update bookstore set book_stock=? where book_id=?";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, Id);
			rs = pstmt.executeQuery();
			
			PrintWriter pw = resp.getWriter();
			
			
			if (rs.next()) {
				double bookPrice = rs.getDouble(1);
				int bookStock    = rs.getInt(2);
				
				if (Qty <= bookStock) {
					double total = Qty * bookPrice;
					
					pw.print("<h1> Total Bill Amount = "+total+"/-</h1>");
					
					pstmt= con.prepareStatement(query1);
					pstmt.setInt(1, bookStock-Qty);
					pstmt.setInt(2, Id);
					int count = pstmt.executeUpdate();
					pw.println("<h1>Stock updated Successfully...</h1>");
				}
				else {
					 pw.println("<h1>Selected Books Out of Stock...</h1>");
				}
			
			}
			else {
				 pw.println("<h1>Id Not Found...</h1>");
			}
			
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

}
