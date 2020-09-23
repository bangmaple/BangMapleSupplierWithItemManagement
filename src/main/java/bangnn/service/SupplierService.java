/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bangnn.service;

import bangnn.dtos.SuppliersDTO;
import java.util.List;

/**
 *
 * @author bangmaple
 */
public interface SupplierService {

    void createSupplier(SuppliersDTO supplier);

    SuppliersDTO getSupplier(String supCode);

    void updateSupplier(SuppliersDTO supplier, String supCode);

    void deleteSupplier(String supCode);

    List<SuppliersDTO> getSuppliers();
}
