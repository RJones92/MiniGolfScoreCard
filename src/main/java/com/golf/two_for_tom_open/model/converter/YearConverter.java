package com.golf.two_for_tom_open.model.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.Year;
import java.util.Optional;

@Converter(autoApply = true)
public class YearConverter implements AttributeConverter<Year, Short> {

    @Override
    public Short convertToDatabaseColumn(Year year) {
        return Optional.ofNullable(year)
                .map(y -> (short) y.getValue())
                .orElse(null);
    }

    @Override
    public Year convertToEntityAttribute(Short dbData) {
        return Optional.ofNullable(dbData)
                .map(x -> Year.of(dbData))
                .orElse(null);
    }
}
