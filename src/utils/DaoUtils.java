package utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DaoUtils {
	public static final ComboPooledDataSource dataSource = new ComboPooledDataSource();
	
	//获取连接对象
	public static Connection openConn()
	{
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	//关闭连接
	public static void closeAll(Connection conn,Statement st, ResultSet rs)
	{
		try {
			if(conn !=null)
				conn.close();
			if(st!=null)
				st.close();
			if(rs!=null)
				rs.close();
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
}
