package com.ght.learn.ffs.tool.sequence.support;

import java.util.Date;

import com.ght.learn.ffs.entity.task.Sequence.SeqType;

import framework.core.utils.DateUtils;

/**
 * 订单号生成器
 * @author MyYate
 *
 */
public class SequenceTableOrderSerialNoGenerator extends CachableSequenceTableSerialNoGenerator {

    @Override
    protected SeqType getSeqType() {
    	return SeqType.OrderSerialNo;
    }

    @Override
    protected String createSerialNoPrefix(String companyCode, String customerCode, Date createDateTime) {
    	return companyCode + DateUtils.formatDate(createDateTime, "yyMMddHHmmss");
    }
}