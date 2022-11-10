package com.example.takehome.Exception;

public class ServiceException extends RuntimeException {

    public ServiceException() {
        super();
    }

    public ServiceException(String msg) {
        super(msg);
    }

    public ServiceException(String msg, Exception e) {
        super(msg);
        this.setStackTrace(e.getStackTrace());
    }

}
