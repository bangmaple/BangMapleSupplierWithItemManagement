/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bangnn.mapper.impl;

import bangnn.blos.SuppliersBLO;
import bangnn.dtos.SuppliersDTO;
import bangnn.mapper.SupplierMapper;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author bangmaple
 */
public class SupplierMapperImpl implements SupplierMapper {

    private static final String NOT_SUPPORTED = "Not supported yet.";
    @Override
    public SuppliersDTO supplierToSupplierDTO(SuppliersBLO supplier) throws IllegalArgumentException {
        throw new IllegalArgumentException(NOT_SUPPORTED);
    }

    @Override
    public SuppliersBLO supplierDTOToSupplier(SuppliersDTO dto) throws IllegalArgumentException {
        throw new IllegalArgumentException(NOT_SUPPORTED);
    }

    @Override
    public List<SuppliersDTO> supplierEntityListToSupplierDTOList(List<SuppliersBLO> list) {
        return list.stream().map((entity) -> {
            SuppliersDTO supplier = new SuppliersDTO();
            supplier.setSupCode(entity.getSupCode());
            supplier.setSupName(entity.getSupName());
            supplier.setAddress(entity.getAddress());
            supplier.setColloborating(entity.getCollaborating());
            return supplier;
        }).collect(Collectors.toList());
    }
    
}
