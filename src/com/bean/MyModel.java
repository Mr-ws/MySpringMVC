/**  

* <p>Title: MyModel.java</p>  

* <p>Description: </p>  

* <p>Copyright: Copyright (c) 2017</p>  
 

* @author κ˧  

* @date 2018��6��9��  
   
*/
package com.bean;

import java.util.Map;

/**
 * ��� ����
 * <p>
 * Title: MyModel
 * </p>
 * 
 * <p>
 * Description:
 * </p>
 * 
 * @author κ˧
 * 
 * @date 2018��6��9��
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
