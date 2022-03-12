package nuist.edu.hpf.EndTest.end.service;

import java.util.List;

import nuist.edu.hpf.Test.bean.Food;


public interface FoodService {

	List<Food> find(String keyword, String foodTypeId);

	Food findById(int id);

	void update(Food food);

	Food findByFoodName(String foodName);

	void save(Food food);

}
