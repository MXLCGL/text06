package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.Project;

public class Dep2ProDao {
	public List<Project> searchByDepartment(int depId) {
		List<Project> list = new ArrayList<Project>();

		try {
			// 2 利用反射 加载数据库驱动
			Class.forName("com.mysql.jdbc.Driver");
			// 3.建立连接

			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/compary", "root", "root");
			// 4建立statement sql 语句执行器
			Statement stat = conn.createStatement();
			// 5 执行sql 语句并得到结果
			ResultSet rs = stat.executeQuery("select * from v_dep_pro where d_id=" + depId);
			// 6 对结果集进行处理
			while (rs.next()) {
				Project pro = new Project();
				pro.setId(rs.getInt("p_id"));
				pro.setName(rs.getString("p_name"));

				list.add(pro);
			}
			// 7 关闭
			rs.close();
			stat.close();
			conn.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}

		return list;
	}

	public List<Project> searchByNotDepartment(int depId) {
		List<Project> list = new ArrayList<Project>();

		try {
			// 2 利用反射 加载数据库驱动
			Class.forName("com.mysql.jdbc.Driver");
			// 3.建立连接

			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/compary", "root", "root");
			// 4建立statement sql 语句执行器
			Statement stat = conn.createStatement();
			// 5 执行sql 语句并得到结果

			String sql = "select * from project where id not in(  select p_id from v_dep_pro where d_id=" + depId + ")";
			ResultSet rs = stat.executeQuery(sql);
			// 6 对结果集进行处理
			while (rs.next()) {
				Project pro = new Project();
				pro.setId(rs.getInt("id"));
				pro.setName(rs.getString("name"));

				list.add(pro);
			}
			// 7 关闭
			rs.close();
			stat.close();
			conn.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}

		return list;
	}
	public boolean add( int depId, int proId) {
		boolean flag = false;
		try {
			// 2 利用反射 加载数据库驱动
			Class.forName("com.mysql.jdbc.Driver");
			// 3.建立连接

			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/compary?characterEncoding=utf-8",
					"root", "root");
			// 4建立statement sql 语句执行器
			Statement stat = conn.createStatement();
			// 5 执行sql 语句并得到结果
			String sql = "insert into r_dep_pro (d_id,p_id) values (" + depId + "," + proId + " )";
			int rs = stat.executeUpdate(sql);
			// int rs = stat.executeUpdate("update project set
			// name='"+pro.getName()+"',sex='"+pro.getSex()+"',age="+pro.getAge()+" where id
			// ="+pro.getId()+"");
			// 6 对结果集进行处理
			if (rs > 0) {
				flag = true;
			}

			// 7 关闭

			stat.close();
			conn.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	public boolean add(Connection conn, int depId, int proId) {
		boolean flag = false;
		
		try {
			// 2 利用反射 加载数据库驱动
			Class.forName("com.mysql.jdbc.Driver");
			// 3.建立连接

			// 4建立statement sql 语句执行器
			Statement stat = conn.createStatement();
			// 5 执行sql 语句并得到结果
			String sql = "insert into r_dep_pro (d_id,p_id) values (" + depId + "," + proId + " )";
			int rs = stat.executeUpdate(sql);
			// int rs = stat.executeUpdate("update project set
			// name='"+pro.getName()+"',sex='"+pro.getSex()+"',age="+pro.getAge()+" where id
			// ="+pro.getId()+"");
			// 6 对结果集进行处理
			if (rs > 0) {
				flag = true;
			}

			// 7 关闭

			stat.close();
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	public boolean addBatch(int depId, String[] proIds) {
		boolean flag = true;
		try {
			
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/compary?characterEncoding=utf-8",
					"root", "root");	
            conn.setAutoCommit(false);
			
			for(int i=0;i<proIds.length;i++) {
        	 
        	 add(conn, depId, Integer.parseInt(proIds[i]));
        	 
         }
			conn.commit();
			conn.setAutoCommit(true);
			
			conn.close();

		 } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			flag=false;
		}
		return flag;
	}
	public boolean deleteBatch(int depId, String[] proIds) {
		boolean flag = true;
		try {
			
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/compary?characterEncoding=utf-8",
					"root", "root");	
            conn.setAutoCommit(false);
			
			for(int i=0;i<proIds.length;i++) {
        	 
        	 delete(conn, depId, Integer.parseInt(proIds[i]));
        	 
         }
			conn.commit();
			conn.setAutoCommit(true);
			
			conn.close();

		 } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			flag=false;
		}
		return flag;
	}
	public boolean delete( Connection conn, int depId, int proId) {
		boolean flag = false;
		try {
			// 2 利用反射 加载数据库驱动
			Class.forName("com.mysql.jdbc.Driver");
			// 3.建立连接

			
			// 4建立statement sql 语句执行器
			Statement stat = conn.createStatement();
			// 5 执行sql 语句并得到结果
			String sql = "delete from r_dep_pro where d_id=" + depId + " and p_id = " + proId + " ";
			int rs = stat.executeUpdate(sql);
			// int rs = stat.executeUpdate("update project set
			// name='"+pro.getName()+"',sex='"+pro.getSex()+"',age="+pro.getAge()+" where id
			// ="+pro.getId()+"");
			// 6 对结果集进行处理
			if (rs > 0) {
				flag = true;
			}

			// 7 关闭

			stat.close();
		

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	public boolean delete(int depId, int proId) {
		boolean flag = false;
		try {
			// 2 利用反射 加载数据库驱动
			Class.forName("com.mysql.jdbc.Driver");
			// 3.建立连接

			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/compary?characterEncoding=utf-8",
					"root", "root");
			// 4建立statement sql 语句执行器
			Statement stat = conn.createStatement();
			// 5 执行sql 语句并得到结果
			String sql = "delete from r_dep_pro where d_id=" + depId + " and p_id = " + proId + " ";
			int rs = stat.executeUpdate(sql);
			// int rs = stat.executeUpdate("update project set
			// name='"+pro.getName()+"',sex='"+pro.getSex()+"',age="+pro.getAge()+" where id
			// ="+pro.getId()+"");
			// 6 对结果集进行处理
			if (rs > 0) {
				flag = true;
			}

			// 7 关闭

			stat.close();
			conn.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
}
