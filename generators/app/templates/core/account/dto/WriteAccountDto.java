package <%= artifact %>.core.account.dto;

import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class WriteAccountDto {

  @NotBlank
  private String name;
}
