package <%= artifact %>.core.account;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class AccountService {

  private final AccountRepository accountRepository;

  Optional<Account> getAccount(Long id) {
    return accountRepository.findById(id);
  }

  Account createAccount(Account account) {
    return accountRepository.save(account);
  }
}
