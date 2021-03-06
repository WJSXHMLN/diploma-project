package nuist.edu.hpf.FrontTest.front.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nuist.edu.hpf.FrontTest.front.service.FoodService;
import nuist.edu.hpf.FrontTest.front.service.FoodServiceImpl;
import nuist.edu.hpf.FrontTest.front.service.FoodTypeService;
import nuist.edu.hpf.FrontTest.front.service.FoodTypeServiceImpl;
import nuist.edu.hpf.Test.bean.Food;
import nuist.edu.hpf.Test.bean.FoodType;

/**
 * Servlet implementation class MenuListServlet
 */
@WebServlet("/app/menuList.do")
public class MenuListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MenuListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String foodTypeId = request.getParameter("foodTypeId");
		//① 查询所有未删除的菜系名字   (调服务层)
		FoodTypeService foodTypeService = new FoodTypeServiceImpl();  //创建服务层对象（接口不可以创建对象，但可以通过实现类创建对象）
		List<FoodType> foodTypes= foodTypeService.findAll();			//
		System.out.println("foodTypes:"+foodTypes);					//测试用
		

		if(foodTypeId == null || foodTypeId.equals("")) {
			//② 默认查询所有菜系中第一个未删除菜系的菜品
			Integer  foodTypeIdInt = foodTypes.get(0).getId();
			foodTypeId = Integer.toString(foodTypeIdInt);
		}
		
		FoodService foodService = new FoodServiceImpl();
		List<Food> foods = foodService.findByFoodTypeId(Integer.parseInt(foodTypeId));//封装到实体bean的Food中
		System.out.println("foods:"+foods);
		
		request.setAttribute("foodTypes", foodTypes);    //前端查看
		request.setAttribute("foods", foods);
		request.getRequestDispatcher("/WEB-INF/jsp/app/menuList.jsp").forward(request, response);
	}

}
