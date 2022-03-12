package nuist.edu.hpf.EndTest.end.service;

import nuist.edu.hpf.Test.bean.OrderDetail;

public interface OrderDetailService {

	OrderDetail findById(int id);

	void update(OrderDetail orderDetail, Integer flag);

	void addFoods(String[] arrfoodIds, Integer orderId);


}
