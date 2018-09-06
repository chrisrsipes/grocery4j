package com.crs.grocery4j.domain.dto;

import com.crs.grocery4j.domain.database.GroceryListItem;
import com.crs.grocery4j.domain.database.Item;

/**
 * Created by crs on 7/6/18.
 */

public class GroceryListItemDto extends AbstractBaseDto {

    private ItemDto item;

    private Double quantity;

    public GroceryListItemDto() {
        super();
    }

    public GroceryListItemDto(GroceryListItem groceryListItem) {
        super(groceryListItem);

        if (groceryListItem != null) {
            this.item = new ItemDto(groceryListItem.getItem());
            this.quantity = groceryListItem.getQuantity();
        }
    }

    public GroceryListItem toEntity(GroceryListItem groceryListItem) {
        super.toEntity(groceryListItem);

        if (this.getItem() != null) {
            groceryListItem.setItem(this.getItem().toEntity(new Item()));
        }

        groceryListItem.setQuantity(this.getQuantity());

        return groceryListItem;
    }

    public ItemDto getItem() {
        return item;
    }

    public void setItem(ItemDto item) {
        this.item = item;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }
}
