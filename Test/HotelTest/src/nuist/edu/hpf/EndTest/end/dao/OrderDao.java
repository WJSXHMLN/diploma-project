package nuist.edu.hpf.EndTest.end.dao;

import java.util.List;

import nuist.edu.hpf.Test.bean.Order;
import nuist.edu.hpf.Test.bean.OrderDetail;
import nuist.edu.hpf.Test.bean.User;


public interface OrderDao {

	List<Order> find(User user, String orderCode, String orderDate, String dinnerTableId);

	Order findById(int id);

	void update(Order order);

	List<OrderDetail> findByOrderId(Integer id);

}
