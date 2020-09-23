/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bangnn.service.impl;

import bangnn.dtos.SuppliersDTO;
import bangnn.repositories.impl.SuppliersCRUDRepositoryImpl;
import bangnn.utils.validator.impl.SuppliersValidatorImpl;
import java.util.List;
import java.util.Objects;
import bangnn.mapper.SupplierMapper;
import bangnn.mapper.impl.SupplierMapperImpl;
import bangnn.service.SupplierService;
import java.util.ResourceBundle;

/**
 *
 * @author bangmaple
 */
public class SupplierServiceImpl implements SupplierService {

    private static String EXIST_SUPPLIER;
    private static String DELETE_SUPPLIER_CONFLICT;
    private static String UPDATE_SUPPLIER_NOT_HAVING_COLLOBORATING_CONFLICT;

    public static void loadInternationalizationResources(ResourceBundle resource) {
        EXIST_SUPPLIER = resource.getString("EXIST_SUPPLIER");
        DELETE_SUPPLIER_CONFLICT = resource.getString("DELETE_SUPPLIER_CONFLICT");
        UPDATE_SUPPLIER_NOT_HAVING_COLLOBORATING_CONFLICT = resource
                .getString("UPDATE_SUPPLIER_NOT_HAVING_COLLOBORATING_CONFLICT");
    }

    private final SuppliersCRUDRepositoryImpl supplierRepo = new SuppliersCRUDRepositoryImpl();
    private final SuppliersValidatorImpl validator = new SuppliersValidatorImpl();
    private final SupplierMapperImpl mapper = new SupplierMapperImpl();

    @Override
    public void createSupplier(SuppliersDTO supplier) throws IllegalArgumentException,
            NullPointerException {
        validator.validate(supplier);
        if (Objects.nonNull(supplierRepo.get(supplier.getSupCode()))) {
            throw new IllegalArgumentException(EXIST_SUPPLIER);

        }
        supplierRepo.add(SupplierMapper.INSTANCE.supplierDTOToSupplier(Objects.requireNonNull(supplier)));
    }

    @Override
    public SuppliersDTO getSupplier(String supCode) throws NullPointerException,
            IllegalArgumentException {
        validator.validateSupCode(supCode);
        return SupplierMapper.INSTANCE
                .supplierToSupplierDTO(Objects.requireNonNull(supplierRepo.get(supCode)));
    }

    @Override
    public void updateSupplier(SuppliersDTO supplier, String supCode)
            throws IllegalArgumentException, NullPointerException {
        validator.validate(supplier);
        if (!supplier.isColloborating() && supplierRepo.get(supCode).getItemsPBOList().size() > 0) {
            throw new IllegalArgumentException(UPDATE_SUPPLIER_NOT_HAVING_COLLOBORATING_CONFLICT);
        }
        supplierRepo.update(SupplierMapper.INSTANCE.supplierDTOToSupplier(Objects.requireNonNull(supplier)));
    }

    @Override
    public void deleteSupplier(String supCode) throws NullPointerException,
            IllegalArgumentException {
        validator.validateSupCode(supCode);
        if (supplierRepo.get(supCode).getItemsPBOList().size() > 0) {
            throw new IllegalArgumentException(DELETE_SUPPLIER_CONFLICT);
        }
        supplierRepo.delete(Objects.requireNonNull(supplierRepo.get(supCode)));

    }

    public List<SuppliersDTO> findSuppliersByAnyChar(final String search)
            throws IllegalArgumentException {
        return mapper.supplierEntityListToSupplierDTOList(supplierRepo
                .findSuppliersByLikeAny(search));
    }

    @Override
    public List<SuppliersDTO> getSuppliers() throws NullPointerException {
        return mapper
                .supplierEntityListToSupplierDTOList(Objects.requireNonNull(supplierRepo
                        .getAllSuppliers()));
    }
}
