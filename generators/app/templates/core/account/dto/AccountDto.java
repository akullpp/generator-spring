package <%= artifact %>.core.account.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AccountDto {

  @NotNull
  private Long id;

  @NotBlank
  private String name;
}
