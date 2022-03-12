package nuist.edu.hpf.FrontTest.front.service;

import java.util.List;

import nuist.edu.hpf.FrontTest.front.dao.FoodTypeDao;
import nuist.edu.hpf.FrontTest.front.dao.FoodTypeDaoImpl;
import nuist.edu.hpf.Test.bean.FoodType;

public class FoodTypeServiceImpl implements FoodTypeService {

	//现在在服务层，需要调用dao层
	private FoodTypeDao foodTypeDao = new FoodTypeDaoImpl();
	@Override
	public List<FoodType> findAll() {
		return foodTypeDao.findAll();
	}

}
