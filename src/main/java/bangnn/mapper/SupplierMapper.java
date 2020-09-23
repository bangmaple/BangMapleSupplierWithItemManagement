/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bangnn.mapper;

import bangnn.dtos.SuppliersDTO;
import bangnn.blos.SuppliersBLO;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 *
 * @author bangmaple
 */
@Mapper
public interface SupplierMapper {

    SupplierMapper INSTANCE = Mappers.getMapper(SupplierMapper.class);

    @Mapping(source = "collaborating", target = "colloborating")
    public SuppliersDTO supplierToSupplierDTO(SuppliersBLO supplier);

    @Mapping(source = "colloborating", target = "collaborating")
    public SuppliersBLO supplierDTOToSupplier(SuppliersDTO dto);
    
    public List<SuppliersDTO> supplierEntityListToSupplierDTOList(List<SuppliersBLO> list);
}
