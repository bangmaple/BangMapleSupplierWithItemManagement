/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bangnn.sort;

/**
 *
 * @author bangmaple
 * @param <T>
 * @param <K>
 */
public interface ISortable<T, K> {

    void sortAscending(T t, K k);

    void sortDescending(T t, K k);
}
