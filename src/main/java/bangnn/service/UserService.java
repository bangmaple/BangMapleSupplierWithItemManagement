/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bangnn.service;

import bangnn.dtos.UsersDTO;

/**
 *
 * @author bangmaple
 */
public interface UserService {

    void createUser(UsersDTO user);

    UsersDTO getUser(String username);

    void updateUser(UsersDTO user, String username);

    void deleteUser(String username);
}
