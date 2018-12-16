package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JEditorPane;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import dao.UserDao;
import modul.User;

@WebServlet("/Publish")
public class Publish extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Publish() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//测试代码
		UserDao dao = new UserDao();
		List<User> userlist = new ArrayList<User>();
		userlist = dao.findAll();
		String str = JSON.toJSONString(userlist).toString();
		response.getWriter().write(str);
		System.out.println(str);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		UserDao dao = new UserDao();
		List<User> userlist = new ArrayList<User>();
		userlist = dao.findAll();
		String str = JSON.toJSONString(userlist).toString();
		response.getWriter().write(str);
		System.out.println("publish");
	}
}
