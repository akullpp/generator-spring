package <%= artifact %>.rest.exception;

import static org.springframework.core.Ordered.HIGHEST_PRECEDENCE;

import java.util.UUID;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Order(HIGHEST_PRECEDENCE)
public class RestExceptionHandler {

  private final Logger log = LoggerFactory.getLogger(getClass().getSimpleName());

  @ResponseBody
  @ExceptionHandler(NotFoundException.class)
  private ExceptionResponse handleNotFoundException(NotFoundException e, HttpServletResponse response) {
    response.setStatus(e.getStatus().value());
    return new ExceptionResponse(UUID.randomUUID(), e.getMessage());
  }

  @ResponseBody
  @ExceptionHandler(RestException.class)
  private ExceptionResponse handleRestException(RestException e, HttpServletResponse response) {
    UUID uuid = UUID.randomUUID();
    log.error("{} - {} - {}", uuid, e.getStatus(), e.getMessage());
    response.setStatus(e.getStatus().value());
    return new ExceptionResponse(uuid, e.getMessage());
  }
}
