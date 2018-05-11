package cn.qfengx.jdbc.test;

import java.util.List;

import cn.qfengx.jdbc.BeanHandler;
import cn.qfengx.jdbc.JdbcUtil;

public class Test {
	public static void main(String[] args) throws Exception {
		String sql = "select * from student";
		List<Student> list = JdbcUtil.query(sql, new BeanHandler<Student>(Student.class));
		System.out.println(list.size());
		for (Student student : list) {
			System.out.println(student);
		}
	}
}