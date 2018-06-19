package com.DB;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 加载实体�?? 结果�?? 工具�??
 * 
 * @author WeiShuai
 * @date 2017�??12�??20�??
 *
 */
public class ResultSetUtil {
	// 加载实体�??
	public static <T> T getObject(ResultSet rs, Class<T> cla)
			throws InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException,
			IllegalArgumentException, InvocationTargetException, SQLException {
		Field[] fields = cla.getDeclaredFields();
		T t = cla.newInstance();

		for (int i = 0; i < fields.length; i++) {
			String name = fields[i].getName();
			String s = name.substring(0, 1);
			String doStr = s.toUpperCase() + name.substring(1);
			String typeStr = fields[i].getType().toString();
			Method declaredMethod = null;
			if (typeStr.endsWith("Integer") || typeStr.endsWith("int")) {
				if (isExistColumn(rs, name)) {
					try {
						declaredMethod = cla.getDeclaredMethod("set" + doStr, Integer.class);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						if (e instanceof java.lang.NoSuchMethodException) {
							declaredMethod = cla.getDeclaredMethod("set" + doStr, int.class);
						} else {
							e.printStackTrace();
						}
					}
					declaredMethod.invoke(t, rs.getInt(name));
				}
			} else if (typeStr.endsWith("String")) {
				if (isExistColumn(rs, name)) {
					declaredMethod = cla.getDeclaredMethod("set" + doStr, String.class);
					declaredMethod.invoke(t, rs.getString(name));
				}
			} else if (typeStr.endsWith("Double") || typeStr.endsWith("double")) {
				if (isExistColumn(rs, name)) {
					try {
						declaredMethod = cla.getDeclaredMethod("set" + doStr, Double.class);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						if (e instanceof java.lang.NoSuchMethodException) {
							declaredMethod = cla.getDeclaredMethod("set" + doStr, Double.class);
						} else {
							e.printStackTrace();
						}
					}
					declaredMethod.invoke(t, rs.getDouble(name));
				}
			}
		}
		return t;
	}

	public static boolean isExistColumn(ResultSet rs, String columnName) {
		try {
			if (rs.findColumn(columnName) > 0) {
				return true;
			}
		} catch (SQLException e) {
			return false;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public static <T> T mapToBean(Map<String, Object> map, Class<T> cla) {
		// �Զ��Ÿ����ֲ�
		// �Զ��Ÿ�ֲ�??
		map = mapToBean(map);
		if (map == null || map.isEmpty())
			return null;
		try {
			Field[] fields = cla.getDeclaredFields();
			T t = cla.newInstance();

			for (int i = 0; i < fields.length; i++) {
				String name = fields[i].getName();
				String s = name.substring(0, 1);
				String doStr = s.toUpperCase() + name.substring(1);
				String typeStr = fields[i].getType().toString();
				Method declaredMethod = null;
				if (map.containsKey(name) && map.get(name) != null) {
					_packagingBean(cla, map, t, name, doStr, typeStr, declaredMethod);
				}
			}
			return t;
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * �� map ��key ��.�ָ����? �� map ��key ��.�ָ����?? >>>>>>> develop
	 * 
	 * @param map
	 * @return
	 * @author WeiShuai
	 * @date 2018��1��22��
	 * @date 2018��1��22��
	 */
	private static Map<String, Object> mapToBean(Map<String, Object> map) {
		if (map == null) {
			return null;
		}
		Map<String, Object> objs = new HashMap<String, Object>();
		for (String name : map.keySet()) {
			if (name != null && map.get(name) != null) {
				if (name.indexOf('.') < 0) {
					objs.put(name, map.get(name));
				} else {
					String key = name.substring(0, name.indexOf("."));
					if (!objs.containsKey(key)) {
						objs.put(key, mapToOneToOne(name, map, name.indexOf(".") + 1));
					}
				}
			}
		}
		return objs;
	}

	// �ݹ������Ӷ���
	// �ݹ�����Ӷ���??
	private static Map<String, Object> mapToOneToOne(String name, Map<String, Object> map, int index) {
		Map<String, Object> objs = new HashMap<String, Object>();
		String substring = name.substring(0, index);
		for (String name1 : map.keySet()) {
			int indexOf = name1.indexOf(substring);
			if (indexOf > -1) {
				String nameTemp = name1.substring(index);
				if (nameTemp.indexOf('.') < 0) {
					objs.put(nameTemp, map.get(name1));
				} else {
					objs.put(nameTemp.substring(0, nameTemp.indexOf(".")),
							mapToOneToOne(name1, map, nameTemp.indexOf(".") + 1 + index));
				}
			}
		}
		// ѭ��������������˶�����ĳһ�����Բ�Ϊnull �򷵻ض��� ���򷵻�null;
		// ѭ������������˶�����ĳһ�����Բ�Ϊnull �򷵻ض��� ���򷵻�null;
		for (String obj : objs.keySet()) {
			if (objs.get(obj) != null) {
				return objs;
			}
		}
		return null;
	}

	public static <T> List<T> listMapToListBean(List<Map<String, Object>> lsitMap, Class<T> cla) {
		if (lsitMap == null)
			return null;
		if (lsitMap.isEmpty()) {
			return new ArrayList<T>();
		}
		List<T> objList = new ArrayList<T>();
		for (Map<String, Object> map : lsitMap) {
			try {
				Field[] fields = cla.getDeclaredFields();
				T t = cla.newInstance();

				for (int i = 0; i < fields.length; i++) {
					String name = fields[i].getName();
					String s = name.substring(0, 1);
					String doStr = s.toUpperCase() + name.substring(1);
					String typeStr = fields[i].getType().toString();
					Method declaredMethod = null;
					if (map.containsKey(name) && map.get(name) != null) {
						_packagingBean(cla, map, t, name, doStr, typeStr, declaredMethod);
					}
				}
				objList.add(t);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return objList;
	}

	private static <T> void _packagingBean(Class<T> cla, Object object, T t, String name, String doStr, String typeStr,
			Method declaredMethod) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		Map<String, Object> map = null;
		ResultSet rs = null;
		if (object instanceof Map) {
			map = (Map<String, Object>) object;
		} else if (object instanceof ResultSet) {
			rs = (ResultSet) object;
		}

		Object valuse = null;

		if (object instanceof Map) {

			valuse = ((Map<String, Object>) object).get(name);
		} else if (object instanceof ResultSet) {
			rs = (ResultSet) object;
			if (isExistColumn(rs, name)) {
				try {
					valuse = rs.getObject(name);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					valuse = null;
				}
			}
		}

		if (valuse == null) {
			return;
		}

		if (typeStr.endsWith("Integer") || typeStr.endsWith("int")) {
			try {
				declaredMethod = cla.getDeclaredMethod("set" + doStr, Integer.class);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				if (e instanceof java.lang.NoSuchMethodException) {
					declaredMethod = cla.getDeclaredMethod("set" + doStr, int.class);
				} else {
					e.printStackTrace();
				}
			}
			declaredMethod.invoke(t, Integer.parseInt(String.valueOf(valuse)));
		} else if (typeStr.endsWith("String")) {
			declaredMethod = cla.getDeclaredMethod("set" + doStr, String.class);
			declaredMethod.invoke(t, String.valueOf(valuse));
		} else if (typeStr.endsWith("Double") || typeStr.endsWith("double")) {
			try {
				declaredMethod = cla.getDeclaredMethod("set" + doStr, Double.class);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				if (e instanceof java.lang.NoSuchMethodException) {
					declaredMethod = cla.getDeclaredMethod("set" + doStr, double.class);
				} else {
					e.printStackTrace();
				}
			}
			declaredMethod.invoke(t, Double.parseDouble(String.valueOf(valuse)));
		} else if (typeStr.endsWith("Long") || typeStr.endsWith("long")) {
			try {
				declaredMethod = cla.getDeclaredMethod("set" + doStr, Long.class);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				if (e instanceof java.lang.NoSuchMethodException) {
					declaredMethod = cla.getDeclaredMethod("set" + doStr, long.class);
				} else {
					e.printStackTrace();
				}
			}
			declaredMethod.invoke(t, Long.parseLong(String.valueOf(valuse)));
		}
	}
}
