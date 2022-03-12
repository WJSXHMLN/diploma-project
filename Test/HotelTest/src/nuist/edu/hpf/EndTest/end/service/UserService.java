package nuist.edu.hpf.EndTest.end.service;

import java.util.List;

import nuist.edu.hpf.Test.bean.User;

public interface UserService {

	User findByLoginNameAndPass(String loginname, String password);

	void save(User user);

	List<User> find(String searchType, String keyword, String disabled);

	User findById(int id);

	void update(User user);

	User findByLoginName(String loginName);

}
