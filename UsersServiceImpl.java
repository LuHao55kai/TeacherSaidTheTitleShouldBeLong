/**
 * 
 */
package com.hpe.service.impl;

import com.hpe.dao.IUsersDao;
import com.hpe.dao.impl.UsersDaoImpl;
import com.hpe.pojo.Users;
import com.hpe.service.IUsersService;

/** 
 * 类描述：
 * 作者：linsiyuan 
 * 创建日期：2020年11月24日
 * 修改人：
 * 修改日期：
 * 修改内容：
 * 版本号： 1.0.0   
 */
public class UsersServiceImpl implements IUsersService{
	
private IUsersDao usersDao = new UsersDaoImpl();
	

	public Users login(String name, String pwd) {
		return usersDao.login(name, pwd);
	}
	
	public int updateUsers(Users users) {
		Users user=usersDao.getUserByName(users.getName());
		if(user!=null && (users.getName().equals(user.getName())&&(users.getPwd()!=user.getPwd())))
		{
			return -1;
		}else{
			return usersDao.updateUsers(users);
		}
	}
	
	public Users getUserByName(String name) {
		return usersDao.getUserByName(name);
	}

	public int register(Users user) {
		Users users = usersDao.getUserByName(user.getName());
		if(users != null) {
			return -1;
		}
		return usersDao.register(user);
	}
	
	public int updateImage(Users users) {
		Users user=usersDao.getUserByName(users.getName());
		if(user!=null && (users.getName().equals(user.getName())&&(users.getPwd()!=user.getPwd())))
		{
			return -1;
		}else{
			return usersDao.updateImage(users);
		}
	}


}
