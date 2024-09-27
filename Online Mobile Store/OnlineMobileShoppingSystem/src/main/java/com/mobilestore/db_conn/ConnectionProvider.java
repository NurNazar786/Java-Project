package com.mobilestore.db_conn;



import java.sql.Connection;
import java.sql.DriverManager;

public final class ConnectionProvider {

static Connection connection=null;
	
	private  ConnectionProvider()
	{
		
	}
	
	synchronized static public Connection getConnection()
	{

		if(connection==null)
		{
			try
			{
				Class.forName(DB_INFO.DB_DRIVER);
				connection=DriverManager.getConnection(DB_INFO.DB_URL,DB_INFO.DB_USER_NAME,DB_INFO.DB_PASSWORD);
				return connection;
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			return connection;
		}
		return connection;
	}
	
	
	
	
	
	
//	if(conn==null)
//	{
//		
//		try {
//			Class.forName(PropertyReader.readProperty("DB_DRIVER"));
//			conn=DriverManager.getConnection(PropertyReader.readProperty("DB_URL"),PropertyReader.readProperty("DB_USER"),PropertyReader.readProperty("DB_PASS"));
//			return conn;
//		} 
//		
//		catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}
//	else
//	{
//		return conn;
//	}
//	
//	return conn;
//	
//}

	
}
