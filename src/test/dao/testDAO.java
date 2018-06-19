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
		System.out.println("����  ysq �ɹ���" + mapBean);
		return mapBean;
	}

	@MyJDBC(Name = "ws")
	BaseDao wsDao;

	public Map<String, Object> testDao2() throws SQLException {
		Map<String, Object> mapBean = wsDao.getMapBean("select * from  jygy_user_kid  order by userid desc limit 1 ");
		System.out.println("���� ws �ɹ���" + mapBean);
		return mapBean;
	}

	public void addStu() {
		try {
			String sql = "insert stu ( name,  age, sex) values (? ,?,?)";

			Object[] addAndRTarrayKey = wsDao.addAndRTarrayKey(sql, "κ˧", 24, 1);
			System.out.println(addAndRTarrayKey.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}