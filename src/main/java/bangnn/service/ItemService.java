/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bangnn.service;


import bangnn.dtos.ItemsDTO;
import java.util.List;


/**
 *
 * @author bangmaple
 */
public interface ItemService {
   
    
    void createItem(ItemsDTO item);
    
    ItemsDTO getItem(String itemCode);
    
    void updateItem(ItemsDTO item, String itemCode);
    
    void deleteItem(String itemCode);
    
    List<ItemsDTO> getItems();
}
