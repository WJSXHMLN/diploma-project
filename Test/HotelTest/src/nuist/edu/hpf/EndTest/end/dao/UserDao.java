package nuist.edu.hpf.EndTest.end.dao;

import java.util.List;

import nuist.edu.hpf.Test.bean.User;

public interface UserDao {

	User findByLoginNameAndPass(String loginname, String password);

	void save(User user);

	List<User> find(String sql);

	User findById(int id);

	void update(User user);

	User findByLoginName(String loginName);

}
