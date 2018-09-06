package com.crs.grocery4j.service.domain;

import com.crs.grocery4j.domain.database.GroceryListItem;
import com.crs.grocery4j.domain.database.Item;
import com.crs.grocery4j.exception.CanNotBeCreatedException;
import com.crs.grocery4j.repository.GroceryListItemRepository;
import com.crs.grocery4j.repository.ItemRepository;
import org.springframework.cache.annotation.EnableCaching;
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
public class GroceryListItemService implements RestService<GroceryListItem> {

    @Inject
    private GroceryListItemRepository groceryListItemRepository;

    @Inject
    private ItemService itemService;

    @Override
    public Long count(Specification<GroceryListItem> specification) {
        return this.groceryListItemRepository.count();
    }

    @Override
    public GroceryListItem findById(Long objectId) {
        return this.groceryListItemRepository.findOne(objectId);
    }

    @Override
    public Page<GroceryListItem> findAll(Specification<GroceryListItem> specification, Pageable pageRequest) {
        return this.groceryListItemRepository.findAll(specification, pageRequest);
    }

    @Override
    public List<GroceryListItem> findAll(Specification<GroceryListItem> specification, Sort sort) {
        return this.groceryListItemRepository.findAll(specification, sort);
    }

    public List<GroceryListItem> findAllByGroceryListId(Long groceryListId) {
        return this.groceryListItemRepository.findAllByGroceryList_Id(groceryListId);
    }

    @Override
    public GroceryListItem create(GroceryListItem entity) {
        // get actual Item by id

        if (entity == null || entity.getItem() == null || entity.getItem().getId() == null) {
            throw new CanNotBeCreatedException("Entity is invalid or is not related to a valid item");
        }

        Item relatedItem = this.itemService.findById(entity.getItem().getId());

        if (relatedItem == null) {
            throw new CanNotBeCreatedException("Related item could not be found");
        }

        entity.setItem(relatedItem);

        return this.groceryListItemRepository.save(entity);
    }

    @Override
    public GroceryListItem update(GroceryListItem entity) {

        if (entity.getId() == null) {
            return null;
        }

        return this.groceryListItemRepository.save(entity);
    }

    @Override
    public void delete(GroceryListItem entity) {

        if (entity.getId() == null) {
            return;
        }

        this.groceryListItemRepository.delete(entity.getId());
    }
}
