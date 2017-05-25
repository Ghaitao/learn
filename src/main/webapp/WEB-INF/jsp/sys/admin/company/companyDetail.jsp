<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jspi/tag.jsp"%>
<!-- 导入functions 使用 substringBefore 和 substringAfter 完成字符串的拆分 -->
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
    <!--<![endif]-->
    <head>
        <%@ include file="/jspi/meta.jsp"%>
        <title><spring:message code="com.jinzhiyi.ffs.message.company.title"/></title>
        <%@ include file="/jspi/metronic_base_css.jsp"%>
   </head>
    <body class="page-sidebar-closed-hide-logo page-content-white page-header-fixed">
		<%@ include file="/WEB-INF/jsp/common/header.jsp"%>
     	 
     	<div class="page-container">
            <div class="page-content-wrapper">
                <div class="page-content">
	                <div class="container">
						<div class="row">
			                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
		                        <div class="portlet light bordered">
                                    <div class="portlet-title">
                                        <div class="caption"><i class="icon-note"></i><spring:message code="com.jinzhiyi.ffs.message.company.panel.detail.title" /></div>
                                    </div>
                                    <div class="portlet-body form">
                                        <form action="" class="form-horizontal" id="companyDetailForm">
                                            <div class="form-body">
                                            	<h4 class="form-section"><spring:message code="com.jinzhiyi.ffs.message.company.panel.info" /></h4>
                                            	<div class="row">
                                            		<div class="col-lg-4 col-xs-4 col-md-4">
														<div class="form-group">
															<label class="control-label col-lg-4 col-xs-4 col-md-4"><spring:message code="com.jinzhiyi.ffs.message.company.company_code" />：</label>
															<div class="col-lg-8 col-xs-8 col-md-8">
																<p class="form-control-static">${company.companyCode}</p>
															</div>
														</div>
													</div>
													<div class="col-lg-4 col-xs-4 col-md-4">
                                                        <div class="form-group">
                                                            <label class="control-label col-lg-4 col-xs-4 col-md-4"><spring:message code="com.jinzhiyi.ffs.message.company.company_name_cn" />：</label>
                                                            <div class="col-lg-8 col-xs-8 col-md-8">
																<p class="form-control-static">${company.nameCn}</p>
															</div>
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-4 col-xs-4 col-md-4">
                                                        <div class="form-group">
                                                            <label class="control-label col-lg-4 col-xs-4 col-md-4"><spring:message code="com.jinzhiyi.ffs.message.company.company_name_en" />：</label>
                                                            <div class="col-lg-8 col-xs-8 col-md-8">
																<p class="form-control-static">${company.nameEn}</p>
															</div>
                                                        </div>
                                                    </div>
												</div>
												<div class="row">
                                            		<div class="col-lg-4 col-xs-4 col-md-4">
														<div class="form-group">
															<label class="control-label col-lg-4 col-xs-4 col-md-4"><spring:message code="com.jinzhiyi.ffs.message.company.company_city_code" />：</label>
															<div class="col-lg-8 col-xs-8 col-md-8">
																<p class="form-control-static">${company.cityCode}</p>
															</div>
														</div>
													</div>
													<div class="col-lg-4 col-xs-4 col-md-4">
                                                        <div class="form-group">
                                                            <label class="control-label col-lg-4 col-xs-4 col-md-4"><spring:message code="com.jinzhiyi.ffs.message.company.company_airport_code" />：</label>
                                                            <div class="col-lg-8 col-xs-8 col-md-8">
																<p class="form-control-static">${company.airportCode}</p>
															</div>
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-4 col-xs-4 col-md-4">
                                                        <div class="form-group">
                                                            <label class="control-label col-lg-4 col-xs-4 col-md-4"><spring:message code="com.jinzhiyi.ffs.message.company.company_rightCopy" />：</label>
                                                            <div class="col-lg-8 col-xs-8 col-md-8">
																<p class="form-control-static">${company.copyRight}</p>
															</div>
                                                        </div>
                                                    </div>
												</div>
												<div class="row">
                                            		<div class="col-lg-4 col-xs-4 col-md-4">
														<div class="form-group">
															<label class="control-label col-lg-4 col-xs-4 col-md-4"><spring:message code="com.jinzhiyi.ffs.message.company.company_customerService_phone" />：</label>
															<div class="col-lg-8 col-xs-8 col-md-8">
																<p class="form-control-static">${company.customerServicePhone}</p>
															</div>
														</div>
													</div>
													<div class="col-lg-4 col-xs-4 col-md-4">
                                                        <div class="form-group">
                                                            <label class="control-label col-lg-4 col-xs-4 col-md-4"><spring:message code="com.jinzhiyi.ffs.message.company.company_customerService_email" />：</label>
                                                            <div class="col-lg-8 col-xs-8 col-md-8">
																<p class="form-control-static">${company.customerServiceEmail}</p>
															</div>
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-4 col-xs-4 col-md-4">
                                                        <div class="form-group">
                                                            <label class="control-label text-nowrap col-lg-4 col-xs-4 col-md-4"><spring:message code="com.jinzhiyi.ffs.message.company.upload_file_sld_name" />：</label>
                                                            <div class="col-lg-8 col-xs-8 col-md-8">
																<p class="form-control-static">${company.uploadFileSldName}</p>
															</div>
                                                        </div>
                                                    </div>
												</div>
												<div class="row">
													<div class="col-lg-4 col-xs-4 col-md-4">
                                                        <div class="form-group">
                                                            <label class="control-label text-nowrap col-lg-4 col-xs-4 col-md-4"><spring:message code="com.jinzhiyi.ffs.message.company.upload_dir_path" />：</label>
                                                            <div class="col-lg-8 col-xs-8 col-md-8">
																<p class="form-control-static">${company.uploadDirPath}</p>
															</div>
                                                        </div>
                                                    </div>
												</div>
												 <div class="row">
                                                    <div class="col-lg-8 col-xs-8 col-md-8">
                                                        <div class="form-group">
                                                            <label class="control-label col-lg-2 col-xs-2 col-md-2"><spring:message code="com.jinzhiyi.ffs.message.company.company_role" />：</label>
															<div class="col-lg-10 col-xs-10 col-md-10">
																<p class="form-control-static">${company.recId}<%-- <ffs:role companyId="${company.recId}" /> --%></p>
															</div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                	<div class="col-lg-4 col-xs-4 col-md-4">
                                                        <div class="form-group">
                                                            <label class="control-label text-nowrap col-lg-4 col-xs-4 col-md-4"><spring:message code="com.jinzhiyi.ffs.message.company.address" />：</label>
                                                            <div class="col-lg-8 col-xs-8 col-md-8">
																<p class="form-control-static">${company.address}</p>
															</div>
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-4 col-xs-4 col-md-4">
                                                        <div class="form-group">
                                                            <label class="control-label text-nowrap col-lg-4 col-xs-4 col-md-4"><spring:message code="com.jinzhiyi.ffs.message.company.postcode" />：</label>
                                                            <div class="col-lg-8 col-xs-8 col-md-8">
																<p class="form-control-static">${company.postcode}</p>
															</div>
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-4 col-xs-4 col-md-4">
                                                        <div class="form-group">
                                                            <label class="control-label text-nowrap col-lg-4 col-xs-4 col-md-4"><spring:message code="com.jinzhiyi.ffs.message.company.contactorName" />：</label>
                                                            <div class="col-lg-8 col-xs-8 col-md-8">
																<p class="form-control-static">${company.contactorName}</p>
															</div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                	<div class="col-lg-4 col-xs-4 col-md-4">
                                                        <div class="form-group">
                                                            <label class="control-label text-nowrap col-lg-4 col-xs-4 col-md-4"><spring:message code="com.jinzhiyi.ffs.message.company.contactorPhone" />：</label>
                                                            <div class="col-lg-8 col-xs-8 col-md-8">
																<p class="form-control-static">${company.contactorPhone}</p>
															</div>
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-4 col-xs-4 col-md-4">
                                                        <div class="form-group">
                                                            <label class="control-label text-nowrap col-lg-4 col-xs-4 col-md-4"><spring:message code="com.jinzhiyi.ffs.message.company.contactorFax" />：</label>
                                                            <div class="col-lg-8 col-xs-8 col-md-8">
																<p class="form-control-static">${company.contactorFax}</p>
															</div>
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-4 col-xs-4 col-md-4">
                                                        <div class="form-group">
                                                            <label class="control-label text-nowrap col-lg-4 col-xs-4 col-md-4"><spring:message code="com.jinzhiyi.ffs.message.company.contactorEmail" />：</label>
                                                            <div class="col-lg-8 col-xs-8 col-md-8">
																<p class="form-control-static">${company.contactorEmail}</p>
															</div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-lg-4 col-xs-4 col-md-4">
                                                        <div class="form-group">
                                                            <label class="control-label col-lg-4 col-xs-4 col-md-4"><spring:message code="com.jinzhiyi.ffs.message.company.company_start_dateTime" />：</label>
                                                            <div class="col-lg-8 col-xs-8 col-md-8">
																<p class="form-control-static"><fmt:formatDate value="${company.startDateTime}" pattern="yyyy-MM-dd HH:mm:ss" /></p>
															</div>
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-4 col-xs-4 col-md-4">
                                                        <div class="form-group">
                                                            <label class="control-label col-lg-4 col-xs-4 col-md-4"><spring:message code="com.jinzhiyi.ffs.message.company.company_expiry_dateTime" />：</label>
                                                            <div class="col-lg-8 col-xs-8 col-md-8">
                                                            
																<p class="form-control-static"><fmt:formatDate value="${company.expiryDateTime}" pattern="yyyy-MM-dd HH:mm:ss" /></p>
															</div>
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-4 col-xs-4 col-md-4">
                                                        <div class="form-group">
                                                            <label class="control-label col-lg-4 col-xs-4 col-md-4"><spring:message code="com.jinzhiyi.ffs.message.common.status"/>：</label>
                                                            <div class="col-lg-8 col-xs-8 col-md-8">
																<p class="form-control-static">
																<c:if test="${!company.locked}">
																	<span class="label label-success"><ffs:enum declaringClassName="${company.status.declaringClass.name}" name="${company.status}" /></span>
																</c:if>
																<c:if test="${company.locked}">
																	<span class="label label-default"><ffs:enum declaringClassName="${company.status.declaringClass.name}" name="${company.status}" /></span>
																</c:if>
																</p>
															</div>
                                                        </div>
                                                    </div>
												</div>
												<div class="row">
													<div class="col-lg-4 col-xs-4 col-md-4">
                                                        <div class="form-group">
                                                            <label class="control-label col-lg-4 col-xs-4 col-md-4"><spring:message code="com.jinzhiyi.ffs.message.common.creator"/>：</label>
                                                            <div class="col-lg-8 col-xs-8 col-md-8">
																<p class="form-control-static"><ffs:user userId="${company.creator}" /></p>
															</div>
                                                        </div>
                                                    </div>
                                            		<div class="col-lg-4 col-xs-4 col-md-4">
														<div class="form-group">
															<label class="control-label col-lg-4 col-xs-4 col-md-4"><spring:message code="com.jinzhiyi.ffs.message.common.create_date_time"/>：</label>
															<div class="col-lg-8 col-xs-8 col-md-8">
																<p class="form-control-static"><fmt:formatDate value="${company.createDateTime}" pattern="yyyy-MM-dd HH:mm:ss" /></p>
															</div>
														</div>
													</div>
												</div>
                                                <div class="row">
													<div class="col-lg-4 col-xs-4 col-md-4">
                                                        <div class="form-group">
                                                            <label class="control-label col-lg-4 col-xs-4 col-md-4"><spring:message code="com.jinzhiyi.ffs.message.common.modifier"/>：</label>
                                                            <div class="col-lg-8 col-xs-8 col-md-8">
																<p class="form-control-static"><ffs:user userId="${company.modifier}" /></p>
															</div>
                                                        </div>
                                                    </div>
                                            		<div class="col-lg-4 col-xs-4 col-md-4">
														<div class="form-group">
															<label class="control-label col-lg-4 col-xs-4 col-md-4"><spring:message code="com.jinzhiyi.ffs.message.common.modify_date_time"/>：</label>
															<div class="col-lg-8 col-xs-8 col-md-8">
																<p class="form-control-static"><fmt:formatDate value="${company.modifyDateTime}" pattern="yyyy-MM-dd HH:mm:ss" /></p>
															</div>
														</div>
													</div>
												</div>
                                               	<h4 class="form-section"><spring:message code="com.jinzhiyi.ffs.message.company.panel.file" /></h4>
                                               	<div class="row">
                                               		<div class="col-lg-6 col-xs-6 col-md-6">
														<div class="form-group">
															<label class="control-label text-nowrap col-lg-4 col-xs-4 col-md-4"><spring:message code="com.jinzhiyi.ffs.message.company.company_faviconFileMetaId" />：</label>
															<div class="col-lg-8 col-xs-8 col-md-8">
																<img src="<ffs:fileMetaImageUrl fileMetaId="${company.faviconFileMetaId}" showDefaultImage="false"/>" 
																	title="<spring:message code="com.jinzhiyi.ffs.message.company.company_faviconFileMetaId" />" style="width: 20px;height: 20px;"/>
															</div>
														</div>
													</div>
													<div class="col-lg-6 col-xs-6 col-md-6">
														<div class="form-group">
															<label class="control-label text-nowrap col-lg-4 col-xs-4 col-md-4"><spring:message code="com.jinzhiyi.ffs.message.company.company_businessLogoFileMetaId" />：</label>
															<div class="col-lg-8 col-xs-8 col-md-8">
																<img src="<ffs:fileMetaImageUrl fileMetaId="${company.businessLogoFileMetaId}" showDefaultImage="false"/>" 
																	title="<spring:message code="com.jinzhiyi.ffs.message.company.company_businessLogoFileMetaId" />"/>
															</div>
														</div>
													</div>
                                                </div>
                                                <div class="row">
                                                	<div class="col-lg-6 col-xs-6 col-md-6">
														<div class="form-group">
															<label class="control-label text-nowrap col-lg-4 col-xs-4 col-md-4"><spring:message code="com.jinzhiyi.ffs.message.company.company_textLogoCnFileMetaId" />：</label>
															<div class="col-lg-8 col-xs-8 col-md-8">
																<img src="<ffs:fileMetaImageUrl fileMetaId="${company.textLogoCnFileMetaId}" showDefaultImage="false"/>" 
																	title="<spring:message code="com.jinzhiyi.ffs.message.company.company_textLogoCnFileMetaId" />"/>
															</div>
														</div>
													</div>
                                                	<div class="col-lg-6 col-xs-6 col-md-6">
														<div class="form-group">
															<label class="control-label text-nowrap col-lg-4 col-xs-4 col-md-4"><spring:message code="com.jinzhiyi.ffs.message.company.company_textLogoBigCnFileMetaId" />：</label>
															<div class="col-lg-8 col-xs-8 col-md-8">
																<img src="<ffs:fileMetaImageUrl fileMetaId="${company.textLogoBigCnFileMetaId}" showDefaultImage="false"/>" 
																	title="<spring:message code="com.jinzhiyi.ffs.message.company.company_textLogoBigCnFileMetaId" />"/>
															</div>
														</div>
													</div>
                                                </div>
                                                <div class="row">
                                                	<div class="col-lg-6 col-xs-6 col-md-6">
														<div class="form-group">
															<label class="control-label text-nowrap col-lg-4 col-xs-4 col-md-4"><spring:message code="com.jinzhiyi.ffs.message.company.company_textLogoEnFileMetaId" />：</label>
															<div class="col-lg-8 col-xs-8 col-md-8">
																<img src="<ffs:fileMetaImageUrl fileMetaId="${company.textLogoEnFileMetaId}" showDefaultImage="false"/>" 
																	title="<spring:message code="com.jinzhiyi.ffs.message.company.company_textLogoEnFileMetaId" />"/>
															</div>
														</div>
													</div>
													<div class="col-lg-6 col-xs-6 col-md-6">
														<div class="form-group">
															<label class="control-label text-nowrap col-lg-4 col-xs-4 col-md-4"><spring:message code="com.jinzhiyi.ffs.message.company.company_textLogoBigEnFileMetaId" />：</label>
															<div class="col-lg-8 col-xs-8 col-md-8">
																<img src="<ffs:fileMetaImageUrl fileMetaId="${company.textLogoBigEnFileMetaId}" showDefaultImage="false"/>" 
																	title="<spring:message code="com.jinzhiyi.ffs.message.company.company_textLogoBigEnFileMetaId" />"/>
															</div>
														</div>
													</div>
                                                </div>
                                           	</div>
                                            <div class="form-actions">
                                                <div class="row">
                                                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center">
                                                    	<a href="${pageContext.request.contextPath}/sys/admin/company/preCompanyModify.do?recId=${company.recId}" class="btn red"><i class="fa fa-edit"></i> <spring:message code="com.jinzhiyi.ffs.message.common.btn.edit" /></a>
                                                    	<c:if test="${company.locked}">
                                                    	<a href="${pageContext.request.contextPath}/sys/admin/company/companyNormal.do?recId=${company.recId}" class="btn btn-default margin-left10" id="btn_unlock"><i class="icon-lock-open"></i> <spring:message code="com.jinzhiyi.ffs.message.common.btn.normal" /></a>
                                                    	</c:if>
                                                    	<c:if test="${!company.locked}">
                                                    	<a href="${pageContext.request.contextPath}/sys/admin/company/companyLocked.do?recId=${company.recId}" class="btn btn-default margin-left10" id="btn_lock"><i class="icon-lock"></i> <spring:message code="com.jinzhiyi.ffs.message.common.btn.locked" /></a>
                                                    	</c:if>
                                                		<a href="../../customer/customerQuery.html" class="btn btn-default margin-left10"><i class="icon-user"></i> 客户管理</a>
                                                		<a href="${pageContext.request.contextPath}/sys/admin/userCompany/userCompanyQuery.do?companyId=${company.recId}" class="btn btn-default margin-left10"><i class="icon-user"></i> 用户管理</a>
                                                		<a class="btn btn-default margin-left10" href="${pageContext.request.contextPath}/sys/admin/company/companyQuery.do"><i class="icon-share-alt"></i> <spring:message code="com.jinzhiyi.ffs.message.common.btn.return" /></a>
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
        
		<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>
   		<%@ include file="/jspi/metronic_base_js.jsp"%>
   		<script src="${ffsStaticUrl}/metronic_v4.5.6/theme/assets/global/plugins/bootbox/bootbox.min.js" type="text/javascript"></script>
		<script type="text/javascript">   
			$(function(){     
				
			});
		</script>
    </body>
</html>