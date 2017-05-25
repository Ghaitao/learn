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
    <title><spring:message code="com.jinzhiyi.ffs.message.customer.audit.adjustmentCustomerRemainingCreditLimit.page.title"/></title>
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
                                                <div class="caption"><i class="fa fa-edit"></i><spring:message code="com.jinzhiyi.ffs.message.customer.audit.adjustmentCustomerRemainingCreditLimit.page.panel.title"/> </div>
                                            </div>
                                            <div class="portlet-body form">
                                                <form:form action="${pageContext.request.contextPath}/customer/doAdjustCustomerRemainingCreditLimit.do" modelAttribute="customer" id="frmAdjustmentCustomerRemainingCreditLimit" cssClass="horizontal-form">
                                                	<form:hidden path="recId"/>
													<form:hidden path="customerCode"/>
                                                    <div class="form-body">
                                                        <div class="row">
                                                            <div class="col-lg-3 col-xs-3 col-md-3">
                                                                <div class="form-group">
                                                                	<!-- 账期 -->
                                                                    <label class="control-label"><spring:message code="com.jinzhiyi.ffs.message.customer.audit.financial.paymentDay"/></label>
			                                                        <p class="form-control form-control-static no-border no-padding-left"><ffs:enum declaringClassName="${customer.accountPeriod.declaringClass.name}" name="${customer.accountPeriod}"/></p>
                                                                </div>
                                                            </div>
                                                            <div class="col-lg-3 col-xs-3 col-md-3">
                                                                <div class="form-group">
                                                                    <label class="control-label"><spring:message code="com.jinzhiyi.ffs.message.customer.audit.financial.creditLine"/><!-- 信用额度 --></label>
                                                                    <p class="form-control form-control-static no-border no-padding-left">${customer.creditLimit}</p>
                                                                </div>
                                                            </div>
                                                            <div class="col-lg-3 col-xs-3 col-md-3">
                                                                <div class="form-group">
                                                                    <label class="control-label"><spring:message code="com.jinzhiyi.ffs.message.customer.audit.financial.remainingCreditLimit"/><!-- 剩余信用额度 --></label>
                                                                    <p class="form-control form-control-static no-border no-padding-left">${customer.remainingCreditLimit}</p>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-lg-3 col-xs-3 col-md-3">
                                                                <div class="form-group">
                                                                	<!-- 临时额度 -->
                                                                    <label class="control-label"><spring:message code="com.jinzhiyi.ffs.message.customer.audit.financial.temporaryLines"/><span class="required">*</span></label>
			                                                        <form:input id="temporaryCreditLimit" path="temporaryCreditLimit"  cssClass="form-control required_input"/>
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
    		
    		$('#temporaryCreditLimit').focus();
    		
            $('#frmAdjustmentCustomerRemainingCreditLimit').validate({
                messages: {
                	temporaryCreditLimit: {
                		required: jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.customer.audit.financial.temporaryLines.required"/>'),
                		number: jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.customer.audit.financial.temporaryLines.required"/>'),
                		maxlength: jQuery.validator.format('<spring:message code="com.jinzhiyi.ffs.message.customer.audit.financial.temporaryLines.required"/>')
                    }
            		
                },
                rules: {
                	temporaryCreditLimit: {
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