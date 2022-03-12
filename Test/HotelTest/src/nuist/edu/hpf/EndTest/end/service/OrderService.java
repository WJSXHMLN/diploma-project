package nuist.edu.hpf.EndTest.end.service;

import java.util.List;

import nuist.edu.hpf.Test.bean.Order;
import nuist.edu.hpf.Test.bean.User;


public interface OrderService {

	List<Order> find(User user, String orderCode, String orderDate, String dinnerTableId);

	Order findById(int id);

	void update(Order order);

}
