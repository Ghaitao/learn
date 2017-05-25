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
    <title><spring:message code="com.jinzhiyi.ffs.message.customer.audit.financial.page.title"/></title>
    <%@ include file="/jspi/metronic_base_css.jsp"%>
</head>
<body class="page-content-white page-header-fixed">
	<!-- 财务修改临时额度信息 -->
<%@ include file="/WEB-INF/jsp/common/header.jsp"%>
<div class="page-container">
            <div class="page-content-wrapper">
                <div class="page-content">
                <div class="container">
					<div class="row">
			                	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
		                            <div class="portlet light bordered">
                                            <div class="portlet-title">
                                                <div class="caption"><i class="fa fa-edit"></i><spring:message code="com.jinzhiyi.ffs.message.customer.audit.financial.panel.title"/> </div>
                                            </div>
                                            <div class="portlet-body form">
                                                <form:form action="${pageContext.request.contextPath}/customer/doModifyCustomerApplyByAccountant.do" modelAttribute="customer" id="frmModifyCustomerApplyByAccountant" cssClass="horizontal-form">
                                                	<form:hidden path="recId"/>
													<form:hidden path="customerCode"/>
                                                    <div class="form-body">
                                                    	<%@ include file="/WEB-INF/jsp/common/errorMessageTip.jsp"%>
                                                        <div class="row">
                                                            <div class="col-lg-3 col-xs-3 col-md-3">
                                                                <div class="form-group">
                                                                	<!-- 账期 -->
                                                                    <label class="control-label"><spring:message code="com.jinzhiyi.ffs.message.customer.audit.financial.paymentDay"/><span class="required">*</span></label>
			                                                        <form:select path="accountPeriod" itemLabel="label" itemValue="value" items="${accountPeriodList}" cssClass="form-control required_input"></form:select>
                                                                </div>
                                                            </div>
                                                            <div class="col-lg-3 col-xs-3 col-md-3">
                                                                <div class="form-group">
                                                                    <label class="control-label"><spring:message code="com.jinzhiyi.ffs.message.customer.audit.financial.creditLine"/><!-- 信用额度 --><span class="required">*</span></label>
                                                                    <form:input path="creditLimit"  cssClass="form-control required_input text-uppercase"/>
                                                                </div>
                                                            </div>
                                                            <%-- <div class="col-lg-6 col-xs-6 col-md-6">
                                                                <div class="form-group">
                                                                    <label class="control-label"><spring:message code="com.jinzhiyi.ffs.message.customer.audit.financial.temporaryLines"/><!-- 临时额度： --></label>
                                                                    <form:input path="temporaryCreditLimit"  cssClass="form-control text-uppercase"/>
                                                                </div>
                                                            </div> --%>
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
            $('#frmModifyCustomerApplyByAccountant').validate({
                messages: {
                	accountPeriod:{
                		required: jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.customer.audit.financial.paymentDay.required"/>')
                	},
                	creditLimit: {
                		required: jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.customer.audit.financial.creditLine.required"/>'),
                		number: jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.customer.audit.financial.creditLine.required"/>'),
                		maxlength: jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.customer.audit.financial.creditLine.required"/>')
                    }
            		
                },
                rules: {
                	accountPeriod:{
                		required: true
                	},
                	creditLimit: {
                        required: true,
                        number : true,
                        maxlength : 10
                    }
                }
            });
    	});
    </script>	
</body>
</html>