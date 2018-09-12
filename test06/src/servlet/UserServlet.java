package servlet;

import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao;
import entity.User;
import entity.ValidateCode;
import util.CreateMD5;
import util.RandomNumber;

public class UserServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		String type = request.getParameter("type");
		if (type == null) {
			showRegister(request, response);
		} else if ("showLogin".equals(type)) {
			showLogin(request, response);
		} else if ("doLogin".equals(type)) {
			doLogin(request, response);
		} else if ("randomImage".equals(type)) {
			randomImage(request, response);
		} else if ("doRegister".equals(type)) {
			doRegister(request, response);
		}

	}

	private void randomImage(HttpServletRequest request, HttpServletResponse response) {
		RandomNumber rn = new RandomNumber();
		try {
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			ValidateCode vc = rn.generateImage();

			ServletOutputStream outStream = response.getOutputStream();
			ImageIO.write(vc.getImage(), "JPEG", outStream);
			outStream.close();
			request.getSession().setAttribute("rand", vc.getRand());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void showLogin(HttpServletRequest request, HttpServletResponse response) {

		try {
			String name = "";
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (int i = 0; i < cookies.length; i++) {
					if ("username".equals(cookies[i].getName())) {

						name = cookies[i].getValue();
					}

				}
			}

			request.setAttribute("name", name);
			request.getRequestDispatcher("WEB-INF/user/login.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void doLogin(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		try {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String random = request.getParameter("random");

			if (random.equals(session.getAttribute("rand"))) {
				User user = new User();
				user.setUsername(username);
				user.setPassword(CreateMD5.getMd5(password + "²Ü¹úÁº"));
				UserDao userDao = new UserDao();
				boolean flag = userDao.search(user);
				if (flag) {

					session.setAttribute("user", username);
					Cookie cookie = new Cookie("username", username);
					cookie.setMaxAge(60);
					response.addCookie(cookie);
					response.sendRedirect("index");
				} else {
					response.sendRedirect("user?type=showLogin");
				}
			} else {

				request.setAttribute("mes", "ÑéÖ¤Âë´íÎó");
				request.getRequestDispatcher("WEB-INF/user/login.jsp").forward(request, response);

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void doRegister(HttpServletRequest request, HttpServletResponse response) {
		UserDao userDao = new UserDao();
		try {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			User user = new User();
			user.setUsername(username);
			user.setPassword(CreateMD5.getMd5(password + "²Ü¹úÁº"));
			boolean flag = userDao.add(user);
			if (flag) {
				response.sendRedirect("user?type=showLogin");
			} else {
				response.sendRedirect("user");

			}

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	private void showRegister(HttpServletRequest request, HttpServletResponse response) {

		try {
			request.getRequestDispatcher("WEB-INF/user/register.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
	}
}
