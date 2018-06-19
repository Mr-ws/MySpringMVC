package test.controller;

import java.sql.SQLException;

import com.bean.MyModel;
import com.interfacebean.MyController;
import com.interfacebean.MyMapping;
import com.interfacebean.MyParam;
import com.interfacebean.MyResource;

import test.service.testService;

@MyController
public class testController {

	@MyResource
	testService testservice;

	@MyMapping(value = "/test")
	public String testMain(@MyParam(value = "name") String name, @MyParam(value = "age", must = true) Integer age,
			MyModel model) {
		System.out.println("访问 控制层成功 ");
		model.addParams("name", name);
		try {
			System.out.println(testservice.testService());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "test";
	}
}
