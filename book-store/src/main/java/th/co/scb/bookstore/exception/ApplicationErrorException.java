package th.co.scb.bookstore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class ApplicationErrorException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	private String actionName;
	private String causeMessage;
	
	public ApplicationErrorException(String actionName) {
		super(String.format("Found problem in %s service", actionName));
		this.actionName = actionName;
	}
	
	public ApplicationErrorException(String actionName, String causeMessage) {
		super(String.format("Found problem in %s service: %s", actionName, causeMessage));
		this.actionName = actionName;
		this.causeMessage = causeMessage;
	}

	public String getActionName() {
		return actionName;
	}

	public String getCauseMessage() {
		return causeMessage;
	}
}
