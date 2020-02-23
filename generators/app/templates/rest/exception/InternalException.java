package <%= artifact %>.rest.exception;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

public class InternalException extends RestException {

  public InternalException() {
    super(INTERNAL_SERVER_ERROR, "INTERNAL");
  }
}
