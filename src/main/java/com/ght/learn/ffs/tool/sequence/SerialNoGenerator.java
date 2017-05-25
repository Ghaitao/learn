package com.ght.learn.ffs.tool.sequence;

import java.util.Date;

import framework.service.ServiceException;

/**
 * 序号生成器
 * @author ght
 *
 */
public interface SerialNoGenerator {

	/**
     * 生成订单编号
     * @return
     */
    String generateSerialNo(String companyCode, String customerCode, Date createDateTime) throws ServiceException;
}
