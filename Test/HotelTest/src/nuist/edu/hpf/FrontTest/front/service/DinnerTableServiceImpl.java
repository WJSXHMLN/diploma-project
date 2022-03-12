package nuist.edu.hpf.FrontTest.front.service;

import java.util.List;

import nuist.edu.hpf.FrontTest.front.dao.DinnerTableDao;
import nuist.edu.hpf.FrontTest.front.dao.DinnerTableDaoImpl;
import nuist.edu.hpf.Test.bean.DinnerTable;

public class DinnerTableServiceImpl implements DinnerTableService {

	private DinnerTableDao dinnerTableDao = new DinnerTableDaoImpl();
	@Override
	public List<DinnerTable> findDinnerTables(String tableStatus, String tableName) {
		return dinnerTableDao.findDinnerTables(tableStatus,tableName);
	}
	@Override
	public DinnerTable findById(int id) {
		return dinnerTableDao.findById(id);
	}
	@Override
	public void update(DinnerTable dinnerTable) {
		dinnerTableDao.update(dinnerTable);
	}	
}
