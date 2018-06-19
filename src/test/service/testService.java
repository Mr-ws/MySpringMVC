/**  

* <p>Title: testService.java</p>  

* <p>Description: </p>  

* <p>Copyright: Copyright (c) 2017</p>  
 

* @author 魏帅  

* @date 2018年6月2日  
   
*/
package test.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.interfacebean.MyResource;
import com.interfacebean.MyServicec;

import test.dao.testDAO;

/**
 * 
 * <p>
 * Title: testService
 * </p>
 * 
 * <p>
 * Description:
 * </p>
 * 
 * @author 魏帅
 * 
 * @date 2018年6月2日
 * 
 */
@MyServicec
public class testService {
	@MyResource
	testDAO dao;

	public Map<String, Object> testService() throws SQLException {
	/*	System.out.println("访问 service 成功");
		dao.testDao2();
		return dao.testDao();*/
		
		dao.addStu();
		return new HashMap<>();
	}
}
