package net.sf.idgenerator;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;


public class InnoDbIdGenerator implements IdGenerator
{
	private DataSource innodbDataSource;
	
	/**
	 * Get next id.
	 * Actually, this method does not need synchronized key word. There's no sharing resource to access in sync way.
	 * Each Id request from thread will get a new connection object from connection pool!
	 * 
	 * NOTE: Transaction is not required!
	 * 
	 * @return the long
	 */
	public Long next(){
		Long nextId = null;
		Connection con = null;
		try {
			con = innodbDataSource.getConnection();
//			con.setAutoCommit(false);
			con.createStatement().execute("REPLACE INTO sequence (stub) VALUES ('a')");
			ResultSet rs = con.createStatement().executeQuery("SELECT LAST_INSERT_ID()");
			if(rs.next()){
				nextId = rs.getLong(1);
			}
//			con.commit();
//			con.setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();			
		} finally {
			try {con.close();} catch (SQLException e) {}
		}
		return nextId;
	}

	public DataSource getInnodbDataSource() {
		return innodbDataSource;
	}

	public void setInnodbDataSource(DataSource innodbDataSource) {
		this.innodbDataSource = innodbDataSource;
	}

  
}
