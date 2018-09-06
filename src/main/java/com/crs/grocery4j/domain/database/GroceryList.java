package com.crs.grocery4j.domain.database;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by crs on 9/5/18.
 */
@Entity
@Table(name = "grocery_list")
public class GroceryList extends AbstractBaseEntity {

    private String name;

    private boolean completed;

    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @Column(name = "due_date")
    private Date dueDate;

    public GroceryList() {
        super();
    }

    public GroceryList(GroceryList groceryList) {
        super(groceryList);
        this.completed = groceryList.getCompleted();
        this.dueDate = groceryList.getDueDate();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCompleted() {
        return completed;
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
}
