package bangnn.mapper;

import bangnn.blos.SuppliersBLO;
import bangnn.dtos.SuppliersDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-09-23T10:40:30+0700",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_251 (Oracle Corporation)"
)
public class SupplierMapperImpl implements SupplierMapper {

    @Override
    public SuppliersDTO supplierToSupplierDTO(SuppliersBLO supplier) {
        if ( supplier == null ) {
            return null;
        }

        SuppliersDTO suppliersDTO = new SuppliersDTO();

        if ( supplier.getCollaborating() != null ) {
            suppliersDTO.setColloborating( supplier.getCollaborating() );
        }
        suppliersDTO.setSupCode( supplier.getSupCode() );
        suppliersDTO.setSupName( supplier.getSupName() );
        suppliersDTO.setAddress( supplier.getAddress() );

        return suppliersDTO;
    }

    @Override
    public SuppliersBLO supplierDTOToSupplier(SuppliersDTO dto) {
        if ( dto == null ) {
            return null;
        }

        SuppliersBLO suppliersBLO = new SuppliersBLO();

        suppliersBLO.setCollaborating( dto.isColloborating() );
        suppliersBLO.setSupCode( dto.getSupCode() );
        suppliersBLO.setSupName( dto.getSupName() );
        suppliersBLO.setAddress( dto.getAddress() );

        return suppliersBLO;
    }

    @Override
    public List<SuppliersDTO> supplierEntityListToSupplierDTOList(List<SuppliersBLO> list) {
        if ( list == null ) {
            return null;
        }

        List<SuppliersDTO> list1 = new ArrayList<SuppliersDTO>( list.size() );
        for ( SuppliersBLO suppliersBLO : list ) {
            list1.add( supplierToSupplierDTO( suppliersBLO ) );
        }

        return list1;
    }
}
