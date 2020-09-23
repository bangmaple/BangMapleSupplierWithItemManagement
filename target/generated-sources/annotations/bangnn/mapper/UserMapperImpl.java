package bangnn.mapper;

import bangnn.blos.UsersBLO;
import bangnn.dtos.UsersDTO;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-09-23T10:40:30+0700",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_251 (Oracle Corporation)"
)
public class UserMapperImpl implements UserMapper {

    @Override
    public UsersDTO userToUserDTO(UsersBLO entity) {
        if ( entity == null ) {
            return null;
        }

        UsersDTO usersDTO = new UsersDTO();

        usersDTO.setPassword( entity.getPassword() );
        usersDTO.setFullname( entity.getFullName() );
        usersDTO.setUsername( entity.getUserId() );
        if ( entity.getStatus() != null ) {
            usersDTO.setActivated( entity.getStatus() );
        }

        return usersDTO;
    }
}
