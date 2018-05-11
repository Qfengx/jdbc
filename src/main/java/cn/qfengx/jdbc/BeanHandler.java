package cn.qfengx.jdbc;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class BeanHandler<T> {
	private Class<T> clazz;
	
	public BeanHandler(Class<T> clazz) {
		this.clazz = clazz;
	}
	
	/**
	 * 从List<Map<String, Object>>获取信息，并进行封装
	 * @param rs
	 * @return
	 * @throws  
	 * @throws InstantiationException 
	 * @throws Exception
	 */
	public List<T> handler(List<Map<String, Object>> list) {
		List<T> res = new ArrayList<>();
		try {
			for (Map<String, Object> map : list) {
				T t = null;
				t = clazz.newInstance();
				for (Entry<String, Object> entry : map.entrySet()) {
//					System.out.println("map:" + entry.getKey() + " " + entry.getValue());
					//属性名字
					String name = entry.getKey();
					try {
						Field field = t.getClass().getDeclaredField(name);
						field.setAccessible(true);
						field.set(t, entry.getValue());
					} catch (NoSuchFieldException e) {
						System.out.println(t.getClass() + "实体类中没有" + name + "属性");
						continue;
					}
				}
				res.add(t);
			}
//			Field[] field = t.getClass().getDeclaredField(name) 
		} catch (Exception e) {
			System.out.println("对象构造异常");
			e.printStackTrace();
		} finally {
			return res;
		}
//		for (Map<String, Object> map : list) {
//			//创建JavaBean对象
//			T t;
//			try {
//				t = clazz.newInstance();
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				System.out.println("JavaBean对象创建失败：" + clazz);
//				e.printStackTrace();
//				return res;
//			}
//			//将Clas对象clazz转化成BeanInfo对象，逐一进行封装
//			BeanInfo bi;
//			try {
//				bi = Introspector.getBeanInfo(clazz);
//			} catch (IntrospectionException e) {
//				// TODO Auto-generated catch block
//				System.out.println("Clas对象clazz转化成BeanInfo对象异常");
//				e.printStackTrace();
//				return res;
//			}
//			PropertyDescriptor[] pds = bi.getPropertyDescriptors();
////			for (PropertyDescriptor pd : pds) {
////				//获取属性名称
////				String name = pd.getName();
////				//获取setXxx方法名
////				Method mtd = pd.getWriteMethod();
////				try {
////					mtd.invoke(t, map.get(name));
////				} catch (Exception e) {
////					System.out.println("set方法异常：" + name);
////					e.printStackTrace();
////				}
////			}
//			for (Entry<String, Object> entry : map.entrySet()) {
//				//获取属性名称
//				String name = entry.getKey();
//				//获取set方法
//				Method mtd = pds
//			}
//			res.add(t);
//		}
//		return res;
	}
}
