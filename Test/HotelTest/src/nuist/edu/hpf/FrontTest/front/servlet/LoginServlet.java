package nuist.edu.hpf.FrontTest.front.servlet;

//登录页面
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nuist.edu.hpf.FrontTest.front.service.UserService;
import nuist.edu.hpf.FrontTest.front.service.UserServiceImpl;
import nuist.edu.hpf.Test.bean.User;
import nuist.edu.hpf.Test.util.ConstantUtil;
import nuist.edu.hpf.Test.util.CookieUtils;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/app/login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//tomcat需要对post精心处理， 解决post请求，中文乱码问题
		request.setCharacterEncoding("utf-8");

		String method = request.getParameter("method");

		String loginname = request.getParameter("loginname");
		String password = request.getParameter("password");
		String remenber = request.getParameter("remenber");
		System.out.println("method:"+method+"   loginname:"+loginname+"   password:"+password+  "  remenber:"+remenber);
		
		
		if(method != null && method.equals("submitTable")) {
			//提交登录表单
			if(loginname != null && !loginname.equals("")
					&& password != null && !password.equals("")) {

				//去数据库查询，用户输入的用户名和密码是否存在，同时是未删除（还没有注销）的状态
				UserService userService = new UserServiceImpl();
				User user = userService.findByLoginNameAndPass(loginname,password);
				System.out.println("前台登录user:"+user);

				if(user != null) {
					//保存在session中的数据是在整个浏览器中有效，默认30分钟有效，前提是保存数据到session后浏览器没有关闭
					//浏览器一关闭，保存在session中的数据马上失效
					request.getSession().setAttribute(ConstantUtil.SESSION_NAME, user);

					// "reme"是前端的value
					if(remenber != null && remenber.equals("reme")) {
						//记住密码一周   需要保存的信息：用户名、密码、保存的时间，cookie的的名字

							//中文  先编码再解码   URLEncoder.encode(password,utf-8)   ★★★★★★★

						CookieUtils.addCookie(URLEncoder.encode(loginname, "utf-8"),URLEncoder.encode(password,"utf-8"),7*24*60*60*1000,ConstantUtil.COOKIE_NAME,response,request);
						//必须加上response 和 request，把cookie响应出去
					}
					
					//登录成功，跳转到首页
					response.sendRedirect(getServletContext().getContextPath()+"/app/index.do");
				}else {
					//登录不成功
					request.setAttribute("message", "登录名或密码错误");
					//跳转到登录页面
					request.getRequestDispatcher("/WEB-INF/jsp/app/login.jsp").forward(request, response);
				}
			}
		}else {
			//跳转到登录页面
			request.getRequestDispatcher("/WEB-INF/jsp/app/login.jsp").forward(request, response);
		}
		
	}

}
