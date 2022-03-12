package nuist.edu.hpf.FrontTest.front.service;

import nuist.edu.hpf.Test.bean.User;

public interface UserService {

	User findByLoginNameAndPass(String loginname, String password);

	User findByLoginName(String loginName);

	void save(User user);

}
