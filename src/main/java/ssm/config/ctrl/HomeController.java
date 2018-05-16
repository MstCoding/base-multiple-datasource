package ssm.config.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ssm.config.read.servcie.ReadService;
import ssm.config.write.service.WriteService;

/**
 * @author AaronLee
 *
 * @version v1.0
 */
@Controller
public class HomeController {

	@Autowired
	private ReadService read;
	@Autowired
	private WriteService write;

	@ResponseBody
	@RequestMapping(value = "/add", method = { RequestMethod.GET })
	public Object add(String user) {
		write.addUser(user);
		return user;
	}

	@ResponseBody
	@RequestMapping(value = "/update/{id}", method = { RequestMethod.GET })
	public Object add(@PathVariable String id, String user) {
		write.updateUser(id, user);
		return user;
	}

	@ResponseBody
	@RequestMapping(value = "/get/{id}", method = { RequestMethod.GET })
	public Object get(@PathVariable int id) {
		return read.getUserById(id) + "_read";
	}

}
