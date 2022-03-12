package nuist.edu.hpf.FrontTest.front.service;

import java.util.List;

import nuist.edu.hpf.Test.bean.DinnerTable;
/*接口实现类*/
public interface DinnerTableService {


	List<DinnerTable> findDinnerTables(String parseInt, String tableName);

	DinnerTable findById(int id);

	void update(DinnerTable dinnerTable);

}
