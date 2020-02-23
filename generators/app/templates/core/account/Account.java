package <%= artifact %>.core.account;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
class Account {

  @Id
  @GeneratedValue
  private Long id;

  @NotBlank
  private String name;
}
