package ssm.config.write.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ssm.config.write.dao.WriteDao;

@Service
public class WriteService {

	@Autowired
	private WriteDao write;

	public void addUser(String user) {
		write.addUser(user);
	}

}
