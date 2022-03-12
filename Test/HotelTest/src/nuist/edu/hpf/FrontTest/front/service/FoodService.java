package nuist.edu.hpf.FrontTest.front.service;

import java.util.List;

import nuist.edu.hpf.Test.bean.Food;

public interface FoodService {

	List<Food> findByFoodTypeId(Integer foodTypeId);

	Food findById(Integer foodId);

}
