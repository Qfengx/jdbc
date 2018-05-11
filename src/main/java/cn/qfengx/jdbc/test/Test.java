package cn.qfengx.jdbc.test;

import cn.qfengx.jdbc.BeanHandler;
import cn.qfengx.jdbc.JdbcUtil;

public class Test {
	public static void main(String[] args) throws Exception {
		String sql = "select * from student";
		System.out.println(JdbcUtil.query(sql, new BeanHandler<Student>(Student.class)));
	}
}
