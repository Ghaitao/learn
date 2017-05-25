package com.ght.learn.ffs.tool.sequence.support;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.ght.learn.ffs.entity.task.Sequence;
import com.ght.learn.ffs.service.base.CompanyQueryService;

import framework.core.utils.CollectionUtils;
import framework.core.utils.StringUtils;
import framework.dao.jdbc.SqlExecution;
import framework.monitor.error.ThrowableMonitor;

/**
 * 基于表t_sequence生成 <序列号>,每天从1开始
 * @author ght
 *
 */
public class SequenceTableSerialNoInitor {

	private static final String SELECT_VALUE_SQL="select s.CURRENT_VALUE from t_sequence s where s.SEQ_TYPE='%s' and s.FMT_DAY='%s' and s.COMPANY_CODE='%s'";
	private static final String INSERT_VALUE_SQL = "insert into t_sequence(SEQ_TYPE, CURRENT_VALUE, FMT_DAY, COMPANY_CODE) values('%s', 0, '%s', '%s')";
    private static final String DELETE_VALUE_SQL = "delete from t_sequence where SEQ_TYPE='%s' and FMT_DAY like '%s%%'";
    /**
     * java8新特性,  DateTimeFormatter类用于将字符串解析为日期
     */
    private static DateTimeFormatter YEARMONTH_FORMATTER = DateTimeFormatter.ofPattern("yyyyMM");
    /**
     * SQL操作接口
     */
    private SqlExecution sqlExecution;
    
    private ThrowableMonitor throwableMonitor;
    
    private CompanyQueryService companyQueryService;
    
    private void doGenerateSequenceMonthly(int startDay, Sequence.SeqType seqType, LocalDate localDate, String companyCode) {
    	int lengthOfMonth = localDate.lengthOfMonth();
    	String fmtYearMonth = localDate.format(YEARMONTH_FORMATTER);
    	ArrayList<String> sqls = new ArrayList<>(lengthOfMonth);
    	for (int i = startDay; i <= lengthOfMonth; i ++) {
    	    sqls.add(String.format(INSERT_VALUE_SQL, seqType.name(), fmtYearMonth + StringUtils.leftPad(String.valueOf(i), 2, "0"), companyCode));
    	}
    	try {
    	    if (sqls.size() > 0) {
    		this.sqlExecution.executeBatchSql(sqls);
    		this.throwableMonitor.monitor(String.format("Generate %s sequence of seqType='%s' and yearMonth='%s' and companyCode='%s'", (lengthOfMonth - startDay + 1), seqType, fmtYearMonth, companyCode));
    	    }
    	} catch (Throwable e) {
    	    this.throwableMonitor.monitor(e);
    	}
   }
    /**
     * 每月生成本月和下月的序列号值，并删除上个月的序列号
     */
    public void generateSequenceMonthly() {
		//List<Object> companyCodes = this.sqlExecution.queryForListBySql(SELECT_COMPANY_CODES_SQL, new Object[] {Company.ADMIN_COMPANY_CODE});
		List<String> companyCodes = this.companyQueryService.queryValidCompanyCodes();
		if (CollectionUtils.isEmpty(companyCodes)) {
		    return;
		}
		/**
		 * ght:LocalDate java8新特性 http://www.liaoxuefeng.com/article/00141939241051502ada88137694b62bfe844cd79e12c32000
		 */
		LocalDate currentMonth = this.sqlExecution.getDbCurrentTimestamp().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		for (Sequence.SeqType seqType : Sequence.SeqType.values()) {
			    for (String companyCode : companyCodes) {
					int today = currentMonth.getDayOfMonth();//获取月的具体哪一天
					// 查看当月是否有数据，没有则生成
					Integer sequenceValue = this.sqlExecution.queryForUniqueObjectBySql(Integer.class, String.format(SELECT_VALUE_SQL, seqType.name(),
						currentMonth.format(YEARMONTH_FORMATTER) + StringUtils.leftPad(String.valueOf(today), 2, "0"), companyCode));
					if (sequenceValue == null) {
					    doGenerateSequenceMonthly(today, seqType, currentMonth, companyCode);
					}
					// 查看下月是否有数据，没有则生成
					LocalDate nextMonth = currentMonth.plusMonths(1);
					sequenceValue = this.sqlExecution.queryForUniqueObjectBySql(Integer.class, String.format(SELECT_VALUE_SQL, seqType.name(),
						nextMonth.format(YEARMONTH_FORMATTER) + StringUtils.leftPad(String.valueOf(1), 2, "0"), companyCode));
					if (sequenceValue == null) {
					    doGenerateSequenceMonthly(1, seqType, nextMonth, companyCode);
					}
			    }
	    //删除上月数据
	    LocalDate previousMonth = currentMonth.plusMonths(-1);
	    String deleteSql = String.format(DELETE_VALUE_SQL, seqType.name(), previousMonth.format(YEARMONTH_FORMATTER));
	    int deleteCount = this.sqlExecution.executeSql(deleteSql);
	    this.throwableMonitor.monitor(String.format("Delete %s sequence when executed delete sql: %s", deleteCount, deleteSql));
		}
    }
    
    
    public void setSqlExecution(SqlExecution sqlExecution) {
        this.sqlExecution = sqlExecution;
    }

    public void setThrowableMonitor(ThrowableMonitor throwableMonitor) {
        this.throwableMonitor = throwableMonitor;
    }

    public void setCompanyQueryService(CompanyQueryService companyQueryService) {
        this.companyQueryService = companyQueryService;
    }
}
