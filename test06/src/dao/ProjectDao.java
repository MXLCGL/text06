package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.Department;
import entity.Project;

public class ProjectDao {
	public List<Project> search() {
		List<Project> list = new ArrayList<Project>();
		
		
		try {
			//2 利用反射 加载数据库驱动
			Class.forName("com.mysql.jdbc.Driver");
			//3.建立连接
			
			Connection conn =DriverManager.getConnection("jdbc:mysql://localhost:3306/compary","root","root");
			//4建立statement sql 语句执行器
			Statement stat=conn.createStatement();
			//5 执行sql 语句并得到结果
			ResultSet rs = stat.executeQuery("select * from project ");
			//6 对结果集进行处理
			while (rs.next() ) {
				Project pro = new Project();
				pro.setId(rs.getInt("id"));
				pro.setName(rs.getString("name"));
			
				list.add(pro );
			}
			//7 关闭
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
	public Project search(int id) {
		Project pro = new Project();
		
		try {
			//2 利用反射 加载数据库驱动
			Class.forName("com.mysql.jdbc.Driver");
			//3.建立连接
			
			Connection conn =DriverManager.getConnection("jdbc:mysql://localhost:3306/compary","root","root");
			//4建立statement sql 语句执行器
			Statement stat=conn.createStatement();
			//5 执行sql 语句并得到结果
			ResultSet rs = stat.executeQuery("select * from project where id="+id);
			//6 对结果集进行处理
			while (rs.next() ) {
		
				pro.setId(rs.getInt("id"));
				pro.setName(rs.getString("name"));
			
	
			}
			//7 关闭
			rs.close();
			stat.close();
			conn.close();
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
		}
		
		
		return pro;
	}
	
	public  boolean add(Project pro) {
		boolean flag=false;
		try {
			//2 利用反射 加载数据库驱动
			Class.forName("com.mysql.jdbc.Driver");
			//3.建立连接
			
			Connection conn =DriverManager.getConnection("jdbc:mysql://localhost:3306/compary?characterEncoding=utf-8","root","root");
			//4建立statement sql 语句执行器
			Statement stat=conn.createStatement();
			//5 执行sql 语句并得到结果
			int rs = stat.executeUpdate("insert into project(name) values('"+pro.getName()+"')");
		//	int rs = stat.executeUpdate("update project set name='"+pro.getName()+"',sex='"+pro.getSex()+"',age="+pro.getAge()+" where id ="+pro.getId()+"");
			//6 对结果集进行处理
			if (rs>0) {
				flag=true;
			}
			
			//7 关闭
			
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

	public  boolean update(Project pro) {
		boolean flag=false;
		try {
			//2 利用反射 加载数据库驱动
			Class.forName("com.mysql.jdbc.Driver");
			//3.建立连接
			
			Connection conn =DriverManager.getConnection("jdbc:mysql://localhost:3306/compary?characterEncoding=utf-8","root","root");
			//4建立statement sql 语句执行器+
			Statement stat=conn.createStatement();
			//5 执行sql 语句并得到结果
		int rs = stat.executeUpdate("update project set name='"+pro.getName()+"'  where id ="+pro.getId()+"");
			//6 对结果集进行处理
			if (rs>0) {
				flag=true;
			}
			
			//7 关闭
			
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

	public  boolean delete(int id) {
		boolean flag=false;
		try {
			//2 利用反射 加载数据库驱动
			Class.forName("com.mysql.jdbc.Driver");
			//3.建立连接
			
			Connection conn =DriverManager.getConnection("jdbc:mysql://localhost:3306/compary?characterEncoding=utf-8","root","root");
			//4建立statement sql 语句执行器
			conn.setAutoCommit(false);
			Statement stat=conn.createStatement();
			//5 执行sql 语句并得到结果
			 int rs = stat.executeUpdate("delete from project where id="+id+"");
			 rs = stat.executeUpdate("delete from r_dep_pro where p_id="+id+"");
			//6 对结果集进行处理
		     conn.commit();
			if (rs>0) {
				flag=true;
			}
			
			//7 关闭
			
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


	
	public List<Project> search(Project condition, int begin,int size) {
		List<Project> list = new ArrayList<Project>();	
		try {
			//2 利用反射 加载数据库驱动
			Class.forName("com.mysql.jdbc.Driver");
			//3.建立连接
			
			Connection conn =DriverManager.getConnection("jdbc:mysql://localhost:3306/compary?characterEncoding=utf-8","root","root");
			//4建立statement sql 语句执行器
			Statement stat=conn.createStatement();
			//5 执行sql 语句并得到结果
			String where=" where 1=1";
			if(condition.getName()!=null&&!condition.getName().equals("")) {
			where +=" and name = '"+condition.getName()+"'";
			}
           
     
			String sql = "select * from project"+where+" limit "+ begin +","+ size;
			ResultSet rs = stat.executeQuery(sql);
			//6 对结果集进行处理
			while (rs.next() ) {
				Project pro = new Project();
				pro.setId(rs.getInt("id"));
				pro.setName(rs.getString("name"));

				list.add(pro);
			}
			//7 关闭
			rs.close();
			stat.close();
			conn.close();
			
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return list;
	}
	
	public int searchCount(Project condition) {
		int count =0;
		try {
			//2 利用反射 加载数据库驱动
			Class.forName("com.mysql.jdbc.Driver");
			//3.建立连接
			
			Connection conn =DriverManager.getConnection("jdbc:mysql://localhost:3306/compary?characterEncoding=utf-8","root","root");
			//4建立statement sql 语句执行器
			Statement stat=conn.createStatement();
			//5 执行sql 语句并得到结果
			String where=" where 1=1";
			if(condition.getName()!=null&&!condition.getName().equals("")) {
			where +=" and name = '"+condition.getName()+"'";
			}
           
     
			String sql = "select count(*) from project "+ where ;
			ResultSet rs = stat.executeQuery(sql);
			//6 对结果集进行处理
			while (rs.next() ) {
			count = rs.getInt(1);

			}
			//7 关闭
			rs.close();
			stat.close();
			conn.close();
			
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return count;
	}
	
	
	
}
