package <%= artifact %>.rest.exception;

import static org.springframework.core.Ordered.LOWEST_PRECEDENCE;

import java.util.UUID;
import javax.annotation.Priority;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Priority(LOWEST_PRECEDENCE)
public class RuntimeExceptionHandler extends ResponseEntityExceptionHandler {

  private final Logger log = LoggerFactory.getLogger(getClass().getSimpleName());

  @ResponseBody
  @ExceptionHandler(RuntimeException.class)
  private ExceptionResponse handleRuntimeException(RuntimeException e, HttpServletResponse response) {
    UUID uuid = UUID.randomUUID();
    InternalException internal = new InternalException();
    log.error("{} - {} - {}", uuid, internal.getStatus(), e);
    response.setStatus(internal.getStatus().value());
    return new ExceptionResponse(uuid, internal.getMessage());
  }
}
