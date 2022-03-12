package nuist.edu.hpf.FrontTest.front.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nuist.edu.hpf.FrontTest.front.service.DinnerTableService;
import nuist.edu.hpf.FrontTest.front.service.DinnerTableServiceImpl;
import nuist.edu.hpf.Test.bean.DinnerTable;
import nuist.edu.hpf.Test.bean.User;
import nuist.edu.hpf.Test.util.ConstantUtil;
		//DinnerTable 占位、取消占位
/**
 * Servlet implementation class DinnerTableServlet
 */
@WebServlet("/app/dinnerTable.action")
public class DinnerTableServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DinnerTableServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * 占座
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dinnerTableId = request.getParameter("dinnerTableId");
		String tableStatus = request.getParameter("tableStatus");
		User user =(User) request.getSession().getAttribute(ConstantUtil.SESSION_NAME);
		
		
		//①通过餐桌的id查询餐桌
		//Servlet是controller层 属于接入层 ，调用服务层service
		DinnerTableService dinnerTableService = new DinnerTableServiceImpl();
		DinnerTable  dinnerTable = dinnerTableService.findById(Integer.parseInt(dinnerTableId));
		dinnerTable.setTableStatus(Integer.parseInt(tableStatus));
		dinnerTable.setUseUserId(user.getId());
		
		//② 更改数据库，通过传入对象更改
		dinnerTableService.update(dinnerTable);
		response.sendRedirect(getServletContext().getContextPath()+"/app/menu.action?id="+dinnerTableId);
		
	}

}
