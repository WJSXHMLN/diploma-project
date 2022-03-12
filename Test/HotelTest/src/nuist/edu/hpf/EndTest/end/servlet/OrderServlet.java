package nuist.edu.hpf.EndTest.end.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nuist.edu.hpf.EndTest.end.service.DinnerTableService;
import nuist.edu.hpf.EndTest.end.service.DinnerTableServiceImpl;
import nuist.edu.hpf.EndTest.end.service.FoodService;
import nuist.edu.hpf.EndTest.end.service.FoodServiceImpl;
import nuist.edu.hpf.EndTest.end.service.OrderService;
import nuist.edu.hpf.EndTest.end.service.OrderServiceImpl;
import nuist.edu.hpf.Test.bean.DinnerTable;
import nuist.edu.hpf.Test.bean.Food;
import nuist.edu.hpf.Test.bean.Order;
import nuist.edu.hpf.Test.bean.User;
import nuist.edu.hpf.Test.util.ConstantUtil;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet("/sys/order.action")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		String id = request.getParameter("id");
		String disabled = request.getParameter("disabled");
		String orderDate = request.getParameter("orderDate");
		String orderCode = request.getParameter("orderCode");
		String dinnerTableId = request.getParameter("dinnerTableId");
		System.out.println("method:"+method+"   id:"+id+"  disabled:"+disabled);
		System.out.println("orderDate:"+orderDate+"  orderCode:"+orderCode+" dinnerTableId: "+dinnerTableId);
		
		HttpSession  session = request.getSession();
		User user = (User) session.getAttribute(ConstantUtil.SESSION_NAME);
		DinnerTableService dinnerTableService = new DinnerTableServiceImpl();
		OrderService  orderService = new OrderServiceImpl();
		FoodService foodService = new FoodServiceImpl();
		if(method != null && method.equals("list")) {
			//查询所有的订单，在前端展示
			List<Order>  orders= orderService.find(user,orderCode,orderDate,dinnerTableId);
			System.out.println("查询所有订单："+orders);
			
			//获取所有的餐桌  
			List<DinnerTable> dinnerTables = dinnerTableService.find(null, null, null);
			
			request.setAttribute("orderDate", orderDate);
			request.setAttribute("orderCode", orderCode);
			request.setAttribute("dinnerTableId", dinnerTableId);
			
			request.setAttribute("dinnerTables", dinnerTables);
			request.setAttribute("orders", orders);
			request.getRequestDispatcher("/WEB-INF/jsp/sys/orderList.jsp").forward(request, response);
		}else if(method != null && method.equals("update")) {
			//删除或激活
			Order  order = orderService.findById(Integer.parseInt(id));
			order.setDisabled(Integer.parseInt(disabled));
			
			orderService.update(order);
			
			request.getRequestDispatcher("/sys/order.action?method=list").forward(request, response);
		}else if(method != null && method.equals("orderAddFood")) {
			//查询所有未删除菜品
			List<Food> foods = foodService.find(null, null);
			System.out.println("foods:"+foods);
			
			//删除或激活
			Order  order = orderService.findById(Integer.parseInt(id));
			request.setAttribute("foods", foods);
			request.setAttribute("order", order);
			request.getRequestDispatcher("/WEB-INF/jsp/sys/orderAddFood.jsp").forward(request, response);
		}
		
	}

}
