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
		<title><spring:message code="com.jinzhiyi.ffs.message.customer.create.page.title" /></title>
		<%@ include file="/jspi/metronic_base_css.jsp"%>
	</head>
<body class="page-content-white page-header-fixed">
	<%@ include file="/WEB-INF/jsp/common/header_simple.jsp"%>
	<div class="page-container">
		<div class="page-content-wrapper">
			<div class="page-content margin-left0">
				<div class="container">
					<div class="row">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<div class="portlet light bordered">
								<div class="portlet-title">
									<div class="caption">
										<i class="icon-note"></i> <spring:message code="com.jinzhiyi.ffs.message.customer.create.page.title" />
									</div>
								</div>
								<div class="portlet-body form">
									<form:form modelAttribute="customerDto"
										id="frmCreateCustomerByCompany"
										method="post"
										action="${pageContext.request.contextPath}/customer/doCreateCustomerByCompany.do">
										<div class="form-body">
											<%@ include file="/WEB-INF/jsp/common/errorMessageTip.jsp"%>
											<!-- 公司信息 -->
											<h4 class="form-section">
												<spring:message code="com.jinzhiyi.ffs.message.customer.apply.page.company.title" />
											</h4>
											<div class="row">
												<!-- 客户类型 -->
												<div class="col-lg-2 col-xs-2 col-md-2">
													<div class="form-group">
														<label class="control-label"><spring:message code="com.jinzhiyi.ffs.message.customer.filed.customerType" /><span class="required">*</span></label>
														<form:select path="customer.customerType" items="${customerTypeList}" itemLabel="label" itemValue="value" cssClass="form-control required_input" />
													</div>
												</div>
												<!-- 客户代码 -->
												<div class="col-lg-2 col-xs-2 col-md-2">
													<div class="form-group">
														<label class="control-label"><spring:message code="com.jinzhiyi.ffs.message.customer.filed.customerCode" /><span class="required">*</span></label>
														<form:input path="customer.customerCode" cssClass="form-control required_input uppercase" id="customerCode" />
													</div>
												</div>
												<!-- 中文名称 -->
												<div class="col-lg-4 col-xs-4 col-md-4">
													<div class="form-group">
														<label class="control-label"><spring:message code="com.jinzhiyi.ffs.message.customer.filed.nameCn" /><span class="required">*</span></label>
														<form:input path="customer.nameCn" cssClass="form-control required_input" />
													</div>
												</div>
												<!-- 英文名称 -->
												<div class="col-lg-4 col-xs-4 col-md-4">
													<div class="form-group">
														<label class="control-label"><spring:message code="com.jinzhiyi.ffs.message.customer.filed.nameEn" /></label>
														<form:input path="customer.nameEn" cssClass="form-control" />
														<span class="help-block"> </span>
													</div>
												</div>
											</div>
											<div class="row">
												<!-- 详细地址 -->
												<div class="col-lg-6 col-xs-6 col-md-6">
													<div class="form-group">
														<label class="control-label"><spring:message code="com.jinzhiyi.ffs.message.customer.filed.address" /><span class="required">*</span></label>
														<form:input path="customer.address" cssClass="form-control required_input" />
														<span class="help-block"></span>
													</div>
												</div>
												<!-- 邮编 -->
												<div class="col-lg-2 col-xs-2 col-md-2">
													<div class="form-group">
														<label class="control-label"><spring:message code="com.jinzhiyi.ffs.message.customer.filed.postcode" /></label>
														<form:input path="customer.postcode" cssClass="form-control" />
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-lg-2 col-xs-2 col-md-2">
													<div class="form-group">
														<label class="control-label"><spring:message code="com.jinzhiyi.ffs.message.customer.filed.contactorName" /></label>
														<form:input path="customer.contactorName" cssClass="form-control" />
													</div>
												</div>
												<div class="col-lg-3 col-xs-3 col-md-3">
													<div class="form-group">
														<label class="control-label"><spring:message code="com.jinzhiyi.ffs.message.customer.filed.contactorPhone" /></label>
														<form:input path="customer.contactorPhone" cssClass="form-control" />
													</div>
												</div>
												<div class="col-lg-3 col-xs-3 col-md-3">
													<div class="form-group">
														<label class="control-label"><spring:message code="com.jinzhiyi.ffs.message.customer.filed.contactorEmail" /></label>
														<form:input path="customer.contactorEmail" cssClass="form-control" />
													</div>
												</div>
												<div class="col-lg-3 col-xs-3 col-md-3">
													<div class="form-group">
														<label class="control-label"><spring:message code="com.jinzhiyi.ffs.message.customer.filed.contactorFax" /></label>
														<form:input path="customer.contactorFax" cssClass="form-control" />
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-lg-2 col-xs-2 col-md-2">
													<div class="form-group">
														<label class="control-label"><spring:message code="com.jinzhiyi.ffs.message.customer.filed.city" /></label>
														<form:input path="customer.city" cssClass="form-control" />
													</div>
												</div>
												<div class="col-lg-2 col-xs-2 col-md-2">
													<div class="form-group">
														<label class="control-label"><spring:message code="com.jinzhiyi.ffs.message.customer.filed.province" /></label>
														<form:input path="customer.city" cssClass="form-control" />
													</div>
												</div>
												<div class="col-lg-2 col-xs-2 col-md-2">
													<div class="form-group">
														<label class="control-label"><spring:message code="com.jinzhiyi.ffs.message.customer.filed.country" /></label>
														<form:input path="customer.country" cssClass="form-control" />
													</div>
												</div>
											</div>
											<div class="row">
												<!-- 备注 -->
												<div class="col-lg-12 col-xs-12 col-md-12">
													<div class="form-group">
														<label class="control-label"><spring:message code="com.jinzhiyi.ffs.message.customer.filed.remark" /></label>
														<form:textarea path="customer.remark" cssClass="form-control" rows="2" />
													</div>
												</div>
											</div>
											<!-- 联系人 -->
											<h4 class="form-section">
												<spring:message code="com.jinzhiyi.ffs.message.customer.apply.page.contactor.title" />
											</h4>
											<div class="row">
												<div class="col-lg-12">
													<table class="table table-bordered">
														<thead>
															<tr>
																<th width="120" class="text-center"><spring:message code="com.jinzhiyi.ffs.message.customer.apply.contactor.filed.name" /></th>
																<th width="130" class="text-center"><spring:message code="com.jinzhiyi.ffs.message.customer.apply.contactor.filed.position" /></th>
																<th width="180" class="text-center"><spring:message code="com.jinzhiyi.ffs.message.customer.apply.contactor.filed.mobilePhone" /></th>
																<th width="180" class="text-center"><spring:message code="com.jinzhiyi.ffs.message.customer.apply.contactor.filed.telephone" /></th>
																<th width="180" class="text-center"><spring:message code="com.jinzhiyi.ffs.message.customer.apply.contactor.filed.fax" /></th>
																<th class="text-center"><spring:message code="com.jinzhiyi.ffs.message.customer.apply.contactor.filed.email" /></th>
															</tr>
														</thead>
														<tbody>
															<c:forEach items="${customerDto.contactors}" varStatus="status">
																<tr>
																	<td><form:input path="contactors[${status.index}].name" cssClass="form-control " /></td>
																	<td><form:input path="contactors[${status.index}].position" cssClass="form-control " /></td>
																	<td><form:input path="contactors[${status.index}].mobilePhone" cssClass="form-control " /></td>
																	<td><form:input path="contactors[${status.index}].telephone" cssClass="form-control " /></td>
																	<td><form:input path="contactors[${status.index}].fax" cssClass="form-control " /></td>
																	<td><form:input path="contactors[${status.index}].email" cssClass="form-control " /></td>
																</tr>
															</c:forEach>
														</tbody>
													</table>
												</div>
											</div>
											<div class="form-actions">
												<div class="row">
													<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center">
														<button type="submit" class="btn red loading-state-btn" id="btnSubmit" data-loading-text="<spring:message code="com.jinzhiyi.ffs.message.common.btn.saving" />"><i class="fa fa-save"></i> <spring:message code="com.jinzhiyi.ffs.message.common.btn.save" /></button>
														<a class="btn btn-default margin-left10" href="${pageContext.request.contextPath}/customer/queryCustomersForCompany.do"><i class="icon-share-alt"></i> <spring:message code="com.jinzhiyi.ffs.message.common.btn.return_search"/></a>
													</div>
												</div>
											</div>
										</div>
									</form:form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="/jspi/metronic_base_js.jsp"%>
	<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>
	<script src="${ffsStaticUrl}/metronic_v4.5.6/theme/assets/global/plugins/jquery-validation/js/jquery.validate.min.js" type="text/javascript"></script>
	<script type="text/javascript">
		$(function() {
			$('#customerCode').focus();
			
			$('#frmCreateCustomerByCompany').validate({
				rules : {
					"customer.customerCode" : {
						required : true,
						maxlength : 10
					},
					"customer.nameCn" : {
						required : true,
						maxlength : 30
					},
					"customer.nameEn" : {
						required : false,
						maxlength : 30
					},
					"customer.customerType" : {
						required : true
					},
					"customer.address" : {
						maxlength : 100,
						required : true
					},
					"customer.postcode" : {
						minlength : 6,
						maxlength : 8
					},
					"customer.contactorName" : {
						required : false,
						maxlength : 30
					},
					"customer.contactorPhone" : {
						required : false,
						maxlength : 50
					},
					"customer.contactorEmail" : {
						required : false,
						maxlength : 50
					},
					"customer.contactorFax" : {
						required : false,
						maxlength : 50
					},
					"customer.city" : {
						required : false,
						maxlength : 30
					},
					"customer.province" : {
						required : false,
						maxlength : 30
					},
					"customer.country" : {
						required : false,
						maxlength : 30
					},
					"customer.remark" : {
						maxlength : 300
					}
				},
				messages : {
					"customer.customerCode" : {
						required : jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.customer.filed.customerCode.required"/>'),
						maxlength : jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.customer.filed.customerCode.maxlength"/>'),
						customerCode_exist : jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.customer.filed.exception.service.customerCode_exist"/>')
					},
					"customer.nameCn" : {
						required : jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.customer.filed.nameCn.required"/>'),
						maxlength : jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.customer.filed.nameCn.maxlength"/>')
					},
					"customer.nameEn" : {
						maxlength : jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.customer.filed.nameEn.maxlength"/>')
					},
					"customer.customerType" : {
						required : jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.customer.filed.customerType.required"/>'),
					},
					"customer.address" : {
						required : jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.customer.filed.address.required"/>'),
						maxlength : jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.customer.filed.address.maxlength"/>')
					},
					"customer.postcode" : {
						minlength : jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.customer.filed.postcode.minlength"/>'),
						maxlength : jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.customer.filed.postcode.maxlength"/>')
					},
					"customer.contactorName" : '<spring:message code="com.jinzhiyi.ffs.message.customer.filed.validate.contactorName"/>',
					"customer.contactorPhone" : '<spring:message code="com.jinzhiyi.ffs.message.customer.filed.validate.contactorPhone"/>',
					"customer.contactorEmail" : '<spring:message code="com.jinzhiyi.ffs.message.customer.filed.validate.contactorEmail"/>',
					"customer.contactorFax" : '<spring:message code="com.jinzhiyi.ffs.message.customer.filed.validate.contactorFax"/>',
					"customer.city" : '<spring:message code="com.jinzhiyi.ffs.message.customer.filed.validate.city"/>',
					"customer.province" : '<spring:message code="com.jinzhiyi.ffs.message.customer.filed.validate.province"/>',
					"customer.country" : '<spring:message code="com.jinzhiyi.ffs.message.customer.filed.validate.country"/>'
				}
			});
		});
	</script>
</body>
</html>