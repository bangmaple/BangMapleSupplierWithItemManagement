/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bangnn.repositories;

import java.io.Serializable;

/**
 *
 * @author bangmaple
 * @param <K>
 * @param <V>
 */
public interface ICRUDRepository<K, V> extends Serializable {

    boolean add(K k) throws IllegalArgumentException;

    K get(V v) throws IllegalArgumentException;

    boolean update(K k) throws IllegalArgumentException;

    boolean delete(K k) throws IllegalArgumentException;

}
