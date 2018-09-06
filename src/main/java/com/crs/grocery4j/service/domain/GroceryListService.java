package com.crs.grocery4j.service.domain;

import com.crs.grocery4j.config.Constants;
import com.crs.grocery4j.domain.database.GroceryList;
import com.crs.grocery4j.domain.database.GroceryListItem;
import com.crs.grocery4j.exception.CanNotBeCreatedException;
import com.crs.grocery4j.exception.DefaultEntityNotFoundException;
import com.crs.grocery4j.repository.GroceryListRepository;
import com.sun.tools.internal.jxc.ap.Const;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by crs on 9/3/18.
 */
@Service
public class GroceryListService implements RestService<GroceryList> {

    @Inject
    private GroceryListRepository groceryListRepository;

    @Override
    public Long count(Specification<GroceryList> specification) {
        return this.groceryListRepository.count();
    }

    @Override
    public GroceryList findById(Long objectId) {
        return this.groceryListRepository.findOne(objectId);
    }

    @Override
    public Page<GroceryList> findAll(Specification<GroceryList> specification, Pageable pageRequest) {
        return this.groceryListRepository.findAll(specification, pageRequest);
    }

    @Override
    public List<GroceryList> findAll(Specification<GroceryList> specification, Sort sort) {
        return this.groceryListRepository.findAll(specification, sort);
    }

    @Override
    public GroceryList create(GroceryList entity) {

        if (entity.getName() == null) {
            entity.setName(Constants.DEFAULT_NEW_GROCERY_LIST_NAME);
        }

        if (entity.getName().equals(Constants.DEFAULT_GROCERY_LIST_NAME)) {
            throw new CanNotBeCreatedException(String.format("Grocery List Name '%s' is reserved", Constants.DEFAULT_GROCERY_LIST_NAME));
        }

        return this.groceryListRepository.save(entity);
    }

    @Override
    public GroceryList update(GroceryList entity) {

        if (entity.getId() == null) {
            return null;
        }

        return this.groceryListRepository.save(entity);
    }

    @Override
    public void delete(GroceryList entity) {

        if (entity.getId() == null) {
            return;
        }

        this.groceryListRepository.delete(entity.getId());
    }

    public GroceryList getDefaultGroceryList(boolean hardCheck) {
        List<GroceryList> defaultGroceryList = this.groceryListRepository.findAllByName(Constants.DEFAULT_GROCERY_LIST_NAME);
        GroceryList result = null;

        if (defaultGroceryList.size() > 0) {
            System.out.println("Warning: multiple grocery lists with the reserved name found, ambiguously selecting a default grocery list");
            result = defaultGroceryList.get(0);
        } else if (defaultGroceryList.size() == 0) {
            System.out.println("Warning: default grocery list not found");

            if (hardCheck) {
                throw new DefaultEntityNotFoundException(String.format("Default Grocery List with name %s could not be found.", Constants.DEFAULT_GROCERY_LIST_NAME));
            }
        } else {
            result = defaultGroceryList.get(0);
        }

        return result;
    }

    public GroceryList createDefaultGroceryListIfNotExists() {
        GroceryList groceryList = this.getDefaultGroceryList(false);
        GroceryList result;

        if (groceryList == null) {
            System.out.println(String.format("Creating default grocery list with name %s", Constants.DEFAULT_GROCERY_LIST_NAME));

            GroceryList defaultGroceryList = new GroceryList();
            defaultGroceryList.setName(Constants.DEFAULT_GROCERY_LIST_NAME);
            result = this.groceryListRepository.save(defaultGroceryList);
        } else {
            System.out.println("Skipping creating default grocery list, already exists");
            result = groceryList;
        }

        return result;
    }

}
