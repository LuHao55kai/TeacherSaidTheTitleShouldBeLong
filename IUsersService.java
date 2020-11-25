/**
 * 
 */
package com.hpe.service;

import com.hpe.pojo.Users;

/** 
 * 类描述：
 * 作者：linsiyuan 
 * 创建日期：2020年11月24日
 * 修改人：
 * 修改日期：
 * 修改内容：
 * 版本号： 1.0.0   
 */
public interface IUsersService {
	
	Users login(String name, String pwd);
	
	int updateUsers(Users users);
	
	Users getUserByName(String name);
	
	int register(Users user);

}
