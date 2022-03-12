package nuist.edu.hpf.FrontTest.front.dao;

import nuist.edu.hpf.Test.bean.User;

public interface UserDao {

	User findByLoginNameAndPass(String loginname, String password);

	User findByLoginName(String loginName);

	void save(User user);

}
