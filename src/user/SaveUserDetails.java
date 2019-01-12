package user;

import java.io.IOException;
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
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SaveUserDetails
 */
@WebServlet("/SaveUserDetails")
public class SaveUserDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	String title = "", first_Name = "", middle_Name = "", last_Name = "", sign_Date = "", rank = "", thumb_Id = "";
	String retri_title = "", retri_first_Name = "", retri_middle_Name = "", retri_last_Name = "", retri_sign_Date = "",
			retri_rank = "", retri_thumb_Id = "";
	StringBuilder retrieve_title = new StringBuilder();
	StringBuilder retrieve_name = new StringBuilder();
	StringBuilder retrieve_m_name = new StringBuilder();
	StringBuilder retrieve_l_name = new StringBuilder();
	StringBuilder retrieve_join_date = new StringBuilder();
	StringBuilder retrieve_rank = new StringBuilder();
	StringBuilder retrieve_thumb_id = new StringBuilder();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SaveUserDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("do get method");
		doPost(request, response);
		// response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Jai Bhole Nath from post");
		HttpSession session11 = request.getSession();
		thumb_Id = (String) session11.getAttribute("thumb_id");
		System.out.println("id-" + thumb_Id);
		/*System.out.println("Jai Shiv Shambhoo");
		System.out.println("Jai Bhole Nath");*/
		title = request.getParameter("title").toString();
		first_Name = request.getParameter("firstName").toString();
		middle_Name = request.getParameter("secondName").toString();
		last_Name = request.getParameter("thirdName").toString();
		sign_Date = request.getParameter("sign_On_date").toString();
		rank = request.getParameter("rank").toString();
		insert_user_detail();
		request.setAttribute("retrieve_title", retrieve_title);
		request.getRequestDispatcher("/userManagement.jsp").forward(request, response);
		retrieve_title.setLength(0);
	}

	public void insert_user_detail() {
		String driverName = "org.postgresql.Driver";
		String url1 = "jdbc:postgresql://ec2-54-243-137-182.compute-1.amazonaws.com:5432/d9mn6hk25h95pe?sslmode=require";
		String user = "fbdlpvmecxgbnh";
		String dbpsw = "dbff70b0e153cd8a901a97849e0c2c30f08158885e10248fe9e0baa1fe914aeb";
		try {
			Class.forName(driverName);
			con = DriverManager.getConnection("jdbc:postgresql://ec2-54-243-137-182.compute-1.amazonaws.com:5432/d9mn6hk25h95pe?sslmode=require", "fbdlpvmecxgbnh", "dbff70b0e153cd8a901a97849e0c2c30f08158885e10248fe9e0baa1fe914aeb");
			String sql = "INSERT  INTO  userdb_Digi VALUES('" + thumb_Id + "','" + title + "','" + first_Name + "',"
					+ "'" + middle_Name + "','" + last_Name + "','" + sign_Date + "','" + rank + "')";

			ps = (PreparedStatement) con.prepareStatement(sql);
			ps.execute();
			con.commit();
			ps.close();
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		retreive_all_User();
	}

	public void retreive_all_User() {
		System.out.println("from retreive method");
		Connection co = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;
		try {
			String query = "SELECT * FROM USERDB_DIGI";
			Class.forName("org.postgresql.Driver");
			co = DriverManager.getConnection("jdbc:postgresql://ec2-54-243-137-182.compute-1.amazonaws.com:5432/d9mn6hk25h95pe?sslmode=require", "fbdlpvmecxgbnh", "dbff70b0e153cd8a901a97849e0c2c30f08158885e10248fe9e0baa1fe914aeb");
			pstm = co.prepareStatement(query);
			rset = pstm.executeQuery();
			while (rset.next()) {
				retri_thumb_Id = rset.getString(1);
				retri_title = rset.getString(2);
				retri_first_Name = rset.getString(3);
				retri_middle_Name = rset.getString(4);
				retri_last_Name = rset.getString(5);
				retri_sign_Date = rset.getString(6);
				retri_rank = rset.getString(7);
				if(retri_rank.equals("CHIEF ENGINEER")||retri_rank.equals("MASTER")||retri_rank.equals("CHIEF MATE"))
				{
					retrieve_title.append("[").append("\"")
					.append(retri_title + " " + retri_first_Name + " " +""+ " " + retri_last_Name)
					.append("\"").append(",").append("\"").append(retri_rank).append("\"").append(",").append("\"")
					.append(retri_sign_Date).append("\"").append(",").append("\"").append("MANAGEMENT").append("\"").append("]").append(",");
				}
				else if(retri_rank.equals("THIRD MATE")||retri_rank.equals("THIRD ENGINEER")||retri_rank.equals("SECOND MATE"))
				{
					retrieve_title.append("[").append("\"")
					.append(retri_title + " " + retri_first_Name + " " +""+ " " + retri_last_Name)
					.append("\"").append(",").append("\"").append(retri_rank).append("\"").append(",").append("\"")
					.append(retri_sign_Date).append("\"").append(",").append("\"").append("OFFICER").append("\"").append("]").append(",");
				}
					
				else
				{
					retrieve_title.append("[").append("\"")
					.append(retri_title + " " + retri_first_Name + " " +""+ " " + retri_last_Name)
					.append("\"").append(",").append("\"").append(retri_rank).append("\"").append(",").append("\"")
					.append(retri_sign_Date).append("\"").append(",").append("\"").append("CREW").append("\"").append("]").append(",");
				}
			}
			co.close();
			pstm.close();
			rset.close();
		} catch (Exception e) {
			System.out.println(e);

		}

	}
}
