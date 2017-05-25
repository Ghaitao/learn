<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jspi/tag.jsp"%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
    <!--<![endif]-->
    <head>
        <%@ include file="/jspi/meta.jsp"%>
        <title><spring:message code="com.jinzhiyi.ffs.message.customer_audit_log.panel.title"/></title>
        <%@ include file="/jspi/metronic_base_css.jsp"%>
   </head>
    <body class="page-content-white">
        <table class="table table-bordered table-striped table-hover">
            <thead>
			   <tr>
			   	<th width="100" class="text-center"><spring:message code="com.jinzhiyi.ffs.message.customer_audit_log.action"/></th>
			     <th width="80" class="text-center"><spring:message code="com.jinzhiyi.ffs.message.customer_audit_log.operator"/></th>
			     <th width="200" class="text-center"><spring:message code="com.jinzhiyi.ffs.message.customer_audit_log.operate_date_time"/></th>
			     <th class="text-center"><spring:message code="com.jinzhiyi.ffs.message.customer_audit_log.remark"/></th>
			   </tr>
			 </thead>
			 <tbody>
			 	<c:forEach items="${customerAuditLogs}" var="customerAuditLog">
			<tr>
				<td class="text-center"><span class="${customerAuditLog.auditAction.cssClass}"><ffs:enum declaringClassName="${customerAuditLog.auditAction.declaringClass.name}" name="${customerAuditLog.auditAction}"/></span></td>
				<td class="text-center"><ffs:user userId="${customerAuditLog.creator}"/></td>
				<td class="text-center"><fmt:formatDate value="${customerAuditLog.createDateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>${customerAuditLog.remark}</td>
			</tr>
			</c:forEach>
		</table>
    </body>
</html>