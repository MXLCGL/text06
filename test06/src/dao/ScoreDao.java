package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import entity.Department;
import entity.Employee;
import entity.Project;
import entity.Score;
import util.Grade;

public class ScoreDao {

	public int searchCount() {
	
             int count=0;
		try {

			Class.forName("com.mysql.jdbc.Driver");

			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/compary?characterEncoding=utf-8",
					"root", "root");

			Statement stat = conn.createStatement();

			ResultSet rs = stat.executeQuery("select count(*) from v_emp_sc  ");
			if (rs.next()) {
				count=rs.getInt(1);
			}

			rs.close();
			stat.close();
			conn.close();

		} catch (Exception e) {

			e.printStackTrace();
		} finally {

		}

		return count;
	}
	
	public List<Score> search(int begin,int size) {
		List<Score> list = new ArrayList<Score>();

		try {

			Class.forName("com.mysql.jdbc.Driver");

			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/compary?characterEncoding=utf-8",
					"root", "root");

			Statement stat = conn.createStatement();

			ResultSet rs = stat.executeQuery("select * from v_emp_sc limit "+begin+","+size+" ");
			while (rs.next()) {
				Score sc = new Score();

				Employee emp = new Employee();
				emp.setId(rs.getInt("e_id"));
				emp.setName(rs.getString("e_name"));
				Department dep = new Department();
				dep.setId(rs.getInt("d_id"));
				dep.setName(rs.getString("d_name"));
				
				emp.setDep(dep);
				sc.setEmp(emp);
				
				Project pro = new Project();
				pro.setId(rs.getInt("p_id"));
				pro.setName(rs.getString("p_name"));
				sc.setPro(pro);
				
				sc.setId(rs.getInt("sc_id"));
				sc.setValue((Integer) rs.getObject("value"));
				Grade g =Grade.getGrade(rs.getString("grade"));
				sc.setGrade(g);
				list.add(sc);
			}

			rs.close();
			stat.close();
			conn.close();

		} catch (Exception e) {

			e.printStackTrace();
		} finally {

		}

		return list;
	}

	public Score search(int id) {

		Score sc = new Score();
		try {
		
			Class.forName("com.mysql.jdbc.Driver");

			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/compary?characterEncoding=utf-8",
					"root", "root");

			Statement stat = conn.createStatement();

			ResultSet rs = stat.executeQuery("select * from v_emp_sc where sc_id="+id);
			while (rs.next()) {
				

				Employee emp = new Employee();
				emp.setId(rs.getInt("e_id"));
				emp.setName(rs.getString("e_name"));
				Department dep = new Department();
				dep.setId(rs.getInt("d_id"));
				dep.setName(rs.getString("d_name"));
				
				emp.setDep(dep);
				sc.setEmp(emp);
				
				Project pro = new Project();
				pro.setId(rs.getInt("p_id"));
				pro.setName(rs.getString("p_name"));
				sc.setPro(pro);
				
				sc.setId(rs.getInt("sc_id"));
				sc.setValue((Integer) rs.getObject("value"));
				Grade g =Grade.getGrade(rs.getString("grade"));
				sc.setGrade(g);
				
			}

			rs.close();
			stat.close();
			conn.close();

		} catch (Exception e) {

			e.printStackTrace();
		} finally {

		}

		return sc;
	}
	

//	public void save(Set<Score> set) {
//		for (Score sc : set) {
//			if (sc.getId() == 0) {
//				add(sc);
//			} else {
//				update(sc);
//			}
//
//		}
//	}

	public int add(Score sc) {
		PreparedStatement pstat = null;
		int id = 0;
		int rs=0;
		try {

			Class.forName("com.mysql.jdbc.Driver");

			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/compary?characterEncoding=utf-8",
					"root", "root");

			String sql = "insert into score(e_id,P_id,value) values(?,?,?)";
			pstat = conn.prepareStatement(sql);
			pstat.setInt(1, sc.getEmp().getId());
			pstat.setInt(2, sc.getPro().getId());
			pstat.setInt(3, sc.getValue());
			rs = pstat.executeUpdate();
		//	System.out.print(sc.getEmp().getId() + "---");
		//	System.out.print(sc.getPro().getId() + "---");
		//	System.out.println(sc.getValue());
			pstat.close();
			sql="select last_insert_id()";
            
            pstat = conn.prepareStatement(sql);
			
            ResultSet r = pstat.executeQuery();
            
            if(r.next()) {
            	
            	id = r.getInt(1);
            }
            
            
            
            pstat.close();
			conn.close();

		} catch (Exception e) {

			e.printStackTrace();
		} finally {

		}

		return id;
	}

	public boolean update(Score sc) {
		PreparedStatement pstat = null;
		int rs = 0;
		try {

			Class.forName("com.mysql.jdbc.Driver");

			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/compary?characterEncoding=utf-8",
					"root", "root");

			String sql = "update score set value=? where id = ?";
			pstat = conn.prepareStatement(sql);

			pstat.setInt(1, sc.getValue());

			pstat.setInt(2, sc.getId());
			rs = pstat.executeUpdate();
			pstat.close();
			conn.close();

		} catch (Exception e) {

			e.printStackTrace();
		} finally {

		}

		return rs > 0;
	}

	public List<Score> searchByCondition(Score condition,int begin,int size) {
		List<Score> list = new ArrayList<Score>();

		try {

			Class.forName("com.mysql.jdbc.Driver");

			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/compary?characterEncoding=utf-8",
					"root", "root");

			Statement stat = conn.createStatement();

			String where=" where 1=1";
			
			if(!condition.getEmp().getName().equals("")) {
			where +=" and e_name='"+condition.getEmp().getName()+"'";
			}
			if(condition.getEmp().getDep().getId() != -1) {
            	where +=" and d_id = "+condition.getEmp().getDep().getId();
			}
            
			if(condition.getPro().getId() != -1) {
            	where +=" and p_id = "+condition.getPro().getId();
			}
			if(!condition.getGrade().equals("")) {
            	where +=" and grade = '"+condition.getGrade()+"'";
			}
			
			String sql = "select * from v_emp_sc "+ where +" limit " + begin + ","+size+";";
			ResultSet rs = stat.executeQuery(sql);	
			while (rs.next()) {
				Score sc1 = new Score();

				Employee emp = new Employee();
				emp.setId(rs.getInt("e_id"));
				emp.setName(rs.getString("e_name"));
				Department dep = new Department();
				dep.setId(rs.getInt("d_id"));
				dep.setName(rs.getString("d_name"));
				emp.setDep(dep);
				sc1.setEmp(emp);
				Project pro = new Project();
				pro.setId(rs.getInt("p_id"));
				pro.setName(rs.getString("p_name"));
				sc1.setPro(pro);
				sc1.setId(rs.getInt("sc_id"));
				sc1.setValue((Integer) rs.getObject("value"));
				Grade g =Grade.getGrade(rs.getString("grade"));
				sc1.setGrade(g);
				list.add(sc1);
			}

			rs.close();
			stat.close();
			conn.close();

		} catch (Exception e) {

			e.printStackTrace();
		} finally {

		}

		return list;
	}

}
