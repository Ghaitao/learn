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
		<title><spring:message code="com.jinzhiyi.ffs.message.customer.apply.page.title" /></title>
		<%@ include file="/jspi/metronic_base_css.jsp"%>
		<link href="${ffsStaticUrl}/metronic_v4.5.6/theme/assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.css" rel="stylesheet" type="text/css" />
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
									<!-- 申请会员账号 -->
									<div class="caption">
										<i class="icon-note"></i>
										<spring:message code="com.jinzhiyi.ffs.message.customer.apply.page.title" />
									</div>
								</div>
								<div class="portlet-body form">
									<form:form modelAttribute="customerDto"
										enctype="multipart/form-data" id="createApplyForm"
										method="post"
										action="${pageContext.request.contextPath}/customer/doCreateApply.do">
										<div class="form-body">
											<%@ include file="/WEB-INF/jsp/common/errorMessageTip.jsp"%>
											<!-- 公司信息 -->
											<h4 class="form-section">
												<spring:message code="com.jinzhiyi.ffs.message.customer.apply.page.company.title" />
											</h4>
											<div class="row">
												<!-- 客户代码 -->
												<div class="col-lg-2 col-xs-2 col-md-2">
													<div class="form-group">
														<label class="control-label"><spring:message code="com.jinzhiyi.ffs.message.customer.filed.customerCode" /><span class="required">*</span></label>
														<form:input path="customer.customerCode" cssClass="form-control required_input uppercase" id="customerCode" />
													</div>
												</div>
												<!-- 客服姓名 -->
												<div class="col-lg-2 col-xs-2 col-md-2">
													<div class="form-group">
														<label class="control-label"><spring:message code="com.jinzhiyi.ffs.message.customer.filed.acceptCustomerService" /><span class="required">*</span></label>
														<form:select path="customer.acceptCustomerServiceId"
															items="${customerServiceUsers}" itemLabel="label"
															itemValue="value" cssClass="form-control required_input" />
														<span class="help-block"></span>
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
												<!-- 客户类型 -->
												<div class="col-lg-2 col-xs-2 col-md-2">
													<div class="form-group">
														<label class="control-label"><spring:message code="com.jinzhiyi.ffs.message.customer.filed.customerType" /><span class="required">*</span></label>
														<form:select path="customer.customerType" items="${customerTypeList}" itemLabel="label" itemValue="value" cssClass="form-control required_input" />
													</div>
												</div>
												<!-- 详细地址 -->
												<div class="col-lg-4 col-xs-4 col-md-4">
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
												<!-- 三证合一 -->
												<div class="col-lg-4 col-xs-4 col-md-4">
													<div class="form-group">
														<label class="control-label"><spring:message code="com.jinzhiyi.ffs.message.customer.filed.license" /></label>
														<div class="fileinput fileinput-new" data-provides="fileinput">
															<span class="btn btn-default btn-file">
																<span class="fileinput-new"><i class="fa fa-file"></i> <spring:message code="com.jinzhiyi.ffs.message.common.file.choose" /></span>
																<span class="fileinput-exists"><i class="fa fa-exchange"></i>
																<spring:message code="com.jinzhiyi.ffs.message.common.file.edit" /> </span>
																<input type="file" name="license" accept="image/*">
															</span>
															<span class="fileinput-filename"> </span> &nbsp; 
															<a href="javascript:;" class="close fileinput-exists" data-dismiss="fileinput" title="<spring:message code="com.jinzhiyi.ffs.message.common.file.delete"/>"></a>
														</div>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-lg-2 col-xs-2 col-md-2">
													<div class="form-group">
														<label class="control-label"><spring:message code="com.jinzhiyi.ffs.message.customer.filed.contactorName" /><span class="required">*</span></label>
														<form:input path="customer.contactorName" cssClass="form-control required_input" />
													</div>
												</div>
												<div class="col-lg-3 col-xs-3 col-md-3">
													<div class="form-group">
														<label class="control-label"><spring:message code="com.jinzhiyi.ffs.message.customer.filed.contactorPhone" /><span class="required">*</span></label>
														<form:input path="customer.contactorPhone" cssClass="form-control required_input" />
													</div>
												</div>
												<div class="col-lg-3 col-xs-3 col-md-3">
													<div class="form-group">
														<label class="control-label"><spring:message code="com.jinzhiyi.ffs.message.customer.filed.contactorEmail" /><span class="required">*</span></label>
														<form:input path="customer.contactorEmail" cssClass="form-control required_input" />
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
														<form:input path="customer.province" cssClass="form-control" />
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
											<!-- 账号信息 -->
											<h4 class="form-section">
												<spring:message code="com.jinzhiyi.ffs.message.customer.apply.page.account.title" />
											</h4>
											<div class="row">
												<!-- 用户名 -->
												<div class="col-lg-2 col-xs-2 col-md-2">
													<div class="form-group">
														<label class="control-label"><spring:message code="com.jinzhiyi.ffs.message.customer.apply.account.filed.userCode" /><span class="required">*</span></label>
														<form:input path="account.userCode" cssClass="form-control required_input" />
													</div>
												</div>
												<!-- 设置密码 -->
												<div class="col-lg-2 col-xs-2 col-md-2">
													<div class="form-group">
														<label class="control-label"><spring:message code="com.jinzhiyi.ffs.message.customer.apply.account.filed.userPassword" /><span class="required">*</span></label>
														<form:password path="account.userPassword" cssClass="form-control required_input" id="userPassword" />
														<span class="help-block"></span>
													</div>
												</div>
												<!-- 确认密码 -->
												<div class="col-lg-2 col-xs-2 col-md-2">
													<div class="form-group">
														<label class="control-label"><spring:message code="com.jinzhiyi.ffs.message.customer.apply.account.filed.confirmPassword" /><span class="required">*</span></label>
														<form:password path="account.confirmPassword" cssClass="form-control required_input" />
													</div>
												</div>
												<!-- 手机号码 -->
												<div class="col-lg-3 col-xs-3 col-md-3">
													<div class="form-group">
														<label class="control-label"><spring:message code="com.jinzhiyi.ffs.message.customer.apply.account.filed.mobilePhone" /><span class="required">*</span></label>
														<form:input path="account.mobilePhone" cssClass="form-control required_input" />
													</div>
												</div>
												<!-- 电子邮箱 -->
												<div class="col-lg-3 col-xs-3 col-md-3">
													<div class="form-group">
														<label class="control-label"><spring:message code="com.jinzhiyi.ffs.message.customer.apply.account.filed.email" /><span class="required">*</span></label>
														<form:input path="account.email" cssClass="form-control required_input" />
													</div>
												</div>
											</div>
											<div class="row">
												<!-- 姓名(中文) -->
												<div class="col-lg-2 col-xs-2 col-md-2">
													<div class="form-group">
														<label class="control-label"><spring:message code="com.jinzhiyi.ffs.message.customer.apply.account.filed.nameCn" /></label>
														<form:input path="account.userNameCn" cssClass="form-control" />
													</div>
												</div>
												<!-- 姓名(英文) -->
												<div class="col-lg-2 col-xs-2 col-md-2">
													<div class="form-group">
														<label class="control-label"><spring:message code="com.jinzhiyi.ffs.message.customer.apply.account.filed.nameEn" /></label>
														<form:input path="account.userNameEn" cssClass="form-control" />
													</div>
												</div>
												<!-- 固定电话 -->
												<div class="col-lg-4 col-xs-4 col-md-4">
													<div class="form-group">
														<label class="control-label"><spring:message code="com.jinzhiyi.ffs.message.customer.apply.account.filed.telephone" /></label>
														<form:input path="account.telephone" cssClass="form-control" />
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
											<div class="row">
												<div class="col-lg-3 col-xs-3 col-md-3">
													<!-- 验证码 -->
													<div class="form-group">
														<label class="control-label"><spring:message code="com.jinzhiyi.ffs.message.captchaCode" /><span class="required">*</span></label>
														<div class="input-group">
															<input type="text" name="captchaCode" class="form-control required_input" maxlength="4" placeholder="<spring:message code="com.jinzhiyi.ffs.message.captchaCode.placeholder"/>" autocomplete="off"/>
															<div class="input-group-btn">
																<a id="btnRefreshCaptchaCode" class="margin-left10" href="javascript:;">
																	<img id="imgCaptchaCode" src="${pageContext.request.contextPath}/code/captcha.do" alt="<spring:message code="com.jinzhiyi.ffs.message.captchaCode.clickrefresh"/>" title="<spring:message code="com.jinzhiyi.ffs.message.captchaCode.clickrefresh"/>" />
																</a>
															</div>
														</div>
													</div>
												</div>
											</div>
											<div class="form-actions">
												<div class="row">
													<div
														class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center">
														<button type="submit" class="btn red loading-state-btn"
															id="btnSubmit"
															data-loading-text="<spring:message code="com.jinzhiyi.ffs.message.customer.apply.page.button.submitting"/>">
															<i class="fa fa-file-o"></i> <spring:message code="com.jinzhiyi.ffs.message.customer.apply.page.button.submit" />
														</button>
														<a class="btn btn-default margin-left10" href="${pageContext.request.contextPath}/login.do">
															<i class="icon-share-alt"></i> <spring:message code="com.jinzhiyi.ffs.message.page.back" />
														</a>
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
	<script src="${ffsStaticUrl}/metronic_v4.5.6/theme/assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.js" type="text/javascript"></script>
	<script src="${ffsStaticUrl}/metronic_v4.5.6/theme/assets/global/plugins/jquery-validation/js/jquery.validate.min.js" type="text/javascript"></script>
	<script src="${ffsStaticUrl}/metronic_v4.5.6/theme/assets/global/plugins/jquery-validation/js/additional-methods.min.js" type="text/javascript"></script>
	<script type="text/javascript">
		$(function() {
			$('#customerCode').focus();
			// 手机号码验证
			jQuery.validator.addMethod("isMobile", 
							function(value, element) {
								var length = value.length;
								var mobile = /^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$/;
								return this.optional(element) || (length == 11 && mobile.test(value));
							},
							'<spring:message code="com.jinzhiyi.ffs.message.customer.filed.mobile_phone.isMobile"/>');
			// 密码格式验证
			jQuery.validator.addMethod("numletter",
							function(value, element) {
								return this.optional(element)
										|| /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]+$/
												.test(value);
							},
							$.validator.format('<spring:message code="com.jinzhiyi.ffs.message.customer.filed.user_password.numletter"/>'));
			
			$('#createApplyForm').validate({
				rules : {
					"customer.customerCode" : {
						required : true,
						maxlength : 10
					},
					"customer.acceptCustomerServiceId" : {
						required : true,
						maxlength : 20
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
						required : true,
						maxlength : 100
					},
					"customer.postcode" : {
						minlength : 6,
						maxlength : 8
					},
					"customer.contactorName" : {
						required : true,
						maxlength : 30
					},
					"customer.contactorPhone" : {
						required : true,
						maxlength : 50
					},
					"customer.contactorEmail" : {
						required : true,
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
					},
					"account.userCode" : {
						maxlength : 20,
						required : true
					},
					"account.userPassword" : {
						required : true,
						minlength : 8,
						numletter : true,
						maxlength : 30
					},
					"account.confirmPassword" : {
						required : true,
						minlength : 8,
						maxlength : 30,
						numletter : true,
						equalTo : "#userPassword"
					},
					"account.userNameCn" : {
						maxlength : 20
					},
					"account.userNameEn" : {
						maxlength : 20
					},
					"account.mobilePhone" : {
						required : true,
						maxlength : 11,
						isMobile : true
					},
					"account.email" : {
						maxlength : 30,
						required : true,
						email : true
					},
					'captchaCode' : {
						required : true
					}
				},
				messages : {
					"customer.customerCode" : {
						required : jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.customer.filed.customerCode.required"/>'),
						maxlength : jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.customer.filed.customerCode.maxlength"/>'),
						customerCode_exist : jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.customer.filed.exception.service.customerCode_exist"/>')
					},
					"customer.acceptCustomerServiceId" : {
						required : jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.customer.filed.acceptCustomerService.required"/>'),
						maxlength : jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.customer.filed.acceptCustomerService.maxlength"/>')
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
					"customer.country" : '<spring:message code="com.jinzhiyi.ffs.message.customer.filed.validate.country"/>',
					"customer.remark" : {
						maxlength : jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.customer.filed.remark.maxlength"/>')
					},
					"account.userCode" : {
						required : jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.customer.filed.user_code.required"/>'),
						maxlength : jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.customer.filed.user_code.maxlength"/>')
					},
					"account.userPassword" : {
						required : jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.customer.filed.user_password.required"/>'),
						minlength : jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.customer.filed.user_password.minlength"/>'),
						numletter : jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.customer.filed.user_password.numletter"/>'),
						maxlength : jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.customer.filed.user_password.maxlength"/>')
					},
					"account.confirmPassword" : {
						required : jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.customer.filed.repeat_user_password.required"/>'),
						minlength : jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.customer.filed.repeat_user_password.minlength"/>'),
						maxlength : jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.customer.filed.repeat_user_password.maxlength"/>'),
						numletter : jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.customer.filed.user_password.numletter"/>'),
						equalTo : jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.customer.filed.repeat_user_password.equalTo"/>')
					},
					"account.userNameCn" : {
						maxlength : jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.customer.filed.userNameCn.maxlength"/>')
					},
					"account.userNameEn" : {
						maxlength : jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.customer.filed.userNameEn.maxlength"/>')
					},
					"account.mobilePhone" : {
						required : jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.customer.filed.mobile_phone.required"/>'),
						isMobile : jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.customer.filed.mobile_phone.isMobile"/>'),
						maxlength : jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.customer.filed.mobile_phone.maxlength"/>')
					},
					"account.email" : {
						required : jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.customer.filed.email.required"/>'),
						email : jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.customer.filed.email.email"/>'),
						maxlength : jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.customer.filed.email.maxlength"/>')
					},
					"captchaCode" : {
						required : jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.customer.filed.captchaCode.required"/>')
					}
				}
			});

			$('#btnRefreshCaptchaCode').click(function() {
				$('#imgCaptchaCode').attr('src', '${pageContext.request.contextPath}/code/captcha.do');
			});
		});
	</script>
</body>
</html>