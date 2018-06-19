/**  

* <p>Title: MyModel.java</p>  

* <p>Description: </p>  

* <p>Copyright: Copyright (c) 2017</p>  
 

* @author 魏帅  

* @date 2018年6月9日  
   
*/
package com.bean;

import java.util.Map;

/**
 * 入参 函数
 * <p>
 * Title: MyModel
 * </p>
 * 
 * <p>
 * Description:
 * </p>
 * 
 * @author 魏帅
 * 
 * @date 2018年6月9日
 * 
 */
public interface MyModel {
	//
	public MyModel addParams(String key, Object value);

	public MyModel setJSPPath(String path);

	public MyModel addAllParams(Map<String, Object> keyMap);

	public String getJSPPath();

	public Map<String,   Object> getParams();
}
