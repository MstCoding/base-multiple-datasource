package ssm.config.read.servcie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ssm.config.read.dao.ReadDao;

@Service
public class ReadService {
	
	@Autowired
	private ReadDao read;
	
	public Object getUserById(int id) {
		return read.getUserById(id);
	}

}
