/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bangnn.mapper.impl;

import bangnn.blos.ItemsBLO;
import bangnn.dtos.ItemsDTO;
import bangnn.dtos.SuppliersDTO;
import bangnn.mapper.ItemMapper;
import bangnn.mapper.SupplierMapper;
import bangnn.service.impl.SupplierServiceImpl;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author bangmaple
 */
public class ItemMapperImpl implements ItemMapper {

    @Override
    public ItemsDTO itemToItemDTO(ItemsBLO item) throws IllegalArgumentException {
        throw new IllegalArgumentException();
    }

    @Override
    public List<ItemsDTO> itemEntityListToItemDTOList(List<ItemsBLO> list) {
        return list.stream().map((entity -> {
            ItemsDTO dto = new ItemsDTO();
            dto.setItemCode(entity.getItemCode());
            dto.setItemName(entity.getItemName());
            dto.setPrice(entity.getPrice().floatValue());
            SuppliersDTO supplier = SupplierMapper.INSTANCE
                    .supplierToSupplierDTO(entity.getSupCode());
            dto.setSupplier(supplier.getSupCode() + "-" + supplier.getSupName());
            dto.setSupplying(entity.getSupplying());
            dto.setUnit(entity.getUnit());
            return dto;
        })).collect(Collectors.toList());
    }

    @Override
    public ItemsBLO itemDTOToItemEntity(ItemsDTO item) {
        ItemsBLO blo = new ItemsBLO(item.getItemCode());
        blo.setItemName(item.getItemName());
        blo.setPrice(item.getPrice().doubleValue());
        blo.setUnit(item.getUnit());
        blo.setSupplying(item.isSupplying());
        System.out.println(item.getSupplier());
        blo.setSupCode(SupplierMapperImpl.INSTANCE
                .supplierDTOToSupplier(new SupplierServiceImpl()
                        .getSupplier(item.getSupplier())));
        return blo;
    }

}
