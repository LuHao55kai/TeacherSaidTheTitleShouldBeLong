/**
 * 
 */
package com.hpe.dao.impl;

import com.hpe.dao.IUsersDao;
import com.hpe.pojo.Users;
import com.hpe.util.DBUtil;

/** 
 * 类描述：
 * 作者：linsiyuan 
 * 创建日期：2020年11月24日
 * 修改人：
 * 修改日期：
 * 修改内容：
 * 版本号： 1.0.0   
 */
public class UsersDaoImpl implements IUsersDao {

	DBUtil dbutil = new DBUtil();
	public Users login(String name, String pwd) {
		String sql = "select * from users where name = ? and pwd = ?";
		Object[] param = {name, pwd};
		Users user = null;
		try {
			user = (Users) dbutil.getObject(Users.class, sql, param);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return user;
	}

	public Users getUserByName(String name) {
		String sql = "select * from users where name = ?";
		Object[] param = {name};
		Users users = null;
		try {
			users = (Users) dbutil.getObject(Users.class, sql, param);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return users;
	}

	public int updateUsers(Users users) {
		// TODO Auto-generated method stub
		String sql = "update users set name=?, pwd=? where id=?";
		int result = 0;
		Object[] param = {users.getName(), users.getPwd(), users.getId()};
		try {
			result = dbutil.execute(sql, param);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hpe.dao.IUsersDao#register(com.hpe.pojo.Users)
	 */
	@Override
	public int register(Users user) {
		String sql = "insert into users(name, pwd) values(?,?)";
		Object[] param = {user.getName(), user.getPwd()};
		int result = 0;
		try {
			result = dbutil.execute(sql, param);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	public int updateImage(Users users) {
		// TODO Auto-generated method stub
		String sql = "update users set imgPath=? where name=?";
		int result = 0;
		Object[] param = {users.getImgPath(), users.getName()};
		try {
			result = dbutil.execute(sql, param);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}
}
