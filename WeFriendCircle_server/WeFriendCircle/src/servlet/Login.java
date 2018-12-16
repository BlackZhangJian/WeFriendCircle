package servlet;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import dao.UserDao;
import modul.User;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Login() {
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
		// 接受表单数据
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String username;
		String password;
		username = request.getParameter("username");
		password = request.getParameter("password");

		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		User.setLOGINUSERNAME(username);
		UserDao dao = new UserDao();
		if (dao.find(user)) {
			System.out.println("login_true");
			response.getWriter().write("LOGINSUCCESS");
		} else {
			System.out.println("login_false");
			response.getWriter().write("LOGINFAILED");
		}
	}


}
