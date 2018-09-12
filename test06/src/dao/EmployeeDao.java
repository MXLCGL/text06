package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.Department;
import entity.Employee;

public class EmployeeDao {
	public List<Employee> search( int begin,int size) {
		List<Employee> list = new ArrayList<Employee>();

		try {
			// 2 利用反射 加载数据库驱动
			Class.forName("com.mysql.jdbc.Driver");
			// 3.建立连接

			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/compary?characterEncoding=utf-8",
					"root", "root");
			// 4建立statement sql 语句执行器
			Statement stat = conn.createStatement();
			// 5 执行sql 语句并得到结果
			ResultSet rs = stat.executeQuery("select * from employee  limit "+begin+","+size+";");
			// 6 对结果集进行处理
			while (rs.next()) {
				Employee emp = new Employee();
				emp.setId(rs.getInt("id"));
				emp.setName(rs.getString("name"));
				emp.setSex(rs.getString("sex"));
				emp.setAge(rs.getInt("age"));

				list.add(emp);
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
	public int searchCount() {
	
 int count=0;
		try {
			// 2 利用反射 加载数据库驱动
			Class.forName("com.mysql.jdbc.Driver");
			// 3.建立连接

			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/compary?characterEncoding=utf-8",
					"root", "root");
			// 4建立statement sql 语句执行器
			Statement stat = conn.createStatement();
			// 5 执行sql 语句并得到结果
			ResultSet rs = stat.executeQuery("select count(*) from employee ;");
			// 6 对结果集进行处理
			if (rs.next()) {
			count=rs.getInt(1);
			}
			/*
			 * 
			 * 
			 * 
			 * 
			 * 
			 */
			// 7 关闭
			rs.close();
			stat.close();
			conn.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}
		return count;
	}
	public List<Employee> search() {
		List<Employee> list = new ArrayList<Employee>();

		try {
			// 2 利用反射 加载数据库驱动
			Class.forName("com.mysql.jdbc.Driver");
			// 3.建立连接

			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/compary?characterEncoding=utf-8",
					"root", "root");
			// 4建立statement sql 语句执行器
			Statement stat = conn.createStatement();
			// 5 执行sql 语句并得到结果
			ResultSet rs = stat.executeQuery("select count * from employee ;");
			// 6 对结果集进行处理
			while (rs.next()) {
				Employee emp = new Employee();
				emp.setId(rs.getInt("id"));
				emp.setName(rs.getString("name"));
				emp.setSex(rs.getString("sex"));
				emp.setAge(rs.getInt("age"));

				list.add(emp);
			}
			/*
			 * 
			 * 
			 * 
			 * 
			 * 
			 */
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
	public int searchCount( Employee condition) {
		
 int count=0;
		try {
			// 2 利用反射 加载数据库驱动
			Class.forName("com.mysql.jdbc.Driver");
			// 3.建立连接

			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/compary?characterEncoding=utf-8",
					"root", "root");
			// 4建立statement sql 语句执行器
			Statement stat = conn.createStatement();
			// 5 执行sql 语句并得到结果
			
			String where=" where 1=1";
			if(condition.getName()!=null&&!condition.getName().equals("")) {
			where +=" and e.name='"+condition.getName()+"'";
			}
            if(condition.getSex()!=null&&!condition.getSex().equals("")) {
            	where +=" and sex='"+condition.getSex()+"'";
			}
            if(condition.getAge() != -1) {
            	where +=" and age="+condition.getAge();
			}
            if(condition.getDep()!=null&&condition.getDep().getId() != -1) {
            	where +=" and d_id="+condition.getDep().getId();
			}
			ResultSet rs = stat.executeQuery("select count(*) from employee as e left join department as d on e.d_id=d.id "+where);
			// 6 对结果集进行处理
			if (rs.next()) {
			count=rs.getInt(1);
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
		return count;
	}
	public boolean add(Employee emp) {

		int rs = 0;
		try {
			// 2 利用反射 加载数据库驱动
			Class.forName("com.mysql.jdbc.Driver");
			// 3.建立连接

			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/compary?characterEncoding=utf-8",
					"root", "root");
			// 4建立statement sql 语句执行器
			Statement stat = conn.createStatement();

			rs = stat.executeUpdate("insert into employee( name,sex,age,d_id,photo )  " + "values('" + emp.getName() + "','"
					+ emp.getSex() + "'," + "" + emp.getAge() +","+emp.getDep().getId()+ " , '"+emp.getPhoto()+"'  )");

			stat.close();
			conn.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs > 0;
	}

	public Employee search(int id) {
		Employee emp = new Employee();

		try {
			// 2 利用反射 加载数据库驱动
			Class.forName("com.mysql.jdbc.Driver");
			// 3.建立连接

			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/compary?characterEncoding=utf-8",
					"root", "root");
			// 4建立statement sql 语句执行器
			Statement stat = conn.createStatement();
			// 5 执行sql 语句并得到结果
			ResultSet rs = stat.executeQuery("select e.*,d.name as depName from employee as e left join department as d on d.id=e.d_id where e.id=" + id);
			// 6 对结果集进行处理
			while (rs.next()) {

				emp.setId(rs.getInt("id"));
				emp.setName(rs.getString("name"));
				emp.setSex(rs.getString("sex"));
				emp.setAge(rs.getInt("age"));
				Department dep =new Department();
				dep.setId(rs.getInt("d_id"));
				dep.setName(rs.getString("depName"));
			//	dep.setEmpCount(rs.getInt("emp_count"));
				emp.setDep(dep);
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
		return emp;
	}
	public List<Employee> search(String ids) {
	List	<Employee> list = new ArrayList<>();

		try {
			// 2 利用反射 加载数据库驱动
			Class.forName("com.mysql.jdbc.Driver");
			// 3.建立连接

			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/compary?characterEncoding=utf-8",
					"root", "root");
			// 4建立statement sql 语句执行器
			Statement stat = conn.createStatement();
			// 5 执行sql 语句并得到结果
			ResultSet rs = stat.executeQuery("select * from employee where id in (" + ids+")");
			// 6 对结果集进行处理
			while (rs.next()) {
                Employee emp = new Employee();
				emp.setId(rs.getInt("id"));
				emp.setName(rs.getString("name"));
				emp.setSex(rs.getString("sex"));
				emp.setAge(rs.getInt("age"));
            list.add(emp);
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

	public boolean update(Employee emp) {
		boolean flag = false;
		try {
			// 2 利用反射 加载数据库驱动
			Class.forName("com.mysql.jdbc.Driver");
			// 3.建立连接

			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/compary?characterEncoding=utf-8",
					"root", "root");
			// 4建立statement sql 语句执行器+
			Statement stat = conn.createStatement();
			// 5 执行sql 语句并得到结果
			int rs = stat.executeUpdate("update employee set name='" + emp.getName() + "',sex='" + emp.getSex()
					+ "',age=" + emp.getAge() + ",d_id="+ emp.getDep().getId() +"  where id =" + emp.getId() + "");
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


	public boolean deleteBatch(String ids) {
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
			int rs = stat.executeUpdate("delete from employee where id in  ("+ ids +")");
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


	public boolean updateBatch2(List<Employee> list) {
		boolean flag = false;
		try {
			// 2 利用反射 加载数据库驱动
			Class.forName("com.mysql.jdbc.Driver");
			// 3.建立连接

			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/compary?characterEncoding=utf-8",
					"root", "root");
			// 4建立statement sql 语句执行器+
			Statement stat = conn.createStatement();
			// 5 执行sql 语句并得到结果
			
			for(int i=0;i<list.size(); i++) {
				Employee emp=list.get(i);
			int rs = stat.executeUpdate("update employee set name='" + emp.getName() + "',sex='" + emp.getSex()
					+ "',age=" + emp.getAge() + "  where id =" + emp.getId() + "");
			// 6 对结果集进行处理
			if (rs > 0) {
				flag = true;
			}
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
	public List<Employee> search(Employee condition ,int begin,int size) {
		List<Employee> list = new ArrayList<Employee>();	
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
			where +=" and e.name='"+condition.getName()+"'";
			}
            if(condition.getSex()!=null&&!condition.getSex().equals("")) {
            	where +=" and sex='"+condition.getSex()+"'";
			}
            if(condition.getAge() != -1) {
            	where +=" and age="+condition.getAge();
			}
            if(condition.getDep()!=null&&condition.getDep().getId() != -1) {
            	where +=" and d_id="+condition.getDep().getId();
			}
    
			//String sql = "select e.*,d.name as dName,emp_count from employee as e "
			//		+ "left join department as d on e.d_id=d.id"+ where ;
            String sql ="select e.*,d.name as depName from employee as e left join department as d on e.d_id=d.id "
            		+where+" limit " + begin + ","+size+";";
            ResultSet rs = stat.executeQuery(sql);
			//6 对结果集进行处理
			while (rs.next() ) {
				Employee emp = new Employee();
				emp.setId(rs.getInt("id"));
				emp.setName(rs.getString("name"));
				emp.setSex(rs.getString("sex"));
				emp.setAge(rs.getInt("age"));
				emp.setPhoto(rs.getString("photo"));
				Department dep =new Department();
				dep.setId(rs.getInt("d_id"));
				dep.setName(rs.getString("depName"));
			//	dep.setEmpCount(rs.getInt("emp_count"));
				emp.setDep(dep);
				list.add(emp);
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
	
}
