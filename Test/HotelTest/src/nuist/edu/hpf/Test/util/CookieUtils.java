package nuist.edu.hpf.Test.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class CookieUtils {

	//添加cookie，用户名  密码   时间  cookie名字
	public static void addCookie(String loginname, String password, int age, String cookieName, HttpServletResponse response, HttpServletRequest request) {

		/*//第一次创建cookie
		Cookie cookie = new Cookie(cookieName,loginname+"#"+password);
		*/

/*

		//第二三次获取cookie
		Cookie[] cookies = request.getCookies();

		Cookie cookie = null;
		if (cookie != null){
			for (Cookie cookie2:cookies) {
				if(cookie2.getName().equals(cookieName)){
					cookie2.setValue(loginname+"#"+password);
				}
			}
		}
*/


		StringBuffer  value = new StringBuffer();
		value.append(loginname);
		value.append("#");
		value.append(password);//admin#123456
		
		Cookie  cookie = getCookieByName(request,cookieName);
		if(cookie != null) {
			cookie.setValue(value.toString());
		}else {
			cookie = new Cookie(cookieName, value.toString());
		}
		
		//1 设置cookie的有效时间
		cookie.setMaxAge(age);
		//2 cookie的作用域
		cookie.setPath(request.getContextPath());

		System.out.println("request.getContextPath():"+request.getContextPath());
		//3 cookie响应出去
		response.addCookie(cookie);
	}

	/* 通过Cookie名获取cookie */
	public static Cookie getCookieByName(HttpServletRequest request, String cookieName) {
		//从request中获取所有的cookie信息
		Cookie[]  cookies =request.getCookies();
		
		if(cookies != null && cookies.length >0) {
			for (Cookie cookie : cookies) {
				if(cookie.getName().equals(ConstantUtil.COOKIE_NAME)) {
					return cookie;
				}
			}
		}
		return null;
		
	}

	//移除cookie
	public static void removeCookie(String cookieName, HttpServletRequest request, HttpServletResponse response) {
		Cookie  cookie = getCookieByName(request, cookieName);
		
		if(cookie != null) {
			//设置cookie的生命周期
			cookie.setMaxAge(0);
			//设置cookie的作用域
			cookie.setPath(request.getContextPath());
			//将cookie响应出去
			response.addCookie(cookie);
		}
	}

}
