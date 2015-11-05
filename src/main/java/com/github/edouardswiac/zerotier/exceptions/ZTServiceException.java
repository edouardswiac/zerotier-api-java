package com.github.edouardswiac.zerotier.exceptions;


public class ZTServiceException extends RuntimeException {
  public ZTServiceException(Throwable cause) {
    super(cause);
  }

  public ZTServiceException() {
    super();
  }

  public ZTServiceException(String message) {
    super(message);
  }

  public ZTServiceException(String message, Throwable cause) {
    super(message, cause);
  }
}
