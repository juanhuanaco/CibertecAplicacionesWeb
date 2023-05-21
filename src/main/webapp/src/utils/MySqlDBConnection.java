package utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySqlDBConnection {

	public static Connection getConnection() {
		
		Connection cn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection("jdbc:mysql://localhost/bd_expenses_tracker", "root", "957772893");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return cn;
	}

}
