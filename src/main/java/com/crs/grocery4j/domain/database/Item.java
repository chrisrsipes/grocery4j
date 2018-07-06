package com.crs.grocery4j.domain.database;


import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * Created by crs on 7/6/18.
 */

@Entity
@Table(name = "jhi_user")
public class Item extends AbstractBaseEntity {

    private static final long serialVersionUID = 1L;

    @Size(max = 50)
    @Column(name = "name", length = 50)
    private String name;

    @Size(max = 100)
    @Column(name = "description", length = 100)
    private String description;

    @Size(max = 20)
    @Column(name = "category", length = 20)
    private String category;

    public static long getSerialVersionUID() {
        return serialVersionUID;
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
