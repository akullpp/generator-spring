package <%= artifact %>.core.account;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface AccountRepository extends CrudRepository<Account, Long> {
}
