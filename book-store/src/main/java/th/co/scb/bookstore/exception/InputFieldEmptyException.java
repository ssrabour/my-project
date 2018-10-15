package th.co.scb.bookstore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InputFieldEmptyException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	private String fieldName;
	private String fieldDesc;

	public InputFieldEmptyException(String fieldName, String fieldDesc) {
		super(String.format("Value %s is empty, %s", fieldName, fieldDesc));
		this.fieldName = fieldName;
		this.fieldDesc = fieldDesc;
	}
	
	public String getFieldName() {
		return fieldName;
	}

	public String getFieldDesc() {
		return fieldDesc;
	}
}
