package servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import dao.DepartmentDao;
import dao.EmployeeDao;
import entity.Department;
import entity.Employee;
import net.sf.json.JSONArray;
import util.Constant;
import util.Pagination;

public class EmployeeServlet extends HttpServlet {
	private static final String path = "WEB-INF/employee/";

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("utf-8");
			String type = request.getParameter("type");
			
			
			Class clazz= this.getClass();
		   try {
			Method method=	clazz.getDeclaredMethod(type, HttpServletRequest.class,HttpServletResponse.class);
		
		   method.invoke(this, request,response);
		   
		   
		   
		   
		   } catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
			
			
//			if (type == null) {
//				search(request, response);
//			} else if ("showAdd".equals(type)) {
//				showAdd(request, response);
//			} else if ("showAdd2".equals(type)) {
//				showAdd2(request, response);
//			} else if ("add".equals(type)) {
//				add(request, response);
//			} else if ("showUpdate".equals(type)) {
//				showUpdate(request, response);
//			} else if ("update".equals(type)) {
//				update(request, response);
//			} else if ("deleteBatch".equals(type)) {
//				deleteBatch(request, response);
//			} else if ("updateBatch3".equals(type)) {
//				updateBatch3(request, response);
//			} else if ("upload".equals(type)) {
//				upload(request, response);
//			} else if ("add2".equals(type)) {
//				add2(request, response);
//			}

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// public void show(HttpServletRequest request, HttpServletResponse response) {
	// try {
	//
	//
	// EmployeeDao empDao = new EmployeeDao();
	// int ye =1;
	// if(request.getParameter("ye")!=null){
	// ye=Integer.parseInt(request.getParameter("ye"));
	// }
	//
	// int count= empDao.searchCount();
	//
	//
	//
	// Pagination p =new
	// Pagination(ye,count,Constant.EMP_NUM_IN_PAGE,Constant.EMP_NUM_OF_PAGE);
	// List<Employee> list = empDao.search(p.getBegin(),Constant.EMP_NUM_IN_PAGE);
	// request.setAttribute("p", p);
	//
	// request.setAttribute("list", list);
	//
	// request.getRequestDispatcher(path+"list.jsp").forward(request, response);
	// } catch (ServletException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// }

	public void showAdd(HttpServletRequest request, HttpServletResponse response) {
		try {
			DepartmentDao depDao = new DepartmentDao();
			List<Department> depList = depDao.search();
			request.setAttribute("depList", depList);
			request.getRequestDispatcher(path + "add.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void showAdd2(HttpServletRequest request, HttpServletResponse response) {
		try {
			DepartmentDao depDao = new DepartmentDao();
			List<Department> depList = depDao.search();
			request.setAttribute("depList", depList);
			request.getRequestDispatcher(path + "add2.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void add2(HttpServletRequest request, HttpServletResponse response) {
		try {
			Employee emp = new Employee();
			String name = request.getParameter("name");
			String sex = request.getParameter("sex");
			int age = Integer.parseInt(request.getParameter("age"));
			Integer depId = null;
			if (!"".equals(request.getParameter("depId"))) {
				depId = Integer.parseInt(request.getParameter("depId"));
			}
			String photo = request.getParameter("picture");

			emp.setName(name);
			emp.setSex(sex);
			emp.setAge(age);
			emp.setPhoto(photo);
			Department dep = new Department();
			dep.setId(depId);
			emp.setDep(dep);
			EmployeeDao empDao = new EmployeeDao();
			boolean flag = empDao.add(emp);

			// show(request, response);

			response.sendRedirect("emp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void add(HttpServletRequest request, HttpServletResponse response) {
		try {

			String name = "";
			String sex = "";
			String age = "";
			String depId = "";

			String path = "F:/eclipse-jee-oxygen-1a-win32-x86_64/test/pic";
			FileItemFactory factory = new DiskFileItemFactory();// 为该请求创建一个DiskFileItemFactory对象，通过它来解析请求。执行解析后，所有的表单项目都保存在一个List中。
			ServletFileUpload upload = new ServletFileUpload(factory);
			List<FileItem> items = upload.parseRequest(request);

			String photoName = "";

			for (int i = 0; i < items.size(); i++) {
				FileItem item = items.get(i);
				if (item.getFieldName().equals("photo")) {
					UUID uuid = UUID.randomUUID();
					String houzhui = item.getName().substring(item.getName().lastIndexOf("."));
					photoName = uuid.toString() + houzhui;
					File savedFile = new File(path, photoName);
					item.write(savedFile);

				} else if (item.getFieldName().equals("name")) {
					name = new String(item.getString().getBytes("ISO-8859-1"), "utf-8");
				} else if (item.getFieldName().equals("sex")) {
					sex = new String(item.getString().getBytes("ISO-8859-1"), "utf-8");
				} else if (item.getFieldName().equals("age")) {
					age = new String(item.getString());
				} else if (item.getFieldName().equals("depId")) {
					depId = new String(item.getString());
				}

			}

			Employee emp = new Employee();
			Department dep = new Department();

			if (!"".equals(depId)) {
				dep.setId(Integer.parseInt(depId));
			}
			emp.setName(name);
			emp.setSex(sex);
			emp.setAge(Integer.parseInt(age));
			emp.setDep(dep);

			emp.setPhoto(photoName);

			EmployeeDao empDao = new EmployeeDao();
			boolean flag = empDao.add(emp);

			// show(request, response);

			response.sendRedirect("emp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void upload(HttpServletRequest request, HttpServletResponse response) {
		try {

			String path = "F:/eclipse-jee-oxygen-1a-win32-x86_64/test/pic";
			FileItemFactory factory = new DiskFileItemFactory();// 为该请求创建一个DiskFileItemFactory对象，通过它来解析请求。执行解析后，所有的表单项目都保存在一个List中。
			ServletFileUpload upload = new ServletFileUpload(factory);
			List<FileItem> items = upload.parseRequest(request);

			String photoName = "";
			for (int i = 0; i < items.size(); i++) {

				FileItem item = items.get(i);
				if (item.getFieldName().equals("photo")) {
					UUID uuid = UUID.randomUUID();
					String houzhui = item.getName().substring(item.getName().lastIndexOf("."));
					photoName = uuid.toString() + houzhui;
					File savedFile = new File(path, photoName);
					item.write(savedFile);

				}
			}

			PrintWriter out = response.getWriter();
			out.print(photoName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void showUpdate(HttpServletRequest request, HttpServletResponse response) {
		try {

			int id = Integer.parseInt(request.getParameter("id"));
			EmployeeDao empDao = new EmployeeDao();
			Employee emp = empDao.search(id);
			DepartmentDao depDao = new DepartmentDao();
			List<Department> depList = depDao.search();
			request.setAttribute("depList", depList);
			request.setAttribute("emp", emp);
			request.getRequestDispatcher(path + "update.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void update(HttpServletRequest request, HttpServletResponse response) {
		try {
			Employee emp = new Employee();
			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("name");
			String sex = request.getParameter("sex");
			int age = Integer.parseInt(request.getParameter("age"));
			Integer depId = null;
			if (!"".equals(request.getParameter("depId"))) {
				depId = Integer.parseInt(request.getParameter("depId"));
			}
			emp.setId(id);
			emp.setName(name);
			emp.setSex(sex);
			emp.setAge(age);
			Department dep = new Department();
			dep.setId(depId);
			emp.setDep(dep);
			EmployeeDao empDao = new EmployeeDao();
			boolean flag = empDao.update(emp);

			// show(request, response);

			response.sendRedirect("emp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void updateBatch3(HttpServletRequest request, HttpServletResponse response) {
		try {
			String emps = request.getParameter("emps");

			List<Employee> list = (List<Employee>) JSONArray.toCollection(JSONArray.fromObject(emps), Employee.class);
			// toList-----toCollection
			EmployeeDao empDao = new EmployeeDao();
			boolean flag = empDao.updateBatch2(list);

			// show(request, response);

			response.sendRedirect("emp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void deleteBatch(HttpServletRequest request, HttpServletResponse response) {
		try {

			String ids = request.getParameter("ids");

			EmployeeDao empDao = new EmployeeDao();
			boolean flag = empDao.deleteBatch(ids);

			// show(request, response);

			response.sendRedirect("emp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void search(HttpServletRequest request, HttpServletResponse response) {
		try {
			EmployeeDao empDao = new EmployeeDao();
			DepartmentDao depDao = new DepartmentDao();
			Employee condition = new Employee();

			String name = request.getParameter("name");
			String sex = request.getParameter("sex");
			int age = -1;
			if (request.getParameter("age") != null && !"".equals(request.getParameter("age"))) {
				age = Integer.parseInt(request.getParameter("age"));
			}

			int depId = -1;
			if (request.getParameter("depId") != null && !"".equals(request.getParameter("depId"))) {
				depId = Integer.parseInt(request.getParameter("depId"));
			}
			condition.setName(name);
			condition.setSex(sex);
			condition.setAge(age);
			Department dep = new Department();
			dep.setId(depId);
			// dep.setName(depName);
			condition.setDep(dep);

			int ye = 1;
			if (request.getParameter("ye") != null) {
				ye = Integer.parseInt(request.getParameter("ye"));
			}

			int count = empDao.searchCount(condition);

			Pagination p = new Pagination(ye, count, Constant.EMP_NUM_IN_PAGE, Constant.EMP_NUM_OF_PAGE);
			List<Employee> list = empDao.search(condition, p.getBegin(), Constant.EMP_NUM_IN_PAGE);

			List<Department> depList = depDao.search();

			request.setAttribute("p", p);
			request.setAttribute("depList", depList);
			request.setAttribute("c", condition);
			request.setAttribute("list", list);

			request.getRequestDispatcher(path + "list.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
	}
}
