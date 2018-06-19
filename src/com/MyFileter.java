package com;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.MyModel;
import com.util.DateTimeUtil;

@WebFilter(filterName = "myfilter", urlPatterns = "/*")
public class MyFileter implements Filter {

	protected static Map<String, ContrillerMe> mappingMap;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain arg2)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		// TODO Auto-generated method stub
		// http://localhost:8080/Example/AServlet?username=zhangsan����ε�ַҲ����Ϊ������Ϣ��װ��request�����У�request�����Ȼ���ṩ��صķ�������ȡ��Щ������Ϣ����Щ��Ϣ��ʵ��������·����Ϣ��
		// request����ͨ�����·�������ȡ����·����������ʾ��
		// String getServerName()����ȡ����������localhost��
		// String getServerPort()����ȡ�������˿ںţ�8080��
		// String getContextPath()����ȡ��Ŀ����/Example��
		// String getServletPath()����ȡServlet·����/AServlet��
		// String getQueryString()����ȡ�������֣����ʺź���Ĳ��֣�username=zhangsan
		// String getRequestURI()����ȡ����URI��������Ŀ��+Servlet·����/Example/AServlet
		// String
		// getRequestURL()����ȡ����URL�����ڲ�������������������·����http://localhost:8080/Example/AServlet
		// ��

		String servletPath = request.getServletPath();
		ContrillerMe contrillerMe = mappingMap.get(servletPath);
		// 404����
		if (contrillerMe == null) {
			response.sendError(HttpServletResponse.SC_BAD_GATEWAY);
			return;
		}
		List<Param> params = contrillerMe.getParams();
		try {
			Object[] o = null;
			MyModel model = null;
			if (!params.isEmpty()) {
				o = new Object[params.size()];
				for (int i = 0; i < params.size(); i++) {
					Param param = params.get(i);
					if (StringUtils.isBlank(param.getName())) {
						if (param.getType() == MyModel.class) {
							o[i] = model = new Model();
							continue;
						}
						o[i] = null;
						continue;
					}

					String parameter = request.getParameter(param.getName());
					o[i] = ClassUtil.getTypeByObj(parameter, param.getType());
					// ���ʹ���˱�ǩ�� �˲���������Ϊ ����� �򷵻� 400
					if (param.getMyParam() != null && param.getMyParam().must() == true && o[i] == null) {
						response.sendError(HttpServletResponse.SC_BAD_REQUEST);
						return;
					}
				}
			}
			Method method = contrillerMe.getMethod();
			Object returnValue = method.invoke(contrillerMe.getObj(), o);
			Class<?> returnType = method.getReturnType();

			if (model != null) {
				Map<String, Object> params2 = model.getParams();
				for (String key : params2.keySet()) {
					request.setAttribute(key, params2.get(key));
				}
			}

			if (returnType == String.class) {
				request.getRequestDispatcher("/" + String.valueOf(returnValue) + ".jsp").forward(request, response);
				return;
			} else if (returnType == MyModel.class) {
				MyModel myModel = (MyModel) returnValue;
				if (myModel != null) {
					String jspPath = myModel.getJSPPath();
					if (StringUtils.isNotBlank(jspPath)) {
						request.getRequestDispatcher("/" + jspPath + ".jsp").forward(request, response);
						return;
					}

				}
			}

			arg2.doFilter(request, response);

		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
	}

}

class Model implements MyModel {
	Map<String, Object> data = new HashMap<String, Object>();
	String urlPath = null;

	@Override
	public MyModel addParams(String key, Object value) {
		// TODO Auto-generated method stub
		data.put(key, value);
		return this;
	}

	@Override
	public MyModel setJSPPath(String path) {
		// TODO Auto-generated method stub
		urlPath = path;
		return this;
	}

	@Override
	public String getJSPPath() {
		// TODO Auto-generated method stub
		return urlPath;
	}

	@Override
	public Map<String, Object> getParams() {
		// TODO Auto-generated method stub
		return data;
	}

	/**
	 *
	 *
	 * @author κ˧
	 * @date 2018��6��9��
	 */
	@Override
	public MyModel addAllParams(Map<String, Object> keyMap) {
		// TODO Auto-generated method stub
		data.putAll(keyMap);
		return this;
	}

}

class ClassUtil {
	@SuppressWarnings("unchecked")
	public static <T> T getTypeByObj(Object obj, Class<T> type) throws ClassNotFoundException {
		if (obj == null)
			return (T) null;
		@SuppressWarnings("unused")
		Object temp = null;
		String t = String.valueOf(obj);
		if (type == String.class) {
			return (T) (temp = t);
		} else if (type == Integer.class || type == int.class) {
			return (T) (temp = Integer.parseInt(String.valueOf(obj)));
		} else if (type == Long.class || type == long.class) {
			return (T) (temp = Long.parseLong(t));
		} else if (type == Double.class || type == double.class) {
			return (T) (temp = Double.parseDouble(t));
		} else if (type == Date.class) {
			return (T) (temp = DateTimeUtil.getDate(t));
		}
		throw new ClassNotFoundException(" �޷�ת������������ ");
	}
}