package cn.qfengx.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JdbcUtil {
	private static String prefix;
	private static MyPool pool;
	
	static {
		try {
			pool = new MyPool();
			prefix = pool.getPrefix();
			System.out.println("JdbcUtil初始化结束");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("jdbcutil初始化错误");
		}
	}
	
	public static String getPrefix() {
		return prefix;
	}
	
	/**
	 * 获取连接池连接
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {
		return pool.getConnection();
	}
	
	/**
	 * 释放连接
	 * @param rs
	 * @param st
	 * @param conn
	 */
	public static void free(ResultSet rs, Statement st, Connection conn) {
		try {
			if (rs != null) 
				rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null) 
					st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (conn != null) 
						conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 将ResultSet封装成List<Map<String, Object>>
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	public static List<Map<String, Object>> mapping(ResultSet rs) throws SQLException {
		List<Map<String, Object>> res = new ArrayList<>();
		while (rs.next()) {
			Map<String, Object> map = new HashMap<>();
			for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
				String name = rs.getMetaData().getColumnLabel(i);
				map.put(name, rs.getObject(name));
			}
			res.add(map);
		}
		return res;
	}
	
	/**
	 * 将参数填入PreparedStatement
	 * @param ps
	 * @param objects
	 * @throws SQLException
	 */
	public static void putPs(PreparedStatement ps, Object... objects) throws SQLException {
		for (int i = 1; i <= objects.length; i++) 
			ps.setObject(i, objects[i - 1]);
	}
	
	/**
	 * 查询
	 * @param sql
	 * @param objects
	 * @return
	 */
	public static List<Map<String, Object>> query(String sql, Object... objects) {
		List<Map<String, Object>> res = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			System.out.println("查询语句：" + sql + "，参数长度：" +objects.length);
			conn = JdbcUtil.getConnection();
			ps = conn.prepareStatement(sql);
			putPs(ps, objects);
			rs = ps.executeQuery();
			res = mapping(rs);
		} catch (SQLException e) {
			System.out.println("查询异常，SQL语句：" + sql);
			System.out.println("参数列表：");
			Arrays.toString(objects);
			e.printStackTrace();
		} finally {
			free(rs, ps, conn);
			return res;
		}
	}
	
	/**
	 * 更新 删除 添加
	 * @param sql
	 * @param objects
	 * @return
	 */
	public static int update(String sql, Object... objects) {
		int res = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			System.out.println("更新语句：" + sql + "，参数长度：" + objects.length);
			conn = JdbcUtil.getConnection();
			ps = conn.prepareStatement(sql);
			putPs(ps, objects);
			res = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("更新异常，SQL语句：" + sql);
			System.out.println("参数列表：");
			Arrays.toString(objects);
			e.printStackTrace();
		} finally {
			free(null, ps, conn);
			return res;
		}
	}
	
	public static <T> List<T> query(String sql, BeanHandler<T> bh, Object... objects) {
		return bh.handler(query(sql, objects));
	}
}
 



























