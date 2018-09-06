package com.crs.grocery4j.domain.dto;

import com.crs.grocery4j.domain.database.GroceryList;
import com.crs.grocery4j.domain.database.GroceryListItem;
import com.crs.grocery4j.domain.database.Item;

/**
 * Created by crs on 7/6/18.
 */

public class GroceryListItemDto extends AbstractBaseDto {


    private Double quantity;

    private boolean completed;

    private ItemDto item;

    private GroceryListDto groceryList;

    public GroceryListItemDto() {
        super();
    }

    public GroceryListItemDto(GroceryListItem groceryListItem) {
        super(groceryListItem);

        if (groceryListItem != null) {
            this.quantity = groceryListItem.getQuantity();
            this.completed = groceryListItem.getCompleted();

            if (groceryListItem.getItem() != null) {
                this.item = new ItemDto(groceryListItem.getItem());
            }

            if (groceryListItem.getGroceryList() != null) {
                this.groceryList = new GroceryListDto(groceryListItem.getGroceryList());
            }
        }
    }

    public GroceryListItem toEntity(GroceryListItem groceryListItem) {
        super.toEntity(groceryListItem);

        if (this.getItem() != null) {
            groceryListItem.setItem(this.getItem().toEntity(new Item()));
        }

        if (this.getGroceryList() != null) {
            groceryListItem.setGroceryList(this.getGroceryList().toEntity(new GroceryList()));
        }

        groceryListItem.setCompleted(this.getCompleted());
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

    public boolean getCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public GroceryListDto getGroceryList() {
        return groceryList;
    }

    public void setGroceryList(GroceryListDto groceryList) {
        this.groceryList = groceryList;
    }
}
