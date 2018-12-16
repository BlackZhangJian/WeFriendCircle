package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import modul.User;

@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Register() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write("你好");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username;
		String password;
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		username = request.getParameter("username");
		password = request.getParameter("password");
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		UserDao dao = new UserDao();
		if(dao.insert(user)) {
			System.out.println("register_true");
			response.getWriter().write("REGISTERSUCCESS");
		}else {
			System.out.println("register_false");
			response.getWriter().write("REGISTERFAILED");
		}
		System.out.println("Register");
	}

}
