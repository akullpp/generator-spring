package <%= artifact %>.rest.exception;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Getter;

@Getter
public class ExceptionResponse {

  private UUID uuid;
  private LocalDateTime timestamp;
  private String message;

  public ExceptionResponse(UUID uuid, String message) {
    this.uuid = uuid;
    this.timestamp = LocalDateTime.now();
    this.message = message;
  }
}
