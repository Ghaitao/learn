<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jspi/tag.jsp"%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
    <!--<![endif]-->
    <!--<![endif]-->
    <head>
        <%@ include file="/jspi/meta.jsp"%>
        <title><spring:message code="com.jinzhiyi.ffs.message.fileMeta.title"/></title>
        <%@ include file="/jspi/metronic_base_css.jsp"%>
	</head>
	<body class="page-content-white page-header-fixed">
		<%@ include file="/WEB-INF/jsp/common/header.jsp"%>
        <div class="page-container">
            <div class="page-content-wrapper">
                <div class="page-content">
	                <div class="container">
	                	<div class="portlet light bordered">
                            <div class="portlet-title">
                                <div class="caption"><i class="icon-user"></i><spring:message code="com.jinzhiyi.ffs.message.fileMeta.panel。query.title"/></div>
                                <div class="actions">
									</div>
                            </div>
                            <div class="portlet-body">
								<div class="row">
									<form role="form" class="form-inline" action="${pageContext.request.contextPath}/sys/fileMeta/fileMetaQuery.do" id="frmApplyQry">
										<div class="col-lg-12">
											<div class="form-group">
												<input type="text" class="form-control input-small disable-ime text-lowercase" id="origFileName" name="origFileName" value="${queryDto.origFileName }" placeholder="<spring:message code="com.jinzhiyi.ffs.message.fileMeta.orig_file_name"/>">
												<input type="text" class="form-control input-small disable-ime text-uppercase" id="companyCode" name="companyCode" value="${queryDto.companyCode }" placeholder="<spring:message code="com.jinzhiyi.ffs.message.fileMeta.company_code"/>">
												<input type="text" class="form-control input-small disable-ime text-uppercase" id="customerCode" name="correlatedCustomerCode" value="${queryDto.correlatedCustomerCode }" placeholder="<spring:message code="com.jinzhiyi.ffs.message.fileMeta.correlated_customer_code"/>">
												<button type="submit" class="btn btn-primary red margin-left10 loading-state-btn" id="btnSubmit" data-loading-text="<spring:message code="com.jinzhiyi.ffs.message.common.btn.searching"/>" autocomplete="off"><i class="fa fa-search"></i> <spring:message code="com.jinzhiyi.ffs.message.common.btn.search"/></button>
											</div>
										</div>
									</form>
								</div>
								<div class="row margin-top20">
									<div class="col-lg-12">
										<c:if test="${result != null && (not empty result)}">
                                        <table class="table table-bordered table-striped table-hover">
                                            <thead>
										        <tr>
										          	<th><spring:message code="com.jinzhiyi.ffs.message.fileMeta.orig_file_name"/><!-- 文件名 --></th>
										          	<th><spring:message code="com.jinzhiyi.ffs.message.fileMeta.file_id"/><!-- 文件路径 --></th>
										          	<%-- <th><spring:message code="com.jinzhiyi.ffs.message.fileMeta.upload_file_sld_name"/><!-- 二级域名 --></th>
										          	<th><spring:message code="com.jinzhiyi.ffs.message.fileMeta.upload_dir_path"/><!-- 存储目录 --></th>
										          	<th><spring:message code="com.jinzhiyi.ffs.message.fileMeta.file_desc"/><!-- 文件描述 --></th> --%>
										          	<th width="75"><spring:message code="com.jinzhiyi.ffs.message.fileMeta.file_size"/><!-- 文件大小 --></th>
										          	<th><spring:message code="com.jinzhiyi.ffs.message.fileMeta.storage_medium"/><!-- 存储媒介 --></th>
										          	<th width="75"><spring:message code="com.jinzhiyi.ffs.message.fileMeta.company_code"/><!-- 所属公司 --></th>
										          	<th width="75"><spring:message code="com.jinzhiyi.ffs.message.fileMeta.correlated_customer_code"/><!-- 所属客户 --></th>
										          	<th width="60"><spring:message code="com.jinzhiyi.ffs.message.common.creator"/><!-- 创建人 --></th>
										          	<th width="155"><spring:message code="com.jinzhiyi.ffs.message.common.create_date_time"/><!-- 创建时间 --></th>
										        </tr>
										    </thead>
										    <tbody>
										       	<c:forEach items="${result}" var="result">
									          	<tr>
										            <td><a href="${pageContext.request.contextPath}/sys/fileMeta/fileMetaDetail.do?recId=${result.fileMetaId }">${result.origFileName }</a></td>
										            <td>${result.fileId}</td>
										            <%-- <td>${result.uploadDirPath}</td>
										            <td>${result.uploadFileSldName}</td>
										            <td>${result.fileDesc}</td> --%>
										            <td>${result.fileSize}</td>
										            <td>${result.storageMedium}</td>
										            <td>${result.companyCode}</td>
										            <td>${result.correlatedCustomerCode}</td>
										            <td><ffs:user userId="${result.creator}" /></td>
										            <td><fmt:formatDate value="${result.createDateTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
									         	</tr>							  
										        </c:forEach>
										    </tbody>
                                        </table>
                                        <div class="clearfix text-right" style="margin-top:10px;">
											<framework:pagination />
										</div>
										</c:if>
                                    </div>
                               	</div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
         <%@ include file="/jspi/metronic_base_js.jsp"%>
		<script type="text/javascript">
		$(function(){
		});
		</script>
	<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>
    </body>
</html>