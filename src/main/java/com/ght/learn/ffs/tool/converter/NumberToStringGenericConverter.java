package com.ght.learn.ffs.tool.converter;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;

import framework.core.utils.CollectionUtils;
import framework.core.utils.NumberUtils;

public class NumberToStringGenericConverter implements GenericConverter {

    private static final String DECIMAL_FORMAT_STRING = "##.##";
    
    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
	return CollectionUtils.asSet(new ConvertiblePair(BigDecimal.class, String.class), 
		new ConvertiblePair(Double.class, String.class), 
		new ConvertiblePair(Float.class, String.class));
    }

    @Override
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
	if (Objects.isNull(source)) {
	    return null;
	}
	
	if (BigDecimal.class.equals(source.getClass()) || Double.class.equals(source.getClass()) || Float.class.equals(source.getClass())) {
	    return NumberUtils.getNumberFormat(DECIMAL_FORMAT_STRING).format(source);
	}
	return source.toString();
    }

}