package nuist.edu.hpf.FrontTest.front.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nuist.edu.hpf.FrontTest.front.service.DinnerTableService;
import nuist.edu.hpf.FrontTest.front.service.DinnerTableServiceImpl;
import nuist.edu.hpf.FrontTest.front.service.FoodService;
import nuist.edu.hpf.FrontTest.front.service.FoodServiceImpl;
import nuist.edu.hpf.FrontTest.front.service.FoodTypeService;
import nuist.edu.hpf.FrontTest.front.service.FoodTypeServiceImpl;
import nuist.edu.hpf.FrontTest.front.service.OrderService;
import nuist.edu.hpf.FrontTest.front.service.OrderServiceImpl;
import nuist.edu.hpf.Test.bean.DinnerTable;
import nuist.edu.hpf.Test.bean.Food;
import nuist.edu.hpf.Test.bean.FoodType;
import nuist.edu.hpf.Test.bean.Order;
import nuist.edu.hpf.Test.bean.User;
import nuist.edu.hpf.Test.util.ConstantUtil;


/**
 * Servlet implementation class MenuListServlet
 */
@WebServlet("/app/menu.action")
public class MenuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MenuServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("=======menu.action================");
		String foodTypeId = request.getParameter("foodTypeId");
		//获取餐桌的id 通过request.getParameter方式获取的参数值都是String类型
		String id = request.getParameter("id");
		System.out.println("id:"+id);
		
		
		//③ 通过餐桌的id查询餐桌
		DinnerTableService dinnerTableService = new DinnerTableServiceImpl();
		DinnerTable dinnerTable = dinnerTableService.findById(Integer.parseInt(id));
		System.out.println("dinnerTable:"+dinnerTable);
		request.setAttribute("dinnerTable", dinnerTable);//前端显示
		
		
		FoodTypeService foodTypeService = new FoodTypeServiceImpl();
		//① 查询所有未删除的菜系名字
		List<FoodType> foodTypes= foodTypeService.findAll();
		System.out.println("foodTypes:"+foodTypes);
		
		if(foodTypeId == null || foodTypeId.equals("")) {
			//② 默认查询所有菜系中第一个未删除菜系的菜品
			Integer  foodTypeIdInt = foodTypes.get(0).getId();
			foodTypeId = Integer.toString(foodTypeIdInt);
		}
		
		FoodService foodService = new FoodServiceImpl();
		List<Food> foods = foodService.findByFoodTypeId(Integer.parseInt(foodTypeId));
		System.out.println("foods:"+foods);
		request.setAttribute("foodTypes", foodTypes);
		request.setAttribute("foods", foods);
		
		System.out.println("dinnerTable.getTableStatus().equals(\"1\"):"+Objects.equals(dinnerTable.getTableStatus(), 1));
		User user =(User) request.getSession().getAttribute(ConstantUtil.SESSION_NAME);
		if(Objects.equals(dinnerTable.getTableStatus(), 0)) {
			//餐桌未使用
			request.getRequestDispatcher("/WEB-INF/jsp/app/menu.jsp").forward(request, response);
			
		}else if(Objects.equals(dinnerTable.getTableStatus(), 1)  &&  Objects.equals(user.getId(),dinnerTable.getUseUserId())  ){
			//餐桌正在使用,且使用人是当前用户
			//判断该餐桌是否有未付款的订单，如有则跳转到订单详情页面去付款，如没有则跳转到正常的点餐页面
			OrderService orderService = new OrderServiceImpl();
			List<Order>  orders= orderService.findByTableId(Integer.parseInt(id),null);
			System.out.println("orders:"+orders);
			if(orders != null && orders.size() >0) {
				//跳转到订单详情页面
				request.setAttribute("orders", orders);
				request.getRequestDispatcher("/WEB-INF/jsp/app/orderItem.jsp").forward(request, response);
			}else {
				//④ 左边餐桌的购物车展示 
				HttpSession  session = request.getSession();
				//通过餐桌的id获取其购物车，   购物车  key为商品id  value为购买数量
				Map<Integer, Integer> shopCar= (Map<Integer, Integer>) session.getAttribute(id);
				List<Food>  foods2 = new ArrayList<>();
				//计算购物车中商品总金额
				Double total = 0.00;
				if(shopCar != null) {
					//拿到当前餐桌购物车中所有的菜品id
					Set<Integer> foodIds = shopCar.keySet();
					for (Integer foodId : foodIds) {
						//通过菜品的id查询菜品
						Food food = foodService.findById(foodId);
						System.out.println("通过id查询food:"+food);
						//通过购物车的key获取value，即购买数量
						Integer buyNum = shopCar.get(foodId);
						food.setBuyNum(buyNum);
						foods2.add(food);
						
						//当前商品购买需要的价格
						Double price = food.getBuyNum()*food.getPrice()*food.getDiscount();
						total = total+price;
					}
				}
			
			
				request.setAttribute("foods2", foods2);
				request.setAttribute("total", total);
				//response.sendRedirect是请求链断开的跳转，不能在下一个servlet或jsp中获取保存在request中的数据
				//response.sendRedirect("/WEB-INF/jsp/app/menu.jsp");
				request.getRequestDispatcher("/WEB-INF/jsp/app/menu.jsp").forward(request, response);
			}
			
		}else if(Objects.equals(dinnerTable.getTableStatus(), 1)  &&  user.getId()!=dinnerTable.getUseUserId() ){
			//餐桌正在使用,但使用人不是当前用户
			
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter  printWriter = response.getWriter();
			printWriter.println("<script type=\"text/javascript\"> alert(\"当前餐桌正在使用！\");  window.location.href = \""+request.getServletContext().getContextPath()+"/app/index.do\"; </script>");
			printWriter.flush();
   }
		
	}

}
