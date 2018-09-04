package com.crs.grocery4j.domain.dto;

import com.crs.grocery4j.domain.database.Item;

/**
 * Created by crs on 7/6/18.
 */

public class ItemDto extends AbstractBaseDto {

    private String name;

    private String description;

    private String category;

    public ItemDto(Item item) {
        this.name = item.getName();
        this.description = item.getDescription();
        this.category = item.getCategory();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
