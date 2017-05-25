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
		<title><spring:message code="com.jinzhiyi.ffs.message.customer.page.customer.modify.title" /></title>
		<%@ include file="/jspi/metronic_base_css.jsp"%>
		<link href="${ffsStaticUrl}/metronic_v4.5.6/theme/assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.css" rel="stylesheet" type="text/css" />
	</head>
<body class="page-content-white page-header-fixed">
	<%@ include file="/WEB-INF/jsp/common/header.jsp"%>
	<div class="page-container">
		<div class="page-content-wrapper">
			<div class="page-content margin-left0">
				<div class="container">
					<div class="row">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<div class="portlet light bordered">
								<div class="portlet-title">
									<div class="caption">
										<i class="icon-note"></i> <spring:message code="com.jinzhiyi.ffs.message.customer.page.customer.modify.panel.title" />
									</div>
								</div>
								<div class="portlet-body form">
									<form:form modelAttribute="customerDto" enctype="multipart/form-data" id="frmModifyCustomerByCustomer" method="post" action="${pageContext.request.contextPath}/customer/doModifyCustomerByCustomer.do">
										<form:hidden path="customer.recId"/>
										<form:hidden path="customer.customerCode"/>
										<div class="form-body">
											<%@ include file="/WEB-INF/jsp/common/errorMessageTip.jsp"%>
											<h4 class="form-section">
												<spring:message code="com.jinzhiyi.ffs.message.customer.apply.page.company.title" />
											</h4>
											<div class="row">
												<!-- 客户代码 -->
												<div class="col-lg-2 col-xs-2 col-md-2">
													<div class="form-group">
														<label class="control-label"><spring:message code="com.jinzhiyi.ffs.message.customer.filed.customerCode" /><span class="required">*</span></label>
														<p class="form-control form-control-static no-border no-padding-left"><strong>${customerDto.customer.customerCode}</strong></p>
													</div>
												</div>
												<!-- 中文名称 -->
												<div class="col-lg-4 col-xs-4 col-md-4">
													<div class="form-group">
														<label class="control-label"><spring:message code="com.jinzhiyi.ffs.message.customer.filed.nameCn" /><span class="required">*</span></label>
														<form:input id="nameCn" path="customer.nameCn" cssClass="form-control required_input" />
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
												<!-- 三证合一 -->
												<div class="col-lg-4 col-xs-4 col-md-4">
													<div class="form-group">
														<label class="control-label"><spring:message code="com.jinzhiyi.ffs.message.customer.filed.license" /></label>
														<div class="fileinput fileinput-new" data-provides="fileinput">
															<c:if test="${customerDto.customer.licenseFileMetaId != null}">
																	<a class="btn btn-default margin-right10" target="_blank" href="<ffs:fileMetaImageUrl fileMetaId="${customerDto.customer.licenseFileMetaId}" showDefaultImage="false"/>">
																		<spring:message code="com.jinzhiyi.ffs.message.common.file.view"/>
																	</a>
															</c:if>
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
											<!-- 联系人 -->
											<h4 class="form-section">
												<spring:message code="com.jinzhiyi.ffs.message.customer.apply.page.contactor.title" />
											</h4>
											<div class="row">
												<div class="col-lg-12">
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
														<c:if test="${customerDto.contactors != null && (not empty customerDto.contactors)}">
															<c:forEach items="${customerDto.contactors}" varStatus="status">
																<tr>
																	<td><form:input path="contactors[${status.index}].name" cssClass="form-control " /></td>
																	<td><form:input path="contactors[${status.index}].position" cssClass="form-control" /></td>
																	<td><form:input path="contactors[${status.index}].mobilePhone" cssClass="form-control " /></td>
																	<td><form:input path="contactors[${status.index}].email" cssClass="form-control " /></td>
																	<td><form:input path="contactors[${status.index}].telephone" cssClass="form-control " /></td>
																	<td><form:input path="contactors[${status.index}].fax" cssClass="form-control " /></td>
																</tr>
															</c:forEach>
														</c:if>
														</tbody>
													</table>
												</div>
											</div>
											<div class="form-actions">
												<div class="row">
													<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center">
														<button type="submit" class="btn red loading-state-btn" id="btnSubmit" data-loading-text="<spring:message code="com.jinzhiyi.ffs.message.common.btn.saving" />"><i class="fa fa-save"></i> <spring:message code="com.jinzhiyi.ffs.message.common.btn.save" /></button>
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
			$('#nameCn').focus();
			
			$('#frmModifyCustomerByCustomer').validate({
				rules : {
					"customer.nameCn" : {
						maxlength : 30,
						required : true
					},
					"customer.nameEn" : {
						maxlength : 30,
						required : false
					},
					"customer.address" : {
						maxlength : 100,
						required : true
					},
					"customer.postcode" : {
						minlength : 6,
						maxlength : 8
					}
				},
				messages : {
					"customer.nameCn" : {
						required : jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.customer.filed.nameCn.required"/>'),
						maxlength : jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.customer.filed.nameCn.maxlength"/>')
					},
					"customer.nameEn" : {
						maxlength : jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.customer.filed.nameEn.maxlength"/>')
					},
					"customer.address" : {
						required : jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.customer.filed.address.required"/>'),
						maxlength : jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.customer.filed.address.maxlength"/>')
					},
					"customer.postcode" : {
						minlength : jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.customer.filed.postcode.minlength"/>'),
						maxlength : jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.customer.filed.postcode.maxlength"/>')
					}
				}
			});
		});
	</script>
</body>
</html>