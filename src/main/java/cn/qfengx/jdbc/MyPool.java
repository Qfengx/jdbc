package cn.qfengx.jdbc;

import java.io.PrintWriter;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

import javax.sql.DataSource;

public class MyPool implements DataSource {
	private static List<Connection> pool = new LinkedList<Connection>();
	private static String prefix = "";//""
	private static int initNum = 5;//5
	private static String driver = "com.mysql.jdbc.Driver";//com.mysql.jdbc.Driver
	private static String url = "";//""
	private static String username = "root";//root
	private static String password = "";//""
	
	static {
		try {
			//加载配置文件
			Properties prop = new Properties();
			prop.load(MyPool.class.getResourceAsStream("/jdbcConfig.properties"));
			prefix = prop.getProperty("jdbc.db.prefix").equals("null") ? "" : prop.getProperty("jdbc.db.prefix");
			initNum = prop.getProperty("jdbc.pool.initNum").equals("null") ? 5 : Integer.parseInt(prop.getProperty("jdbc.pool.initNum"));
			driver = prop.getProperty("jdbc.driver").equals("null") ? "com.mysql.jdbc.Driver" : prop.getProperty("jdbc.driver");
			url = prop.getProperty("jdbc.url");
			username = prop.getProperty("jdbc.username").equals("null") ? "root" : prop.getProperty("jdbc.username");
			password = prop.getProperty("jdbc.password").equals("null") ? "" : prop.getProperty("jdbc.password");
			
			Class.forName(driver);
			//初始连接数
			for (int i = 0; i < initNum; i++) {
				Connection conn = DriverManager.getConnection(url, username, password);
				pool.add(conn);
			} 
			System.out.println("pool初始化结束");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("连接池初始化错误");
		}
	} 

	public String getPrefix() {
		return prefix;
	}
	 
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	
	@Override
	public Connection getConnection() throws SQLException {
		// TODO Auto-generated method stub
//		return null;
		if (pool.size() == 0) {//如果连接耗尽
			for (int i = 0; i < initNum; i++) {
				pool.add(DriverManager.getConnection(url, username, password));
			}
		}
		final Connection conn = pool.remove(0);//在池中拿走一个连接
		//利用动态代理的方式改造close方法，方便归还操作
		Connection proxy = (Connection) Proxy.newProxyInstance(conn.getClass().getClassLoader(), conn.getClass().getInterfaces(), new InvocationHandler() {
			
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				// TODO Auto-generated method stub
//				return null;
				if ("close".equals(method.getName())) {
					//找到close方法
					retConn(conn);
					return null;
				} else {
					//如果不是 不做修改
					return method.invoke(conn, args);
				}
			}
		});
		System.out.println("获取了一个连接，池里还剩 " + pool.size() + " 个连接");
		return proxy;
	}
	
	//归还连接
	private void retConn(Connection conn) {
		try {
			if (conn != null && !conn.isClosed()) {//conn不为空 且使用后没有关闭
				pool.add(conn);
				System.out.println("归还了一个连接，池里还剩 " + pool.size() + " 个连接");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public PrintWriter getLogWriter() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLogWriter(PrintWriter out) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setLoginTimeout(int seconds) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public int getLoginTimeout() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Connection getConnection(String username, String password) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
}



































