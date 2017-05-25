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
        <title><spring:message code="com.jinzhiyi.ffs.message.userPerson.title"/></title>
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
                                        <div class="caption"><i class="icon-note"></i><spring:message code="com.jinzhiyi.ffs.message.user.panel.person.detail.title" /></div>
                                    </div>
                                    <div class="portlet-body form">
                                    	<div class="tabbable-custom ">
	                                        <ul class="nav nav-tabs ">
	                                            <li class="active">
	                                                <a href="#tab_Person" data-toggle="tab"><spring:message code="com.jinzhiyi.ffs.message.common.person" /> </a>
	                                            </li>
	                                            <li>
	                                                <a href="#tab_Company" data-toggle="tab"><spring:message code="com.jinzhiyi.ffs.message.common.company" /></a>
	                                            </li>
	                                            <c:if test="${sessionScope.ffsSessionUser.customer}">
	                                            <li>
	                                                <a href="#tab_Customer" data-toggle="tab"><spring:message code="com.jinzhiyi.ffs.message.common.customer" /></a>
	                                            </li>
	                                            </c:if>
	                                        </ul>
	                                        <div class="tab-content">
	                                            <div class="tab-pane active" id="tab_Person">
													<form action="" class="form-horizontal" id="userPersonDetailForm">
			                                            <div class="form-body">
			                                            	<div class="row">
			                                            		<div class="col-lg-4 col-xs-4 col-md-4">
																	<div class="form-group">
																		<label class="control-label col-lg-4 col-xs-4 col-md-4"><spring:message code="com.jinzhiyi.ffs.message.user.user_code"/>：</label>
																		<div class="col-lg-8 col-xs-8 col-md-8">
																			<p class="form-control-static">${user.userCode}</p>
																		</div>
																	</div>
																</div>
																<div class="col-lg-4 col-xs-4 col-md-4">
			                                                        <div class="form-group">
			                                                            <label class="control-label col-lg-4 col-xs-4 col-md-4"><spring:message code="com.jinzhiyi.ffs.message.user.user_name_cn"/>：</label>
			                                                            <div class="col-lg-8 col-xs-8 col-md-8">
																			<p class="form-control-static">${user.userNameCn}</p>
																		</div>
			                                                        </div>
			                                                    </div>
			                                                    <div class="col-lg-4 col-xs-4 col-md-4">
			                                                        <div class="form-group">
			                                                            <label class="control-label col-lg-4 col-xs-4 col-md-4"><spring:message code="com.jinzhiyi.ffs.message.user.user_name_en"/>：</label>
			                                                            <div class="col-lg-8 col-xs-8 col-md-8">
																			<p class="form-control-static">${user.userNameEn}</p>
																		</div>
			                                                        </div>
			                                                    </div>
															</div>
			                                                <div class="row">
			                                                    <div class="col-lg-4 col-xs-4 col-md-4">
			                                                        <div class="form-group">
			                                                            <label class="control-label control-label col-lg-4 col-xs-4 col-md-4"><spring:message code="com.jinzhiyi.ffs.message.user.mobile_phone"/>：</label>
			                                                            <div class="col-lg-8 col-xs-8 col-md-8">
																			<p class="form-control-static">${user.mobilePhone}</p>
																		</div>
			                                                        </div>
			                                                    </div>
			                                                    <div class="col-lg-4 col-xs-4 col-md-4">
			                                                        <div class="form-group">
			                                                            <label class="control-label col-lg-4 col-xs-4 col-md-4"><spring:message code="com.jinzhiyi.ffs.message.user.telephone"/>：</label>
			                                                            <div class="col-lg-8 col-xs-8 col-md-8">
																			<p class="form-control-static">${user.telephone}</p>
																		</div>
			                                                        </div>
			                                                    </div>
			                                                    <div class="col-lg-4 col-xs-4 col-md-4">
			                                                        <div class="form-group">
			                                                            <label class="control-label col-lg-4 col-xs-4 col-md-4"><spring:message code="com.jinzhiyi.ffs.message.user.email"/>：</label>
			                                                            <div class="col-lg-8 col-xs-8 col-md-8">
																			<p class="form-control-static">${user.email}</p>
																		</div>
			                                                        </div>
			                                                    </div>
			                                                </div>
			                                                <div class="row">
			                                                    <div class="col-lg-4 col-xs-4 col-md-4">
			                                                        <div class="form-group">
			                                                            <label class="control-label control-label col-lg-4 col-xs-4 col-md-4"><spring:message code="com.jinzhiyi.ffs.message.user.qq"/>：</label>
			                                                            <div class="col-lg-8 col-xs-8 col-md-8">
																			<p class="form-control-static">${user.qq}</p>
																		</div>
			                                                        </div>
			                                                    </div>
			                                                    <div class="col-lg-4 col-xs-4 col-md-4">
			                                                        <div class="form-group">
			                                                            <label class="control-label col-lg-4 col-xs-4 col-md-4"><spring:message code="com.jinzhiyi.ffs.message.user.company_code"/>：</label>
			                                                            <div class="col-lg-8 col-xs-8 col-md-8">
																			<p class="form-control-static"><ffs:company companyCode="${user.companyCode}" /></p>
																		</div>
			                                                        </div>
			                                                    </div>
			                                                    <div class="col-lg-4 col-xs-4 col-md-4">
			                                                        <div class="form-group">
			                                                            <label class="control-label col-lg-4 col-xs-4 col-md-4"><spring:message code="com.jinzhiyi.ffs.message.user.customer_code"/>：</label>
			                                                            <div class="col-lg-8 col-xs-8 col-md-8">
																			<p class="form-control-static">${user.customerCode}</p>
																		</div>
			                                                        </div>
			                                                    </div>
			                                                </div>
			                                                <div class="row">
			                                                	<div class="col-lg-8 col-xs-8 col-md-8">
			                                                        <div class="form-group">
			                                                            <label class="control-label col-lg-2 col-xs-2 col-md-2"><spring:message code="com.jinzhiyi.ffs.message.user.role_code"/>：</label>
			                                                            <div class="col-lg-10 col-xs-10 col-md-10">
																			<p class="form-control-static"><ffs:role userId="${user.recId }" /></p>
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
																			<c:if test="${!user.locked}">
																          		<span class="label label-success"><ffs:enum declaringClassName="${user.status.declaringClass.name}" name="${user.status}" /></span>
																          	</c:if>
																          	<c:if test="${user.locked}">
																          		<span class="label label-default"><ffs:enum declaringClassName="${user.status.declaringClass.name}" name="${user.status}" /></span>
																          	</c:if>
																          	</p>
																		</div>
			                                                        </div>
			                                                    </div>
															</div>
			                                           	</div>
			                                            <div class="form-actions">
			                                                <div class="row">
			                                                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center">
			                                                    	<a href="${pageContext.request.contextPath}/sys/userPerson/preUserPersonModify.do?recId=${user.recId}" class="btn red"><i class="fa fa-edit"></i> <spring:message code="com.jinzhiyi.ffs.message.common.btn.edit" /></a>
			                                                		<a class="btn btn-default margin-left10" href="${pageContext.request.contextPath}/sys/userPerson/prePasswordModify.do?recId=${user.recId}"><i class="fa fa-edit"></i> <spring:message code="com.jinzhiyi.ffs.message.user.btn.modify_password" /></a>
			                                                    </div>
			                                                </div>
			                                            </div>
			                                        </form>
	                                            </div>
	                                            <div class="tab-pane" id="tab_Company">
	                                            	<form action="" class="form-horizontal" id="companyDetailForm">
			                                            <div class="form-body">
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
	                                            		</div>
	                                            	<%-- 	<c:if test="${sessionScope.ffsSessionUser.roleAdmin || sessionScope.ffsSessionUser.roleJinZhiYiAdmin}"> --%>
	                                            		<c:if test="${sessionScope.ffsSessionUser.companyUser}">
														<div class="form-actions">
			                                                <div class="row">
			                                                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center">
			                                                    	<a href="${pageContext.request.contextPath}/sys/userPerson/preCompanyByUserPersonModify.do?recId=${company.recId}" class="btn red"><i class="fa fa-edit"></i> <spring:message code="com.jinzhiyi.ffs.message.common.btn.edit" /></a>
			                                                    </div>
			                                                </div>
			                                            </div>
			                                            </c:if>
				                                        <%-- </c:if> --%>
	                                            	</form>
	                                            </div>
	                                            <c:if test="${sessionScope.ffsSessionUser.customer}">
	                                       		<div class="tab-pane" id="tab_Customer">
	                                       			<form action="" class="form-horizontal" id="customerDetailForm">
			                                            <div class="form-body">
			                                            	<div class="row">
			                                            		<div class="col-lg-4 col-xs-4 col-md-4">
																	<div class="form-group">
																		<label class="control-label col-lg-4 col-xs-4 col-md-4"><spring:message code="com.jinzhiyi.ffs.message.customer.filed.customerCode" />：</label>
																		<div class="col-lg-8 col-xs-8 col-md-8">
																			<p class="form-control-static">${customer.customerCode}</p>
																		</div>
																	</div>
																</div>
																<div class="col-lg-4 col-xs-4 col-md-4">
			                                                        <div class="form-group">
			                                                            <label class="control-label col-lg-4 col-xs-4 col-md-4"><spring:message code="com.jinzhiyi.ffs.message.customer.filed.nameCn" />：</label>
			                                                            <div class="col-lg-8 col-xs-8 col-md-8">
																			<p class="form-control-static">${customer.nameCn}</p>
																		</div>
			                                                        </div>
			                                                    </div>
			                                                    <div class="col-lg-4 col-xs-4 col-md-4">
			                                                        <div class="form-group">
			                                                            <label class="control-label col-lg-4 col-xs-4 col-md-4"><spring:message code="com.jinzhiyi.ffs.message.customer.filed.nameEn" />：</label>
			                                                            <div class="col-lg-8 col-xs-8 col-md-8">
																			<p class="form-control-static">${customer.nameEn}</p>
																		</div>
			                                                        </div>
			                                                    </div>
															</div>
															<div class="row">
			                                            		<div class="col-lg-4 col-xs-4 col-md-4">
																	<div class="form-group">
																		<label class="control-label col-lg-4 col-xs-4 col-md-4"><spring:message code="com.jinzhiyi.ffs.message.customer.filed.address" />：</label>
																		<div class="col-lg-8 col-xs-8 col-md-8">
																			<p class="form-control-static">${customer.address}</p>
																		</div>
																	</div>
																</div>
																<div class="col-lg-4 col-xs-4 col-md-4">
																	<div class="form-group">
																		<label class="control-label col-lg-4 col-xs-4 col-md-4"><spring:message code="com.jinzhiyi.ffs.message.customer.filed.postcode" />：</label>
																		<div class="col-lg-8 col-xs-8 col-md-8">
																			<p class="form-control-static">${customer.postcode}</p>
																		</div>
																	</div>
																</div>
																<div class="col-lg-4 col-xs-4 col-md-4">
																	<div class="form-group">
																		<label class="control-label col-lg-4 col-xs-4 col-md-4"><spring:message code="com.jinzhiyi.ffs.message.customer.filed.status" />：</label>
																		<div class="col-lg-8 col-xs-8 col-md-8">
																			<p class="form-control-static"><ffs:enum declaringClassName="${customer.lockStatus.declaringClass.name}" name="${customer.lockStatus}" /></p>
																		</div>
																	</div>
																</div>
															</div>
															<div class="row">
	                                                    		<div class="col-lg-4 col-xs-4 col-md-4">
																	<div class="form-group">
																		<label class="control-label col-lg-4 col-xs-4 col-md-4"><spring:message code="com.jinzhiyi.ffs.message.customer.audit.financial.paymentDay"/>：</label>
																		<div class="col-lg-8 col-xs-8 col-md-8">
					                                                      <p class="form-control-static"><ffs:enum declaringClassName="${customer.accountPeriod.declaringClass.name}" name="${customer.accountPeriod}"/></p>
																		</div>
																	</div>
																</div>
																<div class="col-lg-4 col-xs-4 col-md-4">
	                                                                <div class="form-group">
	                                                                    <label class="control-label col-lg-4 col-xs-4 col-md-4"><spring:message code="com.jinzhiyi.ffs.message.customer.audit.financial.creditLine"/>：</label>
	                                                                    <div class="col-lg-8 col-xs-8 col-md-8">
																			<p class="form-control-static">${customer.creditLimit}</p>
																		</div>
	                                                                </div>
	                                                            </div>
																<div class="col-lg-4 col-xs-4 col-md-4">
	                                                                <div class="form-group">
	                                                                    <label class="control-label col-lg-4 col-xs-4 col-md-4"><spring:message code="com.jinzhiyi.ffs.message.customer.audit.financial.temporaryLines"/>：</label>
	                                                                    <div class="col-lg-8 col-xs-8 col-md-8">
																			<p class="form-control-static">${customer.temporaryCreditLimit}</p>
																		</div>
	                                                                </div>
	                                                            </div>
															</div>
															<div class="row">
				                                            	<div class="col-lg-4 col-xs-4 col-md-4">
	                                                                <div class="form-group">
	                                                                    <label class="control-label col-lg-4 col-xs-4 col-md-4"><spring:message code="com.jinzhiyi.ffs.message.customer.filed.acceptCustomerService" />：</label>
	                                                                    <div class="col-lg-8 col-xs-8 col-md-8">
																			<p class="form-control-static"><ffs:user userId="${customer.acceptCustomerServiceId}"/></p>
																		</div>
	                                                                </div>
	                                                            </div>
				                                            </div>
				                                            <c:if test="${sessionScope.ffsSessionUser.customer}">
				                                            <div class="form-actions">
				                                                <div class="row">
				                                                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center">
				                                                    	<a href="${pageContext.request.contextPath}/customer/modifyCustomerByCustomer.do?customerId=${customer.recId}" class="btn red"><i class="fa fa-edit"></i> <spring:message code="com.jinzhiyi.ffs.message.common.btn.edit" /></a>
				                                                    </div>
				                                                </div>
				                                            </div>
				                                            </c:if>
			                                            </div>
			                                    	</form>
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