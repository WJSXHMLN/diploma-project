package nuist.edu.hpf.EndTest.end.service;

import java.util.List;

import nuist.edu.hpf.EndTest.end.dao.FoodDao;
import nuist.edu.hpf.EndTest.end.dao.FoodDaoImpl;
import nuist.edu.hpf.Test.bean.Food;

public class FoodServiceImpl implements FoodService {

	private FoodDao foodDao = new FoodDaoImpl();
	@Override
	public List<Food> find(String keyword, String foodTypeId) {
		return foodDao.find(keyword,foodTypeId);
	}
	@Override
	public Food findById(int id) {
		return foodDao.findById(id);
	}
	@Override
	public void update(Food food) {
		foodDao.update(food);
	}
	@Override
	public Food findByFoodName(String foodName) {
		return foodDao.findByFoodName(foodName);
	}
	@Override
	public void save(Food food) {
		foodDao.save(food);
	}

}
