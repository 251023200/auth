package  com.sky.auth.exception;

public class RestException extends RuntimeException {

	private static final long serialVersionUID = 7190399602869368672L;

	private int errorCode; // 错误代码

	public RestException(String message) {
		super(message);
	}

	public RestException(String message, int errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

	public RestException(String message, Throwable cause) {
		super(message, cause);
	}

	public RestException(String message, Throwable cause, int errorCode) {
		super(message, cause);
		this.errorCode = errorCode;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

}
