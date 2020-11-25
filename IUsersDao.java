/**
 * 
 */
package com.hpe.dao;

import com.hpe.pojo.Users;

public interface IUsersDao {
	
	/**
	 * 
	 * 方法描述：用户登陆
	 * @param name	用户名
	 * @param pwd	密码
	 * @return		信息
	 */
	
	Users login(String name, String pwd);
	//登录
	
	Users getUserByName(String name);
	//根据姓名查询
	
	int updateUsers(Users users);
	//修改数据
	
	int register(Users user);
	//注册

}
