package nuist.edu.hpf.EndTest.end.dao;

import java.sql.Connection;

import nuist.edu.hpf.Test.bean.OrderDetail;

public interface OrderDetailDao {

	OrderDetail findById(int id);

	void update(OrderDetail orderDetail, Connection connection);

	void addFoods(String[] arrfoodIds, Integer orderId);


}
