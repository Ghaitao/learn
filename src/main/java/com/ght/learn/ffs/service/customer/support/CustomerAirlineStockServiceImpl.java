package com.ght.learn.ffs.service.customer.support;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.ght.learn.ffs.common.FwbUtil;
import com.ght.learn.ffs.common.FwbUtil.AWBNoObj;
import com.ght.learn.ffs.dto.customer.CustomerAirlineStockDto;
import com.ght.learn.ffs.entity.customer.CustomerAirlineStockDetail;
import com.ght.learn.ffs.entity.customer.CustomerAirlineStockSegment;
import com.ght.learn.ffs.enums.IF;
import com.ght.learn.ffs.service.FfsServiceImpl;
import com.ght.learn.ffs.service.customer.CustomerAirlineStockService;
import com.ght.learn.ffs.tool.context.SessionUser;

import framework.core.Constants;
import framework.core.pagination.OrderablePagination;
import framework.core.utils.ObjectUtils;
import framework.core.utils.StringUtils;
import framework.dao.orm.hibernate.query.SafeDetachedCriteria;
import framework.dao.orm.hibernate.query.SafeRestrictions;
import framework.service.ServiceException;

@Component("CustomerAirlineStockService")
public class CustomerAirlineStockServiceImpl extends FfsServiceImpl implements CustomerAirlineStockService {

    @Override
    public List<CustomerAirlineStockSegment> queryCustomerAirlineStockSegments(CustomerAirlineStockDto customerAirlineStockDto, OrderablePagination op,
	    SessionUser operator) {
		SafeDetachedCriteria sdc = SafeDetachedCriteria.forClass(CustomerAirlineStockSegment.class);
		if (customerAirlineStockDto != null) {
		    sdc.add(SafeRestrictions.equal("customerCode", StringUtils.safeToUpperCase(customerAirlineStockDto.getCustomerCode())));
		    sdc.add(SafeRestrictions.equal("stkPrefix", customerAirlineStockDto.getStkPrefix()));
		    sdc.add(SafeRestrictions.and(SafeRestrictions.greaterThanEqual("endNo", customerAirlineStockDto.getStartNoOrEndNo()), 
			    SafeRestrictions.lessThanEqual("startNo", customerAirlineStockDto.getStartNoOrEndNo())));
		}
		sdc.add(SafeRestrictions.equal("companyCode", operator.getCompanyCode()));
		if (op != null && ObjectUtils.isNotEmpty(op.getSorters())) {
		    sdc.addSorters(op.getSorters());
		} else {
		    sdc.addOrders(new Order[] {Order.asc("customerCode"), Order.asc("stkPrefix"), Order.asc("startNo")});
		}
		return this.getEntityManager().queryForListByCriteria(CustomerAirlineStockSegment.class, sdc, op);
    }

    @Override
    public void doDeleteCustomerAirlineStockSegment(Long airlineStockSegmentId, SessionUser operator) {
		if (airlineStockSegmentId == null) {
		    return;
		}
		
		SafeDetachedCriteria sdc = SafeDetachedCriteria.forClass(CustomerAirlineStockDetail.class);
		sdc.setProjection(Projections.count("recId"));
		sdc.add(SafeRestrictions.equal("airlineStockSegmentId", airlineStockSegmentId));
		sdc.add(SafeRestrictions.equal("canUse", IF.Y));
		sdc.add(SafeRestrictions.equal("companyCode", operator.getCompanyCode()));
		Long count = getEntityManager().queryForUniqueObjectByCriteria(Long.class, sdc);
		if (count != null && count.longValue() > 0) {
		    throw new ServiceException(getMessageSource().getMessage("com.jinzhiyi.ffs.message.CustomerAirlineStockSegment.page.query.validate.delete.hasunusedstkno", 
			    new Object[] {count}, LocaleContextHolder.getLocale()));
		}
		
		getEntityManager().executeHql("delete from CustomerAirlineStockSegment c where c.recId=? and c.companyCode=?", new Object[] {airlineStockSegmentId, operator.getCompanyCode()});
		getEntityManager().executeHql("delete from CustomerAirlineStockDetail c where c.airlineStockSegmentId=? and c.companyCode=?", new Object[] {airlineStockSegmentId, operator.getCompanyCode()});
    }

    @Override
    public CustomerAirlineStockDetail doGetNextAwbNo(String customerCode, SessionUser operator) throws ServiceException {
		if (StringUtils.isEmpty(customerCode)) {
		    throw new ServiceException(getMessageSource().getMessage("com.jinzhiyi.ffs.message.CustomerAirlineStockSegment.validate.no_airlineCode", 
			    Constants.EMPTY_OBJECT_ARRAY, LocaleContextHolder.getLocale()));
		}
		
		customerCode = StringUtils.safeToUpperCase(StringUtils.safeToTrim(customerCode));
		
		SafeDetachedCriteria sdc = SafeDetachedCriteria.forClass(CustomerAirlineStockDetail.class);
		sdc.add(SafeRestrictions.equal("canUse", IF.Y));
		sdc.add(SafeRestrictions.equal("customerCode", customerCode));
		sdc.add(SafeRestrictions.equal("companyCode", operator.getCompanyCode()));
		sdc.addOrders(new Order[] {Order.asc("stkPrefix"), Order.asc("stkNo")});
		CustomerAirlineStockDetail customerAirlineStockDetail = getEntityManager().queryForUniqueObjectByCriteria(CustomerAirlineStockDetail.class, sdc);
		if (customerAirlineStockDetail == null) {
		    throw new ServiceException(getMessageSource().getMessage("com.jinzhiyi.ffs.message.CustomerAirlineStockSegment.validate.no_stkNo_touse", 
			    new Object[] {customerCode}, LocaleContextHolder.getLocale()));
		}
		return customerAirlineStockDetail;
    }

    @Override
    public void doUsedAwbNo(String customerCode, String awbFullNo, SessionUser operator) throws ServiceException {
		if (StringUtils.isEmpty(customerCode)) {
		    throw new ServiceException(getMessageSource().getMessage("com.jinzhiyi.ffs.message.CustomerAirlineStockSegment.validate.no_airlineCode", 
			    Constants.EMPTY_OBJECT_ARRAY, LocaleContextHolder.getLocale()));
		}
		AWBNoObj awbNoObj = FwbUtil.parseAWBNo(awbFullNo);
		if (awbNoObj.isInvalid()) {
		    throw new ServiceException(getMessageSource().getMessage("com.jinzhiyi.ffs.message.CustomerAirlineStockSegment.validate.invalid_awbno", 
			    new Object[] {awbFullNo}, LocaleContextHolder.getLocale()));
		}
		
		SafeDetachedCriteria sdc = SafeDetachedCriteria.forClass(CustomerAirlineStockDetail.class);
		sdc.add(SafeRestrictions.equal("stkPrefix", awbNoObj.getAwbPrefix()));
		sdc.add(SafeRestrictions.equal("stkNo", awbNoObj.getAwbNo()));
		sdc.add(SafeRestrictions.equal("customerCode", customerCode));
		sdc.add(SafeRestrictions.equal("companyCode", operator.getCompanyCode()));
		CustomerAirlineStockDetail customerAirlineStockDetail = getEntityManager().queryForUniqueObjectByCriteria(CustomerAirlineStockDetail.class, sdc);
		if (customerAirlineStockDetail == null) {
		    throw new ServiceException(getMessageSource().getMessage("com.jinzhiyi.ffs.message.CustomerAirlineStockSegment.validate.not_find_awbno", 
			    new Object[] {customerCode, awbFullNo}, LocaleContextHolder.getLocale()));
		}
		if (customerAirlineStockDetail.isHasUsed()) {
		    throw new ServiceException(getMessageSource().getMessage("com.jinzhiyi.ffs.message.CustomerAirlineStockSegment.validate.awbno_used", 
			    new Object[] {awbFullNo}, LocaleContextHolder.getLocale()));
		}
		doSetAsUnavailableCustomerAirlineStockDetail(customerAirlineStockDetail.getRecId(), operator);
    }

    @Override
    public List<CustomerAirlineStockDetail> queryCustomerAirlineStockDetails(CustomerAirlineStockDto customerAirlineStockDto, OrderablePagination op,
	    SessionUser operator) {
		SafeDetachedCriteria sdc = SafeDetachedCriteria.forClass(CustomerAirlineStockDetail.class);
		if (customerAirlineStockDto != null) {
		    sdc.add(SafeRestrictions.equal("customerCode", StringUtils.safeToUpperCase(customerAirlineStockDto.getCustomerCode())));
		    sdc.add(SafeRestrictions.equal("airlineStockSegmentId", customerAirlineStockDto.getAirlineStockSegmentId()));
		    AWBNoObj awbNoObj = FwbUtil.parseAWBNo(customerAirlineStockDto.getAwbFullNo());
		    sdc.add(SafeRestrictions.equal("stkPrefix", awbNoObj.getAwbPrefix()));
		    sdc.add(SafeRestrictions.equal("stkNo", awbNoObj.getAwbNo()));
		}
		sdc.add(SafeRestrictions.equal("companyCode", operator.getCompanyCode()));
		if (op != null && ObjectUtils.isNotEmpty(op.getSorters())) {
		    sdc.addSorters(op.getSorters());
		} else {
		    sdc.addOrders(new Order[] {Order.asc("customerCode"), Order.asc("stkPrefix"), Order.asc("stkNo")});
		}
		return this.getEntityManager().queryForListByCriteria(CustomerAirlineStockDetail.class, sdc, op);
    }

    @Override
    public Long doCreateCustomerAirlineStockSegment(CustomerAirlineStockSegment inputCustomerAirlineStockSegment, SessionUser operator) throws ServiceException {
		Assert.notNull(inputCustomerAirlineStockSegment);
		Assert.hasText(inputCustomerAirlineStockSegment.getCustomerCode());
		Assert.hasText(inputCustomerAirlineStockSegment.getStkPrefix());
		Assert.hasText(inputCustomerAirlineStockSegment.getStartNo());
		Assert.hasText(inputCustomerAirlineStockSegment.getEndNo());
		
		resolveInputCustomerAirlineStockSegment(inputCustomerAirlineStockSegment);
		
		if (inputCustomerAirlineStockSegment.getStkPrefix().length() != 3 || !FwbUtil.isAllDigit(inputCustomerAirlineStockSegment.getStkPrefix())) {
		    throw new ServiceException(getMessageSource().getMessage("com.jinzhiyi.ffs.message.CustomerAirlineStockSegment.validate.stkPrefix", Constants.EMPTY_OBJECT_ARRAY, LocaleContextHolder.getLocale()));
		}
		if (inputCustomerAirlineStockSegment.getStartNo().length() != 7 || !FwbUtil.isAllDigit(inputCustomerAirlineStockSegment.getStartNo())) {
		    throw new ServiceException(getMessageSource().getMessage("com.jinzhiyi.ffs.message.CustomerAirlineStockSegment.validate.startNo", Constants.EMPTY_OBJECT_ARRAY, LocaleContextHolder.getLocale()));
		}
		if (inputCustomerAirlineStockSegment.getEndNo().length() != 7 || !FwbUtil.isAllDigit(inputCustomerAirlineStockSegment.getEndNo())) {
		    throw new ServiceException(getMessageSource().getMessage("com.jinzhiyi.ffs.message.CustomerAirlineStockSegment.validate.endNo", Constants.EMPTY_OBJECT_ARRAY, LocaleContextHolder.getLocale()));
		}
		
		long startNoLong = Long.parseLong(inputCustomerAirlineStockSegment.getStartNo());
		long endNoLong = Long.parseLong(inputCustomerAirlineStockSegment.getEndNo());
		
		if (endNoLong < startNoLong) {
		    throw new ServiceException(getMessageSource().getMessage("com.jinzhiyi.ffs.message.CustomerAirlineStockSegment.validate.must.endNoGreaterEqualThanstartNo", Constants.EMPTY_OBJECT_ARRAY, LocaleContextHolder.getLocale()));
		}
		checkStartNoAndEndNo(inputCustomerAirlineStockSegment.getStkPrefix(), inputCustomerAirlineStockSegment.getStartNo(), inputCustomerAirlineStockSegment.getEndNo());
		
		CustomerAirlineStockSegment dbCustomerAirlineStockSegment = new CustomerAirlineStockSegment();
		dbCustomerAirlineStockSegment.setCustomerCode(inputCustomerAirlineStockSegment.getCustomerCode());
		dbCustomerAirlineStockSegment.setStkPrefix(inputCustomerAirlineStockSegment.getStkPrefix());
		dbCustomerAirlineStockSegment.setStartNo(inputCustomerAirlineStockSegment.getStartNo());
		dbCustomerAirlineStockSegment.setEndNo(inputCustomerAirlineStockSegment.getEndNo());
		dbCustomerAirlineStockSegment.setCount(Long.valueOf(endNoLong - startNoLong + 1));
		dbCustomerAirlineStockSegment.setCompanyCode(operator.getCompanyCode());
		fillCreatableEntityOnCreate(dbCustomerAirlineStockSegment, operator);
		Long airlineStockSegmentId = (Long) getEntityManager().save(dbCustomerAirlineStockSegment);
		
		LinkedList<String> detailInsertSQLs = new LinkedList<String>();
		for (long index = startNoLong; index <= endNoLong; index ++) {
		    StringBuilder sql = new StringBuilder();
		    sql.append("INSERT INTO C_CUSTOMER_AIRLINE_STK_DETAIL");
		    sql.append("(AIRLINE_STOCK_SEGMENT_ID, STK_PREFIX, STK_NO, CAN_USE, CUSTOMER_CODE, COMPANY_CODE, CREATOR, CREATE_DATE_TIME)");
		    sql.append(" VALUES(");
		    sql.append(airlineStockSegmentId);
		    sql.append(",");
		    sql.append("'").append(dbCustomerAirlineStockSegment.getStkPrefix()).append("'");
		    sql.append(",");
		    sql.append("'").append(FwbUtil.mod7(String.valueOf(index))).append("'");
		    sql.append(",");
		    sql.append("'").append(IF.Y.name()).append("'");
		    sql.append(",");
		    sql.append("'").append(dbCustomerAirlineStockSegment.getCustomerCode()).append("'");
		    sql.append(",");
		    sql.append("'").append(dbCustomerAirlineStockSegment.getCompanyCode()).append("'");
		    sql.append(",");
		    sql.append(operator.getUserId());
		    sql.append(",");
		    sql.append("now()");
		    sql.append(")");
		    detailInsertSQLs.add(sql.toString());
		}
		getEntityManager().executeBatchSql(detailInsertSQLs);
		return airlineStockSegmentId;
    }

    @Override
    public void doSetAsAvailableCustomerAirlineStockDetail(Long customerAirlineStockDetailRecId, SessionUser operator) throws ServiceException {
		if (customerAirlineStockDetailRecId == null) {
		    return;
		}
		getEntityManager().executeHql("update CustomerAirlineStockDetail c set c.canUse=? where c.recId=? and c.companyCode=?", new Object[] {IF.Y, customerAirlineStockDetailRecId, operator.getCompanyCode()});
    }

    @Override
    public void doSetAsUnavailableCustomerAirlineStockDetail(Long customerAirlineStockDetailRecId, SessionUser operator) throws ServiceException {
		if (customerAirlineStockDetailRecId == null) {
		    return;
		}
		getEntityManager().executeHql("update CustomerAirlineStockDetail c set c.canUse=? where c.recId=? and c.companyCode=?", new Object[] {IF.N, customerAirlineStockDetailRecId, operator.getCompanyCode()});
    }

    /**
     * 号段不能有交叉
     * @param inputCustomerAirlineStockSegment
     */
    private void checkStartNoAndEndNo(String stkPrefix, String startNo, String endNo) throws ServiceException {
		SafeDetachedCriteria sdc = SafeDetachedCriteria.forClass(CustomerAirlineStockSegment.class);
		sdc.setProjection(Projections.property("recId"));
		sdc.add(SafeRestrictions.or(SafeRestrictions.and(SafeRestrictions.lessThanEqual("startNo", startNo), SafeRestrictions.greaterThanEqual("endNo", startNo)),
			SafeRestrictions.and(SafeRestrictions.lessThanEqual("startNo", endNo), SafeRestrictions.greaterThanEqual("endNo", endNo))));
		sdc.add(SafeRestrictions.equal("stkPrefix", stkPrefix));
		Long recId = getEntityManager().queryForUniqueObjectByCriteria(Long.class, sdc);
		if (recId != null && recId.longValue() > 0) {
		    throw new ServiceException(getMessageSource().getMessage("com.jinzhiyi.ffs.message.CustomerAirlineStockSegment.validate.must.startNoOrendNoExists", Constants.EMPTY_OBJECT_ARRAY, LocaleContextHolder.getLocale()));
		}
    }
    
    private void resolveInputCustomerAirlineStockSegment(CustomerAirlineStockSegment inputCustomerAirlineStockSegment) {
		if (inputCustomerAirlineStockSegment == null) {
		    return;
		}
		inputCustomerAirlineStockSegment.setStkPrefix(StringUtils.safeToTrim(inputCustomerAirlineStockSegment.getStkPrefix()));
		inputCustomerAirlineStockSegment.setStartNo(StringUtils.safeToTrim(inputCustomerAirlineStockSegment.getStartNo()));
		inputCustomerAirlineStockSegment.setEndNo(StringUtils.safeToTrim(inputCustomerAirlineStockSegment.getEndNo()));
    }
}
