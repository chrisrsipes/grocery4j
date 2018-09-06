package com.crs.grocery4j.domain.database;


import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * Created by crs on 7/6/18.
 */

@Entity
@Table(name = "grocery_list_item")
public class GroceryListItem extends AbstractBaseEntity {

    private static final long serialVersionUID = 1L;

    @ManyToOne(targetEntity = Item.class, cascade = CascadeType.DETACH)
    private Item item;

    @ManyToOne(targetEntity = GroceryList.class, cascade = CascadeType.DETACH)
    private GroceryList groceryList;

    private Double quantity;

    private boolean completed;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public GroceryListItem() {
        super();
    }

    public GroceryListItem(GroceryListItem groceryListItem) {
        super(groceryListItem);
        this.item = groceryListItem.getItem();
        this.quantity = groceryListItem.getQuantity();
        this.completed = groceryListItem.getCompleted();
        this.groceryList = groceryListItem.getGroceryList();
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public GroceryList getGroceryList() {
        return groceryList;
    }

    public void setGroceryList(GroceryList groceryList) {
        this.groceryList = groceryList;
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
}
