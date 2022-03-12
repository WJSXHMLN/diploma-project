package nuist.edu.hpf.FrontTest.front.dao;

import java.util.List;

import nuist.edu.hpf.Test.bean.Food;

public interface FoodDao {

	List<Food> findByFoodTypeId(Integer foodTypeId);

	Food findById(Integer foodId);

}
