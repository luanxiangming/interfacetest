package com.vipabc.interfacetest.utils;

import shelper.db.Oracle;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectToDB {

	private Connection conn;

	public ConnectToDB() {
		String driver = "com.mysql.cj.jdbc.Driver";

		String url = "jdbc:mysql://172.16.233.82:3306/vfs";

		String user = "vfsdev";

		String password = "vfs123";

		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			if (!conn.isClosed())
				System.out.println("Succeeded connecting to the Database!");
		} catch (ClassNotFoundException e) {

			System.out.println("Sorry,can't find the Driver!");
			e.printStackTrace();

		} catch (SQLException e) {

			e.printStackTrace();

		} catch (Exception e) {

			e.printStackTrace();

		}
	}

	public List<HashMap<String, String>> query(String table, String key) throws SQLException {
		Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery("select * from " + table);
        List<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();

        ResultSetMetaData md = rs.getMetaData();
        int cc = md.getColumnCount();
		while (rs.next()) {
            HashMap<String, String> columnMap = new HashMap<String, String>();
            for (int i = 1; i <= cc; i++) {
                columnMap.put(md.getColumnName(i), rs.getString(i));
            }
            result.add(columnMap);
		}
		rs.close();
		conn.close();
		return result;
	}

	public  List<HashMap<String, String>> query(String sql) throws SQLException {
		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery(sql);
		List<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();

        ResultSetMetaData md = rs.getMetaData();
        int cc = md.getColumnCount();
		while (rs.next()) {
            HashMap<String, String> columnMap = new HashMap<String, String>();
            for (int i = 1; i <= cc; i++) {
                columnMap.put(md.getColumnName(i), rs.getString(i));
            }
            result.add(columnMap);
		}
		rs.close();
//        conn.close();
        return result;
	}

	public void insert(String sql) throws SQLException {
		Statement statement = conn.createStatement();
		statement.executeUpdate(sql);
	}
	public void update(String sql) throws SQLException {
		Statement statement = conn.createStatement();
		statement.executeUpdate(sql);
	}
	public void delete(String sql) throws SQLException {
		Statement statement = conn.createStatement();
		statement.executeUpdate(sql);
	}
	  public void closeDBcon(){
	        try {
	            conn.close();
	        } catch (SQLException ex) {
	             Logger.getLogger(Oracle.class.getName()).log(Level.SEVERE, null, ex);
	        }
	      }

	public static void main(String args[]) throws SQLException {
		ConnectToDB cdb = new ConnectToDB();
		cdb.query("visit_identitify_code", "code_value");
		cdb.query("visit_identitify_code", "status");
//		cdb.insert("INSERT INTO visit_identitify_code"
//				+ "   (`id`, `creater`, `create_time`, `modifier`, `modify_time`, `version`, `code_value`, `follow_id`, `status`, `client_linkman_id`)"
//				+ "VALUES   (3, -1, '2014/5/29 17:00:00', NULL, NULL, 0, '4321', 1, 'visiting', 1);");
	}
}
