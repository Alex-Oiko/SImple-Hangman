package com.nexmo.task.exceptions;


public class SystemException extends RuntimeException {
    private ExceptionCode exceptionCode;

    public SystemException() {}

    public SystemException(ExceptionCode exceptionCode) {
        super(exceptionCode.message());
        this.exceptionCode = exceptionCode;
    }

    public ExceptionCode getExceptionCode() {
        return exceptionCode;
    }
}
