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
        <title><spring:message code="com.jinzhiyi.ffs.message.user.title"/></title>
        <%@ include file="/jspi/metronic_base_css.jsp"%>
        <link href="${ffsStaticUrl}/metronic_v4.5.6/theme/assets/global/plugins/artDialog4.1.7/skins/default.css" rel="stylesheet" type="text/css" />
	</head>
	<body class="page-content-white page-header-fixed">
		<%@ include file="/WEB-INF/jsp/common/header.jsp"%>
        <div class="page-container">
            <div class="page-content-wrapper">
                <div class="page-content">
	                <div class="container">
						<div class="row">
			                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
		                        <div class="portlet light bordered">
                                    <div class="portlet-title">
                                        <div class="caption"><i class="icon-note"></i><spring:message code="com.jinzhiyi.ffs.message.user.panel.detail.title"/></div>
                                    </div>
                                    <div class="portlet-body form">
                                        <form action="" class="form-horizontal" id="userDetail">
                                            <div class="form-body">
                                            	<h4 class="form-section"><spring:message code="com.jinzhiyi.ffs.message.user.panel.info"/> </h4>
                                            	<div class="row">
                                            		<div class="col-lg-4 col-xs-4 col-md-4">
														<div class="form-group">
															<label class="control-label col-lg-4 col-xs-4 col-md-4"><spring:message code="com.jinzhiyi.ffs.message.user.user_code"/>：</label>
															<div class="col-lg-8 col-xs-8 col-md-8">
																<p class="form-control-static">${dto.userCode}</p>
															</div>
														</div>
													</div>
													<div class="col-lg-4 col-xs-4 col-md-4">
                                                        <div class="form-group">
                                                            <label class="control-label col-lg-4 col-xs-4 col-md-4"><spring:message code="com.jinzhiyi.ffs.message.user.user_name_cn"/>：</label>
                                                            <div class="col-lg-8 col-xs-8 col-md-8">
																<p class="form-control-static">${dto.userNameCn}</p>
															</div>
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-4 col-xs-4 col-md-4">
                                                        <div class="form-group">
                                                            <label class="control-label col-lg-4 col-xs-4 col-md-4"><spring:message code="com.jinzhiyi.ffs.message.user.user_name_en"/>：</label>
                                                            <div class="col-lg-8 col-xs-8 col-md-8">
																<p class="form-control-static">${dto.userNameEn}</p>
															</div>
                                                        </div>
                                                    </div>
												</div>
                                                <div class="row">
                                                    <div class="col-lg-4 col-xs-4 col-md-4">
                                                        <div class="form-group">
                                                            <label class="control-label control-label col-lg-4 col-xs-4 col-md-4"><spring:message code="com.jinzhiyi.ffs.message.user.mobile_phone"/>：</label>
                                                            <div class="col-lg-8 col-xs-8 col-md-8">
																<p class="form-control-static">${dto.mobilePhone}</p>
															</div>
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-4 col-xs-4 col-md-4">
                                                        <div class="form-group">
                                                            <label class="control-label col-lg-4 col-xs-4 col-md-4"><spring:message code="com.jinzhiyi.ffs.message.user.telephone"/>：</label>
                                                            <div class="col-lg-8 col-xs-8 col-md-8">
																<p class="form-control-static">${dto.telephone}</p>
															</div>
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-4 col-xs-4 col-md-4">
                                                        <div class="form-group">
                                                            <label class="control-label col-lg-4 col-xs-4 col-md-4"><spring:message code="com.jinzhiyi.ffs.message.user.email"/>：</label>
                                                            <div class="col-lg-8 col-xs-8 col-md-8">
																<p class="form-control-static">${dto.email}</p>
															</div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-lg-4 col-xs-4 col-md-4">
                                                        <div class="form-group">
                                                            <label class="control-label control-label col-lg-4 col-xs-4 col-md-4"><spring:message code="com.jinzhiyi.ffs.message.user.qq"/>：</label>
                                                            <div class="col-lg-8 col-xs-8 col-md-8">
																<p class="form-control-static">${dto.qq}</p>
															</div>
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-4 col-xs-4 col-md-4">
                                                        <div class="form-group">
                                                            <label class="control-label col-lg-4 col-xs-4 col-md-4"><spring:message code="com.jinzhiyi.ffs.message.user.company_code"/>：</label>
                                                            <div class="col-lg-8 col-xs-8 col-md-8">
																<p class="form-control-static"><ffs:company companyCode="${dto.companyCode}" /></p>
															</div>
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-4 col-xs-4 col-md-4">
                                                        <div class="form-group">
                                                            <label class="control-label col-lg-4 col-xs-4 col-md-4"><spring:message code="com.jinzhiyi.ffs.message.user.customer_code"/>：</label>
                                                            <div class="col-lg-8 col-xs-8 col-md-8">
																<p class="form-control-static"><%-- <ffs:customer customerId="${dto.customerId}" /> --%>${dto.customerCode}</p>
															</div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                	<div class="col-lg-8 col-xs-8 col-md-8">
                                                        <div class="form-group">
                                                            <label class="control-label col-lg-2 col-xs-2 col-md-2"><spring:message code="com.jinzhiyi.ffs.message.user.role_code"/>：</label>
                                                            <div class="col-lg-10 col-xs-10 col-md-10">
																<p class="form-control-static"><ffs:role userId="${dto.recId }" /></p>
															</div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                	<div class="col-lg-4 col-xs-4 col-md-4">
                                                        <div class="form-group">
                                                            <label class="control-label col-lg-4 col-xs-4 col-md-4"><spring:message code="com.jinzhiyi.ffs.message.common.status"/>：</label>
                                                            <div class="col-lg-8 col-xs-8 col-md-8">
                                                            	<p class="form-control-static">
																<c:if test="${!result.locked}">
													          		<span class="label label-success"><ffs:enum declaringClassName="${dto.status.declaringClass.name}" name="${dto.status}" /></span>
													          	</c:if>
													          	<c:if test="${result.locked}">
													          		<span class="label label-default"><ffs:enum declaringClassName="${dto.status.declaringClass.name}" name="${dto.status}" /></span>
													          	</c:if>
													          	</p>
															</div>
                                                        </div>
                                                    </div>
													<div class="col-lg-4 col-xs-4 col-md-4">
                                                        <div class="form-group">
                                                            <label class="control-label col-lg-4 col-xs-4 col-md-4"><spring:message code="com.jinzhiyi.ffs.message.common.modifier"/>：</label>
                                                            <div class="col-lg-8 col-xs-8 col-md-8">
																<p class="form-control-static"><ffs:user userId="${dto.modifier}" /></p>
															</div>
                                                        </div>
                                                    </div>
                                            		<div class="col-lg-4 col-xs-4 col-md-4">
														<div class="form-group">
															<label class="control-label col-lg-4 col-xs-4 col-md-4"><spring:message code="com.jinzhiyi.ffs.message.common.modify_date_time"/>：</label>
															<div class="col-lg-8 col-xs-8 col-md-8">
																<p class="form-control-static"><fmt:formatDate value="${dto.modifyDateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></p>
															</div>
														</div>
													</div>
												</div>
												<div class="row">
													<div class="col-lg-4 col-xs-4 col-md-4">
                                                        <div class="form-group">
                                                            <label class="control-label col-lg-4 col-xs-4 col-md-4"><spring:message code="com.jinzhiyi.ffs.message.common.creator"/>：</label>
                                                            <div class="col-lg-8 col-xs-8 col-md-8">
																<p class="form-control-static"><ffs:user userId="${dto.creator}" /></p>
															</div>
                                                        </div>
                                                    </div>
                                            		<div class="col-lg-4 col-xs-4 col-md-4">
														<div class="form-group">
															<label class="control-label col-lg-4 col-xs-4 col-md-4"><spring:message code="com.jinzhiyi.ffs.message.common.create_date_time"/>：</label>
															<div class="col-lg-8 col-xs-8 col-md-8">
																<p class="form-control-static"><fmt:formatDate value="${dto.createDateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></p>
															</div>
														</div>
													</div>
												</div>
                                            </div>
                                            <div class="form-actions">
                                                <div class="row">
                                                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center">
                                                    	<a href="${pageContext.request.contextPath}/sys/admin/userCompany/preUserCompanyModify.do?recId=${dto.recId }" class="btn red"><i class="fa fa-edit"></i> <spring:message code="com.jinzhiyi.ffs.message.common.btn.edit"/></a>
                                                    	<a href="javascript:;" id="btn_delete" class="btn btn-default margin-left10"><i class="glyphicon glyphicon-trash"></i> <spring:message code="com.jinzhiyi.ffs.message.common.btn.delete"/></a>
                                                    	<a href="${pageContext.request.contextPath}/sys/userPerson/preResetPasswordModify.do?recId=${dto.recId }&flag=" class="btn btn-default margin-left10"><i class="fa fa-repeat"></i> <spring:message code="com.jinzhiyi.ffs.message.user.btn.reset_password"/></a>
                                                    	<c:if test="${dto.locked }">
                                                			<a href="${pageContext.request.contextPath}/sys/admin/userCompany/userCompanyNormal.do?recId=${dto.recId }" class="btn btn-default margin-left10" id="btn_unlock"><i class="icon-lock-open"></i> <spring:message code="com.jinzhiyi.ffs.message.common.btn.normal"/></a>
                                                		</c:if>
                                                		<c:if test="${!dto.locked }">
                                                			<a href="${pageContext.request.contextPath}/sys/admin/userCompany/userCompanyLocked.do?recId=${dto.recId }" class="btn btn-default margin-left10" id="btn_lock"><i class="icon-lock"></i> <spring:message code="com.jinzhiyi.ffs.message.common.btn.locked"/></a>
                                                		</c:if>
                                                		<a href="${pageContext.request.contextPath}/sys/admin/userCompany/userCompanyQuery.do?companyId=${dto.companyId }" class="btn btn-default margin-left10" id="btn_return"><i class="icon-share-alt"></i> <spring:message code="com.jinzhiyi.ffs.message.common.btn.return"/></a>
                                                    </div>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>
	                        </div>
		                </div>
		           </div>
                </div>
            </div>
        </div>
        <%@ include file="/jspi/metronic_base_js.jsp"%>
       	<script src="${ffsStaticUrl}/metronic_v4.5.6/theme/assets/global/plugins/artDialog4.1.7/jquery.artDialog.js"></script>
		<script src="${ffsStaticUrl}/metronic_v4.5.6/theme/assets/global/plugins/artDialog4.1.7/plugins/iframeTools.js"></script>
		<script type="text/javascript">
		$(function(){
			$("#btn_delete").click(function() {
				$.dialog.confirm('<spring:message code="com.jinzhiyi.ffs.message.user.prompt_delete"/>', function() {
					window.location.href = '${pageContext.request.contextPath}/sys/admin/userCompany/userCompanyDelete.do?recId=${dto.recId}';
				});
			});
		});
		</script>
	<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>
    </body>
</html>