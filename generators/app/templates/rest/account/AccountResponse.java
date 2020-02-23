package <%= artifact %>.rest.account;

import lombok.Data;

@Data
public class AccountResponse {

  private long id;

  private String name;
}
