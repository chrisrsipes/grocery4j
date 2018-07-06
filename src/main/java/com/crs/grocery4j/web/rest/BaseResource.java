package com.crs.grocery4j.web.rest;

import com.crs.grocery4j.domain.database.AbstractBaseEntity;
import com.crs.grocery4j.domain.dto.AbstractBaseDto;
import com.crs.grocery4j.domain.dto.ResponseDto;
import com.crs.grocery4j.service.domain.RestService;
import com.crs.grocery4j.web.rest.util.PaginationUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * Created by crs on 7/6/18.
 */

public abstract class BaseResource<Entity extends AbstractBaseEntity, Dto extends AbstractBaseDto, Service extends RestService<Entity>> {

    @Inject
    protected Service service;


    protected Sort getSort(List<String> sortStrings) {

        Sort sort;

        if (null == sortStrings || 0 == sortStrings.size()) {
            sort = new Sort(getDefaultSortDirection(), getDefaultSortProperty());
        }
        else {
            sort = PaginationUtil.buildSort(sortStrings);
        }

        return sort;
    }

    protected HttpHeaders getHeadersFromPage(Page<Entity> page, String baseUrl) throws URISyntaxException {
        HttpHeaders httpHeaders;

        if (page != null) {
            httpHeaders = PaginationUtil.generatePaginationHttpHeaders(page, baseUrl, page.getNumber(), page.getSize());
        }
        else {
            httpHeaders = null;
        }

        return httpHeaders;
    }

    protected ResponseEntity<?> abstractCreate(Dto dto) throws URISyntaxException {
        ResponseEntity response;
        try{
            Entity entity = generateEntity(dto);

            // create target using the targetService
            entity = this.service.create(entity);

            // check if bad request
            if (entity != null) {
                response = ResponseEntity.created(new URI(getRestRoute() + "/" + entity.getId())).body(generateDto(entity));
            }
            else {
                response =  ResponseEntity.badRequest().build();
            }
        }catch(Exception e)
        {
            response = ResponseEntity.badRequest().body(e);
        }


        return response;
    }


    protected ResponseEntity<?> abstractFindAll(Integer offset, Integer limit, Integer draw, List<String> sortStrings, Specification<Entity> specification) throws URISyntaxException {

        ResponseEntity response;
        Sort sort = getSort(sortStrings);
        HttpHeaders headers;
        List<Entity> records;
        Page<Entity> page;

        try{
            if (null != offset || null != limit) {
                page = service.findAll(specification, PaginationUtil.generatePageRequestFromOffset(offset, limit, sort));
                records = page.getContent();
            }
            else {
                page = null;
                records = service.findAll(specification, sort);
            }

            headers = getHeadersFromPage(page, getRestRoute());

            if (records != null) {
                List<Dto> recordDtos = transformRecordList(records);

                Long count = service.count(specification);

                ResponseDto<Dto> responseDto = new ResponseDto<>();
                responseDto.setData(recordDtos);
                responseDto.setDraw(draw);
                responseDto.setStart(offset);
                responseDto.setLength(limit);
                responseDto.setRecordsTotal(count);
                responseDto.setRecordsFiltered(count);

                response =  new ResponseEntity<>(responseDto, headers, HttpStatus.OK);
            }
            else {
                response = ResponseEntity.badRequest().build();
            }
        }catch(Exception e)
        {
            response = ResponseEntity.badRequest().body(e);

        }
        return response;
    }

    protected ResponseEntity<?> abstractGet(Long id) {
        ResponseEntity response;

        try{
            Entity entity = this.service.findById(id);

            if (entity != null) {
                response = ResponseEntity.ok().body(generateDto(entity));
            }
            else {
                response = ResponseEntity.noContent().build();
            }
        }catch(Exception e)
        {
            response = ResponseEntity.badRequest().body(e);
        }

        return response;
    }

    protected ResponseEntity<Dto> buildResponseForEntity(Entity entity) {
        ResponseEntity response;

        if (entity != null) {
            response = ResponseEntity.ok().body(generateDto(entity));
        }
        else {
            response = ResponseEntity.noContent().build();
        }

        return response;
    }

    protected ResponseEntity<?> abstractUpdate(Dto dto) {

        ResponseEntity response;
        try{

            if (dto != null && dto.getId() != null) {

                Entity entity = generateEntity(dto);

                entity = this.service.update(entity);

                if (entity != null) {
                    dto = generateDto(entity);
                    response = ResponseEntity.ok().body(dto);
                }
                else {
                    response = ResponseEntity.badRequest().build();
                }

            }
            else {
                response =  ResponseEntity.badRequest().build();
            }
        }catch(Exception e){
            response = ResponseEntity.badRequest().body(e);
        }

        return response;
    }

    protected ResponseEntity<?> abstractDelete(Long id) {

        ResponseEntity response;
        Entity entity = this.service.findById(id);

        if (entity != null) {
            this.service.delete(entity);
            response = ResponseEntity.ok().body(HttpStatus.OK);
        }
        else {
            response = ResponseEntity.badRequest().build();
        }

        return response;
    }

    abstract Sort.Direction getDefaultSortDirection();

    abstract String getDefaultSortProperty();

    abstract List<Dto> transformRecordList(List<Entity> records);

    abstract String getRestRoute();

    abstract Entity generateEntity();

    abstract Entity generateEntity(Dto dto);

    abstract Dto generateDto(Entity entity);

}
