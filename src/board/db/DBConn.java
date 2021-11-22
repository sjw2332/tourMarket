package board.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConn {
	private  String  driver  = "oracle.jdbc.OracleDriver";
	private  String  url     = "jdbc:oracle:thin:@localhost:1521:xe";
	private  String  dbuid   = "travel";
	private  String  dbpwd   = "1234";
	
	private  Connection  conn = null;
	
	public   Connection  getConnection() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, dbuid, dbpwd);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public   void       close() {
		try {
			if( !conn.isClosed() )  conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		
}
