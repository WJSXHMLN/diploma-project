package nuist.edu.hpf.EndTest.end.service;

import java.util.List;

import nuist.edu.hpf.EndTest.end.dao.OrderDao;
import nuist.edu.hpf.EndTest.end.dao.OrderDaoImpl;
import nuist.edu.hpf.Test.bean.Order;
import nuist.edu.hpf.Test.bean.OrderDetail;
import nuist.edu.hpf.Test.bean.User;

public class OrderServiceImpl implements OrderService {

	private OrderDao orderDao = new OrderDaoImpl();
	@Override
	public List<Order> find(User user, String orderCode, String orderDate, String dinnerTableId) {
		List<Order>  orders = orderDao.find(user,orderCode,orderDate,dinnerTableId);
		
		//遍历所有的订单，根据订单id查询其对于订单明细
		if(orders != null && orders.size()>0) {
			for (Order order : orders) {
				List<OrderDetail>  details = orderDao.findByOrderId(order.getId());
				if(details != null && details.size()>0) {
					order.setOrderDetails(details);
				}
			}
		}
				
		return orders;
	}
	@Override
	public Order findById(int id) {
		Order  order =  orderDao.findById(id);
		
		List<OrderDetail>  details = orderDao.findByOrderId(order.getId());
		if(details != null && details.size()>0) {
			order.setOrderDetails(details);
		}
		return order;
	}
	@Override
	public void update(Order order) {
		orderDao.update(order);
	}

}
