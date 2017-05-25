package com.ght.learn.ffs.tool.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.util.NumberUtils;

import framework.core.utils.StringUtils;

/**
 * 字符串对数字的转换，如果错误不抛出异常而是返回null
 * @author MyYate
 *
 */
public class SafeStringToNumberConverterFactory implements ConverterFactory<String, Number> {

    @Override
    public <T extends Number> Converter<String, T> getConverter(Class<T> targetType) {
	return new StringToNumber<T>(targetType);
    }

    private static final class StringToNumber<T extends Number> implements Converter<String, T> {

	private final Class<T> targetType;

	public StringToNumber(Class<T> targetType) {
	    this.targetType = targetType;
	}

	@Override
	public T convert(String source) {
	    if (StringUtils.isEmpty(source)) {
		return null;
	    }
	    try {
		return NumberUtils.parseNumber(source, this.targetType);
	    } catch (Throwable e) {
		return null;
	    }
	}
    }

}