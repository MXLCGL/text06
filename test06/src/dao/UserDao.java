package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.User;

public class UserDao {
	public boolean search(User user) {
		Connection conn = null;
		boolean flag = false;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/compary?characterEncoding=utf-8", "root",
					"root");
			String sql = " select * from user where username=? and password=?";

			PreparedStatement stat = conn.prepareStatement(sql);

			stat.setString(1, user.getUsername());
			stat.setString(2, user.getPassword());

			ResultSet rs = stat.executeQuery();
			if (rs.next()) {
				flag = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {

				conn.close();
				// pstat.close();
				// rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return flag;

	}

	public boolean add(User user) {
		Connection conn = null;
		int rs = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/compary?characterEncoding=utf-8", "root",
					"root");
			String sql = "insert into user(username,password) values(?,?)";

			PreparedStatement stat = conn.prepareStatement(sql);

			stat.setString(1, user.getUsername());
			stat.setString(2, user.getPassword());

			rs = stat.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {

				conn.close();
				// pstat.close();
				// rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return rs > 0;

	}

}
