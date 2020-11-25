/**
 * 
 */
package com.hpe.pojo;

/** 
 * 类描述：
 * 作者：linsiyuan 
 * 创建日期：2020年11月24日
 * 修改人：
 * 修改日期：
 * 修改内容：
 * 版本号： 1.0.0   
 */
public class Users {
	
	private int id;//用户编号
	private String name;//用户名
	private String pwd;//密码
	private String imgPath;//图片路径
	
	public Users(int id, String name, String pwd, String imgPath) {
		super();
		this.id = id;
		this.name = name;
		this.pwd = pwd;
		this.imgPath = imgPath;
	}
	
	public Users(String name, String pwd) {
		super();
		this.name = name;
		this.pwd = pwd;
	}
	
	public Users() {
		super();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

}
