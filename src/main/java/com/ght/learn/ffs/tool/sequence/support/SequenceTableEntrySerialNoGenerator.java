package com.ght.learn.ffs.tool.sequence.support;

import java.util.Date;

import com.ght.learn.ffs.entity.task.Sequence.SeqType;

import framework.core.utils.DateUtils;

/**
 * 进仓编号生成器
 * @author MyYate
 *
 */
public class SequenceTableEntrySerialNoGenerator extends CachableSequenceTableSerialNoGenerator {

    @Override
    protected SeqType getSeqType() {
    	return SeqType.EntrySerialNo;
    }

    @Override
    protected String createSerialNoPrefix(String companyCode, String customerCode, Date createDateTime) {
    	return companyCode + customerCode + DateUtils.formatDate(createDateTime, "yyyyMMdd");
    }
}