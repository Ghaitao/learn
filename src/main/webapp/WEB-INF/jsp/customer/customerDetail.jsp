<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/jspi/tag.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<head>
	<%@ include file="/jspi/meta.jsp"%>
    <title><spring:message code="com.jinzhiyi.ffs.message.customer.page.detail.title"/></title>
    <%@ include file="/jspi/metronic_base_css.jsp"%>
    <link rel="stylesheet" href="${ffsStaticUrl}/metronic_v4.5.6/theme/assets/global/plugins/artDialog4.1.7/skins/default.css">
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
	                                                <div class="caption"><i class="icon-docs"></i><spring:message code="com.jinzhiyi.ffs.message.customer.page.detail.panel.title"/> </div>
	                                            </div>
	                                            <div class="portlet-body form">
                                                    <form class="form-horizontal">
                                                    <div class="form-body ">
                                                    	<%@ include file="/WEB-INF/jsp/common/errorMessageTip.jsp" %>
                                                    	<h4 class="form-section"><spring:message code="com.jinzhiyi.ffs.message.customer.audit.subtitle.companyInfomation"/> </h4>
                                                    	<div class="row">
                                                    		<div class="col-lg-4 col-xs-4 col-md-4">
																<div class="form-group">
																	<!-- 客户代码： -->
																	<label class="control-label col-lg-4 col-xs-4 col-md-4"><spring:message code="com.jinzhiyi.ffs.message.customer.audit.filed.customerCode"/>：</label>
																	<div class="col-lg-8 col-xs-8 col-md-8">
																		<p class="form-control-static"><strong>${customer.customerCode }</strong></p>
																	</div>
																</div>
															</div>
															<div class="col-lg-4 col-xs-4 col-md-4">
                                                                <div class="form-group">
                                                                	<!-- 中文名称： -->
                                                                    <label class="control-label col-lg-4 col-xs-4 col-md-4"><spring:message code="com.jinzhiyi.ffs.message.customer.audit.filed.nameCn"/>：</label>
                                                                    <div class="col-lg-8 col-xs-8 col-md-8">
																		<p class="form-control-static">${customer.nameCn }</p>
																	</div>
                                                                </div>
                                                            </div>
                                                            <div class="col-lg-4 col-xs-4 col-md-4">
                                                                <div class="form-group">
                                                                	<!-- 英文名称： -->
                                                                    <label class="control-label col-lg-4 col-xs-4 col-md-4"><spring:message code=""/><spring:message code="com.jinzhiyi.ffs.message.customer.audit.filed.nameEn"/>：</label>
                                                                    <div class="col-lg-8 col-xs-8 col-md-8">
																		<p class="form-control-static">${customer.nameEn }</p>
																	</div>
                                                                </div>
                                                            </div>
														</div>
                                                        <div class="row">
                                                            <div class="col-lg-4 col-xs-4 col-md-4">
                                                                <div class="form-group">
                                                                	<!-- 详细地址： -->
                                                                    <label class="control-label col-lg-4 col-xs-4 col-md-4"><spring:message code="com.jinzhiyi.ffs.message.customer.audit.filed.address"/>：</label>
                                                                    <div class="col-lg-8 col-xs-8 col-md-8">
																		<p class="form-control-static">${customer.address }</p>
																	</div>
                                                                </div>
                                                            </div>
                                                            <div class="col-lg-4 col-xs-4 col-md-4">
                                                                <div class="form-group">
                                                                	<!-- 邮编： -->
                                                                    <label class="control-label col-lg-4 col-xs-4 col-md-4"><spring:message code="com.jinzhiyi.ffs.message.customer.audit.filed.postcode"/>：</label>
                                                                    <div class="col-lg-8 col-xs-8 col-md-8">
																		<p class="form-control-static">${customer.postcode }</p>
																	</div>
                                                                </div>
                                                            </div>
                                                            <c:if test="${customer.customerSourceApply}">
                                                             <div class="col-lg-4 col-xs-4 col-md-4">
                                                                <div class="form-group">
                                                                	<!-- 营业执照： -->
                                                                    <label class="control-label col-lg-4 col-xs-4 col-md-4"><spring:message code="com.jinzhiyi.ffs.message.customer.audit.filed.license"/>：</label>
                                                                    <div class="col-lg-8 col-xs-8 col-md-8">
                                                                    <p class="form-control-static">
                                                                    <c:if test="${customer.licenseFileMetaId!=null }">
																		<a target="_blank" href="<ffs:fileMetaImageUrl fileMetaId="${customer.licenseFileMetaId}" showDefaultImage="false"/>">
																			<spring:message code="com.jinzhiyi.ffs.message.customer.audit.btn.image.view"/>
																		</a>
                                                                    </c:if>
                                                                    </p>
																	</div>
                                                                </div>
                                                            </div>
                                                            </c:if>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-lg-4 col-xs-4 col-md-4">
                                                                <div class="form-group">
                                                                    <label class="control-label col-lg-4 col-xs-4 col-md-4"><spring:message code="com.jinzhiyi.ffs.message.customer.filed.contactorName"/>：</label>
                                                                    <div class="col-lg-8 col-xs-8 col-md-8">
																		<p class="form-control-static">${customer.contactorName}</p>
																	</div>
                                                                </div>
                                                            </div>
                                                            <div class="col-lg-4 col-xs-4 col-md-4">
                                                                <div class="form-group">
                                                                    <label class="control-label col-lg-4 col-xs-4 col-md-4"><spring:message code="com.jinzhiyi.ffs.message.customer.filed.contactorPhone"/>：</label>
                                                                    <div class="col-lg-8 col-xs-8 col-md-8">
																		<p class="form-control-static">${customer.contactorPhone}</p>
																	</div>
                                                                </div>
                                                            </div>
                                                            <div class="col-lg-4 col-xs-4 col-md-4">
                                                                <div class="form-group">
                                                                    <label class="control-label col-lg-4 col-xs-4 col-md-4"><spring:message code="com.jinzhiyi.ffs.message.customer.filed.contactorEmail"/>：</label>
                                                                    <div class="col-lg-8 col-xs-8 col-md-8">
																		<p class="form-control-static">${customer.contactorEmail}</p>
																	</div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-lg-4 col-xs-4 col-md-4">
                                                                <div class="form-group">
                                                                    <label class="control-label col-lg-4 col-xs-4 col-md-4"><spring:message code="com.jinzhiyi.ffs.message.customer.filed.contactorFax"/>：</label>
                                                                    <div class="col-lg-8 col-xs-8 col-md-8">
																		<p class="form-control-static">${customer.contactorFax}</p>
																	</div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-lg-4 col-xs-4 col-md-4">
                                                                <div class="form-group">
                                                                    <label class="control-label col-lg-4 col-xs-4 col-md-4"><spring:message code="com.jinzhiyi.ffs.message.customer.filed.city"/>：</label>
                                                                    <div class="col-lg-8 col-xs-8 col-md-8">
																		<p class="form-control-static">${customer.city}</p>
																	</div>
                                                                </div>
                                                            </div>
                                                            <div class="col-lg-4 col-xs-4 col-md-4">
                                                                <div class="form-group">
                                                                    <label class="control-label col-lg-4 col-xs-4 col-md-4"><spring:message code="com.jinzhiyi.ffs.message.customer.filed.province"/>：</label>
                                                                    <div class="col-lg-8 col-xs-8 col-md-8">
																		<p class="form-control-static">${customer.province}</p>
																	</div>
                                                                </div>
                                                            </div>
                                                            <div class="col-lg-4 col-xs-4 col-md-4">
                                                                <div class="form-group">
                                                                    <label class="control-label col-lg-4 col-xs-4 col-md-4"><spring:message code="com.jinzhiyi.ffs.message.customer.filed.country"/>：</label>
                                                                    <div class="col-lg-8 col-xs-8 col-md-8">
																		<p class="form-control-static">${customer.country}</p>
																	</div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                        	<div class="col-lg-4 col-xs-4 col-md-4">
	                                                                <div class="form-group">
	                                                                	<!-- 客户类型： -->
	                                                                    <label class="control-label col-lg-4 col-xs-4 col-md-4"><spring:message code="com.jinzhiyi.ffs.message.customer.filed.customerType"/>：</label>
	                                                                    <div class="col-lg-8 col-xs-8 col-md-8">
																			<p class="form-control-static">
																				<ffs:enum declaringClassName="${customer.customerType.declaringClass.name}" name="${customer.customerType}" />
																			</p>
																		</div>
	                                                                </div>
                                                            </div>
                                                            <div class="col-lg-4 col-xs-4 col-md-4">
	                                                                <div class="form-group">
	                                                                	<!-- 客户来源： -->
	                                                                    <label class="control-label col-lg-4 col-xs-4 col-md-4"><spring:message code="com.jinzhiyi.ffs.message.customer.filed.customerSource"/>：</label>
	                                                                    <div class="col-lg-8 col-xs-8 col-md-8">
																			<p class="form-control-static">
																				<ffs:enum declaringClassName="${customer.customerSource.declaringClass.name}" name="${customer.customerSource}" />
																			</p>
																		</div>
	                                                                </div>
                                                            </div>
	                                                         <div class="col-lg-4 col-xs-4 col-md-4">
	                                                                <div class="form-group">
	                                                                	<!-- 备注： -->
	                                                                    <label class="control-label col-lg-4 col-xs-4 col-md-4"><spring:message code="com.jinzhiyi.ffs.message.customer.audit.filed.remark"/>：</label>
	                                                                    <div class="col-lg-8 col-xs-8 col-md-8">
																			<p class="form-control-static">${fn:replace(customer.remark, enter, "<br/>")}</p>
																		</div>
	                                                                </div>
	                                                          </div>
	                                                    </div>
														<div class="row">
                                                        	<div class="col-lg-4 col-xs-4 col-md-4">
                                                                <div class="form-group">
                                                                	<!-- 创建人： -->
                                                                    <label class="control-label col-lg-4 col-xs-4 col-md-4"><spring:message code="com.jinzhiyi.ffs.message.customer.audit.filed.creator"/>：</label>
                                                                    <div class="col-lg-8 col-xs-8 col-md-8">
																		<p class="form-control-static"><ffs:user userId="${customer.creator}"/></p>
																	</div>
                                                                </div>
                                                            </div>
                                                        	<div class="col-lg-4 col-xs-4 col-md-4">
                                                                <div class="form-group">
                                                                	<!-- 创建时间： -->
                                                                    <label class="control-label col-lg-4 col-xs-4 col-md-4"><spring:message code="com.jinzhiyi.ffs.message.customer.audit.filed.creatorDate"/>：</label>
                                                                    <div class="col-lg-8 col-xs-8 col-md-8">
																		<p class="form-control-static"><fmt:formatDate value="${customer.createDateTime}" pattern="yyyy-MM-dd HH:mm:ss" /></p>
																	</div>
                                                                </div>
                                                            </div>
                                                        	<div class="col-lg-4 col-xs-4 col-md-4">
                                                                <div class="form-group">
                                                                	<!-- 审计状态： -->
                                                                    <label class="control-label col-lg-4 col-xs-4 col-md-4"><spring:message code="com.jinzhiyi.ffs.message.customer.audit.filed.auditStatus"/>：</label>
                                                                    <div class="col-lg-8 col-xs-8 col-md-8">
																		<p class="form-control-static"><span class="${customer.auditStatus.cssClass}"><ffs:enum declaringClassName="${customer.auditStatus.declaringClass.name}" name="${customer.auditStatus}" /></span></p>
																	</div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="row">
	                                                        <div class="col-lg-4 col-xs-4 col-md-4">
	                                                                <div class="form-group">
	                                                                	<!-- 修改人： -->
	                                                                    <label class="control-label col-lg-4 col-xs-4 col-md-4"><spring:message code="com.jinzhiyi.ffs.message.customer.audit.filed.modifier"/>：</label>
	                                                                    <div class="col-lg-8 col-xs-8 col-md-8">
																			<p class="form-control-static"><ffs:user userId="${customer.modifier}"/></p>
																		</div>
	                                                                </div>
                                                            </div>
	                                                        <div class="col-lg-4 col-xs-4 col-md-4">
	                                                                <div class="form-group">
	                                                                	<!-- 修改时间： -->
	                                                                    <label class="control-label col-lg-4 col-xs-4 col-md-4"><spring:message code="com.jinzhiyi.ffs.message.customer.audit.filed.modifierDate"/>：</label>
	                                                                    <div class="col-lg-8 col-xs-8 col-md-8">
																			<p class="form-control-static"><fmt:formatDate value="${customer.modifyDateTime }" pattern="yyyy-MM-dd HH:mm:ss" /></p>
																		</div>
	                                                                </div>
                                                            </div>
	                                                        <div class="col-lg-4 col-xs-4 col-md-4">
	                                                                <div class="form-group">
	                                                                	<!-- 状态： -->
	                                                                    <label class="control-label col-lg-4 col-xs-4 col-md-4"><spring:message code="com.jinzhiyi.ffs.message.customer.audit.filed.lockStatus"/>：</label>
	                                                                    <div class="col-lg-8 col-xs-8 col-md-8">
																			<p class="form-control-static">
																				<span class="${customer.lockStatus.showIcon}"></span> 
																				<ffs:enum declaringClassName="${customer.lockStatus.declaringClass.name}" name="${customer.lockStatus}" />
																			</p>
																		</div>
	                                                                </div>
                                                            </div>
                                                        </div>
                                                        <h4 class="form-section"><spring:message code="com.jinzhiyi.ffs.message.customer.audit.subtitle.contactPerson"/></h4>
                                                        <div class="row">
                                                        	<div class="col-lg-12 col-xs-12 col-md-12">
                                                                <table class="table table-bordered">
						                                            <thead>
																        <tr>
																          <th><spring:message code="com.jinzhiyi.ffs.message.customer.audit.contactor.filed.name"/><!-- 姓名 --></th>
																          <th><spring:message code="com.jinzhiyi.ffs.message.customer.audit.contactor.filed.postion"/><!-- 职位 --></th>
																          <th><spring:message code="com.jinzhiyi.ffs.message.customer.audit.contactor.filed.mobilePhone"/><!-- 手机号码 --></th>
																          <th><spring:message code="com.jinzhiyi.ffs.message.customer.apply.contactor.filed.email"/><!-- email --></th>
																          <th><spring:message code="com.jinzhiyi.ffs.message.customer.audit.contactor.filed.telephone"/><!-- 固定电话 --></th>
																          <th><spring:message code="com.jinzhiyi.ffs.message.customer.audit.contactor.filed.fax"/><!-- 传真 --></th>
																        </tr>
																      </thead>
																      <tbody>
																      <c:if test="${contactors != null && (not empty contactors)}">
																	      <c:forEach items="${contactors}" var="contactor">
																			    <tr>
																		          <td>${contactor.name }</td>
																		          <td>${contactor.position }</td>
																		          <td>${contactor.mobilePhone }</td>
																		          <td>${contactor.email }</td>
																		          <td>${contactor.telephone }</td>
																		          <td>${contactor.fax }</td>
																		        </tr>
																	      </c:forEach>
																      </c:if>
																      </tbody>
						                                        </table>
                                                            </div>
                                                        </div>
                                                        <c:if test="${customer.customerSourceApply}">
	                                                        <h4 class="form-section"><spring:message code="com.jinzhiyi.ffs.message.customer.audit.subtitle.accountInfomation"/></h4>
	                                                        <div class="row">
	                                                        	<div class="col-lg-12 col-xs-12 col-md-12">
	                                                                <table class="table table-bordered">
							                                            <thead>
																	        <tr>
																	          <th><spring:message code="com.jinzhiyi.ffs.message.customer.audit.user.filed.userCode"/><!-- 账号 --></th>
																	          <th><spring:message code="com.jinzhiyi.ffs.message.customer.audit.user.filed.userNameCn"/><!-- 中文名 --></th>
																	          <th><spring:message code="com.jinzhiyi.ffs.message.customer.audit.user.filed.userNameEn"/><!-- 英文名 --></th>
																	          <th><spring:message code="com.jinzhiyi.ffs.message.customer.audit.user.filed.email"/><!-- 电子邮箱 --></th>
																	          <th><spring:message code="com.jinzhiyi.ffs.message.customer.audit.user.filed.telephone"/><!-- 固定电话 --></th>
																	          <th><spring:message code="com.jinzhiyi.ffs.message.customer.audit.user.filed.mobilePhone"/><!-- 手机号码 --></th>
																	          <th><spring:message code="com.jinzhiyi.ffs.message.customer.audit.user.filed.status"/><%-- 状态 --%></th>
																	        </tr>
																	      </thead>
																	      <tbody>
																	      <c:forEach items="${userList}" var="user">
																		      <tr>
																		          <td>${user.userCode }</td>
																		          <td>${user.userNameCn}</td>
																		          <td>${user.userNameEn}</td>
																		          <td>${user.email }</td>
																		          <td>${user.telephone }</td>
																		          <td>${user.mobilePhone }</td>
																		          <td><span class="${user.status.showIcon}"></span> <ffs:enum declaringClassName="${user.status.declaringClass.name}" name="${user.status}" /></td>
																		        </tr>
																	      </c:forEach>
																	      </tbody>
							                                        </table>
	                                                            </div>
	                                                        </div>
	                                                        <!-- 财务审核 -->
	                                                        <h4 class="form-section"><spring:message code="com.jinzhiyi.ffs.message.customer.audit.caption.financialAudit"/></h4>
	                                                       	<div class="row">
	                                                    		<div class="col-lg-4 col-xs-4 col-md-4">
																	<div class="form-group">
																		<!-- 账期： -->
																		<label class="control-label col-lg-4 col-xs-4 col-md-4"><spring:message code="com.jinzhiyi.ffs.message.customer.audit.financial.paymentDay"/>：</label>
																		<div class="col-lg-8 col-xs-8 col-md-8">
					                                                      <p class="form-control-static"><ffs:enum declaringClassName="${customer.accountPeriod.declaringClass.name}" name="${customer.accountPeriod}"/></p>
																		</div>
																	</div>
																</div>
																<div class="col-lg-4 col-xs-4 col-md-4">
	                                                                <div class="form-group">
	                                                                	<!-- 信用额度： -->
	                                                                    <label class="control-label col-lg-5 col-xs-5 col-md-5"><spring:message code="com.jinzhiyi.ffs.message.customer.audit.financial.creditLine"/>：</label>
	                                                                    <div class="col-lg-7 col-xs-7 col-md-7">
																			<p class="form-control-static"><fmt:formatNumber value="${customer.creditLimit}" pattern="##.##"/></p>
																		</div>
	                                                                </div>
	                                                            </div>
																<div class="col-lg-4 col-xs-4 col-md-4">
	                                                                <div class="form-group">
	                                                                	<!-- 剩余额度： -->
	                                                                    <label class="control-label col-lg-5 col-xs-5 col-md-5"><spring:message code="com.jinzhiyi.ffs.message.customer.audit.financial.remainingCreditLimit"/>：</label>
	                                                                    <div class="col-lg-7 col-xs-7 col-md-7">
																			<p class="form-control-static">
																				<c:if test="${customer.remainingCreditLimit <= 0}">
																					<span class="label label-danger"><fmt:formatNumber value="${customer.remainingCreditLimit}" pattern="##.##"/></span>
																				</c:if>
																				<c:if test="${customer.remainingCreditLimit > 0}">
																					<fmt:formatNumber value="${customer.remainingCreditLimit}" pattern="##.##"/>
																				</c:if>
																			</p>
																		</div>
	                                                                </div>
	                                                            </div>
															</div>
														</c:if>
                                                    <div class="form-actions">
                                                        <div class="row">
                                                            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center">
                                                            	<c:if test="${customer.customerSourceManual}">
                                                            		<a class="btn red margin-left10" href="${pageContext.request.contextPath}/customer/modifyCustomerByCompany.do?customerId=${customer.recId}"><i class="fa fa-edit"></i> <spring:message code="com.jinzhiyi.ffs.message.common.btn.edit"/></a>
                                                            	</c:if>
                                                            	<c:if test="${customer.customerSourceApply}">
                                                            		<!--客服申请通过  -->
	                                                            	<c:if test="${sessionScope.ffsSessionUser.roleCustomerService && customer.auditStatusSubmitted && sessionScope.ffsSessionUser.userId==customer.acceptCustomerServiceId}">
	                                                            		<button type="button" class="btn btn-primary red margin-left10" id="btnPassCustomerApplyByCustomerService"><i class="glyphicon glyphicon-ok"></i> <spring:message code="com.jinzhiyi.ffs.message.customer.audit.btn.auditPass"/></button>
	                                                            	</c:if>
	                                                            	<!--财务申请通过  -->
	                                                            	<c:if test="${sessionScope.ffsSessionUser.roleAccountant && customer.auditStatusProcessing}">
	                                                            		<button type="button" class="btn btn-primary red margin-left10" id="btnPassCustomerApplyByAccountant"><i class="glyphicon glyphicon-ok"></i> <spring:message code="com.jinzhiyi.ffs.message.customer.audit.btn.auditPass"/></button>
	                                                            	</c:if>
	                                                            	<!-- 拒绝 -->
	                                                            	<%-- <button type="button" class="btn btn-default margin-left10" id="btnReject"><i class="glyphicon glyphicon-remove"></i><spring:message code="com.jinzhiyi.ffs.message.customer.audit.btn.auditReject"/></button> --%>
	                                                            	<!-- 客服修改客户信息 -->
	                                                            	<c:if test="${sessionScope.ffsSessionUser.roleCustomerService && sessionScope.ffsSessionUser.userId==customer.acceptCustomerServiceId && customer.auditStatusSubmitted}">
	                                                            		<a class="btn red margin-left10" href="${pageContext.request.contextPath}/customer/modifyCustomerApplyByCustomerService.do?customerId=${customer.recId}"><i class="fa fa-edit"></i> <spring:message code="com.jinzhiyi.ffs.message.customer.audit.btn.customerServiceCompanyModify"/></a>
	                                                            	</c:if>
	                                                            	<!-- 财务修改  调整临时信用额度-->
	                                                            	<c:if test="${sessionScope.ffsSessionUser.roleAccountant}">
	                                                            		<a class="btn red margin-left10" href="${pageContext.request.contextPath}/customer/modifyCustomerApplyByAccountant.do?customerId=${customer.recId}"><i class="fa fa-edit"></i> <spring:message code="com.jinzhiyi.ffs.message.customer.audit.btn.customerFinanceModify"/></a>
	                                                            		<a class="btn red margin-left10" href="${pageContext.request.contextPath}/customer/adjustCustomerRemainingCreditLimit.do?customerId=${customer.recId}"><i class="fa fa-edit"></i> <spring:message code="com.jinzhiyi.ffs.message.customer.audit.btn.adjustmentCustomerRemainingCreditLimit"/></a>
	                                                            	</c:if>
                                                            	</c:if>
                                                            	<!--客服删除客户  -->
	                                                            <c:if test="${customer.customerSourceManual || (customer.customerSourceApply && customer.auditStatusSubmitted && sessionScope.ffsSessionUser.userId==customer.acceptCustomerServiceId)}">
	                                                            	<button type="button" id="btnDeleteCustomer" class="btn btn-default margin-left10"><i class="fa fa-trash-o"></i> <spring:message code="com.jinzhiyi.ffs.message.common.btn.delete"/></button>
	                                                            </c:if>
	                                                            <!--返回客户审核界面  -->
	                                                            <c:if test="${sessionScope.ffsSessionUser.roleAccountant || sessionScope.ffsSessionUser.roleCustomerService}">
                                                            		<a class="btn btn-default margin-left10" href="${pageContext.request.contextPath}/customer/queryCustomerApplys.do"><i class="icon-share-alt"></i> <spring:message code="com.jinzhiyi.ffs.message.customer.audit.btn.backCustomerApplysQuery"/></a>
                                                            	</c:if>
                                                                <a class="btn btn-default margin-left10" href="${pageContext.request.contextPath}/customer/queryCustomersForCompany.do"><i class="icon-share-alt"></i> <spring:message code="com.jinzhiyi.ffs.message.common.btn.return_search"/></a>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    </div>
                                                    </form>
                                            	</div>
	                                     </div>
		                        </div>
			              </div>
			              <c:if test="${customer.customerSourceApply and !customer.auditStatusFinished}">
		            	  <div class="row">
			                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
		                           <div class="portlet light bordered">
                                        <div class="portlet-title">
                                                <div class="caption"><i class="glyphicon glyphicon-record"></i><spring:message code="com.jinzhiyi.ffs.message.customer.audit.caption.auditLog"/> </div>
                                        </div>
                                        <div class="portlet-body">
                                            <div class="row">
                                            	<div class="col-lg-12">
                                        			<iframe id="ifrmOrderAuditLog" src="${pageContext.request.contextPath}/customer/customerAuditLogs.do?customerId=${customer.recId}" frameborder="0" scrolling="auto" style="padding: 0px; width: 100%;"></iframe>
                                        		</div>
                                        	</div>
                                        </div>
                                    </div>
	                        </div>
		             	</div>
		             	</c:if>
	                </div>
	                </div>
	            </div>
	        </div>
    <%@ include file="/WEB-INF/jsp/common/footer.jsp"%>
   	<%@ include file="/jspi/metronic_base_js.jsp"%>
   	<script src="${ffsStaticUrl}/metronic_v4.5.6/theme/assets/global/plugins/artDialog4.1.7/jquery.artDialog.js"></script>
	<script src="${ffsStaticUrl}/metronic_v4.5.6/theme/assets/global/plugins/artDialog4.1.7/plugins/iframeTools.js"></script>
	<script type="text/javascript">
		$(function(){     
			$("#btnPassCustomerApplyByCustomerService").click(function() {
				$.dialog.confirm('<spring:message code="com.jinzhiyi.ffs.message.customer.audit.customerApplyAudit.ok"/>', function() {
					window.location.href = "${pageContext.request.contextPath}/customer/passCustomerApplyByCustomerService.do?customerId=${customer.recId}";
				});
			});
			
			$("#btnPassCustomerApplyByAccountant").click(function() {
				$.dialog.confirm('<spring:message code="com.jinzhiyi.ffs.message.customer.audit.customerApplyAudit.ok"/>', function() {
					window.location.href = "${pageContext.request.contextPath}/customer/passCustomerApplyByAccountant.do?customerId=${customer.recId}";
				});
			});
			
			$("#btnDeleteCustomer").click(function() {
				$.dialog.confirm('<spring:message code="com.jinzhiyi.ffs.message.customer.audit.customerApplyAudit.delete.confirm"/>', function() {
					window.location.href = "${pageContext.request.contextPath}/customer/deleteCustomer.do?customerId=${customer.recId}";
				});
			});
			
		/* 	$("#btnReject").click(function() {
				$.dialog.prompt('拒绝理由：', function(reason) {
					$.dialog.tips(reason);
				});
			}); */
		});
	</script>
</body>
</html>