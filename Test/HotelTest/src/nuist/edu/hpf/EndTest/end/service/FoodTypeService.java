package nuist.edu.hpf.EndTest.end.service;

import java.util.List;

import nuist.edu.hpf.Test.bean.FoodType;

public interface FoodTypeService {

	List<FoodType> find(String keyword, String disabled);

	FoodType findByFoodName(String foodTypeName);

	void save(FoodType foodType2);

	FoodType findById(int id);

	void update(FoodType foodType2);


}
