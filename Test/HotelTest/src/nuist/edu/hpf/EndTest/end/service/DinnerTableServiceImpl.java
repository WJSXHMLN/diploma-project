package nuist.edu.hpf.EndTest.end.service;

import java.util.List;

import nuist.edu.hpf.EndTest.end.dao.DinnerTableDao;
import nuist.edu.hpf.EndTest.end.dao.DinnerTableDaoImpl;
import nuist.edu.hpf.Test.bean.DinnerTable;

public class DinnerTableServiceImpl implements DinnerTableService {

	private DinnerTableDao dinnerTableDao = new DinnerTableDaoImpl();
	@Override
	public List<DinnerTable> find(String keyword, String tableStatus, String disabled) {
		return dinnerTableDao.find(keyword,tableStatus,disabled);
	}
	@Override
	public DinnerTable findById(int id) {
		return dinnerTableDao.findById(id);
	}
	@Override
	public void update(DinnerTable dinnerTable) {
		dinnerTableDao.update(dinnerTable);
	}
	@Override
	public DinnerTable findByTableName(String tableName) {
		return dinnerTableDao.findByTableName(tableName);
	}
	@Override
	public void save(DinnerTable table) {
		dinnerTableDao.save(table);
	}

}
