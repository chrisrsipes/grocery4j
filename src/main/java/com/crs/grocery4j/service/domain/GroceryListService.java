package com.crs.grocery4j.service.domain;

import com.crs.grocery4j.domain.database.GroceryList;
import com.crs.grocery4j.repository.GroceryListRepository;
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
}
