package com.github.edouardswiac.zerotier.exceptions;


public class ZTClientException extends RuntimeException {
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
