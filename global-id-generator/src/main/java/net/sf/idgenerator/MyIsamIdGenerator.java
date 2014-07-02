package net.sf.idgenerator;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;


public class MyIsamIdGenerator implements IdGenerator
{
	private DataSource myisamDataSource;
	
	/**
	 * Get next id.
	 * 
	 * For MyIsam, there's no transaction! but don't worry, under MyIsam, the r/w for tables is sequential.
	 * 
	 * NOTE: The performance of MyIsam is much better than InnoDB!!
	 * 
	 * Actually, this method does not need synchronized key word. There's no sharing resource to access in sync way.
	 * Each Id request from thread will get a new connection object from connection pool!
	 * 
	 * @return the long
	 */
	public Long next(){
		Long nextId = null;
		Connection con = null;
		try {
			con = myisamDataSource.getConnection();
			con.createStatement().execute("REPLACE INTO sequence (stub) VALUES ('a')");
			ResultSet rs = con.createStatement().executeQuery("SELECT LAST_INSERT_ID()");
			if(rs.next()){
				nextId = rs.getLong(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();			
		} finally {
			try {con.close();} catch (SQLException e) {}
		}
		return nextId;
	}

	public DataSource getMyisamDataSource() {
		return myisamDataSource;
	}

	public void setMyisamDataSource(DataSource myisamDataSource) {
		this.myisamDataSource = myisamDataSource;
	}	
   
}
