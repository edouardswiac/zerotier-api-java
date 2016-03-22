package com.github.edouardswiac.zerotier.exceptions;


public final class ZTServiceException extends RuntimeException {
  private static final long serialVersionUID = 1L;

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
