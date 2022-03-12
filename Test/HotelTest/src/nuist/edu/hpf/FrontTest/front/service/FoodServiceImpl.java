package nuist.edu.hpf.FrontTest.front.service;

import java.util.List;

import nuist.edu.hpf.FrontTest.front.dao.FoodDao;
import nuist.edu.hpf.FrontTest.front.dao.FoodDaoImpl;
import nuist.edu.hpf.Test.bean.Food;

public class FoodServiceImpl implements FoodService {

	//现在在food服务层，调用dao
	private FoodDao foodDao = new FoodDaoImpl();
	@Override
	public List<Food> findByFoodTypeId(Integer foodTypeId) {
		return foodDao.findByFoodTypeId(foodTypeId);
	}
	@Override
	public Food findById(Integer foodId) {
		return foodDao.findById(foodId);
	}

}
