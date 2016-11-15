package com.knightNote.other;

public class ServiceException extends BaseException{

	private static final long serialVersionUID = 1L;

	public ServiceException() {
		super();
	}

	public ServiceException(int code, String msg, String log) {
		super(code, msg, log);
	}

	public ServiceException(int code, String msg) {
		super(code, msg);
	}

	
}
