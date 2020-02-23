package <%= artifact %>.rest.account;

import <%= artifact %>.core.account.dto.AccountDto;
import <%= artifact %>.core.account.dto.WriteAccountDto;
import org.mapstruct.Mapper;

@Mapper
public interface AccountRequestResponseMapper {

  AccountResponse fromDto(AccountDto dto);

  WriteAccountDto toWriteDto(WriteAccountRequest request);
}
