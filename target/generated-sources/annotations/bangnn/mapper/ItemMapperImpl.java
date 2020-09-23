package bangnn.mapper;

import bangnn.blos.ItemsBLO;
import bangnn.dtos.ItemsDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-09-23T10:40:30+0700",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_251 (Oracle Corporation)"
)
public class ItemMapperImpl implements ItemMapper {

    @Override
    public ItemsDTO itemToItemDTO(ItemsBLO item) {
        if ( item == null ) {
            return null;
        }

        ItemsDTO itemsDTO = new ItemsDTO();

        itemsDTO.setItemCode( item.getItemCode() );
        itemsDTO.setItemName( item.getItemName() );
        itemsDTO.setUnit( item.getUnit() );
        if ( item.getPrice() != null ) {
            itemsDTO.setPrice( item.getPrice().floatValue() );
        }
        if ( item.getSupplying() != null ) {
            itemsDTO.setSupplying( item.getSupplying() );
        }

        return itemsDTO;
    }

    @Override
    public ItemsBLO itemDTOToItemEntity(ItemsDTO item) {
        if ( item == null ) {
            return null;
        }

        ItemsBLO itemsBLO = new ItemsBLO();

        itemsBLO.setItemCode( item.getItemCode() );
        itemsBLO.setItemName( item.getItemName() );
        itemsBLO.setUnit( item.getUnit() );
        if ( item.getPrice() != null ) {
            itemsBLO.setPrice( item.getPrice().doubleValue() );
        }
        itemsBLO.setSupplying( item.isSupplying() );

        return itemsBLO;
    }

    @Override
    public List<ItemsDTO> itemEntityListToItemDTOList(List<ItemsBLO> list) {
        if ( list == null ) {
            return null;
        }

        List<ItemsDTO> list1 = new ArrayList<ItemsDTO>( list.size() );
        for ( ItemsBLO itemsBLO : list ) {
            list1.add( itemToItemDTO( itemsBLO ) );
        }

        return list1;
    }
}
