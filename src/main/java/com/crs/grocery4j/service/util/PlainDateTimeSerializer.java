package com.crs.grocery4j.service.util;

import com.crs.grocery4j.config.Constants;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.IOException;

/**
 *
 * @author valery.schevchenko
 */
public class PlainDateTimeSerializer extends JsonSerializer<DateTime> {

    private static DateTimeFormatter formatter = DateTimeFormat
        .forPattern(Constants.APP_DATETIME_FORMAT).withZoneUTC();

    @Override
    public void serialize(DateTime value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        jgen.writeString(formatter.print(value));
    }

    public static String print(DateTime value) {
        return formatter.print(value);
    }
}
