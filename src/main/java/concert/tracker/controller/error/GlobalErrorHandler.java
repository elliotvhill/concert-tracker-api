package concert.tracker.controller.error;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalErrorHandler {
	/**
	 * Enum to specify whether the exception handler should return a full stack
	 * trace or an error message only.
	 */
	private enum LogStatus {
		STACK_TRACE, MESSAGE_ONLY
	}

	/**
	 * Define a general ExceptionMessage object to use in the error handler.
	 */
	@Data
	private class ExceptionMessage {
		private String message;
		private String statusReason;
		private String timestamp;
		private String uri;
		private int statusCode;
	}

	/**
	 * Handle PUT requests sent to an invalid ID.
	 * 
	 * @param exception  The NoSuchElementException object.
	 * @param webRequest Used to access the URI of the request that created the
	 *                   exception.
	 * @return An exception message as a JSON response.
	 */

	@ExceptionHandler(NoSuchElementException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public ExceptionMessage handleNoSuchElementException(NoSuchElementException exception, WebRequest webRequest) {
		return buildExceptionMessage(exception, HttpStatus.NOT_FOUND, webRequest, LogStatus.MESSAGE_ONLY);
	}

	/**
	 * Handle unsupported operations.
	 * 
	 * @param exception  The specific Exception object.
	 * @param webRequest Used to access the URI of the request that created the
	 *                   exception.
	 * @return An exception message as a JSON response.
	 */
	@ExceptionHandler(UnsupportedOperationException.class)
	@ResponseStatus(code = HttpStatus.METHOD_NOT_ALLOWED)
	public ExceptionMessage handleUnsupportedOperationException(UnsupportedOperationException exception,
			WebRequest webRequest) {
		return buildExceptionMessage(exception, HttpStatus.METHOD_NOT_ALLOWED, webRequest, LogStatus.MESSAGE_ONLY);
	}

	/**
	 * Handle a Bad Request.
	 * 
	 * @param exception  The an IllegalStateException.
	 * @param webRequest Used to access the URI of the request that created the
	 *                   exception.
	 * @return An exception message as a JSON response.
	 */
	@ExceptionHandler(IllegalStateException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ExceptionMessage handleBadRequest(IllegalStateException exception, WebRequest webRequest) {
		return buildExceptionMessage(exception, HttpStatus.BAD_REQUEST, webRequest, LogStatus.MESSAGE_ONLY);
	}

	/**
	 * Handle all other exception types.
	 * 
	 * @param exception  The exception thrown.
	 * @param webRequest Used to access the URI of the request that created the
	 *                   exception.
	 * @return An exception error message including a full stack trace.
	 */
	@ExceptionHandler(Exception.class)
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	public ExceptionMessage handleException(Exception exception, WebRequest webRequest) {
		return buildExceptionMessage(exception, HttpStatus.INTERNAL_SERVER_ERROR, webRequest, LogStatus.STACK_TRACE);
	}

	/**
	 * Builds an exception message object to be returned based on the specific error
	 * encountered.
	 * 
	 * @param exception  The exception that was thrown.
	 * @param status     The HTTP status code.
	 * @param webRequest The WebRequest to allow access to the request URI where the
	 *                   exception originated.
	 * @return an ExceptionMessage object with details about the exception.
	 */
	private ExceptionMessage buildExceptionMessage(Exception exception, HttpStatus status, WebRequest webRequest,
			LogStatus logStatus) {
		String message = exception.toString();
		String statusReason = status.getReasonPhrase();
		int statusCode = status.value();
		String uri = null;
		String timestamp = ZonedDateTime.now().format(DateTimeFormatter.RFC_1123_DATE_TIME);

		// Get the URI of the request
		if (webRequest instanceof ServletWebRequest servletWebRequest) {
			uri = servletWebRequest.getRequest().getRequestURI();
		}

		// Log the request error
		if (logStatus == LogStatus.MESSAGE_ONLY) {
			log.error("Exception: {}", exception.toString());
		} else {
			// Log the entire stack trace
			log.error("Exception: ", exception);
		}

		// Instantiate a new ExceptionMessage object
		ExceptionMessage exceptionMessage = new ExceptionMessage();

		// Set the ExceptionMessage's fields
		exceptionMessage.setMessage(message);
		exceptionMessage.setStatusReason(statusReason);
		exceptionMessage.setStatusCode(statusCode);
		exceptionMessage.setUri(uri);
		exceptionMessage.setTimestamp(timestamp);

		return exceptionMessage;
	}
}
