package com.crs.grocery4j.domain.dto;

import com.crs.grocery4j.domain.database.GroceryList;
import com.crs.grocery4j.domain.database.GroceryListItem;
import com.crs.grocery4j.domain.database.Item;
import com.crs.grocery4j.service.util.PlainDateTimeDeserializer;
import com.crs.grocery4j.service.util.PlainDateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by crs on 7/6/18.
 */

public class GroceryListDto extends AbstractBaseDto {

    private boolean completed;

    @JsonSerialize(using = PlainDateTimeSerializer.class)
    @JsonDeserialize(using = PlainDateTimeDeserializer.class)
    private Date dueDate;

    private List<GroceryListItemDto> groceryListItems;


    public GroceryListDto() {
        super();
    }

    public GroceryListDto(GroceryList groceryList) {
        super(groceryList);

        if (groceryList != null) {
            this.completed = groceryList.getCompleted();
            this.dueDate = groceryList.getDueDate();
            this.groceryListItems = groceryList.getGroceryListItems()
                .stream()
                .map(item -> new GroceryListItemDto(item))
                .collect(Collectors.toList());
        }
    }

    public GroceryList toEntity(GroceryList groceryList) {
        super.toEntity(groceryList);

        groceryList.setCompleted(this.getCompleted());
        groceryList.setDueDate(this.getDueDate());

        if (this.getGroceryListItems() != null) {
            List<GroceryListItem> groceryListItems = this.getGroceryListItems()
                .parallelStream()
                .map(item -> item.toEntity(new GroceryListItem()))
                .collect(Collectors.toList());

            groceryList.setGroceryListItems(groceryListItems);
        }

        return groceryList;
    }

    public boolean getCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public List<GroceryListItemDto> getGroceryListItems() {
        return groceryListItems;
    }

    public void setGroceryListItems(List<GroceryListItemDto> groceryListItems) {
        this.groceryListItems = groceryListItems;
    }
}
