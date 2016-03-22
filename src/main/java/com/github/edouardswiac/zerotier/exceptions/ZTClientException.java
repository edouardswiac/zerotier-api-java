package com.github.edouardswiac.zerotier.exceptions;


public final class ZTClientException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public ZTClientException() {
    super();
  }

  public ZTClientException(String message) {
    super(message);
  }

  public ZTClientException(String message, Throwable cause) {
    super(message, cause);
  }

  public ZTClientException(Throwable cause) {
    super(cause);
  }
}
