package com.hpe.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hpe.pojo.Users;
import com.hpe.service.IUsersService;
import com.hpe.service.impl.UsersServiceImpl;
import com.jspsmart.upload.SmartFile;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

/**
 * Servlet implementation class UsersServlet
 */
@WebServlet("/usersServlet")
public class UsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private IUsersService usersService = new UsersServiceImpl();
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsersServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset = utf-8");
		String action = request.getParameter("action");
		if (action.equals("login")) {
			//登录
			login(request, response);
		} else if (action.equals("logout")) {
			//退出
			logout(request, response);
		} else if(action.equals("update")){
			update(request, response);
		} else if(action.equals("updateImg")){
			updateImg(request, response);
		}
	}
 
	protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		//获取用户名
		String name = request.getParameter("name");
		//获取密码
		String pwd = request.getParameter("pwd");
		//调用service
		Users users = usersService.login(name, pwd);
		if (users != null) {
			HttpSession session = request.getSession();
			session.setAttribute("users", users);
			out.write("<script>"
					+ "alert('恭喜你登录成功');"
					+ "window.location.href = '"+ request.getContextPath() +"/web/index.jsp';"
					+ "</script>");
		} else {
			out.write("<script>"
					+ "alert('登录失败');"
					+ "window.location.href = '"+ request.getContextPath() +"/web/login.jsp';"
					+ "</script>");
		}
	}
	
	protected void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset = utf-8");
		//获取参数
		//获取用户名
		String name=request.getParameter("name");
		//获取密码
		String pwd=request.getParameter("pwd");
		//建立用户
		Users users = new Users(name, pwd);
		int result = usersService.register(users);
		if (result == 1) {
			out.write("<script>" + "alert('恭喜你,注册成功');" + "window.location.href = '" + request.getContextPath()
					+ "/qiantai/login.jsp';" + "</script>");
		} else {
			if (result == -1) {
				out.write("<script>" + "alert('用户名重复');" + "window.location.href = '" + request.getContextPath()
						+ "/qiantai/reg.jsp';" + "</script>");
			} else {
				out.write("<script>" + "alert('注册失败');" + "window.location.href = '" + request.getContextPath()
						+ "/qiantai/reg.jsp';" + "</script>");
			}
		} 
}

	
	protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		if (session.getAttribute("users") != null){
		session.removeAttribute("users");
		out.write("<script>"
				+ "alert('恭喜你退出成功！');"
				+ "window.location.href = '"+ request.getContextPath() +"/web/index.jsp';"
				+ "</script>");
		} else {
			out.write("<script>"
					+ "alert('你还没有登录！');"
					+ "window.location.href = '"+ request.getContextPath() +"/web/index.jsp';"
					+ "</script>");
		}
	}
	
	protected void updateImg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//获取用户名
		String name= "admin";//request.getParameter("name");
		PrintWriter out = response.getWriter();
		//1.创建对象
		SmartUpload smartUpload=new SmartUpload();
		//2.执行上传初始化
		smartUpload.initialize(this.getServletConfig(),request, response);
		try {
			//3.执行文件上传
			smartUpload.upload();
			//获取上传文件
			SmartFile file = smartUpload.getFiles().getFile(0);
			//获取文件名
			String imgpath = "img/"+file.getFileName();
			Users users = usersService.getUserByName(name);
			users.setImgPath(imgpath);
			int result=usersService.updateImage(users);
			
			if (result == 1) {
				smartUpload.save("/img");
				out.write("<script>"
						+ "alert('保存成功');" 
						+ "window.location.href='" + request.getContextPath()
						+ "/menusServlet?action=all';"
						+ "</script>");
			} else {
				out.write("<script>" + "alert('上传失败');" + "window.location.href='" + request.getContextPath()
					+ "/menusServlet?action=findTypeAll';" + "</script>");
			}
		} catch (SmartUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out =response.getWriter();
		HttpSession session=request.getSession();
		
		String name=request.getParameter("name");
		String pwd=request.getParameter("pwd");
		Users users=new Users();
		users.setName(name);
		users.setPwd(pwd);
		int result=usersService.updateUsers(users);
		if(result==1){
			session.removeAttribute("users");
			out.write("<script>"
					+"alert('恭喜你修改成功');" 
					+"window.parent.location.href='"+request.getContextPath()+"/web/login.jsp';"
					+"</script>");
		}
		else{
			if(result==-1){
				out.write("<script>"
						+"alert('用户名重复');" 
						+"window.location.href='"+request.getContextPath()+"/web/center.jsp';"
						+"</script>");
			}
			else{
				out.write("<script>"
						+"alert('修改失败');" 
						+"window.location.href='"+request.getContextPath()+"/web/center.jsp';"
						+"</script>");
			}
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
