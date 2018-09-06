package com.crs.grocery4j.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.crs.grocery4j.domain.database.GroceryList;
import com.crs.grocery4j.domain.database.GroceryListItem;
import com.crs.grocery4j.domain.dto.ErrorDto;
import com.crs.grocery4j.domain.dto.GroceryListDto;
import com.crs.grocery4j.domain.dto.GroceryListItemDto;
import com.crs.grocery4j.domain.dto.ItemDto;
import com.crs.grocery4j.exception.CanNotBeCreatedException;
import com.crs.grocery4j.service.domain.GroceryListItemService;
import com.crs.grocery4j.service.domain.GroceryListService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Sort;
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
public class GroceryListResource extends BaseResource<GroceryList, GroceryListDto, GroceryListService> {

    @Inject
    private GroceryListService service;

    /**
     * POST /groceryLists -> Create a new target.
     *
     * @param groceryListDto the target object to create
     * @return targetDto the created target object
     * @throws URISyntaxException if an exception occurs creating the response
     *             object
     */
    @ApiOperation(value = "create", notes = "Create a target. Secured.", consumes = "application/json", produces = "application/json")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Ok", response = GroceryListDto.class), @ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 500, message = "Internal server error", response = ErrorDto.class) })
    @RequestMapping(value = "/groceryLists", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Transactional
    public ResponseEntity<?> create(@Valid @RequestBody GroceryListDto groceryListDto) throws URISyntaxException {
        ResponseEntity response;

        try {
            response = super.abstractCreate(groceryListDto);
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
//                                     }) Specification<GroceryList> specification

    /**
     * GET /groceryLists -> gets all groceryLists
     *
     * @return List<TargetDto> the target objects
     * @throws URISyntaxException if an exception occurs creating the response object
     */
    @ApiOperation(value = "list", notes = "List groceryLists. Secured.", consumes = "application/json", produces = "application/json")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Ok", response = List.class), @ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 500, message = "Internal server error", response = ErrorDto.class) })
    @RequestMapping(value = "/groceryLists", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
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
     * GET /groceryLists/{groceryListId} -> gets the target by the target id.
     *
     * @param id the id of the target
     * @return target that has a matching id
     */
    @ApiOperation(value = "get", notes = "Gets the target with the given target id. Secured.", produces = "application/json")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Ok", response = GroceryListDto.class), @ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 500, message = "Internal server error", response = ErrorDto.class) })
    @RequestMapping(value = "/groceryLists/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Transactional
    public ResponseEntity<?> get(@PathVariable("id") Long id) {
        return super.abstractGet(id);
    }

    /**
     * PUT /groceryLists/{id} -> updates target by the target id.
     *
     * @param groceryListDto the target to be updated in the repository
     * @return target that was updated
     */
    @ApiOperation(value = "put", notes = "Updates the target with the given target id. Secured.", produces = "application/json")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Ok", response = GroceryListDto.class), @ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 500, message = "Internal server error", response = ErrorDto.class) })
    @RequestMapping(value = "/groceryLists/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Transactional
    public ResponseEntity<GroceryListDto> update(@RequestBody GroceryListDto groceryListDto) {
        ResponseEntity response;

        try {
            response = super.abstractUpdate(groceryListDto);
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
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Ok", response = GroceryListDto.class), @ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 500, message = "Internal server error", response = ErrorDto.class) })
    @RequestMapping(value = "/groceryLists/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Transactional
    public ResponseEntity<?> delete(@PathVariable("id") Long itemId) {
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
        return "/api/groceryLists";
    }

    @Override
    protected GroceryList generateEntity() {
        return new GroceryList();
    }

    @Override
    protected GroceryList generateEntity(GroceryListDto dto) {
        GroceryList groceryList = new GroceryList();
        dto.toEntity(groceryList);
        return groceryList;
    }

    @Override
    protected GroceryListDto generateDto(GroceryList entity) {
        return new GroceryListDto(entity);
    }

    @Override
    protected List transformRecordList(List records) {
        List<GroceryListDto> recordDtos = new ArrayList<GroceryListDto>();

        if (records != null) {
            List<GroceryList> itemRecords = (List<GroceryList>) records;

            for (GroceryList record : itemRecords) {
                recordDtos.add(new GroceryListDto(record));
            }
        }

        return recordDtos;
    }

}
