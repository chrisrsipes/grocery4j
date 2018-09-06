package com.crs.grocery4j.repository;

import com.crs.grocery4j.domain.database.GroceryList;
import com.crs.grocery4j.domain.database.GroceryListItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the User entity.
 */
@Repository
public interface GroceryListRepository extends JpaRepository<GroceryList, Long>, JpaSpecificationExecutor<GroceryList> {

}
