package test.dao;

import java.sql.SQLException;
import java.util.Map;

import com.DB.BaseDao;
import com.interfacebean.MyDao;
import com.interfacebean.MyJDBC;

@MyDao
public class testDAO {

	@MyJDBC(Name = "ysq")
	BaseDao base;

	public Map<String, Object> testDao() throws SQLException {
		Map<String, Object> mapBean = base.getMapBean("select * from  jygy_user_kid order by userid desc limit 1 ");
		System.out.println("访问  ysq 成功：" + mapBean);
		return mapBean;
	}

	@MyJDBC(Name = "ws")
	BaseDao wsDao;

	public Map<String, Object> testDao2() throws SQLException {
		Map<String, Object> mapBean = wsDao.getMapBean("select * from  jygy_user_kid  order by userid desc limit 1 ");
		System.out.println("访问 ws 成功：" + mapBean);
		return mapBean;
	}

	public void addStu() {
		try {
			String sql = "insert stu ( name,  age, sex) values (? ,?,?)";

			Object[] addAndRTarrayKey = wsDao.addAndRTarrayKey(sql, "魏帅", 24, 1);
			System.out.println(addAndRTarrayKey.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}