/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bangnn.repositories.impl;

import bangnn.blos.UsersBLO;

/**
 *
 * @author bangmaple
 */
public class UsersCRUDRepositoryImpl extends GenericCRUDRepositoryImpl<UsersBLO, String> {

    private static final String NOT_SUPPORTED = "Not supported yet.";

    @Override
    public boolean add(UsersBLO k) throws IllegalArgumentException {
        throw new IllegalArgumentException(NOT_SUPPORTED);
    }

    @Override
    public UsersBLO get(String v) throws IllegalArgumentException {
        return super.findEntityFromDB(v);
    }

    @Override
    public boolean update(UsersBLO k) throws IllegalArgumentException {
        throw new IllegalArgumentException(NOT_SUPPORTED);
    }

    @Override
    public boolean delete(UsersBLO k) throws IllegalArgumentException {
        throw new IllegalArgumentException(NOT_SUPPORTED);
    }

}
