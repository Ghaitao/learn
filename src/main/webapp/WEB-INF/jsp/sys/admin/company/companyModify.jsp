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
        <title><spring:message code="com.jinzhiyi.ffs.message.company.title"/></title>
        <link href="${ffsStaticUrl}/metronic_v4.5.6/theme/assets/global/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css" />
        <link href="${ffsStaticUrl}/metronic_v4.5.6/theme/assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.css" rel="stylesheet" type="text/css" />
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
                                        <div class="caption"><i class="icon-note"></i><spring:message code="com.jinzhiyi.ffs.message.company.panel.modify.title" /></div>
                                    </div>
                                    <div class="portlet-body form">
                                        <form action="${pageContext.request.contextPath}/sys/admin/company/companyModify.do" id="modifyCompanyForm" enctype="multipart/form-data" method="post">
                                            <div class="form-body">
                                            	<h4 class="form-section"><spring:message code="com.jinzhiyi.ffs.message.company.panel.info" /></h4>
                                                <input type="hidden" name="recId" id="recId" value="${company.recId}" >
                                            	<c:if test="${not empty errorMessage}">
                                            	<div class="row">
                                                	<div class="col-lg-12 col-xs-12 col-md-12">
										                <div class="alert alert-danger">
										                    <span> <i class="fa fa-exclamation-triangle"></i> ${errorMessage} </span>
										                </div>
									                </div>
									            </div>
								                </c:if>
                                                <div class="row">
                                                    <div class="col-lg-6 col-xs-6 col-md-6">
                                                        <div class="form-group">
                                                            <label class="control-label"><spring:message code="com.jinzhiyi.ffs.message.company.company_name_cn" /><span class="required">*</span></label>
                                                            <input type="text" class="form-control required_input" name="nameCn" id="nameCn" value="${company.nameCn}" placeholder="<spring:message code="com.jinzhiyi.ffs.message.company.company_name_cn" />">
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-6 col-xs-6 col-md-6">
                                                        <div class="form-group">
                                                            <label class="control-label"><spring:message code="com.jinzhiyi.ffs.message.company.company_name_en" /></label>
                                                            <input type="text" class="form-control disable-ime" name="nameEn" id="nameEn" value="${company.nameEn}" placeholder="<spring:message code="com.jinzhiyi.ffs.message.company.company_name_en" />">
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                	<div class="col-lg-2 col-xs-2 col-md-2">
														<div class="form-group">
															<label class="control-label"><spring:message code="com.jinzhiyi.ffs.message.company.company_code" /></label>
															<%-- <input type="text" class="form-control required_input text-uppercase" name="companyCode" id="companyCode" value="${company.companyCode}" placeholder="<spring:message code="com.jinzhiyi.ffs.message.company.company_code" />"> --%>
															<div class="form-control no-border">
																<strong>${company.companyCode}</strong>
															</div>
															
														</div>
													</div>
                                                	<div class="col-lg-2 col-xs-2 col-md-2">
                                                        <div class="form-group">
                                                            <label class="control-label"><spring:message code="com.jinzhiyi.ffs.message.company.company_city_code" /><span class="required">*</span></label>
                                                            <input type="text" class="form-control required_input text-uppercase" name="cityCode" id="cityCode" value="${company.cityCode}" placeholder="<spring:message code="com.jinzhiyi.ffs.message.company.company_city_code" />">
                                                        </div>
                                                    </div>
													<div class="col-lg-2 col-xs-2 col-md-2">
                                                        <div class="form-group">
                                                            <label class="control-label"><spring:message code="com.jinzhiyi.ffs.message.company.company_airport_code" /><span class="required">*</span></label>
                                                            <input type="text" class="form-control required_input text-uppercase" name="airportCode" id="airportCode" value="${company.airportCode}" placeholder="<spring:message code="com.jinzhiyi.ffs.message.company.company_airport_code" />">
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-6 col-xs-6 col-md-6">
                                                        <div class="form-group">
                                                            <label class="control-label"><spring:message code="com.jinzhiyi.ffs.message.company.company_rightCopy" /></label>
															<input type="text" class="form-control disable-ime" name="copyRight" id="copyRight" value="${company.copyRight}" placeholder="<spring:message code="com.jinzhiyi.ffs.message.company.company_rightCopy" />">
                                                        </div>
                                                    </div>
												</div>
												<div class="row">
                                            		<div class="col-lg-3 col-xs-3 col-md-3">
														<div class="form-group">
															<label class="control-label"><spring:message code="com.jinzhiyi.ffs.message.company.company_customerService_phone" /></label>
															<input type="text" class="form-control disable-ime" name="customerServicePhone" id="customerServicePhone" value="${company.customerServicePhone}" placeholder="<spring:message code="com.jinzhiyi.ffs.message.company.company_customerService_phone" />">
														</div>
													</div>
													<div class="col-lg-3 col-xs-3 col-md-3">
                                                        <div class="form-group">
                                                            <label class="control-label"><spring:message code="com.jinzhiyi.ffs.message.company.company_customerService_email" /></label>
                                                            <input type="text" class="form-control disable-ime" name="customerServiceEmail" id="customerServiceEmail" value="${company.customerServiceEmail}" placeholder="<spring:message code="com.jinzhiyi.ffs.message.company.company_customerService_email" />">
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-6 col-xs-6 col-md-6">
														<div class="form-group">
															<label class="control-label"><spring:message code="com.jinzhiyi.ffs.message.company.upload_dir_path" /><span class="required">*</span></label>
															<input type="text" class="form-control required_input disable-ime" name="uploadDirPath" id="uploadDirPath" value="${company.uploadDirPath}" placeholder="<spring:message code="com.jinzhiyi.ffs.message.company.upload_dir_path" />">
														</div>
													</div>
												</div>
												<div class="row">
													<div class="col-lg-6 col-xs-6 col-md-6">
														<div class="form-group">
															<label class="control-label"><spring:message code="com.jinzhiyi.ffs.message.company.address" /></label>
															<input type="text" class="form-control disable-ime" name="address" id="address" value="${company.address}" placeholder="<spring:message code="com.jinzhiyi.ffs.message.company.address" />">
														</div>
													</div>
													<div class="col-lg-3 col-xs-3 col-md-3">
														<div class="form-group">
															<label class="control-label"><spring:message code="com.jinzhiyi.ffs.message.company.postcode" /></label>
															<input type="text" class="form-control disable-ime" name="postcode" id="postcode" value="${company.postcode}" placeholder="<spring:message code="com.jinzhiyi.ffs.message.company.postcode" />">
														</div>
													</div>
													<div class="col-lg-3 col-xs-3 col-md-3">
														<div class="form-group">
															<label class="control-label"><spring:message code="com.jinzhiyi.ffs.message.company.contactorName" /></label>
															<input type="text" class="form-control disable-ime" name="contactorName" id="contactorName" value="${company.contactorName}" placeholder="<spring:message code="com.jinzhiyi.ffs.message.company.contactorName" />">
														</div>
													</div>
												</div>
												<div class="row">
													<div class="col-lg-3 col-xs-3 col-md-3">
														<div class="form-group">
															<label class="control-label"><spring:message code="com.jinzhiyi.ffs.message.company.contactorPhone" /></label>
															<input type="text" class="form-control disable-ime" name="contactorPhone" id="contactorPhone" value="${company.contactorPhone}" placeholder="<spring:message code="com.jinzhiyi.ffs.message.company.contactorPhone" />">
														</div>
													</div>
													<div class="col-lg-3 col-xs-3 col-md-3">
														<div class="form-group">
															<label class="control-label"><spring:message code="com.jinzhiyi.ffs.message.company.contactorFax" /></label>
															<input type="text" class="form-control disable-ime" name="contactorFax" id="contactorFax" value="${company.contactorFax}" placeholder="<spring:message code="com.jinzhiyi.ffs.message.company.contactorFax" />">
														</div>
													</div>
													<div class="col-lg-3 col-xs-3 col-md-3">
														<div class="form-group">
															<label class="control-label"><spring:message code="com.jinzhiyi.ffs.message.company.contactorEmail" /></label>
															<input type="text" class="form-control disable-ime" name="contactorEmail" id="contactorEmail" value="${company.contactorEmail}" placeholder="<spring:message code="com.jinzhiyi.ffs.message.company.contactorEmail" />">
														</div>
													</div>
													<div class="col-lg-3 col-xs-3 col-md-3">
                                                        <div class="form-group">
                                                            <label class="control-label"><spring:message code="com.jinzhiyi.ffs.message.company.upload_file_sld_name" /><span class="required">*</span></label>
                                                            <input type="text" class="form-control required_input disable-ime" name="uploadFileSldName" id="uploadFileSldName" value="${company.uploadFileSldName}" placeholder="<spring:message code="com.jinzhiyi.ffs.message.company.upload_file_sld_name" />">
                                                        </div>
                                                    </div>
												</div>
												<div class="row">
                                                    <div class="col-lg-3 col-xs-3 col-md-3">
                                                        <div class="form-group">
                                                            <label class="control-label"><spring:message code="com.jinzhiyi.ffs.message.company.company_start_dateTime" /></label>
                                                            <input type="text" class="form-control disable-ime date-picker" name="startDateTime" id="startDateTime" value="<fmt:formatDate value="${company.startDateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" 
                                                            	placeholder="<spring:message code="com.jinzhiyi.ffs.message.company.company_start_dateTime" />" autocomplete="off">
                                            			</div>
                                           			</div>
                                                    <div class="col-lg-3 col-xs-3 col-md-3">
                                                        <div class="form-group">
                                                            <label class="control-label"><spring:message code="com.jinzhiyi.ffs.message.company.company_expiry_dateTime" /></label>
                                                        	<input type="text" class="form-control disable-ime date-picker" name="expiryDateTime" id="expiryDateTime" value="<fmt:formatDate value="${company.expiryDateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" 
                                                        		placeholder="<spring:message code="com.jinzhiyi.ffs.message.company.company_expiry_dateTime" />" autocomplete="off">
                                                        </div>
                                                    </div>
												</div>
                                                <h4 class="form-section"><spring:message code="com.jinzhiyi.ffs.message.company.panel.file" /></h4>
                                              	<div class="row">
                                               		<div class="col-lg-2 col-xs-2 col-md-2">
														<div class="form-group">
															<label class="control-label"><spring:message code="com.jinzhiyi.ffs.message.company.company_faviconFileMetaId" /></label>
															<div class="fileinput fileinput-new" data-provides="fileinput">
		                                                        <span class="btn btn-default btn-file input-sm">
		                                                            <span class="fileinput-new"><i class="fa fa-file fileinput-exists"></i> <spring:message code="com.jinzhiyi.ffs.message.common.file.choose" /></span>
		                                                            <span class="fileinput-exists"><i class="fa fa-exchange"></i> <spring:message code="com.jinzhiyi.ffs.message.common.file.edit" /> </span>
		                                                            <input type="file" name="faviconFile" id="faviconFileMetaId">
		                                                        </span>
		                                                        <span class="fileinput-filename"> </span> &nbsp;
		                                                        <a href="javascript:;" class="close fileinput-exists" data-dismiss="fileinput" title="<spring:message code="com.jinzhiyi.ffs.message.common.file.delete" />"> </a>
		                                                    </div>
														</div>
													</div>
													<div class="col-lg-2 col-xs-2 col-md-2">
														<div class="form-group">
															<label class="control-label"><spring:message code="com.jinzhiyi.ffs.message.company.company_businessLogoFileMetaId" /></label>
															<div class="fileinput fileinput-new" data-provides="fileinput">
		                                                        <span class="btn btn-default btn-file input-sm">
		                                                            <span class="fileinput-new"><i class="fa fa-file fileinput-exists"></i> <spring:message code="com.jinzhiyi.ffs.message.common.file.choose" /></span>
		                                                            <span class="fileinput-exists"><i class="fa fa-exchange"></i> <spring:message code="com.jinzhiyi.ffs.message.common.file.edit" /> </span>
		                                                            <input type="file" name="businessLogoFile" id="businessLogoFileMetaId">
		                                                        </span>
		                                                        <span class="fileinput-filename"> </span> &nbsp;
		                                                        <a href="javascript:;" class="close fileinput-exists" data-dismiss="fileinput" title="<spring:message code="com.jinzhiyi.ffs.message.common.file.delete" />"> </a>
		                                                    </div>
														</div>
													</div>
													<div class="col-lg-2 col-xs-2 col-md-2">
														<div class="form-group">
															<label class="control-label"><spring:message code="com.jinzhiyi.ffs.message.company.company_textLogoBigCnFileMetaId" /></label>
															<div class="fileinput fileinput-new" data-provides="fileinput">
		                                                        <span class="btn btn-default btn-file input-sm">
		                                                            <span class="fileinput-new"><i class="fa fa-file fileinput-exists"></i> <spring:message code="com.jinzhiyi.ffs.message.common.file.choose" /> </span>
		                                                            <span class="fileinput-exists"><i class="fa fa-exchange"></i> <spring:message code="com.jinzhiyi.ffs.message.common.file.edit" /> </span>
		                                                            <input type="file" name="textLogoBigCnFile" id="textLogoBigCnFileMetaId">
		                                                        </span>
		                                                        <span class="fileinput-filename"> </span> &nbsp;
		                                                        <a href="javascript:;" class="close fileinput-exists" data-dismiss="fileinput" title="<spring:message code="com.jinzhiyi.ffs.message.common.file.delete" />"> </a>
		                                                    </div>
														</div>
													</div>
													<div class="col-lg-2 col-xs-2 col-md-2">
														<div class="form-group">
															<label class="control-label"><spring:message code="com.jinzhiyi.ffs.message.company.company_textLogoCnFileMetaId" /></label>
															<div class="fileinput fileinput-new" data-provides="fileinput">
		                                                        <span class="btn btn-default btn-file input-sm">
		                                                            <span class="fileinput-new"><i class="fa fa-file fileinput-exists"></i> <spring:message code="com.jinzhiyi.ffs.message.common.file.choose" /></span>
		                                                            <span class="fileinput-exists"><i class="fa fa-exchange"></i> <spring:message code="com.jinzhiyi.ffs.message.common.file.edit" /></span>
		                                                            <input type="file" name="textLogoCnFile" id="textLogoCnFileMetaId">
		                                                        </span>
		                                                        <span class="fileinput-filename"> </span> &nbsp;
		                                                        <a href="javascript:;" class="close fileinput-exists" data-dismiss="fileinput" title="<spring:message code="com.jinzhiyi.ffs.message.common.file.delete" />"> </a>
		                                                    </div>
														</div>
													</div>
													<div class="col-lg-2 col-xs-2 col-md-2">
														<div class="form-group">
															<label class="control-label"><spring:message code="com.jinzhiyi.ffs.message.company.company_textLogoBigEnFileMetaId" /></label>
															<div class="fileinput fileinput-new" data-provides="fileinput">
		                                                        <span class="btn btn-default btn-file input-sm">
		                                                            <span class="fileinput-new"><i class="fa fa-file fileinput-exists"></i> <spring:message code="com.jinzhiyi.ffs.message.common.file.choose" /></span>
		                                                            <span class="fileinput-exists"><i class="fa fa-exchange"></i> <spring:message code="com.jinzhiyi.ffs.message.common.file.edit" /></span>
		                                                            <input type="file" name="textLogoBigEnFile" id="textLogoBigEnFileMetaId">
		                                                        </span>
		                                                        <span class="fileinput-filename"> </span> &nbsp;
		                                                        <a href="javascript:;" class="close fileinput-exists" data-dismiss="fileinput" title="<spring:message code="com.jinzhiyi.ffs.message.common.file.delete" />"></a>
		                                                    </div>
														</div>
													</div>
													<div class="col-lg-2 col-xs-2 col-md-2">
														<div class="form-group">
															<label class="control-label"><spring:message code="com.jinzhiyi.ffs.message.company.company_textLogoEnFileMetaId" /></label>
															<div class="fileinput fileinput-new" data-provides="fileinput">
		                                                        <span class="btn btn-default btn-file input-sm">
		                                                            <span class="fileinput-new"><i class="fa fa-file fileinput-exists"></i> <spring:message code="com.jinzhiyi.ffs.message.common.file.choose" /></span>
		                                                            <span class="fileinput-exists"><i class="fa fa-exchange"></i> <spring:message code="com.jinzhiyi.ffs.message.common.file.edit" /></span>
		                                                            <input type="file" name="textLogoEnFile" id="textLogoEnFileMetaId">
		                                                        </span>
		                                                        <span class="fileinput-filename"> </span> &nbsp;
		                                                        <a href="javascript:;" class="close fileinput-exists" data-dismiss="fileinput" title="<spring:message code="com.jinzhiyi.ffs.message.common.file.delete" />"> </a>
		                                                    </div>
														</div>
													</div>
                                                </div>
                                                <h4 class="form-section"><spring:message code="com.jinzhiyi.ffs.message.company.panel.rols" /></h4>
												<div class="row form-horizontal">
													<div class="col-lg-12 col-xs-12 col-md-12">
														<div class="form-group">
															<label class="control-label text-nowrap col-lg-1 col-xs-1 col-md-1"><spring:message code="com.jinzhiyi.ffs.message.user.role_choose"/>：</label>
															<div class="col-lg-8 col-xs-8 col-md-8">
				                                                <div class="mt-checkbox-inline">	
				                                                	<c:forEach items="${roles}" var="role">
				                                                		<label class="mt-checkbox">
				                                                			<input type="checkbox" id="roleId${role.recId }" name="roleIds" value="${role.recId }" data-error-container="#roleIds_error"><ffs:role roleId="${role.recId }" />
			                                                            	<span></span>
			                                                            </label>
				                                                	</c:forEach>
			                                                    </div>
			                                                    <div id="roleIds_error"> </div>
		                                                   	</div>
	                                                  	</div>
	                                                </div>
												</div>
	                                            <div class="form-actions">
	                                                <div class="row">
	                                                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center">
	                                                     	<button type="submit" class="btn red loading-state-btn" id="submit_btn" data-loading-text='<spring:message code="com.jinzhiyi.ffs.message.common.btn.saving" />'><i class="fa fa-save"></i> <spring:message code="com.jinzhiyi.ffs.message.common.btn.save" /></button>
	                                                     	<a class="btn btn-default margin-left10" href="${pageContext.request.contextPath}/sys/admin/company/companyDetail.do?recId=${company.recId}"><i class="icon-share-alt"></i> <spring:message code="com.jinzhiyi.ffs.message.common.btn.return" /></a>
	                                                    </div>
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
        <script src="${ffsStaticUrl}/metronic_v4.5.6/theme/assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.js" type="text/javascript"></script>
       	<script src="${ffsStaticUrl}/metronic_v4.5.6/theme/assets/global/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js" type="text/javascript"></script>
       	<script src="${ffsStaticUrl}/metronic_v4.5.6/theme/assets/global/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js" type="text/javascript"></script>
   		<script src="${ffsStaticUrl}/metronic_v4.5.6/theme/assets/global/plugins/jquery-validation/js/jquery.validate.min.js" type="text/javascript"></script>
        <script src="${ffsStaticUrl}/metronic_v4.5.6/theme/assets/global/plugins/jquery-validation/js/additional-methods.min.js" type="text/javascript"></script>
		<script type="text/javascript">   
			$(function(){  
				
				//显示已选角色信息
				<c:forEach items="${companyRoles}" var="companyRole">
             		$("#roleId" + ${companyRole.roleId }).attr("checked", "checked");
             	</c:forEach>
				
				//显示日历
				$('.date-picker').datetimepicker({
					language: '<ffs:language localeFlag="true" />',
		            autoclose: true,
		            isRTL: App.isRTL(),
		            format: "yyyy-mm-dd hh:ii:ss",
		            pickerPosition: (App.isRTL() ? "bottom-right" : "bottom-left")
				});
				
				//验证信息
				var modifyCompanyForm = $('#modifyCompanyForm');
	            var error = $('.alert-danger', modifyCompanyForm);
	            var success = $('.alert-success', modifyCompanyForm);

	            modifyCompanyForm.validate({
	                errorElement: 'span', //default input error message container
	                errorClass: 'help-block help-block-error', // default input error message class
	                focusInvalid: false, // do not focus the last invalid input
	                ignore: "",  // validate all fields including form hidden input
	                messages: {
	                	nameCn: {
	                        required: jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.company.company_name_cn.required"/>'),
	                        maxlength: jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.company.company_name_cn.maxlength"/>')
	                    },
	                    nameEn: {
	                    	maxlength: jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.company.company_name_en.maxlength"/>')
	                    },
	                    companyCode: {
	                    	required: jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.company.company_code.required"/>'),
	                    	maxlength: jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.company.company_code.maxlength"/>')
	                    },
	                    cityCode: {
	                    	required: jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.company.company_city_code.required"/>'),
	                    	maxlength: jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.company.company_city_code.maxlength"/>')
	                    },
	                    airportCode: {
	                    	required: jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.company.company_airport_code.required"/>'),
	                    	maxlength: jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.company.company_airport_code.maxlength"/>')
	                    },
	                    copyRight: {
	                    	maxlength: jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.company.company_rightCopy.maxlength"/>')
	                    },
	                    customerServicePhone: {
	                    	maxlength: jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.company.company_customerService_phone.maxlength"/>')
	                    },
	                    customerServiceEmail: {
	                    	maxlength: jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.company.company_customerService_email.maxlength"/>')
	                    },
	                    uploadDirPath: {
	                        required: jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.company.upload_dir_path.required"/>'),
	                    	maxlength: jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.company.upload_dir_path.maxlength"/>')
	                    },
	                    uploadFileSldName: {
	                        required: jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.company.upload_file_sld_name.required"/>'),
	                    	maxlength: jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.company.upload_file_sld_name.maxlength"/>')
	                    },
	                    roleIds: {
	                    	required: jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.company.roleIds.required"/>')
	                    },
	                    address: {
	                    	maxlength: jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.company.address.maxlength"/>')
	                    },
	                    postcode: {
	                    	maxlength: jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.company.postcode.maxlength"/>')
	                    },
	                    contactorName: {
	                    	maxlength: jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.company.contactorName.maxlength"/>')
	                    },
	                    contactorPhone: {
	                    	maxlength: jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.company.contactorPhone.maxlength"/>')
	                    },
	                    contactorFax: {
	                    	maxlength: jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.company.contactorFax.maxlength"/>')
	                    },
	                    contactorEmail: {
	                    	maxlength: jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.company.contactorEmail.maxlength"/>')
	                    }
	                },
	                rules: {
	                	nameCn: {
	                        maxlength: 300,
	                        required: true
	                    },
		                nameEn: {
	                        maxlength: 300
	                    },
	                	companyCode: {
	                        maxlength: 20,
	                        required: true
	                    },
	                    cityCode: {
	                    	maxlength: 3,
	                    	required: true
	                    },
	                    airportCode: {
	                    	maxlength: 3,
	                    	required: true
	                    },
	                    copyRight: {
	                    	maxlength: 300
	                    },
	                    customerServicePhone: {
	                    	maxlength: 300
	                    },
	                    customerServiceEmail: {
	                    	maxlength: 300
	                    },
	                    uploadDirPath: {
	                        maxlength: 300,
	                        required: true
	                    },
	                    uploadFileSldName: {
	                        maxlength: 300,
	                        required: true
	                    },
	                    roleIds: {
	                        required: true
	                    },
	                    address: {
	                    	maxlength: 300
	                    },
	                    postcode: {
	                    	maxlength: 300
	                    },
	                    contactorName: {
	                    	maxlength: 300
	                    },
	                    contactorPhone: {
	                    	maxlength: 300
	                    },
	                    contactorFax: {
	                    	maxlength: 300
	                    },
	                    contactorEmail: {
	                    	maxlength: 300
	                    }
	                },

	                errorPlacement: function (error, element) { // render error placement for each input type
	                    if (element.parent(".input-group").size() > 0) {
	                        error.insertAfter(element.parent(".input-group"));
	                    } else if (element.attr("data-error-container")) { 
	                        error.appendTo(element.attr("data-error-container"));
	                    } else if (element.parents('.radio-list').size() > 0) { 
	                        error.appendTo(element.parents('.radio-list').attr("data-error-container"));
	                    } else if (element.parents('.radio-inline').size() > 0) { 
	                        error.appendTo(element.parents('.radio-inline').attr("data-error-container"));
	                    } else if (element.parents('.checkbox-list').size() > 0) {
	                        error.appendTo(element.parents('.checkbox-list').attr("data-error-container"));
	                    } else if (element.parents('.checkbox-inline').size() > 0) { 
	                        error.appendTo(element.parents('.checkbox-inline').attr("data-error-container"));
	                    } else {
	                        error.insertAfter(element); // for other inputs, just perform default behavior
	                    }
	                },
	                
	                invalidHandler: function (event, validator) { //display error alert on form submit              
	                    success.hide();
	                    error.show();
	                    App.scrollTo(error, -200);
	                },

	                highlight: function (element) { // hightlight error inputs
	                    $(element)
	                        .closest('.form-group').addClass('has-error'); // set error class to the control group
	                },

	                unhighlight: function (element) { // revert the change done by hightlight
	                    $(element)
	                        .closest('.form-group').removeClass('has-error'); // set error class to the control group
	                },

	                success: function (label) {
	                    label
	                        .closest('.form-group').removeClass('has-error'); // set success class to the control group
	                },

	                submitHandler: function (form) {
	                    success.show();
	                    error.hide();
	                    form.submit();
	                }
	            });
	       	});
		</script>
    </body>
</html>