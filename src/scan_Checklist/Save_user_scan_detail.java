package scan_Checklist;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Save_user_scan_detail {
	String last_value="";
	String url = "jdbc:postgresql://ec2-54-243-137-182.compute-1.amazonaws.com"
			+ ":5432/d9mn6hk25h95pe?sslmode=require";
	String classname = "org.postgresql.Driver";
	PreparedStatement pstm = null;
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	String driverName = "org.postgresql.Driver";
	String url1 = "jdbc:postgresql://ec2-54-243-137-182.compute-1.amazonaws.com"
			+ ":5432/d9mn6hk25h95pe?sslmode=require";
	String user = "fbdlpvmecxgbnh";
	String dbpsw = "dbff70b0e153cd8a901a97849e0c2c30f08158885e10248fe9e0baa1fe914aeb";

	public void insert_scanvalue(String value) {
		try {
			System.out.println("from save_user_scan_detail");
			Class.forName(classname);
			Connection c = DriverManager.getConnection(url, user, dbpsw);
			c.setAutoCommit(false);
			PreparedStatement pstm = null;

			String sql = "INSERT INTO USER_PIC_DIGI(TIMESTAMP,PIC_NAME) VALUES('"
					+ java.time.LocalDate.now().toString() + "','" + value + "')";
			System.out.println(sql);
			pstm = (PreparedStatement) c.prepareStatement(sql);
			pstm.execute();
			c.commit();
			pstm.close();
			c.close();
		} catch (Exception e) {
			System.out.println(e);

		}
	}
}
