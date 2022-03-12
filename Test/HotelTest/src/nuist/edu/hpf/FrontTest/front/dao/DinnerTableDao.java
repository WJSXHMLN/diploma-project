package nuist.edu.hpf.FrontTest.front.dao;

import java.util.List;

import nuist.edu.hpf.Test.bean.DinnerTable;

public interface DinnerTableDao {

	List<DinnerTable> findDinnerTables(String tableStatus, String tableName);

	DinnerTable findById(int id);

	void update(DinnerTable dinnerTable);

}
