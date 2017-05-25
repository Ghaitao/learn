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
        <title><spring:message code="com.jinzhiyi.ffs.message.role.title"/></title>
        <%@ include file="/jspi/metronic_base_css.jsp"%>
   </head>
    <body class="page-sidebar-closed-hide-logo page-content-white page-header-fixed">
		<%@ include file="/WEB-INF/jsp/common/header.jsp"%>
     
        <div class="page-container">
            <div class="page-content-wrapper">
                <div class="page-content">
	                <div class="container">
	                	<div class="portlet light bordered">
	                        <div class="portlet-title">
	                            <div class="caption"><i class="icon-user"></i><spring:message code="com.jinzhiyi.ffs.message.role.panel.query.title" /></div>
	                            <div class="actions">
									<a href="${pageContext.request.contextPath}/sys/role/preRoleCreate.do" class="btn btn-default btn-sm"> <i class="fa fa-plus-square-o"></i> <spring:message code="com.jinzhiyi.ffs.message.role.panel.create.title" /></a>
								</div>
	                        </div>
	                        <div class="portlet-body">
								<div class="row">
									<form role="form" class="form-inline" action="${pageContext.request.contextPath}/sys/role/roleQuery.do" id="queryRoleForm" method="post">
										<div class="col-lg-12">
											<div class="form-group">
												<%-- <input type="text" class="form-control input-medium disable-ime" name="name" id="name" value="${sessionScope.role.name}" placeholder="中文/英文名称"> --%>
												<input type="text" class="form-control input-medium disable-ime" name="name" id="name" value="${role.name}" placeholder='<spring:message code="com.jinzhiyi.ffs.message.role.role_name" />'>
												<button type="submit" class="btn btn-primary red margin-left10 loading-state-btn" id="query_submit" data-loading-text='<spring:message code="com.jinzhiyi.ffs.message.common.btn.searching" />'><i class="fa fa-search"></i> <spring:message code="com.jinzhiyi.ffs.message.common.btn.search" /></button>
											</div>
										</div>
									</form>
								</div>
								<div class="row margin-top20">
									<div class="col-lg-12">
		                                <c:if test="${listRoles != null && (not empty listRoles)}">
		                                <table class="table table-bordered table-striped table-hover">
		                                    <thead>
										        <tr>
										        	<th width="80"><spring:message code="com.jinzhiyi.ffs.message.role.role_roleId" /></th>
										          	<th><spring:message code="com.jinzhiyi.ffs.message.role.role_name_cn" /></th>
										          	<th><spring:message code="com.jinzhiyi.ffs.message.role.role_name_en" /></th>
										          	<th><spring:message code="com.jinzhiyi.ffs.message.role.role_name_en" /></th>
										        </tr>
										    </thead>
										    <tbody>
										    	<c:forEach items="${listRoles}" var="role">
										    	<tr>
										    		<td><a href="${pageContext.request.contextPath}/sys/role/preRoleModify.do?roleId=${role.recId}">${role.recId}</a></td>
										    		<td>${role.nameCn}</td>
										    		<td>${role.nameEn}</td>
										    		<td>${role.nameEn}</td>
										    	</tr>
										    	</c:forEach>
										    </tbody>
		                                </table>
		                                </c:if>
		                            </div>
	                           	</div>
	                        </div>
	                    </div>
	                </div>
	            </div>
            </div>
        </div>
        
		<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>
   		<%@ include file="/jspi/metronic_base_js.jsp"%>
		<script type="text/javascript">   
			$(function(){     
				
			});
		</script>
    </body>
</html>