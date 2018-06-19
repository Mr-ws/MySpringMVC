package com;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.DB.BaseDao;
import com.finalClass.InterfaceFinal;
import com.interfacebean.MyBean;
import com.interfacebean.MyController;
import com.interfacebean.MyDao;
import com.interfacebean.MyDataSource;
import com.interfacebean.MyJDBC;
import com.interfacebean.MyMapping;
import com.interfacebean.MyParam;
import com.interfacebean.MyResource;
import com.interfacebean.MyServicec;

/**
 * 自启动 监听器
 * 
 * <p>
 * Title: MYListener
 * </p>
 * 
 * <p>
 * Description:
 * </p>
 * 
 * @author 魏帅
 * 
 * @date 2018年5月25日
 */
@WebListener
public class MYListener implements ServletContextListener {

	protected Map<Object, Object> myAllBeans = new HashMap<>();
	protected Map<String, Object> controllerMap = new HashMap<>();
	protected Map<String, Object> notControllerMap = new HashMap<>();
	protected Map<String, Object> serviceMap = new HashMap<>();
	protected Map<String, Object> daoMap = new HashMap<>();
	protected Map<String, ContrillerMe> mappingMap = new HashMap<>();
	protected Map<String, BaseDao> dataSource = new HashMap<>();

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		String path = this.getClass().getResource("/").getPath();
		try {
			myAllBeans = FileUtile.getAllUseObject(path, new File(path).getPath());
			classify();

			classifyControllerMapping();

			immitBean();

			System.out.println("自定义Spring启动成功");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 注入对象
	 *
	 * @author 魏帅
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws ClassNotFoundException
	 * @date 2018年6月2日
	 */
	private void immitBean() throws IllegalArgumentException, IllegalAccessException, ClassNotFoundException {
		// TODO Auto-generated method stub
		for (Object key : myAllBeans.keySet()) {
			Object bean = myAllBeans.get(key);

			Class<? extends Object> class1 = bean.getClass();
			Field[] fs = class1.getDeclaredFields();

			for (int i = 0; i < fs.length; i++) {

				Field f = fs[i];

				f.setAccessible(true); // 设置些属性是可以访问的

				MyResource myResource = f.getAnnotation(MyResource.class);

				if (myResource != null) {
					String resName = null;
					if (StringUtils.isBlank(myResource.value()) || InterfaceFinal.defStr.equals(myResource.value())) {
						String beanName = f.getType().getName();
						resName = beanName;
						if (resName.lastIndexOf(".") > 0) {
							resName = resName.substring(resName.lastIndexOf(".") + 1);
						}
					}
					Object object = myAllBeans.get(resName);
					if (object == null) {
						throw new ClassNotFoundException("bean[" + key + "] property [" + resName + "] is not found!");
					}

					f.set(bean, object); // 给属性设值
				}
				MyJDBC myJDBC = f.getAnnotation(MyJDBC.class);
				if (myJDBC != null && dataSource.isEmpty()) {
					throw new RuntimeException(" The database connection pool is not configured ");
				} else if (myJDBC != null && dataSource.get(myJDBC.Name()) == null) {
					throw new RuntimeException(
							" The database connection pool is not configured [" + key + '.' + f.getName() + "]");
				} else if (myJDBC != null) {
					f.set(bean, dataSource.get(myJDBC.Name()));
				}
			}
		}

	}

	// 分类 控制层连接
	private void classifyControllerMapping() {
		// TODO Auto-generated method stub
		for (String key : controllerMap.keySet()) {
			Object obj = controllerMap.get(key);
			// 获取 内部方法
			Method[] methods = obj.getClass().getMethods();
			for (Method method : methods) {
				MyMapping annotation = method.getAnnotation(MyMapping.class);
				if (annotation != null) {
					List<Param> params = new ArrayList<Param>();
					ContrillerMe me = new ContrillerMe().setMethod(method).setObj(obj);

					// 获取 参数类型 及参数列表 // 未做

					Annotation[][] parameterAnnotations = method.getParameterAnnotations();
					for (int i = 0; i < parameterAnnotations.length; i++) {
						// 获取 第i个参数的 所有注解
						Annotation[] annotations = parameterAnnotations[i];

						Param tt = null;
						for (Annotation annotation2 : annotations) {
							if (annotation2 instanceof MyParam) {
								MyParam myParam = (MyParam) annotation2;
								tt = new Param().setName(myParam.value()).setType(method.getParameterTypes()[i])
										.setIndex(i).setMyParam(myParam);
								;
							}
						}
						if (tt == null)
							tt = new Param().setName(null).setType(method.getParameterTypes()[i]).setIndex(i);
						params.add(tt);
					}
					me.setParams(params);
					// 获取 参数类型 及参数列表
					mappingMap.put(annotation.value(), me);
				}
			}
		}

		MyFileter.mappingMap = mappingMap;
	}

	// 分类
	@SuppressWarnings("unchecked")
	private void classify() {
		// TODO Auto-generated method stub
		for (Object key : myAllBeans.keySet()) {
			Object obj = myAllBeans.get(key);
			if (obj.getClass().getAnnotation(MyController.class) != null) {
				controllerMap.put((String) key, obj);
			} else if (key instanceof String) {
				notControllerMap.put((String) key, obj);
			}
			if (obj.getClass().getAnnotation(MyServicec.class) != null) {
				serviceMap.put((String) key, obj);
			}
			if (obj.getClass().getAnnotation(MyDao.class) != null) {
				daoMap.put((String) key, obj);
			}
		}
		Object object = myAllBeans.get(MyDataSource.class);
		if (object != null)
			dataSource.putAll((Map<String, BaseDao>) object);
	}
}

// class TwoTuples<T, F> {
// private T One;
// private F Tow;
//
// public T getOne() {
// return One;
// }
//
// public TwoTuples<T, F> setOne(T one) {
// One = one;
// return this;
// }
//
// public F getTow() {
// return Tow;
// }
//
// public TwoTuples<T, F> setTow(F tow) {
// Tow = tow;
// return this;
// }
//
// }

class Param {
	/**
	 * 名字
	 */
	private String name;
	/**
	 * 数据类型
	 */
	private Class<?> type;
	/**
	 * 下标 从0开始
	 */
	private int index;

	/**
	 * 参数标签
	 */
	private MyParam myParam;

	/**
	 * 返回 名字
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * 返回 参数标签
	 * 
	 * @return myParam
	 */
	public MyParam getMyParam() {
		return myParam;
	}

	/**
	 * 设置 参数标签
	 * 
	 * @param myParam
	 *            the myParam to set
	 */
	public Param setMyParam(MyParam myParam) {
		this.myParam = myParam;
		return this;
	}

	/**
	 * 设置 名字
	 * 
	 * @param name
	 *            the name to set
	 */
	public Param setName(String name) {
		this.name = name;
		return this;
	}

	/**
	 * 返回 数据类型
	 * 
	 * @return type
	 */
	public Class<?> getType() {
		return type;
	}

	/**
	 * 设置 数据类型
	 * 
	 * @param type
	 *            the type to set
	 */
	public Param setType(Class<?> type) {
		this.type = type;
		return this;
	}

	/**
	 * 返回 下标 从0开始
	 * 
	 * @return index
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * 设置 下标 从0开始
	 * 
	 * @param index
	 *            the index to set
	 */
	public Param setIndex(int index) {
		this.index = index;
		return this;
	}

}

// 控制层实体
class ContrillerMe {
	private Object obj;
	private Method method;

	private List<Param> params;

	public Object getObj() {
		return obj;
	}

	public ContrillerMe setObj(Object obj) {
		this.obj = obj;
		return this;
	}

	public Method getMethod() {
		return method;
	}

	public ContrillerMe setMethod(Method method) {
		this.method = method;
		return this;
	}

	public List<Param> getParams() {
		return params;
	}

	public void setParams(List<Param> params) {
		this.params = params;
	}

}

/**
 * 文件工具类
 * 
 * <p>
 * Title: FileUtile
 * </p>
 * 
 * <p>
 * Description:
 * </p>
 * 
 * @author 魏帅
 * 
 * @date 2018年5月25日
 */
class FileUtile {
	protected static Map<Object, Object> getAllUseObject(String filePhth, String beas) throws Exception {
		Map<Object, Object> arr = new HashMap<>();
		readfile(filePhth, arr, beas);
		return arr;
	}

	/**
	 * 读取某个文件夹下的所有文件
	 * 
	 * @param beas
	 * @throws Exception
	 */
	protected static boolean readfile(String filepath, Map<Object, Object> beans, String beas) throws Exception {
		try {

			File file = new File(filepath);
			if (!file.isDirectory()) {
				// System.out.println("文件");
				// System.out.println("path=" + file.getPath());
				// System.out.println("absolutepath=" + file.getAbsolutePath());
				// System.out.println("name=" + file.getName());
				addFile(beans, beas, file);
			} else if (file.isDirectory()) {
				// System.out.println("文件夹");
				String[] filelist = file.list();
				for (int i = 0; i < filelist.length; i++) {
					File readfile = new File(filepath + "\\" + filelist[i]);
					if (!readfile.isDirectory()) {
						addFile(beans, beas, readfile);
					} else if (readfile.isDirectory()) {
						readfile(filepath + "\\" + filelist[i], beans, beas);
					}
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("readfile()   Exception:" + e.getMessage());
		}
		return true;
	}

	// 加载
	private static void addFile(Map<Object, Object> beans, String beas, File file) throws Exception {
		String path = file.getPath();
		if (path.endsWith(".class")) {
			String substring = path.substring(beas.length() + 1);

			String className = substring.replace("\\", ".").replace(".class", "");
			// 判断 并将类放入集合
			judgeClass(className, beans);
		}
	}

	// 判断类上的注解 是否 是自己的注解
	@SuppressWarnings("unchecked")
	private static void judgeClass(String className, Map<Object, Object> beans) throws Exception {
		if (StringUtils.isBlank(className)) {
			// return null;
		}

		Class<?> cla = Class.forName(className);
		Annotation[] annotations = cla.getAnnotations();

		for (Annotation annotation : annotations) {
			if (annotation instanceof MyController) {
				String beanName = cla.getName();
				if (beanName.lastIndexOf(".") > 0) {
					beanName = beanName.substring(beanName.lastIndexOf(".") + 1);
				}
				beans.put(beanName, cla.newInstance());
			} else if (annotation instanceof MyBean) {
				MyBean mybean = (MyBean) annotation;
				String beanName = null;
				if (StringUtils.isBlank(mybean.value()) || InterfaceFinal.defStr.equals(mybean.value())) {
					beanName = cla.getName();
					if (beanName.lastIndexOf(".") > 0) {
						beanName = beanName.substring(beanName.lastIndexOf(".") + 1);
					}
				} else {
					beanName = mybean.value();
				}
				beans.put(beanName, cla.newInstance());
			} else if (annotation instanceof MyServicec) {
				MyServicec mybean = (MyServicec) annotation;
				String beanName = null;
				if (StringUtils.isBlank(mybean.value()) || InterfaceFinal.defStr.equals(mybean.value())) {
					beanName = cla.getName();
					if (beanName.lastIndexOf(".") > 0) {
						beanName = beanName.substring(beanName.lastIndexOf(".") + 1);
					}
				} else {
					beanName = mybean.value();
				}
				beans.put(beanName, cla.newInstance());
			} else if (annotation instanceof MyDao) {
				MyDao dao = (MyDao) annotation;
				String beanName = null;
				if (StringUtils.isBlank(dao.value()) || InterfaceFinal.defStr.equals(dao.value())) {
					beanName = cla.getName();
					if (beanName.lastIndexOf(".") > 0) {
						beanName = beanName.substring(beanName.lastIndexOf(".") + 1);
					}
				} else {
					beanName = dao.value();
				}
				beans.put(beanName, cla.newInstance());
			}
			if (annotation instanceof MyDataSource) {
				Map<String, BaseDao> data = null;
				MyDataSource my = (MyDataSource) annotation;
				if (beans.containsKey(my.JDBC_name())) {
					if (my.JDBC_name().equals(MyDataSource.jdbcDefault)) {
						throw new RuntimeException(" 数据库连接对象  重复 请检查 ");
					}
					throw new RuntimeException(" 数据库连接对象 [" + my.JDBC_name() + "]重复 请检查 ");
				} else {

					/**
					 * MyDataSource config = new MyDataSource();
					 * config.setJdbcUrl("jdbc:mysql://192.168.2.117:3306/rongtongbgh?zeroDateTimeBehavior=convertToNull");
					 * config.setUsername("zontonec"); config.setPassword("RongTong2015");
					 * config.setDriverClassName("com.mysql.jdbc.Driver");
					 * config.setAutoCommit(true); config.setMinimumIdle(10);
					 * config.setMaximumPoolSize(200); BaseDao baseDao = new BaseDao(config);
					 */
					com.bean.MyDataSource myDataSource = new com.bean.MyDataSource();
					myDataSource.setJdbcUrl(my.jdbcUrl());
					myDataSource.setUsername(my.username());
					myDataSource.setPassword(my.password());
					myDataSource.setDriverClassName(my.driverClassName());
					myDataSource.setAutoCommit(true);
					myDataSource.setMinimumIdle(my.minimumIdle());
					myDataSource.setMaximumPoolSize(my.maximumPoolSize());
					if (beans.get(MyDataSource.class) != null) {
						data = (Map<String, BaseDao>) beans.get(MyDataSource.class);
					} else {
						data = new HashMap<>();
					}
					data.put(my.JDBC_name(), new BaseDao(myDataSource));
				}
				beans.put(MyDataSource.class, data);
			}
		}
		// return null;
	}

}

class StringUtils {
	public static boolean isBlank(final CharSequence cs) {
		int strLen;
		if (cs == null || (strLen = cs.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if (Character.isWhitespace(cs.charAt(i)) == false) {
				return false;
			}
		}
		return true;
	}

	public static boolean isNotBlank(final CharSequence cs) {
		return !isBlank(cs);
	}
}