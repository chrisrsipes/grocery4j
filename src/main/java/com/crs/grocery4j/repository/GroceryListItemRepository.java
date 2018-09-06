package com.crs.grocery4j.repository;

import com.crs.grocery4j.domain.database.GroceryListItem;
import com.crs.grocery4j.domain.database.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data JPA repository for the User entity.
 */
@Repository
public interface GroceryListItemRepository extends JpaRepository<GroceryListItem, Long>, JpaSpecificationExecutor<GroceryListItem> {

    List<GroceryListItem> findAllByGroceryList_Id(Long groceryListId);

}
