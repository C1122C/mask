package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class SQLHelper {
	private static SQLHelper sqlhelper;
	private DataSource datasource = null;
	private Connection connection;
	
	private SQLHelper(){
		InitialContext jndiContext = null;

		Properties properties = new Properties();
		properties.put(javax.naming.Context.PROVIDER_URL, "jnp:///");
		properties.put(javax.naming.Context.INITIAL_CONTEXT_FACTORY, "org.apache.naming.java.javaURLContextFactory");
		try {
			jndiContext = new InitialContext(properties);
			datasource = (DataSource) jndiContext.lookup("java:comp/env/jdbc/mask");
			
			System.out.println("got context");
			System.out.println("About to get ds");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
		
	public static SQLHelper getInstance()
	{
		if(sqlhelper==null) {
			sqlhelper=new SQLHelper();
		}
		return sqlhelper;
	}
	public Connection getConnection() {
		try {
			connection = datasource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
	public void close(Connection conn,PreparedStatement pstmt,ResultSet rs){  
        try {  
            if(rs!=null){  
                 rs.close();  
            }  
            if(pstmt!=null){  
                 pstmt.close();  
            }  
            if(conn!=null){  
                 conn.close();  
            }  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  

   }  
	public void close1(Connection conn,PreparedStatement pstmt){  
        try {  
           if(pstmt!=null){  
                 pstmt.close();  
            }  
            if(conn!=null){  
                 conn.close();  
            }  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  

   }  
	
}
