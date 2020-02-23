package <%= artifact %>.core.account;

import <%= artifact %>.core.account.dto.AccountDto;
import <%= artifact %>.core.account.dto.WriteAccountDto;
import org.mapstruct.Mapper;

@Mapper
interface AccountMapper {

  Account fromCreateDto(WriteAccountDto dto);

  AccountDto toDto(Account account);
}
