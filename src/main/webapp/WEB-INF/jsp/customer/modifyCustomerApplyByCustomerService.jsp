<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/jspi/tag.jsp"%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<head>
	<%@ include file="/jspi/meta.jsp"%>
    <title><spring:message code="com.jinzhiyi.ffs.message.customer.audit.head.title.customerServiceCompanyModify"/></title>
    <%@ include file="/jspi/metronic_base_css.jsp"%>
</head>
<body class="page-content-white page-header-fixed">
<%@ include file="/WEB-INF/jsp/common/header.jsp"%>
<!-- 客服修改公司信息 -->
<div class="page-container">
            <div class="page-content-wrapper">
                <div class="page-content">
                <div class="container">
					<div class="row">
			                	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
		                            <div class="portlet light bordered">
                                            <div class="portlet-title">
                                                <div class="caption"><i class="fa fa-edit"></i><spring:message code="com.jinzhiyi.ffs.message.customer.audit.caption.companyInfoModify"/> </div>
                                            </div>
                                            <div class="portlet-body form">
                                                <form:form action="${pageContext.request.contextPath}/customer/doModifyCustomerApplyByCustomerService.do" modelAttribute="customer" id="frmModifyCustomerApplyByCustomerService" cssClass="horizontal-form">
                                                    <div class="form-body">
                                                    	<form:hidden path="recId"/>
														<form:hidden path="customerCode"/>
														<%@ include file="/WEB-INF/jsp/common/errorMessageTip.jsp"%>
														<div class="row">
                                                        	<div class="col-lg-3 col-xs-3 col-md-3">
                                                                <div class="form-group">
                                                                    <label class="control-label"><spring:message code="com.jinzhiyi.ffs.message.customer.audit.filed.customerCode"/>：<!-- 客户代码 --></label>
                                                                    <p class="form-control-static no-border no-padding-left"><Strong>${customer.customerCode}</Strong></p>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-lg-6 col-xs-6 col-md-6">
                                                                <div class="form-group">
                                                                    <label class="control-label"><spring:message code="com.jinzhiyi.ffs.message.customer.audit.filed.nameCn"/><!-- 中文名称 --><span class="required">*</span></label>
                                                                    <form:input path="nameCn" cssClass="form-control required_input"/>
                                                                </div>
                                                            </div>
                                                            <div class="col-lg-6 col-xs-6 col-md-6">
                                                                <div class="form-group">
                                                                    <label class="control-label"><spring:message code="com.jinzhiyi.ffs.message.customer.audit.filed.nameEn"/><!-- 英文名称 --><span class="required">*</span></label>
                                                                    <form:input path="nameEn" cssClass="form-control disable-ime"/>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-lg-10 col-xs-10 col-md-10">
                                                                <div class="form-group">
                                                                    <label class="control-label"><spring:message code="com.jinzhiyi.ffs.message.customer.audit.filed.address"/><!-- 详细地址 --><span class="required">*</span></label>
                                                                    <form:input path="address" cssClass="form-control required_input"/>
                                                                    <span class="help-block"></span>
                                                                </div>
                                                            </div>
                                                            <div class="col-lg-2 col-xs-2 col-md-2">
                                                                <div class="form-group">
                                                                    <label class="control-label"><spring:message code="com.jinzhiyi.ffs.message.customer.audit.filed.postcode"/><!-- 邮编 --></label>
                                                                    <form:input path="postcode" cssClass="form-control disable-ime"/>
                                                                </div>
                                                            </div>
                                                           
                                                        </div>
                                                        
                                                    </div>
                                                    <div class="form-actions">
                                                        <div class="row">
                                                            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 text-center">
                                                            	<button type="submit" class="btn red loading-state-btn" id="btnSubmit" data-loading-text="<spring:message code="com.jinzhiyi.ffs.message.common.btn.saving" />"><i class="fa fa-save"></i> <spring:message code="com.jinzhiyi.ffs.message.common.btn.save" /></button>
                                                                <a class="btn btn-default margin-left10" href="${pageContext.request.contextPath}/customer/customerDetail.do?customerId=${customer.recId}"><i class="icon-share-alt"></i> <spring:message code="com.jinzhiyi.ffs.message.common.btn.return"/></a>
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
        <%@ include file="/WEB-INF/jsp/common/footer.jsp"%>
   		<%@ include file="/jspi/metronic_base_js.jsp"%>
   		<script src="${ffsStaticUrl}/metronic_v4.5.6/theme/assets/global/plugins/jquery-validation/js/jquery.validate.min.js" type="text/javascript"></script>
    <script src="${ffsStaticUrl}/metronic_v4.5.6/theme/assets/global/plugins/jquery-validation/js/additional-methods.min.js" type="text/javascript"></script>
    <script type="text/javascript">
    	$(function(){
            $('#frmModifyCustomerApplyByCustomerService').validate({
                messages: {
                	/* customerCode: {
                		required: jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.customer.audit.filed.customerCode.required"/>'),
                		maxlength: jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.customer.audit.filed.customerCode.maxLength"/>')
                    }, */
                    nameCn: {
                		required: jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.customer.audit.filed.nameCn.required"/>'),
                		maxlength: jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.customer.audit.filed.nameCn.maxLength"/>')
                    },
                    /* nameEn:{
                    	required: jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.customer.audit.filed.nameEn.required"/>'),
                    	maxlength: jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.customer.audit.filed.nameEn.maxLength"/>')
                    }, */
                    address: {
                    	required:jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.customer.audit.filed.address.required"/>')
                    },
                    postcode: {
                    	required:jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.customer.audit.filed.postcode.maxLength"/>')
                    }
                },
                rules: {
                	/* customerCode: {
                		maxlength:20,
                        required: true
                    }, */
                    nameCn: {
                    	maxlength:300,
                		required: true
                	},
                    /* nameEn: {
                    	maxlength:300,
                		required: true
                	}, */
                	address: {
                		required:true
                	},
                	postcode:{
                		required : false,
                		maxlength:20
                	}
                }
            });
    	});
    </script>	
</body>
</html>