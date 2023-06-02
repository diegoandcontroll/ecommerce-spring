package br.com.diegoandcontroll.ecommerce.exceptions;

public class CustomErrorResponse {
  private String errorMessage;
  private String path;

  public CustomErrorResponse(String errorMessage, String path) {
      this.errorMessage = errorMessage;
      this.path = path;
  }

  public String getErrorMessage() {
      return errorMessage;
  }

  public String getPath() {
      return path;
  }
}
