/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bangnn.utils.validator;

/**
 *
 * @author bangmaple
 * @param <T>
 */
public interface IValidator<T> {
    public boolean validate(T t);
}
