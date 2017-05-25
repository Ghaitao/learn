package com.ght.learn.ffs.tool.sequence.support;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.concurrent.locks.ReentrantLock;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.util.Assert;

import com.ght.learn.ffs.entity.task.Sequence;
import com.ght.learn.ffs.tool.sequence.SerialNoGenerator;

import framework.core.utils.DateUtils;
import framework.core.utils.StringUtils;
import framework.service.ServiceException;

/**
 * 基于表t_sequence生成订单序列号，每天从1开始
 * @author ght
 *
 */
public abstract class CachableSequenceTableSerialNoGenerator implements SerialNoGenerator{
	
	/**
	 * last_insert_id()函数:自动返回最后一个INSERT或 UPDATE 查询中 AUTO_INCREMENT列设置的第一个表发生的值。
	 */
	protected static final String VALUE_SQL = "select last_insert_id()";
    protected static final String UPDATE_VALUE_SQL = "update t_sequence s set s.CURRENT_VALUE=last_insert_id(s.CURRENT_VALUE+%s) where s.SEQ_TYPE='%s' and s.FMT_DAY='%s' and s.COMPANY_CODE='%s'";
    
    private long nextValue = 0;

    private long maxValue = 0;
    
    private int cacheSize = 1;
    
    //注入是在spring-ffs.xml中
    private DataSource ffsDataSource;
    //编号类型:订单编号，进仓编号
    protected abstract Sequence.SeqType getSeqType();
    
    protected abstract String createSerialNoPrefix(String companyCode, String customerCode, Date createDateTime);

    /**
     * 最后数字流水号占用几位
     * @return
     */
    protected int getPostSerialNoCount() {
    	return 4;
    }
    
    @Override
    public String generateSerialNo(String companyCode, String customerCode, Date createDateTime) throws ServiceException {
		Assert.notNull(getSeqType(), "seqType must be not null");
		Assert.hasText(companyCode, "companyCode must be not empty");
		Assert.hasText(customerCode, "customerCode must be not empty");
		Assert.notNull(createDateTime, "createDateTime must be not null");
		
		StringBuilder sequenceNo = new StringBuilder();
		sequenceNo.append(createSerialNoPrefix(companyCode, customerCode, createDateTime));
	
		ReentrantLock lock = new ReentrantLock();
		lock.lock();
		try {
		    sequenceNo.append(StringUtils.leftPad(Long.toString(generateNextValue(companyCode, getSeqType(), createDateTime)), getPostSerialNoCount(), "0"));
		    return sequenceNo.toString();
		} finally {
		    lock.unlock();
		}
    }
    private long generateNextValue(String companyCode, Sequence.SeqType seqType, Date createDateTime) {
    	String fmtDay = DateUtils.formatDate(createDateTime, "yyyyMMdd");
    	if (this.maxValue == this.nextValue) {
    	    Connection con = null;
    	    Statement stmt = null;
    	    ResultSet rs = null;
    	    try {
    		//UPDATE_VALUE_SQL和VALUE_SQL语句需要保证在一个connection里完成执行
    		con = DataSourceUtils.getConnection(this.ffsDataSource);
    		stmt = con.createStatement();
    		DataSourceUtils.applyTransactionTimeout(stmt, this.ffsDataSource);
    		stmt.executeUpdate(String.format(UPDATE_VALUE_SQL, this.cacheSize, seqType.name(), fmtDay, companyCode));
    		rs = stmt.executeQuery(VALUE_SQL);
    		if (!rs.next()) {
    		    throw new DataAccessResourceFailureException("Get last_insert_id() failed after executing an update");
    		}
    		this.maxValue = rs.getLong(1);
    		this.nextValue = this.maxValue - this.cacheSize + 1;
    	    } catch (SQLException ex) {
    		throw new DataAccessResourceFailureException("Could not obtain last_insert_id()", ex);
    	    } finally {
    		JdbcUtils.closeResultSet(rs);
    		JdbcUtils.closeStatement(stmt);
    		DataSourceUtils.releaseConnection(con, this.ffsDataSource);
    	    }
    	} else {
    	    this.nextValue ++;
    	}
    	return this.nextValue;
    }
    
    public DataSource getFfsDataSource() {
        return ffsDataSource;
    }

    public void setFfsDataSource(DataSource ffsDataSource) {
        this.ffsDataSource = ffsDataSource;
    }

    public void setCacheSize(int cacheSize) {
        this.cacheSize = cacheSize;
    }
}
