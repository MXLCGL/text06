package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dep2ProDao;
import dao.DepartmentDao;
import dao.ProjectDao;
import dao.ProjectDao;
import entity.Department;
import entity.Project;
import entity.Project;
import net.sf.json.JSONArray;
import util.Constant;
import util.Pagination;

public class Dep2ProServlet extends HttpServlet {
	private static final String path = "WEB-INF/dep2pro/";

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("utf-8");
			String type = request.getParameter("type");
			if (type == null) {
				search(request, response);
			}else if ("add".equals(type)) {
				add(request, response);
			} else if ("delete".equals(type)) {
				delete(request, response);
			}else if ("m2".equals(type)) {
				search2(request, response);
			}else if ("add2".equals(type)) {
				add2(request, response);
			}else if ("delete2".equals(type)) {
				delete2(request, response);
			}else if ("m3".equals(type)) {
				search3(request, response);
			}else if ("m4".equals(type)) {
				search4(request, response);
			} else if ("addBatch".equals(type)) {
				addBatch(request, response);
			} else if ("deleteBatch".equals(type)) {
				deleteBatch(request, response);
			} else if ("m5".equals(type)) {
				search5(request, response);
			}else if ("m6".equals(type)) {
				search6(request, response);
			}

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}





	public void add(HttpServletRequest request, HttpServletResponse response) {
		try {
			int depId = Integer.parseInt(request.getParameter("depId"));
			int proId = Integer.parseInt(request.getParameter("proId"));
			Dep2ProDao dpDao = new Dep2ProDao();
			dpDao.add(depId, proId);		
			response.sendRedirect("d2p?depId="+depId);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void addBatch(HttpServletRequest request, HttpServletResponse response) {
		try {
			PrintWriter out = response.getWriter();
			
			int depId = Integer.parseInt(request.getParameter("depId"));
			String[] proIds = request.getParameter("proId").split(",");
			Dep2ProDao dpDao = new Dep2ProDao();
				
			boolean flag=dpDao.addBatch(depId, proIds);			
			out.print(flag);
		
			//response.sendRedirect("d2p?depId="+depId);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void add2(HttpServletRequest request, HttpServletResponse response) {
		try {
			PrintWriter out = response.getWriter();
			
			int depId = Integer.parseInt(request.getParameter("depId"));
			int proId = Integer.parseInt(request.getParameter("proId"));
			Dep2ProDao dpDao = new Dep2ProDao();
				
			boolean flag=dpDao.add(depId, proId);			
			out.print(flag);
		
			//response.sendRedirect("d2p?depId="+depId);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void delete(HttpServletRequest request, HttpServletResponse response) {
		try {

		
			int depId = Integer.parseInt(request.getParameter("depId"));
			int proId = Integer.parseInt(request.getParameter("proId"));
			Dep2ProDao dpDao = new Dep2ProDao();
			dpDao.delete(depId, proId);		
			response.sendRedirect("d2p?depId="+depId);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void delete2(HttpServletRequest request, HttpServletResponse response) {
		try {

			PrintWriter out = response.getWriter();
			int depId = Integer.parseInt(request.getParameter("depId"));
			int proId = Integer.parseInt(request.getParameter("proId"));
			Dep2ProDao dpDao = new Dep2ProDao();
			boolean flag=dpDao.delete(depId, proId);			
			out.print(flag);	
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void deleteBatch(HttpServletRequest request, HttpServletResponse response) {
		try {

			PrintWriter out = response.getWriter();
			int depId = Integer.parseInt(request.getParameter("depId"));
			String[] proIds = request.getParameter("proId").split(",");

			Dep2ProDao dpDao = new Dep2ProDao();
			boolean flag=dpDao.deleteBatch(depId, proIds);			
			out.print(flag);	
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void search(HttpServletRequest request, HttpServletResponse response) {
		try {

			int depId = Integer.parseInt(request.getParameter("depId"));

			Dep2ProDao dpDao = new Dep2ProDao();
            DepartmentDao depDao = new DepartmentDao();
             Department dep =depDao.search(depId);
			
			
			List<Project> list = dpDao.searchByDepartment(depId);
			List<Project> noList = dpDao.searchByNotDepartment(depId);
			request.setAttribute("dep", dep);

			request.setAttribute("list", list);
			request.setAttribute("noList", noList);
			request.getRequestDispatcher(path + "list.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void search2(HttpServletRequest request, HttpServletResponse response) {
		try {

			int depId = Integer.parseInt(request.getParameter("depId"));

			Dep2ProDao dpDao = new Dep2ProDao();
DepartmentDao depDao = new DepartmentDao();
Department dep =depDao.search(depId);
			
			
			List<Project> list = dpDao.searchByDepartment(depId);
			List<Project> noList = dpDao.searchByNotDepartment(depId);
			request.setAttribute("dep", dep);

			request.setAttribute("list", list);
			request.setAttribute("noList", noList);
			request.getRequestDispatcher(path + "list2.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void search3(HttpServletRequest request, HttpServletResponse response) {
		try {

			int depId = Integer.parseInt(request.getParameter("depId"));

			Dep2ProDao dpDao = new Dep2ProDao();
            DepartmentDao depDao = new DepartmentDao();
            Department dep =depDao.search(depId);
			
			
			List<Project> list = dpDao.searchByDepartment(depId);
			List<Project> noList = dpDao.searchByNotDepartment(depId);
			request.setAttribute("dep", dep);

			request.setAttribute("list", list);
			request.setAttribute("noList", noList);
			request.getRequestDispatcher(path + "list3.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void search4(HttpServletRequest request, HttpServletResponse response) {
		try {

			int depId = Integer.parseInt(request.getParameter("depId"));

			Dep2ProDao dpDao = new Dep2ProDao();
            DepartmentDao depDao = new DepartmentDao();
            Department dep =depDao.search(depId);
			
			
			List<Project> list = dpDao.searchByDepartment(depId);
			List<Project> noList = dpDao.searchByNotDepartment(depId);
			request.setAttribute("dep", dep);

			request.setAttribute("list", list);
			request.setAttribute("noList", noList);
			request.getRequestDispatcher(path + "list4.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void search5(HttpServletRequest request, HttpServletResponse response) {
		try {

			int depId = Integer.parseInt(request.getParameter("depId"));

			Dep2ProDao dpDao = new Dep2ProDao();
            DepartmentDao depDao = new DepartmentDao();
            Department dep =depDao.search(depId);
			
			
			List<Project> list = dpDao.searchByDepartment(depId);
			List<Project> noList = dpDao.searchByNotDepartment(depId);
			request.setAttribute("dep", dep);

			request.setAttribute("list", list);
			request.setAttribute("noList", noList);
			request.getRequestDispatcher(path + "list5.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void search6(HttpServletRequest request, HttpServletResponse response) {
		try {

			int depId = Integer.parseInt(request.getParameter("depId"));

			Dep2ProDao dpDao = new Dep2ProDao();
            DepartmentDao depDao = new DepartmentDao();
            Department dep =depDao.search(depId);
			
			
			List<Project> list = dpDao.searchByDepartment(depId);
			List<Project> noList = dpDao.searchByNotDepartment(depId);
			request.setAttribute("dep", dep);

			request.setAttribute("list", list);
			request.setAttribute("noList", noList);
			request.getRequestDispatcher(path + "list6.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
	}
}
