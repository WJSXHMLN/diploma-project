package nuist.edu.hpf.EndTest.end.service;

import java.util.List;

import nuist.edu.hpf.Test.bean.DinnerTable;


public interface DinnerTableService {

	List<DinnerTable> find(String keyword, String tableStatus, String disabled);

	DinnerTable findById(int id);

	void update(DinnerTable dinnerTable);

	DinnerTable findByTableName(String tableName);

	void save(DinnerTable table);

}
