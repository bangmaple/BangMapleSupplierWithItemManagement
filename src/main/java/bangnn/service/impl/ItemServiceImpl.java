/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bangnn.service.impl;

import bangnn.blos.SuppliersBLO;
import bangnn.service.ItemService;
import bangnn.dtos.ItemsDTO;
import bangnn.mapper.impl.ItemMapperImpl;
import bangnn.repositories.impl.ItemsCRUDRepositoryImpl;
import bangnn.repositories.impl.SuppliersCRUDRepositoryImpl;
import bangnn.utils.validator.impl.ItemsValidatorImpl;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 *
 * @author bangmaple
 */
public class ItemServiceImpl implements ItemService {

    private static String EXIST_ITEM;
    private final ItemsValidatorImpl validator = new ItemsValidatorImpl();
    private final ItemsCRUDRepositoryImpl repo = new ItemsCRUDRepositoryImpl();
    private final ItemMapperImpl mapper = new ItemMapperImpl();
    private final SuppliersCRUDRepositoryImpl supplierRepo = new SuppliersCRUDRepositoryImpl();

    
    public static void loadInternationalizationResources(ResourceBundle resource) {
        EXIST_ITEM = resource.getString("EXIST_ITEM");
    }
    @Override
    public List<ItemsDTO> getItems() throws IllegalArgumentException,
            NullPointerException {
        return mapper.itemEntityListToItemDTOList(Objects
                .requireNonNull(repo.getAllItems()));
    }

    @Override
    public void createItem(ItemsDTO item) throws IllegalArgumentException,
            NullPointerException {
        validator.validate(item);
        if (Objects.nonNull(repo.get(item.getItemCode()))) {
            throw new IllegalArgumentException(EXIST_ITEM);
        }
        repo.add(mapper.itemDTOToItemEntity(Objects.requireNonNull(item)));
    }

    @Override
    public ItemsDTO getItem(String itemCode) throws IllegalArgumentException,
            NullPointerException {
        return mapper.itemToItemDTO(Objects.requireNonNull(repo.get(itemCode)));
    }

    @Override
    public void updateItem(ItemsDTO item, String itemCode) throws IllegalArgumentException,
            NullPointerException {
        validator.validate(item);
        repo.update(mapper.itemDTOToItemEntity(Objects.requireNonNull(item)));
    }

    @Override
    public void deleteItem(String itemCode) throws IllegalArgumentException,
            NullPointerException {
        validator.validateItemCode(itemCode);
        repo.delete(Objects.requireNonNull(repo.get(itemCode)));
    }

    public List<ItemsDTO> findItemsByAnyChar(final String search) {
        SuppliersBLO supplier;
        try {
            supplier = supplierRepo.get(search);
        } catch (IllegalArgumentException e) {
            supplier = new SuppliersBLO();
        }
        return mapper.itemEntityListToItemDTOList(Objects
                .requireNonNull(repo.getItemsByAnyChar(search, supplier)));
    }

}
