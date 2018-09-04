package com.crs.grocery4j.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.crs.grocery4j.domain.database.Item;
import com.crs.grocery4j.domain.dto.ErrorDto;
import com.crs.grocery4j.domain.dto.ItemDto;
import com.crs.grocery4j.exception.CanNotBeCreatedException;
import com.crs.grocery4j.service.domain.ItemService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Conjunction;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Or;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.inject.Inject;
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
public class ItemResource extends BaseResource<Item, ItemDto, ItemService> {

    /**
     * POST /items -> Create a new target.
     *
     * @param itemDto the target object to create
     * @return targetDto the created target object
     * @throws URISyntaxException if an exception occurs creating the response
     *             object
     */
    @ApiOperation(value = "create", notes = "Create a target. Secured.", consumes = "application/json", produces = "application/json")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Ok", response = ItemDto.class), @ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 500, message = "Internal server error", response = ErrorDto.class) })
    @RequestMapping(value = "/items", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Transactional
    public ResponseEntity<?> create(@Valid @RequestBody ItemDto itemDto) throws URISyntaxException {
        ResponseEntity response;

        try {
            response = super.abstractCreate(itemDto);
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
//                                     }) Specification<Item> specification

    /**
     * GET /items -> gets all items
     *
     * @return List<TargetDto> the target objects
     * @throws URISyntaxException if an exception occurs creating the response object
     */
    @ApiOperation(value = "list", notes = "List items. Secured.", consumes = "application/json", produces = "application/json")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Ok", response = List.class), @ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 500, message = "Internal server error", response = ErrorDto.class) })
    @RequestMapping(value = "/items", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
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
     * GET /items/{itemId} -> gets the target by the target id.
     *
     * @param itemId the id of the target
     * @return target that has a matching id
     */
    @ApiOperation(value = "get", notes = "Gets the target with the given target id. Secured.", produces = "application/json")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Ok", response = ItemDto.class), @ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 500, message = "Internal server error", response = ErrorDto.class) })
    @RequestMapping(value = "/items/{itemId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Transactional
    public ResponseEntity<?> get(@PathVariable("itemId") Long itemId) {
        return super.abstractGet(itemId);
    }

    /**
     * PUT /items/{itemId} -> updates target by the target id.
     *
     * @param itemDto the target to be updated in the repository
     * @return target that was updated
     */
    @ApiOperation(value = "put", notes = "Updates the target with the given target id. Secured.", produces = "application/json")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Ok", response = ItemDto.class), @ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 500, message = "Internal server error", response = ErrorDto.class) })
    @RequestMapping(value = "/items/{itemId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Transactional
    public ResponseEntity<ItemDto> update(@RequestBody ItemDto itemDto) {
        ResponseEntity response;

        try {
            response = super.abstractUpdate(itemDto);
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
        return "/api/items";
    }

    @Override
    protected Item generateEntity() {
        return new Item();
    }

    @Override
    protected Item generateEntity(ItemDto dto) {
        Item item = new Item();
        dto.toEntity(item);
        return item;
    }

    @Override
    protected ItemDto generateDto(Item entity) {
        return new ItemDto(entity);
    }

    @Override
    protected List transformRecordList(List records) {
        List<ItemDto> recordDtos = new ArrayList<ItemDto>();

        if (records != null) {
            List<Item> itemRecords = (List<Item>) records;

            for (Item record : itemRecords) {
                recordDtos.add(new ItemDto(record));
            }
        }

        return recordDtos;
    }
}
