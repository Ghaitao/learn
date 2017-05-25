package com.ght.learn.ffs.tool.timertask;

import framework.dao.jdbc.SqlExecution;
import framework.monitor.error.ThrowableMonitor;

/**
 * 删除通知
 * @author ght
 *
 */
public class HistoryNotificationsCleaner {

	/**
     * 删除30天之前的通知
     * 
     * 解析:
     *  MySQL DATE_ADD() 函数向日期添加指定的时间间隔。
     * 	MySQL interval()函数
     *  例子:INTERVAL(N,N1,N2,N3,..........)
     *  INTERVAL()函数进行比较列表(N1，N2，N3等等)中的N值。该函数如果N<N1返回0，如果N<N2返回1，如果N<N3返回2 等等。如果N为NULL，它将返回-1。列表值必须是N1<N2<N3的形式才能正常工作。下面的代码是显示 INTERVAL()
     *  函数如何工作的一个简单的例子：
     *  	mysql>SELECT INTERVAL(6,1,2,3,4,5,6,7,8,9,10);
	 *		+---------------------------------------------------------+
	 *		| INTERVAL(6,1,2,3,4,5,6,7,8,9,10)                        |
	 *		+---------------------------------------------------------+
	 *		| 6                                                       |
	 *		+---------------------------------------------------------+
	 *		1 row in set (0.00 sec)
	 *		请记住，6是从零开始的索引，列表第一个值的值大于N. 在我们的例子中，7是错误的值，它是位于第六索引插槽
     */
    private static final String DELETE_SQL = "delete from t_notification where CREATE_DATE_TIME < date_add(now(), interval 30 day)";
    
    private SqlExecution sqlExecution;

    private ThrowableMonitor throwableMonitor;

    public void cleanHistoryNotifications() {
	try {
	    int count = this.sqlExecution.executeSql(DELETE_SQL);
	    this.throwableMonitor.monitor(String.format("Deleted %s rows of t_notification", count));
	} catch (Throwable e) {
	    this.throwableMonitor.monitor(e);
	}
    }

    public void setSqlExecution(SqlExecution sqlExecution) {
        this.sqlExecution = sqlExecution;
    }

    public void setThrowableMonitor(ThrowableMonitor throwableMonitor) {
        this.throwableMonitor = throwableMonitor;
    }
}
