package <%= artifact %>.rest.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
class RestException extends RuntimeException {

  private HttpStatus status;
  private String message;
}
