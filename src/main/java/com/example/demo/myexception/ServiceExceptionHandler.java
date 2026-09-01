package com.example.demo.myexception;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.demo.util.ResponseMessage;

//import io.jsonwebtoken.JwtException;
//https://www.baeldung.com/global-error-handler-in-a-spring-rest-api
//https://www.baeldung.com/spring-mvc-custom-validator
@ControllerAdvice
public class ServiceExceptionHandler {
	private static final Logger logger = LoggerFactory.getLogger(ServiceExceptionHandler.class);

	@ExceptionHandler(MyException.class)
	// @ResponseStatus(value = HttpStatus.NOT_FOUND)
	public @ResponseBody ResponseMessage handleResourceNotFound(final MyException exception,
			final HttpServletRequest request) {

		ResponseMessage error = new ResponseMessage();
		error.setMessage(exception.getMessage());
		// error.callerURL(request.getRequestURI());
		error.setObject(null);
		error.setStatus(HttpStatus.EXPECTATION_FAILED);
		return error;
	}

	@ExceptionHandler(UsernameNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public @ResponseBody ResponseMessage handleResourceNotFound(final UsernameNotFoundException exception,
			final HttpServletRequest request) {
		ResponseMessage error = new ResponseMessage();
		error.setMessage(exception.getMessage());
		// error.callerURL(request.getRequestURI());
		error.setObject(null);
		error.setStatus(HttpStatus.NOT_FOUND);
		return error;
	}

//	@ExceptionHandler(UsernameNotFoundException.class)
//	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
//	public @ResponseBody ResponseMessage excepNullPoiner(final NullPointerHandle exception,
//			final HttpServletRequest request) {
//		ResponseMessage error = new ResponseMessage();
//		error.setMessage(ConstantMessage.INTERNAL_SERVER);
//		// error.callerURL(request.getRequestURI());
//		error.setObject(null);
//		error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
//		return error;
//	}

//	@ServiceExceptionHandler(Exception.class)
//	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
//	public @ResponseBody ResponseMessage handleException(final Exception exception, final HttpServletRequest request) {
//
//		ResponseMessage error = new ResponseMessage();
//		error.setMessage(ConstantVar.ERROR_MESSAGE + "   " + exception.getMessage());
//		// error.callerURL(request.getRequestURI());
//		error.setObject(null);
//		error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
//		return error;
//	}
//
//	@ServiceExceptionHandler(value = { JwtException.class })
//	public ResponseEntity<?> handleTokenException(JwtException e) {
//		return new ResponseEntity<Object>(HttpStatus.UNAUTHORIZED);
//	}
//
//	@ExceptionHandler(value = { InvalidAuthTokenException.class })
//	public ResponseEntity<?> handleTokenException(InvalidAuthTokenException e) {
//		return new ResponseEntity<Object>(HttpStatus.UNAUTHORIZED);
//	}
}
