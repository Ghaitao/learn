package com.ght.learn.ffs.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.core.convert.converter.Converter;

import framework.core.Constants;
import framework.core.utils.CollectionUtils;
import framework.core.utils.NumberUtils;
import framework.core.utils.ObjectUtils;
import framework.core.utils.StringUtils;

public class FwbUtil {

    /**
     * 过滤IATA Code 08-3 1558/0066 ==> 08315580066
     * 
     * @param iataCode
     * @return
     */
    public static String extractNumberIATACode(String iataCode) {
	if (StringUtils.isEmpty(iataCode)) {
	    return iataCode;
	}
	char[] array = iataCode.toCharArray();
	StringBuilder iata = new StringBuilder(array.length);
	for (char a : array) {
	    if (Character.isDigit(a)) {
		iata.append(a);
	    }
	}
	return iata.toString();
    }

    /**
     * 给定7为运单号码，计算mod7后返回8为运单号码 无效的运单号码参数则返回参数字符串
     * @param str
     * @return
     */
    public static String mod7(String str) {
	if (StringUtils.isEmpty(str) || !isAllDigit(str) || str.length() != 7) {
	    return str;
	}
	return str + String.valueOf(Long.parseLong(str) % 7);
    }

    /**
     * 模7校验
     * 
     * @param str
     * @return
     */
    public static boolean isMod7(String str) {
	if (StringUtils.isEmpty(str) || !isAllDigit(str) || str.length() != 8) {
	    return false;
	}
	String awbno = str.substring(0, 7);
	String suffix = str.substring(7);
	return Long.parseLong(awbno) % 7 == Long.parseLong(suffix);
    }

    public static boolean isAllDigit(String value) {
	if (value == null) {
	    return false;
	}
	for (int i = 0; i < value.length(); i++) {
	    if (!Character.isDigit(value.charAt(i))) {
		return false;
	    }
	}
	return true;
    }

    public static String formatAwbno(String awbPre, String awbNo) {
	if (StringUtils.isEmpty(awbPre) && StringUtils.isEmpty(awbNo)) {
	    return null;
	}
	StringBuilder awbFullNo = new StringBuilder();
	if (StringUtils.hasText(awbPre)) {
	    awbFullNo.append(awbPre);
	    awbFullNo.append("-");
	}
	if (StringUtils.hasText(awbNo)) {
	    awbFullNo.append(awbNo);
	}
	return awbFullNo.toString();
    }
    
    public static String formatAwbno(AWBNoObj awbNoObj) {
	if (awbNoObj == null) {
	    return null;
	}
	return formatAwbno(awbNoObj.getAwbPrefix(), awbNoObj.getAwbNo());
    }
    
    /**
     * 解析运单号 将形如112-1231231 1，999-99999999，99999999999解析成{awbPrefix : 999, awbNo
     * : 99999999} 与eCargo.js AWBUtils.parseAWBNo同步
     * 
     * @param strAwbNoAll
     * @return
     */
    public static AWBNoObj parseAWBNo(String awbFullNo) {
	if (StringUtils.isEmpty(awbFullNo)) {
	    return new AWBNoObj(null, null, false);
	}

	StringBuilder strAwbNoAllUse = new StringBuilder();
	for (int i = 0; i < awbFullNo.length(); i++) {
	    char d = awbFullNo.charAt(i);
	    if (Character.isDigit(d)) {
		strAwbNoAllUse.append(d);
	    }
	}
	if (StringUtils.isEmpty(strAwbNoAllUse)) {
	    return new AWBNoObj(null, null, false);
	}
	if (strAwbNoAllUse.length() != 11) {
	    if (strAwbNoAllUse.length() > 3) {
		return new AWBNoObj(strAwbNoAllUse.substring(0, 3), strAwbNoAllUse.substring(3), false);
	    } else {
		return new AWBNoObj(strAwbNoAllUse.toString(), null, false);
	    }
	}
	String awbNo = strAwbNoAllUse.substring(3);
	if (isMod7(awbNo)) {
	    return new AWBNoObj(strAwbNoAllUse.substring(0, 3), awbNo, true);
	} else {
	    return new AWBNoObj(strAwbNoAllUse.substring(0, 3), awbNo, false);
	}
    }

    public static AWBNoObj parseAWBNo(String awbPre, String awbNo) {
	return parseAWBNo(StringUtils.replaceNullString(awbPre) + StringUtils.replaceNullString(awbNo));
    }

    /**
     * 解析邮件单单号 格式为ML-123654899LAX，需要有'-'分隔符
     * 
     * @param awbFullNo
     * @return
     */
    public static AWBNoObj parseMailAWBNo(String awbFullNo) {
	if (StringUtils.isEmpty(awbFullNo)) {
	    return new AWBNoObj(null, null, false);
	}
	int index = awbFullNo.indexOf("-");
	if (index < 0) {
	    return new AWBNoObj(null, awbFullNo, true);
	}
	return new AWBNoObj(awbFullNo.substring(0, index), awbFullNo.substring(index + 1), true);
    }

    /**
     * 解析航班号如CA234
     * 
     * @param strFlightNo
     * @return
     */
    public static FlightNoObj parseFlightNo(String strFlightNo) {
	if (StringUtils.isEmpty(strFlightNo)) {
	    return FlightNoObj.EMPTY;
	}

	if (StringUtils.length(strFlightNo) < 2) {
	    return new FlightNoObj(strFlightNo, null, false);
	}
	String carrierCode = strFlightNo.substring(0, 2);
	String flightNo = strFlightNo.substring(2);
	if (Character.isLetter(carrierCode.charAt(0)) || Character.isLetter(carrierCode.charAt(1))) {
	    return new FlightNoObj(carrierCode, flightNo);
	} else {
	    return new FlightNoObj(carrierCode, flightNo, false);
	}
    }

    public static List<? extends Dimension> parseDimensionDescriptions(String dimensionDescriptions) {
	if (StringUtils.isEmpty(dimensionDescriptions)) {
	    return Collections.emptyList();
	}
	int commaIndex = dimensionDescriptions.indexOf(Constants.COMMA);
	if (commaIndex < 0) {
	    return Collections.singletonList(parseDimensionDescription(dimensionDescriptions));
	} else {
	    String[] ddArray = StringUtils.tokenizeToStringArray(dimensionDescriptions, Constants.COMMA);
	    if (ObjectUtils.isEmpty(ddArray)) {
		return Collections.emptyList();
	    }
	    List<Dimension> dos = new ArrayList<FwbUtil.Dimension>(ddArray.length);
	    for (String dd : ddArray) {
		dos.add(parseDimensionDescription(dd));
	    }
	    return dos;
	}
    }

    public static <T extends Dimension> String toStringDimensionDescription(List<T> dimensions) {
	if (CollectionUtils.isEmpty(dimensions)) {
	    return null;
	}
	return StringUtils.collectionToString(dimensions, Constants.COMMA, new Converter<T, String>() {
	    @Override
	    public String convert(T source) {
		return toStringDimensionDescription(source);
	    }
	});
    }

    public static String toStringDimensionDescription(Dimension dimension) {
	if (dimension == null) {
	    return null;
	}
	return String.format("%s*%s*%s/%s", dimension.getLength(), dimension.getWidth(), dimension.getHeight(), dimension.getPcs());
    }

    public static Dimension parseDimensionDescription(String dimensionDescription) {
	if (StringUtils.isEmpty(dimensionDescription)) {
	    return DimensionObj.EMPTY;
	}
	int slashIndex = dimensionDescription.indexOf(Constants.LEFT_SLASH);
	if (slashIndex < 0) {
	    String[] lwhs = StringUtils.tokenizeToStringArray(dimensionDescription, Constants.ASTERISK);
	    if (ObjectUtils.isEmpty(lwhs)) {
		return DimensionObj.EMPTY;
	    }
	    if (lwhs.length == 1) {
		return new DimensionObj(NumberUtils.toInteger(lwhs[0], null), null, null, null, false);
	    }
	    if (lwhs.length == 2) {
		return new DimensionObj(NumberUtils.toInteger(lwhs[0], null), NumberUtils.toInteger(lwhs[1], null), null, null, false);
	    }
	    if (lwhs.length > 2) {
		return new DimensionObj(NumberUtils.toInteger(lwhs[0], null), NumberUtils.toInteger(lwhs[1], null), NumberUtils.toInteger(lwhs[2], null), null,
			false);
	    }
	} else {
	    Long pcs = NumberUtils.toLong(dimensionDescription.substring(slashIndex + 1), null);
	    dimensionDescription = dimensionDescription.substring(0, slashIndex);
	    String[] lwhs = StringUtils.tokenizeToStringArray(dimensionDescription, Constants.ASTERISK);
	    if (ObjectUtils.isEmpty(lwhs)) {
		return DimensionObj.EMPTY;
	    }
	    if (lwhs.length == 1) {
		return new DimensionObj(NumberUtils.toInteger(lwhs[0], null), null, null, pcs, false);
	    }
	    if (lwhs.length == 2) {
		return new DimensionObj(NumberUtils.toInteger(lwhs[0], null), NumberUtils.toInteger(lwhs[1], null), null, pcs, false);
	    }
	    if (lwhs.length == 3) {
		return new DimensionObj(NumberUtils.toInteger(lwhs[0], null), NumberUtils.toInteger(lwhs[1], null), NumberUtils.toInteger(lwhs[2], null), pcs,
			true);
	    }
	    if (lwhs.length > 3) {
		return new DimensionObj(NumberUtils.toInteger(lwhs[0], null), NumberUtils.toInteger(lwhs[1], null), NumberUtils.toInteger(lwhs[2], null), pcs,
			false);
	    }
	}
	return DimensionObj.EMPTY;
    }

    public static interface Dimension {
	Integer getLength();

	Integer getWidth();

	Integer getHeight();

	Long getPcs();

	boolean isValid();
    }

    public static class DimensionObj implements Dimension {

	static DimensionObj EMPTY = new DimensionObj(null, null, null, null, false);

	/**
	 * 长
	 */
	private Integer length;
	/**
	 * 宽
	 */
	private Integer width;
	/**
	 * 高
	 */
	private Integer height;
	/**
	 * 件数
	 */
	private Long pcs;

	private boolean valid;

	public DimensionObj(Integer length, Integer width, Integer height, Long pcs, boolean valid) {
	    super();
	    this.length = length;
	    this.width = width;
	    this.height = height;
	    this.pcs = pcs;
	    this.valid = valid;
	}

	@Override
	public boolean isValid() {
	    return this.valid;
	}

	@Override
	public Integer getLength() {
	    return length;
	}

	@Override
	public Integer getWidth() {
	    return width;
	}

	@Override
	public Integer getHeight() {
	    return height;
	}

	@Override
	public Long getPcs() {
	    return pcs;
	}

	@Override
	public String toString() {
	    return String.format("%s*%s*%s/%s", getLength(), getWidth(), getHeight(), getPcs());
	}
    }

    public static class FlightNoObj {

	static FlightNoObj EMPTY = new FlightNoObj(null, null, false);

	private String carrierCode;

	private String flightNo;

	private boolean valid;

	FlightNoObj(String carrierCode, String flightNo, boolean valid) {
	    this.carrierCode = carrierCode;
	    this.flightNo = flightNo;
	    this.valid = valid;
	}

	FlightNoObj(String carrierCode, String flightNo) {
	    this(carrierCode, flightNo, true);
	}

	public String getCarrierCode() {
	    return carrierCode;
	}

	public String getFlightNo() {
	    return flightNo;
	}

	public boolean isValid() {
	    return this.valid;
	}

	public boolean isInvalid() {
	    return !isValid();
	}

	@Override
	public String toString() {
	    return String.format("carrierCode=%s, flightNo=%s", getCarrierCode(), getFlightNo());
	}
    }

    public static class AWBNoObj implements Serializable {

	private static final long serialVersionUID = -7877353694969715269L;

	private String awbPrefix;

	private String awbNo;

	private boolean valid;

	public AWBNoObj(String awbPrefix, String awbNo) {
	    this(awbPrefix, awbNo, true);
	}

	public AWBNoObj(String awbPrefix, String awbNo, boolean valid) {
	    this.awbPrefix = awbPrefix;
	    this.awbNo = awbNo;
	    this.valid = valid;
	}

	public String getAwbPrefix() {
	    return awbPrefix;
	}

	public String getAwbNo() {
	    return awbNo;
	}

	public boolean isValid() {
	    return this.valid;
	}

	public boolean isInvalid() {
	    return !isValid();
	}

	@Override
	public String toString() {
	    return String.format("awbPrefix=%s, awbNo=%s", getAwbPrefix(), getAwbNo());
	}
    }
}