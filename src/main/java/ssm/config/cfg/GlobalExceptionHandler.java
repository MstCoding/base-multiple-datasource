package ssm.config.cfg;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一处理所有的异常
 *
 * @version v1.0 2017年7月11日
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(value = RuntimeException.class)
	@ResponseBody
	public void defaultRuntimeErrorHandler(HttpServletRequest request, HttpServletResponse response, RuntimeException e) throws RuntimeException {
		logger.error("request normal exception ", e);

		// 业务逻辑异常
	}

	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public void defaultErrorHandler(HttpServletRequest request, HttpServletResponse response, Exception e) throws Exception {
		logger.error("request  error ", e);

		// 业务逻辑之外的异常，都需要将异常保存起来
	}

	@ExceptionHandler(value = Throwable.class)
	@ResponseBody
	public void defaultThrowableHandler(HttpServletRequest request, HttpServletResponse response, Exception e) throws Exception {
		logger.error("request  error ", e);
	}

	@ExceptionHandler(value = BindException.class)
	@ResponseBody
	public void defaultBindException(HttpServletRequest request, HttpServletResponse response, BindException e) throws Exception {

	}

	@ExceptionHandler(value = ConstraintViolationException.class)
	@ResponseBody
	public void defaultBindException(HttpServletRequest request, HttpServletResponse response, ConstraintViolationException e) throws Exception {

	}
}