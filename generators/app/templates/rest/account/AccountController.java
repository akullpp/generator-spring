package <%= artifact %>.rest.account;

import <%= artifact %>.core.account.AccountPort;
import <%= artifact %>.rest.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("account")
public class AccountController {

  private final AccountPort port;
  private final AccountRequestResponseMapper mapper;

  @GetMapping("{accountId}")
  public ResponseEntity<AccountResponse> getAccount(@PathVariable Long accountId) {
    return port.getAccount(accountId)
      .map(mapper::fromDto)
      .map(ResponseEntity::ok)
      .orElseThrow(NotFoundException::new);
  }

  @PostMapping
  public ResponseEntity<AccountResponse> createAccount(@RequestBody WriteAccountRequest request) {
    return ResponseEntity.ok(mapper.fromDto(port.createAccount(mapper.toWriteDto(request))));
  }
}
