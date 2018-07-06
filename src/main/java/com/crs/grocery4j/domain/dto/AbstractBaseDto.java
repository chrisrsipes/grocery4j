package com.crs.grocery4j.domain.dto;

import com.crs.grocery4j.domain.database.AbstractBaseEntity;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.riptide.reflection.services.utility.PlainDateTimeDeserializer;
import com.riptide.reflection.services.utility.PlainDateTimeSerializer;
import org.joda.time.DateTime;
import java.io.Serializable;

/**
 * Created by crs on 7/6/18.
import org.joda.time.DateTime;

import java.io.Serializable;package com.riptide.reflection.domain.dto;

 */

public abstract class AbstractBaseDto implements Serializable {

    private static final long serialVersionUID = 1L;

    protected Long id;

    protected Long version;

    @JsonSerialize(using = PlainDateTimeSerializer.class)
    @JsonDeserialize(using = PlainDateTimeDeserializer.class)
    protected DateTime createdDate;

    @JsonSerialize(using = PlainDateTimeSerializer.class)
    @JsonDeserialize(using = PlainDateTimeDeserializer.class)
    protected DateTime lastModifiedDate;

    public AbstractBaseDto() {
    }

    public AbstractBaseDto(AbstractBaseDto dto) {
        this.id = dto.getId();
        this.version = dto.getVersion();
        this.createdDate = dto.getCreatedDate();
        this.lastModifiedDate = dto.getLastModifiedDate();
    }

    public AbstractBaseDto(AbstractBaseEntity entity) {
        this.id = entity.getId();
        this.version = entity.getVersion();
        this.createdDate = entity.getCreatedDate();
        this.lastModifiedDate = entity.getLastModifiedDate();
    }

    public void toEntity(AbstractBaseEntity entity){
        if (entity != null) {
            entity.setId(this.id);
            entity.setVersion(this.version);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public DateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(DateTime createdDate) {
        this.createdDate = createdDate;
    }

    public DateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(DateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}

