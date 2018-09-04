package com.crs.grocery4j.service.domain;

import com.crs.grocery4j.domain.database.Item;
import com.crs.grocery4j.repository.ItemRepository;
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
public class ItemService implements RestService<Item> {

    @Inject
    private ItemRepository itemRepository;

    @Override
    public Long count(Specification<Item> specification) {
        return this.itemRepository.count();
    }

    @Override
    public Item findById(Long objectId) {
        return this.itemRepository.findOne(objectId);
    }

    @Override
    public Page<Item> findAll(Specification<Item> specification, Pageable pageRequest) {
        return this.itemRepository.findAll(specification, pageRequest);
    }

    @Override
    public List<Item> findAll(Specification<Item> specification, Sort sort) {
        return this.itemRepository.findAll(specification, sort);
    }

    @Override
    public Item create(Item entity) {
        return this.itemRepository.save(entity);
    }

    @Override
    public Item update(Item entity) {

        if (entity.getId() == null) {
            return null;
        }

        return this.itemRepository.save(entity);
    }

    @Override
    public void delete(Item entity) {
        if (entity.getId() == null) {
            return;
        }

        this.itemRepository.delete(entity.getId());
    }
}
