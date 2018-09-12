package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.Department;

public class DepartmentDao {
	public List<Department> search() {
		List<Department> list = new ArrayList<Department>();

		try {
			// 2 ���÷��� �������ݿ�����
			Class.forName("com.mysql.jdbc.Driver");
			// 3.��������

			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/compary?characterEncoding=utf-8",
					"root", "root");
			// 4����statement sql ���ִ����
			Statement stat = conn.createStatement();
			// 5 ִ��sql ��䲢�õ����
			ResultSet rs = stat.executeQuery("select * from department ");
			// 6 �Խ�������д���
			while (rs.next()) {
				Department dep = new Department();
				dep.setId(rs.getInt("id"));
				dep.setName(rs.getString("name"));

				dep.setEmpCount(rs.getInt("emp_count"));

				list.add(dep);
			}
			// 7 �ر�
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

	public Department search(int id) {
		Department dep = new Department();

		try {
			// 2 ���÷��� �������ݿ�����
			Class.forName("com.mysql.jdbc.Driver");
			// 3.��������

			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/compary?characterEncoding=utf-8",
					"root", "root");
			// 4����statement sql ���ִ����
			Statement stat = conn.createStatement();
			// 5 ִ��sql ��䲢�õ����
			ResultSet rs = stat.executeQuery("select * from department where id=" + id);
			// 6 �Խ�������д���
			if (rs.next()) {

				dep.setId(rs.getInt("id"));
				dep.setName(rs.getString("name"));

				dep.setEmpCount(rs.getInt("emp_count"));

			}
			// 7 �ر�
			rs.close();
			stat.close();
			conn.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}

		return dep;
	}

	public boolean add(Department dep) {
		boolean flag = false;
		try {
			// 2 ���÷��� �������ݿ�����
			Class.forName("com.mysql.jdbc.Driver");
			// 3.��������

			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/compary?characterEncoding=utf-8",
					"root", "root");
			// 4����statement sql ���ִ����
			Statement stat = conn.createStatement();
			// 5 ִ��sql ��䲢�õ����
			int rs = stat.executeUpdate("insert into department(name) values('" + dep.getName() + "')");
			// int rs = stat.executeUpdate("update department set
			// name='"+dep.getName()+"',sex='"+dep.getSex()+"',age="+dep.getAge()+" where id
			// ="+dep.getId()+"");
			// 6 �Խ�������д���
			if (rs > 0) {
				flag = true;
			}

			// 7 �ر�

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

	public boolean update(Department dep) {
		boolean flag = false;
		try {
			// 2 ���÷��� �������ݿ�����
			Class.forName("com.mysql.jdbc.Driver");
			// 3.��������

			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/compary?characterEncoding=utf-8",
					"root", "root");
			// 4����statement sql ���ִ����+
			Statement stat = conn.createStatement();
			// 5 ִ��sql ��䲢�õ����
			int rs = stat
					.executeUpdate("update department set name='" + dep.getName() + "' where id =" + dep.getId() + "");
			// 6 �Խ�������д���
			if (rs > 0) {
				flag = true;
			}

			// 7 �ر�

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

	public boolean delete(int id) {
		boolean flag = false;
		try {
			// 2 ���÷��� �������ݿ�����
			Class.forName("com.mysql.jdbc.Driver");
			// 3.��������

			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/compary?characterEncoding=utf-8",
					"root", "root");
			// 4����statement sql ���ִ����

			conn.setAutoCommit(false);
			Statement stat = conn.createStatement();
			// 5 ִ��sql ��䲢�õ����
			// stat.executeUpdate("update department set emp_count=0 where id=" + id + "");
			int rs = stat.executeUpdate("delete from department where id=" + id + "");
			// stat.close();
			rs = stat.executeUpdate("update employee set d_id=null where d_id=" + id + "");
			rs = stat.executeUpdate("delete from r_dep_pro where d_id=" + id + "");
			conn.commit();
			// 6 �Խ�������д���
			if (rs > 0) {
				flag = true;
			}

			// 7 �ر�

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

	public List<Department> search(Department condition, int begin, int size) {
		List<Department> list = new ArrayList<Department>();
		try {
			// 2 ���÷��� �������ݿ�����
			Class.forName("com.mysql.jdbc.Driver");
			// 3.��������

			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/compary?characterEncoding=utf-8",
					"root", "root");
			// 4����statement sql ���ִ����
			Statement stat = conn.createStatement();
			// 5 ִ��sql ��䲢�õ����
			String where = " where 1=1";
			if (condition.getName() != null && !condition.getName().equals("")) {
				where += " and name='" + condition.getName() + "'";
			}

			if (condition.getEmpCount() != -1) {
				where += " and ifnull(emp_count,0)=" + condition.getEmpCount();
			}
			String sql = "select * from department" + where + " limit " + begin + "," + size;
			ResultSet rs = stat.executeQuery(sql);
			// 6 �Խ�������д���
			while (rs.next()) {
				Department dep = new Department();
				dep.setId(rs.getInt("id"));
				dep.setName(rs.getString("name"));
				dep.setEmpCount(rs.getInt("emp_count"));
				list.add(dep);
			}
			// 7 �ر�
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

	public int searchCount(Department condition) {
		int count = 0;
		try {
			// 2 ���÷��� �������ݿ�����
			Class.forName("com.mysql.jdbc.Driver");
			// 3.��������

			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/compary?characterEncoding=utf-8",
					"root", "root");
			// 4����statement sql ���ִ����
			Statement stat = conn.createStatement();
			// 5 ִ��sql ��䲢�õ����
			String where = " where 1=1";
			if (condition.getName() != null && !condition.getName().equals("")) {
				where += " and name='" + condition.getName() + "'";
			}

			if (condition.getEmpCount() != -1) {
				where += " and ifnull(emp_count,0)=" + condition.getEmpCount();
			}
			String sql = "select count(*) from department" + where;
			ResultSet rs = stat.executeQuery(sql);
			// 6 �Խ�������д���
			if (rs.next()) {
				count = rs.getInt(1);
			}
			// 7 �ر�
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

	public List<Department> searchs(Department dep) {
		List<Department> list = new ArrayList<Department>();

		try {
			// 2 ���÷��� �������ݿ�����
			Class.forName("com.mysql.jdbc.Driver");
			// 3.��������

			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/compary?characterEncoding=utf-8",
					"root", "root");
			// 4����statement sql ���ִ����
			Statement stat = conn.createStatement();
			// 5 ִ��sql ��䲢�õ����
			ResultSet rs = stat.executeQuery("select * from v_dep_pro where d_id=" + dep.getId() + "  ");
			// 6 �Խ�������д���
			while (rs.next()) {
				Department dep2 = new Department();
				dep2.setId(rs.getInt("p_id"));
				dep2.setName(rs.getString("p_name"));
				list.add(dep2);
			}
			// 7 �ر�
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

}
