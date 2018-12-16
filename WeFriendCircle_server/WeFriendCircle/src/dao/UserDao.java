package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import modul.User;
import utils.JDBCUtils;

public class UserDao {
	// 添加用户的操作 [注册]
	public boolean insert(User user) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			// 获得数据的连接
			conn = JDBCUtils.getConnection();
			// 获得Statement对象
			stmt = conn.createStatement();
			// 发送SQL语句
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String publishtime = sdf.format(new Date());//获取当前系统时间
			String sql = "INSERT INTO user(publishtime,username,password) " + "VALUES('"+publishtime+"','"+ user.getUsername() + "','"
					+ user.getPassword() + "')";
			System.out.println("insert sql:" + sql);
			int num = stmt.executeUpdate(sql);
			if (num > 0) {
				System.out.println("xxxxxxx插入成功xxxxxx");
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.release(rs, stmt, conn);
		}
		return false;
	}

	// 登录查找指定的user[登录]
	public boolean find(User user) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			// 获得数据的连接
			conn = JDBCUtils.getConnection();
			// 获得Statement对象
			stmt = conn.createStatement();
			// 发送SQL语句
			String sql = "SELECT * FROM user WHERE username='" + user.getUsername() + "' and password='"
					+ user.getPassword() + "'";
			rs = stmt.executeQuery(sql);
			System.out.println("find sql:" + sql);
			if (rs.next()) {
				System.out.println("xxxxxxx查询成功xxxxxx");
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.release(rs, stmt, conn);
		}
		return false;
	}

	// 查询所有的User对象
	public List<User> findAll() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		// * 将数据库的表数据 按照 我们自己定义【数据结构】 存储在内存
		List<User> list = new ArrayList<User>();
		try {
			// 获得数据的连接
			conn = JDBCUtils.getConnection();
			// 获得Statement对象
			stmt = conn.createStatement();
			// 发送SQL语句
			String sql = "SELECT * FROM user where content !='"+""+"' order by publishtime desc";// sql脚本
			rs = stmt.executeQuery(sql);
			System.out.println(sql);
			// 处理结果集
			while (rs.next()) {// 一次循环 取出一行 数据
				// 取出了一行数据存储到用户对象
				User user = new User(rs.getTimestamp("publishtime"), rs.getString("username"), rs.getString("password"),
						rs.getString("content"), rs.getString("imgurl"));
				// 将用户对象放在集合中
				list.add(user);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.release(rs, stmt, conn);
		}
		return null;
	}

	// 修改用户
	@SuppressWarnings({ "unused", "static-access" })
	public boolean update(User user) {
		if(user.getLOGINUSERNAME()==null) {
			System.out.println("用户名为空");
		}else {
			Connection conn = null;
			Statement stmt = null;
			ResultSet rs = null;
			try {
				// 获得数据的连接
				conn = JDBCUtils.getConnection();
				// 获得Statement对象
				stmt = conn.createStatement();
				// 发送SQL语句
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String publishtime = sdf.format(new Date());//获取当前系统时间
				String sql = "UPDATE user set publishtime='" + publishtime + "',content='" + user.getContent()
						+ "' WHERE username='" +user.getLOGINUSERNAME()+"'";
				System.out.println(sql);
				int num = stmt.executeUpdate(sql);
				if (num > 0) {
					return true;
				}
				return false;
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				JDBCUtils.release(rs, stmt, conn);
			}
		}
		return false;
	}

}
