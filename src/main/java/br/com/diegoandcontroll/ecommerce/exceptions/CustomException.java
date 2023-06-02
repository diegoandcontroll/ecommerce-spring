package br.com.diegoandcontroll.ecommerce.exceptions;

import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException {

  private final String errorMessage;
  private final HttpStatus status;
  private final String path;

  public CustomException(String errorMessage, HttpStatus status, String path) {
    this.errorMessage = errorMessage;
    this.status = status;
    this.path = path;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public HttpStatus getStatus() {
    return status;
  }

  public String getPath() {
    return path;
  }
}