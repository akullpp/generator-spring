package <%= artifact %>.core.account;

import <%= artifact %>.core.account.dto.AccountDto;
import <%= artifact %>.core.account.dto.WriteAccountDto;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountPort {

  private final AccountService accountService;
  private final AccountMapper accountMapper;

  public Optional<AccountDto> getAccount(long id) {
    return accountService.getAccount(id).map(accountMapper::toDto);
  }

  public AccountDto createAccount(@Valid @NotNull WriteAccountDto dto) {
    return accountMapper.toDto(accountService.createAccount(accountMapper.fromCreateDto(dto)));
  }
}
