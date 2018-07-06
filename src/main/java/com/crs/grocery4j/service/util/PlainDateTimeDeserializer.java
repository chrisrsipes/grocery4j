package com.crs.grocery4j.service.util;

import com.crs.grocery4j.config.Constants;
import org.joda.time.DateTime;
import org.slf4j.LoggerFactory;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.joda.time.format.ISODateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;

/**
 * Created by crs on 7/6/18.
 */

public class PlainDateTimeDeserializer extends JsonDeserializer<DateTime> {

    private static final Logger log = LoggerFactory.getLogger(PlainDateTimeDeserializer.class);

    private static DateTimeFormatter formatter = DateTimeFormat
        .forPattern(Constants.APP_DATETIME_FORMAT);

    @Override
    public DateTime deserialize(JsonParser jp, DeserializationContext dc) throws IOException, JsonProcessingException {
        JsonToken t = jp.getCurrentToken();
        if (t == JsonToken.VALUE_STRING) {
            String str = jp.getText().trim();
            if (str.contains("Z")) {
                return ISODateTimeFormat.dateTimeParser().withZoneUTC().parseDateTime(str);
            } else if (str.contains("-")) {
                return formatter.parseDateTime(str);
            } else {
                return DateTimeFormat.forPattern(Constants.APP_DATETIME_FORMAT).parseDateTime(str);
            }
        }
        throw new RuntimeException("incorrect input type");
    }
}




