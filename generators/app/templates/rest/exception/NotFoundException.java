package <%= artifact %>.rest.exception;

import static org.springframework.http.HttpStatus.NOT_FOUND;

public class NotFoundException extends RestException {

  public NotFoundException() {
    super(NOT_FOUND, "NOT_FOUND");
  }
}
