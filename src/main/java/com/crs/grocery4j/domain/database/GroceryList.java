package com.crs.grocery4j.domain.database;

import org.hibernate.annotations.Type;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

/**
 * Created by crs on 9/5/18.
 */
public class GroceryList extends AbstractBaseEntity {

    private boolean completed;

    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @Column(name = "due_date")
    private Date dueDate;

    @OneToMany(targetEntity = GroceryListItem.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<GroceryListItem> groceryListItems;

    public GroceryList() {
        super();
    }

    public GroceryList(GroceryList groceryList) {
        super(groceryList);
        this.completed = groceryList.getCompleted();
        this.dueDate = groceryList.getDueDate();
        this.groceryListItems = groceryList.getGroceryListItems();
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

    public List<GroceryListItem> getGroceryListItems() {
        return groceryListItems;
    }

    public void setGroceryListItems(List<GroceryListItem> groceryListItems) {
        this.groceryListItems = groceryListItems;
    }
}
