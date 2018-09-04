package com.crs.grocery4j.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.crs.grocery4j.domain.database.GroceryListItem;
import com.crs.grocery4j.domain.database.Item;
import com.crs.grocery4j.domain.dto.ErrorDto;
import com.crs.grocery4j.domain.dto.GroceryListItemDto;
import com.crs.grocery4j.domain.dto.ItemDto;
import com.crs.grocery4j.exception.CanNotBeCreatedException;
import com.crs.grocery4j.service.domain.GroceryListItemService;
import com.crs.grocery4j.service.domain.ItemService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by crs on 9/3/18.
 */
@RestController
@RequestMapping("/api")
public class GroceryListItemResource extends BaseResource<GroceryListItem, GroceryListItemDto, GroceryListItemService> {

    /**
     * POST /groceryListItems -> Create a new target.
     *
     * @param groceryListItemDto the target object to create
     * @return targetDto the created target object
     * @throws URISyntaxException if an exception occurs creating the response
     *             object
     */
    @ApiOperation(value = "create", notes = "Create a target. Secured.", consumes = "application/json", produces = "application/json")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Ok", response = GroceryListItemDto.class), @ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 500, message = "Internal server error", response = ErrorDto.class) })
    @RequestMapping(value = "/groceryListItems", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Transactional
    public ResponseEntity<?> create(@Valid @RequestBody GroceryListItemDto groceryListItemDto) throws URISyntaxException {
        ResponseEntity response;

        try {
            response = super.abstractCreate(groceryListItemDto);
        } catch(CanNotBeCreatedException e) {
            ErrorDto errorDto = new ErrorDto(e.getMessage());
            response = ResponseEntity.ok().body(errorDto);
        }

        return response;
    }

//    @Conjunction(value = {
//                                         @Or({
//                                             @Spec(path = "name", params = "keyword", spec = Like.class),
//                                             @Spec(path = "description", params = "keyword", spec = Like.class)
//                                         })
//                                     }, and = {
//                                         @Spec(path = "category", params = "category", spec = Equal.class)
//                                     }) Specification<GroceryListItem> specification

    /**
     * GET /groceryListItems -> gets all groceryListItems
     *
     * @return List<TargetDto> the target objects
     * @throws URISyntaxException if an exception occurs creating the response object
     */
    @ApiOperation(value = "list", notes = "List groceryListItems. Secured.", consumes = "application/json", produces = "application/json")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Ok", response = List.class), @ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 500, message = "Internal server error", response = ErrorDto.class) })
    @RequestMapping(value = "/groceryListItems", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Transactional
    public ResponseEntity<?> findAll(@RequestParam(value = "start", required = false) Integer offset,
                                     @RequestParam(value = "length", required = false) Integer limit,
                                     @RequestParam(value = "draw", required = false) Integer draw,
                                     @RequestParam(value = "sorts", required = false) List<String> sortStrings
                                     ) throws URISyntaxException {
        return super.abstractFindAll(offset, limit, draw, sortStrings, null);
    }

    /**
     * GET /groceryListItems/{groceryListItemId} -> gets the target by the target id.
     *
     * @param id the id of the target
     * @return target that has a matching id
     */
    @ApiOperation(value = "get", notes = "Gets the target with the given target id. Secured.", produces = "application/json")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Ok", response = GroceryListItemDto.class), @ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 500, message = "Internal server error", response = ErrorDto.class) })
    @RequestMapping(value = "/groceryListItems/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Transactional
    public ResponseEntity<?> get(@PathVariable("id") Long id) {
        return super.abstractGet(id);
    }

    /**
     * PUT /groceryListItems/{id} -> updates target by the target id.
     *
     * @param groceryListItemDto the target to be updated in the repository
     * @return target that was updated
     */
    @ApiOperation(value = "put", notes = "Updates the target with the given target id. Secured.", produces = "application/json")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Ok", response = GroceryListItemDto.class), @ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 500, message = "Internal server error", response = ErrorDto.class) })
    @RequestMapping(value = "/groceryListItems/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Transactional
    public ResponseEntity<GroceryListItemDto> update(@RequestBody GroceryListItemDto groceryListItemDto) {
        ResponseEntity response;

        try {
            response = super.abstractUpdate(groceryListItemDto);
        } catch(CanNotBeCreatedException e) {
            ErrorDto errorDto = new ErrorDto(e.getMessage());
            response = ResponseEntity.ok().body(errorDto);
        }

        return response;
    }

    /**
     * DELETE /items/{itemId} -> deletes target by the target id.
     *
     * @param itemId the id of the target
     * @return target that has a matching id that was deleted
     */
    @ApiOperation(value = "get", notes = "Soft deletes the target with the given target id. Secured.", produces = "application/json")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Ok", response = ItemDto.class), @ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 500, message = "Internal server error", response = ErrorDto.class) })
    @RequestMapping(value = "/items/{itemId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Transactional
    public ResponseEntity<?> delete(@PathVariable("itemId") Long itemId) {
        return super.abstractDelete(itemId);
    }

    @Override
    protected Sort.Direction getDefaultSortDirection() {
        return Sort.Direction.ASC;
    }

    @Override
    protected String getDefaultSortProperty() {
        return "createdDate";
    }

    @Override
    protected String getRestRoute() {
        return "/api/groceryListItems";
    }

    @Override
    protected GroceryListItem generateEntity() {
        return new GroceryListItem();
    }

    @Override
    protected GroceryListItem generateEntity(GroceryListItemDto dto) {
        GroceryListItem groceryListItem = new GroceryListItem();
        dto.toEntity(groceryListItem);
        return groceryListItem;
    }

    @Override
    protected GroceryListItemDto generateDto(GroceryListItem entity) {
        return new GroceryListItemDto(entity);
    }

    @Override
    protected List transformRecordList(List records) {
        List<GroceryListItemDto> recordDtos = new ArrayList<GroceryListItemDto>();

        if (records != null) {
            List<GroceryListItem> itemRecords = (List<GroceryListItem>) records;

            for (GroceryListItem record : itemRecords) {
                recordDtos.add(new GroceryListItemDto(record));
            }
        }

        return recordDtos;
    }
}
