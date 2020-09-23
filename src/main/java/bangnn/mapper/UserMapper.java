/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bangnn.mapper;

import bangnn.blos.UsersBLO;
import bangnn.dtos.UsersDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 *
 * @author bangmaple
 */
@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "userId", target = "username")
    @Mapping(source = "fullName", target = "fullname")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "status", target = "activated")
    public UsersDTO userToUserDTO(UsersBLO entity);
}
