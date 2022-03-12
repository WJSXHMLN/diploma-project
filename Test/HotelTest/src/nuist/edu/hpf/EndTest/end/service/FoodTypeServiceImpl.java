package nuist.edu.hpf.EndTest.end.service;

import java.util.List;

import nuist.edu.hpf.EndTest.end.dao.FoodTypeDao;
import nuist.edu.hpf.EndTest.end.dao.FoodTypeDaoImpl;
import nuist.edu.hpf.Test.bean.FoodType;

public class FoodTypeServiceImpl implements FoodTypeService {
	private FoodTypeDao foodTypeDao = new FoodTypeDaoImpl();

	@Override
	public List<FoodType> find(String keyword, String disabled) {
		// TODO Auto-generated method stub
		return foodTypeDao.find(keyword,disabled);
	}

	@Override
	public FoodType findByFoodName(String foodTypeName) {
		return foodTypeDao.findByFoodName(foodTypeName);
	}

	@Override
	public void save(FoodType foodType2) {
		foodTypeDao.save(foodType2);
	}

	@Override
	public FoodType findById(int id) {
		return foodTypeDao.findById(id);
	}

	@Override
	public void update(FoodType foodType2) {
		foodTypeDao.update(foodType2);
	}

}
