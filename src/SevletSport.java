

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

@WebServlet("/SevletSport")
public class SevletSport extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public SevletSport() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		
		out.print("<h3>display the record</h3>");
	
		String sprt_id = request.getParameter("sprt_id");
		out.print("<table border='1'><tr><th>sport id</th><th>sport name</th></tr>");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sports","root","KILLU");
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from sport where id="+sprt_id+"");
			while(rs.next()) {
				
				
				out.print("<td>");
				out.print(rs.getString(2));
				out.print("</td>");
				
				out.print("</tr>");
			}
			
		}catch(Exception e) {
			System.out.println(e);
		}
		out.print("</table>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
