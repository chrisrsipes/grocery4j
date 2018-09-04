package com.crs.grocery4j.domain.dto;

import com.crs.grocery4j.domain.database.GroceryListItem;
import com.crs.grocery4j.domain.database.Item;

/**
 * Created by crs on 7/6/18.
 */

public class GroceryListItemDto extends AbstractBaseDto {

    private Item item;

    private Double quantity;

    public GroceryListItemDto() {
        super();
    }

    public GroceryListItemDto(GroceryListItem groceryListItem) {
        super(groceryListItem);
        this.item = groceryListItem.getItem();
        this.quantity = groceryListItem.getQuantity();
    }

    public GroceryListItem toEntity(GroceryListItem groceryListItem) {
        super.toEntity(groceryListItem);
        groceryListItem.setItem(this.getItem());
        groceryListItem.setQuantity(this.getQuantity());

        return groceryListItem;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }
}
