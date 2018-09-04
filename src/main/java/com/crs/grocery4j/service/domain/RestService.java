package com.crs.grocery4j.service.domain;

import com.crs.grocery4j.domain.database.AbstractBaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import java.util.List;

/**
 * Created by crs on 7/6/18.
 */

public interface RestService<Entity extends AbstractBaseEntity> {

    Long count(Specification<Entity> specification);

    Entity findById(Long objectId);

    Page<Entity> findAll(Specification<Entity> specification, Pageable pageRequest);

    List<Entity> findAll(Specification<Entity> specification, Sort sort);

    Entity create(Entity entity);

    Entity update(Entity entity);

    void delete(Entity entity);
}
