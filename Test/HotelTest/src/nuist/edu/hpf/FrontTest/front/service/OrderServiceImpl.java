package nuist.edu.hpf.FrontTest.front.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import nuist.edu.hpf.FrontTest.front.dao.DinnerTableDao;
import nuist.edu.hpf.FrontTest.front.dao.DinnerTableDaoImpl;
import nuist.edu.hpf.FrontTest.front.dao.OrderDao;
import nuist.edu.hpf.FrontTest.front.dao.OrderDaoImpl;
import nuist.edu.hpf.Test.bean.DinnerTable;
import nuist.edu.hpf.Test.bean.Order;
import nuist.edu.hpf.Test.bean.OrderDetail;

public class OrderServiceImpl implements OrderService {

	//现在在服务层 ，需要调用持久层dao
	private OrderDao orderDao = new OrderDaoImpl();
	@Override
	public void order(int dinnerTableId, Map<Integer, Integer> shopCar, String total, Integer userId) {
		Order order = new Order();
		
		//拼装订单编码
		StringBuffer orderCode = new StringBuffer();
		orderCode.append("OP-");

		//样本日期格式
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		orderCode.append(dateFormat.format(new Date()));
		orderCode.append(UUID.randomUUID().toString());  //UUID不会重复的字符串
		
		order.setOrderCode(orderCode.toString());   //订单编码
		order.setTableId(dinnerTableId);			//桌号
		order.setTotalPrice(Double.valueOf(total));	//总金额
		
		orderDao.order(order,shopCar,userId);
	}
	@Override
	public List<Order> findByTableId(int dinnerTableId, Integer userId) {
		//根据餐桌的id查询所有未删除的未付款的订单
		List<Order>  orders = orderDao.findByTableId(dinnerTableId,userId);
		
		//遍历所有的订单，根据订单id查询其对于订单明细
		if(orders != null && orders.size() > 0) {
			for (Order order : orders) {
				List<OrderDetail>  details = orderDao.findByOrderId(order.getId());
				if(details != null && details.size()>0) {
					order.setOrderDetails(details);
				}
			}
		}
		
		return orders;   //service返回到Controller层
	}
	@Override
	public Order findById(int id) {
		return orderDao.findById(id);
	}
	@Override
	public void pay(Order order) {
		 orderDao.pay(order);
	}
	@Override
	public void deleteOrder(Order order) {
		orderDao.deleteOrder(order);
	}
	@Override
	public List<Order> findAll(Integer useId) {
		//根据餐桌的id查询所有未删除的未付款的订单
		List<Order>  orders = orderDao.findAll(useId);
		
		//遍历所有的订单，根据订单id查询其对于订单明细
		if(orders != null && orders.size()>0) {
			for (Order order : orders) {
				List<OrderDetail>  details = orderDao.findByOrderId(order.getId());
				if(details != null && details.size()>0) {
					order.setOrderDetails(details);
				}
				
				DinnerTableDao dinnerTableDao = new DinnerTableDaoImpl();
				//通过餐桌的id查询餐桌
				DinnerTable dinnerTable = dinnerTableDao.findById(order.getTableId());
				
				order.setDinnerTable(dinnerTable);
			}
		}
		
		return orders;
	}

}
