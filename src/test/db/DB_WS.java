/**  

* <p>Title: DBHelp.java</p>  

* <p>Description: </p>  

* <p>Copyright: Copyright (c) 2017</p>  
 

* @author 魏帅  

* @date 2018年6月11日  
   
*/
package test.db;

import com.interfacebean.MyDataSource;

/**
 * 数据库 连接对象 1
 * <p>
 * Title: DBHelp
 * </p>
 * 
 * <p>
 * Description:
 * </p>
 * 
 * @author 魏帅
 * 
 * @date 2018年6月11日
 * 
 */
@MyDataSource(JDBC_name = "ws", jdbcUrl = "jdbc:mysql://192.168.2.117:3306/rongtongbgh?zeroDateTimeBehavior=convertToNull", driverClassName = "com.mysql.jdbc.Driver", password = "RongTong2015", username = "zontonec")
public class DB_WS {

}
