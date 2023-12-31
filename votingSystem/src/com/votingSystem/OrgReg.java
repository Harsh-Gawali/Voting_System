package com.votingSystem;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class OrgReg
 */
@WebServlet("/OrgReg")
public class OrgReg extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrgReg() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		Connection con=DbConnection.connect();
		String org_name=request.getParameter("name");
		String org_email=request.getParameter("email");
		String password=request.getParameter("password");
		
		try {
			PreparedStatement psmt = con.prepareStatement("insert into organizer_login values(?,?,?,?,?)");
			psmt.setInt(1, 0);
			psmt.setString(2, org_name);
			psmt.setString(3, org_email);
			psmt.setString(4, password);
			psmt.setString(5, "Pending");
			if(psmt.executeUpdate()==1){
				response.sendRedirect("index.jsp");
			}
			else{
				response.sendRedirect("failure.html");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
