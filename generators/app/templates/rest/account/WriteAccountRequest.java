package <%= artifact %>.rest.account;

import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class WriteAccountRequest {

  @NotBlank
  private String name;
}
