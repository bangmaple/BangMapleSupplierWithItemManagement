/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bangnn.mapper;

import bangnn.dtos.ItemsDTO;
import bangnn.blos.ItemsBLO;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 *
 * @author bangmaple
 */
@Mapper
public interface ItemMapper {
    ItemMapper INSTANCE = Mappers.getMapper(ItemMapper.class);

    ItemsDTO itemToItemDTO(ItemsBLO item);

    ItemsBLO itemDTOToItemEntity(ItemsDTO item);
    
    List<ItemsDTO> itemEntityListToItemDTOList(List<ItemsBLO> list);
}
