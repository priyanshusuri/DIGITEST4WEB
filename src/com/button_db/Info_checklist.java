package com.button_db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Info_checklist {
	String checklistname=null;
	String checklistnumber=null,create_date=null,year_of_ck=null;
	int type=0;
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	

	public String getvalues() throws SQLException, ClassNotFoundException
	{
		Class.forName("org.postgresql.Driver");
		String sql="SELECT * FROM SAFETY_CHECKLIST_INFO";
		con = DriverManager.getConnection("jdbc:postgresql://ec2-54-243-137-182.compute-1.amazonaws.com:5432/d9mn6hk25h95pe?sslmode=require", "fbdlpvmecxgbnh", "dbff70b0e153cd8a901a97849e0c2c30f08158885e10248fe9e0baa1fe914aeb");
		ps = con.prepareStatement(sql);
		rs=ps.executeQuery();
		
		while(rs.next())
		{
			create_date=rs.getString(1).toString();
		checklistname=rs.getString(2).toString();
		checklistnumber=rs.getString(3).toString();
		type=Integer.parseInt(checklistnumber);
		type=type-1;
		}
		con.close();
		ps.close();
		rs.close();
		year_of_ck=create_date.substring(24,28).toString();
		return year_of_ck+"|"+checklistname+"|"+type;
		
	}
}
